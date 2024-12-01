package Manager;

import java.util.Scanner;

import BUS.*;
import util.Validate;

public class Menu {
    private final Scanner input = new Scanner(System.in);

    public Menu() {
        // create static fields
        new TypesBUS();
        new GenresBUS();
        new StaTypesBUS();
        new PublishersBUS();
        new MidForBooksBUS();
        new SuppliersBUS();

        // methods handler();
        addHandler();
        searchHandler();
        removeHandler();
        editHandler();

    }

    public void mainHandler() {
        int choice;
        do {
            System.out.println("=".repeat(140));
            System.out.println("1. Add new");
            System.out.println("2. Search");
            System.out.println("3. Remove");
            System.out.println("4. Edit");
            System.out.println("5. Find");
            System.out.println("0. Exit");
            System.out.println("=".repeat(140));
            System.out.print("Enter your choice: ");
            String option = input.nextLine().trim();
            if (option.equals("0")) {
                System.out.println("See you later!");
                return;
            }
            choice = Validate.parseChooseHandler(option, 5);
            switch (choice) {
                case 1:
                    addHandler();
                    break;
                case 2:
                    searchHandler();
                    break;
                case 3:
                    removeHandler();
                    break;
                case 4:
                    editHandler();
                    break;
                case 5:
                    findHandler();
                    break;
            }
        } while (true);
    }

    public void addHandler() {
        int choice;
        do {
            System.out.println("=".repeat(140));
            System.out.println("1. Add new product");
            System.out.println("2. Add new employee");
            System.out.println("3. Add new customer");
            System.out.println("4. Add new supplier");
            System.out.println("5. Add new GRN");
            System.out.println("6. Add new Bill");
            System.out.println("7. Add new publisher");
            System.out.println("8. Add new BookTypes");
            System.out.println("9. Add new Genre");
            System.out.println("10. Add new StaTypes");
            System.out.println("0. Exit");
            System.out.println("=".repeat(140));
            System.out.print("Enter your choice: ");
            choice = Validate.parseChooseHandler(input.nextLine(), 10);
            if (choice == 0) {
                System.out.println("Exit program.");
                return;
            }
            switch (choice) {
                case 1:
                    // Add new product logic
                    break;
                case 2:
                    // Add new employee logic
                    break;
                case 3:
                    // Add new customer logic
                    break;
                case 4:
                    // Add new supplier logic
                    break;
                case 5:
                    // Add new GRN logic
                    break;
                case 6:
                    // Add new Bill logic
                    break;
                case 7:
                    // Add new publisher logic
                    break;
                case 8:
                    // Add new BookTypes logic
                    break;
                case 9:
                    // Add new Genre logic
                    break;
                case 10:
                    // Add new StaTypes logic
                    break;
            }
        } while (true);
    }

    public void findHandler() {
        int choice;
        do {
            System.out.println("=".repeat(140));
            System.out.println("1. Find product");
            System.out.println("2. Find employee");
            System.out.println("3. Find customer");
            System.out.println("4. Find supplier");
            System.out.println("5. Find GRN");
            System.out.println("6. Find Bill");
            System.out.println("7. Find publisher");
            System.out.println("8. Find BookTypes");
            System.out.println("9. Find Genre");
            System.out.println("10. Find StaTypes");
            System.out.println("0. Exit");
            System.out.println("=".repeat(140));
            System.out.print("Enter your choice: ");
            choice = Validate.parseChooseHandler(input.nextLine(), 10);
            if (choice == 0) {
                System.out.println("Exit program.");
                return;
            }
            switch (choice) {
                case 1:
                    // Find product logic
                    break;
                case 2:
                    // Find employee logic
                    break;
                case 3:
                    // Find customer logic
                    break;
                case 4:
                    // Find supplier logic
                    break;
                case 5:
                    // Find GRN logic
                    break;
                case 6:
                    // Find Bill logic
                    break;
                case 7:
                    // Find publisher logic
                    break;
                case 8:
                    // Find BookTypes logic
                    break;
                case 9:
                    // Find Genre logic
                    break;
                case 10:
                    // Find StaTypes logic
                    break;
            }
        } while (true);
    }

    public void searchHandler() {
        System.out.println("=".repeat(140));
        System.out.println("Search functionality not implemented yet.");
        System.out.println("=".repeat(140));
    }

    public void removeHandler() {
        System.out.println("=".repeat(140));
        System.out.println("Remove functionality not implemented yet.");
        System.out.println("=".repeat(140));
    }

    public void editHandler() {
        System.out.println("=".repeat(140));
        System.out.println("Edit functionality not implemented yet.");
        System.out.println("=".repeat(140));
    }

}
