package Controller;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Database {
    private static Connection con;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost/employee", "root", "");
        return con;
    }

    public static Connection getcon() {
        return con;
    }
}


