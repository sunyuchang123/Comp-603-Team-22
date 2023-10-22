package functionality;

import Inventory.Productdata;
import Inventory.Product;


/**
 *
 * @author Junxiu Wu
 */
public class CreateProduct {

    //create the product.
    public Productdata createProduct(String category, String name, int quantity, double price, String other) {
        Productdata item;

        if ("INVENTORY".equalsIgnoreCase(category)) {
            item = new Product();
            try {
                item.getCode(Integer.parseInt(other));
            } catch (NumberFormatException e) {
                System.out.println("Invalid Input!");
                return null; // Return null if parsing fails
            }
        } else {
            System.out.println("Wrong selection");
            return null; // Return null for unrecognized categories
        }

        item.setName(name);
        item.setQuantity(quantity);
        item.setPrice(price);
        
        return item;
    }
}
