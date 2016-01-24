package ua.com.goit.gojava.kickstarter;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by alex on 10.01.16.
 */
public abstract class CategoriesTest {

    private Categories list;

    @Before
    public void setup() {
        list = getCategories();
    }

    abstract Categories getCategories();

    @Test
    public void shouldCategoriesList_whenAddCategories() {
        list.add(new Category("name1"));
        list.add(new Category("name2"));

        String[] categories = list.getCategories();
        assertEquals("[1 name1, 2 name2]",
                Arrays.toString(categories));
    }

    @Test
    public void shouldCategoriesList_whenNoCategories() {
        String[] categories = list.getCategories();
        assertEquals("[]",
                Arrays.toString(categories));
    }

    @Test
    public void shouldGetCategoryByIndex_whenAddCategories() {
        Category category1 = new Category("name1");
        list.add(category1);

        Category category2 = new Category("name2");
        list.add(category2);

        assertEquals(category1, list.get(0));
        assertEquals(category2, list.get(1));
    }

    @Test
    public void shouldCategoriesListSize_whenNoCategories() {
        assertEquals(0, list.size());
    }

    @Test
    public void shouldCategoriesListSize_whenAddCategories() {
        list.add(new Category("name1"));
        list.add(new Category("name2"));

        assertEquals(2, list.size());
    }
}
