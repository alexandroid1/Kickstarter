package ua.com.goit.gojava.kickstarter;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

/**
 * Created by alex on 10.01.16.
 */
public class QuoteGeneratorTest {

    class FakeRandom extends Random {


        private final List<Integer> numbers;

        public FakeRandom (Integer... numbers){
            this.numbers = new LinkedList(Arrays.asList(numbers));
        }

        @Override
        public int nextInt(int i){
            return numbers.remove(0);
        }
    }

    @Test
    public void shouldGenerateNewQuote(){

        QuoteGenerator generator = new QuoteGenerator(new FakeRandom(0,1));

        String quote1 = generator.nextQuote();

        assertEquals("In the end, it's not going to matter how many breaths you took, " +
                "but how many moments took your breath away", quote1);

        String quote2 = generator.nextQuote();

        assertEquals("When life gives you a hundred reasons to cry, " +
                "show life that you have a thousand reasons to smile", quote2);
    }
}
