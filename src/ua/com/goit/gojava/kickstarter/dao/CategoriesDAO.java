package ua.com.goit.gojava.kickstarter.dao;

import ua.com.goit.gojava.kickstarter.Categories;
import ua.com.goit.gojava.kickstarter.Category;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by alex on 27.01.16.
 */
public class CategoriesDAO implements Categories {

    private /*static*/ Connection connection;

    public CategoriesDAO(Connection connection) {
        this.connection = connection;
    }

 /*   static {
        // load the sqlite-JDBC driver using the current class loader
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("something wrong with downloading drivers: ", e);
        }
    }

    // for testing
    public static void main(String[] args) {

        FileInputStream fis;
        Properties properties = new Properties();

        try {
            fis = new FileInputStream("./resources/application.properties");
            properties.load(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(properties.getProperty("jdbc.url"), properties);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        CategoriesDAO categoriesDAO = new CategoriesDAO(connection);

        Category category = categoriesDAO.get(1);
        categoriesDAO.add(new Category("CategoryName3"));
        List<Category> list = categoriesDAO.getCategories();


        System.out.println("category.toString() = " + category.toString());
        System.out.println("categoriesDAO.size() = " + categoriesDAO.size());
        System.out.println("list.toString() =  " + list.toString());
    }*/

    @Override
    public void add(final Category category) {
            String insertTableSQL = "INSERT INTO Categories"
                    + "(name) VALUES"
                    + "(?)";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(insertTableSQL);
            preparedStatement.setString(1, category.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("something wrong with getting connection: ", e);
        }

    }

    @Override
    public List<Category> getCategories() {
        try {
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            List<Category> result = new LinkedList<Category>();

            ResultSet rs = statement
                    .executeQuery("select * from Categories");
            while (rs.next()) {
              result.add(new Category(rs.getInt("id"), rs.getString("name")));
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException("something wrong with getting connection: ", e);
        }
    }

    @Override
    public Category get(int index) {
        try {
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            ResultSet rs = statement
                    .executeQuery("select * from Categories WHERE id-1 = " + index);
            while (rs.next()) {
                return new Category(rs.getInt("id"), rs.getString("name"));
            }

            throw new RuntimeException("Category not found");
        } catch (SQLException e) {
            throw new RuntimeException("something wrong with getting connection: ", e);
        }
    }

    @Override
    public int size() {
        try {
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            ResultSet rs = statement.executeQuery("select COUNT(*) FROM Categories");
            return  rs.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException("something wrong with getting connection: ", e);
        }
    }

    @Override
    public boolean exists(final int id) {
        try {
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            ResultSet rs = statement.executeQuery("select id FROM Categories WHERE id = " + id);
            return  rs.next();
        } catch (SQLException e) {
            throw new RuntimeException("something wrong with getting connection: ", e);
        }
    }

}
