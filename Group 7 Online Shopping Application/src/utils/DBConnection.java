package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/onlineshop?useSSL=false&allowPublicKeyRetrieval=true";
    private static final String USER = "root";     // XAMPP default
    private static final String PASSWORD = "";     // empty by default in XAMPP

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
