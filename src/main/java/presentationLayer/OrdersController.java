package presentationLayer;

import businessLayer.CustomerBLL;
import businessLayer.ProductBLL;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.Customer;
import model.Product;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class OrdersController implements Initializable {
    public ComboBox cbCustomer;
    public ComboBox cbProduct;
    public TextField tfQuantity;
    public Button btnCreateOrder;

    Customer customer;
    CustomerBLL customerBLL;
    Product product;
    ProductBLL productBLL;

    private ArrayList<String> getProducts() throws SQLException {
        productBLL = new ProductBLL();
        return productBLL.getNames();
    }

    private ArrayList<String> getCustomers() throws SQLException {
        customerBLL = new CustomerBLL();
        return customerBLL.getNames();
    }

    public void selectCustomer(ActionEvent actionEvent) {
    }

    public void selectProduct(ActionEvent actionEvent) {
    }

    public void createOrder(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            cbCustomer.getItems().addAll(getCustomers());
            cbProduct.getItems().addAll(getProducts());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
