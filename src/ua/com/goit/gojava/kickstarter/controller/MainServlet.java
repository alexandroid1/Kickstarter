package ua.com.goit.gojava.kickstarter.controller;

import ua.com.goit.gojava.kickstarter.Category;
import ua.com.goit.gojava.kickstarter.dao.CategoriesDAO;
import ua.com.goit.gojava.kickstarter.dao.ConnectionPool;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

/**
 * Created by alex on 10.02.16.
 */
public class MainServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = getAction(req);
        ConnectionPool connections = getConnections(req);

        if (action.startsWith("/categories")) {

            CategoriesDAO categoriesDAO = new CategoriesDAO(connections);
            List<Category> categories = categoriesDAO.getCategories();

            resp.getOutputStream().println(categories.toString());
        } else if (action.equals("/projects")){

        }
        super.doGet(req, resp);
    }

    private String getAction(HttpServletRequest req) {
        String requestURI = req.getRequestURI();
        return requestURI.substring(req.getContextPath().length(), requestURI.length());
    }

    private ConnectionPool getConnections(HttpServletRequest req) {
        ConnectionPool result = (ConnectionPool) req.getSession().getAttribute("connections");
        if (result == null) {

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
                result = (ConnectionPool) DriverManager.getConnection(properties.getProperty("jdbc.url"), properties);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            req.getSession().setAttribute("connections",result);
        }
        return result;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(req.getParameterMap().toString());
        super.doPost(req, resp);
    }
}
