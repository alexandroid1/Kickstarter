package ua.com.goit.gojava.kickstarter;

import java.util.Arrays;
import java.util.Random;
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
        QuoteGenerator generator = new QuoteGenerator(new Random());
        System.out.println(generator.nextQuote());

        while (true) {
            askCategory();
            int menu = selectMenu();
            Category category = chooseCategory(menu);
            if (category == null){
                continue;
            }

            Project[] found = projects.getProjects(category);
            printProjects(found);

            projectMenu(found);
        }
    }

    private void projectMenu(Project[] found) {
        while (true){
            askProject(found);
            int menu = selectMenu();
            if (menu == 0) {
                break;
            }
            Project project = chooseProject(menu, found);
            if (project == null){
                continue;
            }
            chooseProject(project);
            printProjectDetails(project);
        }
    }

    private Project chooseProject(int menu, Project[] found) {
        if (menu <= 0 || found.length < menu){
            System.out.println("Error index menu " + menu );
            return null;
        }
        return found[menu -1];
    }

    private void askProject(Project[] found) {
        if (found.length == 0){
            System.out.println("No any projects in cathegory. Press 0 - for exit ");
        } else {
            int from = 1;
            int to = found.length;
            System.out.println("Choose project: [" + from + " ... " + to + "] or 0 for exit " );
        }
    }

    private void printProjectDetails(Project project) {
        System.out.println("project: ");
        System.out.println(project.getHistory());
        System.out.println(project.getDemoVideo());
        String questionAnswers = project.getQuestionAnswers();
        if ( questionAnswers != null ){
            System.out.println(questionAnswers);
        }
        System.out.println("----------------");

    }

    private void chooseProject(Project project) {
        System.out.println("You chosen project:" + project.getName());
        System.out.println("----------------");
    }

    private void printProjects(Project[] found) {
        for (int index = 0; index < found.length; index++) {
            Project project = found[index];
            System.out.print( (index + 1 ) + " - ");
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
        System.out.println("Choose category:");
        System.out.println(Arrays.toString(categories.getCategories()));
    }


    private Category chooseCategory(int menu) {
        if ( menu <= 0 || categories.size()  < menu){
            System.out.println("Error index menu " + (menu-1));
            return null;
        }

        Category category = categories.get(menu-1);
        System.out.println("You chosen category:" + category.getName());
        System.out.println("----------------");
        return category;
    }

    private int selectMenu() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
}
