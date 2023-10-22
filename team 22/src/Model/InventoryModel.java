/**
 *
 * @author Junxiu Wu
 */
package Model;

import functionality.CreateProduct;
import DataBase.DBmanager;
import Inventory.Productdata;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import java.util.List;
;

public class InventoryModel {

    private DBmanager db;
    Connection conn;
    private Statement statement;
    
    private static final String dbName = "IVT_EDB";
    private static InventoryModel instance;//singleton
    private static final String USER_NAME = "wujunxiu", PASSWORD = "wujunxiu", URL = "jdbc:derby:" + dbName + ";create=true";

    private InventoryModel() {
        
        establishConnection();
    }

   /**
 * Establishes a connection to the database using pre-defined constants.
 * Outputs the connection status to the console.
 */
    public void establishConnection() {
        try {
            conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            System.out.printf("Connected to database: %s%n", dbName);
        } catch (SQLException ex) {
            System.out.printf("Failed to connect to database: %s%nError: %s%n", dbName, ex.getMessage());
        }
    }
    
    public Connection getConnection() {
	if (this.conn != null){ 
           return this.conn;
        }
        else{ 
            return null;
        }
    }

    //close connection to DB
    public void closeConnections() {
        if (conn != null) {
            try {
                conn.close();
                System.out.println(dbName + "closed.");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

        /**
      * Initializes and populates sample tables in the database.
      */
     public void createTables() {
         if (db == null) {
             db = new DBmanager();
         }
         db.createTables();
     }

        /**
     * Fetches all names from the Item_Name column of a specified table.
     * 
     * @param table Name of the table to query.
     * @return An array of item names.
     */
    public String[] getProductNames(String table) {
        List<String> itemNames = new ArrayList<>();

        String query = "SELECT ITEM_NAME FROM " + table;

        try (PreparedStatement preparedStatement = conn.prepareStatement(query);
             ResultSet rs = preparedStatement.executeQuery()) {

            while (rs.next()) {
                itemNames.add(rs.getString("ITEM_NAME"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(InventoryModel.class.getName()).log(Level.SEVERE, "Failed to fetch item names.", ex);
        }

        return itemNames.toArray(new String[0]);
    }

    /**
     * Fetches the quantity of a specified item from a table.
     * 
     * @param table    Name of the table to query.
     * @param itemName Name of the item whose quantity is to be retrieved.
     * @return Quantity of the item.
     */
    public int getProductQuantity(String table, String itemName) {
        int quantity = 0;

        String query = "SELECT QUANTITY FROM " + table + " WHERE ITEM_NAME = ?";

        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setString(1, itemName);

            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    quantity = rs.getInt("QUANTITY");
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(InventoryModel.class.getName()).log(Level.SEVERE, "Failed to fetch item quantity.", ex);
        }

        return quantity;
    }

        /**
      * Retrieves all information from a row in the specified table based on the Item_Name.
      * Uses the data to create and return a ProductData object.
      * 
      * @param table    Name of the table to query.
      * @param itemName Name of the item to retrieve.
      * @return A ProductData object containing item details.
      */
     public Productdata getProduct(String table, String itemName) {
         Productdata item = null;
         String query = "SELECT * FROM " + table + " WHERE ITEM_NAME = ?";

         try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
             preparedStatement.setString(1, itemName);

             try (ResultSet rs = preparedStatement.executeQuery()) {
                 if (rs.next()) {
                     CreateProduct factory = new CreateProduct();
                     item = factory.createProduct(table, 
                                                  rs.getString(1), 
                                                  rs.getInt(2), 
                                                  rs.getDouble(3), 
                                                  rs.getString(4));
                 }
             }
         } catch (SQLException ex) {
             Logger.getLogger(InventoryModel.class.getName()).log(Level.SEVERE, "Failed to retrieve item.", ex);
         }

         return item;
     }

     /**
      * Retrieves all information from every row of the specified table.
      * Uses the data to create an array of ProductData objects.
      * 
      * @param table Name of the table to query.
      * @return An array of ProductData objects.
      */
     public Productdata[] getTable(String table) {
         String[] itemNames = this.getProductNames(table);
         Productdata[] items = new Productdata[itemNames.length];

         for (int i = 0; i < itemNames.length; i++) {
             items[i] = this.getProduct(table, itemNames[i]);
         }

         return items;
     }
    
       // singleton
    public static InventoryModel getInstance() {
        if (instance == null) {
            synchronized (InventoryModel.class) {
                if (instance == null) {
                    instance = new InventoryModel();
                }
            }
        }
        return instance;
    }


            /**
         * Updates the quantity of a specific item in the given table.
         *
         * @param table    The table to update.
         * @param itemName The name of the item.
         * @param amount   The amount to add to the current quantity.
         */
        public void updateProductQuantity(String table, String itemName, int amount) {
            String selectQuery = "SELECT QUANTITY FROM " + table + " WHERE ITEM_NAME = ?";
            String updateQuery = "UPDATE " + table + " SET QUANTITY = ? WHERE ITEM_NAME = ?";

            try (PreparedStatement selectStmt = conn.prepareStatement(selectQuery);
                 PreparedStatement updateStmt = conn.prepareStatement(updateQuery)) {

                selectStmt.setString(1, itemName);

                try (ResultSet rs = selectStmt.executeQuery()) {
                    if (rs.next()) {
                        amount += rs.getInt(1);
                    }
                }

                updateStmt.setInt(1, amount);
                updateStmt.setString(2, itemName);
                updateStmt.executeUpdate();

            } catch (SQLException ex) {
                Logger.getLogger(InventoryModel.class.getName()).log(Level.SEVERE, "Failed to update item quantity.", ex);
            }
        }

        /**
         * Adds a new product to the given table.
         *
         * @param table The table to insert into.
         * @param item  The product data to insert.
         */
        public void addProduct(String table, Productdata item) {
            String insertQuery = "INSERT INTO " + table + " VALUES " + item.getSQLString();

            try (PreparedStatement stmt = conn.prepareStatement(insertQuery)) {
                stmt.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(InventoryModel.class.getName()).log(Level.SEVERE, "Failed to add product.", ex);
            }
        }

        /**
         * Removes a product from the given table based on its name.
         *
         * @param table    The table to delete from.
         * @param itemName The name of the item to delete.
         */
        public void removeProduct(String table, String itemName) {
            String deleteQuery = "DELETE FROM " + table + " WHERE ITEM_NAME = ?";

            try (PreparedStatement stmt = conn.prepareStatement(deleteQuery)) {
                stmt.setString(1, itemName);
                stmt.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(InventoryModel.class.getName()).log(Level.SEVERE, "Failed to remove product.", ex);
            }
        }
}