package chaula.eduscore.non_Academic.register_Student;
import chaula.eduscore.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;


public class register_StudentMain  {
    double xOffset = 0;
    double yOffset = 0;
    public void register_StudentWindow(Stage primaryStage) throws IOException {

        System.out.println("register_Student window Started");
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("register_StudentView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Student Registration Panel");
        primaryStage.initStyle(StageStyle.TRANSPARENT);

        // Event handler for mouse pressed
        scene.setOnMousePressed((MouseEvent event) -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        // Event handler for mouse dragged
        scene.setOnMouseDragged((MouseEvent event) -> {
            primaryStage.setX(event.getScreenX() - xOffset);
            primaryStage.setY(event.getScreenY() - yOffset);
        });

        // Show the stage to calculate its size
        primaryStage.show();

        // Get the screen dimensions after the stage is shown
        double screenWidth = Screen.getPrimary().getVisualBounds().getWidth();
        double screenHeight = Screen.getPrimary().getVisualBounds().getHeight();

        // Calculate the position to center the stage
        double stageX = (screenWidth - primaryStage.getWidth()) / 2;
        double stageY = (screenHeight - primaryStage.getHeight()) / 2;

        // Set the stage position after calculating the center
        primaryStage.setX(stageX);
        primaryStage.setY(stageY);

    }
}

