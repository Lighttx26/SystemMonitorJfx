package systemmonitor.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;

public class overviewController {
    private int clientNumber;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private AnchorPane anchorScrollPane;

    private double gap;

    public void initialize() {
        clientNumber = 0;
        gap = 50;
    }
}
