import util.Validate;
import java.io.IOException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws IOException {
        // Menu app = new Menu();
        Scanner input = new Scanner(System.in);
        String tempI;

        do {
            System.out.print("test validate: ");
            tempI = input.nextLine().trim();
            if (!Validate.validateID(tempI)) {
                System.out.println("error bigDecimal!");
                tempI = "";
            }
        } while (tempI.isEmpty());
        System.out.println(tempI);

        input.close();

    }
}
