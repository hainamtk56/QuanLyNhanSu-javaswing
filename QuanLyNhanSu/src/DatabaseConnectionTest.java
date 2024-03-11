import java.sql.Connection;
import java.sql.SQLException;
import ConnectionManager.ConnectionManager;

public class DatabaseConnectionTest {
    public static void main(String[] args) {
        Connection con = ConnectionManager.getConnection();
        if (con != null) {
            System.out.println("Database connection established successfully.");
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("Failed to close the database connection.");
                e.printStackTrace();
            }
        } else {
            System.out.println("Failed to establish database connection.");
        }
    }
}
