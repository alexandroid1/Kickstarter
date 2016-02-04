package ua.com.goit.gojava.kickstarter.dao;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by alex
 */
public interface ConnectionRunner<T> {
    T run (Connection connection) throws SQLException;
}
