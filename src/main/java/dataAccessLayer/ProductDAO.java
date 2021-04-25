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

}
