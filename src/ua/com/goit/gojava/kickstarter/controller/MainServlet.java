package ua.com.goit.gojava.kickstarter.controller;

import ua.com.goit.gojava.kickstarter.dao.CategoriesDAO;
import ua.com.goit.gojava.kickstarter.dao.ConnectionPool;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Map;
import java.util.Properties;

/**
 * Created by alex on 10.02.16.
 */
public class MainServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String requestURI = req.getRequestURI();
        String action = requestURI.substring(req.getContextPath().length(), requestURI.length());
        System.out.println(action);

        if (action.equals("/categories")){

            ConnectionPool connections = getConnections(req);
            CategoriesDAO categoriesDAO = new CategoriesDAO(connections);

        } else if (action.equals("/projects")){

        }

        super.doGet(req, resp);
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
                e.printStackTrace();
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
