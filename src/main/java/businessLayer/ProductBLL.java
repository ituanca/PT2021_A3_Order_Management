package businessLayer;

import businessLayer.validators.ProductFieldsValidator;
import businessLayer.validators.ProductUnitPriceValidator;
import businessLayer.validators.UnitsInStockValidator;
import businessLayer.validators.Validator;
import dataAccessLayer.ProductDAO;
import model.Product;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


public class ProductBLL {

    private List<Validator<Product>> validators;
    private ProductDAO productDAO;

    /**
     * Constructor for the ProductBLL class which also creates the list of validators by adding new validators that check if the unit price and  the number of units in stock meet some criteria
     */
    public ProductBLL() {
        validators = new ArrayList<Validator<Product>>();
        validators.add(new ProductFieldsValidator());
        validators.add(new ProductUnitPriceValidator());
        validators.add(new UnitsInStockValidator());

        productDAO = new ProductDAO();
    }

    /**
     * Method to find product having a specified id in the products table
     * @param id  depending on this criteria, a product in the products table will be found
     * @return object of class Product
     * @throws SQLException
     */
    public Product findProductById(int id) throws SQLException {
        Product product = productDAO.findById(id);
        if (product == null) {
            throw new NoSuchElementException("The product with id = " + id + " was not found!");
        }
        return product;
    }

    /**
     * Method to find product having a specified name in the products table
     * @param name depending on this criteria, a product in the products table will be found
     * @return an object of class Product is returned
     * @throws SQLException
     */
    public Product findProductByName(String name) throws SQLException {
        Product product = productDAO.findByName(name);
        if (product == null) {
            throw new NoSuchElementException("The product with name = " + name + " was not found!");
        }
        return product;
    }

    /**
     * Method to check if all the attributes of product object meet the required criteria and then send product as a parameter to the insert method from ProductDAO
     * @param product object to be send as parameter to the insert method from ProductDAO
     * @return returns -1 if the insertion failed or the id of the customer otherwise
     * @throws SQLException
     */
    public int insertProduct(Product product) throws SQLException {
        boolean ok = true;
        for (Validator<Product> v : validators) {
            if(!v.validate(product)){
                ok = false;
                break;
            }
        }
        if(ok) {
            return ProductDAO.insert(product);
        }
        return -1;
    }

    /**
     * Method to check if all the attributes of product object meet the required criteria and then send product as a parameter to the updateProduct method from ProductDAO
     * @param product object to be send as parameter to the updateProduct method from ProductDAO
     * @return returns -1 if the update operation failed or the id of the customer otherwise
     * @throws SQLException
     */
    public int updateProduct(Product product) throws SQLException {
        boolean ok = true;
        for (Validator<Product> v : validators) {
            if(!v.validate(product)){
                ok = false;
                break;
            }
        }
        if(ok) {
            return ProductDAO.updateProduct(product);
        }
        return -1;
    }

    /**
     * Method to call deleteProduct from ProductsDAO
     * @param product object to be sent as parameter to the deleteProduct method from ProductDAO
     * @return 0 if the delete operation failed, 1 otherwise
     * @throws SQLException
     */
    public int deleteProduct(Product product) throws SQLException {
        return ProductDAO.deleteProduct(product);
    }

    /**
     * Method to call selectNames method from ProductDAO
     * @return a list of String objects representing the names is returned
     * @throws SQLException
     */
    public ArrayList<String> getNames() throws SQLException {
        return  ProductDAO.selectNames();
    }

    /**
     * Method to call findAll method from ProductDAO
     * @return a list of Product objects is returned
     * @throws SQLException
     */
    public ArrayList<Product> getProducts() throws SQLException {
        return  ProductDAO.findAll();
    }

}
