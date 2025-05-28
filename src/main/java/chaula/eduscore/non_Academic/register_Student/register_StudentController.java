package chaula.eduscore.non_Academic.register_Student;

import chaula.eduscore.non_Academic.non_AcademicMain;
import chaula.eduscore.non_Academic.non_AcademicModel;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class register_StudentController {
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
    public void initialize() {
        // Add items to gradeComboBox
        gradeComboBox.setItems(FXCollections.observableArrayList(
                "6", "7", "8", "9"
        ));

        // Add items to classComboBox
        classComboBox.setItems(FXCollections.observableArrayList(
                "A", "B"
        ));
    }
    public void registerBtnClicked(ActionEvent actionEvent) {
        String firstName = firstnameBox.getText();
        String lastName = lastnameBox.getText();
        String grade = gradeComboBox.getValue();
        String studentClass = classComboBox.getValue();
        String address = addressBox.getText();
        String birthday = null;
        //check wheather all the fields are filled
        if (firstName.isEmpty() || lastName.isEmpty() || grade.isEmpty() || studentClass.isEmpty() || address.isEmpty() || birthdayBox.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText("Please fill all the fields");
            alert.showAndWait();
            return;
        }
        else {
            birthday = birthdayBox.getValue().toString();
            // Call the insertStudent method from StudentModel to insert the data into the database
            register_StudentModel register_studentModel = new register_StudentModel();
            if(register_studentModel.insertStudent(firstName, lastName, grade, studentClass, birthday, address)){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle(null);
                alert.setHeaderText(null);
                alert.setContentText("Successfully Registered");
                alert.showAndWait();
            }
            clearFields();
        }
    }
    public void clearBtnClicked(ActionEvent actionEvent) {
        clearFields();
    }
    public void clearFields() {
        firstnameBox.clear();
        lastnameBox.clear();
        gradeComboBox.getSelectionModel().clearSelection();
        classComboBox.getSelectionModel().clearSelection();
        addressBox.clear();
        birthdayBox.getEditor().clear();
    }
    public void gobackBtnClicked(ActionEvent actionEvent) throws IOException {
         Stage stage = (Stage) firstnameBox.getScene().getWindow();
         stage.close();
         non_AcademicMain nonAcademicApp = new non_AcademicMain();
         nonAcademicApp.non_AcademicWindow(new Stage());
    }
}



