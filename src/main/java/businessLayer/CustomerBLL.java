package businessLayer;

import businessLayer.validators.CustomerFieldsValidator;
import businessLayer.validators.EmailValidator;
import businessLayer.validators.PhoneValidator;
import businessLayer.validators.Validator;
import dataAccessLayer.CustomerDAO;

import model.Customer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class CustomerBLL {

    private List<Validator<Customer>> validators;
    private CustomerDAO customerDAO;

    /**
     * Constructor for the CustomerBLL class which also creates the list of validators by adding new validators that check if the fields are not empty, if they contain only alphabets, spaces and hyphens
     * in case of name and city and to check if the phone number and the email are valid
     */
    public CustomerBLL() {
        validators = new ArrayList<Validator<Customer>>();
        validators.add(new CustomerFieldsValidator());
        validators.add(new PhoneValidator());
        validators.add(new EmailValidator());

        customerDAO = new CustomerDAO();
    }

    /**
     * Method to find customer having a specified id in the customers table
     * @param id depending on this criteria, a customer in the customers table will be found
     * @return an object of class Customer is returned
     * @throws SQLException
     */
    public Customer findCustomerById(int id) throws SQLException {
        Customer customer = customerDAO.findById(id);
        if (customer == null) {
            throw new NoSuchElementException("The product with id = " + id + " was not found!");
        }
        return customer;
    }

    /**
     * Method to find customer having a specified name in the customers table
     * @param name depending on this criteria, a customer in the customers table will be found
     * @return an object of class Customer is returned
     * @throws SQLException
     */
    public Customer findCustomerByName(String name) throws SQLException {
        Customer customer = customerDAO.findByName(name);
        if (customer == null) {
            throw new NoSuchElementException("The product with name = " + name + " was not found!");
        }
        return customer;
    }

    /**
     * Method to check if all the attributes of customer object meet the required criteria and then send customer as a parameter to the insert method from CustomerDAO
     * @param customer object to be send as parameter to the insert method from CustomerDAO
     * @return returns -1 if the insertion failed or the id of the customer otherwise
     * @throws SQLException
     */
    public int insertCustomer(Customer customer) throws SQLException {
       boolean ok = true;
        for (Validator<Customer> v : validators) {
            if(!v.validate(customer)){
                ok = false;
                break;
            }
        }
        if(ok) {
            return CustomerDAO.insert(customer);
        }
       return -1;
    }

    /**
     * Method to check if all the attributes of customer object meet the required criteria and then send customer as a parameter to the updateCustomer method from CustomerDAO
     * @param customer object to be send as parameter to the updateCustomer method from CustomerDAO
     * @return 0 if the update operation failed, 1 otherwise
     * @throws SQLException
     */
    public int updateCustomer(Customer customer) throws SQLException {
        boolean ok = true;
        for (Validator<Customer> v : validators) {
            if(!v.validate(customer)){
                ok = false;
                break;
            }
        }
        if(ok) {
           return CustomerDAO.updateCustomer(customer);
        }
        return -1;
    }

    /**
     * Method to call deleteCustomer method from CustomerDAO
     * @param customer object to be sent as parameter to the deleteCustomer method from CustomerDAO
     * @return 0 if the delete operation failed, 1 otherwise
     * @throws SQLException
     */
    public int deleteCustomer(Customer customer) throws SQLException {
        return CustomerDAO.deleteCustomer(customer);
    }

    /**
     * Method to call selectNames method from CustomerDAO
     * @return a list of String objects representing the names is returned
     * @throws SQLException
     */
    public ArrayList<String> getNames() throws SQLException {
        return  CustomerDAO.selectNames();
    }

    /**
     * Method to call findAll method from CustomerDAO
     * @return a list of Customer objects is returned
     * @throws SQLException
     */
    public ArrayList<Customer> getCustomers() throws SQLException {
        return CustomerDAO.findAll();
    }

}
