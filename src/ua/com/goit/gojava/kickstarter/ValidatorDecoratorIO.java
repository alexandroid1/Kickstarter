package ua.com.goit.gojava.kickstarter;

/**
 * Created by alex on 24.01.16.
 */
public class ValidatorDecoratorIO implements IO{

    private IO io;

    public ValidatorDecoratorIO(IO io){
        this.io = io;
    }

    @Override
    public String read() {
        String read = io.read();
        if (read.length()>1000){
            throw new IllegalArgumentException("line should not exceed 100 characters");
        }
        return read;

    }

    @Override
    public void print(String message) {
        if (message.length()>1000){
            throw new IllegalArgumentException("line should not exceed 100 characters");
        }
        io.print(message);
    }
}
