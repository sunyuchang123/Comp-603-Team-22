/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import Inventory.ClientSelection;
import Inventory.Productdata;
import Model.InventoryModel;
import View.PanelView;
/**
 *
 * @author yuchang Sun
 */
public class InventoryController {

    /** Represents the data and business logic of the inventory system. */
    public InventoryModel model;

    /** Represents the user interface of the inventory system. */
    public PanelView view;

    /** Represents the current selection or filter applied by the client. */
    public ClientSelection selection;

    /** Enumerates the different GUI windows/views that can be displayed. */
    public enum GuiWindow {
        NONE,       // No specific window/view selected.
        MAIN,       // Home or main window/view.
        INVENTORY   // Inventory category or view.
    }

    /** Current window/view state of the GUI. */
    public GuiWindow guiWindow = GuiWindow.NONE;

    /**
     * Constructor to initialize the controller with specific model and view instances.
     * 
     * @param model The inventory model instance.
     * @param view  The inventory view instance.
     */
    public InventoryController(InventoryModel model, PanelView view) {
        this.model = model;
        this.view = view;
        this.selection = new ClientSelection();
    }
    
    /**
     * Bootstraps the inventory system, setting up the database tables and launching the GUI.
     */
    public void systemStart() {
        model.createTables();
        view.setController(this);
        view.startGUI();
    }


    /**
     * Updates the current GUI window based on the provided enum value.
     * 
     * @param guiWindow The window state to switch to.
     */
    public void swapWindow(GuiWindow guiWindow) {
        this.guiWindow = guiWindow;
    }
    
    /**
     * Safely terminates the inventory system, closing any active database connections 
     * and shutting down the GUI.
     */
    public void endProgram() {
        swapWindow(GuiWindow.NONE);
        model.closeConnections();
        view.dispose();
    }


    /**
     * Retrieves the names of items based on the current client selection.
     * 
     * @return An array of item names.
     */
    public String[] getItemName() {
        return model.getProductNames(selection.getCurrentSelection(this));
    }
    
    /**
     * Fetches detailed information about a specific item.
     * 
     * @param name Name of the item.
     * @return Detailed item information as a string.
     */
    public String getStringName(String name) {
        return selection.getProductInfo(this, model.getProduct(selection.getCurrentSelection(this), name));
    }

    /**
     * Retrieves the quantity of a specified item.
     * 
     * @param name Name of the item.
     * @return Quantity of the item.
     */
    public int getQuantity(String name) {
        return model.getProductQuantity(selection.getCurrentSelection(this), name);
    }

    /**
     * Updates the quantity of a specific item in the inventory.
     * 
     * @param name   Name of the item.
     * @param amount New quantity for the item.
     */
    public void updateQuantity(String name, int amount) {
        model.updateProductQuantity(selection.getCurrentSelection(this), name, amount);
    }

    /**
     * Adds a new product to the inventory.
     * 
     * @param newItem The product data to be added.
     */
    public void add(Productdata newItem) {
        model.addProduct(selection.getCurrentSelection(this), newItem);
    }
    
    /**
     * Removes a specified product from the inventory.
     * 
     * @param name Name of the product to be removed.
     */
    public void remove(String name) {
        model.removeProduct(selection.getCurrentSelection(this), name);
        view.fillInventory();
    }
}

