package start;

import businessLayer.ProductBLL;
import model.Product;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Start {
    protected static final Logger LOGGER = Logger.getLogger(Start.class.getName());

    public static void main(String[] args) throws SQLException {
        Product product = new Product("Biscuits", 10.56, 50);
        ProductBLL productBLL = new ProductBLL();

        int idProduct = productBLL.insertProduct(product);
        try {
            if (idProduct > 0) {
                productBLL.findProductById(idProduct);
            }
        }catch(Exception e){
            LOGGER.log(Level.INFO, e.getMessage());
        }

        //obtain field-value pairs for object through reflection
       // ReflectionExample.retrieveProperties(product);
    }

}
