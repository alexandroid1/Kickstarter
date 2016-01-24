package ua.com.goit.gojava.kickstarter;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by alex on 21.01.16.
 */
public class InFileCategories implements Categories {

    private final File file;

    public InFileCategories(String fileName){
        file = createFileIfNeed(fileName);
    }

    @Override
    public void add(Category category) {
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new FileWriter(file, true));
            out.append(category.getName() + "\n");

        } catch (FileNotFoundException e) {
            throw new RuntimeException("We could not read the file ", e);
        } catch (IOException e) {
            throw new RuntimeException("Reading from a file fallen ", e);
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    throw new RuntimeException("We could not close the stream ", e);
                }
            }
        }
    }

    @Override
    public String[] getCategories() {
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(file));
            List<String> result = new LinkedList<>();
            String line = in.readLine();
            int index = 1;
            while (line != null) {
                result.add(index + " " + line);
                line = in.readLine();
                index++;
            }
            return result.toArray(new String[result.size()]);

        } catch (FileNotFoundException e) {
            throw new RuntimeException("We could not read the file ", e);
        } catch (IOException e) {
            throw new RuntimeException("Reading from a file fallen ", e);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    throw new RuntimeException("We could not close the stream ", e);
                }
            }
        }
    }

    @Override
    public Category get(int index) {
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(file));

            String line = in.readLine();
            int current = 0;
            while (line != null) {
                if (current==index) break;
                line = in.readLine();
                current++;
            }

            return  new Category(line);

        } catch (FileNotFoundException e) {
            throw new RuntimeException("We could not read the file ", e);
        } catch (IOException e) {
            throw new RuntimeException("Reading from a file fallen ", e);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    throw new RuntimeException("We could not close the stream ", e);
                }
            }
        }
    }

    @Override
    public int size() {
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(file));

            int counter = 0;
            String line = in.readLine();
            while (line != null) {
                counter++;
                line = in.readLine();
            }
            return counter;

        } catch (FileNotFoundException e) {
            throw new RuntimeException("We could not read the file ", e);
        } catch (IOException e) {
            throw new RuntimeException("Reading from a file fallen ", e);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    throw new RuntimeException("We could not close the stream ", e);
                }
            }
        }
    }

    private File createFileIfNeed(String fileName) {
        File file = new File(fileName);
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException("File container fell ", e);
            }
        }
        return file;
    }

}
