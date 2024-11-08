package BUS;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Scanner;
import DTO.BookTypes;
import DTO.Books;
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
     // strict find 
     @Override
     public int find (String inputValue) {
          for (int i = 0; i < booksList.length; i++)
               if (booksList[i].getProductID().equals(inputValue) || booksList[i].getProductName().equals(inputValue))
                    return i;
          System.out.println("your book is not exist! ");
          return -1;
     }

     // relative find
     // return list index of products that have contains specific string
     public Books[] relativeFind (String name) {
          int count = 0;
          Books[] booksArray = new Books[0];
         for (Books books : booksList)
             if (books.getProductName().contains(name)) {
                 booksArray = Arrays.copyOf(booksArray, booksArray.length + 1);
                 booksArray[count] = books;
                 count++;
             }
          if (count == 0)
               return null;
          return booksArray;
     }

     // add method (DONE)
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

     // search methods (CONTINUE)
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
     public void relativeSearch (String name) {
          Books[] indexList = relativeFind(name);
          if (indexList == null) {
               System.out.println("not found any books!");
               return;
          }
         for (Books books : indexList)
             System.out.printf("book's id : %s\ndetail : %s\n", books.getProductID(), composeUsingFormatter(books));
     }

     // advanced search
     public void advancedSearch () {

     }

     // remove method ()
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

     // edit methods
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

     // some other methods
     private String composeUsingFormatter (Books book) {
          return String.format(" publisher name: %s\n author: %s\n book type: %s\n format: %s\n packaging size: %s\n", 
          book.getPublisherName(), book.getAuthor(), book.getTypeName(), book.getFormat(), book.getPackagingSize());
     }

     @Override
     public void add() {
          // TODO Auto-generated method stub
          throw new UnsupportedOperationException("Unimplemented method 'add'");
     }

     @Override
     public int find() {
          // TODO Auto-generated method stub
          throw new UnsupportedOperationException("Unimplemented method 'find'");
     }

     @Override
     public void search() {
          // TODO Auto-generated method stub
          throw new UnsupportedOperationException("Unimplemented method 'search'");
     }

     @Override
     public void remove() {
          // TODO Auto-generated method stub
          throw new UnsupportedOperationException("Unimplemented method 'remove'");
     }

     @Override
     public void edit() {
          // TODO Auto-generated method stub
          throw new UnsupportedOperationException("Unimplemented method 'edit'");
     }
}
