package DTO;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;

public class Books extends Products {
     private Publishers publisher;
     private String author;
     private String format;
     private String packagingSize;
     private BookTypes bookType;
     private final Scanner input = new Scanner(System.in);

     // constructors
     public Books () {}

     public Books (String productId, String productName, LocalDate releaseDate, BigDecimal productPrice,
      int quantity, Publishers publisher, String author, BookTypes type, String format, String packagingSize) {
          super (productId, productName, releaseDate, productPrice, quantity);
          this.publisher = publisher;
          this.author = author;
          this.bookType = type;
          this.format = format;
          this.packagingSize = packagingSize;
     }

     public Books (Books bookInput) {
          super (bookInput.getProductID(), bookInput.getProductName(), bookInput.getReleaseDate(),
                  bookInput.getProductPrice(), bookInput.getQuantity());
          this.publisher = bookInput.publisher;
          this.author = bookInput.author;
          this.bookType= bookInput.bookType;
          this.format = bookInput.format;
          this.packagingSize = bookInput.packagingSize;
     }

     // getter / setter
     public String getPublisherID () {
          return this.publisher.getPublisherID();
     }

     public String getPublisherName () {
          return this.publisher.getPublisherName();
     }

     public String getAuthor () {
          return this.author;
     }

     public String getTypeID () {
          return this.bookType.getTypeID();
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

     public void setPublisher (Publishers publisher) {
          this.publisher = publisher;
     } 

     public void setAuthor (String author) {
          this.author = author;
     } 
     
     public void setType (BookTypes type) {
          this.bookType = type;
     } 
     
     public void setFormat (String format) {
          this.format = format;
     } 
     
     public void setPackagingSize (String packagingSize) {
          this.packagingSize = packagingSize;
     } 

     @Override
     protected String productIDModifier (String bookId) {
          return "BK" + bookId + "PD";
     } 

     public void showInfo () {
          System.out.printf("Publisher name: %s\n", publisher.getPublisherName());
          System.out.printf("Author: %s\n", author);
          System.out.printf("Format: %s\n", format);
          System.out.printf("Packaging Size: %s\n", packagingSize);
          System.out.printf("Book Type: %s\n", bookType != null ? bookType.getTypeName() : "Unknown");
     }
}
