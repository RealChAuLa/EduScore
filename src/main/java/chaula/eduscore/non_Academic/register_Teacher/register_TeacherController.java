package chaula.eduscore.non_Academic.register_Teacher;

import chaula.eduscore.non_Academic.non_AcademicMain;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class register_TeacherController {
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
    private register_TeacherModel teacherModel;
    public void initialize() {
        // Initialize the model
        teacherModel = new register_TeacherModel();

        // Add items to subjectComboBox
        subjectComboBox.setItems(FXCollections.observableArrayList(
                "Mathematics", "Science", "English", "History", "Religion", "Sinhala",
                "SubjectGroup1", "SubjectGroup2", "SubjectGroup3"
        ));
    }
    public void registerBtnClicked(ActionEvent actionEvent) {
        String firstName = firstnameBox.getText();
        String lastName = lastnameBox.getText();
        String subject = subjectComboBox.getValue();
        String username = usernameBox.getText();
        String password = passwordBox.getText();
        String address = addressBox.getText();
        String birthday = null;

        // Check whether all the fields are filled
        if (firstName.isEmpty() || lastName.isEmpty() || subject.isEmpty() || username.isEmpty() || password.isEmpty() || address.isEmpty() || birthdayBox.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText("Please fill all the fields");
            alert.showAndWait();
            return;
        }

        birthday = birthdayBox.getValue().toString();

        // Save to database using the model
        if (teacherModel.insertTeacher(firstName, lastName, subject, birthday, address, username, password)) {
            // Display success alert
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText("Teacher registered successfully!");
            alert.showAndWait();

            // Clear all fields
            clearFields();
        }
    }
    public void clearBtnClicked(ActionEvent actionEvent) {
        clearFields();
    }
    private void clearFields() {
        firstnameBox.clear();
        lastnameBox.clear();
        subjectComboBox.getSelectionModel().clearSelection();
        usernameBox.clear();
        passwordBox.clear();
        addressBox.clear();
        birthdayBox.setValue(null);
    }
    public void gobackBtnClicked(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) firstnameBox.getScene().getWindow();
        stage.close();
        non_AcademicMain nonAcademicApp = new non_AcademicMain();
        nonAcademicApp.non_AcademicWindow(new Stage());
    }
}
