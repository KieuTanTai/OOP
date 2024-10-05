package DTO;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Books extends Products {
     private String publisherId;
     private String author;
     private String type;
     private String genre;
     private String format;
     private String packagingSize;
     
     public Books () {}

     public Books (String productId, String productName, String productBrand, LocalDate releaseDate, BigDecimal productPrice, int quantity, String publisherId, String author, String type, String genre, String format, String packagingSize) {
          super(productId, productName, productBrand, releaseDate, productPrice, quantity);
          this.publisherId = publisherId;
          this.author = author;
          this.type = type;
          this.genre = genre;
          this.format = format;
          this.packagingSize = packagingSize;
     }

     public String getPublisherId () {
          return this.publisherId;
     }

     public String getAuthor () {
          return this.author;
     }

     public String getType () {
          return this.type;
     }
     
     public String getGenre () {
          return this.genre;
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
     
     public void setType (String type) {
          this.type = type;
     } 
     
     public void setGenre (String genre) {
          this.genre = genre;
     } 
     
     public void setFormat (String format) {
          this.format = format;
     } 
     
     public void setPackagingSize (String packagingSize) {
          this.packagingSize = packagingSize;
     } 
}
