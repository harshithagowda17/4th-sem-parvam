import java.sql.*;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Properties;

/**
 * 🚩 DAY 5: THE FINAL VERIFICATION
 * Run this code to see if your VS Code can talk to XAMPP.
 */
public class ConnectionTest {
    public static void main(String[] args) {
        // XAMPP Default Info
        String url = "jdbc:mysql://localhost:3306/";
        String user = "root";
        String pass = "";

        // Hardcoded path to MySQL Connector JAR
        String jarPath = "C:\\Users\\CEC52\\Desktop\\cec gagana\\Day5\\mysql-connector-j-9.7.0\\mysql-connector-j-9.7.0.jar";

        System.out.println("Testing Connection...");

        try {
            
            // Load the JAR dynamically
            URL jarUrl = new URL("file:///" + jarPath.replace("\\", "/"));
            URLClassLoader classLoader = new URLClassLoader(new URL[]{jarUrl}, ClassLoader.getSystemClassLoader());
            
            // Set the context classloader so DriverManager can find the driver
            Thread.currentThread().setContextClassLoader(classLoader);
            
            // Load MySQL JDBC Driver
            Class<?> driverClass = classLoader.loadClass("com.mysql.cj.jdbc.Driver");
            Driver driver = (Driver) driverClass.getDeclaredConstructor().newInstance();
            
            // Connect directly using the driver
            Properties props = new Properties();
            props.setProperty("user", user);
            props.setProperty("password", pass);
            
            try (Connection conn = driver.connect(url, props)) {
                
                System.out.println("==========================================");
                System.out.println("SUCCESS: Java is talking to XAMPP MySQL!");
                System.out.println("==========================================");
                
                // Show some database info
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("Database Product: " + meta.getDatabaseProductName());
                System.out.println("Database Version: " + meta.getDatabaseProductVersion());
                System.out.println("==========================================");

            }
        } catch (SQLException e) {
            System.out.println("==========================================");
            System.out.println("CONNECTION FAILED!");
            System.out.println("Error: " + e.getMessage());
            System.out.println("------------------------------------------");
            System.out.println("CHECKLIST:");
            System.out.println("1. Is XAMPP MySQL 'Start' button clicked (Green)?");
            System.out.println("2. JAR path: " + jarPath);
            System.out.println("==========================================");
        } catch (Exception e) {
            System.out.println("==========================================");
            System.out.println("DRIVER LOADING FAILED!");
            System.out.println("Error: " + e.getMessage());
            System.out.println("Make sure the JAR file exists at: " + jarPath);
            System.out.println("==========================================");
        }
    }
}