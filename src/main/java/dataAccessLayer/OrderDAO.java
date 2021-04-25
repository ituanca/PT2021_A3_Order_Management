package dataAccessLayer;

import connection.ConnectionFactory;
import model.Order;
import model.Product;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderDAO {

    protected static final Logger LOGGER = Logger.getLogger(OrderDAO.class.getName());
    private static final String insertStatementString = "INSERT INTO orders (customer,product,quantity,price)" + " VALUES (?,?,?,?)";

    public static int createOrder(Order order) throws SQLException {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement insertStatement = null;
        int insertedId = -1;
        try {
            insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setString(1, order.getCustomer());
            insertStatement.setString(2, order.getProduct());
            insertStatement.setInt(3, order.getQuantity());
            insertStatement.setDouble(4, order.getPrice());

            insertStatement.executeUpdate();

            ResultSet rs = insertStatement.getGeneratedKeys();
            if (rs.next()) {
                insertedId = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "OrderDAO:insert " + e.getMessage());
            System.out.println("OrderDAO:insert" + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
        return insertedId;
    }
//
//    public Order findById(int orderID) throws SQLException {
//
//        Order order = null;
//        Connection dbConnection = ConnectionFactory.getConnection();
//        PreparedStatement findStatement = null;
//        ResultSet resultSet = null;
//
//        try {
//            findStatement = dbConnection.prepareStatement(findStatementString);
//            findStatement.setLong(1, orderID);
//
//            resultSet = findStatement.executeQuery();
//
//            resultSet.next();
//
//            int customerID = resultSet.getInt("customerID");
//            int productID = resultSet.getInt("productID");
//            int quantity = resultSet.getInt("quantity");
//            int price = resultSet.getInt("price");
//
//            order = new Order(orderID, customerID, productID, quantity, price);
//
//        } catch (SQLException e) {
//            LOGGER.log(Level.WARNING,"OrderDAO:findById " + e.getMessage());
//        } finally {
//            ConnectionFactory.close(resultSet);
//            ConnectionFactory.close(findStatement);
//            ConnectionFactory.close(dbConnection);
//        }
//        return order;
//    }
}
