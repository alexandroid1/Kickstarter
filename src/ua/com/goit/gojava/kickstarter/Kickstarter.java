package ua.com.goit.gojava.kickstarter;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by alex on 25.12.15.
 */
public class Kickstarter {
    private Projects projects;
    private Categories categories;

    public Kickstarter(Categories categories, Projects projects) {
        this.categories = categories;
        this.projects = projects;
    }

    public void run() {
        QuoteGenerator generator = new QuoteGenerator();
        System.out.println(generator.nextQuote());

        while (true) {
            askCategory();
            int categoryIndex = selectMenu();
            Category category = chooseCategory(categoryIndex);

            printProjects(category);

            while (true){
                askProject();
                int projectIndex = selectMenu();
                Project project = chooseProject(projectIndex);

                printProjectDetails(project);

            }
        }
    }

    private void askProject() {
        System.out.println();
        System.out.println("Choose project:");
       // System.out.println(Arrays.toString(categories.getCategories()));
    }

    private void printProjectDetails(Project project) {
        System.out.println("project: ");
        System.out.println(project.getHistory());
        System.out.println(project.getDemoVideo());
        System.out.println(project.getQuestionAnswers());

    }

    private Project chooseProject(int projectIndex) {
        Project project = projects.get(projectIndex);
        System.out.println("You chosen project:" + project.getName());
        System.out.println("----------------");
        return project;
    }

    private void printProjects(Category category) {
        Project[] foundProjects = projects.getProjects(category);
        for (int index =0; index<foundProjects.length; index++) {
            Project project = foundProjects[index];
            System.out.print(index + " - ");
            printProjects(project);
        }
    }

    private void printProjects(Project project) {
        System.out.println(project.getName());
        System.out.println(project.getDescription());
        System.out.println("Need money : " + project.getAmount());
        System.out.println("Already have money: " + project.getExist() + "$");
        System.out.println("Days to get money : " + project.getDays());
        System.out.println("----------------");
    }

    private void askCategory() {
        System.out.println();
        System.out.println("Choose category:");
        System.out.println(Arrays.toString(categories.getCategories()));
    }

    private Category chooseCategory(int categoryIndex) {
        Category category = categories.get(categoryIndex);
        System.out.println("You chosen category:" + category.getName());
        System.out.println("----------------");
        return category;
    }

    private int selectMenu() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
}
