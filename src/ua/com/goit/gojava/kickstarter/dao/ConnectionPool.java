package ua.com.goit.gojava.kickstarter.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by alex
 */
public class ConnectionPool {

    static {
        // load the sqlite-JDBC driver using the current class loader
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("something wrong with downloading drivers: ", e);
        }
    }

    private Connection connection;

    public ConnectionPool(Properties properties){
        try {
           connection = DriverManager.getConnection(properties.getProperty("jdbc.url"), properties);
        } catch (SQLException e) {
            throw new RuntimeException("something wrong with getting connection: ", e);
        }
    }

    public <T> T get(ConnectionRunner<T> runnable) {
        try {
            return runnable.run(connection);
        } catch (SQLException e) {
            throw new RuntimeException("during Execution the Query has appeared error: ", e);
        }
    }

    public void close(){
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException("something wrong during closing connection: ", e);
        }
    }
}
