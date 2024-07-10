package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    static String url = "jdbc:mysql://localhost:3306";
    static String user = "root";
    static String password = "root";

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        return DriverManager.getConnection(url, user, password);
    }
}

