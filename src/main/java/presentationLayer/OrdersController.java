package presentationLayer;

import businessLayer.CustomerBLL;
import businessLayer.OrderBLL;
import businessLayer.PDFGenerator;
import businessLayer.ProductBLL;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.Customer;
import model.Order;
import model.Product;

import java.net.URL;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class OrdersController implements Initializable {
    public ComboBox cbCustomer;
    public ComboBox cbProduct;
    public TextField tfQuantity;
    public Button btnCreateOrder;
    public TextField tfUnitPrice;
    public TextField tfPrice;
    public Label lblUnitPrice;
    public Label lblUnitsInStock;
    public TextField tfUnitsInStock;
    public Button btnSeePrice;

    Customer customer;
    CustomerBLL customerBLL;
    Product product;
    ProductBLL productBLL;
    Order order;
    OrderBLL orderBLL;
    PDFGenerator pdfGenerator = new PDFGenerator();

    public void createOrder(ActionEvent actionEvent) throws SQLException {
        order = new Order(customer.getName(), product.getName(), getQuantity(), getTotalPrice());
        orderBLL = new OrderBLL();
        if (orderBLL.createOrder(order) > -1 && checkIfValidQuantity()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Order created successfully");
            alert.show();
            product.setUnitsInStock(product.getUnitsInStock() - getQuantity());
            productBLL.updateProduct(product);

            order = orderBLL.getLastOrder();
            pdfGenerator.generatePDF(order, customer, product);
        }
    }

    private ArrayList<String> getProducts() throws SQLException {
        productBLL = new ProductBLL();
        return productBLL.getNames();
    }

    private ArrayList<String> getCustomers() throws SQLException {
        customerBLL = new CustomerBLL();
        return customerBLL.getNames();
    }

    public void selectCustomer(ActionEvent actionEvent) throws SQLException {
        customer = customerBLL.findCustomerByName((String) cbCustomer.getValue());
    }

    public void selectProduct(ActionEvent actionEvent) throws SQLException {
        product = productBLL.findProductByName((String) cbProduct.getValue());
        lblUnitPrice.setVisible(true);
        tfUnitPrice.setVisible(true);
        tfUnitPrice.setText(changeToDecimalFormat(product.getUnitPrice()));
        tfUnitPrice.setEditable(false);
        lblUnitsInStock.setVisible(true);
        tfUnitsInStock.setVisible(true);
        tfUnitsInStock.setText(Integer.toString(product.getUnitsInStock()));
        tfUnitsInStock.setEditable(false);
    }

    public void computePrice(ActionEvent actionEvent) {
        if(checkIfValidQuantity()){
            tfPrice.setVisible(true);
            tfPrice.setText(Double.toString(product.getUnitPrice() * getQuantity()));
            tfPrice.setEditable(false);
            btnCreateOrder.setVisible(true);
        }
    }

    private boolean checkIfValidQuantity(){
        if(getQuantity() > product.getUnitsInStock() || getQuantity() < 1){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please fill in a number of units between 0 and " + product.getUnitsInStock() + ".");
            alert.show();
            return false;
        }
        return true;
    }

    public String changeToDecimalFormat(double numberToBeDisplayed){
        DecimalFormat df2 = new DecimalFormat("#.##");
        return df2.format(numberToBeDisplayed);
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

    private Integer getQuantity() {
        try{
            return Integer.parseInt(tfQuantity.getText());
        }catch(NumberFormatException nfe){
            return -1;
        }
    }

    private Double getTotalPrice() { return product.getUnitPrice() * getQuantity(); }

}
