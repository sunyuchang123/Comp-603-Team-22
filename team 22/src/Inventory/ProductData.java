/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Inventory;

/**
 *
 * @author yuchang, Junxiu Wu
 */
public abstract class ProductData {
     // Instance variables
    protected String name;
    protected double price;
    protected int quantity;

    // Default constructor
    public ProductData() {
        setName("unknown");
        setPrice(0);
        setQuantity(0);
    }

    // Parameterized constructor
    public ProductData(String name, double price, int quantity) {
        setName(name);
        setPrice(price);
        setQuantity(quantity);
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Setter for name
    public void setName(String name) {
        this.name = name;
    }

    // Getter for price
    public double getPrice() {
        return price;
    }

    // Setter for price
    public void setPrice(double price) {
        this.price = price;
    }

    // Getter for quantity
    public int getQuantity() {
        return quantity;
    }

    // Setter for quantity
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Represents the selection in a formatted string.
     *
     * @return String representation of the selection.
     */
    @Override
    public String toString() {
        return name + " " + quantity + " " + price;
    }

    // Hook methods (default implementations provided, to be overridden by subclasses if needed)

    public void getCode(int days) {
        // No-op in this abstract class
    }

    public void setUsage(String objectToClean) {
    }

    public void setApplication(String bodyPart) {
    }

    public int getCode() {
        return -1; // Default value
    }

    public String getUsage() {
        return ""; 
    }

    public String getWarning() {
        return ""; 
}
}
