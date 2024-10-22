package DTO;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class Products {
     private String productId;
     private String productName;
     private LocalDate releaseDate;
     private BigDecimal productPrice;

     //  constructors
     public Products () {}

     public Products (String productId, String productName, LocalDate releaseDate, BigDecimal productPrice) {
          this.productId = productIdModifier(productId);
          this.productName = productName;
          this.releaseDate = releaseDate;
          this.productPrice = productPrice;
     }

     // getter / setter
     public String getProductId () {
          return this.productId;
     }

     public String getProductName () {
          return this.productName;
     }

     public String getReleaseDate () {
          return getFormattedReleaseDate();
     }

     public BigDecimal getProductPrice () {
          return this.productPrice;
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

     public String getFormattedReleaseDate() {
          DateTimeFormatter convertedFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
          return this.releaseDate.format(convertedFormat);
     }

     protected abstract String productIdModifier (String productId); 
     public abstract void showInfo ();
}
