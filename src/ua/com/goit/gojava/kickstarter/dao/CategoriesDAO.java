package ua.com.goit.gojava.kickstarter.dao;

import ua.com.goit.gojava.kickstarter.Categories;
import ua.com.goit.gojava.kickstarter.Category;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

/**
 * Created by alex on 27.01.16.
 */
public class CategoriesDAO implements Categories {

    private ConnectionPool connections;

    public CategoriesDAO(ConnectionPool connections) {
        this.connections = connections;
    }

    // for testing
    public static void main(String[] args) {

        FileInputStream fis;
        Properties property = new Properties();

        try {
            fis = new FileInputStream("./resources/application.properties");
            property.load(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ConnectionPool connections = new ConnectionPool(property);
        CategoriesDAO categoriesDAO = new CategoriesDAO(connections);

        Category category = categoriesDAO.get(1);
        categoriesDAO.add(new Category("CategoryName3"));
        List<Category> list = categoriesDAO.getCategories();


        System.out.println("category.toString() = " + category.toString());
        System.out.println("categoriesDAO.size() = " + categoriesDAO.size());
        System.out.println("list.toString() =  " + list.toString());
    }

    @Override
    public void add(final Category category) {
        connections.get(connection1 -> {
            String insertTableSQL = "INSERT INTO Categories"
                    + "(name) VALUES"
                    + "(?)";
            PreparedStatement preparedStatement = connection1.prepareStatement(insertTableSQL);
            preparedStatement.setString(1, category.getName());
            preparedStatement.executeUpdate();
            return null;
        });
    }

    @Override
    public List<Category> getCategories() {

        return connections.get(connection -> {
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

            List<Category> result = new LinkedList<>();

            ResultSet rs = statement
                    .executeQuery("select * from Categories");

            while (rs.next()) {
              result.add(new Category(rs.getInt("id"), rs.getString("name")));
            }
            return result;
        });
    }

    @Override
    public Category get(int index) {

        return connections.get(connection -> {

            Category category = null;

            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            ResultSet rs = statement
                    .executeQuery("select * from Categories WHERE id = " + index);

            while (rs.next()) {
                category = new Category(rs.getInt("id"), rs.getString("name"));
            }

            return category;
        });
    }

    @Override
    public int size() {

        return connections.get(connection -> {

            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            ResultSet rs = statement.executeQuery("select COUNT(*) AS total FROM Categories");

            if (rs != null) {
                return  rs.getInt("total");
            } else {
                throw new RuntimeException("during Execution the Query has appeared error: ");
            }
        });
    }

}