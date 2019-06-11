package com.gmail.lesinski.piotr.dao;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {

    private static final Logger logger = Logger.getLogger(DbUtil.class);


    public static Connection getConnection() throws SQLException {

        Connection conn;

        try {

            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Szkola_programowania?useSSL=false&serverTimezone=UTC",
                    "root", "coderslab");

            return conn;

        } catch (SQLException | ClassNotFoundException e) {

            logger.error("Błąd połączenia z bazą danych", e);

            throw new RuntimeException("Błąd połączenia z bazą danych", e);

        }

    }
}
