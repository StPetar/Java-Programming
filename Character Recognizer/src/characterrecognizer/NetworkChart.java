package characterrecognizer;

import ffbp.FFBP;
import javafx.geometry.Insets;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.text.Font;

public class NetworkChart extends BarChart<String, Number> {

    //Define the width and height of the Network Chart
    private static final int CHARTWIDTH = 170;
    private static final int CHARTHEIGHT = 320;

    //Define the Neural Network
    private FFBP net = null;

    //Define the Series of data and the Data of the Chart
    private XYChart.Series<String, Number> series = null;
    private XYChart.Data<String, Number>[] data = null;


    public NetworkChart(FFBP net) {

        //Initialize the Network Chart and the Network
        super(new CategoryAxis(), new NumberAxis(0.0, 1.0, 0.1));
        this.net = net;

        //Initialize the Series of data and the Data
        series = new XYChart.Series<>();
        data = new XYChart.Data[8];
        getData().add(series);

        //Set the Data with the corresponding label letter and value
        String labelLetter = "ABCDEFGH";
        for (int i = 0; i < 8; i++) {
            data[i] = new XYChart.Data<>(labelLetter.substring(i, i + 1), net.getOutput()[i]);
            series.getData().add(data[i]);
            data[i].getNode().setStyle("-fx-bar-fill: green");
        }

        //Set properties of the Network Chart
        setLegendVisible(false);
        setMaxSize(CHARTWIDTH, CHARTHEIGHT);
        setMinWidth(CHARTWIDTH);
        setPadding(new Insets(0, 0, 0, -20));
        setCategoryGap(7);
        setBarGap(3);
        setVerticalGridLinesVisible(false);
        setAnimated(false);

        //Set properties of the X Axis
        getXAxis().setTickMarkVisible(false);
        getXAxis().setTickLength(10);
        getXAxis().setTickLabelFont(new Font(16));

        //Set properties of the Y Axis
        getYAxis().setTickLabelsVisible(false);
        getYAxis().setOpacity(0);
    }


    //A method which updates the chart with the new value of the Grid State
    public void updateChart() {
        for (int i = 0; i < 8; i++) {
            data[i].setYValue(net.getOutput()[i]);
        }
    }

    //A method which sets the Neural Network
    public void setNet(FFBP net) {
        this.net = net;
        updateChart();
    }
}