import ua.com.goit.gojava.kickstarter.*;
import ua.com.goit.gojava.kickstarter.dao.CategoriesDAO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Random;

/**
 * Created by alex on 28.12.15.
 */
public class KickstarterRunner {
    public static void main(String[] args) throws SQLException {

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

        Connection connection = null;

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(properties.getProperty("jdbc.url"), properties);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            statement.execute("DELETE FROM Categories");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Category category1 = new Category("Photo");
        Category category2 = new Category("Video");
        Category category3 = new Category("Music");

        //Categories categories = new InMemoryCategories(); // for in memory categories
        CategoriesDAO categories = new CategoriesDAO(connection); // for DB categories

        categories.add(category1); // for in memory categories
        categories.add(category2);
        categories.add(category3);

        Project project1 = new Project("film about java EE code",
                100000,
                15,
                "https://www.youtube.com/watch?v=YVhS1axhzRs",
                "How to do right code");

        Project project2 = new Project("film GoJava7",
                2345,
                10,
                "https://www.youtube.com/watch?v=HOndhtfIXSY",
                "Learning to write clean code with GoIT");

        project1.setCategory(category2);
        project2.setCategory(category2);

        Projects projects = new Projects();
        Projects.add(project1);
        Projects.add(project2);

        Kickstarter application = new Kickstarter(categories,
                projects,
                new ValidatorDecoratorIO(new ConsoleIO()),
                // new LoggerDecoratorIO(new ConsoleIO()),
                new QuoteGenerator(new Random()));

        project1.setHistory("History of first project - java code history ...");
        project2.setHistory("History of second project - GoIT history ...");

        project1.addQuestionAnswer("Q: What is the duration of the movie?" + "\n" +
                "A: 2 hours");

        application.run();

        connection.close();
    }
}
