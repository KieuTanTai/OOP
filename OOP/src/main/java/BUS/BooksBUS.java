package BUS;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Scanner;

import DTO.BookTypes;
import DTO.Books;
import DTO.Publishers;
import Manager.Menu;
import util.Validate;

public class BooksBUS implements IRuleSets {
     private Books[] booksList;
     private int count;
     private final Scanner input = new Scanner(System.in);

     // constructors
     public BooksBUS () {
          this.count = 0;
          this.booksList = new Books[0];
     }

     public BooksBUS (int count, Books[] booksList) {
          this.count = count;
          this.booksList = booksList;
     }

     // when have existences booksArray before
     public BooksBUS (BooksBUS booksArray) {
          this.count = booksArray.count;
          this.booksList = booksArray.booksList;
     }

     // getter / setter
     public Books[] getBooksList () {
          return this.booksList;
     }

     public Books getBook (String id) {
          for (int i = 0; i < count; i++) 
               if (booksList[i].getProductID().equals(id))
                    return booksList[i];
          return null;
     }

     public int getCount () {
          return this.count;
     }

     public void setBooksList (Books[] newBooksList) {
          this.booksList = newBooksList;
     }

     public void setCount (int newQuantity) {
          this.count = newQuantity;
     }

     // all others methods like: add remove edit find show....
     // find methods (DONE)
     @Override
     public void find() {
          Menu.findHandler();
     }

     // strict find 
     @Override
     public int find (String inputValue) {
          for (int i = 0; i < booksList.length; i++)
               if (booksList[i].getProductID().equals(inputValue) || booksList[i].getProductName().equals(inputValue))
                    return i;
          System.out.println("your book is not exist! ");
          return -1;
     }

     // relative finds
     // return list index of products that have contains specific string
     public Books[] relativeFind (Object originalKey, String request) {
          int count = 0;
          boolean flag = false;
          Books[] booksArray = new Books[0];
          for (Books books : booksList) {
               if (originalKey instanceof String) {
                    String key = (String) originalKey;
                    if (request.equals("name") && books.getProductName().contains(key))
                         flag = true;
                    else if (request.equals("author") && books.getAuthor().equals(key))
                         flag = true;
                    else if (request.equals("publisher") && books.getPublisher().getPublisherID().equals(key))
                         flag = true;
                    else if (request.equals("type") && books.getType().getTypeID().equals(key))
                         flag = true;
               }
               else if (originalKey instanceof LocalDate)
                    if (request.equals("releaseDate") && books.getReleaseDate().equals((LocalDate) originalKey))
                         flag = true;              

               if (flag) {
                    booksArray = Arrays.copyOf(booksArray, booksArray.length + 1);
                    booksArray[count] = books;
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
     public Books[] advancedFind (BigDecimal minPrice, BigDecimal maxPrice, String request) {
          int count = 0;
          boolean flag = false;
          Books[] booksArray = new Books[0];
          for (Books books : booksList) {
               BigDecimal productPrice = books.getProductPrice();
               if ((request.equals("min")) && (productPrice.compareTo(minPrice) >= 0))
                    flag = true;
               else if ((request.equals("max")) && (productPrice.compareTo(maxPrice) <= 0))
                    flag = true;
               else if (request.equals("range")) 
                    if ((productPrice.compareTo(minPrice) >= 0) && (productPrice.compareTo(maxPrice) <= 0))
                         flag = true;

               if (flag) {
                    booksArray = Arrays.copyOf(booksArray, booksArray.length + 1);
                    booksArray[count] = books;
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

     public Books[] advancedFind (Object originalKeyI, Object originalTimeOrKey, String request) {
          int count = 0;
          boolean flag = false;
          Books[] booksArray = new Books[0];
          for (Books books : booksList) {
               if ((originalKeyI instanceof String) && (originalTimeOrKey instanceof String) && (request.contains("Month"))) {
                    String keyI = (String) originalKeyI;
                    boolean keyII =  books.getReleaseDate().getMonthValue() == (int) originalTimeOrKey;
                    if ((request.equals("PubMonth")) && (books.getPublisher().getPublisherID().equals(keyI) && keyII))
                         flag = true;
                    else if ((request.equals("AuthMonth")) && (books.getAuthor().equals(keyI) && keyII))
                         flag = true;
                    else if ((request.equals("TypeMonth")) && (books.getType().getTypeID().equals(keyI) && keyII))
                         flag = true;
               }
               else if ((originalKeyI instanceof String) && (originalTimeOrKey instanceof String) && (request.contains("year"))) {
                    String keyI = (String) originalKeyI;
                    boolean keyII =  books.getReleaseDate().getYear() == (int) originalTimeOrKey;
                    if ((request.equals("PubYear")) && (books.getPublisher().getPublisherID().equals(keyI) && keyII))
                         flag = true;
                    else if ((request.equals("AuthYear")) && (books.getAuthor().equals(keyI) && keyII))
                         flag = true;
                    else if ((request.equals("TypeYear")) && (books.getType().getTypeID().equals(keyI) && keyII))
                         flag = true;
               }
               else if ((originalKeyI instanceof String) && (originalTimeOrKey instanceof String)) {
                    String keyI = (String) originalKeyI, keyII = (String) originalTimeOrKey;
                    if ((request.equals("PubType")) && (books.getPublisher().getPublisherID().equals(keyI) && books.getType().getTypeID().equals(keyII)))
                         flag = true;
                    else if ((request.equals("PubAuth")) && (books.getPublisher().getPublisherID()).equals(keyI) && books.getAuthor().equals(keyII))
                         flag = true;
                    else if ((request.equals("TypeAuth")) && (books.getType().getTypeID().equals(keyI) && books.getAuthor().equals(keyII)))
                         flag = true;
               }

               if (flag) {
                    booksArray = Arrays.copyOf(booksArray, booksArray.length + 1);
                    booksArray[count] = books;
                    flag = false;
                    count++;
               }
          }
          if (count == 0) {
               System.out.println("not found any books with the specified price!");
               return null;
          }
          return booksArray;
     }

     // search methods (CONTINUE)
     @Override
     public void search() {
          Menu.searchHandler();
     }

     // strict search 
     @Override
     public void search (String inputValue) {
          int index = find(inputValue);
          if (index != -1) {
               String toStringHandler = composeUsingFormatter(booksList[index]);
               System.out.printf("book's id / name is : %s\nbook detail : \n%s", inputValue, toStringHandler);
          }
     }

     // relative search
     public void relativeSearch (Object key, String request) {
          Books[] list = relativeFind(key, request);
          if (list != null)
               for (Books books : list)
                   System.out.printf("book's id : %s\ndetail : %s\n", books.getProductID(), composeUsingFormatter(books));
     }

     // advanced search
     public void advancedSearch (Object keyI, Object timeOrKey, String request) {
          Books[] list = advancedFind(keyI, timeOrKey, request);
          if (list != null)
               for (Books books : list)
                   System.out.printf("book's id : %s\ndetail : %s\n", books.getProductID(), composeUsingFormatter(books));
          
     }

     // add methods (DONE)
     @Override
     public void add() {
          Menu.addHandler();
     }

     @Override
     public void add (Object newBook) {
          if (newBook instanceof Books) {
               booksList = Arrays.copyOf(booksList, booksList.length + 1);
               booksList[count] = (Books) newBook;
               count++;
          }
          else 
               System.out.println("your new book have something not like book!");
     }

     // edit methods (DONE)
     @Override
     public void edit() {
          Menu.editHandler();
     }

     // edit name
     @Override
     public void edit (String bookId) {
          int index = find(bookId);
          if (index != -1) {
               String newName;
               do {
                    System.out.print("enter a new name for this book: ");
                    newName = input.nextLine();
               }while (Validate.checkName(newName));
               booksList[index].setProductName(newName);    
          }
     }

     // edit release date
     public void edit (String id, LocalDate newDate) {
          int index = find(id);
          if (index != -1) 
               booksList[index].setReleaseDate(newDate);    
     }

     // edit price
     public void edit (String id, BigDecimal newPrice) {
          int index = find(id);
          if (index != -1) 
               booksList[index].setProductPrice(newPrice);
     }

     // edit quantity
     public void edit (String id, int newQuantity) {
          int index = find(id);
          if (index != -1) 
               booksList[index].setQuantity(newQuantity);
     }
     
     // edit author
     public void edit (String id, String newAuthor) {
          int index = find(id);
          if (index == -1) {
               System.out.println("your book is not exist !");
               return;
          }
          booksList[index].setAuthor(newAuthor);
     }

     // edit type
     public void edit (String id, BookTypes newType) {
          int index = find(id);
          if (index == -1) {
               System.out.println("your book is not exist !");
               return;
          }
          booksList[index].setType(newType);
     }

     // edit format
     public void editFormat (String id, String newFormat) {
          int index = find(id);
          if (index == -1) {
               System.out.println("your book is not exist !");
               return;
          }
          booksList[index].setFormat(newFormat);
     }

     // edit packaging size
     public void editPackagingSize(String id, String newPackagingSize) {
          int index = find(id);
          if (index == -1) {
               System.out.println("your book is not exist !");
               return;
          }
          booksList[index].setPackagingSize(newPackagingSize);
     }

     // remove methods ()
     @Override
     public void remove() {
          Menu.removeHandler();
     }

     @Override
     public void remove (String inputId) {
          int index = find(inputId);
          if (index != -1) {
               for (int i = index; i < booksList.length - 1; i++) 
                    booksList[i] = booksList[i + 1];
               booksList = Arrays.copyOf(booksList, booksList.length - 1);
               count --;
          }
     }

     // execute files
     //write file
     public void writeFile () throws IOException {
          try (DataOutputStream file = new DataOutputStream(new FileOutputStream("../../resources/ListGenres", false))) {
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
                    file.writeUTF(booksList[i].getFormat());
                    file.writeUTF(booksList[i].getPackagingSize());
                    file.writeUTF(System.lineSeparator());
               }
               System.out.println("write done!");
          } catch (FileNotFoundException err) {
               System.out.printf("404 not found!\n%s", err);
          }
     }


     // read file
     public void readFile () throws IOException {
          try (DataInputStream file = new DataInputStream(new FileInputStream("../../resources/ListGenres"))) {
               count = file.readInt();
               Books[] list = new Books[count];
               for (int i = 0; i < count; i++) {
                    String productID =  file.readUTF();
                    String productName = file.readUTF();
                    BigDecimal price = new BigDecimal(file.readUTF());
                    LocalDate releaseDate = LocalDate.parse(file.readUTF());
                    String author = file.readUTF();
                    String publisherID = file.readUTF();  //use as param for query publisher from class Publishers
                    String typeID = file.readUTF();
                    int quantity = file.readInt();
                    String format = file.readUTF();
                    String packagingSize = file.readUTF();
                    file.readUTF();

                    // execute IDs
                    Publishers publisher = PublishersBUS.getPublisher(publisherID);
                    BookTypes type = TypesBUS.getType(typeID);
                    list[i] = new Books(productID, productName, releaseDate, price, quantity, publisher, author, type, format, packagingSize);
               }
               setCount(count);
               setBooksList(list);
          } catch (FileNotFoundException err) {
               System.out.printf("404 not found!\n%s", err);
          }
     }

     // some other methods
     private String composeUsingFormatter (Books book) {
          return String.format(" publisher name: %s\n author: %s\n book type: %s\n format: %s\n packaging size: %s\n", 
          book.getPublisher().getPublisherName(), book.getAuthor(), book.getType().getTypeName(), book.getFormat(), book.getPackagingSize());
     }
}
