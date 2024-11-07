package DTO;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public abstract class Products {
     private String productID;
     private String productName;
     private LocalDate releaseDate;
     private BigDecimal productPrice;
     private int quantity;

     //  constructors
     public Products () {}

     public Products (String productID, String productName, LocalDate releaseDate, BigDecimal productPrice, int quantity) {
          this.productID = productIDModifier(productID + UUID.randomUUID().toString());
          this.productName = productName;
          this.releaseDate = releaseDate;
          this.productPrice = productPrice;
          this.quantity = quantity;
     }

     // getter / setter
     public String getProductID () {
          return this.productID;
     }

     public String getProductName () {
          return this.productName;
     }

     public LocalDate getReleaseDate () {
          return getFormattedReleaseDate();
     }

     public BigDecimal getProductPrice () {
          return this.productPrice;
     }

     public int getQuantity () {
          return this.quantity;
     }

     public void setProductID (String productID) {
          this.productID = productIDModifier(productID);
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

     public void setQuantity (int quantity) {
          this.quantity = quantity;
     }

     public LocalDate getFormattedReleaseDate() {
          DateTimeFormatter convertedFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
          return LocalDate.parse(this.releaseDate.format(convertedFormat));
     }

     protected abstract String productIDModifier (String productID); 
     public abstract void showInfo ();
}
