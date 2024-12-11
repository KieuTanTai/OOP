package Manager;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

import BUS.*;
import DTO.Bill;
import DTO.Products;
import DTO.SaleEvents;
import util.Validate;

public class Menu {
    private final Scanner input = new Scanner(System.in);
    private String accountID;

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

    public void login() {
        int choice;
        do {
            System.out.println("=".repeat(140));
            System.out.println("I. Login as Manager");
            System.out.println("II. Login as Staff");
            System.out.println("III. Login as Warehouse Keeper");
            System.out.println("0. Exit");
            System.out.println("=".repeat(140));
            System.out.print("Enter your choice: ");
            String option = input.nextLine().trim();
            if (option.equals("0")) {
                System.out.println("I'll auto-logout for you! See ya -_-'");
                return;
            }
            choice = Validate.parseChooseHandler(option, 3);

            try {
                if (choice == 1) {
                    EmployeesBUS list = new EmployeesBUS();
                    list.readFile();
                    this.accountID = list.login("Manager");
                    if (!accountID.isEmpty()) {
                        System.out.println("Yay ! Finally you can login -_-'");
                        menuManagers();
                    }
                } else if (choice == 2) {
                    EmployeesBUS list = new EmployeesBUS();
                    list.readFile();
                    this.accountID = list.login("Employee"); 
                    if (!accountID.isEmpty()) {
                        System.out.println("Yay ! Finally you can login -_-'");
                        menuForEmployees();
                    }
                } else if (choice == 3) {
                    EmployeesBUS list = new EmployeesBUS();
                    list.readFile();
                    this.accountID = list.login("Warehouse Keeper");
                    if (!accountID.isEmpty()) {
                        System.out.println("Yay ! Finally you can login -_-'");
                        menuForWarehouseKeepers();
                    }
                }
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        } while (true);
    }

    private void menuManagers() {
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

    private void addHandler() {
        int choice;
        do {
            System.out.println("=".repeat(140));
            System.out.println("I. Add new product");
            System.out.println("II. Add new employee");
            System.out.println("III. Add new customer");
            System.out.println("IV. Add new supplier");
            System.out.println("V. Add new publisher");
            System.out.println("VI. Add new BookTypes");
            System.out.println("VII. Add new Genre");
            System.out.println("VIII. Add new StaTypes");
            System.out.println("IX. Add new sale event");
            System.out.println("0. Exit");
            System.out.println("=".repeat(140));
            System.out.print("Enter your choice: ");
            String option = input.nextLine().trim();
            if (option.equals("0")) {
                System.out.println("Exit program!");
                return;
            }
            choice = Validate.parseChooseHandler(option, 9);

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
                        PublishersBUS publishersList = new PublishersBUS();
                        publishersList.readFile();
                        publishersList.add();
                        publishersList.writeFile();
                        break;
                    case 6:
                        TypesBUS typesList = new TypesBUS();
                        typesList.readFile();
                        typesList.add();
                        typesList.writeFile();
                        break;
                    case 7:
                        GenresBUS genresList = new GenresBUS();
                        genresList.readFile();
                        genresList.add();
                        genresList.writeFile();
                        break;
                    case 8:
                        StaTypesBUS staTypesList = new StaTypesBUS();
                        staTypesList.readFile();
                        staTypesList.add();
                        staTypesList.writeFile();
                        break;
                    case 9:
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

    private void searchHandler() {
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
                        index = salesList.findById(userInput);
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

    private void removeHandler() {
        int choice;
        do {
            System.out.println("=".repeat(140));
            System.out.println("I. Remove product");
            System.out.println("II. Remove employee");
            System.out.println("III. Remove customer");
            System.out.println("IV. Remove supplier");
            System.out.println("V. Remove publisher");
            System.out.println("VI. Remove BookTypes");
            System.out.println("VII. Remove Genre");
            System.out.println("VIII. Remove StaTypes");
            System.out.println("IX. Remove sale event");
            System.out.println("0. Exit");
            System.out.println("=".repeat(140));
            System.out.print("Enter your choice: ");
            String option = input.nextLine().trim();
            if (option.equals("0")) {
                System.out.println("Exit program!");
                return;
            }
            choice = Validate.parseChooseHandler(option, 9);

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
                        PublishersBUS publishersList = new PublishersBUS();
                        publishersList.readFile();
                        publishersList.remove();
                        publishersList.writeFile();
                        break;
                    case 6:
                        TypesBUS typesList = new TypesBUS();
                        typesList.readFile();
                        typesList.remove();
                        typesList.writeFile();
                        break;
                    case 7:
                        GenresBUS genresList = new GenresBUS();
                        genresList.readFile();
                        genresList.remove();
                        genresList.writeFile();
                        break;
                    case 8:
                        StaTypesBUS staTypesList = new StaTypesBUS();
                        staTypesList.readFile();
                        staTypesList.remove();
                        staTypesList.writeFile();
                        break;
                    case 9:
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

    private void editHandler() {
        int choice;
        do {
            System.out.println("=".repeat(140));
            System.out.println("I. Edit product");
            System.out.println("II. Edit employee");
            System.out.println("III. Edit customer");
            System.out.println("IV. Edit supplier");
            System.out.println("V. Edit publisher");
            System.out.println("VI. Edit BookTypes");
            System.out.println("VII. Edit Genre");
            System.out.println("VIII. Edit StaTypes");
            System.out.println("IX. Edit sale event");
            System.out.println("0. Exit");
            System.out.println("=".repeat(140));
            System.out.print("Enter your choice: ");
            String option = input.nextLine().trim();
            if (option.equals("0")) {
                System.out.println("Exit program!");
                return;
            }
            choice = Validate.parseChooseHandler(option, 9);

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
                        PublishersBUS publishersList = new PublishersBUS();
                        publishersList.readFile();
                        publishersList.edit();
                        publishersList.writeFile();
                        break;
                    case 6:
                        TypesBUS typesList = new TypesBUS();
                        typesList.readFile();
                        typesList.edit();
                        typesList.writeFile();
                        break;
                    case 7:
                        GenresBUS genresList = new GenresBUS();
                        genresList.readFile();
                        genresList.edit();
                        genresList.writeFile();
                        break;
                    case 8:
                        StaTypesBUS staTypesList = new StaTypesBUS();
                        staTypesList.readFile();
                        staTypesList.edit();
                        staTypesList.writeFile();
                        break;
                    case 9:
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

    private void menuForEmployees() {
        int choice;
        do {
            System.out.println("=".repeat(140));
            System.out.println("I. Sales");
            System.out.println("0. Get off work");
            System.out.println("=".repeat(140));
            System.out.print("Enter your choice: ");
            String option = input.nextLine().trim();
            if (option.equals("0")) {
                try {
                    BillBUS list = new BillBUS();
                    list.readFile();
                    int index = list.find(accountID);
                    if (index != -1) 
                        System.out.printf("Total price : %s\n", Validate.formatPrice(list.getList()[index].getTotalPrice()).toString());
                    System.out.println("See ya -_-'!");
                    return;
                } catch (Exception e) {
                    System.out.println("An error occurred: " + e.getMessage());
                }
            }
            choice = Validate.parseChooseHandler(option, 1);

            try {
                if (choice == 1)
                    sales();
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        } while (true);
    }

    // methods sale for employee
    private void sales() {
        int choice;
        do {
            System.out.println("=".repeat(140));
            System.out.println("I. Show products");
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
                        int productChoice;
                        do {
                            System.out.println("=".repeat(140));
                            System.out.println("I. Show Books");
                            System.out.println("II. Show Stationeries");
                            System.out.println("III. Buy");
                            System.out.println("IV.Search Books");
                            System.out.println("V.Search Stationeries");
                            System.out.println("0. Back to main menu");
                            System.out.println("=".repeat(140));
                            System.out.print("Enter your choice: ");
                            String chooseProduct = input.nextLine().trim();
                            if (chooseProduct.equals("0")) {
                                System.out.println("Exit program!");
                                return;
                            }
                            productChoice = Validate.parseChooseHandler(chooseProduct, 5);
                            try {
                                BooksBUS booksList = new BooksBUS();
                                StationeriesBUS staList = new StationeriesBUS();
                                switch (productChoice) {
                                    case 1:
                                        booksList.readFile();
                                        booksList.showList();
                                        break;
                                    case 2:
                                        staList.readFile();
                                        staList.showList();
                                        break;
                                    case 3:
                                        System.out.printf("| %s %s %s |\n", "I.Books", "-".repeat(20),
                                                "II.Stationeries");
                                        do {
                                            System.out.print("Choose option (1 or 2): ");
                                            option = input.nextLine().trim();
                                            productChoice = Validate.parseChooseHandler(option, 2);
                                        } while (productChoice == -1);
                                        Products[] listProducts = customerBought(productChoice == 1 ? "Books" : "Stationeries");
                                        if (listProducts != null) {
                                            int length = listProducts.length;
                                            int[] quantity = new int[length - 1];
                                            // confirm again
                                            System.out.printf("| %s %s %s |\n", "I.Cancel", "-".repeat(20), "II.Buy");
                                            do {
                                                System.out.print("choose option (1 or 2) : ");
                                                option = input.nextLine().trim();
                                                choice = Validate.parseChooseHandler(option, 2);
                                            } while (choice == -1);
                                            System.out.println("*".repeat(60));
                                            if (choice == 1) {
                                                System.out.println("Ok");
                                                break;
                                            }
                                            // set quantity
                                            for (int i = 0; i < length; i++) {
                                                listProducts[i].showInfo();
                                                do {
                                                    System.out.print("set quantity : ");
                                                    String quantityInput = input.nextLine().trim();
                                                    quantity[i] = Validate.isNumber(quantityInput);
                                                    if (!Validate.checkQuantity(quantity[i])) {
                                                        System.out.println("Error quantity! What the **** are you cooking!");
                                                        quantity[i] = -1;
                                                    }
                                                } while (quantity[i] == -1);
                                            }
                                            // set bill
                                            BillBUS bills = new BillBUS();
                                            bills.readFile();
                                            Bill newBill = new Bill();
                                            newBill.setInfo();
                                            bills.add(newBill);
                                            bills.writeFile();
                                        }
                                        break;
                                    case 4:
                                        booksList.readFile();
                                        booksList.search();
                                        break;
                                    case 5:
                                        staList.readFile();
                                        staList.search();
                                        break;
                                }
                            } catch (Exception e) {
                                System.out.println("An error occurred: " + e.getMessage());
                            }
                        } while (true);
                }
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        } while (true);
    }

    private Products[] customerBought(String request) {
        int userChoice = 0, count = 0;
        Products[] listProducts = new Products[0];
        request = request.toLowerCase();
        try {
            if (request == "books") {
                BooksBUS booksList = new BooksBUS();
                booksList.readFile();
                System.out.println("Enter name of book: ");
                String name = input.nextLine().trim();
                // find books and show info
                Products[] list = booksList.relativeFind(name, "name");
                if (list != null) {
                    for (Products book : list)
                        book.showInfo();
                    // get user choice
                    System.out.println("-".repeat(60));
                    do {
                        System.out.print("choose multiple books (like 1, 2,etc...) : ");
                        String options = input.nextLine().trim();
                        String[] splitOptions = options.split(" ");

                        if (Validate.hasDuplicates(splitOptions)) {
                            System.out.println("has duplicate! please try again!");
                            continue;
                        }

                        for (String item : splitOptions) {
                            userChoice = Validate.parseChooseHandler(item, list.length);
                            if (userChoice == -1) {
                                count = 0;
                                break;
                            }
                            listProducts = Arrays.copyOf(listProducts, listProducts.length + 1);
                            listProducts[count] = list[userChoice];
                            count++;
                        }
                    } while (count == 0);
                }
            } else if (request == "stationeries") {
                StationeriesBUS staList = new StationeriesBUS();
                staList.readFile();
                System.out.println("Enter name of stationary: ");
                String name = input.nextLine().trim();
                // find books and show info
                Products[] list = staList.relativeFind(name, "name");
                if (list != null) {
                    for (Products stationary : list)
                        stationary.showInfo();
                    // get user choice
                    System.out.println("-".repeat(60));
                    do {
                        System.out.print("choose multiple stationeries (like 1, 2,etc...) : ");
                        String options = input.nextLine().trim();
                        String[] splitOptions = options.split(" ");

                        if (Validate.hasDuplicates(splitOptions)) {
                            System.out.println("has duplicate! please try again!");
                            continue;
                        }

                        for (String item : splitOptions) {
                            userChoice = Validate.parseChooseHandler(item, list.length);
                            if (userChoice == -1) {
                                count = 0;
                                break;
                            }
                            listProducts = Arrays.copyOf(listProducts, listProducts.length + 1);
                            listProducts[count] = list[userChoice];
                            count++;
                        }
                    } while (count == 0);
                }
            }
            return listProducts;
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            return null;
        }
    }

    // menu for warehouse keepers
    private void menuForWarehouseKeepers() {
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

}
