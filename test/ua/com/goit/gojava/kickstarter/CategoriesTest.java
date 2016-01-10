package ua.com.goit.gojava.kickstarter;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by alex on 10.01.16.
 */
public class CategoriesTest {

    @Test
    public void shouldCategoriesList_whenAddCategories(){
        Categories list = new Categories();

        list.add(new Category("name1"));
        list.add(new Category("name2"));

        String[] categories = list.getCategories();
        assertEquals("[1 name1, 2 name2]",
                Arrays.toString(categories));
    }

    @Test
    public void shouldCategoriesList_whenNoCategories(){
        Categories list = new Categories();

        String[] categories = list.getCategories();
        assertEquals("[]",
                Arrays.toString(categories));
    }

    @Test
    public void shouldGetCategoryByIndex_whenAddCategories(){
        Categories list = new Categories();

        Category category1 = new Category("name1");
        list.add(category1);

        Category category2 = new Category("name2");
        list.add(category2);

        assertSame(category1, list.get(0));
        assertSame(category2, list.get(1));
    }

    @Test
    public void shouldCategoriesListSize_whenAddCategories(){
        Categories list = new Categories();

        assertEquals(0, list.size());

        list.add(new Category("name1"));
        list.add(new Category("name2"));

        assertEquals(2, list.size());
    }
}
