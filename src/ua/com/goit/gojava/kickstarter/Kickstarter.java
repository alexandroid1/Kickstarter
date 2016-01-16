package ua.com.goit.gojava.kickstarter;

import java.util.Arrays;

/**
 * Created by alex on 25.12.15.
 */
public class Kickstarter {
    private IO io;
    private Projects projects;
    private Categories categories;
    private QuoteGenerator generator;

    public Kickstarter(Categories categories, Projects projects, IO io, QuoteGenerator generator) {
        this.categories = categories;
        this.projects = projects;
        this.io = io;
        this.generator = generator;
    }

    public void run() {

        println(generator.nextQuote());

        categoryMenu();
        println("Thanks for using Kickstarter");
    }

    private void categoryMenu() {
        while (true) {
            askCategories();
            int menu = io.read();
            if (menu == 0) {
                break;
            }
            Category category = chooseCategory(menu);
            if (category == null){
                continue;
            }

            Project[] found = projects.getProjects(category);
            printProjects(found);

            projectsMenu(found);
        }
    }

    private void projectsMenu(Project[] found) {
        while (true){
            askProjects(found);
            int menu = io.read();
            if (menu == 0) {
                break;
            }
            Project project = chooseProject(menu, found);
            if (project == null){
                continue;
            }
            chooseProject(project);
            printProjectDetails(project);

            projectMenu(project);
        }
    }

    private void projectMenu(Project project) {
        while (true){
            askProject(project);
            int menu = io.read();
            if (menu == 0) {
                break;
            }

            if (menu == 1) {
                println("Thank you for what you want to invest in the project");
            }
        }
    }

    private void askProject(Project project) {
        println("Choose action: \n" +
                "0 - List of projects; " +
                "1 - Invest in the project");
    }

    private Project chooseProject(int menu, Project[] found) {
        if (menu <= 0 || found.length < menu){
            println("Error index menu " + menu);
            return null;
        }
        return found[menu -1];
    }

    private void println(String message) {
        io.print(message + "\n");
    }

    private void askProjects(Project[] found) {
        if (found.length == 0){
            println("No any projects in cathegory. Press 0 - for exit ");
        } else {
            int from = 1;
            int to = found.length;
            println("Choose project: [" + from + " ... " + to + "] or 0 for exit ");
        }
    }

    private void printProjectDetails(Project project) {
        println("project: ");
        println(project.getHistory());
        println(project.getDemoVideo());
        String questionAnswers = project.getQuestionAnswers();
        if ( questionAnswers != null ){
            println(questionAnswers);
        }
        println("----------------");

    }

    private void chooseProject(Project project) {
        println("You chosen project:" + project.getName());
        println("----------------");
    }

    private void printProjects(Project[] found) {
        for (int index = 0; index < found.length; index++) {
            Project project = found[index];
            io.print((index + 1) + " - ");
            printProjects(project);
        }
    }

    private void printProjects(Project project) {
        println(project.getName());
        println(project.getDescription());
        println("Need money : " + project.getAmount());
        println("Already have money: " + project.getExist() + "$");
        println("Days to get money : " + project.getDays());
        println("----------------");
    }

    private void askCategories() {
        println("Choose category or 0 for Exit:");
        println(Arrays.toString(categories.getCategories()));
    }


    private Category chooseCategory(int menu) {
        if ( menu <= 0 || categories.size()  < menu){
            println("Error index menu " + (menu - 1));
            return null;
        }

        Category category = categories.get(menu-1);
        println("You chosen category:" + category.getName());
        println("----------------");
        return category;
    }

}
