package ua.com.goit.gojava.kickstarter;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by alex on 25.12.15.
 */
public class Kickstarter{
    private Projects projects;
    private Categories categories;


    public Kickstarter(Categories categories, Projects projects) {
        this.categories = categories;
        this.projects = projects;
    }

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

    private void run() {
        QuoteGenerator generator = new QuoteGenerator();
        System.out.println(generator.nextQuote());

        System.out.println();
        System.out.println("Choose category:");
        System.out.println(Arrays.toString(categories.getCategories()));

        Scanner scanner = new Scanner(System.in);
        int categoryIndex = scanner.nextInt();
        Category category = categories.getName(categoryIndex);
        System.out.println("You chosen category:" + category.getName());

        Project[] foundProjects = this.projects.getProjects(category);

        System.out.println("----------------");

        for (Project project : foundProjects){
            System.out.println(project.getName());
            System.out.println(project.getDescription());
            System.out.println("Need money : " + project.getAmount());
            System.out.println("Already have money: " + project.getExist());
            System.out.println("Days to get money : " + project.getDays());
            System.out.println("----------------");
        }
    }
}
