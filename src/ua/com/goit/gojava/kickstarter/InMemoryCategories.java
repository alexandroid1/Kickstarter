package ua.com.goit.gojava.kickstarter;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by alex on 25.12.15.
 */
public class InMemoryCategories implements Categories {

    private List<Category> data = new LinkedList<Category>();

    @Override
    public void add(Category category) {
        data.add(category);
    }

    @Override
    public List<Category> getCategories(){
        List<Category> result = new LinkedList<Category>();
        for (int index=0; index<data.size(); index ++){
            result.add(new Category(index+1, data.get(index).getName()));
        }
        return result;
    }

    @Override
    public Category get(int index) {
        return data.get(index);
    }

    @Override
    public int size() {
        return data.size();
    }

    @Override
    public boolean exists(int id) {
        if (data.get(id) != null) {
            return true;
        } else {
            return false;
        }
    }
}
