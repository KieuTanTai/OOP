package DTO;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import BUS.EmployeesBUS;
import BUS.PublishersBUS;
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
          do {
               System.out.print("set id : ");
               id = input.nextLine().trim();
               if (!Validate.validateID(id)) {
                    System.out.println("error id!");
                    id = "";
               }
          } while (id.isEmpty());
          return id;
     }

     // set date
     public LocalDate setDate() {
          LocalDate date;
          do {
               System.out.print("set release date : ");
               String dateInput = input.nextLine().trim();
               date = Validate.isCorrectDate(dateInput);
          } while (date == null);
          return date;
     }

     // set total price
     public BigDecimal setTotalPrice() {
          BigDecimal price;
          do {
               System.out.print("set price (VND) : ");
               String value = input.nextLine();
               price = Validate.isBigDecimal(value);
          } while (price == null);
          return price;
     }

     // set employee
     public Employees setEmployee() {
          try {
               int index;
               EmployeesBUS list = new EmployeesBUS();
               list.readFile();
               System.out.println("----------------------------");
               do {
                    System.out.print("name employee : ");
                    String name = input.nextLine().trim();
                    index = list.find(name);
               } while (index == -1);

               Employees employees = list.getEmployeesList()[index];
               return employees;
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

     // set info
     public void setInfo() {
          System.out.println("*".repeat(60));
          String id = setID();

          System.out.println("-".repeat(60));
          LocalDate date = setDate();
          
          System.out.println("-".repeat(60));
          BigDecimal totalPrice = setTotalPrice();

          System.out.println("-".repeat(60));
          Employees employee = setEmployee();
          
          System.out.println("-".repeat(60));
          Suppliers supplier = setSupplier();

          int userChoose;
          System.out.printf("*".repeat(60) + "\n");
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
               setTotalPrice(totalPrice);
               System.out.println("create and set fields success");
          }
     }

     // show info
     public void showInfo() {
          LocalDate date = this.getReleaseDate();
          BigDecimal price = this.getProductPrice();
          String productID = this.getProductID(), productName = this.getProductName();

          System.out.println("=".repeat(160));
          System.out.printf("| %-22s : %s \n", "ID", productID != null ? productID : "N/A");
          System.out.printf("| %-22s : %s \n", "Stationeries ID", stationaryID != null ? stationaryID : "N/A");
          System.out.printf("| %-22s : %s \n", "Name", productName != null ? productName : "N/A");
          System.out.printf("| %-22s : %s \n", "Release Date",
                    date != null ? date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) : "N/A");
          System.out.printf("| %-22s : %s \n", "Type", this.staTypes != null ? this.staTypes.getTypeName() : "N/A");
          System.out.printf("| %-22s : %s \n", "Material", this.material != null ? this.material : "N/A");
          System.out.printf("| %-22s : %s \n", "Source", this.source != null ? this.source : "N/A");
          System.out.printf("| %-22s : %s \n", "Brand", this.brand != null ? this.brand : "N/A");
          System.out.printf("| %-22s : %d \n", "Quantity", this.getQuantity());
          System.out.printf("| %-22s : %s \n", "Price", price != null ? Validate.formatPrice(price) : "N/A");
          System.out.println("=".repeat(160));
     }
}
