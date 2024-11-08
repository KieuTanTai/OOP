package DTO;

import util.Validate;

import java.math.BigDecimal;
import java.time.LocalDate;

import BUS.PublishersBUS;
import BUS.TypesBUS;

public class Books extends Products {
    private Publishers publisher;
    private String author;
    private String format;
    private String packagingSize;
    private BookTypes bookType;

    // constructors
    public Books() {}

    public Books(String productId, String productName, LocalDate releaseDate, BigDecimal productPrice,
            int quantity, Publishers publisher, String author, BookTypes type, String format, String packagingSize) {
        super(productId, productName, releaseDate, productPrice, quantity);
        this.publisher = publisher;
        this.author = author;
        this.bookType = type;
        this.format = format;
        this.packagingSize = packagingSize;
    }

    public Books(Books bookInput) {
        super(bookInput.getProductID(), bookInput.getProductName(), bookInput.getReleaseDate(),
                bookInput.getProductPrice(), bookInput.getQuantity());
        this.publisher = bookInput.publisher;
        this.author = bookInput.author;
        this.bookType = bookInput.bookType;
        this.format = bookInput.format;
        this.packagingSize = bookInput.packagingSize;
    }

    // getter / setter
    public String getPublisherID() {
        return this.publisher.getPublisherID();
    }

    public String getPublisherName() {
        return this.publisher.getPublisherName();
    }

    public String getAuthor() {
        return this.author;
    }

    public String getTypeID() {
        return this.bookType.getTypeID();
    }

    public String getTypeName() {
        return this.bookType.getTypeName();
    }

    public String getFormat() {
        return this.format;
    }

    public String getPackagingSize() {
        return this.packagingSize;
    }

    // set have params
    public void setPublisher(Publishers publisher) {
        this.publisher = publisher;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setType(BookTypes type) {
        this.bookType = type;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public void setPackagingSize(String packagingSize) {
        this.packagingSize = packagingSize;
    }

    // set no params
    // set author
    public String setAuthor() {
        String authorName;
        do {
            System.out.print("set author name: ");
            authorName = input.nextLine().trim();
            if (!Validate.checkHumanName(authorName)) {
                System.out.println("error name!");
                authorName = "";
            }
        } while (authorName.isEmpty());
        return authorName;
    }

    // set format
    public String setFormat() {
        String[] formats = { "Hardcover", "Paperback", "Leather-bound" };
        int userChoose;
        System.out.printf("1.%s\n2.%s\n3.%s\n", formats[0], formats[1], formats[2]);
        do {
            System.out.print("select your option (like \"1, 2, 3\"): ");
            userChoose = Validate.parseChooseHandler(input.nextLine().trim(), 3);
        } while (userChoose == -1);
        return formats[userChoose - 1];
    }

    // set packaging size
    public String setPackagingSize() {
        String packagingSize;
        do {
            System.out.println("packaging size have format: \"number 'x'  number 'cm'\" ");
            System.out.print("set packaging size: ");
            packagingSize = input.nextLine();
            if (!Validate.checkPackagingSize(packagingSize)) {
                System.out.println("error packaging size!");
                packagingSize = "";
            }
        } while (packagingSize.isEmpty());
        return packagingSize;
    }

    // set book type
    public BookTypes setType() {
        int userChoose;
        BookTypes type;
        TypesBUS.showList();
        System.out.println("----------------------------");
        do {
            System.out.print("choose type you want (like 1, 2,etc...): ");
            String option = input.nextLine().trim();
            userChoose = Validate.parseChooseHandler(option, TypesBUS.getCount());
        } while (userChoose == -1);

        type = TypesBUS.getTypesList()[userChoose - 1];
        return type;
    }

    // set publisher
    public Publishers setPublisher() {
        int userChoose;
        Publishers publisher;
        PublishersBUS.showList();
        System.out.println("----------------------------");
        do {
            System.out.print("choose type you want (like 1, 2,etc...): ");
            String option = input.nextLine().trim();
            userChoose = Validate.parseChooseHandler(option, PublishersBUS.getCount());
        } while (userChoose == -1);

        publisher = PublishersBUS.getPublishersList()[userChoose - 1];
        return publisher;
    }

    // other methods
    @Override
    protected String productIDModifier(String bookId) {
        return "BK" + bookId + "PD";
    }

    // set info
    @Override
    public void setInfo() {

    }

    // show info
    @Override
    public void showInfo() {
        System.out.printf("Publisher name: %s\n", publisher.getPublisherName());
        System.out.printf("Author: %s\n", author);
        System.out.printf("Format: %s\n", format);
        System.out.printf("Packaging Size: %s\n", packagingSize);
        System.out.printf("Book Type: %s\n", bookType != null ? bookType.getTypeName() : "Unknown");
    }
}
