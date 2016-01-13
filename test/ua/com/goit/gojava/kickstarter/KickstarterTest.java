package ua.com.goit.gojava.kickstarter;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;

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

}
