package com.epam.jmp.springmvc.persontask.pool;

import com.epam.jmp.springmvc.persontask.exceptions.ConnectionPoolException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.LinkedBlockingQueue;
import javax.sql.DataSource;

public class ConnectionPool {

    private static ConnectionPool instance;
    private static final int CONNECTION_COUNT = 5;
    private static LinkedBlockingQueue<Connection> resourcesAll;
    private static LinkedBlockingQueue<Connection> resourcesFree;
    private DataSource dataSource;

    private ConnectionPool() {
    }

    public void init() throws ConnectionPoolException {
        if (instance == null) {
            instance = new ConnectionPool();
            resourcesAll = new LinkedBlockingQueue<Connection>(CONNECTION_COUNT);
            resourcesFree = new LinkedBlockingQueue<Connection>(CONNECTION_COUNT);
            for (int i = 0; i < CONNECTION_COUNT; i++) {
                Connection connection;
                try {
                    connection = dataSource.getConnection();
                    resourcesAll.add(connection);
                    resourcesFree.add(connection);
                } catch (SQLException ex) {
                    throw new ConnectionPoolException(ex.getMessage());
                }
            }
        }
    }

    public static Connection getResource() throws ConnectionPoolException {
        Connection res;
        try {
            res = resourcesFree.take();
        } catch (InterruptedException ex) {
            throw new ConnectionPoolException(ex.getMessage());
        }
        return res;
    }

    public static void returnResource(Connection cn) throws ConnectionPoolException {
        try {
            resourcesFree.add(cn);
        } catch (Exception ex) {
            throw new ConnectionPoolException(ex.getMessage());
        }

    }

    public void closeAllConnections() throws ConnectionPoolException {
        for (int i = 0; i < CONNECTION_COUNT; i++) {
            Connection conn = resourcesFree.poll();
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    throw new ConnectionPoolException(ex.getMessage());
                }
            }
        }
        resourcesAll.clear();
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}