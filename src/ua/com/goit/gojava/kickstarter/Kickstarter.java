package ua.com.goit.gojava.kickstarter;

import java.util.Locale;

/**
 * Created by alex on 25.12.15.
 */
public class Kickstarter{
    public static void main(String[] args) {
        Category category1 = new Category("Photo");
        Category category2 = new Category("Video");
        Category category3 = new Category("Music");

        Categories categories = new Categories();
        categories.add(category1);
        categories.add(category2);
        categories.add(category3);

        Kickstarter application = new Kickstarter();
        application.run();
    }

    private void run() {
        QuoteGenerator generator = new QuoteGenerator();
        System.out.println(generator.nextQuote());
    }

}
