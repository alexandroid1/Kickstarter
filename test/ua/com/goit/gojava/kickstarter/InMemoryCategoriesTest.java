package ua.com.goit.gojava.kickstarter;

/**
 * Created by alex on 21.01.16.
 */
public class InMemoryCategoriesTest extends CategoriesTest {

    @Override
    Categories getCategories() {
        return new InMemoryCategories();
    }
}
