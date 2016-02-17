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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = getAction(req);
        Connection connection = getConnection(req);

        if (action.startsWith("/categories")) {

            CategoriesDAO categoriesDAO = new CategoriesDAO(connection);
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

    private Connection getConnection(HttpServletRequest req) {


 /*       Context envContext  = null;
        try {
            Context initContext = new InitialContext();
            envContext = (Context) initContext.lookup("java:/comp/env");
            DataSource ds = (DataSource) envContext.lookup("jdbc/myDS");
        } catch (NamingException e) {
            e.printStackTrace();
        }*/


        Connection result = (Connection) req.getSession().getAttribute("connection");
        if (result == null) {

/*            FileInputStream fis;
            Properties properties = new Properties();

            try {
                fis = new FileInputStream("./resources/application.properties");
                properties.load(fis);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }*/

            try {
                result = DriverManager.getConnection(
                        "jdbc:sqlite:\\home\\alex\\Документы\\GoIT\\Kickstarter\\resources\\database.db");

                /*result = DriverManager.getConnection(
                        "jdbc:sqlite:resources/database.db");*/

/*                result = DriverManager.getConnection(
                        "jdbc:sqlite:/home/alex/Документы/GoIT/Kickstarter/resources/database.db");*/
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            req.getSession().setAttribute("connection", result);
        }
        return result;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        System.out.println(req.getParameterMap().toString());
        super.doPost(req, resp);
    }
}
