/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package InventoryManagementSystem;
import Controller.InventoryController;
import Model.InventoryModel;
import View.PanelView;

/**
 *
 * @author yuchang Sun
 */
public class InventoryManagementSystem {

    public static void main(String[] args) {
         // Create instances of model and view
        InventoryModel model = InventoryModel.getInstance();
        PanelView view = new PanelView();

        // Pass the model and view instances to the controller
        InventoryController inventoryController = new InventoryController(model, view);
        inventoryController.systemStart();
    }
}
