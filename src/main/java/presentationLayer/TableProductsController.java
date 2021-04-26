package presentationLayer;

import businessLayer.ProductBLL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Product;

import java.lang.reflect.Field;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TableProductsController implements Initializable {

    public TableView tableView;
    public ScrollPane scrollPane;

    ProductBLL productBLL;
    ArrayList<TableColumn<Product, String>> columns = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            tableView = new TableView<Product>();
            ObservableList<Product> products = getProducts();
            ObjectsController objectsController = new ObjectsController();
            objectsController.createTable(products, columns, scrollPane, tableView);

            columns.get(0).prefWidthProperty().setValue(40);
            columns.get(1).prefWidthProperty().setValue(170);
            columns.get(2).prefWidthProperty().setValue(80);
            columns.get(3).prefWidthProperty().setValue(80);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private ObservableList<Product> getProducts() throws SQLException {
        productBLL = new ProductBLL();
        return FXCollections.observableArrayList(productBLL.getProducts());
    }
}
