package businessLayer;

import businessLayer.validators.Validator;
import dataAccessLayer.OrderDAO;
import dataAccessLayer.ProductDAO;
import model.Order;
import model.Product;

import java.sql.SQLException;

public class OrderBLL {

    public int createOrder(Order order) throws SQLException {
         return OrderDAO.createOrder(order);
    }

}
