package ua.com.goit.gojava.kickstarter;

import org.junit.After;
import org.junit.Before;

import java.io.File;

/**
 * Created by alex on 21.01.16.
 */
public class InFileCategoriesTest extends CategoriesTest {

    public static final String CATEGORIES_FILE = "categories.txt";

    @Override
    Categories getCategories() {
        return new InFileCategories(CATEGORIES_FILE);
    }

    @After
    public void cleanUp(){
        new File(CATEGORIES_FILE).delete();
    }
}
