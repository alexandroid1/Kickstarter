package ua.com.goit.gojava.kickstarter;

import org.junit.After;
import ua.com.goit.gojava.kickstarter.dao.CategoriesDAO;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * Created by alex
 */
public class CategoriesDAOTest extends CategoriesTest {

    private Connection connection;

    @Override
    Categories getCategories() throws SQLException {
        Properties properties = new Properties();
        properties.put("jdbc.url","jdbc:sqlite:D:\\java\\projects\\Kickstarter\\resources\\test-database.db");

        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        connection = DriverManager.getConnection(properties.getProperty("jdbc.url"), properties);


        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.setQueryTimeout(30);
            statement
                    .execute("CREATE TABLE Categories (" +
                            "id INTEGER  NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE," +
                            "name TEXT NOT NULL UNIQUE" +
                            ");");
        } catch (SQLException e) {
            throw new RuntimeException("something wrong with getting connection: ", e);
        }

        return new CategoriesDAO(connection);
    }

    @After
    public void cleanUp() throws SQLException {
        connection.close();
        new File("D:\\java\\projects\\Kickstarter\\resources\\test-database.db").delete();
    }

}
