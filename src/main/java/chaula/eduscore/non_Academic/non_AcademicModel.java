package chaula.eduscore.non_Academic;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class non_AcademicModel {
    // JDBC URL, username, and password of MySQL server
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/eduscore";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    // Method to fetch all student data from the database
    public static List<Student> fetchStudentData() {
        List<Student> students = new ArrayList<>();

        // Create a connection to the database
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            // Create a statement
            try (Statement statement = connection.createStatement()) {
                // Execute the query
                ResultSet resultSet = statement.executeQuery("SELECT * FROM studenttbl");

                // Iterate through the result set and create Student objects
                while (resultSet.next()) {
                    int id = resultSet.getInt("student_id");
                    String firstName = resultSet.getString("student_first_name");
                    String lastName = resultSet.getString("student_last_name");
                    String studentClass = resultSet.getString("student_class");
                    int grade = resultSet.getInt("student_grade");
                    Date birthday = resultSet.getDate("student_birthday");
                    String address = resultSet.getString("student_address");

                    // Create a new Student object and add it to the list
                    Student student = new Student(id, firstName, lastName, studentClass, grade, birthday, address);
                    students.add(student);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle SQLException appropriately
        }

        return students;
    }

    public static List<Teacher> fetchTeacherData() {
        List<Teacher> teachers = new ArrayList<>();

        // Create a connection to the database
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            // Create a statement
            try (Statement statement = connection.createStatement()) {
                // Execute the query
                ResultSet resultSet = statement.executeQuery("SELECT * FROM teachertbl");

                // Iterate through the result set and create Student objects
                while (resultSet.next()) {
                    int teacher_id = resultSet.getInt("teacher_id");
                    String teacher_first_name = resultSet.getString("teacher_first_name");
                    String teacher_last_name = resultSet.getString("teacher_last_name");
                    String teacher_subject = resultSet.getString("teacher_subject");
                    Date teacher_birthday = resultSet.getDate("teacher_birthday");
                    String teacher_address = resultSet.getString("teacher_address");
                    String teacher_username = resultSet.getString("teacher_username");
                    String teacher_password = resultSet.getString("teacher_password");

                    // Create a new Teacher object and add it to the list
                    Teacher teacher = new Teacher(teacher_id, teacher_first_name, teacher_last_name, teacher_subject, teacher_birthday, teacher_address, teacher_username, teacher_password);
                    teachers.add(teacher);
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle SQLException appropriately
        }

        return teachers;
    }
}
