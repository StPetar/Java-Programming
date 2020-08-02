package gridpainter;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GridPainter extends Application {

    public static void main(String[] args) {

        launch(args);
    }

    public void start(Stage myStage) {

        // Give the stage a title
        myStage.setTitle("Grid Painter");

        // Create the HBox
        HBox rootNode = new HBox(10);

        // Set border
        rootNode.setBorder(new Border(new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
                BorderWidths.DEFAULT, new Insets(10, 10, 10, 10))));

        //Initialize the Grid Canvas
        GridCanvas grid = new GridCanvas();

        // Create a scene.
        Scene myScene = new Scene(rootNode, rootNode.getMinWidth(), rootNode.getMinHeight());
        rootNode.getChildren().add(grid);

        // Set the scene on the stage
        myStage.setScene(myScene);

        // Parametrize and show the stage and its scene
        myStage.setResizable(false);
        myStage.show();
    }
}
