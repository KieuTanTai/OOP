import util.Validate;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        // Menu app = new Menu();
        Scanner input = new Scanner(System.in);
        String temp;
        do {
            System.out.print("test validate: ");
            temp = input.nextLine().trim();
            if (!Validate.checkName(temp)) {
                System.out.println("error name!");
                temp = "";
            }
        } while (temp.isEmpty());
    }
}
