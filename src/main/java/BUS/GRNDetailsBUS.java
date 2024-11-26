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
import java.util.Arrays;
import java.util.Scanner;

import DTO.Books;
import DTO.GRNDetails;
import DTO.Products;
import DTO.Stationeries;
import util.Validate;

public class GRNDetailsBUS {
     private GRNDetails[] grnDetailsList;
     private int count;
     private Scanner input = new Scanner(System.in);

     // constructors
     public GRNDetailsBUS() {
          count = 0;
          grnDetailsList = new GRNDetails[0];
     }

     public GRNDetailsBUS(GRNDetails[] grnDetailsList, int count) {
          this.count = count;
          this.grnDetailsList = grnDetailsList;
     }

     // getters / setters
     public int getCount() {
          return count;
     }

     public GRNDetails[] getGrnDetailsList() {
          return this.grnDetailsList;
     }

     public GRNDetails getGRNDetail(String grnID) {
          for (GRNDetails grnDetail : grnDetailsList)
               if (grnDetail.getGrnID().equals(grnID))
                    return grnDetail;
          return null;
     }

     public void setCount(int count) {
          this.count = count;
     }

     public void setGrnDetail(GRNDetails now, GRNDetails newDetail) {
          for (GRNDetails detail : grnDetailsList)
               if ((detail.getGrnID().equals(now.getGrnID())))
                    detail = newDetail;
     }

     public void setGrnDetailsList(GRNDetails[] grnDetailsList) {
          this.grnDetailsList = grnDetailsList;
     }

     // all others methods like: add remove edit find....
     // show list
     public void showList() {
          if (grnDetailsList == null)
               return;
          showAsTable(grnDetailsList);
     }

     // find methods
     // strict find
     public int find(String grnID, String productID) {
          for (int i = 0; i < grnDetailsList.length; i++) {
               if (grnDetailsList[i].getGrnID().equals(grnID))
                    return i;
          }
          return -1;
     }

     // relative find
     public GRNDetails[] relativeFind(String id) {
          int count = 0;
          GRNDetails[] list = new GRNDetails[0];
          for (GRNDetails detail : grnDetailsList)
               if (detail.getGrnID().equals(id)) {
                    list = Arrays.copyOf(list, list.length + 1);
                    list[count] = detail;
                    count++;
               }
          if (count == 0) {
               System.out.println("not found any grn detail!");
               return null;
          }
          return list;
     }

     // search methods
     // strict search
     public void search(String grnID, String productID) {
          int index = find(grnID, productID);
          if (index == -1) {
               System.out.println("404 not found!");
               return;
          }
          showAsTable(grnDetailsList[index]);
     }

     // relative search
     public void relativeSearch(String id) {
          GRNDetails[] list = relativeFind(id);
          if (list != null)
               showAsTable(list);
     }

     // add methods
     public void add(GRNDetails detail) {
          if (getGRNDetail(detail.getGrnID()) == null) {
               grnDetailsList = Arrays.copyOf(grnDetailsList, grnDetailsList.length + 1);
               grnDetailsList[count] = detail;
               count++;
          }
     }

     public void add(GRNDetails[] details) {
          int tempIndex = 0, newListLength = details.length;
          int initCount = getCount();
          int total = initCount + newListLength;
          grnDetailsList = Arrays.copyOf(grnDetailsList, grnDetailsList.length + newListLength);

          for (int i = initCount; i < total; i++, tempIndex++)
               grnDetailsList[i] = details[tempIndex];
          this.count = total;
     }

     // edit method
     public void edit(String grnID) {
          GRNDetails[] list = relativeFind(grnID);
          if (list != null) {
               int count = list.length;
               // show all grn detail has been found for user
               for (int i = 0; i < count; i++)
                    System.out.printf("| \t%-20s %-21s %-21s %-21s %-21s %-21s |\n", 1, list[i].getGrnID(),
                              list[i].getProduct().getProductName(), list[i].getQuantity(), list[i].getPrice(),
                              list[i].getSubTotal());
               System.out.println("-".repeat(60));

               // let user decision they wanna edit or not
               int userChoose, productChoose, newQuantity;
               BigDecimal newPrice;
               do {
                    System.out.print("choose detail you wanna edit (like 1, 2,etc...): ");
                    String option = input.nextLine().trim();
                    userChoose = Validate.parseChooseHandler(option, list.length);
               } while (userChoose == -1);

               // let user decision they wanna change now product to books or stationeries
               System.out.printf("| %s %s %s |\n", "I.Books", "-".repeat(20), "II.Stationeries");
               System.out.println("-".repeat(60));
               do {
                    System.out.print("choose product (1 or 2): ");
                    String option = input.nextLine().trim();
                    productChoose = Validate.parseChooseHandler(option, 2);
               } while (productChoose == -1);

               // let new quantity
               System.out.println("-".repeat(60));
               do {
                    System.out.print("set quantity : ");
                    String quantityInput = input.nextLine().trim();
                    newQuantity = Validate.isNumber(quantityInput);
               } while (newQuantity == -1);

               // let new price
               System.out.println("-".repeat(60));
               do {
                    System.out.print("set price (VND) : ");
                    String value = input.nextLine();
                    newPrice = Validate.isBigDecimal(value);
               } while (newPrice == null);

               // try catch for execute product that user had been chosen
               System.out.println("-".repeat(60));
               try {
                    GRNDetails newGrnDetails = null;
                    if (productChoose == 1) {
                         int tempChoose;
                         BooksBUS bookList = new BooksBUS();
                         bookList.readFile();
                         Books book = new Books();
                         book.setInfo();
                         newGrnDetails = new GRNDetails(grnID, book, newQuantity, newPrice);

                         System.out.printf("| %s %s %s |\n", "I.Cancel", "-".repeat(20), "II.Submit");
                         do {
                              System.out.print("choose option (1 or 2) : ");
                              String option = input.nextLine().trim();
                              tempChoose = Validate.parseChooseHandler(option, list.length);
                         } while (tempChoose == -1);

                         if (tempChoose == 1)
                              return;
                         bookList.add(book);
                         bookList.writeFile();
                    } else {
                         int tempChoose;
                         StationeriesBUS staList = new StationeriesBUS();
                         staList.readFile();
                         Stationeries stationary = new Stationeries();
                         stationary.setInfo();
                         newGrnDetails = new GRNDetails(grnID, stationary, newQuantity, newPrice);

                         System.out.printf("| %s %s %s |\n", "I.Cancel", "-".repeat(20), "II.Submit");
                         do {
                              System.out.print("choose option (1 or 2) : ");
                              String option = input.nextLine().trim();
                              tempChoose = Validate.parseChooseHandler(option, list.length);
                         } while (tempChoose == -1);
                         if (tempChoose == 1)
                              return;
                         staList.add(stationary);
                         staList.writeFile();
                    }

                    setGrnDetail(list[userChoose - 1], newGrnDetails);
               } catch (Exception e) {
                    System.out.println("error reading file!\n" + e.getMessage());
               }
          }
     }

     // remove methods
     public void remove(String grnID) {
          int size = 0;
          GRNDetails[] reduceArray = new GRNDetails[0];
          for (int i = 0; i < grnDetailsList.length; i++) {
               if (grnDetailsList[i].getGrnID().equals(grnID))
                    continue;
               reduceArray = Arrays.copyOf(reduceArray, reduceArray.length + 1);
               reduceArray[size] = grnDetailsList[i];
               size++;
          }

          if (size == grnDetailsList.length) {
               System.out.println("not found any mid!");
               return;
          }

          setCount(size);
          setGrnDetailsList(reduceArray);
     }

     // show as table methods
     public void showAsTable(GRNDetails[] list) {
          if (list == null)
               return;
          System.out.println("=".repeat(180));
          System.out.printf("| \t%-20s %-21s %-21s %-21s %-21s %-21s |\n", "No.", "GRN ID", "Product", "quantity",
                    "Price", "Sub Total");
          System.out.println("=".repeat(180));
          for (int i = 0; i < list.length; i++) {
               if (i > 0)
                    System.out.println("|" + "-".repeat(178) + "|");
               System.out.printf("| \t%-20s %-21s %-21s %-21s %-21s %-21s |\n", i + 1, list[i].getGrnID(),
                         list[i].getProduct().getProductName(), list[i].getQuantity(), list[i].getPrice(),
                         list[i].getSubTotal());
          }
          System.out.println("=".repeat(180));
     }

     public void showAsTable(GRNDetails item) {
          if (item == null)
               return;
          System.out.println("=".repeat(180));
          System.out.printf("| \t%-20s %-21s %-21s %-21s %-21s %-21s |\n", "No.", "GRN ID", "Product", "quantity",
                    "Price", "Sub Total");
          System.out.println("=".repeat(180));
          System.out.printf("| \t%-20s %-21s %-21s %-21s %-21s %-21s |\n", 1, item.getGrnID(),
                    item.getProduct().getProductName(), item.getQuantity(), item.getPrice(), item.getSubTotal());
          System.out.println("=".repeat(180));

     }

     // execute file resources
     // write file
     public void writeFile() throws IOException {
          try (DataOutputStream file = new DataOutputStream(
                    new BufferedOutputStream(new FileOutputStream("src/main/resources/GRNDetails", false)))) {
               file.writeInt(count);
               for (GRNDetails detail : grnDetailsList) {
                    file.writeUTF(detail.getGrnID());
                    file.writeUTF(detail.getProduct().getProductID());
                    file.writeInt(detail.getQuantity());
                    file.writeUTF(detail.getPrice().toString());
               }
          } catch (Exception e) {
               System.out.printf("Error writing file: %s\n", e.getMessage());
          }
     }

     // read file
     public void readFile() throws IOException {
          File testFile = new File("src/main/resources/GRNDetails");
          if (testFile.length() == 0)
               return;

          try (DataInputStream file = new DataInputStream(
                    new BufferedInputStream(new FileInputStream("src/main/resources/GRNDetails")))) {
               count = file.readInt();
               GRNDetails[] list = new GRNDetails[count];
               for (int i = 0; i < count; i++) {
                    String grnID = file.readUTF();
                    String productID = file.readUTF();
                    int quantity = file.readInt();
                    BigDecimal price = new BigDecimal(file.readUTF());

                    // execute id
                    Products product;
                    if (productID.startsWith("ST") && productID.endsWith("PD"))
                         product = new StationeriesBUS().getStationary(productID);
                    else if (productID.startsWith("BK") && productID.endsWith("PD"))
                         product = new BooksBUS().getBook(productID);
                    else
                         product = null;
                    list[i] = new GRNDetails(grnID, product, quantity, price);
               }
               setGrnDetailsList(list);
          } catch (Exception e) {
               System.out.printf("Error reading file: %s\n", e.getMessage());
          }
     }
}
