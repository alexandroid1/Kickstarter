package ua.com.goit.gojava.kickstarter;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Created by alex on 12.01.16.
 */
public class KickstarterTest {

    @Test
    public void stub_and_dummy(){
        Categories categories = new Categories();
        Projects projects = new Projects();
        IO io = new IO() {

            @Override
            public int read() {
                return 0;
            }

            @Override
            public void print(String message) {
            }
        };
        Kickstarter kickstarter = new Kickstarter(categories, projects, io, new StubQuoteGenerator());
        kickstarter.run();
    }

    class FakeIO implements IO {

        private List<String> messages = new LinkedList<>();
        private List<Integer> input = new LinkedList<>();

        public FakeIO(Integer... input){
            this.input = new LinkedList<>(Arrays.asList(input));
        }

        @Override
        public int read() {
            return input.remove(0);
        }

        @Override
        public void print(String message) {
            messages.add(message);
        }

        public List<String> getMessages() {
            return messages;
        }
    }

    class StubQuoteGenerator extends QuoteGenerator {

        public StubQuoteGenerator() {
            super(new Random());
        }

        @Override
        public String nextQuote(){
            return "quote";
        }

    }

    @Test
    public void fake(){
        Categories categories = new Categories();
        categories.add(new Category("category1"));
        categories.add(new Category("category2"));
        Projects projects = new Projects();
        FakeIO io = new FakeIO(1, 0, 0);
        Kickstarter kickstarter = new Kickstarter(categories, projects, io, new StubQuoteGenerator());
        kickstarter.run();

        assertEquals("[quote\n" +
                ", Choose category or 0 for Exit:\n" +
                ", [1 category1, 2 category2]\n" +
                ", You chosen category:category1\n" +
                ", ----------------\n" +
                ", No any projects in cathegory. Press 0 - for exit \n" +
                ", Choose category or 0 for Exit:\n" +
                ", [1 category1, 2 category2]\n" +
                ", Thanks for using Kickstarter\n" +
                "]", io.getMessages().toString());
    }

    @Test
    public void shouldMenuWithProject(){
        Categories categories = new Categories();
        Category category = new Category("category1");
        categories.add(category);

        Projects projects = new Projects();
        Project project1 = new Project("project1", 100, 1000, "video1", "description1");
        projects.add(project1);

        project1.setCategory(category);

        Project project2 = new Project("project2", 200, 2000, "video2", "description2");
        projects.add(project2);

        project2.setHistory("history2");
        project2.setQuestionAnswers("QA");
        project2.setCategory(category);

        FakeIO io = new FakeIO(1, 2, 0, 0, 0);
        Kickstarter kickstarter = new Kickstarter(categories, projects, io, new StubQuoteGenerator());
        kickstarter.run();

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
                ", Choose project: [1 ... 2] or 0 for exit \n" +
                ", Choose category or 0 for Exit:\n" +
                ", [1 category1]\n" +
                ", Thanks for using Kickstarter\n" +
                "]", io.getMessages().toString());
    }

    @Test
    public void mockTest(){
        Categories categories = new Categories();
        categories.add(new Category("category1"));
        categories.add(new Category("category2"));
        Projects projects = new Projects();

        IO io = mock(IO.class);
        QuoteGenerator generator = mock(QuoteGenerator.class);

        Kickstarter kickstarter = new Kickstarter(categories, projects, io, generator);

        when(generator.nextQuote()).thenReturn("quote");
        when(io.read()).thenReturn(1, 0, 0);

        kickstarter.run();

        verify(io).print("quote\n");
        verify(io, times(2)).print("Choose category or 0 for Exit:\n");
        verify(io, times(2)).print("[1 category1, 2 category2]\n");
        verify(io).print("You chosen category:category1\n");
        verify(io).print("----------------\n");
        verify(io).print("No any projects in cathegory. Press 0 - for exit \n");
        verify(io).print("Thanks for using Kickstarter\n");

    }
}
