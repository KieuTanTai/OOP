package BUS;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Scanner;

import DTO.BookFormats;
import DTO.BookGenres;
import DTO.BookTypes;
import DTO.Books;
import DTO.MidForBooks;
import DTO.Publishers;
import Manager.Menu;
import util.Validate;

public class BooksBUS implements IRuleSets {
     private Books[] booksList;
     private int count;
     private final Scanner input = new Scanner(System.in);

     // *constructors (TEST DONE)
     public BooksBUS() {
          this.count = 0;
          this.booksList = new Books[0];
     }

     public BooksBUS(Books[] booksList, int count) {
          this.count = count;
          this.booksList = booksList;
     }

     // when have existences booksArray before
     public BooksBUS(BooksBUS booksArray) {
          this.count = booksArray.count;
          this.booksList = booksArray.booksList;
     }

     // *getter / setter (TEST DONE)
     public Books[] getBooksList() {
          return this.booksList;
     }

     public Books getBook(String bookID) {
          for (int i = 0; i < count; i++)
               if (booksList[i].getProductID().equals(bookID))
                    return booksList[i];
          return null;
     }

     public int getCount() {
          return this.count;
     }

     public void setBooksList(Books[] newBooksList) {
          this.booksList = newBooksList;
     }

     public void setCount(int newQuantity) {
          this.count = newQuantity;
     }

     // all others methods like: add remove edit find show....
     // *methods showList (TEST DONE)
     public void showList() {
          if (booksList == null)
               return;
          for (Books book : booksList)
               book.showInfo();
     }
     
     // *find methods (TEST DONE)
     @Override
     public void find() {
          Menu.findHandler();
     }

     // strict find
     @Override
     public int find(String nameOrID) {
          for (int i = 0; i < booksList.length; i++)
               if (booksList[i].getProductID().equals(nameOrID)
                         || booksList[i].getProductName().toLowerCase().equals(nameOrID.toLowerCase()))
                    return i;
          System.out.println("your book is not exist!");
          return -1;
     }

     // relative finds
     // return list index of products that have contains specific string
     public Books[] relativeFind(Object originalKey, String request) {
          int count = 0;
          boolean flag = false;
          Books[] booksArray = new Books[0];
          request = request.toLowerCase().trim();

          for (Books book : booksList) {
               if (originalKey instanceof String) {
                    BookTypes types = book.getType();
                    BookFormats formats = book.getFormat();
                    Publishers publishers = book.getPublisher();
                    String author = book.getAuthor();
                    String bookName = book.getProductName();
                    String key = (String) originalKey;
                    
                    // assign and check null
                    author = Validate.requiredNotNull(author) ? author.toLowerCase() : "";
                    bookName = Validate.requiredNotNull(bookName) ? bookName.toLowerCase() : "";
                    
                    String typeID = (Validate.requiredNotNull(types)) ? types.getTypeID() : "",
                           typeName = (Validate.requiredNotNull(types)) ? types.getTypeName().toLowerCase() : "";
                    
                    String formatID = (Validate.requiredNotNull(formats)) ? formats.getFormatID() : "",
                           formatName = (Validate.requiredNotNull(formats)) ? formats.getFormatName().toLowerCase() : "";
                    
                    String publisherID = (Validate.requiredNotNull(publishers)) ? publishers.getPublisherID() : "",
                           publisherName = (Validate.requiredNotNull(publishers)) ? publishers.getPublisherName().toLowerCase() : "";
                    
                    if (request.equals("name") && bookName.contains(key.toLowerCase()))
                         flag = true;

                    else if (request.equals("author") && author.contains(key.toLowerCase()))
                         flag = true;

                    else if (request.equals("format") && (formatID.equals(key) || formatName.contains(key.toLowerCase())))
                         flag = true;

                    else if (request.equals("type") && (typeID.equals(key) || typeName.contains(key.toLowerCase())))
                         flag = true;

                    else if (request.equals("publisher")
                              && (publisherID.equals(key) || publisherName.contains(key.toLowerCase())))
                         flag = true;

               } else if (originalKey instanceof LocalDate)
                    if (request.equals("released") && (book.getReleaseDate().isEqual((LocalDate) originalKey)))
                         flag = true;

               if (flag) {
                    booksArray = Arrays.copyOf(booksArray, booksArray.length + 1);
                    booksArray[count] = book;
                    flag = false;
                    count++;
               }
          }
          if (count == 0) {
               System.out.println("not found any books!");
               return null;
          }
          return booksArray;
     }

     // advanced finds
     public Books[] advancedFind(BigDecimal minPrice, BigDecimal maxPrice, String request) {
          request = request.toLowerCase().trim();
          if (request.equals("range")
                    && ((minPrice.compareTo(maxPrice) >= 0) || (minPrice.compareTo(BigDecimal.ZERO) < 0) ||
                              (maxPrice.compareTo(BigDecimal.ZERO) < 0))) {
               System.out.println("error range!");
               return null;
          }

          int count = 0;
          boolean flag = false;
          Books[] booksArray = new Books[0];
          for (Books book : booksList) {
               BigDecimal productPrice = book.getProductPrice();

               if ((request.equals("min")) && (productPrice.compareTo(minPrice) >= 0))
                    flag = true;

               else if ((request.equals("max")) && (productPrice.compareTo(maxPrice) <= 0))
                    flag = true;

               else if (request.equals("range")
                         && ((productPrice.compareTo(minPrice) >= 0) && (productPrice.compareTo(maxPrice) <= 0)))
                    flag = true;

               if (flag) {
                    booksArray = Arrays.copyOf(booksArray, booksArray.length + 1);
                    booksArray[count] = book;
                    flag = false;
                    count++;
               }
          }
          if (count == 0) {
               System.out.println("not found any books!");
               return null;
          }
          return booksArray;
     }

     public Books[] advancedFind(Object originalKeyI, Object originalTimeOrKey, String request) {
          int count = 0;
          boolean flag = false;
          Books[] booksArray = new Books[0];
          request = request.toLowerCase().trim();

          for (Books book : booksList) {
               int inputTime = 0;
               BookTypes types = book.getType();
               BookFormats formats = book.getFormat();
               Publishers publishers = book.getPublisher();
               String author = book.getAuthor();

               // assign and check null
               author = Validate.requiredNotNull(author) ? author.toLowerCase() : "";
               
               String typeID = (Validate.requiredNotNull(types)) ? types.getTypeID() : "",
                         typeName = (Validate.requiredNotNull(types)) ? types.getTypeName().toLowerCase() : "";
               
               String formatID = (Validate.requiredNotNull(formats)) ? formats.getFormatID() : "",
                         formatName = (Validate.requiredNotNull(formats)) ? formats.getFormatName().toLowerCase() : "";
               
               String publisherID = (Validate.requiredNotNull(publishers)) ? publishers.getPublisherID() : "",
                         publisherName = (Validate.requiredNotNull(publishers)) ? publishers.getPublisherName().toLowerCase() : "";
                    
               // execute request
               if ((request.contains("month")) || (request.contains("year"))) {
                    inputTime = Validate.isNumber((String) originalTimeOrKey);
                    if (inputTime == -1) {
                         System.out.println("something went wrong!");
                         return null;
                    }
               }

               if ((originalKeyI instanceof String) && (originalTimeOrKey instanceof String)
                         && (request.contains("month"))) {
                    String keyI = (String) originalKeyI;
                    boolean keyII = book.getReleaseDate().getMonthValue() == inputTime;

                    if ((request.contains("auth")) && (author.contains(keyI.toLowerCase()) && keyII))
                         flag = true;

                    else if ((request.contains("format")) && ((formatID.equals(keyI) && keyII) || (formatName.contains(keyI.toLowerCase()) && keyII)))
                         flag = true;

                    else if ((request.contains("type"))
                              && ((typeID.equals(keyI) && keyII) || (typeName.contains(keyI.toLowerCase()) && keyII)))
                         flag = true;

                    else if ((request.contains("pub")) && ((publisherID.equals(keyI) && keyII)
                              || (publisherName.contains(keyI.toLowerCase()) && keyII)))
                         flag = true;

               } else if ((originalKeyI instanceof String) && (originalTimeOrKey instanceof String)
                         && (request.contains("year"))) {
                    String keyI = (String) originalKeyI;
                    boolean keyII = book.getReleaseDate().getYear() == inputTime;

                    if ((request.contains("auth")) && (author.contains(keyI.toLowerCase()) && keyII))
                         flag = true;

                    else if ((request.contains("format")) && ((formatID.equals(keyI) && keyII) || (formatName.contains(keyI.toLowerCase()) && keyII)))
                         flag = true;

                    else if ((request.contains("type"))
                              && ((typeID.equals(keyI) && keyII) || (typeName.contains(keyI.toLowerCase()) && keyII)))
                         flag = true;

                    else if ((request.contains("pub")) && ((publisherID.equals(keyI) && keyII)
                              || (publisherName.contains(keyI.toLowerCase()) && keyII)))
                         flag = true;

               }
               else if ((originalKeyI instanceof String) && (originalTimeOrKey instanceof String)) {
                    String keyI = (String) originalKeyI, keyII = (String) originalTimeOrKey;
                    boolean hasPub = request.contains("pub");
                    boolean hasType = request.contains("type");
                    boolean hasAuth = request.contains("auth");
                    boolean hasFormat = request.contains("for");

                    boolean isPubAndType = (hasPub && hasType) && ((publisherID.equals(keyI) && typeID.equals(keyII))
                              || (typeID.equals(keyI) && publisherID.equals(keyII))
                              || (publisherName.contains(keyI.toLowerCase()) && typeName.contains(keyII.toLowerCase()))
                              || (typeName.contains(keyI.toLowerCase()) && publisherName.contains(keyII.toLowerCase())));

                    boolean isPubAndAuth = (hasPub && hasAuth) && ((publisherID.equals(keyI)
                              && author.contains(keyII.toLowerCase()))
                              || (author.contains(keyI.toLowerCase()) && publisherID.equals(keyII))
                              || (publisherName.contains(keyI.toLowerCase()) && author.contains(keyII.toLowerCase()))
                              || (author.contains(keyI.toLowerCase()) && publisherName.contains(keyII.toLowerCase())));

                    boolean isTypeAndAuth = (hasType && hasAuth) && ((typeID.equals(keyI)
                              && author.contains(keyII.toLowerCase()))
                              || (author.contains(keyI.toLowerCase()) && typeID.equals(keyII))
                              || (typeName.contains(keyI.toLowerCase()) && author.contains(keyII.toLowerCase()))
                              || (author.contains(keyI.toLowerCase()) && typeName.contains(keyII.toLowerCase())));

                    boolean isPubAndFormat = (hasPub && hasFormat) && ((publisherID.equals(keyI)
                              && formatID.equals(keyII))
                              || (formatID.equals(keyI) && publisherID.equals(keyII))
                              || (publisherName.contains(keyI.toLowerCase()) && formatName.contains(keyII.toLowerCase()))
                              || (formatName.contains(keyI.toLowerCase()) && publisherName.contains(keyII.toLowerCase())));
                      
                    boolean isTypeAndFormat = (hasType && hasFormat) && ((typeID.equals(keyI)
                              && formatID.equals(keyII))
                              || (formatID.equals(keyI) && typeID.equals(keyII))
                              || (typeName.contains(keyI.toLowerCase()) && formatName.contains(keyII.toLowerCase()))
                              || (formatName.contains(keyI.toLowerCase()) && typeName.contains(keyII.toLowerCase())));
                      
                    boolean isAuthAndFormat = (hasAuth && hasFormat) && ((author.contains(keyI.toLowerCase())
                              && formatName.contains(keyII.toLowerCase()))
                              || (formatName.contains(keyI.toLowerCase()) && author.contains(keyII.toLowerCase()))
                              || (publisherName.contains(keyI.toLowerCase()) && formatName.contains(keyII.toLowerCase()))
                              || (formatName.contains(keyI.toLowerCase()) && publisherName.contains(keyII.toLowerCase())));
                      
                    // assign flag
                    if (isPubAndType || isPubAndAuth || isTypeAndAuth || isPubAndFormat || isTypeAndFormat || isAuthAndFormat)
                         flag = true;
               }

               if (flag) {
                    booksArray = Arrays.copyOf(booksArray, booksArray.length + 1);
                    booksArray[count] = book;
                    flag = false;
                    count++;
               }
          }
          if (count == 0) {
               System.out.println("not found any books!");
               return null;
          }
          return booksArray;
     }

     // *search methods (TEST DONE)
     @Override
     public void search() {
          Menu.searchHandler();
     }

     // strict search
     @Override
     public void search(String nameOrID) {
          int index = find(nameOrID);
          if (index != -1)
               booksList[index].showInfo();
     }

     // relative search
     public void relativeSearch(Object key, String request) {
          Books[] list = relativeFind(key, request);
          if (list != null)
               for (Books book : list)
                    book.showInfo();
     }

     // advanced search
     public void advancedSearch(Object keyI, Object timeOrKey, String request) {
          Books[] list;
          if ((keyI instanceof BigDecimal) && (timeOrKey instanceof BigDecimal))
               list = advancedFind((BigDecimal) keyI, (BigDecimal) timeOrKey, request);
          else
               list = advancedFind(keyI, timeOrKey, request);
          if (list != null)
               for (Books book : list)
                    book.showInfo();

     }

     // *add methods (TEST DONE)
     @Override
     public void add() {
          Menu.addHandler();
     }

     @Override
     public void add(Object newBook) {
          if (newBook instanceof Books) {
               booksList = Arrays.copyOf(booksList, booksList.length + 1);
               booksList[count] = (Books) newBook;
               count++;
          } else
               System.out.println("your new book have something not like book!");
     }

     public void add(Books[] newBooks, int size) {
          booksList = Arrays.copyOf(booksList, booksList.length + newBooks.length);

          int tempIndex = 0;
          int initCount = this.getCount();
          int total = initCount + size;

          for (int i = initCount; i < total; i++, tempIndex++)
               booksList[i] = newBooks[tempIndex];
          this.count = total;
     }

     // *edit methods (TEST DONE)
     @Override
     public void edit() {
          Menu.editHandler();
     }

     // edit name
     @Override
     public void edit(String bookID) {
          int index = find(bookID);
          if (index != -1) {
               String name;
               int userChoose;
               // show list for user choose
               booksList[index].showInfo();
               System.out.printf("| %s %s %s |\n", "I.Cancel", "-".repeat(20), "II.Edit");
               do {
                    System.out.print("choose option (like 1, 2,etc...): ");
                    String option = input.nextLine().trim();
                    userChoose = Validate.parseChooseHandler(option, 2);
               } while (userChoose == -1);
               if (userChoose == 1)
                    return;
               
               do {
                    System.out.print("new name : ");
                    name = input.nextLine().trim();
                    if (!Validate.checkName(name)) {
                         System.out.println("name is wrong format!");
                         name = "";
                    }
               } while (name.isEmpty());
               booksList[index].setProductName(name);
          }
     }

     // edit release date
     public void editReleaseDate(String bookID) {
          int index = find(bookID);
          if (index != -1) {
               LocalDate date;
               int userChoose;
               // show list for user choose
               booksList[index].showInfo();
               System.out.printf("| %s %s %s |\n", "I.Cancel", "-".repeat(20), "II.Edit");
               do {
                    System.out.print("choose option (like 1, 2,etc...): ");
                    String option = input.nextLine().trim();
                    userChoose = Validate.parseChooseHandler(option, 2);
               } while (userChoose == -1);
               if (userChoose == 1)
                    return;

               do {
                    System.out.print("new release date : ");
                    String dateInput = input.nextLine().trim();
                    date = Validate.isCorrectDate(dateInput);
               } while (date == null);
               booksList[index].setReleaseDate(date);
          }
     }

     // edit price
     public void editPrice(String bookID) {
          int index = find(bookID);
          if (index != -1) {
               BigDecimal price;
               int userChoose;
               // show list for user choose
               booksList[index].showInfo();
               System.out.printf("| %s %s %s |\n", "I.Cancel", "-".repeat(20), "II.Edit");
               do {
                    System.out.print("choose option (like 1, 2,etc...): ");
                    String option = input.nextLine().trim();
                    userChoose = Validate.parseChooseHandler(option, 2);
               } while (userChoose == -1);
               if (userChoose == 1)
                    return;

               do {
                    System.out.print("new price (VND) : ");
                    String value = input.nextLine();
                    price = Validate.isBigDecimal(value);
               } while (price == null);
               booksList[index].setProductPrice(price);
          }
     }

     // edit quantity
     public void editQuantity(String bookID) {
          int index = find(bookID);
          if (index != -1) {
               int quantity, userChoose;
               // show list for user choose
               booksList[index].showInfo();
               System.out.printf("| %s %s %s |\n", "I.Cancel", "-".repeat(20), "II.Edit");
               do {
                    System.out.print("choose option (like 1, 2,etc...): ");
                    String option = input.nextLine().trim();
                    userChoose = Validate.parseChooseHandler(option, 2);
               } while (userChoose == -1);
               if (userChoose == 1)
                    return;

               do {
                    System.out.print("new quantity: ");
                    String quantityInput = input.nextLine().trim();
                    quantity = Validate.isNumber(quantityInput);
               } while (quantity == -1);
               booksList[index].setQuantity(quantity);
          }
     }

     // edit author
     public void editAuthor(String bookID) {
          int index = find(bookID);
          if (index != -1) {
               String authorName;
               int userChoose;
               // show list for user choose
               booksList[index].showInfo();
               System.out.printf("| %s %s %s |\n", "I.Cancel", "-".repeat(20), "II.Edit");
               do {
                    System.out.print("choose option (like 1, 2,etc...): ");
                    String option = input.nextLine().trim();
                    userChoose = Validate.parseChooseHandler(option, 2);
               } while (userChoose == -1);
               if (userChoose == 1)
                    return;

               do {
                    System.out.print("new author name: ");
                    authorName = input.nextLine().trim();
                    if (!Validate.checkHumanName(authorName)) {
                         System.out.println("error name!");
                         authorName = "";
                    }
               } while (authorName.isEmpty());
               booksList[index].setAuthor(authorName);
          }
     }

     // edit publisher
     public void editPublisher(String bookID) {
          if (PublishersBUS.getCount() == 0) {
               System.out.println("not have any publisher for edit!");     
               return;
          }

          int index = find(bookID);
          if (index != -1) {
               int userChoose;
               // show list for user choose
               booksList[index].showInfo();
               System.out.printf("| %s %s %s |\n", "I.Cancel", "-".repeat(20), "II.Edit");
               do {
                    System.out.print("choose option (like 1, 2,etc...): ");
                    String option = input.nextLine().trim();
                    userChoose = Validate.parseChooseHandler(option, 2);
               } while (userChoose == -1);
               if (userChoose == 1)
                    return;

               PublishersBUS.showList();
               do {
                    System.out.print("choose publisher (like 1, 2,etc...): ");
                    String option = input.nextLine().trim();
                    userChoose = Validate.parseChooseHandler(option, PublishersBUS.getCount());
               } while (userChoose == -1);

               Publishers publisher = PublishersBUS.getPublishersList()[userChoose - 1];
               booksList[index].setPublisher(publisher);
          }
     }

     // edit type
     public void editType(String bookID) {
          if (TypesBUS.getCount() == 0) {
               System.out.println("not have any type for edit!");     
               return;
          }

          int index = find(bookID);
          if (index != -1) {
               int userChoose;
               // show list for user choose
               booksList[index].showInfo();
               System.out.printf("| %s %s %s |\n", "I.Cancel", "-".repeat(20), "II.Edit");
               do {
                    System.out.print("choose option (like 1, 2,etc...): ");
                    String option = input.nextLine().trim();
                    userChoose = Validate.parseChooseHandler(option, 2);
               } while (userChoose == -1);
               if (userChoose == 1)
                    return;

                    TypesBUS.showList();
               do {
                    System.out.print("choose type you want (like \\\"1, 2, 3,etc....\\\"): ");
                    String option = input.nextLine().trim();
                    userChoose = Validate.parseChooseHandler(option, TypesBUS.getCount());
               } while (userChoose == -1);

               BookTypes type = TypesBUS.getTypesList()[userChoose - 1];
               booksList[index].setType(type);
          }
     }

     // edit genre
     public void editGenre(String bookID) {
          try {
               int index = find(bookID);
               if (index != -1) {
                    MidForBooksBUS genres = new MidForBooksBUS();
                    genres.readFile();
                    MidForBooks[] nowGenres = genres.relativeFind(bookID);
                    int[] list = new int[0];
                    int userChoose, count = 0;
                    BookGenres[] listGenres = new BookGenres[0];

                    // show list of now genres for user know
                    MidForBooksBUS.showAsTable(nowGenres);
                    System.out.printf("| %s %s %s |\n", "I.Cancel", "-".repeat(20), "II.Edit");
                    do {
                         System.out.print("choose option (like 1, 2,etc...): ");
                         String option = input.nextLine().trim();
                         userChoose = Validate.parseChooseHandler(option, 2);
                    } while (userChoose == -1);
                    if (userChoose == 1)
                         return;

                    // show list for user choose
                    GenresBUS.showList();
                    if (GenresBUS.getCount() == 0) // if not have any genres
                         return;
                    System.out.println("-".repeat(110));
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

                    // execute all user choose (remove old genres and add new genres)
                    genres.remove(bookID);
                    for (int i = 0; i < listGenres.length; i++)
                         genres.add(new MidForBooks(bookID, listGenres[i]));
                    genres.writeFile();
               }
          } catch (Exception err) {
               System.out.printf("error when executing file!\nt%s\n", err.getMessage());
          }

     }

     // edit format
     public void editFormat(String bookID) {
          if (BookFormatsBUS.getCount() == 0) {
               System.out.println("not have any format for edit!");     
               return;
          }

          int index = find(bookID);
          if (index != -1) {
               int userChoose;
               // show list for user choose
               booksList[index].showInfo();
               System.out.printf("| %s %s %s |\n", "I.Cancel", "-".repeat(20), "II.Edit");
               do {
                    System.out.print("choose option (like 1, 2,etc...): ");
                    String option = input.nextLine().trim();
                    userChoose = Validate.parseChooseHandler(option, 2);
               } while (userChoose == -1);
               if (userChoose == 1)
                    return;


               BookFormatsBUS.showList();
               do {
                    System.out.print("choose format (like 1, 2,etc...): ");
                    String option = input.nextLine().trim();
                    userChoose = Validate.parseChooseHandler(option, BookFormatsBUS.getCount());
               } while (userChoose == -1);

               BookFormats format = BookFormatsBUS.getFormatsList()[userChoose - 1];
               booksList[index].setFormat(format);
          }
     }

     // edit packaging size
     public void editPackagingSize(String bookID) {
          int index = find(bookID);
          if (index != -1) {
               String packagingSize;
               int userChoose;
               // show list for user choose
               booksList[index].showInfo();
               System.out.printf("| %s %s %s |\n", "I.Cancel", "-".repeat(20), "II.Edit");
               do {
                    System.out.print("choose option (like 1, 2,etc...): ");
                    String option = input.nextLine().trim();
                    userChoose = Validate.parseChooseHandler(option, 2);
               } while (userChoose == -1);
               if (userChoose == 1)
                    return;

               do {
                    System.out.println("packaging size have format: \"number 'x' number 'cm'\" ");
                    System.out.print("new packaging size: ");
                    packagingSize = input.nextLine();
                    if (!Validate.checkPackagingSize(packagingSize)) {
                         System.out.println("error packaging size!");
                         packagingSize = "";
                    }
               } while (packagingSize.isEmpty());
               booksList[index].setPackagingSize(packagingSize);
          }
     }

     // remove methods ()
     @Override
     public void remove() {
          Menu.removeHandler();
     }

     @Override
     public void remove(String bookID) {
          int index = find(bookID);
          if (index != -1) {
               int userChoose;
               // show list for user choose
               booksList[index].showInfo();
               System.out.printf("| %s %s %s |\n", "I.Cancel", "-".repeat(20), "II.Remove");
               do {
                   System.out.print("choose option (like 1, 2,etc...): ");
                   String option = input.nextLine().trim();
                   userChoose = Validate.parseChooseHandler(option, 2);
               } while (userChoose == -1);
               if (userChoose == 1)
                   return;

               for (int i = index; i < booksList.length - 1; i++)
                    booksList[i] = booksList[i + 1];
               booksList = Arrays.copyOf(booksList, booksList.length - 1);
               count--;
          }
     }

     // execute files
     // write file
     public void writeFile() throws IOException {
          try (DataOutputStream file = new DataOutputStream(
                    new FileOutputStream("OOP/src/main/resources/Books", false))) {
               file.writeInt(count);
               for (int i = 0; i < count; i++) {
                    file.writeUTF(booksList[i].getProductID());
                    file.writeUTF(booksList[i].getProductName());
                    file.writeUTF(booksList[i].getProductPrice().setScale(0).toString());
                    file.writeUTF(booksList[i].getReleaseDate().toString());
                    file.writeUTF(booksList[i].getAuthor());
                    file.writeUTF(booksList[i].getPublisher().getPublisherID());
                    file.writeUTF(booksList[i].getType().getTypeID());
                    file.writeInt(booksList[i].getQuantity());
                    file.writeUTF(booksList[i].getFormat().getFormatID());
                    file.writeUTF(booksList[i].getPackagingSize());
               }
          } catch (Exception err) {
               System.out.printf("error writing file!\nt%s\n", err.getMessage());
          }
     }

     // read file
     public void readFile() throws IOException {
          try (DataInputStream file = new DataInputStream(new FileInputStream("OOP/src/main/resources/Books"))) {
               count = file.readInt();
               Books[] list = new Books[count];
               for (int i = 0; i < count; i++) {
                    String productID = file.readUTF();
                    String productName = file.readUTF();
                    BigDecimal price = new BigDecimal(file.readUTF());
                    LocalDate releaseDate = LocalDate.parse(file.readUTF());
                    String author = file.readUTF();
                    String publisherID = file.readUTF(); // use as param for query publisher from class Publishers
                    String typeID = file.readUTF();
                    int quantity = file.readInt();
                    String formatID = file.readUTF();
                    String packagingSize = file.readUTF();

                    // execute IDs
                    Publishers publisher = PublishersBUS.getPublisher(publisherID);
                    BookTypes type = TypesBUS.getType(typeID);
                    BookFormats format = BookFormatsBUS.getFormat(formatID);
                    list[i] = new Books(productID, productName, releaseDate, price, quantity, publisher, author, type,
                              format, packagingSize);
               }
               setCount(count);
               setBooksList(list);
          } catch (Exception err) {
               System.out.printf("error reading file!\nt%s\n", err.getMessage());
          }
     }
}
