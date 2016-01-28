package ua.com.goit.gojava.kickstarter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by alex on 27.01.16.
 */
public class CategoriesDAO implements Categories {

    static {
        // load the sqlite-JDBC driver using the current class loader
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("something wrong with downloading drivers ", e);
        }
    }

    private Connection connection;

    @Override
    public void add(Category category) {

    }

    @Override
    public String[] getCategories() {
        return new String[0];
    }

    public static void main(String[] args){
        Category category = new CategoriesDAO().get(1);
        System.out.println(category.toString());
    }


    @Override
    public Category get(int index) {
        Category category = null;

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:./resources/database.db");

            // create a database connection
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

            ResultSet rs = statement.executeQuery("select * from Categories WHERE id = " + index);
            while(rs.next())
            {
                category = new Category(rs.getInt("id"), rs.getString("name"));
            }

        } catch (SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e);
            }
        }
        return category;
    }

    @Override
    public int size() {
        return 0;
    }
}
