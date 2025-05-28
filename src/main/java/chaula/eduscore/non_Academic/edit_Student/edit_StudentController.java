package chaula.eduscore.non_Academic.edit_Student;

import chaula.eduscore.non_Academic.non_AcademicMain;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.Optional;

public class edit_StudentController {
    @FXML
    private TableColumn<edit_StudentModel, String> addressColumn;
    @FXML
    private TableColumn<edit_StudentModel, LocalDate> birthdayColumn;
    @FXML
    private TableColumn<edit_StudentModel, String> classColumn;
    @FXML
    private TableColumn<edit_StudentModel, String> first_nameColumn;
    @FXML
    private TableColumn<edit_StudentModel, Integer> gradeColumn;
    @FXML
    private TableColumn<edit_StudentModel, Integer> student_idColumn;
    @FXML
    private TableColumn<edit_StudentModel, String> last_nameColumn;
    @FXML
    private TableView<edit_StudentModel> studentTable;
    @FXML
    private TextField searchBox;
    @FXML
    private Label studentidLabel;
    @FXML
    private ComboBox<String> gradeComboBox;
    @FXML
    private ComboBox<String> classComboBox;
    @FXML
    private TextField firstnameBox;
    @FXML
    private TextField lastnameBox;
    @FXML
    private TextArea addressBox;
    @FXML
    private DatePicker birthdayBox;
    private edit_StudentModel model;
    public void initialize() {
        model = new edit_StudentModel();
        student_idColumn.setCellValueFactory(new PropertyValueFactory<>("student_id"));
        first_nameColumn.setCellValueFactory(new PropertyValueFactory<>("student_first_name"));
        last_nameColumn.setCellValueFactory(new PropertyValueFactory<>("student_last_name"));
        classColumn.setCellValueFactory(new PropertyValueFactory<>("student_class"));
        gradeColumn.setCellValueFactory(new PropertyValueFactory<>("student_grade"));
        birthdayColumn.setCellValueFactory(new PropertyValueFactory<>("student_birthday"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("student_address"));
    }
    @FXML
    public void searchBoxTextChanged(KeyEvent event) {
        String searchText = searchBox.getText();
        if (!searchText.isEmpty()) {
            studentTable.getItems().clear(); // Clear existing data
            model.populateTable(searchText,studentTable); // Populate table with search results
        }
    }
    public void gobackBtnClicked(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) studentidLabel.getScene().getWindow();
        stage.close();
        non_AcademicMain nonAcademicApp = new non_AcademicMain();
        nonAcademicApp.non_AcademicWindow(new Stage());
    }
    public void updateBtnClicked(ActionEvent actionEvent) {
        try {
            int studentId = Integer.parseInt(studentidLabel.getText());
            String firstName = firstnameBox.getText();
            String lastName = lastnameBox.getText();
            String studentClass = classComboBox.getValue();
            int grade = Integer.parseInt(gradeComboBox.getValue());
            LocalDate birthday = birthdayBox.getValue();
            String address = addressBox.getText();

            // Create a confirmation alert
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Confirm Update");
            confirmationAlert.setHeaderText(null);
            confirmationAlert.setContentText("Are you sure you want to update details for Student ID: " + studentId + "?");

            // Show the confirmation alert and wait for the user's response
            Optional<ButtonType> result = confirmationAlert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {

                boolean success = model.updateStudent(studentId, firstName, lastName, grade, studentClass, birthday, address);

                if (success) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setHeaderText(null);
                    alert.setContentText("Student updated successfully.");
                    alert.showAndWait();
                    // Optionally, refresh the table or clear the form
                    searchBoxTextChanged(null); // Refresh the table with the current search text
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Update Failed");
                    alert.setContentText("An error occurred while updating the student. Please try again.");
                    alert.showAndWait();
                }
            }
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid Input");
            alert.setContentText("Please ensure all fields are filled out correctly.");
            alert.showAndWait();
        }
    }
    public void clearBtnClicked(ActionEvent actionEvent) {
        studentidLabel.setText(null);
        firstnameBox.clear();
        lastnameBox.clear();
        addressBox.clear();
        gradeComboBox.setValue(null);
        classComboBox.setValue(null);
        birthdayBox.setValue(null);
    }
    public void deleteBtnClicked(ActionEvent actionEvent) {
        try {
            int studentId = Integer.parseInt(studentidLabel.getText());

            // Create a confirmation alert
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Confirm Delete");
            confirmationAlert.setHeaderText(null);
            confirmationAlert.setContentText("Are you sure you want to DELETE Student ID: " + studentId + "?");

            // Show the confirmation alert and wait for the user's response
            Optional<ButtonType> result = confirmationAlert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {

                boolean success = model.deleteStudent(studentId);

                if (success) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setHeaderText(null);
                    alert.setContentText("Student deleted successfully.");
                    alert.showAndWait();
                    // Optionally, refresh the table or clear the form
                    clearBtnClicked(null); // Clear the form
                    searchBoxTextChanged(null); // Refresh the table with the current search text
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Delete Failed");
                    alert.setContentText("An error occurred while deleting the student. Please try again.");
                    alert.showAndWait();
                }
            }
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid Input");
            alert.setContentText("Please ensure a valid student is selected.");
            alert.showAndWait();
        }
    }
    public void studentTableClicked(MouseEvent mouseEvent) {
        // Get the selected student model
        edit_StudentModel selectedStudent = studentTable.getSelectionModel().getSelectedItem();

        if (selectedStudent != null) {
            // Set values to corresponding text fields
            studentidLabel.setText(String.valueOf(selectedStudent.getStudent_id()));
            firstnameBox.setText(selectedStudent.getStudent_first_name());
            lastnameBox.setText(selectedStudent.getStudent_last_name());
            classComboBox.setValue(selectedStudent.getStudent_class());
            gradeComboBox.setValue(String.valueOf(selectedStudent.getStudent_grade()));
            birthdayBox.setValue(selectedStudent.getStudent_birthday());
            addressBox.setText(selectedStudent.getStudent_address());
        }
    }
}
