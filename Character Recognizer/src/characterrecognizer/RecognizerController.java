package characterrecognizer;

import ffbp.FFBP;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.VBox;

import java.util.Random;

public class RecognizerController extends VBox {

    //Define the width and height of the Buttons
    private static final int BUTTONWIDTH = 160;
    private static final int BUTTONHEIGHT = 20;

    //Define the Buttons
    private Button btnetewNet = null;
    private Button btnLearn = null;
    private Button btnA = null;
    private Button btnB = null;
    private Button btnC = null;
    private Button btnD = null;
    private Button btnE = null;
    private Button btnF = null;
    private Button btnG = null;
    private Button btnH = null;

    //Define the Toggle Noise Button
    private ToggleButton btnNoise = null;

    //Define the separators
    private Separator separtaor1 = null;
    private Separator separtaor2 = null;

    //Define the Network Chart
    private NetworkChart chart = null;

    //Define the Grid Canvas
    private GridCanvas grid = null;

    //Define the Neural Network
    private FFBP net = null;

    public RecognizerController(GridCanvas grid) {

        //Initialize the Recognizer Controller and the Grid Canvas
        super();
        this.grid = grid;

        //Set the Recognize Controller of the Grid Canvas and Initialize the Network
        grid.setController(this);
        InitializeNetwork();

        //Initialize the New Net Button
        btnetewNet = new Button("New Net");
        btnetewNet.setMinSize(BUTTONWIDTH, BUTTONHEIGHT);

        //Initialize the Learn Button
        btnLearn = new Button("Learn 500 Cycles");
        btnLearn.setMinSize(BUTTONWIDTH, BUTTONHEIGHT);

        //Initialize the Character A Button
        btnA = new Button("A");
        btnA.setMinSize(BUTTONWIDTH, BUTTONHEIGHT);

        //Initialize the Character B Button
        btnB = new Button("B");
        btnB.setMinSize(BUTTONWIDTH, BUTTONHEIGHT);

        //Initialize the Character C Button
        btnC = new Button("C");
        btnC.setMinSize(BUTTONWIDTH, BUTTONHEIGHT);

        //Initialize the Character D Button
        btnD = new Button("D");
        btnD.setMinSize(BUTTONWIDTH, BUTTONHEIGHT);

        //Initialize the Character E Button
        btnE = new Button("E");
        btnE.setMinSize(BUTTONWIDTH, BUTTONHEIGHT);

        //Initialize the Character F Button
        btnF = new Button("F");
        btnF.setMinSize(BUTTONWIDTH, BUTTONHEIGHT);

        //Initialize the Character G Button
        btnG = new Button("G");
        btnG.setMinSize(BUTTONWIDTH, BUTTONHEIGHT);

        //Initialize the Character H Button
        btnH = new Button("H");
        btnH.setMinSize(BUTTONWIDTH, BUTTONHEIGHT);

        //Initialize the Noise Toggle Button
        btnNoise = new ToggleButton("Noise");
        btnNoise.setMinSize(BUTTONWIDTH, BUTTONHEIGHT);

        //Initialize the Separators
        separtaor1 = new Separator();
        separtaor1.setMaxWidth(160);

        separtaor2 = new Separator();
        separtaor2.setMaxWidth(160);

        //Initialize the Network Chart
        chart = new NetworkChart(net);

        //Create an Action Event for the New Network Button
        btnetewNet.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                InitializeNetwork();
            }
        });

        //Create an Action Event for the Learn Button
        btnLearn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                TrainNetwork();
            }
        });

        //Create an Action Event for Character A Button
        btnA.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                double[] currentCharacter = Character.getCharacterA(btnNoise.isSelected());
                grid.paintCharacter(currentCharacter);
            }
        });

        //Create an Action Event for Character B Button
        btnB.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                double[] currentCharacter = Character.getCharacterB(btnNoise.isSelected());
                grid.paintCharacter(currentCharacter);
            }
        });

        //Create an Action Event for Character C Button
        btnC.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                double[] currentCharacter = Character.getCharacterC(btnNoise.isSelected());
                grid.paintCharacter(currentCharacter);
            }
        });

        //Create an Action Event for Character D Button
        btnD.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                double[] currentCharacter = Character.getCharacterD(btnNoise.isSelected());
                grid.paintCharacter(currentCharacter);
            }
        });

        //Create an Action Event for Character E Button
        btnE.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                double[] currentCharacter = Character.getCharacterE(btnNoise.isSelected());
                grid.paintCharacter(currentCharacter);
            }
        });

        //Create an Action Event for Character F Button
        btnF.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                double[] currentCharacter = Character.getCharacterF(btnNoise.isSelected());
                grid.paintCharacter(currentCharacter);
            }
        });

        //Create an Action Event for Character G Button
        btnG.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                double[] currentCharacter = Character.getCharacterG(btnNoise.isSelected());
                grid.paintCharacter(currentCharacter);
            }
        });

        //Create an Action Event for Character H Button
        btnH.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                double[] currentCharacter = Character.getCharacterH(btnNoise.isSelected());
                grid.paintCharacter(currentCharacter);
            }
        });

        //Set the spacing between the elements
        setSpacing(7);

        //Add all elements to the Recognizer Controller
        getChildren().addAll(btnetewNet, btnNoise, btnLearn, separtaor1, btnA, btnB, btnC,
                btnD, btnE, btnF, btnG, btnH, separtaor2, chart);
    }

    //A method which initializes the Neural Network
    public void InitializeNetwork() {

        //Set Network properties and feed once
        int[] layout = {256, 16, 8};
        net = new FFBP(layout);
        net.randomize(-0.1, +0.1);
        net.setEta(0.5);
        net.setAlpha(0.5);
        net.activateInputAndFeedForward(grid.getGridState());

        //Set the Network of the Network Chart if the object isn't null
        if (chart != null) {
            chart.setNet(net);
        }
    }

    //Train the Neural Network
    public void TrainNetwork() {

        //Initialize the desired output arrays
        double[] desiredOutputA = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
        double[] desiredOutputB = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
        double[] desiredOutputC = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0};
        double[] desiredOutputD = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0};
        double[] desiredOutputE = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0};
        double[] desiredOutputF = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0};
        double[] desiredOutputG = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0};
        double[] desiredOutputH = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0};

        //Initialize a Random variable
        Random rand = new Random();

        //Perform 500 training cycles
        for (int i = 0; i < 500; i++) {
            switch (rand.nextInt(8)) {
                case 0:
                    net.activateInputAndFeedForward(Character.getCharacterA(btnNoise.isSelected()));
                    net.applyDesiredOutputAndPropagateBack(desiredOutputA);
                    break;
                case 1:
                    net.activateInputAndFeedForward(Character.getCharacterB(btnNoise.isSelected()));
                    net.applyDesiredOutputAndPropagateBack(desiredOutputB);
                    break;
                case 2:
                    net.activateInputAndFeedForward(Character.getCharacterC(btnNoise.isSelected()));
                    net.applyDesiredOutputAndPropagateBack(desiredOutputC);
                    break;
                case 3:
                    net.activateInputAndFeedForward(Character.getCharacterD(btnNoise.isSelected()));
                    net.applyDesiredOutputAndPropagateBack(desiredOutputD);
                    break;
                case 4:
                    net.activateInputAndFeedForward(Character.getCharacterE(btnNoise.isSelected()));
                    net.applyDesiredOutputAndPropagateBack(desiredOutputE);
                    break;
                case 5:
                    net.activateInputAndFeedForward(Character.getCharacterF(btnNoise.isSelected()));
                    net.applyDesiredOutputAndPropagateBack(desiredOutputF);
                    break;
                case 6:
                    net.activateInputAndFeedForward(Character.getCharacterG(btnNoise.isSelected()));
                    net.applyDesiredOutputAndPropagateBack(desiredOutputG);
                    break;
                case 7:
                    net.activateInputAndFeedForward(Character.getCharacterH(btnNoise.isSelected()));
                    net.applyDesiredOutputAndPropagateBack(desiredOutputH);
                    break;
            }
        }
        feedNetwork();
    }

    //A method which feeds the Network with the current Grid State
    public void feedNetwork() {
        net.activateInputAndFeedForward(grid.getGridState());
        chart.updateChart();
    }
}