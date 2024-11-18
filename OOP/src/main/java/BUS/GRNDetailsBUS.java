package BUS;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;

import DTO.GRNDetails;
import DTO.Products;

public class GRNDetailsBUS {
     private static GRNDetails[] grnDetailsList;
     private static int count;

     // Constructors
     public GRNDetailsBUS() {
          count = 0;
          grnDetailsList = new GRNDetails[0];
     }

     public GRNDetailsBUS(GRNDetails[] grnDetailsList, int count) {
          GRNDetailsBUS.count = count;
          GRNDetailsBUS.grnDetailsList = grnDetailsList;
     }

     // Getter/Setter
     public static int getCount() {
          return count;
     }

     public static GRNDetails[] getGrnDetailsList() {
          return Arrays.copyOf(grnDetailsList, grnDetailsList.length);
     }

     public static GRNDetails getGRNDetail(String grnID) {
          for (GRNDetails grnDetail : grnDetailsList)
               if (grnDetail.getGrnID().equals(grnID))
                    return grnDetail;
          return null;
     }

     public void setCount(int count) {
          GRNDetailsBUS.count = count;
     }

     public void setGrnDetailsList(GRNDetails[] grnDetailsList) {
          GRNDetailsBUS.grnDetailsList = grnDetailsList;
     }

     // Add methods
     public void add(GRNDetails detail) {
          if (getGRNDetail(detail.getGrnID()) == null) {
               grnDetailsList = Arrays.copyOf(grnDetailsList, grnDetailsList.length + 1);
               grnDetailsList[count] = detail;
               count++;
          }
     }

     public void add(GRNDetails[] details) {
          for (GRNDetails detail : details) {
               if (getGRNDetail(detail.getGrnID()) == null) {
                    grnDetailsList = Arrays.copyOf(grnDetailsList, grnDetailsList.length + 1);
                    grnDetailsList[count] = detail;
                    count++;
               }
          }
     }

     // Remove methods
     public void remove(String grnID) {
          int index = find(grnID);
          if (index == -1) {
               System.out.println("404 not found!");
               return;
          }
          for (int i = index; i < count - 1; i++) {
               grnDetailsList[i] = grnDetailsList[i + 1];
          }
          grnDetailsList = Arrays.copyOf(grnDetailsList, grnDetailsList.length - 1);
          count--;
     }

     // Find methods
     public int find(String grnID) {
          for (int i = 0; i < grnDetailsList.length; i++) {
               if (grnDetailsList[i].getGrnID().equals(grnID))
                    return i;
          }
          return -1;
     }

     // Show methods
     public static void showList() {
          if (grnDetailsList == null)
               return;
          showAsTable(grnDetailsList);
     }

     public static void showAsTable(GRNDetails[] list) {
          if (list == null)
               return;
          System.out.println("=".repeat(80));
          System.out.printf("| %-10s | %-20s | %-10s | %-10s | %-10s |\n", "GRN ID", "Product", "Quantity", "Price",
                    "SubTotal");
          System.out.println("=".repeat(80));
          for (GRNDetails detail : list) {
               System.out.printf("| %-10s | %-20s | %-10d | %-10.2f | %-10.2f |\n",
                         detail.getGrnID(), detail.getProductList().getProductName(),
                         detail.getQuantity(), detail.getPrice(), detail.getSubTotal());
          }
          System.out.println("=".repeat(80));
     }

     // File handling
     public void writeFile() throws IOException {
          try (DataOutputStream file = new DataOutputStream(
                    new BufferedOutputStream(new FileOutputStream("src/main/resources/GRNDetails", false)))) {
               file.writeInt(count);
               for (GRNDetails detail : grnDetailsList) {
                    file.writeUTF(detail.getGrnID());
                    file.writeUTF(detail.getProductList().getProductID());
                    file.writeInt(detail.getQuantity());
                    file.writeUTF(detail.getPrice().toString());
               }
          } catch (Exception e) {
               System.out.printf("Error writing file: %s\n", e.getMessage());
          }
     }

     public void readFile() throws IOException {
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
