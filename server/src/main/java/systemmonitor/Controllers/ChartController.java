package systemmonitor.Controllers;

import java.io.IOException;
import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextField;
import javafx.util.Duration;

import systemmonitor.Utilities.DataAccess;

public class ChartController {

    @FXML
    private LineChart<String, Number> lineChart;
    @FXML
    private TextField textField;
    private int timeIndex = 1;

    public void initialize() throws IOException, InterruptedException {
        // NumberAxis xAxis = (NumberAxis) lineChart.getXAxis();
        // NumberAxis yAxis = (NumberAxis) lineChart.getYAxis();
        lineChart.setTitle("Line Chart Example");

        XYChart.Series<String, Number> dataSeries = new XYChart.Series<>();
        dataSeries.setName("Data Series");

        DataAccess da = new DataAccess();

        // Add data points to the series
        dataSeries.getData().clear();
        lineChart.getData().clear();

        ArrayList<Long> ar = da.getMemoryUsages();
        for (Long mem : ar) {
            dataSeries.getData().add(new XYChart.Data<String, Number>(Integer.toString(timeIndex++), mem));
        }

        lineChart.getData().add(dataSeries);

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1.5), event -> updateChartData()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void updateChartData() {
        // Update the chart data
        // XYChart.Series<String, Number> dataSeries = new XYChart.Series<>();
        XYChart.Series<String, Number> dataSeries = lineChart.getData().get(0);

        DataAccess da = new DataAccess();

        // dataSeries.getData().clear();
        // lineChart.getData().clear();

        Long mem = da.getCurrentMemoryUsage();
        dataSeries.getData().remove(0);
        dataSeries.getData().add(new XYChart.Data<String, Number>(Integer.toString(timeIndex++), mem));
    }
}
