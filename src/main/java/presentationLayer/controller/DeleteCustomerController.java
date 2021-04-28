package presentationLayer.controller;

import businessLayer.CustomerBLL;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.Customer;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 *  Controller class used for deleting customer
 *  @author Anca
 */
public class DeleteCustomerController implements Initializable {
    public ComboBox cbName;
    public Label lblName;
    public Label lblAddress;
    public Label lblCity;
    public Label lblPhone;
    public Label lblEmail;
    public TextField tfName;
    public TextField tfAddress;
    public TextField tfCity;
    public TextField tfPhone;
    public TextField tfEmail;
    public Button btnDelete;

    Customer customer;
    CustomerBLL customerBLL;

    private ArrayList<String> getNames() throws SQLException {
        customerBLL = new CustomerBLL();
        return customerBLL.getNames();
    }

    /**
     * Method that sets event on the combo box
     * @param actionEvent
     * @throws SQLException
     */
    public void selectCustomer(ActionEvent actionEvent) throws SQLException {
        lblName.setVisible(true);
        lblAddress.setVisible(true);
        lblCity.setVisible(true);
        lblPhone.setVisible(true);
        lblEmail.setVisible(true);
        tfName.setVisible(true);
        tfAddress.setVisible(true);
        tfCity.setVisible(true);
        tfPhone.setVisible(true);
        tfEmail.setVisible(true);
        tfName.setEditable(false);
        tfAddress.setEditable(false);
        tfCity.setEditable(false);
        tfPhone.setEditable(false);
        tfEmail.setEditable(false);
        lblAddress.setVisible(true);
        lblCity.setVisible(true);
        lblPhone.setVisible(true);
        lblEmail.setVisible(true);
        customer = customerBLL.findCustomerByName((String) cbName.getValue());
        tfName.setText(customer.getName());
        tfAddress.setText(customer.getAddress());
        tfCity.setText(customer.getCity());
        tfPhone.setText(customer.getPhone());
        tfEmail.setText(customer.getEmail());
        btnDelete.setVisible(true);
    }

    /**
     * Method that sets the deletion as an event on the button
     * @param actionEvent
     * @throws SQLException
     */
    public void delete(ActionEvent actionEvent) throws SQLException {
        customer = new Customer(customer.getId(), getName(), getAddress(), getCity(), getPhone(), getEmail());
        customerBLL = new CustomerBLL();
        if(customerBLL.deleteCustomer(customer) == 1){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Customer deleted successfully");
            alert.show();
        }
    }

    private String getName() { return tfName.getText(); }

    private String getAddress() { return tfAddress.getText(); }

    private String getCity() { return tfCity.getText(); }

    private String getPhone() { return tfPhone.getText(); }

    private String getEmail() { return tfEmail.getText(); }

    /**
     * initialization of combo box with all the names
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            cbName.getItems().addAll(getNames());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
