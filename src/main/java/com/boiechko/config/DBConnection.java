package com.boiechko.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private final static String url = "jdbc:mysql://localhost/boutique_servlets?serverTimezone=UTC";
    private final static String username = "vldmr";
    private final static String password = "17032001";

    private static Connection connection = null;

    public static synchronized Connection getConnection() throws SQLException{

        if (connection == null) {

            connection = initConnection();

        }

        return connection;
    }

    private static Connection initConnection() throws SQLException {

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException exception) {
            exception.printStackTrace();
        }

        return DriverManager.getConnection(url, username, password);

    }
}