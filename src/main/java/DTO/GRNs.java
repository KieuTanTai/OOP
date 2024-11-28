package DTO;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import BUS.BooksBUS;
import BUS.EmployeesBUS;
import BUS.GRNDetailsBUS;
import BUS.GRNsBUS;
import BUS.StationeriesBUS;
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
          this.grnID = grnIDModifier(grnID);
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
          this.grnID = grnIDModifier(grnID);
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
          String grnID;
          GRNs[] list = new GRNsBUS().getListGRN();

          if (list.length == 0) {
               return "00000001";
          } else {
               String getID = list[list.length - 1].getGrnID();
               int prevID = Integer.parseInt(getID.substring(3, getID.length() - 2));
               grnID = String.format("%d", prevID + 1);
               // check if id length < 8
               while (grnID.length() != 8)
                    grnID = "0" + grnID;
          }
          return grnIDModifier(grnID);
     }

     // set employee
     public Employees setEmployee() {
          try {
               int userChoice;
               EmployeesBUS list = new EmployeesBUS();
               list.readFile();
               if (list.getCount() == 0) // if not have any supplier
                    return null;
               Employees[] tempList = list.relativeFind("Warehouse Staff", "role");
               for (Employees employee : tempList)
                    employee.showInfo();
               do {
                    System.out.print("choose employee (like 1, 2,etc...) : ");
                    String option = input.nextLine().trim();
                    userChoice = Validate.parseChooseHandler(option, tempList.length);
               } while (userChoice == -1);
               return tempList[userChoice - 1];
          } catch (IOException e) {
               System.out.println("error reading file!\n" + e.getMessage());
               return null;
          }
     }

     // set supplier
     public Suppliers setSupplier() {
          int userChoice;
          // show list for user choose
          SuppliersBUS.showList();
          if (SuppliersBUS.getCount() == 0) // if not have any supplier
               return null;
          System.out.println("-".repeat(60));
          do {
               System.out.print("choose supplier (like 1, 2,etc...) : ");
               String option = input.nextLine().trim();
               userChoice = Validate.parseChooseHandler(option, SuppliersBUS.getCount());
          } while (userChoice == -1);

          Suppliers supplier = SuppliersBUS.getSupplierList()[userChoice - 1];
          return supplier;
     }

     // set grn detail (NEED TO FIX)
     public GRNDetails[] setGRNDetails(String grnID) {
          GRNDetailsBUS listGRN = new GRNDetailsBUS();
          int userChoice = 0;

          do {
               System.out.println("I. Add detail");
               System.out.println("II. Remove detail");
               System.out.println("III. Edit detail");
               System.out.println("0: Exit!");
               System.out.println("-".repeat(60));
               System.out.print("choose option (like 0, 1, 2,etc...) : ");
               String option = input.nextLine().trim();
               userChoice = Validate.parseChooseHandler(option, SuppliersBUS.getCount());

               // execute userChoice
               try {
                    switch (userChoice) {
                         case 1: // add
                              int index = 0;
                              String productID = "";

                              if (chooseTypeProduct() == 1) {
                                   BooksBUS booksList = new BooksBUS();
                                   booksList.readFile();
                                   booksList.showList();

                                   do {
                                        System.out.println("NOTE : IF YOU WANNA RECEIVE NEW PRODUCT YOUR INPUT SHOULD BE \"new\" ");
                                        System.out.print("product id: ");
                                        productID = input.nextLine().trim();

                                        // execute when wanna receive new book
                                        if (productID.toLowerCase().equals("new"))
                                             break;

                                        index = booksList.find(productID);
                                        if (index == -1)
                                             productID = "";
                                   } while (productID == "");

                                   // create new book
                                   Books product;
                                   if (productID.equals("new")) {
                                        product = new Books();
                                        product.setInfo();
                                   } else
                                        product = booksList.getBooksList()[index];
                                   int quantity = setQuantity();
                                   BigDecimal price = product.getProductPrice();

                                   // for user submit
                                   System.out.println("*".repeat(60));
                                   System.out.printf("| %s %s %s |\n", "I.Cancel", "-".repeat(20), "II.Submit");
                                   do {
                                        System.out.print("choose option (like 1, 2,etc...): ");
                                        option = input.nextLine().trim();
                                        userChoice = Validate.parseChooseHandler(option, 2);
                                   } while (userChoice == -1);
                                   if (userChoice == 1) {
                                        System.out.println("ok!");
                                        break;
                                   }

                                   // add new book
                                   if (productID.equals("new")) {
                                        booksList.add(product);
                                        booksList.writeFile();
                                   }
                                   listGRN.add(new GRNDetails(grnID, product, quantity, price));

                              } else {
                                   StationeriesBUS staList = new StationeriesBUS();
                                   staList.readFile();
                                   staList.showList();

                                   do {
                                        System.out.println("NOTE : IF YOU WANNA RECEIVE NEW PRODUCT YOUR INPUT SHOULD BE \"new\" ");
                                        System.out.print("product id: ");
                                        productID = input.nextLine().trim();

                                        // execute when wanna receive new book
                                        if (productID.equals("new"))
                                             break;

                                        index = staList.find(productID);
                                        if (index == -1)
                                             productID = "";
                                   } while (productID == "");

                                   // create new stationary
                                   Stationeries product;
                                   if (productID.equals("new")) {
                                        product = new Stationeries();
                                        product.setInfo();
                                   } else
                                        product = staList.getStaList()[index];
                                   int quantity = setQuantity();
                                   BigDecimal price = product.getProductPrice();

                                   // for user submit
                                   System.out.println("*".repeat(60));
                                   System.out.printf("| %s %s %s |\n", "I.Cancel", "-".repeat(20), "II.Submit");
                                   do {
                                        System.out.print("choose option (like 1, 2,etc...): ");
                                        option = input.nextLine().trim();
                                        userChoice = Validate.parseChooseHandler(option, 2);
                                   } while (userChoice == -1);
                                   if (userChoice == 1) {
                                        System.out.println("ok!");
                                        break;
                                   }

                                   // add new stationary
                                   if (productID.equals("new")) {
                                        staList.add(product);
                                        staList.writeFile();
                                   }
                                   listGRN.add(new GRNDetails(grnID, product, quantity, price));
                              }
                              break;

                         case 2: // remove
                                 // if not have any grn detail
                              if (listGRN.getCount() == 0 || listGRN == null) {
                                   System.out.println("not have any grn detail!");
                                   break;
                              }
                              listGRN.showList();
                              System.out.println("-".repeat(60));
                              do {
                                   System.out.print("choose grn (like 1, 2,etc...) : ");
                                   option = input.nextLine().trim();
                                   userChoice = Validate.parseChooseHandler(option, listGRN.getCount());
                              } while (userChoice == -1);

                              listGRN.remove(listGRN.getGrnDetailsList()[userChoice - 1].getGrnID());
                              break;

                         case 3: // edit
                              if (listGRN.getCount() == 0 || listGRN == null) {
                                   System.out.println("not have any grn detail!");
                                   break;
                              }
                              listGRN.showList();
                              System.out.println("-".repeat(60));
                              do {
                                   System.out.print("choose grn (like 1, 2,etc...) : ");
                                   option = input.nextLine().trim();
                                   userChoice = Validate.parseChooseHandler(option, listGRN.getCount());
                              } while (userChoice == -1);

                              listGRN.edit(listGRN.getGrnDetailsList()[userChoice - 1].getGrnID());
                              break;
                    }
               } catch (Exception e) {
                    System.out.printf("error when execute file!\nt%s\n", e.getMessage());
               }
          } while (userChoice == -1 && userChoice != 0);
          return listGRN.getGrnDetailsList();
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

     private int chooseTypeProduct() {
          int userChoice;
          // let user decision they wanna change now product to books or stationeries
          System.out.printf("| %s %s %s |\n", "I.Books", "-".repeat(20), "II.Stationeries");
          do {
               System.out.print("choose product (1 or 2): ");
               String option = input.nextLine().trim();
               userChoice = Validate.parseChooseHandler(option, 2);
          } while (userChoice == -1);
          return userChoice;
     }

     // set info
     public void setInfo() {
          System.out.println("*".repeat(60));
          String id = setID();

          System.out.println("-".repeat(60));
          LocalDate date = LocalDate.now();

          System.out.println("-".repeat(60));
          Employees employee = setEmployee();

          System.out.println("-".repeat(60));
          Suppliers supplier = setSupplier();

          System.out.println("-".repeat(60));
          GRNDetails[] detailsArray = setGRNDetails(id);
          BigDecimal totalPrice = new BigDecimal(0);

          int userChoice;
          System.out.println("*".repeat(60));
          System.out.printf("| %s %s %s |\n", "I.Cancel", "-".repeat(20), "II.Submit");
          do {
               System.out.print("choose option (like 1, 2,etc...): ");
               String option = input.nextLine().trim();
               userChoice = Validate.parseChooseHandler(option, 2);
          } while (userChoice == -1);
          if (userChoice == 1) {
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

     // modify id
     private String grnIDModifier(String grnID) {
          if (grnID.startsWith("GRN") && grnID.endsWith("LL") && grnID.length() == 12)
               return grnID;
          if (!Validate.validateID(grnID)) {
               System.out.println("error id!");
               return "N/A";
          }
          return "GRN" + grnID + "LL";
     }
}
