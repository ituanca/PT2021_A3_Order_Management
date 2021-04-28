package model;

public class Product {
    private int id;
    private String name;
    private double unitPrice;
    private int unitsInStock;

    /**
     * Constructor for object of class Product
     * @param id
     * @param name
     * @param unitPrice
     * @param unitsInStock
     */
    public Product(int id, String name, double unitPrice, int unitsInStock) {
        this.id = id;
        this.name = name;
        this.unitPrice = unitPrice;
        this.unitsInStock = unitsInStock;
    }

    /**
     * Constructor for object of class Product
     * @param name
     * @param unitPrice
     * @param unitsInStock
     */
    public Product(String name, double unitPrice, int unitsInStock) {
        this.name = name;
        this.unitPrice = unitPrice;
        this.unitsInStock = unitsInStock;
    }

    /**
     * getter for product id
     * @return int id
     */
    public int getId() { return id; }

    /**
     * setter for product id
     * @param id
     */
    public void setId(int id) { this.id = id; }

    /**
     * getter for product name
     * @return String name
     */
    public String getName() { return name; }

    /**
     * setter for product name
     * @param name
     */
    public void setName(String name) { this.name = name; }

    /**
     * getter for product unit price
     * @return double unit price
     */
    public double getUnitPrice() { return unitPrice; }

    /**
     * getter for product units in stock
     * @return int units in stock
     */
    public int getUnitsInStock() { return unitsInStock; }

    /**
     * setter for product units in stock
     * @param unitsInStock
     */
    public void setUnitsInStock(int unitsInStock) { this.unitsInStock = unitsInStock; }

}
