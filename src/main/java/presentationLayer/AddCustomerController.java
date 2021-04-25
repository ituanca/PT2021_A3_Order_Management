package presentationLayer;

import businessLayer.CustomerBLL;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.Customer;

import java.sql.SQLException;

public class AddCustomerController {

    public TextField tfName;
    public TextField tfAddress;
    public TextField tfCity;
    public TextField tfPhone;
    public TextField tfEmail;
    public Button btnAddCustomer;

    Customer customer;
    CustomerBLL customerBLL;

    public void addCustomer(ActionEvent actionEvent) throws SQLException {
        if(checkIfFieldsAreNotEmpty() && checkIfValidCharacters()){
            customer = new Customer(getName(), getAddress(), getCity(), getPhone(), getEmail());
            customerBLL = new CustomerBLL();
            if(customerBLL.insertCustomer(customer) > -1){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Customer added successfully");
                alert.show();
            }
        }
    }

    private boolean checkIfFieldsAreNotEmpty(){
        if(getName().isEmpty() || getAddress().isEmpty() || getCity().isEmpty() || getPhone().isEmpty() || getEmail().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please fill in all the fields");
            alert.show();
            return false;
        }
        return true;
    }

    private boolean checkIfValidCharacters(){
        if(!getName().matches("^(?![\\s.]+$)[a-zA-Z\\s.]*$") || !getCity().matches("^(?![\\s.]+$)[a-zA-Z\\s.]*$")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please fill in valid data");
            alert.show();
            return false;
        }
        return true;
    }

    private String getName() { return tfName.getText(); }

    private String getAddress() { return tfAddress.getText(); }

    private String getCity() { return tfCity.getText(); }

    private String getPhone() { return tfPhone.getText(); }

    private String getEmail() { return tfEmail.getText(); }
}
