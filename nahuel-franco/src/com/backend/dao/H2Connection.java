package com.backend.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

public class H2Connection{
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("org.h2.Driver");
        return DriverManager.getConnection("jdbc:h2:~/c1clase11", "sa", "sa");
    }
}
