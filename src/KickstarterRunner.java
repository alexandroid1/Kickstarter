import ua.com.goit.gojava.kickstarter.*;

/**
 * Created by alex on 28.12.15.
 */
public class KickstarterRunner {
        public static void main(String[] args) {
            Category category1 = new Category("Photo");
            Category category2 = new Category("Video");
            Category category3 = new Category("Music");

            Categories categories = new Categories();
            categories.add(category1);
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

            Kickstarter application = new Kickstarter(categories, projects);

            project1.setHistory("History of first project - java code history ...");
            project2.setHistory("History of second project - GoIT history ...");

            project1.setQuestionAnswers("Q: What is the duration of the movie?" +"\n"+
                    "A: 2 hours");


            application.run();
        }
}
