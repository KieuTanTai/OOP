package DTO;

import java.math.BigDecimal;
import java.util.Scanner;

import util.Validate;

public class GRNDetails {
     private String grnID;
     private Products product;
     private int quantity;
     private BigDecimal price;
     private BigDecimal subTotal;
     private Scanner input = new Scanner(System.in);

     // Constructors
     public GRNDetails() {
     }

     public GRNDetails(String grnID, Products product, int quantity, BigDecimal price) {
          this.grnID = grnID;
          this.product = product;
          this.quantity = quantity;
          this.price = price;
          this.subTotal = price.multiply(new BigDecimal(quantity));
     }

     // Getter / Setter
     public String getGrnID() {
          return this.grnID;
     }

     public Products getProduct() {
          return this.product;
     }

     public int getQuantity() {
          return this.quantity;
     }

     public BigDecimal getPrice() {
          return this.price;
     }

     public BigDecimal getSubTotal() {
          return this.subTotal;
     }

     public void setGrnID(String grnID) {
          this.grnID = grnID;
     }

     public void setProduct(Products product) {
          this.product = product;
     }

     public void setQuantity(int quantity) {
          this.quantity = quantity;
          this.subTotal = this.price.multiply(new BigDecimal(quantity));
     }

     public void setPrice(BigDecimal price) {
          this.price = price;
          this.subTotal = price.multiply(new BigDecimal(this.quantity));
     }

     public void setSubTotal(BigDecimal subTotal) {
          BigDecimal tempNowTotal = this.price.multiply(new BigDecimal(quantity));
          if ((subTotal.compareTo(tempNowTotal) > 0) || (subTotal.compareTo(tempNowTotal) < 0)) {
               int userChoose;
               System.out.printf("*".repeat(60) + "\n");
               System.out.println("something wrong with your total price! Are you sure you wanna set it!");
               System.out.printf("-".repeat(60) + "\n");
               System.out.printf("| %s %s %s |\n", "I.Cancel", "-".repeat(20), "II.Set");
               do {
                    System.out.print("choose option (1 or 2) : ");
                    String option = input.nextLine().trim();
                    userChoose = Validate.parseChooseHandler(option, 2);
               } while (userChoose == -1);

          }
          this.subTotal = subTotal;
     }
}
