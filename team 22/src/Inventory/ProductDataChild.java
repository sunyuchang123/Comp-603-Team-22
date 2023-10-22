/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Inventory;

import Inventory.Productdata;

/**
 *
 * @author Junxiu Wu
 */

public class ProductDataChild extends Productdata {

    private String inventory;

    // Constructors
    public ProductDataChild() {
        super();
        this.inventory = "";
    }

    public ProductDataChild(String name, double price, int quantity, String inventory) {
        super(name, price, quantity);
        this.inventory = inventory;
    }

    @Override
    public String getInventory() {
        return inventory;
    }

    @Override
    public void setInventory(String inventory) {
        this.inventory = inventory;
    }

    // Business Logic
    /**
     * Transforms the product's attributes into an SQL-compatible format.
     */
    @Override
    public String getSQLString() {
        return String.format(
            "('%s', %d, %.2f, '%s')", 
            getName(), 
            getQuantity(), 
            getPrice(), 
            getInventory()
        );
    }


    @Override
    public String toString() {
        return String.format("%s %s", super.toString(), inventory);
    }
}
