package model;

public class Order {
    private int id;
    private String customer;
    private String product;
    private int quantity;
    private double price;

    /**
     * Constructor for object of class Order
     * @param id
     * @param customer
     * @param product
     * @param quantity
     * @param price
     */
    public Order(int id, String customer, String product, int quantity, double price) {
        this.id = id;
        this.customer = customer;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
    }

    /**
     * Constructor for object of class Order
     * @param customer
     * @param product
     * @param quantity
     * @param price
     */
    public Order(String customer, String product, int quantity, double price) {
        this.customer = customer;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
    }

    /**
     * getter for order id
     * @return int id
     */
    public int getId() {
        return id;
    }

    /**
     * setter for order id
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * getter for customer
     * @return
     */
    public String getCustomer() { return customer; }

    /**
     * setter for customer
     * @param customer
     */
    public void setCustomer(String customer) { this.customer = customer; }

    /**
     * getter for product
     * @return
     */
    public String getProduct() { return product; }

    /**
     * setter for product
     * @param product
     */
    public void setProduct(String product) { this.product = product; }

    /**
     * getter for price
     * @return
     */
    public double getPrice() { return price; }

    /**
     * setter for price
     * @param price
     */
    public void setPrice(double price) { this.price = price; }

    /**
     * getter for quantity
     * @return
     */
    public int getQuantity() { return quantity; }

}
