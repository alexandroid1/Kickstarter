package ua.com.goit.gojava.kickstarter;

/**
 * Created by alex on 21.01.16.
 */
public interface Categories {
    void add(Category category);

    String[] getCategories();

    Category get(int index);

    int size();
}
