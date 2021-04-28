package businessLayer.validators;

import javafx.scene.control.Alert;
import model.Customer;

import java.util.regex.Pattern;

/**
 * Class that implements the interface Validator and validates the phone number
 * @author Anca
 */
public class PhoneValidator implements Validator<Customer>{

    private static final String PHONE_PATTERN = "^(\\+4|)?(07[0-8][0-9]|02[0-9]{2}|03[0-9]{2})(\\s|\\.|\\-)?([0-9]{3}(\\s|\\.|\\-|)){2}$";

    /**
     * Method to check if the phone number is valid
     * @param customer object transmitted as parameter
     * @return boolean
     */
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
