package chaula.eduscore.non_Academic.register_Student;

import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class register_StudentModel {
    private static final String URL = "jdbc:mysql://localhost:3306/eduscore";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    public boolean insertStudent(String firstName, String lastName, String grade, String studentClass, String birthday, String address) {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String query = "INSERT INTO studenttbl (student_first_name, student_last_name, student_grade, student_class, student_birthday, student_address) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, grade);
            preparedStatement.setString(4, studentClass);
            preparedStatement.setString(5, birthday);
            preparedStatement.setString(6, address);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Database Error");
            alert.setContentText(String.valueOf(e));
            alert.showAndWait();
            e.printStackTrace();
            return false; // Return false in case of any exceptions
        }
    }

}
