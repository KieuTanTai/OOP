package BUS;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Scanner;
import DTO.BookTypes;
import DTO.Books;
import util.Validate;

public class BooksBUS implements RuleSets {
     private Books[] booksList;
     private int many;
     private final Scanner input = new Scanner(System.in);

     // constructors
     public BooksBUS () {
          this.many = 0;
          this.booksList = new Books[0];
     }

     public BooksBUS (int many, Books[] booksList) {
          this.many = many;
          this.booksList = booksList;
     }

     // when have existences booksArray before
     public BooksBUS (BooksBUS booksArray) {
          this.many = booksArray.many;
          this.booksList = booksArray.booksList;
     }

     // getter / setter
     public Books[] getBooksList () {
          return this.booksList;
     }

     public int getIndex () {
          return this.many;
     }

     public void setBooksList (Books[] newBooksList) {
          this.booksList = newBooksList;
     }

     public void setIndex (int newQuantity) {
          this.many = newQuantity;
     }

     // add find remove search....
     public int find (String inputValue) {
          for (int i = 0; i < booksList.length; i++)
               if (booksList[i].getProductId().equals(inputValue) || booksList[i].getProductName().equals(inputValue))
                    return i;
          return -1;
     }

     // return list index of products that have contain specific string
     public int[] relativeFind (String inputValue) {
          int index = 0;
          int[] tempInt = new int[index];
          for (int i = 0; i < booksList.length; i++)
               if (booksList[i].getProductName().contains(inputValue)) {
                    tempInt[index] = i;
                    index++;
               }
          if (index == 0)
               return null;
          return tempInt;
     }

     public void add (Object newBook) {
          if (newBook != null && newBook instanceof Books ) {
               booksList = Arrays.copyOf(booksList, booksList.length + 1);
               booksList[many] = (Books) newBook;
               many++;
          }
          else 
               System.out.println("your new book is not instance of Books!");
     }

     // search methods
     public void search (String inputValue) {
          int indexBook = find(inputValue);
          if (indexBook != -1) {
               String toStringHandler = composeUsingFormatter(indexBook);
               System.out.printf("your book id / name is : %s\nyour book detail : \n%s", inputValue, toStringHandler);
          }
          else
               System.out.println("your book is not found!");
     }

     public void relativeSearch (String inputValue) {
          int[] indexList = relativeFind(inputValue);
          if (indexList.equals(null)) {
               System.out.println("not found any books!");
               return;
          }
          for (int i = 0; i < indexList.length; i++)
               System.out.printf("total books found : %d\ndetail : %s\n", indexList.length, composeUsingFormatter (indexList[i]));
     }

     public void advancedSearch () {

     }

     // remove methods
     public void remove (String inputId) {
          int indexBook = find(inputId);
          if (indexBook != -1) {
               for (int i = indexBook; i < booksList.length - 1; i++) 
                    booksList[i] = booksList[i + 1];
               booksList = Arrays.copyOf(booksList, booksList.length - 1);
          }
     }

     // edit methods
     // edit name
     public void edit (String bookId) {
          int index = find(bookId);
          if (index == -1) {
               System.out.println("your book is not exist !");
               return;
          }
          String newName = "";
          do {
               System.out.print("enter a new name for this book: ");
               newName = input.nextLine();
          }while (Validate.validateName(newName));
          booksList[index].setProductName(newName);    
     }

     // edit release date
     public void edit (String bookId, LocalDate newDate) {
          int index = find(bookId);
          if (index == -1) {
               System.out.println("your book is not exist !");
               return;
          }
          booksList[index].setReleaseDate(newDate);    
     }

     // edit price
     public void edit (String bookId, BigDecimal newPrice) {
          int index = find(bookId);
          if (index == -1) {
               System.out.println("your book is not exist !");
               return;
          }
          booksList[index].setProductPrice(newPrice);
     }

     // edit quantity
     public void edit (String bookId, int newQuantity) {
          int index = find(bookId);
          if (index == -1) {
               System.out.println("your book is not exist !");
               return;
          }
          booksList[index].setQuantity(newQuantity);
     }
     
     // edit author
     public void edit (String bookId, String newAuthor) {
          int index = find(bookId);
          if (index == -1) {
               System.out.println("your book is not exist !");
               return;
          }
          booksList[index].setAuthor(newAuthor);
     }

     // edit type
     public void edit (String bookId, BookTypes newType) {
          int index = find(bookId);
          if (index == -1) {
               System.out.println("your book is not exist !");
               return;
          }
          booksList[index].setType(newType);
     }

     // edit format
     public void editFormat (String bookId, String newFormat) {
          int index = find(bookId);
          if (index == -1) {
               System.out.println("your book is not exist !");
               return;
          }
          booksList[index].setFormat(newFormat);
     }

     // edit packaging size
     public void editPackagingSize(String bookId, String newPackagingSize) {
          int index = find(bookId);
          if (index == -1) {
               System.out.println("your book is not exist !");
               return;
          }
          booksList[index].setPackagingSize(newPackagingSize);
     }

     // some other methods
     private String composeUsingFormatter (int isFindId) {
          Books book = booksList[isFindId];
          return String.format(" publisher name: %s\n author: %s\n book type: %s\n format: %s\n packaging size: %s\n", 
          book.getPublisherId(), book.getAuthor(), book.getTypeName(), book.getFormat(), book.getPackagingSize());
     }
}
