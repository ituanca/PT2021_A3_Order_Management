package presentationLayer;

import businessLayer.CustomerBLL;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.Customer;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EditCustomerController implements Initializable {

    public ComboBox cbName;
    public Button btnEditCustomer;
    public TextField tfName;
    public TextField tfAddress;
    public TextField tfCity;
    public TextField tfPhone;
    public TextField tfEmail;
    public Button btnUpdate;

    Customer customer;
    CustomerBLL customerBLL;

    private ArrayList<String> getNames() throws SQLException {
        customerBLL = new CustomerBLL();
        return customerBLL.getNames();
    }

    public void selectCustomer(ActionEvent actionEvent) {
        btnEditCustomer.setVisible(true);
        tfName.setText("");
        tfAddress.setText("");
        tfCity.setText("");
        tfPhone.setText("");
        tfEmail.setText("");
        tfName.setEditable(false);
        tfAddress.setEditable(false);
        tfCity.setEditable(false);
        tfPhone.setEditable(false);
        tfEmail.setEditable(false);
        btnUpdate.setVisible(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            cbName.getItems().addAll(getNames());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void editCustomer(ActionEvent actionEvent) throws SQLException {
        customer = customerBLL.findCustomerByName((String) cbName.getValue());
        tfName.setText(customer.getName());
        tfAddress.setText(customer.getAddress());
        tfCity.setText(customer.getCity());
        tfPhone.setText(customer.getPhone());
        tfEmail.setText(customer.getEmail());
        tfName.setEditable(true);
        tfAddress.setEditable(true);
        tfCity.setEditable(true);
        tfPhone.setEditable(true);
        tfEmail.setEditable(true);
        btnUpdate.setVisible(true);
    }

    public void update(ActionEvent actionEvent) throws SQLException {
        if(checkIfFieldsAreNotEmpty()){
            customer = new Customer(customer.getId(), getName(), getAddress(), getCity(), getPhone(), getEmail());
            customerBLL = new CustomerBLL();
            if(customerBLL.updateCustomer(customer) > -1){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Customer updated successfully");
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
