package businessLayer;

import dataAccessLayer.OrderDAO;

import model.Order;

import java.sql.SQLException;

public class OrderBLL {

    /**
     * Method to call createOrder method from OrderDAO
     * @param order  object to be send as parameter to the createOrder method from OrderDAO
     * @return returns -1 if the insertion failed or the id of the customer otherwise
     * @throws SQLException
     */
    public int createOrder(Order order) throws SQLException {
         return OrderDAO.createOrder(order);
    }

    /**
     * Method to call getLastOrder method from OrderDAO
     * @return an object of class Order is returned
     * @throws SQLException
     */
    public Order getLastOrder() throws SQLException {
        return OrderDAO.getLastOrder();
    }

}
