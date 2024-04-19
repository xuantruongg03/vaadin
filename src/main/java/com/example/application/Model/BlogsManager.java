package com.example.application.Model;

import java.util.ArrayList;
import java.util.List;

import com.example.application.common.Function;
import com.vaadin.flow.component.notification.Notification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BlogsManager {
    public BlogsManager() {
    }

    public static void addBlog(Blog blog) {
        try (Connection conn = ConnectDB.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(
                    "INSERT INTO blogs (blog_id, title, content, des, image, user_id, status) VALUES (?, ?, ?, ?, ?, ?, ?)");
            statement.setString(1, Function.generaID());
            statement.setString(2, blog.getTitle());
            statement.setString(3, blog.getContent());
            statement.setString(4, blog.getDes());
            statement.setString(5, blog.getImage());
            statement.setString(6, "Dc1YNIjruYapAWabtTzWIZzNgvUvQm");
            statement.setInt(7, 1);
            statement.executeUpdate();
        } catch (SQLException ex) {
            Notification.show("Connection failed: " + ex.getMessage());
        }
    }

    public static List<Blog> getBlogs() {
        List<Blog> blogs = new ArrayList<>();
        try (Connection conn = ConnectDB.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(
                    "SELECT blog_id, title, content, des, image, name, blogs.status as status, blogs.updated_at \r\n" + //
                            " FROM blogs inner join users on users.user_id = blogs.user_id ORDER BY blogs.updated_at DESC");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Blog blog = new Blog(rs.getString("blog_id"), rs.getString("title"), rs.getString("content"),
                        rs.getString("des"), rs.getString("image"), rs.getInt("status"), rs.getString("name"),
                        rs.getString("updated_at"));
                blogs.add(blog);
            }
        } catch (SQLException ex) {
            Notification.show("Connection failed: " + ex.getMessage());
        }
        return blogs;
    }

    public static void removeBlog (String b) {
        try (Connection conn = ConnectDB.getConnection()) {
            PreparedStatement statement = conn.prepareStatement("DELETE FROM blogs WHERE blog_id = ?");
            statement.setString(1, b);
            statement.executeUpdate();
        } catch (SQLException ex) {
            Notification.show("Connection failed: " + ex.getMessage());
        }
    }
}
