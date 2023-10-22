/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Inventory;


/**
 *
 * @author Junxiu Wu, Yuchang Sun
 */
public class Product extends Productdata {

    private int Code;

     // Default constructor initializes product with default values
    public Product() {
        super();
        getCode(0);
    }

    // Parameterized constructor initializes product with given values
    public Product(String name, double price, int quantity, int code) {
        super(name, price, quantity);
        getCode(code);
    }

    @Override
    public int getCode() {
        return Code;
    }

    // Retrieves the unique code of the product
    @Override
    public void getCode(int code) {
        this.Code = code;
    }

    // Generates an SQL snippet for inserting this product into a database
    @Override
    public String getSQLString() {
        return String.format("('%s', %d, %f, %d)", getName(), getQuantity(), getPrice(), getCode());
    }

    // Represents the product as a string including its superclass attributes and its unique code
    @Override
    public String toString() {
        return String.format("%s %d", super.toString(), getCode());
    }
}
