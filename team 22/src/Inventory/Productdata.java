/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Inventory;

/**
 *
 * @author Junxiu WU， yuchang Sun
 */
public abstract class Productdata {

    
    protected String name;
    protected double price;
    protected int quantity;

       /**
     * Constructs a product with default attributes.
     */
    public Productdata() {
        this(null, 0.0, 0);
    }

       /**
     * Constructs a product with specified attributes.
     * 
     * @param name The name of the product.
     * @param price The price of the product.
     * @param quantity The available quantity of the product.
     */
    public Productdata(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

      /**
     * Returns a string representation of the product.
     * 
     * @return A formatted string detailing the product attributes.
     */
    @Override
    public String toString() {
        return String.format("%s %d %.2f", name, quantity, price);
    }
    
    //default implementations provided, to be overridden by subclasses if needed

    public void getCode(int code) {
        // No-op in this abstract class
    }

    public void setInventory(String bodyPart) {
        // No-op in this abstract class
    }

    public int getCode() {
        return -1; // Default value
    }


    public String getInventory() {
        return ""; // Default value
    }

    public String getSQLString() {
        return "";
    }
}