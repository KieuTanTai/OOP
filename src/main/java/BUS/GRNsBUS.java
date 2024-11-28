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
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Scanner;

import DTO.Employees;
import DTO.GRNs;
import DTO.Suppliers;
import Manager.Menu;
import util.Validate;

public class GRNsBUS implements IRuleSets {
     private GRNs[] grnList;
     private int count;
     private Scanner input = new Scanner(System.in);

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
          for (GRNs book : grnList)
               book.showInfo();
     }

     // find methods
     @Override
     public void find() {
          Menu.findHandler();
     }

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
               if (originalKey instanceof String) {
                    String key = (String) originalKey;

                    String employeeID = (grn.getEmployee() != null) ? grn.getEmployee().getPersonID() : "";
                    String employeeName = (grn.getEmployee() != null) ? grn.getEmployee().getFullName().toLowerCase()
                              : "";

                    String supplierID = (grn.getSupplier() != null) ? grn.getSupplier().getSupplierID() : "";
                    String supplierName = (grn.getSupplier() != null)
                              ? grn.getSupplier().getSupplierName().toLowerCase()
                              : "";

                    if (request.equals("employee")
                              && (employeeID.equals(key) || employeeName.contains(key.toLowerCase())))
                         flag = true;

                    else if (request.equals("supplier")
                              && (supplierID.equals(key) || supplierName.contains(key.toLowerCase())))
                         flag = true;
               } else if (originalKey instanceof LocalDate) {
                    if (request.equals("date") && grn.getDate().isEqual((LocalDate) originalKey))
                         flag = true;
               } else if (originalKey instanceof BigDecimal) {
                    if (request.equals("totalprice") && grn.getTotalPrice().compareTo((BigDecimal) originalKey) == 0)
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

     // search methods
     @Override
     public void search() {
          Menu.searchHandler();
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

     // add methods
     @Override
     public void add() {
          Menu.addHandler();
     }

     @Override
     public void add(Object newGRN) {
          if (newGRN instanceof GRNs) {
               GRNs grn = (GRNs) newGRN;
               grn.setGrnID(grn.getGrnID());
               grnList = Arrays.copyOf(grnList, grnList.length + 1);
               grnList[count] = grn;
               count++;
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
          Menu.editHandler();
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

     public void editTotalPrice(String grnID) {
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

               BigDecimal price;
               do {
                    System.out.print("set price (VND) : ");
                    String value = input.nextLine();
                    price = Validate.isBigDecimal(value);
               } while (price == null);
               grnList[index].setTotalPrice(price);
          }
     }

     // remove methods
     @Override
     public void remove() {
          Menu.removeHandler();
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
                    file.writeUTF(grn.getTotalPrice().setScale(0).toString());
               }
          } catch (Exception err) {
               System.out.printf("error writing file!\nt%s\n", err.getMessage());
          }
     }

     // read file
     public void readFile() throws IOException {
          File testFile = new File("src/main/resources/GRNs");
          if (testFile.length() == 0)
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

          } catch (Exception err) {
               System.out.printf("error reading file!\nt%s\n", err.getMessage());

          }
     }

}
