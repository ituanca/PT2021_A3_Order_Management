package presentationLayer.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 *  Class used for generating pane objects
 *  @author Anca
 */
public class FxmlLoader {

    private Pane view;

    /**
     * Method used to generate the pane corresponding to the fxml file specified by the path including the parameter filename
     * @param filename the name of the file to be included in the path
     * @return object of class Pane
     */
    public Pane getPage(String filename) {
        try{
            URL url = new File("src\\main\\java\\presentationLayer\\fxmlFiles\\" + filename + ".fxml").toURI().toURL();
            view = new FXMLLoader().load(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return view;
    }
}
