import Manager.Menu;
import DTO.*;

import java.math.BigDecimal;
import java.time.LocalDate;

import BUS.*;
public class App {
    public static void main(String[] args) {
        BookTypes type = new BookTypes("T001", "hello");
        BooksBUS temp = new BooksBUS();
        TypesBUS temp1 = new TypesBUS();
        temp1.add(type);
        temp1.showList();
        Books book1 = new Books(
            "B001",                        // productId
            "Introduction to Java",         // productName
            LocalDate.of(2023, 10, 1),      // releaseDate
            new BigDecimal("29.99"),        // productPrice
            100,                            // quantity
            "PUB123",                       // publisherId
            "John Doe",                     // author
            type,                         // typeId
            "Hardcover",                    // format
            "15x20 cm"                      // packagingSize
        );

        temp.add(book1);
        System.out.println("Product ID: " + book1.getProductId());
        System.out.println("Product Name: " + book1.getProductName());
        System.out.println("Release Date: " + book1.getReleaseDate());
        System.out.println("Product Price: " + book1.getProductPrice());
        System.out.println("Quantity: " + book1.getQuantity());
        System.out.println("Publisher ID: " + book1.getPublisherId());
        System.out.println("Author: " + book1.getAuthor());
        System.out.println("Book Type ID: " + book1.getTypeId() + "\nBook Type Name: " + book1.getTypeName());
        System.out.println("Format: " + book1.getFormat());
        System.out.println("Packaging Size: " + book1.getPackagingSize());

    }
}
