import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    public static Connection getConnection() {
        Connection conn = null;

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            String url = "jdbc:sqlserver://127.0.0.1:55662;"
                    + "databaseName=LibraryDB;"
                    + "encrypt=true;"
                    + "trustServerCertificate=true;"
                    + "user=libraryuser;"
                    + "password=Library@123;";

            conn = DriverManager.getConnection(url);

            System.out.println("Connected to SQL Server successfully!");

        } catch (Exception e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
        }

        return conn;
    }
}