package businessLayer.validators;

import javafx.scene.control.Alert;
import model.Product;

public class ProductFieldsValidator implements Validator<Product>{
    @Override
    public boolean validate(Product product) {
        if(product.getUnitPrice() == -1 || product.getUnitsInStock() == -1 || product.getUnitPrice() < 0 || product.getUnitsInStock() < 0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please fill in valid data");
            alert.show();
            return false;
        }
        return true;
    }
}
