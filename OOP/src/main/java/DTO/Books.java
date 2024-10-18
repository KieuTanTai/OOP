package DTO;
import java.math.BigDecimal;
import java.time.LocalDate;

public class Books extends Products {
     private String publisherId;
     private String author;
     private String format;
     private String packagingSize;
     private BookTypes bookType;
     // constructors
     public Books () {}

     public Books (String productId, String productName, LocalDate releaseDate, BigDecimal productPrice,
      Integer quantity, String publisherId, String author, String typeId, String format, String packagingSize) {
          super (productId, productName, releaseDate, productPrice, quantity);
          this.publisherId = publisherId;
          this.author = author;
          this.bookType.setTypeId(typeId);
          this.format = format;
          this.packagingSize = packagingSize;
          
     }

     public Books (Books bookInput) {
          this.publisherId = bookInput.publisherId;
          this.author = bookInput.author;
          this.bookType.setTypeId(bookInput.getTypeId());
          this.format = bookInput.format;
          this.packagingSize = bookInput.packagingSize;
     }


     // getter / setter
     public String getPublisherId () {
          return this.publisherId;
     }

     public String getAuthor () {
          return this.author;
     }

     public String getTypeId () {
          return this.bookType.getTypeId();
     }
     
     public String getTypeName () {
          return this.bookType.getTypeName();
     }

     public String getFormat () {
          return this.format;
     }

     public String getPackagingSize () {
          return this.packagingSize;
     }

     public void setPublisherId (String publisherId) {
          this.publisherId = publisherId;
     } 

     public void setAuthor (String author) {
          this.author = author;
     } 
     
     public void setTypeId (String typeId) {
          this.bookType.setTypeId(typeId);
     } 
     
     public void setFormat (String format) {
          this.format = format;
     } 
     
     public void setPackagingSize (String packagingSize) {
          this.packagingSize = packagingSize;
     } 

}
