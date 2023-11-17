package systemmonitor.Controllers;

import java.net.InetAddress;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;

public class overviewController {
    private ArrayList<InetAddress> addresses;

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private AnchorPane anchorScrollPane;

    private double gap = 50;

    // public void setClientNumber(int num) {
    // this.clientNumber = num;
    // }

    // public void increaseClientNumber() {
    // this.clientNumber++;
    // }

    public void addAddress(InetAddress address) {
        this.addresses.add(address);
        addTitledPane(address.getHostName());
    }

    public overviewController() {
        this.addresses = new ArrayList<>();
    }

    public void initialize() {
        gap = 50;
    }

    public void addTitledPane(String clientName) {
        TitledPane newTitledPane = new TitledPane();
        newTitledPane.setText(clientName);

        AnchorPane contentPane = new AnchorPane();
        contentPane.setPrefSize(200, 180);
        // You can customize the content of the TitledPane here if needed.

        newTitledPane.setContent(contentPane);

        // Set the position for the new TitledPane
        // Calculate the position based on the number of existing panes.
        double xc, yc;
        if ((addresses.size() - 1) % 2 == 0) {
            xc = 14;
            yc = 14 + (180 + gap) * (addresses.size() - 1) / 2;
        } else {
            xc = 14 + (200 + gap);
            yc = 14 + (180 + gap) * (int) ((addresses.size() - 1) / 2);
        }

        newTitledPane.setLayoutX(xc);
        newTitledPane.setLayoutY(yc);

        // Add the new TitledPane to the existing AnchorPane
        anchorScrollPane.getChildren().add(newTitledPane);
    }
}
