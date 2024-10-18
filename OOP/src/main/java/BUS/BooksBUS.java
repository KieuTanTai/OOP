package BUS;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Scanner;
import DTO.Books;
import util.Validate;

public class BooksBUS {
     private Books[] booksList;
     private int quantity;
     private final Scanner input = new Scanner(System.in);

     // constructors
     public BooksBUS () {
          this.quantity = 0;
          this.booksList = new Books[0];
     }

     public BooksBUS (int quantity, Books[] booksList) {
          this.quantity = quantity;
          this.booksList = booksList;
     }

     // when have existences booksArray before
     public BooksBUS (BooksBUS booksArray) {
          this.quantity = booksArray.quantity;
          this.booksList = booksArray.booksList;
     }

     // getter / setter
     public Books[] getBooksList () {
          return this.booksList;
     }

     public int getIndex () {
          return this.quantity;
     }

     public void setBooksList (Books[] newBooksList) {
          this.booksList = newBooksList;
     }

     public void setIndex (int newQuantity) {
          this.quantity = newQuantity;
     }

     // add find remove search....
     public int findBook (String inputId) {
          for (int i = 0; i < booksList.length; i++)
               if (booksList[i].getProductId().equals(inputId))
                    return i;
          System.out.println("your id is not found!");
          return -1;
     }

     public void addBook (Books newBook) {
          if (newBook != null) {
               booksList = Arrays.copyOf(booksList, booksList.length + 1);
               booksList[quantity] = newBook;
               quantity++;
          }
     }

     public void searchBook (String inputId) {
          int indexBook = findBook(inputId);
          if (indexBook != -1) {
               String toStringHandler = composeUsingFormatter(indexBook);
               System.out.printf("your book id is: %s\n your book detail: \n%s", inputId, toStringHandler);
          }
     }

     // genre of book is an array and this method is only use for edit list genre of specific books
     private String[] addGenreOfBook (String[] genresList, Integer quantityGenre) {
          do {
               System.out.print("enter quantity of genre you wanna add: ");
               String quantity = input.nextLine().trim();
               quantityGenre = Validate.isNumber((quantity));
          }while (quantityGenre.equals(null));
          for (int i = 0; i < quantityGenre; i++) {
               String temp = input.nextLine().trim();
               genresList[i] = genresList[i].concat(temp).concat(" ");
          }
          return genresList;
     }

     // remove methods
     public void removeBook (String inputId) {
          int indexBook = findBook(inputId);
          if (indexBook != -1) {
               for (int i = indexBook; i < booksList.length - 1; i++) 
                    booksList[i] = booksList[i + 1];
               booksList = Arrays.copyOf(booksList, booksList.length - 1);
          }
     }

     // edit methods
     public void editBookHandler () {
          String userChoose;
          int optionChoose, indexBook;
          System.out.printf("%20s", "-");
          System.out.println("option 1: " + "edit book's name: ");
          System.out.println("option 2: " + "edit book's release date: ");
          System.out.println("option 3: " + "edit book's price: ");
          System.out.println("option 4: " + "edit book's quantity: ");
          System.out.println("option 5: " + "edit book's author: ");
          System.out.println("option 6: " + "edit book's type: ");
          System.out.println("option 7: " + "edit book's genre: ");
          System.out.println("option 8: " + "edit book's format: ");
          System.out.println("option 9: " + "edit book's packaging size: ");
          System.out.println("option 10: " + "exit!");
          System.out.printf("%20s", "-");
          // validate user choose
          do {
               System.out.print("enter value of option you choose (integer): ");
               userChoose = input.nextLine().trim();
               optionChoose = Validate.parseChooseHandler(userChoose, 10);
          } while (optionChoose == 0);
          // let input id from user to find book in list
          do {
               System.out.print("enter book id you wanna edit: ");
               String inputId = input.nextLine().trim();
               indexBook = findBook(inputId);
          } while (indexBook == -1);
          editBook(booksList[indexBook], optionChoose);
     }

     // some private methods for method handlers
     private void editBook (Books book, int userChoose) {
          String tempUserInput;
          // case that user chooses
          switch (userChoose) {
               case 1:
                    System.out.print("enter new name: ");
                    String newName = input.nextLine();
                    book.setProductName(newName);
                    break;
               case 2:
                    LocalDate newReleaseDate;
                    do {
                         System.out.print("enter new release date: ");
                         tempUserInput = input.nextLine().trim();
                         newReleaseDate = Validate.formatInputDate(tempUserInput);
                    } while (newReleaseDate.equals(null));
                    book.setReleaseDate(newReleaseDate);
                    break;
               case 3:
                    Object newPrice;
                    do {
                         System.out.print("enter new price: ");
                         newPrice = input.next();
                    } while (!Validate.validatePrice(newPrice));
                    book.setProductPrice((BigDecimal) newPrice);
                    break;
               case 4: 
                    Integer newQuantity;
                    do {
                         System.out.println("enter new quantity: ");
                         tempUserInput = input.nextLine().trim();
                         newQuantity = Validate.isNumber(tempUserInput);
                    } while (newQuantity.equals(-1));
                    book.setQuantity(newQuantity);
                    break;
               case 5:
                    System.out.print("enter new author: ");
                    String newAuthor = input.nextLine().trim();
                    book.setAuthor(newAuthor);
                    break;

               case 6:
                    System.out.print("enter new type: ");
                    tempUserInput = input.nextLine();
                    break;

               case 7:
                    int optionChoose;
                    System.out.println("option 1: replace all genres before.");
                    System.out.println("option 2: replace one of all genres before.");
                    System.out.println("option 3: add new genres.");
                    do {
                         System.out.print("enter value of option you choose (integer): ");
                         tempUserInput = input.nextLine().trim();
                         optionChoose = Validate.parseChooseHandler(tempUserInput, 3);
                    } while (optionChoose == 0);
                    
                    break;                    

          }
     }

     // some other methods
     private String composeUsingFormatter (int isFindId) {
          Books book = booksList[isFindId];
          return String.format(" publisher name: %s\n author: %s\n book type: %s\n format: %s\n packaging size: %s\n", 
          book.getPublisherId(), book.getAuthor(), book.getTypeName(), book.getFormat(), book.getPackagingSize());
     }
}
