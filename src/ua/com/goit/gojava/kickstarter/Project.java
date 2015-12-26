package ua.com.goit.gojava.kickstarter;

/**
 * Created by alex on 26.12.15.
 */

public class Project {

    private String name;
    private int amount;
    private int days;

    public Project(String name, int amount, int days) {
        this.name = name;
        this.amount = amount;
        this.days = days;
    }
}
