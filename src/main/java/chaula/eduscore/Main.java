package chaula.eduscore;
import chaula.eduscore.non_Academic.non_AcademicMain;
import chaula.eduscore.non_Academic.register_Student.register_StudentMain;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
public class Main extends Application {
    // Variables to store mouse cursor position
    private double xOffset = 0;
    private double yOffset = 0;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("EduScore: Academic Performance Tracker");
        stage.initStyle(StageStyle.TRANSPARENT);

        // Event handler for mouse pressed
        scene.setOnMousePressed((MouseEvent event) -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        // Event handler for mouse dragged
        scene.setOnMouseDragged((MouseEvent event) -> {
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
        });

        // Show the stage to calculate its size
        stage.show();

        // Get the screen dimensions after the stage is shown
        double screenWidth = Screen.getPrimary().getVisualBounds().getWidth();
        double screenHeight = Screen.getPrimary().getVisualBounds().getHeight();

        // Calculate the position to center the stage
        double stageX = (screenWidth - stage.getWidth()) / 2;
        double stageY = (screenHeight - stage.getHeight()) / 2;

        // Set the stage position after calculating the center
        stage.setX(stageX);
        stage.setY(stageY);

        //ByPass LOgin
        //non_AcademicMain nonAcademicApp = new non_AcademicMain();
        //nonAcademicApp.non_AcademicWindow(new Stage());

    }

    /*public static void main(String[] args) {
        launch();
    }*/
}