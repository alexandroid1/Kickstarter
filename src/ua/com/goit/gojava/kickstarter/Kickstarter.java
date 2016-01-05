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

            if (category == null){
                continue;
            }

            Project[] foundProjects = projects.getProjects(category);
            printProjects(foundProjects);

            while (true){
                askProject(foundProjects);
                int projectIndex = selectMenu();

                if (projectIndex<0 || foundProjects.length <= projectIndex){
                    System.out.println("Error index menu " + projectIndex);
                    continue;
                }

                Project project = foundProjects[projectIndex];
                chooseProject(project);

                printProjectDetails(project);
            }
        }
    }

    private void askProject(Project[] foundProjects) {
        int from = 0;
        int to = foundProjects.length -1;
        System.out.println("Choose project: [" + from + " ... " + to + "]" );
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

    private void printProjects(Project[] foundProjects) {
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
        System.out.println("Choose category:");
        System.out.println(Arrays.toString(categories.getCategories()));
    }


    private Category chooseCategory(int categoryIndex) {

        if (categoryIndex<0 || categories.size() <= categoryIndex){
            System.out.println("Error index menu " + categoryIndex);
            return null;
        }

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
