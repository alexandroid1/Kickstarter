package ua.com.goit.gojava.kickstarter;

import org.junit.Test;

/**
 * Created by alex on 12.01.16.
 */
public class KickstarterTest {
    @Test
    public void should_when(){
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
        Kickstarter kickstarter = new Kickstarter(categories, projects, io);
        kickstarter.run();
    }

}
