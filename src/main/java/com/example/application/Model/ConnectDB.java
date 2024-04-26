package com.example.application.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This class provides a connection to the database.
 */
public class ConnectDB {
    private static final String URL = "jdbc:mysql://localhost:3306/bookings";
    private static final String USER = "root";
    private static final String PASSWORD = "lexuantruong2k3@";

    /**
     * Returns a connection to the database.
     *
     * @return a connection to the database
     * @throws SQLException if a database access error occurs
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
