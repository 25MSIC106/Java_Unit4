import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DBConnection {

    public static Connection connect() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:expense.db");

            // Create table if not exists
            Statement stmt = conn.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS expenses ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "title TEXT, "
                    + "category TEXT, "
                    + "amount REAL, "
                    + "date TEXT)";
            stmt.execute(sql);
            System.out.println("Table Created!");


            return conn;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
