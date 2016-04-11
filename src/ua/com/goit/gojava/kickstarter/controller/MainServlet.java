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
import java.util.Map;

/**
 * Created by alex on 10.02.16.
 */
public class MainServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //System.out.println(req.getRequestURI());  //   /sample/categories

        String requestURI = req.getRequestURI();
        String action = req.getRequestURI().substring(req.getContextPath().length(), requestURI.length()); //  /categories

        System.out.println(action);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(req.getParameterMap().toString());
    }
}
