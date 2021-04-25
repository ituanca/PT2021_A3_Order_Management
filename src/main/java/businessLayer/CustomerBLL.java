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
import java.util.logging.Logger;

public class CustomerBLL {

    protected static final Logger LOGGER = Logger.getLogger(CustomerDAO.class.getName());
    private List<Validator<Customer>> validators;
    private CustomerDAO customerDAO;

    public CustomerBLL() {
        validators = new ArrayList<Validator<Customer>>();
        validators.add(new CustomerFieldsValidator());
        validators.add(new PhoneValidator());
        validators.add(new EmailValidator());

        customerDAO = new CustomerDAO();
    }

    public Customer findCustomerById(int id) throws SQLException {
        Customer customer = customerDAO.findById(id);
        if (customer == null) {
            throw new NoSuchElementException("The product with id = " + id + " was not found!");
        }
        return customer;
    }

    public Customer findCustomerByName(String name) throws SQLException {
        Customer customer = customerDAO.findByName(name);
        if (customer == null) {
            throw new NoSuchElementException("The product with name = " + name + " was not found!");
        }
        return customer;
    }

    public int insertCustomer(Customer customer) throws SQLException {
       boolean ok = true;
        for (Validator<Customer> v : validators) {       // verificam daca datele de inserat sunt valide
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

    public int updateCustomer(Customer customer) throws SQLException {
        boolean ok = true;
        for (Validator<Customer> v : validators) {       // verificam daca datele de inserat sunt valide
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

    public int deleteCustomer(Customer customer) throws SQLException {
        return CustomerDAO.deleteCustomer(customer);
    }

    public ArrayList<String> getNames() throws SQLException {
        return  CustomerDAO.selectNames();
    }

    public ArrayList<Customer> getCustomers() throws SQLException {
        return CustomerDAO.findAll();
    }

}
