package businessLayer.validators;

import javafx.scene.control.Alert;
import model.Customer;

import java.util.regex.Pattern;

public class PhoneValidator implements Validator<Customer>{

    private static final String PHONE_PATTERN = "^(\\+4|)?(07[0-8][0-9]|02[0-9]{2}|03[0-9]{2})(\\s|\\.|\\-)?([0-9]{3}(\\s|\\.|\\-|)){2}$";

    @Override
    public boolean validate(Customer customer) {
        Pattern pattern = Pattern.compile(PHONE_PATTERN);
        if (!pattern.matcher(customer.getPhone()).matches()) {
            System.out.println("invalid phone number");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please fill in all a valid phone number");
            alert.show();
            return false;
        }
        System.out.println("valid phone number");
        return true;
    }
}
