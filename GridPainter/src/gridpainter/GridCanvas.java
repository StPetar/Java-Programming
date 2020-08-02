package gridpainter;

import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class GridCanvas extends Canvas {

    //Define the width and height of the canvas
    private static final int CANVASWIDTH = 290;
    private static final int CANVASHEIGHT = 290;

    //Define the size of the square and the gap between the squares
    private static final int SQUARESIZE = 16;
    private static final int SQUAREGAP = 2;

    //Define a Graphics Context
    private GraphicsContext gc = null;

    public GridCanvas() {

        //Initialize the Grid Canvas and the Graphics Context
        super(CANVASWIDTH, CANVASHEIGHT);
        gc = getGraphicsContext2D();

        //Create a mouse event, which is activated when the mouse is dragged
        setOnMouseDragged(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {

                //Check if the Primary Mouse Button is held
                if (me.isPrimaryButtonDown()) {
                    fillSquare((int) me.getX(), (int) me.getY());
                }
            }
        });

        //Create a mouse event, which is activated when the mouse is clicked
        setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {

                //Check if the Primary or the Secondary Mouse Button is clicked
                if (me.getButton() == MouseButton.PRIMARY) {
                    fillSquare((int) me.getX(), (int) me.getY());
                } else if (me.getButton() == MouseButton.SECONDARY) {
                    //Reset the canvas
                    gc.clearRect(0, 0, getWidth(), getHeight());
                }
            }
        });
    }

    //A method which fills the square, on the the position of the mouse
    public void fillSquare(int x, int y) {

        //Calculate the X and Y offset from the gap between the squares
        int offsetX = x % (SQUARESIZE + SQUAREGAP);
        int offsetY = y % (SQUARESIZE + SQUAREGAP);

        //Check if the mouse is on the gap between the squares
        if (offsetX >= SQUAREGAP && offsetY >= SQUAREGAP) {

            //Get the coordinates of the top left corner of the square
            int coordinateX = x - offsetX + SQUAREGAP;
            int coordinateY = y - offsetY + SQUAREGAP;

            //Fill the square
            gc.fillRect(coordinateX, coordinateY, SQUARESIZE, SQUARESIZE);
        }
    }
}