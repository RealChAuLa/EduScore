package chaula.eduscore;

import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MainModel {
    // Database connection parameters
    private final String DBurl = "jdbc:mysql://localhost:3306/EduScore";
    private final String DBusername = "root";
    private final String DBpassword = "";

    // Method to check if the username and password combination is valid
    public boolean isValidUser(String username, String password) {
        String query = "SELECT * FROM userTbl WHERE username = ? AND password = ?";
        try (Connection conn = DriverManager.getConnection(DBurl, DBusername, DBpassword);
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next(); // If there is a row, the username and password are valid
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Failed");
            alert.setHeaderText("Database Error");
            alert.setContentText(String.valueOf(e));
            alert.showAndWait();
            e.printStackTrace();
            return false;
        }
    }
}
