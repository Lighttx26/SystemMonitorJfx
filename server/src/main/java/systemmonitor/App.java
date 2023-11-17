package systemmonitor;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import systemmonitor.Server.Server;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    // Launch() method will invoke this function
    @Override
    public void start(Stage stage) throws IOException {
        // Start server to communicate with clients
        Server server = new Server();
        // app.LoadServerConfig("src\\main\\resources\\config\\config.cfg");

        // set UI
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("overview" + ".fxml"));
        scene = new Scene(fxmlLoader.load(), 500, 375);
        stage.setScene(scene);
        stage.setTitle("System Monitor");
        server.setController(fxmlLoader.getController());
        server.start();
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}