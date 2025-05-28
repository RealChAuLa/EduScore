package chaula.eduscore.non_Academic.register_Teacher;

import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class register_TeacherModel {
    private static final String URL = "jdbc:mysql://localhost:3306/eduscore";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    public boolean insertTeacher(String firstName, String lastName, String subject, String birthday, String address, String username, String password) {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String query = "INSERT INTO teachertbl (teacher_first_name, teacher_last_name, teacher_subject, teacher_birthday, teacher_address, teacher_username, teacher_password) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, subject);
            preparedStatement.setString(4, birthday);
            preparedStatement.setString(5, address);
            preparedStatement.setString(6, username);
            preparedStatement.setString(7, password);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Database Error");
            alert.setContentText(String.valueOf(e));
            alert.showAndWait();
            e.printStackTrace();
            return false;
        }
    }
}

