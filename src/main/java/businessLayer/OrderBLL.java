package businessLayer;

import dataAccessLayer.OrderDAO;

import model.Order;


import java.sql.SQLException;
import java.util.ArrayList;

public class OrderBLL {

    public int createOrder(Order order) throws SQLException {
         return OrderDAO.createOrder(order);
    }

    public Order getLastOrder() throws SQLException {
        return OrderDAO.getLastOrder();
    }

}
