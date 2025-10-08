import java.sql.*;

public class DBConnection {
    private static Connection con = null;

    public static Connection getConnection() {
        if (con != null) return con;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/atm_system?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC",
                "root",
                "saurabh123@patil"
            );
            System.out.println("✅ Connected to DB successfully!");
        } catch (ClassNotFoundException e) {
            System.out.println("❌ MySQL Driver not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("❌ Cannot connect to DB: " + e.getMessage());
            e.printStackTrace();
        }

        return con;
    }
}
