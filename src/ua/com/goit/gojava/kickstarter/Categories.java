package ua.com.goit.gojava.kickstarter;

import java.util.List;

/**
 * Created by alex on 21.01.16.
 */
public interface Categories {
    void add(Category category);

    List<Category> getCategories();

    Category get(int index);

    int size();
}
