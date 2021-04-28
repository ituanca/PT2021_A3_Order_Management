package presentationLayer.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;

public class Interface extends Application {

    /**
     * Method to open the main stage of the application when it is started
     * @param stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        URL url = new File("src\\main\\java\\presentationLayer\\fxmlFiles\\sample.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        stage.setTitle("Order management");
        Scene scene = new Scene(root, 1000, 600);
        stage.setScene(scene);
        stage.show();
    }
}
