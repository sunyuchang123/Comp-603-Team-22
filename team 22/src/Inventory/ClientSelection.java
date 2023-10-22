/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Inventory;
import Controller.InventoryController;
/**
 *
 * @author yuchang Sun
 */
public class ClientSelection {
    
    public final String Selection = "Inventory";

    public String getProductInfo(Productdata item,InventoryController controller) {
        StringBuilder returnString = new StringBuilder();
        returnString.append("Product name: ").append(item.getName());
        returnString.append("\nProduct quantity: ").append(item.getQuantity());
        returnString.append("\nProduct price: $").append(item.getPrice());
        returnString.append("\nProduct Code: ").append(item.getCode());
        
        // If the current GUI window is the inventory window, append the product's code
        if (isInInventoryWindow(controller)) {
            returnString.append("\nProduct Code: ").append(item.getCode());
        }
        
        return returnString.toString();
    }
 // Updates the controller's GUI window based on the provided selection string
    public void swapEnum(InventoryController controller, String inputSelection) {
        if (Selection.equalsIgnoreCase(inputSelection)) {
            controller.swapWindow(InventoryController.GuiWindow.INVENTORY);
        } else {
            controller.swapWindow(InventoryController.GuiWindow.NONE);
        }
    }
    /**
     * Checks if the current GUI window of the controller is set to INVENTORY.
     * 
     * @param controller The inventory controller that indicates the current GUI window.
     * @return true if the current GUI window is INVENTORY, false otherwise.
     */
    private boolean isInInventoryWindow(InventoryController controller) {
        if (controller.guiWindow != InventoryController.GuiWindow.INVENTORY) {
            System.out.println("ERROR: No such product type!");
            return false;
        }
        return true;
    }
  
    private boolean isInventoryWindow(InventoryController controller) {
       return controller.guiWindow == InventoryController.GuiWindow.INVENTORY;
   }
    /**
    * Retrieves the current selection type based on the controller's state.
    * 
    * @param controller The inventory controller that indicates the current GUI window.
    * @return The current selection type.
    */
    public String getCurrentSelection(InventoryController controller) {
       if (isInventoryWindow(controller)) {
           return Selection;
       }

       System.out.println("Selection unavailable");
       return "unknown";
   }
}
    

    


