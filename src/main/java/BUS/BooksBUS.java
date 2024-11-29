package BUS;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Scanner;

import DTO.BillDetails;
import DTO.BookFormats;
import DTO.BookGenres;
import DTO.BookTypes;
import DTO.Books;
import DTO.GRNDetails;
import DTO.MidForBooks;
import DTO.Publishers;
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
          for (Books book : booksList)
               if (book.getProductID().equals(bookID))
                    return book;
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
      // strict find
     @Override
     public int find(String nameOrID) {
          for (int i = 0; i < booksList.length; i++)
               if (booksList[i].getProductID().equals(nameOrID) || booksList[i].getProductName().equalsIgnoreCase(nameOrID))
                    return i;
          System.out.println("your book is not exist!");
          return -1;
     }

     // relative finds
     public Books[] relativeFind(BookGenres genre) {
          int count = 0;
          Books[] bookArray = new Books[0];
          MidForBooks[] midList = MidForBooksBUS.getMidList();
          int length = MidForBooksBUS.getMidList().length;

          for (int i = 0; i < length; i++) {
               String genreID = midList[i].getGenre().getGenreID();
               String genreName = midList[i].getGenre().getGenreName();
               if (genreID.equals(genre.getGenreID())  && genreName.equals(genre.getGenreName())) {
                    int index = find(midList[i].getBookID());
                    bookArray = Arrays.copyOf(bookArray, bookArray.length + 1);
                    bookArray[count] = booksList[index];
                    count++;
               }
          }
          if (count == 0) {
               System.out.println("not found any books!");
               return null;
          }
          return bookArray;
     }

     // return list index of products that have contains specific string
     public Books[] relativeFind(Object originalKey, String request) {
          int count = 0;
          boolean flag = false;
          Books[] booksArray = new Books[0];
          request = request.toLowerCase().trim();

          // relative find genres
          if (originalKey instanceof BookGenres && request.equals("genre"))
               return relativeFind((BookGenres) originalKey);

          for (Books book : booksList) {
               if (originalKey instanceof String key) {
                   String author = book.getAuthor();
                    String bookName = book.getProductName();

                    // assign and check null
                    author = Validate.requiredNotNull(author) ? author.toLowerCase() : "";
                    bookName = Validate.requiredNotNull(bookName) ? bookName.toLowerCase() : "";

                    if (request.equals("name") && bookName.contains(key.toLowerCase()))
                         flag = true;

                    else if (request.equals("author") && author.contains(key.toLowerCase()))
                         flag = true;
               }
               else if (originalKey instanceof LocalDate && request.equals("released"))
                    if (book.getReleaseDate().isEqual((LocalDate) originalKey))
                         flag = true;

                    else if (originalKey instanceof BookTypes key && request.equals("type")) {
                        BookTypes types = book.getType();
                         String typeID = (Validate.requiredNotNull(types)) ? types.getTypeID() : "",
                                   typeName = (Validate.requiredNotNull(types)) ? types.getTypeName().toLowerCase() : "";
                         if (key != null)
                              if (typeID.equals(key.getTypeID()) && typeName.equals(key.getTypeName().toLowerCase()))
                                   flag = true;
                    }

                    else if (originalKey instanceof BookFormats key && request.equals("format")) {
                        BookFormats formats = book.getFormat();
                         String formatID = (Validate.requiredNotNull(formats)) ? formats.getFormatID() : "",
                                   formatName = (Validate.requiredNotNull(formats)) ? formats.getFormatName().toLowerCase() : "";
                         if (key != null)
                              if (formatID.equals(key.getFormatID()) && formatName.equals(key.getFormatName()))
                                   flag = true;
                    }

                    else if (originalKey instanceof Publishers key && request.equals("publisher")) {
                        Publishers publishers = book.getPublisher();
                         String publisherID = (Validate.requiredNotNull(publishers)) ? publishers.getPublisherID() : "",
                                   publisherName = (Validate.requiredNotNull(publishers)) ? publishers.getPublisherName().toLowerCase() : "";
                         if (key != null)
                              if (publisherID.equals(key.getPublisherID()) && publisherName.equals(key.getPublisherName()))
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

     // advanced finds
     public Books[] advancedFind(BigDecimal minPrice, BigDecimal maxPrice, String request) {
          request = request.toLowerCase().trim();
          if (minPrice.compareTo(BigDecimal.ZERO) < 0 || maxPrice.compareTo(BigDecimal.ZERO) < 0)
               return null;
          if (request.equals("range") && ((minPrice.compareTo(maxPrice) >= 0))) {
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
                         publisherName = (Validate.requiredNotNull(publishers))
                                   ? publishers.getPublisherName().toLowerCase()
                                   : "";

               // execute request
               if ((request.contains("month")) || (request.contains("year"))) {
                    inputTime = Validate.isNumber((String) originalTimeOrKey);
                    if (inputTime == -1) {
                         System.out.println("something went wrong!");
                         return null;
                    }
               }

               if ((originalKeyI instanceof String keyI) && (originalTimeOrKey instanceof String) && (request.contains("month"))) {
                   boolean keyII = book.getReleaseDate().getMonthValue() == inputTime;

                    if ((request.contains("auth")) && (author.contains(keyI.toLowerCase()) && keyII))
                         flag = true;

                    else if ((request.contains("format")) && ((formatID.equals(keyI) && keyII)
                              || (formatName.contains(keyI.toLowerCase()) && keyII)))
                         flag = true;

                    else if ((request.contains("type"))
                              && ((typeID.equals(keyI) && keyII) || (typeName.contains(keyI.toLowerCase()) && keyII)))
                         flag = true;

                    else if ((request.contains("pub")) && ((publisherID.equals(keyI) && keyII)
                              || (publisherName.contains(keyI.toLowerCase()) && keyII)))
                         flag = true;

               } else if ((originalKeyI instanceof String keyI) && (originalTimeOrKey instanceof String)
                         && (request.contains("year"))) {
                   boolean keyII = book.getReleaseDate().getYear() == inputTime;

                    if ((request.contains("auth")) && (author.contains(keyI.toLowerCase()) && keyII))
                         flag = true;

                    else if ((request.contains("format")) && ((formatID.equals(keyI) && keyII)
                              || (formatName.contains(keyI.toLowerCase()) && keyII)))
                         flag = true;

                    else if ((request.contains("type"))
                              && ((typeID.equals(keyI) && keyII) || (typeName.contains(keyI.toLowerCase()) && keyII)))
                         flag = true;

                    else if ((request.contains("pub")) && ((publisherID.equals(keyI) && keyII)
                              || (publisherName.contains(keyI.toLowerCase()) && keyII)))
                         flag = true;

               } else if ((originalKeyI instanceof String keyI) && (originalTimeOrKey instanceof String keyII)) {
                   boolean hasPub = request.contains("pub");
                    boolean hasType = request.contains("type");
                    boolean hasAuth = request.contains("auth");
                    boolean hasFormat = request.contains("for");

                    boolean isPubAndType = (hasPub && hasType) && ((publisherID.equals(keyI) && typeID.equals(keyII))
                              || (typeID.equals(keyI) && publisherID.equals(keyII))
                              || (publisherName.contains(keyI.toLowerCase()) && typeName.contains(keyII.toLowerCase()))
                              || (typeName.contains(keyI.toLowerCase())
                                        && publisherName.contains(keyII.toLowerCase())));

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
                    if (isPubAndType || isPubAndAuth || isTypeAndAuth || isPubAndFormat || isTypeAndFormat
                              || isAuthAndFormat)
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
          int choice;
          do {
               System.out.println("*".repeat(60));
               System.out.println("I. Strict search");
               System.out.println("II. Relative search");
               System.out.println("III. Advanced search");
               System.out.println("0. Exit");
               System.out.println("*".repeat(60));
               System.out.print("Enter your choice : ");
               String inputChoice = input.nextLine().trim();
               // validate if user choose 0
               if (inputChoice.equals("0")) {
                    System.out.println("Exit program.");
                    break;
               }
               choice = Validate.parseChooseHandler(inputChoice, 3);
               switch (choice) {
                    case 1:
                         System.out.println("Enter name or id of book : ");
                         String userInput = input.nextLine().trim();
                         search(userInput);
                         break;
                    case 2:
                         caseRelativeSearch();
                         break;
                    case 3:
                         caseAdvancedSearch();
                         break;
               }
          } while (choice != 0);
     }

     // case handler for relative search
     private void caseRelativeSearch() {
          int choice;
          do {
               System.out.println("*".repeat(60));
               System.out.println("I. Search by Type");
               System.out.println("II. Search by Genre");
               System.out.println("III. Search by Format");
               System.out.println("IV. Search by Publisher");
               System.out.println("V. Search by Book's name");
               System.out.println("VI. Search by Author");
               System.out.println("VII. Search by Release date");
               System.out.println("0. Exit");
               System.out.println("*".repeat(60));
               System.out.print("Enter your choice : ");
               String inputChoice = input.nextLine().trim();
               // validate if user choose 0
               if (inputChoice.equals("0")) {
                    System.out.println("Exit program.");
                    break;
               }
               choice = Validate.parseChooseHandler(inputChoice, 7);
               switch (choice) {
                    case 1:
                         if (TypesBUS.getCount() == 0)
                              break;
                         System.out.println("*".repeat(60));
                         TypesBUS.showList();
                         System.out.println("*".repeat(60));
                         do {
                              choice = Validate.parseChooseHandler(input.nextLine().trim(), TypesBUS.getCount());
                         } while (choice != -1);
                         relativeSearch(TypesBUS.getTypesList()[choice - 1], "type");
                         break;
                    case 2:
                         if (GenresBUS.getCount() == 0)
                              break;
                         System.out.println("*".repeat(60));
                         GenresBUS.showList();
                         System.out.println("*".repeat(60));
                         do {
                              choice = Validate.parseChooseHandler(input.nextLine().trim(), GenresBUS.getCount());
                         } while (choice != -1);
                         relativeSearch(GenresBUS.getGenresList()[choice - 1], "genre");
                         break;
                    case 3:
                         if (BookFormatsBUS.getCount() == 0)
                              break;
                         System.out.println("*".repeat(60));
                         BookFormatsBUS.showList();
                         System.out.println("*".repeat(60));
                         do {
                              choice = Validate.parseChooseHandler(input.nextLine().trim(), BookFormatsBUS.getCount());
                         } while (choice != -1);
                         relativeSearch(BookFormatsBUS.getFormatsList()[choice - 1], "format");
                         break;
                    case 4:
                         if (PublishersBUS.getCount() == 0)
                              break;
                         System.out.println("*".repeat(60));
                         PublishersBUS.showList();
                         System.out.println("*".repeat(60));
                         do {
                              choice = Validate.parseChooseHandler(input.nextLine().trim(), PublishersBUS.getCount());
                         } while (choice != -1);
                         relativeSearch(PublishersBUS.getPublishersList()[choice - 1], "publisher");
                         break;
                    case 5:
                         System.out.println("Enter book's name : ");
                         String name = input.nextLine().trim();
                         relativeSearch(name, "name");
                         break;
                    case 6:
                         System.out.println("Enter book's author : ");
                         String author = input.nextLine().trim();
                         relativeSearch(author, "author");
                         break;
                    case 7:
                         LocalDate date;
                         do {
                              System.out.print("Enter release date (dd-mm-yyyy) : ");
                              String dateInput = input.nextLine().trim();
                              date = Validate.isCorrectDate(dateInput);
                         } while (date == null);
                         relativeSearch(date, "released");
                         break;
               }
          } while (choice != 0);
     }

     // case handler for advanced search
     private void caseAdvancedSearch() {
          int choice, tempChoice, monthOrYear = 0;
          BigDecimal price;
          String author = "", inputDate = "";
          BookFormats format = null;
          Publishers publisher = null;
          BookTypes type = null;
          do {
               System.out.println("*".repeat(60));
               System.out.println("I. Search with min price");
               System.out.println("II. Search with max price");
               System.out.println("III. Search with range min to max price");
               System.out.println("IV. Search by Author & Month (Year) of release date");
               System.out.println("V. Search by Format & Month (Year) of release date");
               System.out.println("VI. Search by Type & Month (Year) of release date");
               System.out.println("VII. Search by Publisher & Month (Year) of release date");
               System.out.println("VIII. Search by Author & Publisher");
               System.out.println("IX. Search by Author & Type");
               System.out.println("X. Search by Author &  Format");
               System.out.println("XI. Search by Type & Publisher");
               System.out.println("XII. Search by Type & Format");
               System.out.println("XIII. Search by Format & Publisher");
               System.out.println("0. Exit");
               System.out.println("*".repeat(60));
               System.out.print("Enter your choice : ");
               String inputChoice = input.nextLine().trim();
               // validate if user choose 0
               if (inputChoice.equals("0")) {
                    System.out.println("Exit program.");
                    break;
               }
               choice = Validate.parseChooseHandler(inputChoice, 13);
               switch (choice) {
                    case 1:
                    case 2:
                         do {
                              if (choice == 1)
                                   System.out.print("Enter min price (VND) : ");
                              else if (choice == 2)
                                   System.out.print("Enter max price (VND : ");
                              String value = input.nextLine().trim();
                              price = Validate.isBigDecimal(value);
                         } while (price == null);

                         if (choice == 1)
                              advancedSearch(price, price, "min");
                         else if (choice == 2)
                              advancedSearch(price, price, "max");
                         break;
                    case 3:
                         BigDecimal maxPrice;
                         do {
                              System.out.print("Enter min price (VND : ");
                              String value = input.nextLine().trim();
                              price = Validate.isBigDecimal(value);

                              System.out.print("Enter max price (VND : ");
                              value = input.nextLine().trim();
                              maxPrice = Validate.isBigDecimal(value);
                         } while (price == null || maxPrice == null);
                         advancedSearch(price, maxPrice, "range");
                         break;

                    case 4:
                    case 5:
                    case 6:
                    case 7:
                         // get input month or year
                         int isNumber;
                         boolean valueFlag;
                         do {
                              System.out.println("I. Month || II.Year");
                              System.out.print("Enter your choice : ");
                              monthOrYear = Validate.parseChooseHandler(input.nextLine().trim(), 2);
                         } while (monthOrYear == -1);
                         if (monthOrYear == 1)
                              do {
                                   System.out.println("Enter Month value : ");
                                   inputDate = input.nextLine().trim();
                                   valueFlag = true;
                                   // validate input
                                   isNumber = Validate.isNumber(inputDate);
                                   if (isNumber > 12 || isNumber < 1) {
                                        System.out.println("Error value!");
                                        valueFlag = false;
                                   }
                              } while (!valueFlag);
                         else if (monthOrYear == 2)
                              do {
                                   System.out.println("Enter Year value : ");
                                   inputDate = input.nextLine().trim();
                                   // validate input
                                   valueFlag = true;
                                   isNumber = Validate.isNumber(inputDate);
                                   if (isNumber == -1 || isNumber > LocalDate.now().getYear() || isNumber < 1900) {
                                        System.out.println("Error value!");
                                        valueFlag = false;
                                   }
                              } while (!valueFlag);

                    case 8:
                    case 9:
                    case 10:
                    case 11:
                    case 12:
                    case 13:
                         // get other fields (use for both case 4 - > 7 and case 8 -> 9 so if case have multiple choices)
                         if (choice == 4 || choice == 8 || choice == 9 || choice == 10) {
                              System.out.println("Enter book's author : ");
                              author = input.nextLine().trim();
                         }

                         if (choice == 5 || choice == 10 || choice == 12 || choice == 13) {
                              if (BookFormatsBUS.getCount() == 0)
                                   break;
                              System.out.println("*".repeat(60));
                              BookFormatsBUS.showList();
                              System.out.println("*".repeat(60));
                              do {
                                   System.out.println("Choice book's format (Like 1, 2, 3....) : ");
                                   tempChoice = Validate.parseChooseHandler(input.nextLine(), BookFormatsBUS.getCount());
                              } while (tempChoice != -1);
                              format = BookFormatsBUS.getFormatsList()[tempChoice - 1];
                         }

                         if (choice == 6 || choice == 9 || choice == 11 || choice == 12) {
                              if (TypesBUS.getCount() == 0)
                                   break;
                              System.out.println("*".repeat(60));
                              TypesBUS.showList();
                              System.out.println("*".repeat(60));
                              do {
                                   System.out.println("Choice book's type (Like 1, 2, 3....) : ");
                                   tempChoice = Validate.parseChooseHandler(input.nextLine(), TypesBUS.getCount());
                              } while (tempChoice != -1);
                              type = TypesBUS.getTypesList()[tempChoice - 1];
                         }

                         if (choice == 7 || choice == 8 || choice == 11 || choice == 13) {
                              if (PublishersBUS.getCount() == 0)
                                   break;
                              System.out.println("*".repeat(60));
                              PublishersBUS.showList();
                              System.out.println("*".repeat(60));
                              do {
                                   System.out.println("Choice book's publisher (Like 1, 2, 3....) : ");
                                   tempChoice = Validate.parseChooseHandler(input.nextLine(), PublishersBUS.getCount());
                              } while (tempChoice != -1);
                              publisher = PublishersBUS.getPublishersList()[tempChoice - 1];
                         }

                         // if case is 4 -> 7
                         if (!inputDate.isEmpty() && monthOrYear != 0) {
                              if (!author.isEmpty())
                                   advancedSearch(author, inputDate, monthOrYear == 1 ? "auth-month" : "auth-year");
                              else if (format != null)
                                   advancedSearch(format.getFormatName(), inputDate, monthOrYear == 1 ? "format-month" : "format-year");
                              else if (type != null)
                                   advancedSearch(type.getTypeName(), inputDate, monthOrYear == 1 ? "type-month" : "type-year");
                              else if (publisher != null)
                                   advancedSearch(publisher.getPublisherName(), inputDate, monthOrYear == 1 ? "pub-month" : "pub-year");
                              // reset value
                              inputDate = "";
                              monthOrYear = 0;
                              break;
                         }

                         // if case 8 -> 13
                         if (choice == 8)
                              advancedSearch(author, publisher, "auth-pub");
                         else if (choice == 9)
                              advancedSearch(author, type, "auth-type");
                         else if (choice == 10)
                              advancedSearch(author, format, "auth-format");
                         else if (choice == 11)
                              advancedSearch(type, publisher, "type-pub");
                         else if (choice == 12)
                              advancedSearch(type, format, "type-format");
                         else if (choice == 13)
                              advancedSearch(format, publisher, "format-pub");
                         break;
               }
          } while (choice != 0);
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
          int choice;
          do {
               System.out.println("*".repeat(60));
               System.out.println("I. Add book");
               System.out.println("II. Add list of books");
               System.out.println("0. Exit");
               System.out.println("*".repeat(60));
               System.out.print("Enter your choice : ");
               String inputChoice = input.nextLine().trim();
               // validate if user choose 0
               if (inputChoice.equals("0")) {
                    System.out.println("Exit program.");
                    break;
               }
               choice = Validate.parseChooseHandler(inputChoice, 2);
               // try catch for execute file after add
               try {
                    switch (choice) {
                         case 1:
                              Books newBook = new Books();
                              newBook.setInfo();
                              // confirm
                              System.out.printf("| %s %s %s |\n", "I.Cancel", "-".repeat(20), "II.Add");
                              do {
                                   System.out.print("choose option (1 or 2) : ");
                                   String option = input.nextLine().trim();
                                   choice = Validate.parseChooseHandler(option, 2);
                              } while (choice == -1);
                              if (choice == 1)
                                   break;
                              add(newBook);
                              writeFile();
                              break;
                         case 2:
                              int count = 0;
                              Books[] list = new Books[0];
                              do {
                                   System.out.print("Enter total books you wanna add : ");
                                   String option = input.nextLine().trim();
                                   choice = Validate.isNumber(option);
                              } while (choice == -1);
                              // for loop with input time
                              for (int i = 0; i < choice; i++) {
                                   Books book = new Books();
                                   book.setInfo();
                                   list = Arrays.copyOf(list, list.length + 1);
                                   list[count] = book;
                                   count++;
                              }

                              // confirm
                              System.out.printf("| %s %s %s |\n", "I.Cancel", "-".repeat(20), "II.Add");
                              do {
                                   System.out.print("choose option (1 or 2) : ");
                                   String option = input.nextLine().trim();
                                   choice = Validate.parseChooseHandler(option, 2);
                              } while (choice == -1);
                              if (choice == 1)
                                   break;
                              add(list);
                              writeFile();
                              break;
                    }
               } catch (Exception e) {
                    System.out.printf("error writing file!\nt%s\n", e.getMessage());
               }
          } while (choice != 0);
     }

     @Override
     public void add(Object newBook) {
          if (newBook instanceof Books book) {
              book.setProductID(book.getProductID());
               booksList = Arrays.copyOf(booksList, booksList.length + 1);
               booksList[count] = book;
               count++;
          } else
               System.out.println("your new book have something not like book!");
     }

     public void add(Books[] newBooks) {
          int tempIndex = 0, newListLength = newBooks.length;
          int initCount = this.getCount();
          int total = initCount + newListLength;
          booksList = Arrays.copyOf(booksList, booksList.length + newListLength);

          for (int i = initCount; i < total; i++, tempIndex++) {
               newBooks[tempIndex].setProductID(newBooks[tempIndex].getProductID());
               booksList[i] = newBooks[tempIndex];
          }
          this.count = total;
     }

     // *edit methods (TEST DONE)
     @Override
     public void edit() {
          int choice;
          do {
               System.out.println("*".repeat(60));
               System.out.println("I. Edit name");
               System.out.println("II. Edit release date");
               System.out.println("III. Edit price");
               System.out.println("IV. Edit quantity");
               System.out.println("V. Edit author");
               System.out.println("VI. Edit publisher");
               System.out.println("VII. Edit type");
               System.out.println("VIII. Edit genres");
               System.out.println("IX. Edit format");
               System.out.println("X. Edit packaging size");
               System.out.println("0. Exit");
               System.out.println("*".repeat(60));
               System.out.print("Enter your choice : ");
               String inputChoice = input.nextLine().trim();
               // validate if user choose 0
               if (inputChoice.equals("0")) {
                    System.out.println("Exit program.");
                    break;
               }

               choice = Validate.parseChooseHandler(inputChoice, 10);
               System.out.println("Enter name or id of book : ");
               String userInput = input.nextLine().trim();

               // if case
               try {
                    if (choice == 1)
                         edit(userInput);
                    else if (choice == 2)
                         editReleaseDate(userInput);
                    else if (choice == 3)
                         editPrice(userInput);
                    else if (choice == 4)
                         editQuantity(userInput);
                    else if (choice == 5)
                         editAuthor(userInput);
                    else if (choice == 6)
                         editPublisher(userInput);
                    else if (choice == 7)
                         editType(userInput);
                    else if (choice == 8)
                         editGenre(userInput);
                    else if (choice == 9)
                         editFormat(userInput);
                    else if (choice == 10)
                         editPackagingSize(userInput);
                    else 
                         break;
                    // update file
                    writeFile();
               } catch (Exception e) {
                    System.out.printf("error writing file!\nt%s\n", e.getMessage());
               }
          } while (true);
     }

     // edit name
     @Override
     public void edit(String bookNameOrID) {
          int index = find(bookNameOrID);
          if (index != -1) {
               String name;
               int userChoice;
               // show list for user choice
               booksList[index].showInfo();
               System.out.printf("| %s %s %s |\n", "I.Cancel", "-".repeat(20), "II.Edit");
               do {
                    System.out.print("choose option (1 or 2) : ");
                    String option = input.nextLine().trim();
                    userChoice = Validate.parseChooseHandler(option, 2);
               } while (userChoice == -1);
               if (userChoice == 1)
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
     public void editReleaseDate(String bookNameOrID) {
          int index = find(bookNameOrID);
          if (index != -1) {
               LocalDate date;
               int userChoice;
               // show option for user choice
               booksList[index].showInfo();
               System.out.printf("| %s %s %s |\n", "I.Cancel", "-".repeat(20), "II.Edit");
               do {
                    System.out.print("choose option (1 or 2) : ");
                    String option = input.nextLine().trim();
                    userChoice = Validate.parseChooseHandler(option, 2);
               } while (userChoice == -1);
               if (userChoice == 1)
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
     public void editPrice(String bookNameOrID) {
          int index = find(bookNameOrID);
          if (index != -1) {
               BigDecimal price;
               int userChoice;
               // show list for user choice
               booksList[index].showInfo();
               System.out.printf("| %s %s %s |\n", "I.Cancel", "-".repeat(20), "II.Edit");
               do {
                    System.out.print("choose option (1 or 2) : ");
                    String option = input.nextLine().trim();
                    userChoice = Validate.parseChooseHandler(option, 2);
               } while (userChoice == -1);
               if (userChoice == 1)
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
     public void editQuantity(String bookNameOrID) {
          int index = find(bookNameOrID);
          if (index != -1) {
               int quantity, userChoice;
               // show list for user choice
               booksList[index].showInfo();
               System.out.printf("| %s %s %s |\n", "I.Cancel", "-".repeat(20), "II.Edit");
               do {
                    System.out.print("choose option (1 or 2) : ");
                    String option = input.nextLine().trim();
                    userChoice = Validate.parseChooseHandler(option, 2);
               } while (userChoice == -1);
               if (userChoice == 1)
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
     public void editAuthor(String bookNameOrID) {
          int index = find(bookNameOrID);
          if (index != -1) {
               String authorName;
               int userChoice;
               // show list for user choice
               booksList[index].showInfo();
               System.out.printf("| %s %s %s |\n", "I.Cancel", "-".repeat(20), "II.Edit");
               do {
                    System.out.print("choose option (1 or 2) : ");
                    String option = input.nextLine().trim();
                    userChoice = Validate.parseChooseHandler(option, 2);
               } while (userChoice == -1);
               if (userChoice == 1)
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
     public void editPublisher(String bookNameOrID) {
          if (PublishersBUS.getCount() == 0) {
               System.out.println("not have any publisher for edit!");
               return;
          }

          int index = find(bookNameOrID);
          if (index != -1) {
               int userChoice;
               // show list for user choice
               booksList[index].showInfo();
               System.out.printf("| %s %s %s |\n", "I.Cancel", "-".repeat(20), "II.Edit");
               do {
                    System.out.print("choose option (1 or 2) : ");
                    String option = input.nextLine().trim();
                    userChoice = Validate.parseChooseHandler(option, 2);
               } while (userChoice == -1);
               if (userChoice == 1)
                    return;

               PublishersBUS.showList();
               do {
                    System.out.print("choose publisher (like 1, 2,etc...): ");
                    String option = input.nextLine().trim();
                    userChoice = Validate.parseChooseHandler(option, PublishersBUS.getCount());
               } while (userChoice == -1);

               Publishers publisher = PublishersBUS.getPublishersList()[userChoice - 1];
               booksList[index].setPublisher(publisher);
          }
     }

     // edit type
     public void editType(String bookNameOrID) {
          if (TypesBUS.getCount() == 0) {
               System.out.println("not have any type for edit!");
               return;
          }

          int index = find(bookNameOrID);
          if (index != -1) {
               int userChoice;
               // show list for user choice
               booksList[index].showInfo();
               System.out.printf("| %s %s %s |\n", "I.Cancel", "-".repeat(20), "II.Edit");
               do {
                    System.out.print("choose option (1 or 2) : ");
                    String option = input.nextLine().trim();
                    userChoice = Validate.parseChooseHandler(option, 2);
               } while (userChoice == -1);
               if (userChoice == 1)
                    return;

               TypesBUS.showList();
               do {
                    System.out.print("choose type you want (like \\\"1, 2, 3,etc....\\\"): ");
                    String option = input.nextLine().trim();
                    userChoice = Validate.parseChooseHandler(option, TypesBUS.getCount());
               } while (userChoice == -1);

               BookTypes type = TypesBUS.getTypesList()[userChoice - 1];
               booksList[index].setType(type);
          }
     }

     // edit genre
     public void editGenre(String bookNameOrID) {
          try {
               int index = find(bookNameOrID);
               if (index != -1) {
                    MidForBooksBUS genres = new MidForBooksBUS();
                    genres.readFile();
                    MidForBooks[] nowGenres = genres.relativeFind(bookNameOrID);
                    int[] list = new int[0];
                    int userChoice, count = 0;
                    BookGenres[] listGenres = new BookGenres[0];

                    // show list of now genres for user know
                    MidForBooksBUS.showAsTable(nowGenres);
                    System.out.printf("| %s %s %s |\n", "I.Cancel", "-".repeat(20), "II.Edit");
                    do {
                         System.out.print("choose option (1 or 2) : ");
                         String option = input.nextLine().trim();
                         userChoice = Validate.parseChooseHandler(option, 2);
                    } while (userChoice == -1);
                    if (userChoice == 1)
                         return;

                    // show list for user choice
                    if (GenresBUS.getCount() == 0) // if not have any genres
                         return;
                    GenresBUS.showList();
                    System.out.println("-".repeat(110));
                    do {
                         System.out.print("choose genres (like 1, 2,etc...): ");
                         String options = input.nextLine().trim();
                         String[] splitOptions = options.split(" ");

                         if (Validate.hasDuplicates(splitOptions)) {
                              System.out.println("has duplicate! please try again!");
                              continue;
                         }

                         for (String item : splitOptions) {
                              userChoice = Validate.parseChooseHandler(item, GenresBUS.getCount());
                              if (userChoice == -1) {
                                   count = 0;
                                   break;
                              }
                              list = Arrays.copyOf(list, list.length + 1);
                              list[count] = userChoice;
                              count++;
                         }
                    } while (count == 0);

                    for (int i = 0; i < count; i++) {
                         int option = list[i];
                         BookGenres genre = GenresBUS.getGenresList()[option - 1];
                         listGenres = Arrays.copyOf(listGenres, i + 1);
                         listGenres[i] = genre;
                    }

                    // execute all user choice (remove old genres and add new genres)
                    genres.remove(bookNameOrID);
                   for (BookGenres listGenre : listGenres)
                        genres.add(new MidForBooks(bookNameOrID, listGenre));
                   genres.writeFile();
               }
          } catch (Exception err) {
               System.out.printf("error when executing file!\nt%s\n", err.getMessage());
          }

     }

     // edit format
     public void editFormat(String bookNameOrID) {
          if (BookFormatsBUS.getCount() == 0) {
               System.out.println("not have any format for edit!");
               return;
          }

          int index = find(bookNameOrID);
          if (index != -1) {
               int userChoice;
               // show list for user choice
               booksList[index].showInfo();
               System.out.printf("| %s %s %s |\n", "I.Cancel", "-".repeat(20), "II.Edit");
               do {
                    System.out.print("choose option (1 or 2) : ");
                    String option = input.nextLine().trim();
                    userChoice = Validate.parseChooseHandler(option, 2);
               } while (userChoice == -1);
               if (userChoice == 1)
                    return;

               if (BookFormatsBUS.getCount() == 0)
                    return;
               BookFormatsBUS.showList();
               do {
                    System.out.print("choose format (like 1, 2,etc...): ");
                    String option = input.nextLine().trim();
                    userChoice = Validate.parseChooseHandler(option, BookFormatsBUS.getCount());
               } while (userChoice == -1);

               BookFormats format = BookFormatsBUS.getFormatsList()[userChoice - 1];
               booksList[index].setFormat(format);
          }
     }

     // edit packaging size
     public void editPackagingSize(String bookNameOrID) {
          int index = find(bookNameOrID);
          if (index != -1) {
               String packagingSize;
               int userChoice;
               // show list for user choice
               booksList[index].showInfo();
               System.out.printf("| %s %s %s |\n", "I.Cancel", "-".repeat(20), "II.Edit");
               do {
                    System.out.print("choose option (1 or 2) : ");
                    String option = input.nextLine().trim();
                    userChoice = Validate.parseChooseHandler(option, 2);
               } while (userChoice == -1);
               if (userChoice == 1)
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
          int choice;
          do {
               System.out.println("*".repeat(60));
               System.out.println("I. Remove");
               System.out.println("0. Exit");
               System.out.println("*".repeat(60));
               System.out.print("Enter your choice : ");
               String inputChoice = input.nextLine().trim();
               // validate if user choose 0
               if (inputChoice.equals("0")) {
                    System.out.println("Exit program.");
                    break;
               }
               choice = Validate.parseChooseHandler(inputChoice, 1);
               try {
                    System.out.println("Enter name or id of book : ");
                    String userInput = input.nextLine().trim();
                    remove(userInput);
                    writeFile();
               } catch (Exception e) {
                    System.out.printf("error writing file!\nt%s\n", e.getMessage());
               }
          } while (choice != 0);
     }

     @Override
     public void remove(String bookNameOrID) {
          int index = find(bookNameOrID);
          if (index != -1) {
               int userChoice;
               // show list for user choice
               booksList[index].showInfo();
               System.out.printf("| %s %s %s |\n", "I.Cancel", "-".repeat(20), "II.Remove");
               do {
                    System.out.print("choose option (1 or 2) : ");
                    String option = input.nextLine().trim();
                    userChoice = Validate.parseChooseHandler(option, 2);
               } while (userChoice == -1);
               if (userChoice == 1)
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
                    new BufferedOutputStream(new FileOutputStream("src/main/resources/Books", false)))) {
               file.writeInt(count);
               for (Books book : booksList) {
                    file.writeUTF(book.getProductID());
                    file.writeUTF(book.getProductName());
                    file.writeUTF(book.getProductPrice().setScale(0, RoundingMode.UNNECESSARY).toString());
                    file.writeUTF(book.getReleaseDate().toString());
                    file.writeUTF(book.getAuthor());
                    file.writeUTF(book.getPublisher().getPublisherID());
                    file.writeUTF(book.getType().getTypeID());
                    file.writeInt(book.getQuantity());
                    file.writeUTF(book.getFormat().getFormatID());
                    file.writeUTF(book.getPackagingSize());
               }
          } catch (Exception err) {
               System.out.printf("error writing file!\nt%s\n", err.getMessage());
          }
     }

     // update quantity
     public void updateQuantity (GRNDetails[] list) {
          for (GRNDetails detail : list) {
               int index = find(detail.getProduct().getProductID());
               if (index != -1) {
                    booksList[index].setQuantity(booksList[index].getQuantity() + detail.getQuantity());
               }
          }
     }

    public void updateQuantity(BillDetails[] list) {
        for(BillDetails detail : list) {
            int index = find(detail.getProducts().getProductID());
            if (index != -1)
                booksList[index].setQuantity(booksList[index].getQuantity() - detail.getQuantity());
        }
    }

     // read file
     public void readFile() throws IOException {
          File testFile = new File("src/main/resources/Books");
          if (testFile.length() == 0 || !testFile.exists())
               return;

          try (DataInputStream file = new DataInputStream(
                    new BufferedInputStream(new FileInputStream("src/main/resources/Books")))) {
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
