package ua.com.goit.gojava.kickstarter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by alex on 12.01.16.
 */
public class KickstarterTest {

    private QuoteGenerator generator;
    private IO io;
    private Categories categories;
    private Projects projects;
    private Kickstarter kickstarter;

    @Before
    public void setup(){
        generator = mock(QuoteGenerator.class);
        when(generator.nextQuote()).thenReturn("quote");

        io = mock(IO.class);
        categories = new Categories();
        projects = new Projects();

        kickstarter = new Kickstarter(categories, projects, io, generator);
    }

    @Test
    public void shouldExitFromProgram_WhenExitFromCategoriesMenu(){
        when(io.read()).thenReturn("0");
        kickstarter.run();
    }

    @Test
    public void shouldNoCategoriesInProject_whenSelectCategory(){

        categories.add(new Category("category1"));
        categories.add(new Category("category2"));

        when(io.read()).thenReturn("1", "0", "0");
        kickstarter.run();

        List<String> values = assertPrinted(io, 9);

        assertEquals("[quote\n" +
                ", Choose category or 0 for Exit:\n" +
                ", [1 category1, 2 category2]\n" +
                ", You chosen category:category1\n" +
                ", ----------------\n" +
                ", No any projects in cathegory. Press 0 - for exit \n" +
                ", Choose category or 0 for Exit:\n" +
                ", [1 category1, 2 category2]\n" +
                ", Thanks for using Kickstarter\n" +
                "]", values.toString());
    }

    @Test
    public void shouldMenuWithProject(){

        Category category = new Category("category1");
        categories.add(category);

        Project project1 = new Project("project1", 100, 1000, "video1", "description1");
        projects.add(project1);

        project1.setCategory(category);

        Project project2 = new Project("project2", 200, 2000, "video2", "description2");
        projects.add(project2);

        project2.setHistory("history2");
        project2.setQuestionAnswers("QA");
        project2.setCategory(category);

        when(io.read()).thenReturn("1", "2", "0", "0", "0", "0");
        kickstarter.run();

        List<String> values = assertPrinted(io, 32);

        assertEquals("[quote\n" +
                ", Choose category or 0 for Exit:\n" +
                ", [1 category1]\n" +
                ", You chosen category:category1\n" +
                ", ----------------\n" +
                ", 1 - , project1\n" +
                ", description1\n" +
                ", Need money : 100\n" +
                ", Already have money: 0$\n" +
                ", Days to get money : 1000\n" +
                ", ----------------\n" +
                ", 2 - , project2\n" +
                ", description2\n" +
                ", Need money : 200\n" +
                ", Already have money: 0$\n" +
                ", Days to get money : 2000\n" +
                ", ----------------\n" +
                ", Choose project: [1 ... 2] or 0 for exit \n" +
                ", You chosen project:project2\n" +
                ", ----------------\n" +
                ", project: \n" +
                ", history2\n" +
                ", video2\n" +
                ", QA\n" +
                ", ----------------\n" +
                ", Choose action: \n" +
                "0 - List of projects; 1 - Invest in the project\n" +
                ", Choose project: [1 ... 2] or 0 for exit \n" +
                ", Choose category or 0 for Exit:\n" +
                ", [1 category1]\n" +
                ", Thanks for using Kickstarter\n" +
                "]", values.toString());
    }

    @Test
    public void shouldPrintProjectMenu_whenSelectIt(){

        Category category = new Category("category1");
        categories.add(category);

        Project project = new Project("project1", 100, 1000, "video1", "description1");
        projects.add(project);

        project.setCategory(category);

        when(generator.nextQuote()).thenReturn("quote");
        when(io.read()).thenReturn("1", "1", "1", "0", "0", "0");

        kickstarter.run();

        List<String> values = assertPrinted(io, 31);
        assertPrinted(values, "Choose action: \n" +
                "0 - List of projects; 1 - Invest in the project\n");
        assertPrinted(values, "Thank you for what you want to invest in the project\n");
    }

    @Test
    public void shouldCategoryWithoutProjects(){

        categories.add(new Category("category1"));
        categories.add(new Category("category2"));

        when(io.read()).thenReturn("1", "0", "0");

        kickstarter.run();

        List<String> values = assertPrinted(io, 9);
        assertPrinted(values, "quote\n");
        assertPrinted(values, "Choose category or 0 for Exit:\n");
        assertPrinted(values, "[1 category1, 2 category2]\n");
        assertPrinted(values, "You chosen category:category1\n");
        assertPrinted(values, "----------------\n");
        assertPrinted(values, "No any projects in cathegory. Press 0 - for exit \n");
        assertPrinted(values, "Thanks for using Kickstarter\n");
    }

    @Test
    public void shouldIncomeAmountToProject_whenDonate(){
        int TOTAL = 100;

        Category category = new Category("category1");
        categories.add(category);

        Project project = new Project("project1", TOTAL, 1000, "video1", "description1");
        projects.add(project);

        project.setCategory(category);

        when(io.read()).thenReturn("1", "1", "1", "Alex", "231321321", "25", "0", "0", "0");

        kickstarter.run();

        List<String> values = assertPrinted(io, 31);
        assertPrinted(values, "Choose action: \n" +
                "0 - List of projects; 1 - Invest in the project\n");
        assertPrinted(values, "Thank you for what you want to invest in the project\n");
        assertPrinted(values, "enter your name:\n");
        assertPrinted(values, "enter your credit card number:\n");
        assertPrinted(values, "enter the amount of money:\n");
        assertPrinted(values, "Thank you Alex Money amounting to 25 successfully deposited!\n");

    }

    private void assertPrinted(List<String> values, String expected) {
        assertTrue("Actual data: \n" + values.toString() + "doesn't contain: \n\n" + expected,
                values.contains(expected));
    }

    private List<String> assertPrinted(IO io, int times) {
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(io, times(times)).print(captor.capture());
        return captor.getAllValues();
    }
}
