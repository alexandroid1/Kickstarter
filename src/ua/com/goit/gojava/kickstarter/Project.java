package ua.com.goit.gojava.kickstarter;

/**
 * Created by alex on 26.12.15.
 */

public class Project {

    private String name;
    private int amount;
    private int days;
    private Category category;
    private String description;
    private int exist;
    private String history;

    public Project(String name, int amount, int days, String description) {
        this.name = name;
        this.amount = amount;
        this.days = days;
        this.description = description;
        this.exist = 0;
        this.history = null;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getAmount() {
        return amount;
    }

    public int getExist() {
        return exist;
    }

    public int getDays() {
        return days;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public String getDemoVideo() {
        return "https://www.youtube.com/watch?v=yRIwTeaOy5I";
    }

    public String getQuestionAnswers() {
        return "Q: question \n" +
                "A: answer";
    }
}
