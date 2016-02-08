package ua.com.goit.gojava.kickstarter;

import org.junit.After;
import ua.com.goit.gojava.kickstarter.dao.ConnectionPool;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

/**
 * Created by alex
 */
public class CategoriesDAOTest extends CategoriesTest{

    private ConnectionPool connections;

    @Override
    Categories getCategories() {
        Properties properties = new Properties();
        properties.put("jdbc.driverClassName","org.sqlite.JDBC");
        properties.put("jdbc.url","jdbc:sqlite:./bin/test-database.db");
        properties.put("user","");
        properties.put("password","");

        connections = new ConnectionPool(properties);

        return connections.get(connection -> {

            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            statement
                    .executeQuery("CREATE TABLE Categories (" +
                            "id INTEGER  NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE," +
                            "name TEXT NOT NULL UNIQUE" +
                            ")");
            return null;
        });
    }

    @After
    public void cleanUp(){
        connections.close();
        new File("./bin/test-database.db").delete();
    }


}
