package chaula.eduscore.non_Academic;

import chaula.eduscore.Main;
import chaula.eduscore.non_Academic.edit_Student.edit_StudentMain;
import chaula.eduscore.non_Academic.edit_Teacher.edit_TeacherMain;
import chaula.eduscore.non_Academic.register_Student.register_StudentMain;
import chaula.eduscore.non_Academic.register_Teacher.register_TeacherMain;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static chaula.eduscore.non_Academic.non_AcademicModel.fetchStudentData;

public class non_AcademicController {
    @FXML
    private Button logoutBtn;
    @FXML
    protected void logoutBtnClicked(ActionEvent actionEvent) throws Exception {
        Stage stage = (Stage) logoutBtn.getScene().getWindow();
        stage.close();
        Main newMain = new Main();
        newMain.start(new Stage());
    }
    public void newstudentBtnClicked(ActionEvent actionEvent) throws IOException {
        register_StudentMain registerStudentApp = new register_StudentMain();
        registerStudentApp.register_StudentWindow(new Stage());
        Stage stage = (Stage) logoutBtn.getScene().getWindow();
        stage.close();
    }
    public void editstudentBtnClicked(ActionEvent actionEvent) throws IOException {
        edit_StudentMain editStudentApp = new edit_StudentMain();
        editStudentApp.edit_StudentWindow(new Stage());
        Stage stage = (Stage) logoutBtn.getScene().getWindow();
        stage.close();
    }
    @FXML
    public void reportsstudentBtnClicked(ActionEvent actionEvent) {
        try {
            // Load the Jasper report from JRXML file
            JasperReport jasperReport = JasperCompileManager.compileReport("C:\\Users\\ASUS\\JaspersoftWorkspace\\MyReports\\StudentReport.jrxml");

            // Fetch data for the report
            List<Student> students = non_AcademicModel.fetchStudentData();

            // Convert data to JRBeanCollectionDataSource
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(students);

            // Parameters for the report
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("ReportTitle", "Student Details Report");

            // Fill the report with data
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

            // View the report
            JasperViewer.viewReport(jasperPrint, false);

        } catch (JRException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Report Generation Failed");
            alert.setContentText("An error occurred while generating the report. Please try again.");
            alert.showAndWait();
        }
    }
    public void newteacherBtnClicked(ActionEvent actionEvent) throws IOException {
        register_TeacherMain register_TeacherApp = new register_TeacherMain();
        register_TeacherApp.register_TeacherWindow(new Stage());
        Stage stage = (Stage) logoutBtn.getScene().getWindow();
        stage.close();
    }
    public void editteacherBtnClicked(ActionEvent actionEvent) throws IOException {
        edit_TeacherMain edit_TeacherApp = new edit_TeacherMain();
        edit_TeacherApp.edit_TeacherWindow(new Stage());
        Stage stage = (Stage) logoutBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void reportsteacherBtnClicked(ActionEvent actionEvent) {
        try {
            // Load the Jasper report from JRXML file
            JasperReport jasperReport = JasperCompileManager.compileReport("C:\\Users\\ASUS\\JaspersoftWorkspace\\MyReports\\TeacherReport.jrxml");

            // Fetch data for the report
            List<Teacher> teachers = non_AcademicModel.fetchTeacherData();

            // Convert data to JRBeanCollectionDataSource
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(teachers);

            // Parameters for the report
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("ReportTitle", "Teacher's Details Report");

            // Fill the report with data
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

            // View the report
            JasperViewer.viewReport(jasperPrint, false);

        } catch (JRException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Report Generation Failed");
            alert.setContentText("An error occurred while generating the report. Please try again.");
            alert.showAndWait();
        }
    }

}
