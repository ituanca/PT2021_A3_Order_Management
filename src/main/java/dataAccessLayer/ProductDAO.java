package dataAccessLayer;

import connection.ConnectionFactory;
import model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductDAO {

    protected static final Logger LOGGER = Logger.getLogger(ProductDAO.class.getName());
    private static final String insertStatementString = "INSERT INTO products (name,unitPrice,unitsInStock)" + " VALUES (?,?,?)";
    private final static String findStatementString = "SELECT * FROM products where id = ?";
    private final static String findByNameStatementString = "SELECT * FROM products where name = ?";
    private final static String findNamesStatementString = "SELECT name FROM products";
    private final static String updateStatementString = "UPDATE products SET name = ?, unitPrice = ?, unitsInStock = ? WHERE id = ?";
    private final static String deleteStatementString = "DELETE FROM products WHERE id = ?";
    private final static String findAllStatementString = "SELECT * FROM products";

    /**
     * Method to find product having a specified id in the products table
     * @param productID depending on this criteria, a product in the products table will be found
     * @return an object of class Product is returned
     * @throws SQLException
     */
    public Product findById(int productID) throws SQLException {
        Product product = null;
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet resultSet = null;
        try {
            findStatement = dbConnection.prepareStatement(findStatementString);
            findStatement.setLong(1, productID);
            resultSet = findStatement.executeQuery();
            resultSet.next();

            String name = resultSet.getString("name");
            double unitPrice = resultSet.getDouble("unitPrice");
            int unitsInStock = resultSet.getInt("unitsInStock");

            product = new Product(productID, name, unitPrice, unitsInStock);

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"ProductDAO:findById " + e.getMessage());
            System.out.println("ProductDAO:findById" + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
        return product;
    }

    /**
     * Method to find product having a specified name in the products table
     * @param productName depending on this criteria, a product in the products table will be found
     * @return an object of class Product is returned
     * @throws SQLException
     */
    public Product findByName(String productName) throws SQLException {
        Product product = null;
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet resultSet = null;
        try {
            findStatement = dbConnection.prepareStatement(findByNameStatementString);
            findStatement.setString(1, productName);
            resultSet = findStatement.executeQuery();
            resultSet.next();

            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            double unitPrice = resultSet.getDouble("unitPrice");
            int unitsInStock = resultSet.getInt("unitsInStock");

            product = new Product( id, name, unitPrice, unitsInStock);

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"ProductDAO:findByName " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
        return product;
    }

    /**
     * Method to insert product in the products table
     * @param product object to be inserted in the table
     * @return returns -1 if the insertion failed or the id of the product otherwise
     * @throws SQLException
     */
    public static int insert(Product product) throws SQLException {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement insertStatement = null;
        int insertedId = -1;
        try {
            insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setString(1, product.getName());
            insertStatement.setDouble(2, product.getUnitPrice());
            insertStatement.setInt(3, product.getUnitsInStock());
            insertStatement.executeUpdate();

            ResultSet rs = insertStatement.getGeneratedKeys();
            if (rs.next()) {
                insertedId = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ProductDAO:insert " + e.getMessage());
            System.out.println("ProductDAO:insert" + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
        return insertedId;
    }

    /**
     * Method to update product in the products table
     * @param product object to be updated in the table
     * @return 0 if the update operation failed, 1 otherwise
     * @throws SQLException
     */
    public static int updateProduct(Product product) throws SQLException {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement updateStatement = null;
        int successfullyUpdated = 0;
        try {
            updateStatement = dbConnection.prepareStatement(updateStatementString, Statement.RETURN_GENERATED_KEYS);
            updateStatement.setString(1, product.getName());
            updateStatement.setDouble(2, product.getUnitPrice());
            updateStatement.setInt(3, product.getUnitsInStock());
            updateStatement.setInt(4, product.getId());
            successfullyUpdated = updateStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"ProductDAO:update " + e.getMessage());
            System.out.println("ProductDAO:update" + e.getMessage());
        } finally {
            ConnectionFactory.close(updateStatement);
            ConnectionFactory.close(dbConnection);
        }
        return successfullyUpdated;
    }

    /**
     * Method to delete product from the products table
     * @param product object to be deleted from the table
     * @return 0 if the delete operation failed, 1 otherwise
     * @throws SQLException
     */
    public static int deleteProduct(Product product) throws SQLException {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement deleteStatement = null;
        int successfullyDeleted = 0;
        try {
            deleteStatement = dbConnection.prepareStatement(deleteStatementString, Statement.RETURN_GENERATED_KEYS);
            deleteStatement.setInt(1, product.getId());
            successfullyDeleted = deleteStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"ProductDAO:delete " + e.getMessage());
            System.out.println("ProductDAO:delete" + e.getMessage());
        } finally {
            ConnectionFactory.close(deleteStatement);
            ConnectionFactory.close(dbConnection);
        }
        return successfullyDeleted;
    }

    /**
     * Method to get the names of all the products in the table
     * @return a list of String objects representing the names is returned
     * @throws SQLException
     */
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
            LOGGER.log(Level.WARNING,"ProductDAO:selectNames " + e.getMessage());
            System.out.println("ProductDAO:selectNames" + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(findNamesStatement);
            ConnectionFactory.close(dbConnection);
        }
        return names;
    }

    /**
     * Method to get all the products in the table
     * @return a list of Product objects is returned
     * @throws SQLException
     */
    public static ArrayList<Product> findAll() throws SQLException {
        ArrayList<Product> products = new ArrayList<>();
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findAllStatement = null;
        ResultSet resultSet = null;
        try {
            findAllStatement = dbConnection.prepareStatement(findAllStatementString);
            resultSet = findAllStatement.executeQuery();
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double unitPrice = resultSet.getDouble("unitPrice");
                int unitsInStock = resultSet.getInt("unitsInStock");

                Product product = new Product( id, name, unitPrice, unitsInStock);
                products.add(product);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"ProductDAO:find all " + e.getMessage());
            System.out.println("ProductDAO:find all " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(findAllStatement);
            ConnectionFactory.close(dbConnection);
        }
        return products;
    }
}
