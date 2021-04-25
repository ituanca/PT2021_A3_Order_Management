package presentationLayer;

import businessLayer.ProductBLL;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.Product;

import java.sql.SQLException;

public class AddProductController {
    public Button btnAddProduct;
    public TextField tfName;
    public TextField tfUnitPrice;
    public TextField tfUnitsInStock;

    Product product;
    ProductBLL productBLL;

    public void addProduct(ActionEvent actionEvent) throws SQLException {
        if(checkIfFieldsAreNotEmpty()){
            product = new Product(getName(), getUnitPrice(), getUnitsInStock());
            productBLL = new ProductBLL();
            if(productBLL.insertProduct(product) > -1){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Product added successfully");
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
