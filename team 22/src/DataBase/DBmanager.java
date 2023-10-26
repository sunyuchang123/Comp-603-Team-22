
package DataBase;

import DataBase.SQLQueries;
import Model.InventoryModel;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;

/**
 *
 * @author Junxiu Wu
 */
public class DBmanager {
  
    private static final Logger LOGGER = Logger.getLogger(DBmanager.class.getName());

    private InventoryModel model;
    private Connection conn;
    private Statement statement;

    //constructor
    public DBmanager() {
        this.model = InventoryModel.getInstance(); //singleton
        this.conn = this.model.getConnection();
        try {
            this.statement = conn.createStatement();
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Failed", ex);
        }
    }

    //check if table is exist
   private boolean isTableExist(String name) {
    try {
        DatabaseMetaData dbmd = conn.getMetaData();
        ResultSet rs = dbmd.getTables(null, null, null, new String[]{"TABLE"});

        while (rs.next()) {
            if (name.equalsIgnoreCase(rs.getString("TABLE_NAME"))) {
                System.out.println("Table " + name + " exists");
                return true;
            }
        }
    } catch (SQLException ex) {
        LOGGER.log(Level.SEVERE, String.format("Error checking existence of table: %s.", name), ex);
    }
    
    return false;
}

    //create table for any product
    public void createTables() {
        createProductTable();
    }

    private void createProductTable() {
    if (isTableExist("Inventory")) {
        return;
    }
    
    System.out.println("Creating table Inventory");
    try {
        statement = conn.createStatement();

        statement.addBatch(SQLQueries.createFoodTable());
        for (String query : SQLQueries.populateProductTable()) {
            statement.addBatch(query);
        }

        statement.executeBatch();
    } catch (SQLException ex) {
        LOGGER.log(Level.SEVERE, "Error occurred.", ex);
    }
}
    
      public InventoryModel getModel() {
        return model;
    }

}
