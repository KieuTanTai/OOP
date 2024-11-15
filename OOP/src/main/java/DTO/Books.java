package DTO;

import util.Validate;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Locale;

import BUS.GenresBUS;
import BUS.MidForBooksBUS;
import BUS.PublishersBUS;
import BUS.TypesBUS;

public class Books extends Products {
    private Publishers publisher;
    private String author;
    private String format;
    private String packagingSize;
    private BookTypes bookType;

    // constructors
    public Books() {
    }

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
    public Publishers getPublisher() {
        return this.publisher;
    }

    public String getAuthor() {
        return this.author;
    }

    public BookTypes getType() {
        return this.bookType;
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
        System.out.printf("1.%s\n2.%s \n3.%s\n", formats[0], formats[1], formats[2]);
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
            System.out.println("packaging size have format: \"number 'x' number 'cm'\" ");
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
        // show list for user choose
        TypesBUS.showList();
        if (TypesBUS.getCount() == 0) // if not have any publisher
            return null;
        System.out.println("----------------------------");
        do {
            System.out.print("choose type you want (like \\\"1, 2, 3,etc....\\\"): ");
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
        // show list for user choose
        PublishersBUS.showList();
        if (PublishersBUS.getCount() == 0) // if not have any types
            return null;
        System.out.println("----------------------------");
        do {
            System.out.print("choose publisher (like 1, 2,etc...): ");
            String option = input.nextLine().trim();
            userChoose = Validate.parseChooseHandler(option, PublishersBUS.getCount());
        } while (userChoose == -1);

        publisher = PublishersBUS.getPublishersList()[userChoose - 1];
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
            System.out.print("choose genres (like 1, 2,etc...): ");
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

    // other methods
    // *set info (TEST DONE)
    @Override
    public void setInfo() {
        System.out.println("-----------------------------------------------");
        String id = setID();
        System.out.println("-----------------------------------------------");
        String name = setName();
        System.out.println("-----------------------------------------------");
        BigDecimal price = setPrice();
        System.out.println("-----------------------------------------------");
        LocalDate releaseDate = setRelDate();
        System.out.println("-----------------------------------------------");
        String author = setAuthor();
        System.out.println("-----------------------------------------------");
        Publishers publisher = setPublisher();
        System.out.println("-----------------------------------------------");
        BookTypes type = setType();
        System.out.println("-----------------------------------------------");
        BookGenres[] genres = setBookGenres();
        System.out.println("-----------------------------------------------");
        int quantity = setQuantity();
        System.out.println("-----------------------------------------------");
        String format = setFormat();
        System.out.println("-----------------------------------------------");
        String packagingSize = setPackagingSize();
        System.out.println("-----------------------------------------------");

        int userChoose;
        System.out.println("I.Cancel ------------------- II.Submit");
        do {
            System.out.print("choose option (like 1, 2,etc...): ");
            String option = input.nextLine().trim();
            userChoose = Validate.parseChooseHandler(option, 2);
        } while (userChoose == -1);
        System.out.println("***********************************************");

        if (userChoose == 1) {
            System.out.println("ok!");
            return;
        } 
        else {
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

            // execute list of genres for product
            int count = 0;
            MidForBooks[] hashArray = new MidForBooks[0];
            for (BookGenres genre : genres) {
                MidForBooks mid = new MidForBooks(id, genre);
                hashArray = Arrays.copyOf(hashArray, count + 1);
                hashArray[count] = mid;
                count++;
            }

            try {
                MidForBooksBUS midList = new MidForBooksBUS();
                midList.readFile();
                midList.add(hashArray, count);
                midList.writeFile();
                System.out.println("create and set fields success");
            } catch (Exception e) {
                System.out.println("error writing or reading file!\n" + e.getMessage());
            }
        }
    }

    // *show info (TEST DONE)
    @Override
    public void showInfo() {
        LocalDate date = this.getReleaseDate();
        NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.of("vi", "VN"));
        BigDecimal price = this.getProductPrice();
        System.out.println("=".repeat(140));
        System.out.printf("| %-22s : %s \n", "ID", this.getProductID());
        System.out.printf("| %-22s : %s \n", "Book's Name", this.getProductName());
        System.out.printf("| %-22s : %s \n", "Release Date", 
            date != null ? date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) : "N/A");
        System.out.printf("| %-22s : %s \n", "Publisher Name", 
            publisher != null ? publisher.getPublisherName() : "N/A");
        System.out.printf("| %-22s : %s \n", "Author", author != null ? author : "N/A");
        System.out.printf("| %-22s : %s \n", "Format", format != null ? format : "N/A");
        System.out.printf("| %-22s : %s \n", "Packaging Size", 
            packagingSize != null ? packagingSize : "N/A");
        System.out.printf("| %-22s : %s \n", "Book Type", 
            bookType != null ? bookType.getTypeName() : "N/A");
    
        System.out.printf("| %-22s : ", "Genres");
        try {
            MidForBooksBUS listGenres = new MidForBooksBUS();
            listGenres.readFile();
            MidForBooks[] list = listGenres.relativeFind(this.getProductID());
            if (list.length > 0) {
                for (int i = 0; i < list.length; i++) {
                    if (i == list.length - 1) {
                        System.out.printf("%s", list[i].getGenre().getGenreName());
                        continue;
                    }

                    if (i % 7 == 0 && i != 0) {
                        System.out.printf("\n| %-22s   %s, ", " ", list[i].getGenre().getGenreName());
                        continue;
                    }
                    
                    System.out.printf("%s, ", list[i].getGenre().getGenreName());
                }
            } else {
                System.out.print("N/A");
            }
        } catch (Exception e) {
            System.out.print("Error loading genres!");
        }
        System.out.println();
    
        System.out.printf("| %-22s : %s \n", "Quantity", this.getQuantity());
        System.out.printf("| %-22s : %s \n", "Price",price != null ? formatter.format(price) : "N/A");
        System.out.println("=".repeat(140));
    }

    @Override
    protected String productIDModifier(String bookId) {
        return "BK" + bookId + "PD";
    }
}
