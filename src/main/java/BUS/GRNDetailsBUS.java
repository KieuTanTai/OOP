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

import DTO.GRNDetails;
import DTO.Products;
import util.Validate;

public class GRNDetailsBUS {
     private GRNDetails[] grnDetailsList;
     private int count;
     private final Scanner input = new Scanner(System.in);

     // *constructors (TEST DONE)
     public GRNDetailsBUS() {
          count = 0;
          grnDetailsList = new GRNDetails[0];
     }

     public GRNDetailsBUS(GRNDetails[] grnDetailsList, int count) {
          this.count = count;
          this.grnDetailsList = grnDetailsList;
     }

     // *getters / setters (TEST DONE)
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
          for (int i = 0; i < this.count; i++) {
               if ((grnDetailsList[i].getGrnID().equals(now.getGrnID())) &&
                         (grnDetailsList[i].getProduct().getProductID().equals(now.getProduct().getProductID())))
                    grnDetailsList[i] = newDetail;
               System.out.println(grnDetailsList[i].getGrnID());
          }
     }

     public void setGrnDetailsList(GRNDetails[] grnDetailsList) {
          this.grnDetailsList = grnDetailsList;
     }

     // all others methods like: add remove edit find....
     // show list
     public void showList() {
          System.out.println(grnDetailsList.length);
          if (grnDetailsList == null)
               return;
          showAsTable(grnDetailsList);
     }

     // find methods
     // strict find
     public int find(String grnID, String productID) {
          for (int i = 0; i < grnDetailsList.length; i++) {
               if (grnDetailsList[i].getGrnID().equals(grnID)
                         && grnDetailsList[i].getProduct().getProductID().equals(productID))
                    return i;
          }
          return -1;
     }

     // *relative find (TEST DONE)
     public GRNDetails[] relativeFind(String id) {
          int count = 0;
          GRNDetails[] list = new GRNDetails[0];
          for (GRNDetails detail : grnDetailsList) {
               if (detail.getGrnID().equals(id)) {
                    list = Arrays.copyOf(list, list.length + 1);
                    list[count] = detail;
                    count++;
               }
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

     // *add methods (TEST DONE)
     public void add(GRNDetails detail) {
          if (find(detail.getGrnID(), detail.getProduct().getProductID()) == -1) {
               grnDetailsList = Arrays.copyOf(grnDetailsList, grnDetailsList.length + 1);
               grnDetailsList[count] = detail;
               this.count++;
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

     // *edit method (TEST DONE)
     public void edit(String grnID) {
          GRNDetails[] list = relativeFind(grnID);
          if (list != null) {
               showAsTable(list);
               // let user decision they want to edit or not
               Products product = null;
               BigDecimal newPrice;
               int userChoose = 1, productChoose, newQuantity;
               do {
                    System.out.println("choose 0 to EXIST!");
                    System.out.print("choose detail you wanna edit (like 0, 1, 2,etc...): ");
                    String option = input.nextLine().trim();
                    if (option.equals("0")) {
                         System.out.println("Exit program.");
                         return;
                    }
                    userChoose = Validate.parseChooseHandler(option, list.length);
               } while (userChoose == -1);

               // let user decision they want to change now product to books or stationer
               System.out.printf("| %s %s %s |\n", "I.Book", "-".repeat(20), "II.Stationary");
               System.out.println("-".repeat(60));
               do {
                    System.out.print("choose product (1 or 2): ");
                    String option = input.nextLine().trim();
                    productChoose = Validate.parseChooseHandler(option, 2);
               } while (productChoose == -1);

               // show list of products (BLOCK TRY _ CATCH)
               try {
                    if (productChoose == 1) {
                         BooksBUS listBooks = new BooksBUS();
                         listBooks.readFile();
                         listBooks.showList();
                         product = getProductOnList(listBooks);
                    } else {
                         StationeriesBUS listStationeries = new StationeriesBUS();
                         listStationeries.readFile();
                         listStationeries.showList();
                         product = getProductOnList(listStationeries);
                    }
               } catch (Exception e) {
                    System.out.printf("Error reading file: %s\n", e.getMessage());
               }

               // get new quantity and calc new Price
               newQuantity = setQuantity();
               newPrice = setPrice();

               // calc methods setter
               setGrnDetail(list[userChoose - 1], new GRNDetails(grnID, product, newQuantity, newPrice));
          }
     }

     // some private methods for execute specific value
     private BigDecimal setPrice() {
          BigDecimal price;
          do {
               System.out.print("set price (VND) : ");
               String value = input.nextLine();
               price = Validate.isBigDecimal(value);
          } while (price == null);
          return price;
     }

     private int setQuantity() {
          int quantity;
          do {
               System.out.print("set quantity : ");
               String quantityInput = input.nextLine().trim();
               quantity = Validate.isNumber(quantityInput);
               if (!Validate.checkQuantity(quantity))
                    quantity = -1;
          } while (quantity == -1);
          return quantity;
     }

     private Products getProductOnList(Object list) {
          String productID;
          boolean isBook = list instanceof BooksBUS;
          boolean isStationary = list instanceof StationeriesBUS;
          do {
               System.out.print("product id: ");
               productID = input.nextLine().trim();

               // execute when want to receive new book
               if (productID.equalsIgnoreCase("new"))
                    break;

               if (isBook)
                    if (((BooksBUS) list).find(productID) == -1)
                         productID = "";
                    else if (isStationary)
                         if (((StationeriesBUS) list).find(productID) == -1)
                              productID = "";
          } while (productID.isEmpty());

          // return product
          Products product = null;
          if (isBook)
               product = ((BooksBUS) list).getBook(productID);
          else if (isStationary)
               product = ((StationeriesBUS) list).getStationary(productID);
          return product;
     }

     // *remove methods (TEST DONE)
     public void remove(String grnID, String productID) {
          int size = 0;
          GRNDetails[] reduceArray = new GRNDetails[0];
          for (GRNDetails grnDetails : grnDetailsList) {
               if (grnDetails.getGrnID().equals(grnID) && grnDetails.getProduct().getProductID().equals(productID))
                    continue;
               reduceArray = Arrays.copyOf(reduceArray, reduceArray.length + 1);
               reduceArray[size] = grnDetails;
               size++;
          }

          if (size == grnDetailsList.length) {
               System.out.println("not found any mid!");
               return;
          }
          setCount(size);
          setGrnDetailsList(reduceArray);
     }

     // *show as table methods (TEST DONE)
     public void showAsTable(GRNDetails[] list) {
          if (list == null)
               return;
          System.out.println("=".repeat(140));
          System.out.printf("| %-6s %-15s %-66s %-12s %-16s %-16s |\n",
                    "No.", "GRN ID", "Product", "Quantity", "Price (VND)", "Sub Total (VND)");
          System.out.println("=".repeat(140));
          for (int i = 0; i < list.length; i++) {
               if (i > 0)
                    System.out.println("|" + "-".repeat(138) + "|");
               System.out.printf("| %-6s %-15s %-66s %-12s %-16s %-16s |\n", i + 1, list[i].getGrnID(),
                         list[i].getProduct().getProductName(), list[i].getQuantity(),
                         Validate.formatPrice(list[i].getPrice()),
                         Validate.formatPrice(list[i].getSubTotal()));
          }
          System.out.println("=".repeat(140));
     }

     public void showAsTable(GRNDetails item) {
          if (item == null)
               return;
          System.out.println("=".repeat(140));
          System.out.printf("| %-6s %-15s %-66s %-12s %-16s %-16s |\n", "No.", "GRN ID", "Product", "quantity",
                    "Price", "Sub Total");
          System.out.println("=".repeat(140));
          System.out.printf("| %-6s %-15s %-66s %-12s %-16s %-16s |\n", 1, item.getGrnID(),
                    item.getProduct().getProductName(), item.getQuantity(), Validate.formatPrice(item.getPrice()),
                    Validate.formatPrice(item.getSubTotal()));
          System.out.println("=".repeat(140));

     }

     // execute file resources
     // *write file (TEST DONE)
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

     // *read file (TEST DONE)
     public void readFile() throws IOException {
          File testFile = new File("src/main/resources/GRNDetails");
          if (testFile.length() == 0 || !testFile.exists())
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
                    if (productID.startsWith("ST") && productID.endsWith("PD")) {
                         StationeriesBUS staList = new StationeriesBUS();
                         staList .readFile(); 
                         product = staList.getStationary(productID);
                    }
                    else if (productID.startsWith("BK") && productID.endsWith("PD")) {
                         BooksBUS bookList = new BooksBUS(); 
                         bookList.readFile();
                         product = bookList.getBook(productID);
                    }
                    else
                         product = null;
                    list[i] = new GRNDetails(grnID, product, quantity, price);
               }
               setCount(count);
               setGrnDetailsList(list);
          } catch (Exception e) {
               System.out.printf("Error reading file: %s\n", e.getMessage());
          }
     }
}
