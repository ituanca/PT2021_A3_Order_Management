package businessLayer.validators;

import javafx.scene.control.Alert;
import model.Product;

public class UnitsInStockValidator implements Validator<Product>{

    private static final double MIN_QUANTITY = 5;
    private static final double MAX_QUANTITY = 2000;

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
