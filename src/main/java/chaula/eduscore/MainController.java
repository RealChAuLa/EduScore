package chaula.eduscore;

import chaula.eduscore.non_Academic.non_AcademicMain;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;


public class MainController {
    @FXML
    private TextField usernameBox;
    @FXML
    private PasswordField passwordBox;


    @FXML
    protected void loginBtnClick() throws IOException {
        MainModel model = new MainModel();
        String username = usernameBox.getText();
        String password = passwordBox.getText();
        // check username password is filled
        if (username.isEmpty() || password.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Failed");
            alert.setHeaderText(null);
            alert.setContentText("Please fill in both username and password!");
            alert.showAndWait();
            return;
        }
        if (model.isValidUser(username, password)) {
            System.out.println("Login successful");
            // Close the current window
            Stage currentStage = (Stage) usernameBox.getScene().getWindow();
            currentStage.close();
            try {
                non_AcademicMain nonAcademicApp = new non_AcademicMain();
                nonAcademicApp.non_AcademicWindow(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Login failed");
            // Show a dialog indicating failed login
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Failed");
            alert.setHeaderText(null);
            alert.setContentText("Invalid username or password!");
            alert.showAndWait();
        }
    }

    @FXML
    protected void loginCloseBtnClick() {
        // Get the reference to the current stage
        Stage stage = (Stage) usernameBox.getScene().getWindow();
        // Close the stage
        stage.close();
    }
}