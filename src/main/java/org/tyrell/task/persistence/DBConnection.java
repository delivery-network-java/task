package org.tyrell.task.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private final static String url = "jdbc:h2:mem:task;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE";
    private final static String username = "root";
    private final static String password = "root";
    private static Connection connection = null;

    public static Connection getInstance() throws SQLException {
        if (connection == null) {
            connection = DriverManager.getConnection(url, username, password);
        }

        return connection;
    }

}
