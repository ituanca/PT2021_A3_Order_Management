package businessLayer.validators;

import javafx.scene.control.Alert;
import model.Product;

/**
 * Class that implements the interface Validator and validates the number of units in stock of a product
 * @author Anca
 */
public class UnitsInStockValidator implements Validator<Product>{

    private static final double MIN_QUANTITY = 5;
    private static final double MAX_QUANTITY = 2000;

    /**
     * Method to check if the inserted number of units in stock is in the required interval
     * @param product object transmitted as parameter
     * @return boolean
     */
    @Override
    public boolean validate(Product product) {
        if (product.getUnitsInStock() < MIN_QUANTITY || product.getUnitsInStock() > MAX_QUANTITY) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please fill in a number of units greater than 5 and smaller than 2000");
            alert.show();
            return false;
        }
        return true;
    }
}
