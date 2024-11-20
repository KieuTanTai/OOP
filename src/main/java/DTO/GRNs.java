package DTO;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Scanner;

import BUS.EmployeesBUS;
import BUS.GRNDetailsBUS;
import BUS.SuppliersBUS;
import util.Validate;

public class GRNs {
     private String grnID;
     private LocalDate date;
     private Employees employee;
     private Suppliers supplier;
     private BigDecimal totalPrice;
     private Scanner input = new Scanner(System.in);

     // constructors
     public GRNs() {
     }

     public GRNs(String grnID, LocalDate date, Employees employee, Suppliers supplier, BigDecimal totalPrice) {
          this.grnID = grnID;
          this.date = date;
          this.employee = employee;
          this.supplier = supplier;
          this.totalPrice = totalPrice;
     }

     // getters / setters
     public String getGrnID() {
          return grnID;
     }

     public LocalDate getDate() {
          return date;
     }

     public Employees getEmployee() {
          return employee;
     }

     public Suppliers getSupplier() {
          return supplier;
     }

     public BigDecimal getTotalPrice() {
          return totalPrice;
     }

     // setters have params
     public void setGrnID(String grnID) {
          this.grnID = grnID;
     }

     public void setDate(LocalDate date) {
          this.date = date;
     }

     public void setEmployee(Employees employee) {
          this.employee = employee;
     }

     public void setSupplier(Suppliers supplier) {
          this.supplier = supplier;
     }

     public void setTotalPrice(BigDecimal totalPrice) {
          this.totalPrice = totalPrice;
     }

     // setters no params
     // set id
     public String setID() {
          String id;
          GRNDetails[] list = GRNDetailsBUS.getGrnDetailsList();

          if (list.length == 0) {
               return "00000001";
          } else {
               int prevID = Integer.parseInt((list[list.length - 1]).getGrnID().substring(3, list.length - 2));
               id = String.format("%d", prevID + 1);
          }

          return id;
     }

     // set employee
     public Employees setEmployee() {
          try {
               int userChoose;
               EmployeesBUS list = new EmployeesBUS();
               list.readFile();
               if (SuppliersBUS.getCount() == 0) // if not have any supplier
                    return null;
               Employees[] tempList = list.relativeFind("Warehouse Staff", "role");
               for (Employees employee : tempList)
                    employee.showInfo();
               do {
                    System.out.print("choose employee (like 1, 2,etc...) : ");
                    String option = input.nextLine().trim();
                    userChoose = Validate.parseChooseHandler(option, tempList.length);
               } while (userChoose == -1);
               ;
               return tempList[userChoose - 1];
          } catch (IOException e) {
               System.out.println("error reading file!\n" + e.getMessage());
               return null;
          }
     }

     // set supplier
     public Suppliers setSupplier() {
          int userChoose;
          // show list for user choose
          SuppliersBUS.showList();
          if (SuppliersBUS.getCount() == 0) // if not have any supplier
               return null;
          System.out.println("----------------------------");
          do {
               System.out.print("choose supplier (like 1, 2,etc...) : ");
               String option = input.nextLine().trim();
               userChoose = Validate.parseChooseHandler(option, SuppliersBUS.getCount());
          } while (userChoose == -1);

          Suppliers supplier = SuppliersBUS.getSupplierList()[userChoose - 1];
          return supplier;
     }

     // set grn detail
     public GRNDetails[] setGRNDetails() {
          BigDecimal newPrice;
          GRNDetails newGrnDetails = null;
          GRNDetails[] listGRN = new GRNDetails[0];
          int productChoose, totalProduct, newQuantity, count = 0;

          System.out.println("*".repeat(60));
          System.out.println("product on grn!");
          // let total product
          do {
               System.out.print("choose total block product (like 1, 2, 3,etc.....) : ");
               String option = input.nextLine().trim();
               totalProduct = Validate.isNumber(option);
          } while (totalProduct == -1);

          for (int i = 0; i < totalProduct; i++) {
               // choose type product -> set price of this -> set quantity of this
               System.out.println("-".repeat(60));
               productChoose = chooseTypeProduct();

               System.out.println("-".repeat(60));
               newPrice = setPrice();

               System.out.println("-".repeat(60));
               newQuantity = setQuantity();

               System.out.println("-".repeat(60));
               if (productChoose == 1) {
                    Books book = new Books();
                    book.setInfo();
                    newGrnDetails = new GRNDetails(grnID, book, newQuantity, newPrice);
               } else {
                    Stationeries stationary = new Stationeries();
                    stationary.setInfo();
                    newGrnDetails = new GRNDetails(grnID, stationary, newQuantity, newPrice);
               }
               System.out.println("*".repeat(60));
               listGRN = Arrays.copyOf(listGRN, listGRN.length);
               listGRN[count] = newGrnDetails;
               count++;
          }
          return listGRN;
     }

     // private set methods for grn detail
     private int setQuantity() {
          int quantity;
          // let new quantity
          do {
               System.out.print("set quantity : ");
               String quantityInput = input.nextLine().trim();
               quantity = Validate.isNumber(quantityInput);
          } while (quantity == -1);
          return quantity;
     }

     private BigDecimal setPrice() {
          BigDecimal price;
          // let new price
          do {
               System.out.print("set price (VND) : ");
               String value = input.nextLine();
               price = Validate.isBigDecimal(value);
          } while (price == null);
          return price;
     }

     private int chooseTypeProduct() {
          int userChoose;
          // let user decision they wanna change now product to books or stationeries
          System.out.printf("| %s %s %s |\n", "I.Books", "-".repeat(20), "II.Stationeries");
          do {
               System.out.print("choose product (1 or 2): ");
               String option = input.nextLine().trim();
               userChoose = Validate.parseChooseHandler(option, 2);
          } while (userChoose == -1);
          return userChoose;
     }

     // set info
     public void setInfo() {
          System.out.println("*".repeat(60));
          String id = setID();

          System.out.println("-".repeat(60));
          LocalDate date = LocalDate.parse(LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));

          System.out.println("-".repeat(60));
          Employees employee = setEmployee();

          System.out.println("-".repeat(60));
          Suppliers supplier = setSupplier();

          System.out.println("-".repeat(60));
          GRNDetails[] detailsArray = setGRNDetails();

          int userChoose;
          BigDecimal totalPrice = new BigDecimal(0);
          System.out.println("*".repeat(60));
          System.out.printf("| %s %s %s |\n", "I.Cancel", "-".repeat(20), "II.Submit");
          do {
               System.out.print("choose option (like 1, 2,etc...): ");
               String option = input.nextLine().trim();
               userChoose = Validate.parseChooseHandler(option, 2);
          } while (userChoose == -1);

          if (userChoose == 1) {
               System.out.println("ok!");
               return;
          } else {
               // set fields for product
               setGrnID(id);
               setDate(date);
               setEmployee(employee);
               setSupplier(supplier);
               // execute grn detail
               try {
                    GRNDetailsBUS detailList = new GRNDetailsBUS();
                    detailList.readFile();
                    for (GRNDetails detail : detailsArray)
                         if (detailList.find(detail.getGrnID(), detail.getProduct().getProductID()) == -1) {
                              totalPrice.add(detail.getSubTotal());
                              detailList.add(detail);
                         }
                    detailList.writeFile();
               } catch (Exception e) {
                    System.out.println("error writing or reading file!\n" + e.getMessage());
               }
               setTotalPrice(totalPrice);

               System.out.println("create and set fields success");
          }
     }

     // show info
     public void showInfo() {
          LocalDate date = this.getDate();
          BigDecimal totalPrice = this.getTotalPrice();
          String grnID = this.getGrnID(), employeeName = this.getEmployee().getFullName(),
                    supplier = this.getSupplier().getSupplierName();

          System.out.println("=".repeat(160));
          System.out.printf("| %-22s : %s \n", "GRN ID", grnID != null ? grnID : "N/A");
          System.out.printf("| %-22s : %s \n", "Release Date",
                    date != null ? date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) : "N/A");
          System.out.printf("| %-22s : %s \n", "Employee", employeeName != null ? employeeName : "N/A");
          System.out.printf("| %-22s : %s \n", "Supplier", supplier != null ? supplier : "N/A");
          try {
               GRNDetailsBUS detailList = new GRNDetailsBUS();
               detailList.readFile();
               GRNDetails[] list = detailList.relativeFind(this.getGrnID());
               for (GRNDetails grn : list) {
                    String product = grn.getProduct().getProductName();
                    BigDecimal price = grn.getPrice();
                    BigDecimal subTotal = grn.getSubTotal();
                    System.out.printf("| %-22s : %s \n", "Product", product != null ? product : "N/A");
                    System.out.printf("| %-22s : %s \n", "Quantity", grn.getQuantity());
                    System.out.printf("| %-22s : %s \n", "Price", price != null ? Validate.formatPrice(price) : "N/A");
                    System.out.printf("| %-22s : %s \n", "Sub Total",
                              subTotal != null ? Validate.formatPrice(subTotal) : "N/A");
               }
          } catch (Exception e) {
               System.out.print("| Error loading genres!");
          }

          System.out.printf("| %-22s : %s \n", "Total Price",
                    totalPrice != null ? Validate.formatPrice(totalPrice) : "N/A");
          System.out.println("=".repeat(160));
     }
}
