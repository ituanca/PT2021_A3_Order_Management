package presentationLayer.controller;

import businessLayer.CustomerBLL;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.Customer;

import java.sql.SQLException;

/**
 *  Controller class used for inserting customer
 *  @author Anca
 */
public class AddCustomerController {

    public TextField tfName;
    public TextField tfAddress;
    public TextField tfCity;
    public TextField tfPhone;
    public TextField tfEmail;
    public Button btnAddCustomer;

    Customer customer;
    CustomerBLL customerBLL;

    /**
     * Method to add customer to table and show alert message if insertion succeeded
     * @param actionEvent
     * @throws SQLException
     */
    public void addCustomer(ActionEvent actionEvent) throws SQLException {
        customer = new Customer(getName(), getAddress(), getCity(), getPhone(), getEmail());
        customerBLL = new CustomerBLL();
        if (customerBLL.insertCustomer(customer) > -1) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Customer added successfully");
            alert.show();
        }
    }

    private String getName() { return tfName.getText(); }

    private String getAddress() { return tfAddress.getText(); }

    private String getCity() { return tfCity.getText(); }

    private String getPhone() { return tfPhone.getText(); }

    private String getEmail() { return tfEmail.getText(); }
}
