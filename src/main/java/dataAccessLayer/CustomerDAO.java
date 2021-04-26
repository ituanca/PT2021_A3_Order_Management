package dataAccessLayer;

import connection.ConnectionFactory;
import model.Customer;
import model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomerDAO {
    protected static final Logger LOGGER = Logger.getLogger(CustomerDAO.class.getName());
    private static final String insertStatementString = "INSERT INTO customers (name,address,city,phone,email)" + " VALUES (?,?,?,?,?)";
    private final static String findStatementString = "SELECT * FROM customers where id = ?";
    private final static String findByNameStatementString = "SELECT * FROM customers where name = ?";
    private final static String findNamesStatementString = "SELECT name FROM customers";
    private final static String updateStatementString = "UPDATE customers SET name = ?, address = ?, city = ?, phone = ?, email = ? WHERE id = ?";
    private final static String deleteStatementString = "DELETE FROM customers WHERE id = ?";
    private final static String findAllStatementString = "SELECT * FROM customers";

    public static int updateCustomer(Customer customer) throws SQLException {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement updateStatement = null;
        int successfullyUpdated = 0;
        try {
            updateStatement = dbConnection.prepareStatement(updateStatementString, Statement.RETURN_GENERATED_KEYS);
            updateStatement.setString(1, customer.getName());
            updateStatement.setString(2, customer.getAddress());
            updateStatement.setString(3, customer.getCity());
            updateStatement.setString(4, customer.getPhone());
            updateStatement.setString(5, customer.getEmail());
            updateStatement.setInt(6, customer.getId());
            System.out.println("id: " + customer.getId());
            successfullyUpdated =  updateStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"CustomerDAO:update " + e.getMessage());
            System.out.println("CustomerDAO:update" + e.getMessage());
        } finally {
            ConnectionFactory.close(updateStatement);
            ConnectionFactory.close(dbConnection);
        }
        return successfullyUpdated;
    }

    public static int deleteCustomer(Customer customer) throws SQLException {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement deleteStatement = null;
        int successfullyDeleted = 0;
        try {
            deleteStatement = dbConnection.prepareStatement(deleteStatementString, Statement.RETURN_GENERATED_KEYS);
            deleteStatement.setInt(1, customer.getId());
            successfullyDeleted = deleteStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"CustomerDAO:delete " + e.getMessage());
            System.out.println("CustomerDAO:delete" + e.getMessage());
        } finally {
            ConnectionFactory.close(deleteStatement);
            ConnectionFactory.close(dbConnection);
        }
        return successfullyDeleted;
    }

    public static ArrayList<String> selectNames() throws SQLException {
        ArrayList<String> names = new ArrayList<>();
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findNamesStatement = null;
        ResultSet resultSet = null;
        try {
            findNamesStatement = dbConnection.prepareStatement(findNamesStatementString);
            resultSet = findNamesStatement.executeQuery();
            while(resultSet.next()){
                names.add(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"CustomerDAO:selectNames " + e.getMessage());
            System.out.println("CustomerDAO:selectNames" + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(findNamesStatement);
            ConnectionFactory.close(dbConnection);
        }
        return names;
    }

    public static ArrayList<Customer> findAll() throws SQLException {
        ArrayList<Customer> customers = new ArrayList<>();
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findAllStatement = null;
        ResultSet resultSet = null;
        try {
            findAllStatement = dbConnection.prepareStatement(findAllStatementString);
            resultSet = findAllStatement.executeQuery();
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String address = resultSet.getString("address");
                String city = resultSet.getString("city");
                String phone = resultSet.getString("phone");
                String email = resultSet.getString("email");

                Customer customer = new Customer( id, name, address, city, phone, email);
                customers.add(customer);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"CustomerDAO:find all " + e.getMessage());
            System.out.println("CustomerDAO:find all " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(findAllStatement);
            ConnectionFactory.close(dbConnection);
        }
        return customers;
    }

    public Customer findByName(String customerName) throws SQLException {
        Customer customer = null;
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet resultSet = null;
        try {
            findStatement = dbConnection.prepareStatement(findByNameStatementString);
            findStatement.setString(1, customerName);
            resultSet = findStatement.executeQuery();
            resultSet.next();

            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String address = resultSet.getString("address");
            String city = resultSet.getString("city");
            String phone = resultSet.getString("phone");
            String email = resultSet.getString("email");

            customer = new Customer( id, name, address, city, phone, email);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"CustomerDAO:findByName " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
        return customer;
    }

    public Customer findById(int customerID) throws SQLException {
        Customer customer = null;
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet resultSet = null;
        try {
            findStatement = dbConnection.prepareStatement(findStatementString);
            findStatement.setLong(1, customerID);
            resultSet = findStatement.executeQuery();
            resultSet.next();

            String name = resultSet.getString("name");
            String address = resultSet.getString("address");
            String city = resultSet.getString("city");
            String phone = resultSet.getString("phone");
            String email = resultSet.getString("email");

            customer = new Customer( name, address, city, phone, email);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"CustomerDAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
        return customer;
    }

    public static int insert(Customer customer) throws SQLException {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement insertStatement = null;
        int insertedId = -1;
        try {
            insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setString(1, customer.getName());
            insertStatement.setString(2, customer.getAddress());
            insertStatement.setString(3, customer.getCity());
            insertStatement.setString(4, customer.getPhone());
            insertStatement.setString(5, customer.getEmail());
            insertStatement.executeUpdate();

            ResultSet rs = insertStatement.getGeneratedKeys();
            if (rs.next()) {
                insertedId = rs.getInt(1);
            }
            System.out.println(customer.getName() + " " + customer.getAddress() + " " + customer.getCity() + " " + customer.getPhone() + " " + customer.getEmail());
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "CustomerDAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
        return insertedId;
    }
}
