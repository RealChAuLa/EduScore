package chaula.eduscore.non_Academic.edit_Teacher;

import chaula.eduscore.non_Academic.non_AcademicMain;
import javafx.collections.FXCollections;
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

public class edit_TeacherController {
    @FXML
    private TableColumn<edit_TeacherModel, Integer> teacher_idColumn;
    @FXML
    private TableColumn<edit_TeacherModel, String> first_nameColumn;
    @FXML
    private TableColumn<edit_TeacherModel, String> last_nameColumn;
    @FXML
    private TableColumn<edit_TeacherModel, String> subjectColumn;
    @FXML
    private TableColumn<edit_TeacherModel, LocalDate> birthdayColumn;
    @FXML
    private TableColumn<edit_TeacherModel, String> addressColumn;
    @FXML
    private TableColumn<edit_TeacherModel, String> usernameColumn;
    @FXML
    private TableColumn<edit_TeacherModel, String> passwordColumn;
    @FXML
    private TableView<edit_TeacherModel> teacherTable;
    @FXML
    private TextField searchBox;
    @FXML
    private Label teacheridLabel;
    @FXML
    private ComboBox<String> subjectComboBox;
    @FXML
    private TextField firstnameBox;
    @FXML
    private TextField lastnameBox;
    @FXML
    private TextField usernameBox;
    @FXML
    private TextField passwordBox;
    @FXML
    private TextArea addressBox;
    @FXML
    private DatePicker birthdayBox;
    private edit_TeacherModel model;
    public void initialize() {
        model = new edit_TeacherModel();

        teacher_idColumn.setCellValueFactory(new PropertyValueFactory<>("teacher_id"));
        first_nameColumn.setCellValueFactory(new PropertyValueFactory<>("teacher_first_name"));
        last_nameColumn.setCellValueFactory(new PropertyValueFactory<>("teacher_last_name"));
        subjectColumn.setCellValueFactory(new PropertyValueFactory<>("teacher_subject"));
        birthdayColumn.setCellValueFactory(new PropertyValueFactory<>("teacher_birthday"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("teacher_address"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("teacher_username"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("teacher_password"));

        subjectComboBox.setItems(FXCollections.observableArrayList(
                "Mathematics", "Science", "English", "History", "Religion", "Sinhala",
                "SubjectGroup1", "SubjectGroup2", "SubjectGroup3"
        ));
    }
    @FXML
    public void searchBoxTextChanged(KeyEvent event) {
        String searchText = searchBox.getText();
        if (!searchText.isEmpty()) {
            teacherTable.getItems().clear();
            model.populateTable(searchText, teacherTable);
        }
    }
    public void gobackBtnClicked(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) teacheridLabel.getScene().getWindow();
        stage.close();
        non_AcademicMain nonAcademicApp = new non_AcademicMain();
        nonAcademicApp.non_AcademicWindow(new Stage());
    }
    public void updateBtnClicked(ActionEvent actionEvent) {
        try {
            int teacherId = Integer.parseInt(teacheridLabel.getText());
            String firstName = firstnameBox.getText();
            String lastName = lastnameBox.getText();
            String subject = subjectComboBox.getValue();
            String username = usernameBox.getText();
            String password = passwordBox.getText();
            LocalDate birthday = birthdayBox.getValue();
            String address = addressBox.getText();

            // Create a confirmation alert
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Confirm Update");
            confirmationAlert.setHeaderText(null);
            confirmationAlert.setContentText("Are you sure you want to update details for Teacher ID: " + teacherId + "?");

            // Show the confirmation alert and wait for the user's response
            Optional<ButtonType> result = confirmationAlert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                boolean success = model.updateTeacher(teacherId, firstName, lastName, subject, birthday, address, username, password);

                if (success) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setHeaderText(null);
                    alert.setContentText("Teacher updated successfully.");
                    alert.showAndWait();
                    searchBoxTextChanged(null);
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Update Failed");
                    alert.setContentText("An error occurred while updating the teacher. Please try again.");
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
        teacheridLabel.setText(null);
        firstnameBox.clear();
        lastnameBox.clear();
        addressBox.clear();
        subjectComboBox.setValue(null);
        usernameBox.clear();
        passwordBox.clear();
        birthdayBox.setValue(null);
    }
    public void deleteBtnClicked(ActionEvent actionEvent) {
        try {
            int teacherId = Integer.parseInt(teacheridLabel.getText());

            // Create a confirmation alert
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Confirm Delete");
            confirmationAlert.setHeaderText(null);
            confirmationAlert.setContentText("Are you sure you want to DELETE Teacher ID: " + teacherId + "?");

            // Show the confirmation alert and wait for the user's response
            Optional<ButtonType> result = confirmationAlert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {

                boolean success = model.deleteTeacher(teacherId);

                if (success) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setHeaderText(null);
                    alert.setContentText("Teacher deleted successfully.");
                    alert.showAndWait();
                    clearBtnClicked(null);
                    searchBoxTextChanged(null);
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Delete Failed");
                    alert.setContentText("An error occurred while deleting the teacher. Please try again.");
                    alert.showAndWait();
                }
            }
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid Input");
            alert.setContentText("Please ensure a valid teacher is selected.");
            alert.showAndWait();
        }
    }
    public void teacherTableClicked(MouseEvent mouseEvent) {
        edit_TeacherModel selectedTeacher = teacherTable.getSelectionModel().getSelectedItem();

        if (selectedTeacher != null) {
            teacheridLabel.setText(String.valueOf(selectedTeacher.getTeacher_id()));
            firstnameBox.setText(selectedTeacher.getTeacher_first_name());
            lastnameBox.setText(selectedTeacher.getTeacher_last_name());
            subjectComboBox.setValue(selectedTeacher.getTeacher_subject());
            usernameBox.setText(selectedTeacher.getTeacher_username());
            passwordBox.setText(selectedTeacher.getTeacher_password());
            birthdayBox.setValue(selectedTeacher.getTeacher_birthday());
            addressBox.setText(selectedTeacher.getTeacher_address());
        }
    }
}
