package ua.com.goit.gojava.kickstarter.dao;

import ua.com.goit.gojava.kickstarter.Categories;
import ua.com.goit.gojava.kickstarter.Category;

import java.sql.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

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

    // for testing
    public static void main(String[] args) {
        CategoriesDAO categoriesDAO = new CategoriesDAO();

        Category category = categoriesDAO.get(1);

        categoriesDAO.add(new Category("CategoryName3"));

        String[] list = categoriesDAO.getCategories();



        System.out.println("category.toString() = " + category.toString());

        System.out.println("categoriesDAO.size() = " + categoriesDAO.size());

        System.out.println("list.toString() =  " + Arrays.toString(list));
    }


    @Override
    public void add(Category category) {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:./resources/database.db");

            String insertTableSQL = "INSERT INTO Categories"
                    + "(id, name) VALUES"
                    + "(?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertTableSQL);
            preparedStatement.setInt(1, 3);
            preparedStatement.setString(2, category.getName());

            // execute insert SQL stetement
            preparedStatement .executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("add(Category category) failed: ", e);
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
    }

    @Override
    public String[] getCategories() {
        List<String> result = new LinkedList<>();
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:./resources/database.db");

            // create a database connection
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

            ResultSet rs = statement
                    .executeQuery("select * from Categories");

            while (rs.next()) {
                result.add(String.valueOf(rs.getInt("id")) + " " + rs.getString("name"));
            }
            return result.toArray(new String[result.size()]);

        } catch (SQLException e) {
            throw new RuntimeException("getInt(id) failed: ", e);
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
    }



    @Override
    public Category get(int index) {
        Category category = null;

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:./resources/database.db");

            // create a database connection
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

            ResultSet rs = statement
                    .executeQuery("select * from Categories WHERE id = " + index);

            while (rs.next()) {
                category = new Category(rs.getInt("id"), rs.getString("name"));
            }

        } catch (SQLException e) {
            throw new RuntimeException("getInt(id) failed: ", e);
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

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:./resources/database.db");

            // create a database connection
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

            ResultSet rs = statement.executeQuery("select COUNT(*) AS total FROM Categories");

            if (rs != null) {
                return  rs.getInt("total");  // better impl
            }

        } catch (SQLException e) {
            throw new RuntimeException("getInt(id) failed: ", e);
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
        throw new RuntimeException("Some Error");
    }
}
