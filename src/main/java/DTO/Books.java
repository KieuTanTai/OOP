package DTO;

import util.Validate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import BUS.BookFormatsBUS;
import BUS.GenresBUS;
import BUS.MidForBooksBUS;
import BUS.PublishersBUS;
import BUS.TypesBUS;

public class Books extends Products {
    private String author;
    private Publishers publisher;
    private BookFormats format;
    private BookTypes bookType;
    private String packagingSize;

    // constructors
    public Books() {
    }

    public Books(String productId, String productName, LocalDate releaseDate, BigDecimal productPrice,
            int quantity, Publishers publisher, String author, BookTypes type, BookFormats format,
            String packagingSize) {
        super(productId, productName, releaseDate, productPrice, quantity);
        this.publisher = publisher;
        this.author = author;
        this.bookType = type;
        this.format = format;
        this.packagingSize = packagingSize;
    }

    public Books(String productId, String productName, LocalDate releaseDate, BigDecimal productPrice,
            int quantity, Publishers publisher, String author, BookTypes type, BookFormats format, String packagingSize,
            BookGenres[] genres) {
        super(productId, productName, releaseDate, productPrice, quantity);
        this.publisher = publisher;
        this.author = author;
        this.bookType = type;
        this.format = format;
        this.packagingSize = packagingSize;
        execGenres(productId, genres);
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
    public Publishers getPublisher() {
        return this.publisher;
    }

    public String getAuthor() {
        return this.author;
    }

    public BookTypes getType() {
        return this.bookType;
    }

    public BookFormats getFormat() {
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

    public void setFormat(BookFormats format) {
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
            System.out.print("set author name : ");
            authorName = input.nextLine().trim();
            if (!Validate.checkHumanName(authorName)) {
                System.out.println("error name!");
                authorName = "";
            }
        } while (authorName.isEmpty());
        return authorName;
    }

    // set format
    public BookFormats setFormat() {
        int userChoose;
        // show list for user choose
        BookFormatsBUS.showList();
        if (BookFormatsBUS.getCount() == 0) // if not have any format
            return null;
        System.out.println("----------------------------");
        do {
            System.out.print("choose format (like 1, 2,etc...) : ");
            String option = input.nextLine().trim();
            userChoose = Validate.parseChooseHandler(option, BookFormatsBUS.getCount());
        } while (userChoose == -1);

        BookFormats format = BookFormatsBUS.getFormatsList()[userChoose - 1];
        return format;
    }

    // set packaging size
    public String setPackagingSize() {
        String packagingSize;
        do {
            System.out.println("packaging size have format: \"number 'x' number 'cm'\" ");
            System.out.print("set packaging size : ");
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
        // show list for user choose
        TypesBUS.showList();
        if (TypesBUS.getCount() == 0) // if not have any types
            return null;
        System.out.println("----------------------------");
        do {
            System.out.print("choose type you want (like \"1, 2, 3,etc....\") : ");
            String option = input.nextLine().trim();
            userChoose = Validate.parseChooseHandler(option, TypesBUS.getCount());
        } while (userChoose == -1);

        BookTypes type = TypesBUS.getTypesList()[userChoose - 1];
        return type;
    }

    // set publisher
    public Publishers setPublisher() {
        int userChoose;
        // show list for user choose
        PublishersBUS.showList();
        if (PublishersBUS.getCount() == 0) // if not have any publisher
            return null;
        System.out.println("----------------------------");
        do {
            System.out.print("choose publisher (like 1, 2,etc...) : ");
            String option = input.nextLine().trim();
            userChoose = Validate.parseChooseHandler(option, PublishersBUS.getCount());
        } while (userChoose == -1);

        Publishers publisher = PublishersBUS.getPublishersList()[userChoose - 1];
        return publisher;
    }

    // set multiple genres and return genres array
    public BookGenres[] setBookGenres() {
        int userChoose, count = 0;
        int[] list = new int[0];
        BookGenres[] listGenres = new BookGenres[0];
        // show list for user choose
        GenresBUS.showList();
        if (GenresBUS.getCount() == 0) // if not have any genres
            return null;
        System.out.println("----------------------------");
        do {
            System.out.print("choose genres (like 1, 2,etc...) : ");
            String options = input.nextLine().trim();
            String[] splitOptions = options.split(" ");

            if (Validate.hasDuplicates(splitOptions)) {
                System.out.println("has duplicate! please try again!");
                count = 0;
                continue;
            }

            for (String item : splitOptions) {
                userChoose = Validate.parseChooseHandler(item, GenresBUS.getCount());
                if (userChoose == -1) {
                    count = 0;
                    break;
                }
                list = Arrays.copyOf(list, list.length + 1);
                list[count] = userChoose;
                count++;
            }
        } while (count == 0);

        for (int i = 0; i < count; i++) {
            int option = list[i];
            BookGenres genre = GenresBUS.getGenresList()[option - 1];
            listGenres = Arrays.copyOf(listGenres, i + 1);
            listGenres[i] = genre;
        }
        return listGenres;
    }

    // execute list of genres for product
    public void execGenres(String bookID, BookGenres[] genres) {
        int count = 0;
        bookID = productIDModifier(bookID);
        MidForBooks[] hashArray = new MidForBooks[0];
        if (genres == null)
            return;
        for (BookGenres genre : genres) {
            MidForBooks mid = new MidForBooks(bookID, genre);
            hashArray = Arrays.copyOf(hashArray, count + 1);
            hashArray[count] = mid;
            count++;
        }

        try {
            MidForBooksBUS midList = new MidForBooksBUS();
            midList.readFile();
            for (MidForBooks mid : hashArray)
                if (midList.find(mid.getBookID(), mid.getGenre().getGenreID()) == -1)
                    midList.add(mid);
            midList.writeFile();
        } catch (Exception e) {
            System.out.println("error writing or reading file!\n" + e.getMessage());
        }
    }

    // other methods
    // *set info (TEST DONE)
    @Override
    public void setInfo() {
        System.out.println("*".repeat(60));
        String id = setID(this);

        System.out.println("-".repeat(60));
        String name = setName();

        System.out.println("-".repeat(60));
        BigDecimal price = setPrice();

        System.out.println("-".repeat(60));
        LocalDate releaseDate = setRelDate();

        System.out.println("-".repeat(60));
        String author = setAuthor();

        System.out.println("-".repeat(60));
        Publishers publisher = setPublisher();

        System.out.println("-".repeat(60));
        BookTypes type = setType();

        System.out.println("-".repeat(60));
        BookGenres[] genres = setBookGenres();

        System.out.println("-".repeat(60));
        int quantity = setQuantity();

        System.out.println("-".repeat(60));
        BookFormats format = setFormat();
        
        System.out.println("-".repeat(60));
        String packagingSize = setPackagingSize();

        int userChoose;
        System.out.printf("*".repeat(60) + "\n");
        System.out.printf("| %s %s %s |\n", "I.Cancel", "-".repeat(20), "II.Submit");
        do {
            System.out.print("choose option (1 or 2) : ");
            String option = input.nextLine().trim();
            userChoose = Validate.parseChooseHandler(option, 2);
        } while (userChoose == -1);
        System.out.printf("*".repeat(60) + "\n");

        if (userChoose == 1) {
            System.out.println("ok!");
            return;
        } else {
            // set fields for product
            setProductID(id);
            setProductName(name);
            setProductPrice(price);
            setReleaseDate(releaseDate);
            setAuthor(author);
            setPublisher(publisher);
            setType(type);
            setQuantity(quantity);
            setFormat(format);
            setPackagingSize(packagingSize);
            execGenres(id, genres);
            System.out.println("create and set fields success");

        }
    }

    // *show info (TEST DONE)
    @Override
    public void showInfo() {
        LocalDate date = this.getReleaseDate();
        BigDecimal price = this.getProductPrice();
        String productID = this.getProductID(), productName = this.getProductName();

        System.out.println("=".repeat(140));
        System.out.printf("| %-22s : %s \n", "ID", productID != null ? productID : "N/A");
        System.out.printf("| %-22s : %s \n", "Book's Name", productName != null ? productName : "N/A");
        System.out.printf("| %-22s : %s \n", "Release Date",
                date != null ? date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) : "N/A");
        System.out.printf("| %-22s : %s \n", "Publisher Name",
                this.publisher != null ? this.publisher.getPublisherName() : "N/A");
        System.out.printf("| %-22s : %s \n", "Author", this.author != null ? this.author : "N/A");
        System.out.printf("| %-22s : %s \n", "Format", this.format != null ? this.format.getFormatName() : "N/A");
        System.out.printf("| %-22s : %s \n", "Packaging Size", this.packagingSize != null ? this.packagingSize : "N/A");
        System.out.printf("| %-22s : %s \n", "Book Type",
                this.bookType != null ? this.bookType.getTypeName() : "N/A");

        System.out.printf("| %-22s : ", "Genres");
        try {
            MidForBooksBUS listGenres = new MidForBooksBUS();
            listGenres.readFile();
            MidForBooks[] list = listGenres.relativeFind(this.getProductID());
            for (int i = 0; i < list.length; i++) {
                if (i == list.length - 1) {
                    System.out.printf("%s", list[i].getGenre().getGenreName());
                    continue;
                }

                if (i % 8 == 0 && i != 0) {
                    System.out.printf("\n| %-22s   %s, ", " ", list[i].getGenre().getGenreName());
                    continue;
                }

                System.out.printf("%s, ", list[i].getGenre().getGenreName());
            }
        } catch (Exception e) {
            System.out.print("| Error loading genres!");
        }

        System.out.println();
        System.out.printf("| %-22s : %s \n", "Quantity", this.getQuantity());
        System.out.printf("| %-22s : %s \n", "Price", price != null ? Validate.formatPrice(price) : "N/A");
        System.out.println("=".repeat(140));
    }

    @Override
    protected String productIDModifier(String bookID) {
        if (bookID.startsWith("BK") && bookID.endsWith("PD") && bookID.length() == 12)
            return bookID;
        if (!Validate.validateID(bookID)) {
            System.out.println("error id!");
            return "N/A";
        }
        return "BK" + bookID + "PD";
    }
}
