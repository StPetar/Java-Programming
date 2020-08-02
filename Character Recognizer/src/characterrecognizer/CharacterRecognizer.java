package characterrecognizer;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class CharacterRecognizer extends Application {

    public static void main(String[] args) {

        launch(args);
    }

    public void start(Stage myStage) {

        // Give the stage a title
        myStage.setTitle("Character Recognizer");

        // Create the HBox
        HBox rootNode = new HBox(10);
        rootNode.setPadding(new Insets(10, 10, 10, 10));

        //Initialize the Grid Canvas
        GridCanvas grid = new GridCanvas();

        //Initialize the Recognizer Controller
        RecognizerController controller = new RecognizerController(grid);

        // Create a scene.
        Scene myScene = new Scene(rootNode);
        rootNode.getChildren().addAll(controller, grid);

        // Set the scene on the stage
        myStage.setScene(myScene);

        // Parametrize and show the stage and its scene
        myStage.sizeToScene();
        myStage.setResizable(false);
        myStage.show();
    }
}