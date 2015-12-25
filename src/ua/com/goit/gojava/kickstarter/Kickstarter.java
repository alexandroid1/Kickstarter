package ua.com.goit.gojava.kickstarter;

import java.util.Random;

/**
 * Created by alex on 25.12.15.
 */
public class Kickstarter {
    public static void main(String[] args) {
        Kickstarter application = new Kickstarter();
        application.run();
    }

    private void run() {


        System.out.println(nextQuote());
    }

    private String nextQuote() {
        String[] strings = new String[]{
                " In the end, it's not going to matter how many breaths you took, " +
                        "but how many moments took your breath away",

                "When life gives you a hundred reasons to cry, " +
                        "show life that you have a thousand reasons to smile",

                "It hurts to love someone and not be loved in return.\n" +
                        "But what is more painful is to love someone and never\n" +
                        "find the courage to let that person know how you feel",

                "A sad thing in life is when you meet someone who\n" +
                        "means a lot to you, only to find out in the end that it was\n" +
                        "never meant to be and you just have to let go",

                "The best kind of friend is the kind you can sit on a\n" +
                        "porch swing with, never say a word, and then walk away\n" +
                        "feeling like it was the best conversation you've ever had",
        };
        int index = new Random().nextInt(strings.length);
        return strings[index];
    }
}
