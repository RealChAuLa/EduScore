package chaula.eduscore.non_Academic;

import java.sql.Date; // Import Date class from java.sql package
import java.time.LocalDate;

public class Teacher {
    private int teacher_id;
    private String teacher_first_name;
    private String teacher_last_name;
    private String teacher_subject;
    private Date teacher_birthday;
    private String teacher_address;
    private String teacher_username;
    private String teacher_password;

    // Constructor
    public Teacher(int id, String firstName, String lastName, String subject, Date birthday, String address, String username, String password) {
        teacher_id = id;
        teacher_first_name = firstName;
        teacher_last_name = lastName;
        teacher_subject = subject;
        teacher_birthday = birthday;
        teacher_address = address;
        teacher_username = username;
        teacher_password = password;
    }

    // Getters
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
    public Date getTeacher_birthday() {
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

    // toString method to represent the object as a string
    @Override
    public String toString() {
        return "Teacher{" +
                "teacher_id=" + teacher_id +
                ", teacher_first_name='" + teacher_first_name + '\'' +
                ", teacher_last_name='" + teacher_last_name + '\'' +
                ", teacher_subject='" + teacher_subject + '\'' +
                ", teacher_birthday=" + teacher_birthday +
                ", teacher_address='" + teacher_address + '\'' +
                ", teacher_username='" + teacher_username + '\'' +
                ", teacher_password='" + teacher_password + '\'' +
                '}';
    }

}
