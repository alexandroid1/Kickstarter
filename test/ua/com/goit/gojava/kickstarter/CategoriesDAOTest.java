package ua.com.goit.gojava.kickstarter;

import org.junit.After;
import org.junit.Before;
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

    private static ConnectionPool connections;

    static {
        Properties properties = new Properties();
        properties.put("jdbc.driverClassName","org.sqlite.JDBC");
        properties.put("jdbc.url","jdbc:sqlite:./bin/test-database.db");

        connections = new ConnectionPool(properties);
    }

    @Override
    Categories getCategories() {
        return new CategoriesDAO(connections);
    }

/*    @Before
    public void setup(){
        connections.get(new ConnectionRunner<Void>() {
            public Void run(Connection connection) throws SQLException {
                Statement statement = connection.createStatement();
                statement.setQueryTimeout(30);
                statement
                        .execute("DELETE FROM Categories");
                return null;
            }
        });
    }*/

    @After
    public void cleanUp(){

        connections.get(new ConnectionRunner<Void>() {
            public Void run(Connection connection) throws SQLException {
                Statement statement = connection.createStatement();
                statement.setQueryTimeout(30);
                statement
                        .execute("DELETE FROM Categories");
                return null;
            }
        });


        //connections.close();
        //new File("./bin/test-database.db").delete();


/*        public Void run(Connection connection) throws SQLException {
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            statement
                    .execute("CREATE TABLE Categories (" +
                            "id INTEGER  NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE," +
                            "name TEXT NOT NULL UNIQUE" +
                            ");");
            return null;
        }*/

    }

}
