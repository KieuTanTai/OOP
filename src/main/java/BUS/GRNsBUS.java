package BUS;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Scanner;

import DTO.Employees;
import DTO.GRNs;
import DTO.Suppliers;
import util.Validate;

public class GRNsBUS implements IRuleSets {
     private GRNs[] grnList;
     private int count;
     private final Scanner input = new Scanner(System.in);

     // constructors
     public GRNsBUS() {
          this.count = 0;
          grnList = new GRNs[0];
     }

     public GRNsBUS(GRNs[] grnList, int count) {
          this.count = count;
          this.grnList = grnList;
     }

     public GRNsBUS(GRNsBUS list) {
          this.grnList = list.grnList;
          this.count = list.count;
     }

     // getters / setters
     public GRNs[] getListGRN() {
          return grnList;
     }

     public GRNs getGRN(String grnID) {
          for (GRNs grn : grnList)
               if (grn.getGrnID().equals(grnID))
                    return grn;
          return null;
     }

     public int getCount() {
          return count;
     }

     public void setListGRN(GRNs[] grnList) {
          this.grnList = grnList;
     }

     public void setCount(int count) {
          this.count = count;
     }

     // all others methods like: add remove edit find show....
     // methods showList
     public void showList() {
          if (grnList == null)
          return;
          for (GRNs grn : grnList) {
               grn.showInfo();
          }
     }

     // find methods
     // strict find
     @Override
     public int find(String grnID) {
          for (int i = 0; i < grnList.length; i++)
               if (grnList[i].getGrnID().equals(grnID))
                    return i;
          System.out.println("your grn is not exist!");
          return -1;
     }

     // relative find
     public GRNs[] relativeFind(Object originalKey, String request) {
          int count = 0;
          boolean flag = false;
          GRNs[] resultArray = new GRNs[0];
          request = request.toLowerCase().trim();

          for (GRNs grn : grnList) {
               if (originalKey instanceof String key) {

                    String employeeID = (grn.getEmployee() != null) ? grn.getEmployee().getPersonID() : "";
                    String employeeName = (grn.getEmployee() != null) ? grn.getEmployee().getFullName().toLowerCase() : "";

                    String supplierID = (grn.getSupplier() != null) ? grn.getSupplier().getSupplierID() : "";
                    String supplierName = (grn.getSupplier() != null)
                              ? grn.getSupplier().getSupplierName().toLowerCase() : "";

                    if (request.equals("employee")
                              && (employeeID.equals(key) || employeeName.contains(key.toLowerCase())))
                         flag = true;

                    else if (request.equals("supplier")
                              && (supplierID.equals(key) || supplierName.contains(key.toLowerCase())))
                         flag = true;
               } else if (originalKey instanceof LocalDate) {
                    if (request.equals("date") && grn.getDate().isEqual((LocalDate) originalKey))
                         flag = true;
               }

               // Add matching GRN to result
               if (flag) {
                    resultArray = Arrays.copyOf(resultArray, resultArray.length + 1);
                    resultArray[count] = grn;
                    flag = false;
                    count++;
               }
          }

          if (count == 0) {
               System.out.println("not found any GRNs!");
               return null;
          }
          return resultArray;
     }

     // advanced finds
     public GRNs[] advancedFind(BigDecimal minPrice, BigDecimal maxPrice, String request) {
          request = request.toLowerCase().trim();
          if (minPrice.compareTo(BigDecimal.ZERO) < 0 || maxPrice.compareTo(BigDecimal.ZERO) < 0)
               return null;
          if (request.equals("range") && ((minPrice.compareTo(maxPrice) >= 0))) {
               System.out.println("error range!");
               return null;
          }

          int count = 0;
          boolean flag = false;
          GRNs[] grnList = new GRNs[0];
          for (GRNs grn : grnList) {
               BigDecimal productPrice = grn.getTotalPrice();

               if ((request.equals("min")) && (productPrice.compareTo(minPrice) >= 0))
                    flag = true;

               else if ((request.equals("max")) && (productPrice.compareTo(maxPrice) <= 0))
                    flag = true;

               else if (request.equals("range")
                         && ((productPrice.compareTo(minPrice) >= 0) && (productPrice.compareTo(maxPrice) <= 0)))
                    flag = true;

               if (flag) {
                    grnList = Arrays.copyOf(grnList, grnList.length + 1);
                    grnList[count] = grn;
                    flag = false;
                    count++;
               }
          }
          if (count == 0) {
               System.out.println("not found any books!");
               return null;
          }
          return grnList;
     }

     // search methods
     @Override
     public void search() {
          int choice;
          do {
               System.out.println("*".repeat(60));
               System.out.println("I. Strict search");
               System.out.println("II. Relative search");
               System.out.println("III. Advanced search");
               System.out.println("0. Exit");
               System.out.println("*".repeat(60));
               System.out.print("Enter your choice : ");
               String inputChoice = input.nextLine().trim();
               // validate if user choose 0
               if (inputChoice.equals("0")) {
                    System.out.println("Exit program.");
                    break;
               }
               choice = Validate.parseChooseHandler(inputChoice, 3);
               switch (choice) {
                    case 1:
                         System.out.print("Enter id of grn : ");
                         String userInput = input.nextLine().trim();
                         search(userInput);
                         break;
                    case 2:
                         caseRelativeSearch();
                         break;
                    case 3:
                         caseAdvancedSearch();
                         break;
               }
          } while (choice != 0);
     }

     private void caseRelativeSearch() {
          int choice, tempChoice;
          do {
               System.out.println("*".repeat(60));
               System.out.println("I. Search by Employee");
               System.out.println("II. Search by Supplier");
               System.out.println("III. Search by Date");
               System.out.println("0. Exit");
               System.out.println("*".repeat(60));
               System.out.print("Enter your choice : ");
               String inputChoice = input.nextLine().trim();
               // validate if user choose 0
               if (inputChoice.equals("0")) {
                    System.out.println("Exit program.");
                    break;
               }
               choice = Validate.parseChooseHandler(inputChoice, 3);
               switch (choice) {
                    case 1:
                         try {
                              EmployeesBUS list = new EmployeesBUS();
                              list.readFile();
                              Employees[] tempList = list.relativeFind("Warehouse Keeper", "role");
                              int length = tempList.length;
                              for (int i = 0; i < length; i++) {
                                   System.out.printf("%d : \n", i + 1);
                                   tempList[i].showInfo();
                              }
                              do {
                                   System.out.print("choose employee (like 1, 2,etc...) : ");
                                   String option = input.nextLine().trim();
                                   tempChoice = Validate.parseChooseHandler(option, tempList.length);
                              } while (tempChoice == -1);
                              relativeSearch(tempList[tempChoice - 1].getFullName(), "employee");
                              break;
                         } catch (IOException e) {
                              System.out.println("error reading file!\n" + e.getMessage());
                         }
                    case 2:
                         // show list for user choose
                         SuppliersBUS.showList();
                         System.out.println("-".repeat(60));
                         do {
                              System.out.print("choose supplier (like 1, 2,etc...) : ");
                              String option = input.nextLine().trim();
                              tempChoice = Validate.parseChooseHandler(option, SuppliersBUS.getCount());
                         } while (tempChoice == -1);
                         relativeFind(SuppliersBUS.getSupplierList()[tempChoice - 1].getSupplierName(), "supplier");
                         break;
                    case 3:
                         LocalDate date;
                         do {
                              System.out.print("Enter date (dd-mm-yyyy) : ");
                              String dateInput = input.nextLine().trim();
                              date = Validate.isCorrectDate(dateInput);
                         } while (date == null);
                         relativeSearch(date, "date");
                         break;
               }
          } while (choice != 0);
     }

     private void caseAdvancedSearch() {
          int choice;
          BigDecimal price;
          do {
               System.out.println("*".repeat(60));
               System.out.println("I. Search with min price");
               System.out.println("II. Search with max price");
               System.out.println("III. Search with range min to max price");
               System.out.println("0. Exit");
               System.out.println("*".repeat(60));
               System.out.print("Enter your choice : ");
               String inputChoice = input.nextLine().trim();
               // validate if user choose 0
               if (inputChoice.equals("0")) {
                    System.out.println("Exit program.");
                    break;
               }
               choice = Validate.parseChooseHandler(inputChoice, 3);
               switch (choice) {
                    case 1:
                    case 2:
                         do {
                              if (choice == 1)
                                   System.out.print("Enter min price (VND) : ");
                              else if (choice == 2)
                                   System.out.print("Enter max price (VND) : ");
                              String value = input.nextLine().trim();
                              price = Validate.isBigDecimal(value);
                         } while (price == null);

                         if (choice == 1)
                              advancedSearch(price, price, "min");
                         else if (choice == 2)
                              advancedSearch(price, price, "max");
                         break;
                    case 3:
                         BigDecimal maxPrice;
                         do {
                              System.out.print("Enter min price (VND) : ");
                              String value = input.nextLine().trim();
                              price = Validate.isBigDecimal(value);

                              System.out.print("Enter max price (VND) : ");
                              value = input.nextLine().trim();
                              maxPrice = Validate.isBigDecimal(value);
                         } while (price == null || maxPrice == null);
                         advancedSearch(price, maxPrice, "range");
                         break;
               }
          } while (choice != 0);
     }

     @Override
     // strict search
     public void search(String grnID) {
          int index = find(grnID);
          if (index != -1)
               grnList[index].showInfo();
     }

     // relative search
     public void relativeSearch(Object key, String request) {
          GRNs[] list = relativeFind(key, request);
          if (list != null)
               for (GRNs grn : list)
                    grn.showInfo();
     }

     // advanced search
     public void advancedSearch(Object keyI, Object timeOrKey, String request) {
          GRNs[] list = advancedFind((BigDecimal) keyI, (BigDecimal) timeOrKey, request);
          if (list != null)
               for (GRNs grn : list)
                    grn.showInfo();

     }

     // add methods
     @Override
     public void add() {
          int choice;
          do {
               System.out.println("*".repeat(60));
               System.out.println("I. Add grn");
               System.out.println("II. Add list of grn");
               System.out.println("0. Exit");
               System.out.println("*".repeat(60));
               System.out.print("Enter your choice : ");
               String inputChoice = input.nextLine().trim();
               // validate if user choose 0
               if (inputChoice.equals("0")) {
                    System.out.println("Exit program.");
                    break;
               }
               choice = Validate.parseChooseHandler(inputChoice, 2);
               // try catch for execute file after add
               try {
                    switch (choice) {
                         case 1:
                              GRNs newGrNs = new GRNs();
                              newGrNs.setInfo();
                              // confirm
                              System.out.printf("| %s %s %s |\n", "I.Cancel", "-".repeat(20), "II.Add");
                              do {
                                   System.out.print("choose option (1 or 2) : ");
                                   String option = input.nextLine().trim();
                                   choice = Validate.parseChooseHandler(option, 2);
                              } while (choice == -1);
                              if (choice == 1)
                                   break;
                              add(newGrNs);
                              writeFile();
                              break;
                         case 2:
                              int count = 0;
                              GRNs[] list = new GRNs[0];
                              do {
                                   System.out.print("Enter total grn you wanna add : ");
                                   String option = input.nextLine().trim();
                                   choice = Validate.isNumber(option);
                              } while (choice == -1);
                              // for loop with input time
                              for (int i = 0; i < choice; i++) {
                                   GRNs grn = new GRNs();
                                   grn.setInfo();
                                   list = Arrays.copyOf(list, list.length + 1);
                                   list[count] = grn;
                                   count++;
                              }

                              // confirm
                              System.out.printf("| %s %s %s |\n", "I.Cancel", "-".repeat(20), "II.Add");
                              do {
                                   System.out.print("choose option (1 or 2) : ");
                                   String option = input.nextLine().trim();
                                   choice = Validate.parseChooseHandler(option, 2);
                              } while (choice == -1);
                              if (choice == 1)
                                   break;
                              add(list);
                              writeFile();
                              break;
                    }
               } catch (Exception e) {
                    System.out.printf("error writing file!\nt%s\n", e.getMessage());
               }
          } while (choice != 0);
     }

     @Override
     public void add(Object newGRN) {
          if (newGRN instanceof GRNs grn) {
               grn.setGrnID(grn.getGrnID());
               grnList = Arrays.copyOf(grnList, grnList.length + 1);
               grnList[count] = grn;
               this.count++;
          } else
               System.out.println("your new book have something not like book!");
     }

     public void add(GRNs[] newGRN) {
          int tempIndex = 0, newListLength = newGRN.length;
          int initCount = this.getCount();
          int total = initCount + newListLength;
          grnList = Arrays.copyOf(grnList, grnList.length + newListLength);

          for (int i = initCount; i < total; i++, tempIndex++) {
               newGRN[tempIndex].setGrnID(newGRN[tempIndex].getGrnID());
               grnList[i] = newGRN[tempIndex];
          }
          this.count = total;
     }

     // edit methods
     @Override
     public void edit() {
          int choice;
          do {
               System.out.println("*".repeat(60));
               System.out.println("I. Edit date");
               System.out.println("II. Edit employee");
               System.out.println("III. Edit customer");
               System.out.println("IV. Edit Detail");
               System.out.println("0. Exit");
               System.out.println("*".repeat(60));
               System.out.print("Enter your choice : ");
               String inputChoice = input.nextLine().trim();
               // validate if user choose 0
               if (inputChoice.equals("0")) {
                    System.out.println("Exit program.");
                    break;
               }

               choice = Validate.parseChooseHandler(inputChoice, 4);
               System.out.print("Enter name or id of grn : ");
               String userInput = input.nextLine().trim();

               // if case
               try {
                    if (choice == 1)
                         edit(userInput);
                    else if (choice == 2)
                         editEmployee(userInput);
                    else if (choice == 3)
                         editSupplier(userInput);
                    else if (choice == 4)
                         editDetail(userInput);
                    // update file
                    writeFile();
               } catch (Exception e) {
                    System.out.printf("error writing file!\nt%s\n", e.getMessage());
               }
          } while (true);
     }

     @Override
     public void edit(String grnID) {
          int index = find(grnID);
          if (index != -1) {
               LocalDate date;
               int userChoose;
               // show list for user choose
               grnList[index].showInfo();
               System.out.printf("| %s %s %s |\n", "I.Cancel", "-".repeat(20), "II.Edit");
               do {
                    System.out.print("choose option (1 or 2) : ");
                    String option = input.nextLine().trim();
                    userChoose = Validate.parseChooseHandler(option, 2);
               } while (userChoose == -1);
               if (userChoose == 1)
                    return;

               do {
                    System.out.print("new date : ");
                    String dateInput = input.nextLine().trim();
                    date = Validate.isCorrectDate(dateInput);
               } while (date == null);
               grnList[index].setDate(date);
          }
     }

     public void editEmployee(String grnID) {
          int index = find(grnID);
          if (index != -1) {
               try {
                    int userChoose;
                    grnList[index].showInfo();
                    System.out.printf("| %s %s %s |\n", "I.Cancel", "-".repeat(20), "II.Edit");
                    do {
                         System.out.print("choose option (1 or 2) : ");
                         String option = input.nextLine().trim();
                         userChoose = Validate.parseChooseHandler(option, 2);
                    } while (userChoose == -1);
                    if (userChoose == 1)
                         return;

                    EmployeesBUS list = new EmployeesBUS();
                    list.readFile();
                    System.out.println("-".repeat(60));
                    do {
                         System.out.print("name employee : ");
                         String name = input.nextLine().trim();
                         userChoose = list.find(name);
                    } while (userChoose == -1);

                    Employees employees = list.getEmployeesList()[userChoose];
                    grnList[index].setEmployee(employees);
               } catch (IOException e) {
                    System.out.println("error reading file!\n" + e.getMessage());
               }

          }
     }

     public void editSupplier(String grnID) {
          int index = find(grnID);
          if (index != -1) {
               int userChoose;
               grnList[index].showInfo();
               System.out.printf("| %s %s %s |\n", "I.Cancel", "-".repeat(20), "II.Edit");
               do {
                    System.out.print("choose option (1 or 2) : ");
                    String option = input.nextLine().trim();
                    userChoose = Validate.parseChooseHandler(option, 2);
               } while (userChoose == -1);
               if (userChoose == 1)
                    return;

               // show list for user choose
               SuppliersBUS.showList();
               if (SuppliersBUS.getCount() == 0) // if not have any supplier
                    return;
               System.out.println("-".repeat(60));
               do {
                    System.out.print("choose supplier (like 1, 2,etc...) : ");
                    String option = input.nextLine().trim();
                    userChoose = Validate.parseChooseHandler(option, SuppliersBUS.getCount());
               } while (userChoose == -1);

               Suppliers supplier = SuppliersBUS.getSupplierList()[userChoose - 1];
               grnList[index].setSupplier(supplier);
          }
     }

     public void editDetail (String grnID) {
          try {
               GRNDetailsBUS listDetails = new GRNDetailsBUS();
               listDetails.readFile();
               listDetails.edit(grnID);
               listDetails.writeFile();
          } catch (Exception e) {
               System.out.printf("error reading file!\nt%s\n", e.getMessage());
          }

     }
     // remove methods
     @Override
     public void remove() {
          int choice;
          do {
               System.out.println("*".repeat(60));
               System.out.println("I. Remove");
               System.out.println("0. Exit");
               System.out.println("*".repeat(60));
               System.out.print("Enter your choice : ");
               String inputChoice = input.nextLine().trim();
               // validate if user choose 0
               if (inputChoice.equals("0")) {
                    System.out.println("Exit program.");
                    break;
               }
               choice = Validate.parseChooseHandler(inputChoice, 1);
               try {
                    System.out.print("Enter name or id of grn : ");
                    String userInput = input.nextLine().trim();
                    remove(userInput);
                    writeFile();
               } catch (Exception e) {
                    System.out.printf("error writing file!\nt%s\n", e.getMessage());
               }
          } while (choice != 0);
     }

     @Override
     public void remove(String nameOrID) {
          int index = find(nameOrID);
          if (index != -1) {
               for (int i = index; i < grnList.length - 1; i++)
                    grnList[i] = grnList[i + 1];
               grnList = Arrays.copyOf(grnList, grnList.length - 1);
               count--;
          }
     }

     // execute file resources
     // write file
     public void writeFile() throws IOException {
          try (DataOutputStream file = new DataOutputStream(
                    new BufferedOutputStream(new FileOutputStream("src/main/resources/GRNs", false)))) {
               file.writeInt(count);
               for (GRNs grn : grnList) {
                    file.writeUTF(grn.getGrnID());
                    file.writeUTF(grn.getDate().toString());
                    file.writeUTF(grn.getEmployee().getPersonID());
                    file.writeUTF(grn.getSupplier().getSupplierID());
                    file.writeUTF(grn.getTotalPrice().setScale(0, RoundingMode.UNNECESSARY).toString());
               }
          } catch (Exception err) {
               System.out.printf("error writing file!\nt%s\n", err.getMessage());
          }
     }

     // read file
     public void readFile() throws IOException {
          File testFile = new File("src/main/resources/GRNs");
          if (testFile.length() == 0 || !testFile.exists())
               return;

          try (DataInputStream file = new DataInputStream(
                    new BufferedInputStream(new FileInputStream("src/main/resources/GRNs")))) {
               int count = file.readInt();
               GRNs[] list = new GRNs[count];
               for (int i = 0; i < count; i++) {
                    String grnID = file.readUTF();
                    LocalDate date = LocalDate.parse(file.readUTF());
                    String employeeID = file.readUTF();
                    String supplierID = file.readUTF();
                    BigDecimal totalPrice = new BigDecimal(file.readUTF());

                    // execute ID
                    EmployeesBUS employeesList = new EmployeesBUS();
                    employeesList.readFile();
                    Employees employee = employeesList.getEmployee(employeeID);
                    Suppliers supplier = SuppliersBUS.getSupplier(supplierID);
                    list[i] = new GRNs(grnID, date, employee, supplier, totalPrice);
               }
               setCount(count);
               setListGRN(list);
          } catch (Exception err) {
               System.out.printf("error reading file!\nt%s\n", err.getMessage());
          }
     }

}
