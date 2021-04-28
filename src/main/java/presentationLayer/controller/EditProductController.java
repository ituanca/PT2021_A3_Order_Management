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
 *  Controller class used for editing product
 *  @author Anca
 */
public class EditProductController implements Initializable {
    public ComboBox cbName;
    public TextField tfName;
    public TextField tfUnitPrice;
    public TextField tfUnitsInStock;
    public Button btnUpdate;
    public Label lblName;
    public Label lblUnitPrice;
    public Label lblUnitsInStock;

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
        product = productBLL.findProductByName((String) cbName.getValue());
        tfName.setText(product.getName());
        tfUnitPrice.setText(String.valueOf(product.getUnitPrice()));
        tfUnitsInStock.setText(String.valueOf(product.getUnitsInStock()));
        btnUpdate.setVisible(true);
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

    /**
     * Method that sets the update as an event on the button
     * @param actionEvent
     * @throws SQLException
     */
    public void update(ActionEvent actionEvent) throws SQLException {
        if(checkIfFieldsAreNotEmpty() && checkIfValidData()){
            product = new Product(product.getId(), getName(), getUnitPrice(), getUnitsInStock());
            productBLL = new ProductBLL();
            if(productBLL.updateProduct(product) == 1){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Product updated successfully");
                alert.show();
            }
        }
    }

    private boolean checkIfFieldsAreNotEmpty(){
        if(getName().isEmpty() || tfUnitPrice.getText().equals("") || tfUnitsInStock.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please fill in all the fields");
            alert.show();
            return false;
        }
        return true;
    }

    private boolean checkIfValidData(){
        if(getUnitPrice() == -1 || getUnitsInStock() == -1 || getUnitPrice() < 0 || getUnitsInStock() < 0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please fill in valid data");
            alert.show();
            return false;
        }
        return true;
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
