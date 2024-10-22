package DTO;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class Products {
     private String productId;
     private String productName;
     private LocalDate releaseDate;
     private BigDecimal productPrice;
     private Integer quantity;

     //  constructors
     public Products () {}

     public Products (String productId, String productName, LocalDate releaseDate, BigDecimal productPrice, Integer quantity) {
          DateTimeFormatter convertedFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
          this.releaseDate.format(convertedFormat);
          this.productId = productId;
          this.productName = productName;
          this.releaseDate = releaseDate;
          this.productPrice = productPrice;
          this.quantity = quantity;
     }

     // getter / setter
     public String getProductId () {
          return this.productId;
     }

     public String getProductName () {
          return this.productName;
     }

     public LocalDate getReleaseDate () {
          return this.releaseDate;
     }

     public BigDecimal getProductPrice () {
          return this.productPrice;
     }

     public Integer getQuantity () {
          return this.quantity;
     }

     public void setProductId (String productId) {
          this.productId = productIdModifier(productId);
     }

     public void setProductName (String productName) {
          this.productName = productName;
     }

     public void setReleaseDate (LocalDate releaseDate) {
          this.releaseDate = releaseDate;
     }

     public void setProductPrice (BigDecimal productPrice) {
          this.productPrice = productPrice;
     }

     public void setQuantity (Integer quantity) {
          this.quantity = quantity;
     }

     protected abstract String productIdModifier (String productId); 
}
