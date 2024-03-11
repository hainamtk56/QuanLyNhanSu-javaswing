package ConnectionManager;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    private static Connection con;

    public static Connection getConnection() {
        String url="jdbc:mysql://localhost:3306/quanlynhansu";
        String username="root";
        String password="";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try {
                con = DriverManager.getConnection(url,username,password);
            } catch (SQLException ex) {
                System.out.println("Failed to create the database connection.");
                ex.printStackTrace();
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver not found.");
            ex.printStackTrace();
        }
        return con;
    }
}