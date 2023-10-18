package DataBase;

/**
 *
 * @author Junxiu Wu, Yuchang Sun
 */
public class SQLQueries {
    public static String createFoodTable() {
        return "CREATE TABLE Inventory (item_name VARCHAR(50), quantity INT, price DOUBLE, code INT)";
    }

   public static String[] populateProductTable() {
    return new String[]{
        "INSERT INTO Inventory VALUES ('Laptop', 1200, 2.5, 50)",
        "INSERT INTO Inventory VALUES ('Desk Chair', 150, 4.7, 100)",
        "INSERT INTO Inventory VALUES ('Table Lamp', 35, 1.2, 70)",
        "INSERT INTO Inventory VALUES ('Wireless Mouse', 25, 0.3, 200)",
        "INSERT INTO Inventory VALUES ('Headphones', 60, 0.8, 150)",
        "INSERT INTO Inventory VALUES ('Notebook', 3, 0.1, 500)",
        "INSERT INTO Inventory VALUES ('Backpack', 40, 1.5, 80)",
    };
}
}
