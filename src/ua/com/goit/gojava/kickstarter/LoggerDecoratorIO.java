package ua.com.goit.gojava.kickstarter;

/**
 * Created by alex on 24.01.16.
 */
public class LoggerDecoratorIO implements IO {
    private IO io;

    public LoggerDecoratorIO(IO io){
        this.io = io;
    }

    @Override
    public String read() {
        String read = io.read();
        System.out.println("LOG ---> FROM CONSOLE:" + read);
        return read;

    }

    @Override
    public void print(String message) {
        io.print(message);
        System.out.println("LOG ---> PRINT MESSAGE:" + message);
    }
}
