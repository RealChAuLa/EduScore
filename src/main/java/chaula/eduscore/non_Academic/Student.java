package chaula.eduscore.non_Academic;

import java.sql.Date; // Import Date class from java.sql package

public class Student {
    private int id;
    private String firstName;
    private String lastName;
    private String studentClass;
    private int grade;
    private Date birthday;
    private String address;

    // Constructor
    public Student(int id, String firstName, String lastName, String studentClass, int grade, Date birthday, String address) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.studentClass = studentClass;
        this.grade = grade;
        this.birthday = birthday;
        this.address = address;
    }

    // Getters and setters (omitted for brevity)


    public int getstudent_id() {
        return id;
    }

    public String getstudent_first_name() {
        return firstName;
    }

    public String getstudent_last_name() {
        return lastName;
    }

    public String getstudent_class() {
        return studentClass;
    }

    public int getstudent_grade() {
        return grade;
    }

    public Date getstudent_birthday() {
        return birthday;
    }

    public String getstudent_address() {
        return address;
    }

    // toString method to represent the object as a string
    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", studentClass='" + studentClass + '\'' +
                ", grade=" + grade +
                ", birthday=" + birthday +
                ", address='" + address + '\'' +
                '}';
    }
}
