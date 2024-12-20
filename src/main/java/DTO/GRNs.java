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
     private final Scanner input = new Scanner(System.in);

     // constructors
     public GRNs() {
     }

     public GRNs(String grnID, LocalDate date, Employees employee, Suppliers supplier) {
          this.grnID = grnIDModifier(grnID);
          this.date = date;
          this.employee = employee;
          this.supplier = supplier;
          this.totalPrice = totalPrice(grnID);
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
          try {
               StringBuilder grnID;
               GRNsBUS listGRN = new GRNsBUS();
               listGRN.readFile();
               GRNs[] list = listGRN.getListGRN();

               if (list.length == 0) {
                    grnID = new StringBuilder("00000001");
               } else {
                    String getID = list[list.length - 1].getGrnID();
                    int prevID = Integer.parseInt(getID.substring(3, getID.length() - 2));
                    grnID = new StringBuilder(String.format("%d", prevID + 1));
                    // check if id length < 8
                    while (grnID.length() != 8)
                         grnID.insert(0, "0");
               }
               return grnIDModifier(grnID.toString());
          } catch (Exception e) {
               System.out.printf("error reading file!\nt%s\n", e.getMessage());
               return grnIDModifier("00000001");
          }
     }

     // set employee
     public Employees setEmployee() {
          try {
               int userChoice;
               EmployeesBUS list = new EmployeesBUS();
               list.readFile();
               if (list.getCount() == 0) // if not have any supplier
                    return null;
               Employees[] tempList = list.relativeFind("Warehouse Keeper", "role");
               int length = tempList.length;
               for (int i = 0; i < length; i++) {
                    System.out.printf("%d : \n", i + 1);
                    tempList[i].showInfo();
               }
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
          if (SuppliersBUS.getCount() == 0) // if not have any supplier
               return null;
          SuppliersBUS.showList();
          System.out.println("-".repeat(60));
          do {
               System.out.print("choose supplier (like 1, 2,etc...) : ");
               String option = input.nextLine().trim();
               userChoice = Validate.parseChooseHandler(option, SuppliersBUS.getCount());
          } while (userChoice == -1);

          return SuppliersBUS.getSupplierList()[userChoice - 1];
     }

     // set grn detail (NEED TO FIX)
     public GRNDetails[] setGRNDetails(String grnID) {
          GRNDetailsBUS listGRN = new GRNDetailsBUS();
          int userChoice;

          do {
               System.out.println("I. Add detail");
               System.out.println("II. Remove detail");
               System.out.println("III. Edit detail");
               System.out.println("0: Exit!");
               System.out.println("-".repeat(60));
               System.out.print("choose option (like 0, 1, 2,etc...) : ");
               String option = input.nextLine().trim();
               if (option.equals("0")) {
                    System.out.println("Exit program.");
                    break;
               }
               userChoice = Validate.parseChooseHandler(option, 3);

               // execute userChoice
               try {
                    switch (userChoice) {
                         case 1: // add
                              int index = 0;
                              String productID;
                              Products product;

                              if (chooseTypeProduct() == 1) {
                                   BooksBUS booksList = new BooksBUS();
                                   booksList.readFile();
                                   booksList.showList();

                                   do {
                                        System.out.print("product id: ");
                                        productID = input.nextLine().trim();
                                        // execute input id
                                        index = booksList.find(productID);
                                        if (index == -1)
                                             productID = "";
                                   } while (productID.isEmpty());

                                   product = booksList.getBooksList()[index];
                                   int quantity = setQuantity();
                                   BigDecimal price = setPrice();
                                   listGRN.add(new GRNDetails(grnID, product, quantity, price));

                                   System.out.println(listGRN.getGrnDetailsList().length);

                              } else {
                                   StationeriesBUS staList = new StationeriesBUS();
                                   staList.readFile();
                                   staList.showList();

                                   do {
                                        System.out.print("product id: ");
                                        productID = input.nextLine().trim();
                                        // execute input id
                                        index = staList.find(productID);
                                        if (index == -1)
                                             productID = "";
                                   } while (productID.isEmpty());

                                   product = staList.getStaList()[index];
                                   int quantity = setQuantity();
                                   BigDecimal price = setPrice();
                                   listGRN.add(new GRNDetails(grnID, product, quantity, price));
                              }
                              break;

                         case 2: // remove
                                 // if not have any grn detail
                              if (listGRN.getCount() == 0) {
                                   System.out.println("not have any grn detail!");
                                   break;
                              }
                              listGRN.showList();
                              System.out.println("-".repeat(60));
                              do {
                                   System.out.println("choose 0 to EXIST!");
                                   System.out.print("choose grn (like 0, 1, 2,etc...) : ");
                                   option = input.nextLine().trim();
                                   if (option.equals("0")) {
                                        System.out.println("Exit program.");
                                        break;
                                   }
                                   userChoice = Validate.parseChooseHandler(option, listGRN.getCount());
                              } while (userChoice == -1);
                              if (!option.equals("0")) {
                                   GRNDetails detail = listGRN.getGrnDetailsList()[userChoice - 1];
                                   listGRN.remove(grnID, detail.getProduct().getProductID());

                              }
                              break;

                         case 3: // edit
                              if (listGRN.getCount() == 0) {
                                   System.out.println("not have any grn detail!");
                                   break;
                              }
                              listGRN.edit(grnID);
                              break;
                    }
               } catch (Exception e) {
                    System.out.printf("error when execute file!\nt%s\n", e.getMessage());
               }
          } while (true);
          return listGRN.getGrnDetailsList();
     }

     // private set methods for grn detail
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
          // let new quantity
          do {
               System.out.print("set grn quantity : ");
               String quantityInput = input.nextLine().trim();
               quantity = Validate.isNumber(quantityInput);
          } while (quantity == -1);
          return quantity;
     }

     private int chooseTypeProduct() {
          int userChoice;
          // let user decision they want to change now product to books or stationeries
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
          // date fields
          LocalDate date = LocalDate.now();
          // employee fields
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
          if (userChoice == 1)
               System.out.println("ok!");
          else {
               // set fields for product
               setGrnID(id);
               setDate(date);
               setEmployee(employee);
               setSupplier(supplier);
               // execute grn detail
               try {
                    BooksBUS bookList = new BooksBUS();
                    StationeriesBUS staList = new StationeriesBUS();
                    GRNDetailsBUS detailList = new GRNDetailsBUS();
                    staList.readFile();
                    detailList.readFile();
                    bookList.readFile();
                    for (GRNDetails detail : detailsArray) {
                         String productID = detail.getProduct().getProductID();
                         if (detailList.find(detail.getGrnID(), productID) == -1) {
                              totalPrice = totalPrice.add(detail.getSubTotal());
                              detailList.add(detail);

                              // execute update quantity
                              if (productID.startsWith("ST") && productID.endsWith("PD")) 
                                   staList.updateQuantity(detail);

                              else if (productID.startsWith("BK") && productID.endsWith("PD")) 
                                   bookList.updateQuantity(detail);
                              
                         }
                    }
                    bookList.writeFile();
                    staList.writeFile();
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

          System.out.println("=".repeat(140));
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
                    System.out.println("|" + "-".repeat(139));
                    System.out.printf("| %-22s : %s \n", "Product", product != null ? product : "N/A");
                    System.out.printf("| %-22s : %s \n", "Quantity", grn.getQuantity());
                    System.out.printf("| %-22s : %s \n", "Price", price != null ? Validate.formatPrice(price) : "N/A");
                    System.out.printf("| %-22s : %s \n", "Sub Total",
                              subTotal != null ? Validate.formatPrice(subTotal) : "N/A");
               }
          } catch (Exception e) {
               System.out.println("| Error loading grn!\n" + e.getMessage());
          }

          System.out.println("|" + "*".repeat(139));
          System.out.printf("| %-22s : %s \n", "Total Price",
                    totalPrice != null ? Validate.formatPrice(totalPrice) : "N/A");
          System.out.println("=".repeat(140));
     }

     // calc totalPrice
     private BigDecimal totalPrice(String grnID) {
          BigDecimal total = new BigDecimal(0);
          try {
               GRNDetailsBUS detailList = new GRNDetailsBUS();
               detailList.readFile();
               GRNDetails[] list = detailList.getGrnDetailsList();
               for (GRNDetails detail : list)
                    if (detail.getGrnID().equals(grnID))
                         total = total.add(detail.getSubTotal());
               return total;
          } catch (Exception e) {
               System.out.println("error writing or reading file!\n" + e.getMessage());
               return new BigDecimal(0);
          }
     }

     // modify id
     private String grnIDModifier(String grnID) {
          if (grnID.startsWith("GRN") && grnID.endsWith("LL") && grnID.length() == 13)
               return grnID;
          if (!Validate.validateID(grnID)) {
               System.out.println("error id!");
               return "N/A";
          }
          return "GRN" + grnID + "LL";
     }
}
