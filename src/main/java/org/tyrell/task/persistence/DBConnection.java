package org.tyrell.task.persistence;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DBConnection {

    private final static String url = "jdbc:h2:mem:task;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE";
    private final static String username = "root";
    private final static String password = "root";
    private static BasicDataSource connectionPool = null;

    private DBConnection() {
    }

    public static BasicDataSource getInstance() {
        if (connectionPool == null) {
            connectionPool = new BasicDataSource();
            connectionPool.setUrl(url);
            connectionPool.setUsername(username);
            connectionPool.setPassword(password);
            connectionPool.setInitialSize(1);
            connectionPool.setMinIdle(1);
            connectionPool.setMaxIdle(3);
            connectionPool.setMaxTotal(9);
        }

        return connectionPool;
    }

    public static Connection getConnection() throws SQLException {
        return getInstance().getConnection();
    }

}
