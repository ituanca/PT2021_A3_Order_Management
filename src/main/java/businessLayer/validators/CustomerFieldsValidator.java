package businessLayer.validators;

import javafx.scene.control.Alert;
import model.Customer;

/**
 * Class that implements the interface Validator and validates the customer fields
 * @author Anca
 */
public class CustomerFieldsValidator implements Validator<Customer>{

    /**
     * Method to check if all the fields contain data and if the name and the city contain only alphabets, spaces or hyphens respectively
     * @param customer object transmitted as parameter
     * @return boolean
     */
    @Override
    public boolean validate(Customer customer) {
        if(customer.getName().isEmpty() || customer.getAddress().isEmpty() || customer.getCity().isEmpty() || customer.getPhone().isEmpty() || customer.getEmail().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please fill in all the fields");
            alert.show();
            return false;
        }
        if(!customer.getName().matches("^(?![\\s.]+$)[a-zA-Z\\s.-]*$") || !customer.getCity().matches("^(?![\\s.]+$)[a-zA-Z\\s.-]*$")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please fill in valid data");
            alert.show();
            return false;
        }
        return true;
    }
}
