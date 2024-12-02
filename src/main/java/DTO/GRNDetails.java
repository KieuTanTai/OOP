package DTO;

import java.math.BigDecimal;

public class GRNDetails {
     private String grnID;
     private Products product;
     private int quantity;
     private BigDecimal price;
     private BigDecimal subTotal;

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

     // public void setSubTotal(BigDecimal subTotal) {
     //      this.subTotal = subTotal;
     // }
}
