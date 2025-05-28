package chaula.eduscore.non_Academic.edit_Teacher;

import javafx.scene.control.TableView;

import java.sql.*;
import java.time.LocalDate;

public class edit_TeacherModel {
    private static final String DBurl = "jdbc:mysql://localhost:3306/eduscore";
    private static final String DBusername = "root";
    private static final String DBpassword = "";

    private int teacher_id;
    private String teacher_first_name;
    private String teacher_last_name;
    private String teacher_subject;
    private LocalDate teacher_birthday;
    private String teacher_address;
    private String teacher_username;
    private String teacher_password;
    public edit_TeacherModel() {}
    public edit_TeacherModel(int id, String firstName, String lastName, String subject, LocalDate birthday, String address, String username, String password) {
        teacher_id = id;
        teacher_first_name = firstName;
        teacher_last_name = lastName;
        teacher_subject = subject;
        teacher_birthday = birthday;
        teacher_address = address;
        teacher_username = username;
        teacher_password = password;
    }
    public int getTeacher_id() {
        return teacher_id;
    }
    public String getTeacher_first_name() {
        return teacher_first_name;
    }
    public String getTeacher_last_name() {
        return teacher_last_name;
    }
    public String getTeacher_subject() {
        return teacher_subject;
    }
    public LocalDate getTeacher_birthday() {
        return teacher_birthday;
    }
    public String getTeacher_address() {
        return teacher_address;
    }
    public String getTeacher_username() {
        return teacher_username;
    }
    public String getTeacher_password() {
        return teacher_password;
    }
    public void populateTable(String name, TableView<edit_TeacherModel> teacherTable) {
        try (Connection connection = DriverManager.getConnection(DBurl, DBusername, DBpassword)) {
            String query = "SELECT * FROM teachertbl WHERE teacher_first_name LIKE ? OR teacher_last_name LIKE ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "%" + name + "%");
            preparedStatement.setString(2, "%" + name + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("check");

            while (resultSet.next()) {
                edit_TeacherModel teacher = new edit_TeacherModel(
                        resultSet.getInt("teacher_id"),
                        resultSet.getString("teacher_first_name"),
                        resultSet.getString("teacher_last_name"),
                        resultSet.getString("teacher_subject"),
                        resultSet.getDate("teacher_birthday").toLocalDate(),
                        resultSet.getString("teacher_address"),
                        resultSet.getString("teacher_username"),
                        resultSet.getString("teacher_password")
                );
                teacherTable.getItems().add(teacher);
            }
            teacherTable.refresh();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public boolean updateTeacher(int teacherId, String firstName, String lastName, String subject, LocalDate birthday, String address, String username, String password) {
        String query = "UPDATE teachertbl SET teacher_first_name = ?, teacher_last_name = ?, teacher_subject = ?, teacher_birthday = ?, teacher_address = ?, teacher_username = ?, teacher_password = ? WHERE teacher_id = ?";
        try (Connection connection = DriverManager.getConnection(DBurl, DBusername, DBpassword);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, subject);
            preparedStatement.setDate(4, Date.valueOf(birthday));
            preparedStatement.setString(5, address);
            preparedStatement.setString(6, username);
            preparedStatement.setString(7, password);
            preparedStatement.setInt(8, teacherId);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean deleteTeacher(int teacherId) {
        String query = "DELETE FROM teachertbl WHERE teacher_id = ?";
        try (Connection connection = DriverManager.getConnection(DBurl, DBusername, DBpassword);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, teacherId);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
