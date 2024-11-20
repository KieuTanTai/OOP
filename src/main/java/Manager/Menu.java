package Manager;

import java.util.Scanner;

import BUS.*;
import DTO.*;
import util.Validate;

public class Menu {
    static Scanner input = new Scanner(System.in);

    public Menu() {
        // create static fields
        new TypesBUS();
        new GenresBUS();
        new StaTypesBUS();
        new PublishersBUS();
        new MidForBooksBUS();

        // methods handler();
        addHandler();
        findHandler();
        searchHandler();
        removeHandler();
        editHandler();

    }

    public static void mainHandler(){
        int choice;
        do {
            System.out.println("1. Add new");
            System.out.println("2. Search");
            System.out.println("3. Remove");
            System.out.println("4. Edit");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = Validate.parseChooseHandler(input.nextLine(), 5);
            switch (choice){
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
                case 0:
                    System.out.println("Exit program.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }while (choice != 0);
    }
    /*----- methods for only Books -----*/

    public static void addHandler() {
        int choice;
        do {
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
            System.out.print("Enter your choice: ");
            choice = Validate.parseChooseHandler(input.nextLine(), 12);
            break;
        }while (choice == -1);
        switch (choice){
                case 1:
                    do {
                        System.out.println("1. Add new book");
                        System.out.println("2. Add new stationery");
                        System.out.println("0. Exit");
                        System.out.print("Enter your choice: ");
                        choice = Validate.parseChooseHandler(input.nextLine(), 3);
                        Products product;
                        switch (choice){
                            case 1:
                                BooksBUS arrBook = new BooksBUS();
                                product = new Books();
                                product.setInfo();
                                arrBook.add(product);
                                break;
                            case 2:
                                StationeriesBUS arrSta = new StationeriesBUS();
                                product = new Stationeries();
                                product.setInfo();
                                arrSta.add(product);
                                break;
                            case 0:
                                System.out.println("Exit program.");
                                break;
                            default:
                                System.out.println("Invalid choice. Please try again.");
                        }
                    }while (true);
                case 2:
                    EmployeesBUS arrEmp = new EmployeesBUS();
                    Employees employees = new Employees();
                    employees.setInfo();
                    arrEmp.add(employees);
                    break;
                case 3:
                    CustomersBUS arrCus = new CustomersBUS();
                    Customers customers = new Customers();
                    customers.setInfo();
                    arrCus.add(customers);
                    break;
                case 4:
                    SuppliersBUS arrSup = new SuppliersBUS();
                    Suppliers suppliers = new Suppliers();
                    arrSup.add(suppliers);
                    break;
                case 5:
                    GRNsBUS arrGRN = new GRNsBUS();
                    GRNs grns = new GRNs();
                    grns.setInfo();
                    arrGRN.add(grns);
                    break;
                case 6:
                    // BillsBUS arrBill = new BillsBUS();
                    // Bills bills = new Bills();
                    // bills.setInfo();
                    // arrBill.add(bills);
                    break;
                case 7:
                    PublishersBUS arrPub = new PublishersBUS();
                    Publishers publishers = new Publishers();
//                    publishers.setInfo();
                    arrPub.add(publishers);
                    break;
                case 8:
                    TypesBUS arrType = new TypesBUS();
                    BookTypes bookTypes = new BookTypes();
//                    bookTypes.setInfo();
                    arrType.add(bookTypes);
                    break;
                case 9:
                    GenresBUS arrGenre = new GenresBUS();
                    BookGenres bookGenres = new BookGenres();
//                    bookGenres.setInfo();
                    arrGenre.add(bookGenres);
                    break;
                case 10:
                    StaTypesBUS arrStaType = new StaTypesBUS();
                    StaTypes staTypes = new StaTypes();
//                    staTypes.setInfo();
                    arrStaType.add(staTypes);
                    break;
                case 0:
                    System.out.println("Exit program.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

    }

    public static void findHandler() {
    }

    public static void searchHandler() {
        int choice;
        do {
            System.out.println("1. Search book");
            System.out.println("2. Search stationery");
            System.out.println("3. Search employee");
            System.out.println("4. Search customer");
            System.out.println("5. Search supplier");
            System.out.println("6. Search GRN");
            System.out.println("7. Search Bill");
            System.out.println("8. Search publisher");
            System.out.println("9. Search BookTypes");
            System.out.println("10. Search Genre");
            System.out.println("11. Search StaTypes");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = Validate.parseChooseHandler(input.nextLine().trim(),12);
            switch (choice){
                case 1:
                    BooksBUS arrBook = new BooksBUS();
                    arrBook.search();
                    break;
                case 2:
                    StationeriesBUS arrSta = new StationeriesBUS();
                    arrSta.search();
                    break;
                case 3:
                    EmployeesBUS arrEmp = new EmployeesBUS();
                    arrEmp.search();
                    break;
                case 4:
                    CustomersBUS arrCus = new CustomersBUS();
                    arrCus.search();
                    break;
                case 5:
                    SuppliersBUS arrSup = new SuppliersBUS();
                    arrSup.search();
                    break;
                case 6:
                    GRNsBUS arrGRN = new GRNsBUS();
                    arrGRN.search();
                    break;
                case 7:
                    // BillsBUS arrBill = new BillsBUS();
                    break;
                case 8:
                    PublishersBUS arrPub = new PublishersBUS();
                    arrPub.search();
                    break;
                case 9:
                    TypesBUS arrType = new TypesBUS();
                    arrType.search();
                    break;
                case 10:
                    GenresBUS arrGenre = new GenresBUS();
                    arrGenre.search();
                    break;
                case 11:
                    StaTypesBUS arrStaType = new StaTypesBUS();
                    arrStaType.search();
                    break;
                case 0:
                    System.out.println("Exit program.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }while (choice != 0);
    }

    public static void removeHandler() {
        int choice;
        do {
            System.out.println("1. Remove book");
            System.out.println("2. Remove stationery");
            System.out.println("3. Remove employee");
            System.out.println("4. Remove customer");
            System.out.println("5. Remove supplier");
            System.out.println("6. Remove publisher");
            System.out.println("7. Remove BookTypes");
            System.out.println("8. Remove Genre");
            System.out.println("9. Remove StaTypes");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = Validate.parseChooseHandler(input.nextLine().trim(), 10);
            switch (choice) {
                case 1:
                    BooksBUS arrBook = new BooksBUS();
                    arrBook.remove();
                    break;
                case 2:
                    StationeriesBUS arrSta = new StationeriesBUS();
                    arrSta.remove();
                    break;
                case 3:
                    EmployeesBUS arrEmp = new EmployeesBUS();
                    arrEmp.remove();
                    break;
                case 4:
                    CustomersBUS arrCus = new CustomersBUS();
                    arrCus.remove();
                    break;
                case 5:
                    SuppliersBUS arrSup = new SuppliersBUS();
                    arrSup.remove();
                    break;
                case 6:
                    PublishersBUS arrPub = new PublishersBUS();
                    arrPub.remove();
                    break;
                case 7:
                    TypesBUS arrType = new TypesBUS();
                    arrType.remove();
                    break;
                case 8:
                    GenresBUS arrGenre = new GenresBUS();
                    arrGenre.remove();
                    break;
                case 9:
                    StaTypesBUS arrStaType = new StaTypesBUS();
                    arrStaType.remove();
                    break;
                case 0:
                    System.out.println("Exit program.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);
    }

    public static void editHandler() {
        int choice;
        do {
            System.out.println("1. Edit book");
            System.out.println("2. Edit stationery");
            System.out.println("3. Edit employee");
            System.out.println("4. Edit customer");
            System.out.println("5. Edit supplier");
            System.out.println("6. Edit publisher");
            System.out.println("7. Edit BookTypes");
            System.out.println("8. Edit Genre");
            System.out.println("9. Edit StaTypes");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = Validate.parseChooseHandler(input.nextLine().trim(), 10);
            switch (choice) {
                case 1:
                    BooksBUS arrBook = new BooksBUS();
                    arrBook.showList();
                    System.out.print("Enter book ID to edit: ");
                    String bookID = input.nextLine().trim();
                    if (arrBook.find(bookID) != -1) {
                        int choice2;
                        do {
                            System.out.println("1.Edit name");
                            System.out.println("2.Edit price");
                            System.out.println("3.Edit release date");
                            System.out.println("4.Edit quantity");
                            System.out.println("5.Edit author");
                            System.out.println("6.Edit publisher");
                            System.out.println("7.Edit type");
                            System.out.println("8.Edit genre");
                            System.out.println("9.Edit format");
                            System.out.println("10.Edit book packagingSize");
                            System.out.println("0.Exit");
                            System.out.print("Enter your choice: ");
                            choice2 = Validate.parseChooseHandler(input.nextLine().trim(), 10);
                        } while (choice2 == -1);
                        switch (choice2) {
                            case 1:
                                arrBook.edit(bookID);
                                break;
                            case 2:
                                arrBook.editPrice(bookID);
                                break;
                            case 3:
                                arrBook.editReleaseDate(bookID);
                                break;
                            case 4:
                                arrBook.editQuantity(bookID);
                                break;
                            case 5:
                                arrBook.editAuthor(bookID);
                                break;
                            case 6:
                                arrBook.editPublisher(bookID);
                                break;
                            case 7:
                                arrBook.editType(bookID);
                                break;
                            case 8:
                                arrBook.editGenre(bookID);
                                break;
                            case 9:
                                arrBook.editFormat(bookID);
                                break;
                            case 10:
                                arrBook.editPackagingSize(bookID);
                                break;
                            case 0:
                                System.out.println("Exit program.");
                                break;
                            default:
                                System.out.println("Invalid choice. Please try again.");
                        }
                    } else {
                        System.out.println("Book ID not found.");
                    }
                    break;
                case 2:
                    StationeriesBUS arrSta = new StationeriesBUS();
                    arrSta.showList();
                    System.out.print("Enter stationery ID to edit: ");
                    String staID = input.nextLine().trim();

                    if (arrSta.find(staID) != -1) {
                        int choice2;
                        do {
                            System.out.println("1.Edit name");
                            System.out.println("2.Edit price");
                            System.out.println("3.Edit release date");
                            System.out.println("4.Edit quantity");
                            System.out.println("5.Edit staType");
                            System.out.println("6.Edit brand");
                            System.out.println("7.Edit source");
                            System.out.println("8.Edit material");
                            System.out.println("0.Exit");
                            System.out.print("Enter your choice: ");
                            choice2 = Validate.parseChooseHandler(input.nextLine().trim(), 7);
                        } while (choice2 == -1);
                        switch (choice2) {
                            case 1:
                                arrSta.edit(staID);
                                break;
                            case 2:
                                arrSta.editPrice(staID);
                                break;
                            case 3:
                                arrSta.editReleaseDate(staID);
                                break;
                            case 4:
                                arrSta.editQuantity(staID);
                                break;
                            case 5:
                                arrSta.editType(staID);
                                break;
                            case 6:
                                arrSta.editBrand(staID);
                                break;
                            case 7:
                                arrSta.editSource(staID);
                                break;
                            case 8:
                                arrSta.editMaterial(staID);
                                break;
                            case 0:
                                System.out.println("Exit program.");
                                break;
                            default:
                                System.out.println("Invalid choice. Please try again.");
                        }
                    }
                    break;
                case 3:
                    EmployeesBUS arrEmp = new EmployeesBUS();
                    arrEmp.showList();
                    System.out.print("Enter employee ID to edit: ");
                    String empID = input.nextLine().trim();
                    if (arrEmp.find(empID) != -1) {
                        int choice2;
                        do {
                            System.out.println("1.Edit name");
                            System.out.println("2.Edit phone");
                            System.out.println("3.Edit date of birth");
                            System.out.println("4.Edit role");
                            System.out.println("5.Edit status");
                            System.out.println("0.Exit");
                            System.out.print("Enter your choice: ");
                            choice2 = Validate.parseChooseHandler(input.nextLine().trim(), 5);
                        } while (choice2 == -1);
                        switch (choice2) {
                            case 1:
                                arrEmp.edit(empID);
                                break;
                            case 2:
                                arrEmp.editPhone(empID);
                                break;
                            case 3:
                                arrEmp.editDateOfBirth(empID);
                                break;
                            case 4:
                                arrEmp.editRole(empID);
                                break;
                            case 5:
                                arrEmp.editStatus(empID);
                                break;
                            case 0:
                                System.out.println("Exit program.");
                                break;
                            default:
                                System.out.println("Invalid choice. Please try again.");
                        }
                    }
                    arrEmp.edit();
                    break;
                case 4:
                    CustomersBUS arrCus = new CustomersBUS();
                    arrCus.showList();
                    System.out.print("Enter customer ID to edit: ");
                    String cusID = input.nextLine().trim();
                    if (arrCus.find(cusID) != -1) {
                        int choice2;
                        do {
                            System.out.println("1.Edit name");
                            System.out.println("2.Edit phone");
                            System.out.println("3.Edit date of birth");
                            System.out.println("4.Edit address");
                            System.out.println("5.Edit point");
                            System.out.println("0.Exit");
                            System.out.print("Enter your choice: ");
                            choice2 = Validate.parseChooseHandler(input.nextLine().trim(), 6);
                        } while (choice2 == -1);
                        switch (choice2) {
                            case 1:
                                arrCus.edit(cusID);
                                break;
                            case 2:
                                arrCus.editPhone(cusID);
                                break;
                            case 3:
                                arrCus.editDateOfBirth(cusID);
                                break;
                            case 4:
                                arrCus.editAddress(cusID);
                                break;
                            case 5:
                                arrCus.editPoint(cusID);
                                break;
                            case 0:
                                System.out.println("Exit program.");
                                break;
                            default:
                                System.out.println("Invalid choice. Please try again.");
                        }
                    }
                    break;
                case 5:
                    SuppliersBUS arrSup = new SuppliersBUS();
                    arrSup.showList();
                    System.out.print("Enter supplier ID to edit: ");
                    String supID = input.nextLine().trim();
                    if (arrSup.find(supID) != -1) {
                        int choice2;
                        do {
                            System.out.println("1.Edit name");
                            System.out.println("2.Edit phone");
                            System.out.println("0.Exit");
                            System.out.print("Enter your choice: ");
                            choice2 = Validate.parseChooseHandler(input.nextLine().trim(), 4);
                        } while (choice2 == -1);
                        switch (choice2) {
                            case 1:
                                arrSup.edit(supID);
                                break;
                            case 2:
                                arrSup.editPhone(supID);
                                break;
                            case 0:
                                System.out.println("Exit program.");
                                break;
                            default:
                                System.out.println("Invalid choice. Please try again.");
                        }
                    }
                    break;
                case 6:
                    PublishersBUS arrPub = new PublishersBUS();
                    arrPub.showList();
                    System.out.print("Enter publisher ID to edit: ");
                    String pubID = input.nextLine().trim();
                    if (arrPub.find(pubID) != -1) {
                        int choice2;
                        do {
                            System.out.println("1.Edit name");
                            System.out.println("0.Exit");
                            System.out.print("Enter your choice: ");
                            choice2 = Validate.parseChooseHandler(input.nextLine().trim(), 4);
                        } while (choice2 == -1);
                        if (choice2==1){
                            arrPub.edit(pubID);
                        }
                    }
                    break;
                case 7:
                    TypesBUS arrType = new TypesBUS();
                    arrType.showList();
                    System.out.print("Enter type ID to edit: ");
                    String typeID = input.nextLine().trim();
                    if (arrType.find(typeID) != -1) {
                        int choice2;
                        do {
                            System.out.println("1.Edit name");
                            System.out.println("0.Exit");
                            System.out.print("Enter your choice: ");
                            choice2 = Validate.parseChooseHandler(input.nextLine().trim(), 2);
                        } while (choice2 == -1);
                        if (choice2==1){
                            arrType.edit(typeID);
                        }
                    }
                    break;
                case 8:
                    GenresBUS arrGenre = new GenresBUS();
                    arrGenre.showList();
                    System.out.print("Enter genre ID to edit: ");
                    String genreID = input.nextLine().trim();
                    if (arrGenre.find(genreID) != -1) {
                        int choice2;
                        do {
                            System.out.println("1.Edit name");
                            System.out.println("0.Exit");
                            System.out.print("Enter your choice: ");
                            choice2 = Validate.parseChooseHandler(input.nextLine().trim(), 2);
                        } while (choice2 == -1);
                        if (choice2==1){
                            arrGenre.edit(genreID);
                        }
                    }
                    break;
                case 9:
                    StaTypesBUS arrStaType = new StaTypesBUS();
                    arrStaType.showList();
                    System.out.print("Enter staType ID to edit: ");
                    String staTypeID = input.nextLine().trim();
                    if (arrStaType.find(staTypeID) != -1) {
                        int choice2;
                        do {
                            System.out.println("1.Edit name");
                            System.out.print("Enter your choice: ");
                            choice2 = Validate.parseChooseHandler(input.nextLine().trim(), 2);
                        } while (choice2 == -1);
                        if (choice2==1){
                            arrStaType.edit(staTypeID);
                        }
                    }
                    break;
                case 0:
                    System.out.println("Exit program.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }while (choice != 0);
    }

}
