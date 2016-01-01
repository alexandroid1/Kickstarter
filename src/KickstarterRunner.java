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
                    100000, 15,
                    "How to do right code");

            Project project2 = new Project("film GoJava7",
                    2345, 10,
                    "Learning to write clean code with GoIT");

            project1.setCategory(category2);
            project2.setCategory(category2);

            Projects projects = new Projects();
            Projects.add(project1);
            Projects.add(project2);

            Kickstarter application = new Kickstarter(categories, projects);
            application.run();
        }
}
