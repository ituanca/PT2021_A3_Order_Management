package presentationLayer;

import businessLayer.CustomerBLL;
import businessLayer.ProductBLL;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.Product;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EditProductController implements Initializable {
    public ComboBox cbName;
    public Button btnEditProduct;
    public TextField tfName;
    public TextField tfUnitPrice;
    public TextField tfUnitsInStock;
    public Button btnUpdate;

    Product product;
    ProductBLL productBLL;

    private ArrayList<String> getNames() throws SQLException {
        productBLL = new ProductBLL();
        return productBLL.getNames();
    }

    public void selectProduct(ActionEvent actionEvent) {
        btnEditProduct.setVisible(true);
        tfName.setText("");
        tfUnitPrice.setText("");
        tfUnitsInStock.setText("");
        tfName.setEditable(false);
        tfUnitPrice.setEditable(false);
        tfUnitsInStock.setEditable(false);
        btnUpdate.setVisible(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            cbName.getItems().addAll(getNames());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void editProduct(ActionEvent actionEvent) throws SQLException {
        product = productBLL.findProductByName((String) cbName.getValue());
        tfName.setText(product.getName());
        tfUnitPrice.setText(String.valueOf(product.getUnitPrice()));
        tfUnitsInStock.setText(String.valueOf(product.getUnitsInStock()));
        tfName.setEditable(true);
        tfUnitPrice.setEditable(true);
        tfUnitsInStock.setEditable(true);
        btnUpdate.setVisible(true);
    }

    public void update(ActionEvent actionEvent) throws SQLException {
        if(checkIfFieldsAreNotEmpty() && checkIfValidData()){
            product = new Product(product.getId(), getName(), getUnitPrice(), getUnitsInStock());
            productBLL = new ProductBLL();
            if(productBLL.updateProduct(product) > -1){
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
