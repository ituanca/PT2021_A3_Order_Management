package model;

public class Customer {
    private int id;
    private String name;
    private String address;
    private String city;
    private String phone;
    private String email;

    /**
     * Constructor for object of class Customer
     * @param id
     * @param name
     * @param address
     * @param city
     * @param phone
     * @param email
     */
    public Customer(int id, String name, String address, String city, String phone, String email) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.city = city;
        this.phone = phone;
        this.email = email;
    }

    /**
     * Constructor for object of class Customer
     * @param name
     * @param address
     * @param city
     * @param phone
     * @param email
     */
    public Customer(String name, String address, String city, String phone, String email) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.phone = phone;
        this.email = email;
    }

    /**
     * getter for customer id
     * @return int id
     */
    public int getId() { return id; }

    /**
     * setter for customer id
     * @param id
     */
    public void setId(int id) { this.id = id; }

    /**
     * getter for customer name
     * @return String name
     */
    public String getName() { return name; }

    /**
     * setter for customer name
     * @param name
     */
    public void setName(String name) { this.name = name; }

    /**
     * getter for customer address
     * @return String address
     */
    public String getAddress() { return address; }

    /**
     *  getter for customer city
     * @return String city
     */
    public String getCity() { return city; }

    /**
     * getter for customer phone
     * @return String phone
     */
    public String getPhone() { return phone; }

    /**
     * getter for customer email
     * @return String email
     */
    public String getEmail() { return email; }

}
