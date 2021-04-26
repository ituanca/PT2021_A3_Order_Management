package presentationLayer;

import businessLayer.CustomerBLL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Customer;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TableCustomersController implements Initializable {

    public ScrollPane scrollPane;
    public TableView tableView;

    CustomerBLL customerBLL;
    ArrayList<TableColumn<Customer, String>> columns = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            tableView = new TableView<Customer>();
            ObservableList<Customer> customers = getCustomers();
            ObjectsController objectsController = new ObjectsController();
            objectsController.createTable(customers, columns, scrollPane, tableView);

            columns.get(0).prefWidthProperty().setValue(30);     //id
            columns.get(1).prefWidthProperty().setValue(130);    //name
            columns.get(2).prefWidthProperty().setValue(180);    //address
            columns.get(3).prefWidthProperty().setValue(110);    //city
            columns.get(4).prefWidthProperty().setValue(120);    //phone
            columns.get(5).prefWidthProperty().setValue(160);    //email
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private ObservableList<Customer> getCustomers() throws SQLException {
        customerBLL = new CustomerBLL();
        return FXCollections.observableArrayList(customerBLL.getCustomers());
    }
}
