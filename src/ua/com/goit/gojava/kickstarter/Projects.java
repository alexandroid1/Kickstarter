package ua.com.goit.gojava.kickstarter;

/**
 * Created by alex on 26.12.15.
 */
public class Projects {

    private static Project[] projects = new Project[100];
    private static int count = 0;

    public static void add(Project project) {
        projects[count] = project;
        count++;
    }

    public Project[] getProjects(Category category) {
        Project[] result = new Project[100];
        int found = 0;
        for (int index = 0; index< count; index++){
            Project project = projects[index];
            if (category.equals(project.getCategory())){
                result[found] = project;
                found++;
            }
        }
        Project[] result2 = new Project[found];
        System.arraycopy(result, 0, result2, 0, found);

        return result2;
    }

    public Project get(int index) {
        return projects[index];
    }
}
