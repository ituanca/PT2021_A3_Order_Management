package businessLayer.validators;

import javafx.scene.control.Alert;
import model.Product;

/**
 * Class that implements the interface Validator and validates the product fields
 * @author Anca
 */
public class ProductFieldsValidator implements Validator<Product>{

    /**
     * Method to check if the inserted unit price and number of units in stock are valid
     * @param product object transmitted as parameter
     * @return boolean
     */
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
