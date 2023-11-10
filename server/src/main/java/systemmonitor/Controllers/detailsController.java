package systemmonitor.Controllers;

import java.io.IOException;
import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import systemmonitor.Utilities.DataAccess;

public class detailsController {
    @FXML
    private AreaChart<String, Number> memoryChart;
    @FXML
    private AreaChart<String, Number> cpuChart;
    @FXML
    private AreaChart<String, Number> ethernetChart;
    @FXML
    private TextField textField;
    private int timeIndex = 1;

    public void initialize() throws IOException, InterruptedException {
        // NumberAxis xAxis = (NumberAxis) lineChart.getXAxis();
        // NumberAxis yAxis = (NumberAxis) lineChart.getYAxis();
        memoryChart.setTitle("MEMORY");
        memoryChart.setLegendVisible(false);

        XYChart.Series<String, Number> memDataSeries = new XYChart.Series<>();
        memDataSeries.setName("Memory usage (MB)");

        DataAccess da = new DataAccess();

        // // Add data points to the series
        memDataSeries.getData().clear();
        memoryChart.getData().clear();

        // ArrayList<Long> ar = da.getMemoryUsages();
        // for (Long mem : ar) {
        // memDataSeries.getData().add(new XYChart.Data<String,
        // Number>(Integer.toString(timeIndex++), mem));
        // }

        memDataSeries.getData()
                .add(new XYChart.Data<String, Number>(Integer.toString(timeIndex++), da.getCurrentMemoryUsage()));
        memoryChart.getData().add(memDataSeries);

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1.5), event -> updateChartData()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void updateChartData() {
        // Update the chart data
        // XYChart.Series<String, Number> dataSeries = new XYChart.Series<>();
        XYChart.Series<String, Number> memDataSeries = memoryChart.getData().get(0);

        DataAccess da = new DataAccess();

        // dataSeries.getData().clear();
        // lineChart.getData().clear();

        Long mem = da.getCurrentMemoryUsage();
        if (memDataSeries.getData().size() > 10)
            memDataSeries.getData().remove(0);
        memDataSeries.getData().add(new XYChart.Data<String, Number>(Integer.toString(timeIndex++), mem));
    }
}
