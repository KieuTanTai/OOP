package DTO;
import java.math.BigDecimal;
import java.time.LocalDate;

public class Products {
     private String productId;
     private String productName;
     private String productBrand;
     private LocalDate releaseDate;
     private BigDecimal productPrice;
     private int quantity;

     Products () {}

     Products (String productId, String productName, String productBrand, LocalDate releaseDate, BigDecimal productPrice, int quantity) {
          this.productId = productId;
          this.productName = productName;
          this.productBrand = productBrand;
          this.releaseDate = releaseDate;
          this.productPrice = productPrice;
          this.quantity = quantity;
     }

     public String getProductId () {
          return this.productId;
     }

     public String getProductName () {
          return this.productName;
     }

     public String getProductBrand () {
          return this.productBrand;
     }

     public LocalDate getReleaseDate () {
          return this.releaseDate;
     }

     public BigDecimal getProductPrice () {
          return this.productPrice;
     }

     public int getQuantity () {
          return this.quantity;
     }

     public void setProductId (String productId) {
          this.productId = productId;
     }

     public void setProductName (String productName) {
          this.productName = productName;
     }

     public void setProductBrand (String productBrand) {
          this.productBrand = productBrand;
     }

     public void setReleaseDate (LocalDate releaseDate) {
          this.releaseDate = releaseDate;
     }

     public void setProductPrice (BigDecimal productPrice) {
          this.productPrice = productPrice;
     }

     public void setQuantity (int quantity) {
          this.quantity = quantity;
     }

}
