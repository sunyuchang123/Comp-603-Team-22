/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Inventory;

/**
 *
 * @author yucha
 */
public class ClientSelection {
    
    public final String Selection = "Inventory";

    public String getProductInfo(Productdata item) {
        StringBuilder returnString = new StringBuilder();
        returnString.append("Product name: ").append(item.getName());
        returnString.append("\nProduct quantity: ").append(item.getQuantity());
        returnString.append("\nProduct price: $").append(item.getPrice());
        returnString.append("\nProduct Code: ").append(item.getCode());

        return returnString.toString();
    }

    /**
     * Retrieves the current selection type.
     * 
     * @return The current selection type.
     */
    public String getCurrentSelection() {
        return Selection;
    }
}

class ProductData {
    public String getName() {
        return "";
    }

    public int getQuantity() {
        return 0;
    }

    public double getPrice() {
        return 0.0;
    }

    public String getCode() {
        return "";
    }
}
