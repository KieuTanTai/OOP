package BUS;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
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

     @Override
     public int find(String grnID) {
          for (int i = 0; i < grnList.length; i++)
               if (grnList[i].getGrnID().equals(grnID))
                    return i;
          System.out.println("your grn is not exist!");
          return -1;
     }

     // search methods
     @Override
     public void search() {
          Menu.searchHandler();
     }

     @Override
     public void search(String grnID) {
          int index = find(grnID);
          if (index != -1)
               grnList[index].showInfo();
     }

     // add methods
     @Override
     public void add() {
          Menu.addHandler();
     }

     @Override
     public void add(Object newGRN) {
          if (newGRN instanceof GRNs) {
               grnList = Arrays.copyOf(grnList, grnList.length + 1);
               grnList[count] = (GRNs) newGRN;
               count++;
          } else
               System.out.println("your new book have something not like book!");
     }

     public void add(GRNs[] newGRN, int size) {
          int tempIndex = 0;
          int initCount = this.getCount();
          int total = initCount + size;
          grnList = Arrays.copyOf(grnList, grnList.length + newGRN.length);

          for (int i = initCount; i < total; i++, tempIndex++)
               grnList[i] = newGRN[tempIndex];
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
                    System.out.print("choose option (like 1, 2,etc...): ");
                    String option = input.nextLine().trim();
                    userChoose = Validate.parseChooseHandler(option, 2);
               } while (userChoose == -1);
               if (userChoose == 1)
                    return;

               do {
                    System.out.print("new release date : ");
                    String dateInput = input.nextLine().trim();
                    date = Validate.isCorrectDate(dateInput);
               } while (date == null);
               grnList[index].setDate(date);
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
          try (DataOutputStream file = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("src/main/resources/GRNs", false)))) {
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
          try (DataInputStream file = new DataInputStream(new BufferedInputStream(new FileInputStream("src/main/resources/GRNs")))) {
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
