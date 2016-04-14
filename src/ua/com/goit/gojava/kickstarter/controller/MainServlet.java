package ua.com.goit.gojava.kickstarter.controller;

import ua.com.goit.gojava.kickstarter.Category;
import ua.com.goit.gojava.kickstarter.dao.CategoriesDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by alex on 10.02.16.
 */
public class MainServlet extends HttpServlet {

    static {
        // load the sqlite-JDBC driver using the current class loader
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("something wrong with downloading drivers: ", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = getAction(req); //   /categories
        Connection connection = getConnection(req);

        if (action.startsWith("/categories")){

            CategoriesDAO categoriesDAO = new CategoriesDAO(connection);
            List<Category> categories = categoriesDAO.getCategories();

            req.setAttribute("categories", categories);
            req.getRequestDispatcher("categories.jsp").forward(req, resp);

        } else if (action.equals("/projects")) {

        }
    }

    private String getAction(HttpServletRequest req) {
        String requestURI = req.getRequestURI();
        return requestURI.substring(req.getContextPath().length(), requestURI.length());
    }

    private Connection getConnection(HttpServletRequest req) {
        Connection result = (Connection) req.getSession().getAttribute("connection");
        if (result == null){
            try {
                result = DriverManager.getConnection("jdbc:sqlite:D:\\java\\projects\\Kickstarter\\resources\\database.db");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            req.getSession().setAttribute("connection",result);
        }
        return result;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(req.getParameterMap().toString());
    }
}
