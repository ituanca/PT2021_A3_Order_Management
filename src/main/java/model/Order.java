package model;

public class Order {
    private int id;
    private String customer;
    private String product;
    private int quantity;
    private double price;

    public Order(int id, String customer, String product, int quantity, double price) {
        this.id = id;
        this.customer = customer;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
    }

    public Order(String customer, String product, int quantity, double price) {
        this.customer = customer;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomer() { return customer; }

    public void setCustomer(String customer) { this.customer = customer; }

    public String getProduct() { return product; }

    public void setProduct(String product) { this.product = product; }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) { this.price = price; }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
