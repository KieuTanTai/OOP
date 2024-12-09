package Manager;

import java.io.IOException;
import java.util.Scanner;

import BUS.*;
import DTO.SaleEvents;
import util.Validate;

public class Menu {
    private final Scanner input = new Scanner(System.in);

    public Menu() throws IOException {
        // create static fields
        new TypesBUS().readFile();
        new GenresBUS().readFile();
        new StaTypesBUS().readFile();
        new PublishersBUS().readFile();
        new MidForBooksBUS().readFile();
        new SuppliersBUS().readFile();
        new BookFormatsBUS().readFile();
    }

    public void mainHandler() {
        int choice;
        do {
            System.out.println("=".repeat(140));
            System.out.println("I. Add new");
            System.out.println("II. Search");
            System.out.println("III. Remove");
            System.out.println("IV. Edit");
            System.out.println("0. Exit");
            System.out.println("=".repeat(140));
            System.out.print("Enter your choice: ");
            String option = input.nextLine().trim();
            if (option.equals("0")) {
                System.out.println("See you later!");
                return;
            }
            choice = Validate.parseChooseHandler(option, 4);

            try {
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
                }
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        } while (true);
    }

    public void addHandler() {
        int choice;
        do {
            System.out.println("=".repeat(140));
            System.out.println("I. Add new product");
            System.out.println("II. Add new employee");
            System.out.println("III. Add new customer");
            System.out.println("IV. Add new supplier");
            System.out.println("V. Add new GRN");
            System.out.println("VI. Add new Bill");
            System.out.println("VII. Add new publisher");
            System.out.println("VIII. Add new BookTypes");
            System.out.println("IX. Add new Genre");
            System.out.println("X. Add new StaTypes");
            System.out.println("XI. Add new sale event");
            System.out.println("0. Exit");
            System.out.println("=".repeat(140));
            System.out.print("Enter your choice: ");
            String option = input.nextLine().trim();
            if (option.equals("0")) {
                System.out.println("Exit program!");
                return;
            }
            choice = Validate.parseChooseHandler(option, 11);

            try {
                switch (choice) {
                    case 1:
                        int productChoice;
                        do {
                            System.out.println("=".repeat(140));
                            System.out.println("I. Add Book");
                            System.out.println("II. Add Stationary");
                            System.out.println("0. Back to main menu");
                            System.out.println("=".repeat(140));
                            System.out.print("Enter your choice: ");
                            String chooseProduct = input.nextLine().trim();
                            if (chooseProduct.equals("0")) {
                                System.out.println("Exit program!");
                                return;
                            }
                            productChoice = Validate.parseChooseHandler(chooseProduct, 3);
                            try {
                                switch (productChoice) {
                                    case 1:
                                        BooksBUS booksList = new BooksBUS();
                                        booksList.readFile();
                                        booksList.add();
                                        booksList.writeFile();
                                        break;
                                    case 2:
                                        StationeriesBUS staList = new StationeriesBUS();
                                        staList.readFile();
                                        staList.add();
                                        staList.writeFile();
                                        break;
                                }
                            } catch (Exception e) {
                                System.out.println("An error occurred: " + e.getMessage());
                            }
                        } while (true);
                    case 2:
                        EmployeesBUS employeesList = new EmployeesBUS();
                        employeesList.readFile();
                        employeesList.add();
                        employeesList.writeFile();
                        break;
                    case 3:
                        CustomersBUS customersList = new CustomersBUS();
                        customersList.readFile();
                        customersList.add();
                        customersList.writeFile();
                        break;
                    case 4:
                        SuppliersBUS supsList = new SuppliersBUS();
                        supsList.readFile();
                        supsList.add();
                        supsList.writeFile();
                        break;
                    case 5:
                        GRNsBUS grnList = new GRNsBUS();
                        grnList.readFile();
                        grnList.add();
                        grnList.writeFile();
                        break;
                    case 6:
                        BillBUS billsList = new BillBUS();
                        billsList.readFile();
                        billsList.add();
                        billsList.writeFile();
                        break;
                    case 7:
                        PublishersBUS publishersList = new PublishersBUS();
                        publishersList.readFile();
                        publishersList.add();
                        publishersList.writeFile();
                        break;
                    case 8:
                        TypesBUS typesList = new TypesBUS();
                        typesList.readFile();
                        typesList.add();
                        typesList.writeFile();
                        break;
                    case 9:
                        GenresBUS genresList = new GenresBUS();
                        genresList.readFile();
                        genresList.add();
                        genresList.writeFile();
                        break;
                    case 10:
                        StaTypesBUS staTypesList = new StaTypesBUS();
                        staTypesList.readFile();
                        staTypesList.add();
                        staTypesList.writeFile();
                        break;
                    case 11:
                        int userChoice;
                        SaleEventsBUS salesList = new SaleEventsBUS();
                        salesList.readFile();
                        System.out.printf("| %s %s %s |\n", "I.Cancel", "-".repeat(20), "II.Add");
                        do {
                            System.out.print("choose option (1 or 2) : ");
                            String userInput = input.nextLine().trim();
                            userChoice = Validate.parseChooseHandler(userInput, 2);
                        } while (userChoice == -1);
                        if (userChoice == 1)
                            break;
                        // add methods
                        SaleEvents sale = new SaleEvents();
                        sale.setInfo();
                        if (sale != null)
                            salesList.add(sale);
                        salesList.writeFile();
                        break;
                }
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        } while (true);
    }

    public void searchHandler() {
        int choice;
        do {
            System.out.println("=".repeat(140));
            System.out.println("I. Search product");
            System.out.println("II. Search employee");
            System.out.println("III. Search customer");
            System.out.println("IV. Search supplier");
            System.out.println("V. Search GRN");
            System.out.println("VI. Search Bill");
            System.out.println("VII. Search publisher");
            System.out.println("VIII. Search BookTypes");
            System.out.println("IX. Search Genre");
            System.out.println("X. Search StaTypes");
            System.out.println("XI. Search sale event");
            System.out.println("0. Exit");
            System.out.println("=".repeat(140));
            System.out.print("Enter your choice: ");
            String option = input.nextLine().trim();
            if (option.equals("0")) {
                System.out.println("Exit program!");
                return;
            }
            choice = Validate.parseChooseHandler(option, 11);

            try {
                switch (choice) {
                    case 1:
                        int productChoice;
                        do {
                            System.out.println("=".repeat(140));
                            System.out.println("I. Search Book");
                            System.out.println("II. Search Stationary");
                            System.out.println("0. Back to main menu");
                            System.out.println("=".repeat(140));
                            System.out.print("Enter your choice: ");
                            String chooseProduct = input.nextLine().trim();
                            if (chooseProduct.equals("0")) {
                                System.out.println("Exit program!");
                                return;
                            }
                            productChoice = Validate.parseChooseHandler(chooseProduct, 4);
                            try {
                                switch (productChoice) {
                                    case 1:
                                        BooksBUS booksList = new BooksBUS();
                                        booksList.readFile();
                                        booksList.search();
                                        booksList.writeFile();
                                        break;
                                    case 2:
                                        StationeriesBUS staList = new StationeriesBUS();
                                        staList.readFile();
                                        staList.search();
                                        staList.writeFile();
                                        break;
                                }
                            } catch (Exception e) {
                                System.out.println("An error occurred: " + e.getMessage());
                            }
                        } while (true);
                    case 2:
                        EmployeesBUS employeesList = new EmployeesBUS();
                        employeesList.readFile();
                        employeesList.search();
                        employeesList.writeFile();
                        break;
                    case 3:
                        CustomersBUS customersList = new CustomersBUS();
                        customersList.readFile();
                        customersList.search();
                        customersList.writeFile();
                        break;
                    case 4:
                        SuppliersBUS supsList = new SuppliersBUS();
                        supsList.readFile();
                        supsList.search();
                        supsList.writeFile();
                        break;
                    case 5:
                        GRNsBUS grnList = new GRNsBUS();
                        grnList.readFile();
                        grnList.search();
                        grnList.writeFile();
                        break;
                    case 6:
                        BillBUS billsList = new BillBUS();
                        billsList.readFile();
                        billsList.search();
                        billsList.writeFile();
                        break;
                    case 7:
                        PublishersBUS publishersList = new PublishersBUS();
                        publishersList.readFile();
                        publishersList.search();
                        publishersList.writeFile();
                        break;
                    case 8:
                        TypesBUS typesList = new TypesBUS();
                        typesList.readFile();
                        typesList.search();
                        typesList.writeFile();
                        break;
                    case 9:
                        GenresBUS genresList = new GenresBUS();
                        genresList.readFile();
                        genresList.search();
                        genresList.writeFile();
                        break;
                    case 10:
                        StaTypesBUS staTypesList = new StaTypesBUS();
                        staTypesList.readFile();
                        staTypesList.search();
                        staTypesList.writeFile();
                        break;
                    case 11:
                        int index;
                        SaleEventsBUS salesList = new SaleEventsBUS();
                        salesList.readFile();
                        System.out.print("Enter name or id of sale event : ");
                        String userInput = input.nextLine().trim();
                        index  = salesList.findById(userInput);
                        if (index != -1) {
                            salesList.getListSaleEvent()[index].showInfo();
                        }
                        break;
                }
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        } while (true);
    }

    public void removeHandler() {
        int choice;
        do {
            System.out.println("=".repeat(140));
            System.out.println("I. Remove product");
            System.out.println("II. Remove employee");
            System.out.println("III. Remove customer");
            System.out.println("IV. Remove supplier");
            System.out.println("V. Remove GRN");
            System.out.println("VI. Remove Bill");
            System.out.println("VII. Remove publisher");
            System.out.println("VIII. Remove BookTypes");
            System.out.println("IX. Remove Genre");
            System.out.println("X. Remove StaTypes");
            System.out.println("XI. Remove sale event");
            System.out.println("0. Exit");
            System.out.println("=".repeat(140));
            System.out.print("Enter your choice: ");
            String option = input.nextLine().trim();
            if (option.equals("0")) {
                System.out.println("Exit program!");
                return;
            }
            choice = Validate.parseChooseHandler(option, 11);

            try {
                switch (choice) {
                    case 1:
                        int productChoice;
                        do {
                            System.out.println("=".repeat(140));
                            System.out.println("I. Remove Book");
                            System.out.println("II. Remove Stationary");
                            System.out.println("0. Back to main menu");
                            System.out.println("=".repeat(140));
                            System.out.print("Enter your choice: ");
                            String chooseProduct = input.nextLine().trim();
                            if (chooseProduct.equals("0")) {
                                System.out.println("Exit program!");
                                return;
                            }
                            productChoice = Validate.parseChooseHandler(chooseProduct, 4);

                            try {
                                switch (productChoice) {
                                    case 1:
                                        BooksBUS booksList = new BooksBUS();
                                        booksList.readFile();
                                        booksList.remove();
                                        booksList.writeFile();
                                        break;
                                    case 2:
                                        StationeriesBUS staList = new StationeriesBUS();
                                        staList.readFile();
                                        staList.remove();
                                        staList.writeFile();
                                        break;
                                }
                            } catch (Exception e) {
                                System.out.println("An error occurred: " + e.getMessage());
                            }
                        } while (true);
                    case 2:
                        EmployeesBUS employeesList = new EmployeesBUS();
                        employeesList.readFile();
                        employeesList.remove();
                        employeesList.writeFile();
                        break;
                    case 3:
                        CustomersBUS customersList = new CustomersBUS();
                        customersList.readFile();
                        customersList.remove();
                        customersList.writeFile();
                        break;
                    case 4:
                        SuppliersBUS supsList = new SuppliersBUS();
                        supsList.readFile();
                        supsList.remove();
                        supsList.writeFile();
                        break;
                    case 5:
                        GRNsBUS grnList = new GRNsBUS();
                        grnList.readFile();
                        grnList.remove();
                        grnList.writeFile();
                        break;
                    case 6:
                        BillBUS billsList = new BillBUS();
                        billsList.readFile();
                        billsList.remove();
                        billsList.writeFile();
                        break;
                    case 7:
                        PublishersBUS publishersList = new PublishersBUS();
                        publishersList.readFile();
                        publishersList.remove();
                        publishersList.writeFile();
                        break;
                    case 8:
                        TypesBUS typesList = new TypesBUS();
                        typesList.readFile();
                        typesList.remove();
                        typesList.writeFile();
                        break;
                    case 9:
                        GenresBUS genresList = new GenresBUS();
                        genresList.readFile();
                        genresList.remove();
                        genresList.writeFile();
                        break;
                    case 10:
                        StaTypesBUS staTypesList = new StaTypesBUS();
                        staTypesList.readFile();
                        staTypesList.remove();
                        staTypesList.writeFile();
                        break;
                    case 11:
                        SaleEventsBUS salesList = new SaleEventsBUS();
                        salesList.readFile();
                        System.out.print("Enter name or id of sale event : ");
                        String userInput = input.nextLine().trim();
                        salesList.delete(userInput);
                        salesList.writeFile();
                        break;
                }
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        } while (true);
    }

    public void editHandler() {
        int choice;
        do {
            System.out.println("=".repeat(140));
            System.out.println("I. Edit product");
            System.out.println("II. Edit employee");
            System.out.println("III. Edit customer");
            System.out.println("IV. Edit supplier");
            System.out.println("V. Edit GRN");
            System.out.println("VI. Edit Bill");
            System.out.println("VII. Edit publisher");
            System.out.println("VIII. Edit BookTypes");
            System.out.println("IX. Edit Genre");
            System.out.println("X. Edit StaTypes");
            System.out.println("XI. Edit sale event");
            System.out.println("0. Exit");
            System.out.println("=".repeat(140));
            System.out.print("Enter your choice: ");
            String option = input.nextLine().trim();
            if (option.equals("0")) {
                System.out.println("Exit program!");
                return;
            }
            choice = Validate.parseChooseHandler(option, 11);

            try {
                switch (choice) {
                    case 1:
                        int productChoice;
                        do {
                            System.out.println("=".repeat(140));
                            System.out.println("I. Edit Book");
                            System.out.println("II. Edit Stationary");
                            System.out.println("0. Back to main menu");
                            System.out.println("=".repeat(140));
                            System.out.print("Enter your choice: ");
                            String chooseProduct = input.nextLine().trim();
                            if (chooseProduct.equals("0")) {
                                System.out.println("Exit program!");
                                return;
                            }
                            productChoice = Validate.parseChooseHandler(chooseProduct, 4);

                            try {
                                switch (productChoice) {
                                    case 1:
                                        BooksBUS booksList = new BooksBUS();
                                        booksList.readFile();
                                        booksList.edit();
                                        booksList.writeFile();
                                        break;
                                    case 2:
                                        StationeriesBUS staList = new StationeriesBUS();
                                        staList.readFile();
                                        staList.edit();
                                        staList.writeFile();
                                        break;
                                }
                            } catch (Exception e) {
                                System.out.println("An error occurred: " + e.getMessage());
                            }
                        } while (true);
                    case 2:
                        EmployeesBUS employeesList = new EmployeesBUS();
                        employeesList.readFile();
                        employeesList.edit();
                        employeesList.writeFile();
                        break;
                    case 3:
                        CustomersBUS customersList = new CustomersBUS();
                        customersList.readFile();
                        customersList.edit();
                        customersList.writeFile();
                        break;
                    case 4:
                        SuppliersBUS supsList = new SuppliersBUS();
                        supsList.readFile();
                        supsList.edit();
                        supsList.writeFile();
                        break;
                    case 5:
                        GRNsBUS grnList = new GRNsBUS();
                        grnList.readFile();
                        grnList.edit();
                        grnList.writeFile();
                        break;
                    case 6:
                        BillBUS billsList = new BillBUS();
                        billsList.readFile();
                        billsList.edit();
                        billsList.writeFile();
                        break;
                    case 7:
                        PublishersBUS publishersList = new PublishersBUS();
                        publishersList.readFile();
                        publishersList.edit();
                        publishersList.writeFile();
                        break;
                    case 8:
                        TypesBUS typesList = new TypesBUS();
                        typesList.readFile();
                        typesList.edit();
                        typesList.writeFile();
                        break;
                    case 9:
                        GenresBUS genresList = new GenresBUS();
                        genresList.readFile();
                        genresList.edit();
                        genresList.writeFile();
                        break;
                    case 10:
                        StaTypesBUS staTypesList = new StaTypesBUS();
                        staTypesList.readFile();
                        staTypesList.edit();
                        staTypesList.writeFile();
                        break;
                    case 11:
                        SaleEventsBUS salesList = new SaleEventsBUS();
                        salesList.readFile();
                        System.out.print("Enter name or id of sale event : ");
                        String userInput = input.nextLine().trim();
                        salesList.update(userInput);
                        salesList.writeFile();
                        break;
                }
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        } while (true);
    }

}
