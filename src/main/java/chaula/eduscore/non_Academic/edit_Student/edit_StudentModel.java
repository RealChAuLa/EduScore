package chaula.eduscore.non_Academic.edit_Student;

import javafx.scene.control.TableView;
import java.sql.*;
import java.time.LocalDate;

public class edit_StudentModel {
    // Database connection parameters
    private static final String DBurl = "jdbc:mysql://localhost:3306/EduScore";
    private static final String DBusername = "root";
    private static final String DBpassword = "";
    // Table columns
    private int student_id;
    private String student_first_name;
    private String student_last_name;
    private int student_grade;
    private String student_class;
    private LocalDate student_birthday;
    private String student_address;
    // Default constructor
    public edit_StudentModel() {}
    // Parameterized constructor
    public edit_StudentModel(int id, String firstName, String lastName, int grade, String studentClass, LocalDate birthday, String address) {
        student_id = id;
        student_first_name = firstName;
        student_last_name = lastName;
        student_grade = grade;
        student_class = studentClass;
        student_birthday = birthday;
        student_address = address;
    }
    // Getters and setters
    public int getStudent_id() {
        return student_id;
    }
    public String getStudent_first_name() {
        return student_first_name;
    }
    public int getStudent_grade() {
        return student_grade;
    }
    public String getStudent_last_name() {
        return student_last_name;
    }
    public String getStudent_class() {
        return student_class;
    }
    public LocalDate getStudent_birthday() {
        return student_birthday;
    }
    public String getStudent_address() {
        return student_address;
    }
    public void populateTable(String name, TableView<edit_StudentModel> studentTable) {
        try (Connection connection = DriverManager.getConnection(DBurl,DBusername,DBpassword)){
            String query = "SELECT * FROM studenttbl WHERE student_first_name LIKE ? OR student_last_name LIKE ?" ;
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "%" + name + "%"); // % is wildcard for any characters
            preparedStatement.setString(2, "%" + name + "%");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                edit_StudentModel student = new edit_StudentModel(
                        resultSet.getInt("student_id"),
                        resultSet.getString("student_first_name"),
                        resultSet.getString("student_last_name"),
                        resultSet.getInt("student_grade"),
                        resultSet.getString("student_class"),
                        resultSet.getDate("student_birthday").toLocalDate(),
                        resultSet.getString("student_address")
                );
                studentTable.getItems().add(student);
            }
            studentTable.refresh();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public boolean updateStudent(int studentId, String firstName, String lastName, int grade, String studentClass, LocalDate birthday, String address) {
        String query = "UPDATE studenttbl SET student_first_name = ?, student_last_name = ?, student_grade = ?, student_class = ?, student_birthday = ?, student_address = ? WHERE student_id = ?";
        try (Connection connection = DriverManager.getConnection(DBurl, DBusername, DBpassword);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, grade);
            preparedStatement.setString(4, studentClass);
            preparedStatement.setDate(5, Date.valueOf(birthday));
            preparedStatement.setString(6, address);
            preparedStatement.setInt(7, studentId);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0; // Return true if at least one row was updated
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean deleteStudent(int studentId) {
        String query = "DELETE FROM studenttbl WHERE student_id = ?";
        try (Connection connection = DriverManager.getConnection(DBurl, DBusername, DBpassword);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, studentId);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0; // Return true if at least one row was deleted
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
