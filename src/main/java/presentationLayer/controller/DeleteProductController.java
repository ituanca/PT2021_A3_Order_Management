package presentationLayer.controller;

import businessLayer.ProductBLL;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.Product;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 *  Controller class used for inserting product
 *  @author Anca
 */
public class DeleteProductController implements Initializable {
    public ComboBox cbName;
    public Button btnDelete;
    public Label lblName;
    public Label lblUnitPrice;
    public Label lblUnitsInStock;
    public TextField tfName;
    public TextField tfUnitPrice;
    public TextField tfUnitsInStock;

    Product product;
    ProductBLL productBLL;

    private ArrayList<String> getNames() throws SQLException {
        productBLL = new ProductBLL();
        return productBLL.getNames();
    }

    /**
     * Method that sets event on the combo box
     * @param actionEvent
     * @throws SQLException
     */
    public void selectProduct(ActionEvent actionEvent) throws SQLException {
        lblName.setVisible(true);
        lblUnitPrice.setVisible(true);
        lblUnitsInStock.setVisible(true);
        tfName.setVisible(true);
        tfUnitPrice.setVisible(true);
        tfUnitsInStock.setVisible(true);
        tfName.setEditable(false);
        tfUnitPrice.setEditable(false);
        tfUnitsInStock.setEditable(false);
        product = productBLL.findProductByName((String) cbName.getValue());
        tfName.setText(product.getName());
        tfUnitPrice.setText(String.valueOf(product.getUnitPrice()));
        tfUnitsInStock.setText(String.valueOf(product.getUnitsInStock()));
        btnDelete.setVisible(true);
    }

    /**
     * Method that sets the deletion as an event on the button
     * @param actionEvent
     * @throws SQLException
     */
    public void delete(ActionEvent actionEvent) throws SQLException {
        product = new Product(product.getId(), getName(), getUnitPrice(), getUnitsInStock());
        productBLL = new ProductBLL();
        if (productBLL.deleteProduct(product) == 1) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Product deleted successfully");
            alert.show();
        }
    }

    /**
     * initialization of combo box with all the names
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            cbName.getItems().addAll(getNames());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private String getName() { return tfName.getText(); }

    private Double getUnitPrice() {
        try{
            return Double.parseDouble(tfUnitPrice.getText());
        }catch(NumberFormatException nfe){
            return (double) -1;
        }
    }

    private Integer getUnitsInStock() {
        try{
            return Integer.parseInt(tfUnitsInStock.getText());
        }catch(NumberFormatException nfe){
            return -1;
        }
    }
}
