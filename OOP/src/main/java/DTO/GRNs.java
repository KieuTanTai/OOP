package DTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;

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

     // set have param
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

     // set no param
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

     public LocalDate setDate() {
          LocalDate date;
          do {
               System.out.print("set release date : ");
               String dateInput = input.nextLine().trim();
               date = Validate.isCorrectDate(dateInput);
          } while (date == null);
          return date;
     }

     public BigDecimal setPrice() {
          BigDecimal price;
          do {
               System.out.print("set price (VND) : ");
               String value = input.nextLine();
               price = Validate.isBigDecimal(value);
          } while (price == null);
          return price;
     }


     // show info
     public void showInfo() {
          
     }
}
