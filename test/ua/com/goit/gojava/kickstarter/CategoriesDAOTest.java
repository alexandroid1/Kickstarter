package ua.com.goit.gojava.kickstarter;

import org.junit.After;
import ua.com.goit.gojava.kickstarter.Categories;
import ua.com.goit.gojava.kickstarter.CategoriesTest;
import ua.com.goit.gojava.kickstarter.dao.CategoriesDAO;
import ua.com.goit.gojava.kickstarter.dao.ConnectionPool;
import ua.com.goit.gojava.kickstarter.dao.ConnectionRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * Created by alex
 */
public class CategoriesDAOTest extends CategoriesTest {

    private ConnectionPool connections;

    @Override
    Categories getCategories() {
        Properties properties = new Properties();
        properties.put("jdbc.driverClassName","org.sqlite.JDBC");
        properties.put("jdbc.url","jdbc:sqlite:./bin/test-database.db");

        connections = new ConnectionPool(properties);
        ConnectionPool connections = new ConnectionPool(properties);
        CategoriesDAO categoriesDAO = new CategoriesDAO(connections);

        connections.get(new ConnectionRunner<Void>() {
            public Void run(Connection connection) throws SQLException {
                Statement statement = connection.createStatement();
                statement.setQueryTimeout(30);
                statement
                        .execute("CREATE TABLE Categories (" +
                                "id INTEGER  NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE," +
                                "name TEXT NOT NULL UNIQUE" +
                                ");");
                return null;
            }
        });

        return new CategoriesDAO(connections);
    }

    @After
    public void cleanUp(){
        connections.close();
        new File("./bin/test-database.db").delete();
    }

}
