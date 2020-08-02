package characterrecognizer;

import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;


public class GridCanvas extends Canvas {

    // Define the size of the square and the gap between the squares
    private static final int SQUARESIZE = 38;
    private static final int SQUAREGAP = 2;

    // Define the width and height of the canvas
    private static int canvasWidth = 16 * SQUARESIZE + 17 * SQUAREGAP;
    private static int canvasHeight = 16 * SQUARESIZE + 17 * SQUAREGAP;

    // Define a Graphics Context
    private GraphicsContext gc = null;

    //Define an array, which stores the state of the Grid
    private double[] gridState = null;

    //Define the Recognizer Controller
    private RecognizerController controller = null;

    public GridCanvas() {

        // Initialize the Grid Canvas, the Graphics Context and the Grid State
        super(canvasWidth, canvasHeight);
        gc = getGraphicsContext2D();
        gridState = Character.getEmptyState();

        //Paint a gray border around the canvas
        gc.setStroke(Color.GRAY);
        gc.strokeRect(0, 0, getWidth() - 1, getHeight() - 1);

        // Create a mouse event, which is activated when the mouse is dragged
        setOnMouseDragged(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {

                // Check if the Primary Mouse Button is held
                if (me.isPrimaryButtonDown() && me.getX() < canvasWidth && me.getY() < canvasHeight) {
                    fillSquare((int) me.getX(), (int) me.getY());
                }
            }
        });

        // Create a mouse event, which is activated when the mouse is clicked
        setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {

                // Check if the Primary or the Secondary Mouse Button is clicked
                if (me.getButton() == MouseButton.PRIMARY) {
                    fillSquare((int) me.getX(), (int) me.getY());
                } else if (me.getButton() == MouseButton.SECONDARY) {
                    // Reset the canvas
                    clearGrid();
                }
            }
        });
    }

    // A method which fills the square, on the the position of the mouse
    public void fillSquare(int x, int y) {

        // Calculate the X and Y offset from the gap between the squares
        int offsetX = x % (SQUARESIZE + SQUAREGAP);
        int offsetY = y % (SQUARESIZE + SQUAREGAP);

        // Check if the mouse is on the gap between the squares
        if (offsetX >= SQUAREGAP && offsetY >= SQUAREGAP) {

            // Get the coordinates of the top left corner of the square
            int coordinateX = x - offsetX + SQUAREGAP;
            int coordinateY = y - offsetY + SQUAREGAP;

            //Save the state of the newly painted pixel in the Grid State array
            int squareIndex = 16 * (coordinateY / (SQUARESIZE + SQUAREGAP)) + coordinateX / (SQUARESIZE + SQUAREGAP);
            gridState[squareIndex] = 1;

            // Fill the square
            gc.fillRect(coordinateX, coordinateY, SQUARESIZE, SQUARESIZE);

            //Feed the Network with the newly drawn character
            controller.feedNetwork();
        }
    }

    //A method which resets the Grid Canvas
    public void clearGrid() {

        //Clear the Canvas and repaint the border
        gc.clearRect(0, 0, getWidth(), getHeight());
        gc.strokeRect(0, 0, getWidth() - 1, getHeight() - 1);

        //Set the Grid State to empty and feed the Network
        gridState = Character.getEmptyState();
        controller.feedNetwork();
    }

    //A method which paints a character on the Grid Canvas from its array representation
    public void paintCharacter(double[] character) {

        clearGrid();

        //Get the X and Y coordinates of all active squares and paint them on the Grid Canvas
        for (int i = 0; i < 256; i++) {
            if (character[i] == 1.0) {
                int coordinateX = i % 16 * (SQUARESIZE + SQUAREGAP) + SQUAREGAP;
                int coordinateY = i / 16 * (SQUARESIZE + SQUAREGAP) + SQUAREGAP;
                gc.fillRect(coordinateX, coordinateY, SQUARESIZE, SQUARESIZE);
            }
        }

        //Set the grid state array to the corresponding new value and feed the Network
        gridState = character;
        controller.feedNetwork();
    }

    //Get the Grid State
    public double[] getGridState() {
        return gridState;
    }

    //Set the Recognizer Controller
    public void setController(RecognizerController controller) {
        this.controller = controller;
    }
}