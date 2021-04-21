package businessLayer.validators;

import javafx.scene.control.Alert;
import model.Product;

public class ProductUnitPriceValidator implements Validator<Product>{

    private static final double MIN_PRICE = 5;
    private static final double MAX_PRICE = 500;

    @Override
    public boolean validate(Product product) {
        if (product.getUnitPrice() < MIN_PRICE || product.getUnitPrice() > MAX_PRICE) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please fill in a unit price greater than 5.00 and smaller than 500.00");
            alert.show();
           return false;
        }
        return true;
    }
}
