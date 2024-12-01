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
            System.out.println("1. Add new");
            System.out.println("2. Search");
            System.out.println("3. Remove");
            System.out.println("4. Edit");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            String option = input.nextLine().trim();
            if (option.equals("0")) {
                System.out.println("See you later!");
                return;
            }
            choice = Validate.parseChooseHandler(option, 4);
            switch (choice) {
                case 1:
                    addHandler();
                    break;
                case 2:
                    findHandler();
                    break;
                case 3:
                    removeHandler();
                    break;
                case 4:
                    searchHandler();
                    break;
                case 5:
                    editHandler();
                    break;
            }
        } while (choice != 0);
    }

    public void addHandler() {
        int choice = 0;
        do {
            System.out.println("*".repeat(60));
            System.out.println("1. Add new product ");
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
            System.out.println("*".repeat(60));
            System.out.print("Enter your choice: ");
            String inputChoice = input.nextLine();
            if (inputChoice.equals("0")) {
                System.out.println("Exit program.");
                break;
            }
            choice = Validate.parseChooseHandler(inputChoice, 10);
        } while (choice == -1);

        switch (choice) {
            case 1:
                do {
                    System.out.println("*".repeat(60));
                    System.out.println("I. Add new book");
                    System.out.println("II. Add new stationary");
                    System.out.println("0. Exit");
                    System.out.println("*".repeat(60));
                    System.out.print("Enter your choice: ");
                    choice = Validate.parseChooseHandler(input.nextLine(), 2);
                    switch (choice) {
                        case 1:

                            break;
                        case 2:

                            break;
                        case 0:
                            System.out.println("Exit program.");
                            break;
                    }
                } while (choice != 0);
                break;
            case 2:

                break;
            case 3:

                break;
            case 4:

                break;
            case 5:

                break;
            case 6:

                break;
            case 7:

                break;
            case 8:

                break;
            case 9:

                break;
            case 10:

                break;
            case 0:
                System.out.println("Exit program.");
                break;
        }

    }

    public void findHandler() {
    }

    public void searchHandler() {
    }

    public void removeHandler() {

    }

    public void editHandler() {

    }
}
