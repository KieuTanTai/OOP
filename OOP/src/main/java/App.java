import util.Validate;
import java.io.IOException;

import BUS.TypesBUS;

public class App {
    public static void main(String[] args) throws IOException {
        TypesBUS test = new TypesBUS();
        test.readFile();

        // show list before
        System.out.println("print list!");
        TypesBUS.showList();

        // Test methods
        test.edit("1");

        // show list after
        System.out.println("print list!");
        TypesBUS.showList();
        // testI.writeFile();
    }
}
