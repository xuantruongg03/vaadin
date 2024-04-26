package com.example.application.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.vaadin.flow.component.notification.Notification;

public class ManagerAdmin {
    public ManagerAdmin() {
    }
    /**
     * Checks if the provided username and password match an entry in the admins table.
     *
     * @param username the username to check
     * @param password the password to check
     * @return true if the username and password match an entry in the admins table, false otherwise
     */
    public static boolean checkLogin(String username, String password) {
        try (Connection conn = ConnectDB.getConnection()) {
            PreparedStatement statement = conn
                    .prepareStatement("SELECT * FROM admins WHERE username = ? AND password = ?");
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return true;
            }
            return false;
        } catch (SQLException ex) {
            Notification.show("Connection failed: " + ex.getMessage());
            return false;
        }
    }
}
