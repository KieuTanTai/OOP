import util.Validate;
import java.io.IOException;
import java.util.Scanner;
import BUS.TypesBUS;

public class App {
    public static void main(String[] args) throws IOException {
        // Menu app = new Menu();
        Scanner input = new Scanner(System.in);
        String temp;

        TypesBUS listTypes = new TypesBUS();
        listTypes.readFile();
        do {
            System.out.print("test validate: ");
            temp = input.nextLine().trim();
            if (!Validate.checkName(temp)) {
                System.out.println("error name!");
                temp = "";
            }
        } while (temp.isEmpty());
        input.close();

    }
}
