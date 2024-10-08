package BUS;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Scanner;
import DTO.Books;
import DTO.BookGenres;
import DTO.BookTypes;

public class BooksBUS {
     private Books[] booksList;
     private int index;
     Scanner input = new Scanner(System.in);

     // constructors
     public BooksBUS () {
          this.index = 0;
          this.booksList = new Books[0];
     }

     public BooksBUS (int index, Books[] booksList) {
          this.index = index;
          this.booksList = booksList;
     }

     // when have existents booksArray before
     public BooksBUS (BooksBUS booksArray) {
          this.index = booksArray.index;
          this.booksList = booksArray.booksList;
     }

     // getter setter
     public Books[] getBooksList () {
          return this.booksList;
     }

     public int getIndex () {
          return this.index;
     }

     public void setBooksList (Books[] newBooksList) {
          this.booksList = newBooksList;
     }

     public void setIndex (int newIndex) {
          this.index = newIndex;
     }

     // add methods
     public void addBook (Books newBook) {
          if (newBook instanceof Books) {
               booksList = Arrays.copyOf(booksList, booksList.length + 1);
               booksList[index] = newBook;
               index++;
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
               genresList[i] = input.nextLine().trim();
          }
          return genresList;
     }

     // edit methods
     public void editBookHandler () {
          String userChoose;
          boolean anchor = false;
          int optionChoose;
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
          do {
               System.out.print("enter value of option you choose (integer): ");
               userChoose = input.nextLine().trim();
               optionChoose = Validate.parseChooseHandler(userChoose, 10);
          } while (optionChoose == 0);
          // let input id from user to find book in list
          do {
               System.out.print("enter book id you wanna edit: ");
               String userInputId = input.nextLine().trim();
               // loop books in booksList
               for (Books book : booksList) {
                    if (book.getProductId().equals(userInputId)) {
                         editBook(book, optionChoose);
                         anchor = false;
                         break;
                    }

                    if (book.equals(booksList[booksList.length - 1]))
                         anchor = true;
               }
          } while (anchor);
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
                    } while (newQuantity.equals(null));
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
                    editGenreOfBook(book, optionChoose);
                    break;                    

          }
     }

     
     private void editGenreOfBook (Books book, int flag) {
          String[] genresList = {};
          String[] getBookGenres = book.getGenre();
          int bookGenresLength = getBookGenres.length;
          Integer quantityGenre = genresList.length;
          switch (flag) {
               case 1: 
                    genresList = addGenreOfBook(genresList, quantityGenre);
                    book.setGenre(genresList);
                    break;
               case 2:
                    String findGenre;
                    System.out.print("enter genre you wanna replace: ");
                    findGenre = input.nextLine().trim();
                    for (int i = 0; i < bookGenresLength; i++) {
                         if (getBookGenres[i].equals(findGenre)) {
                              System.out.print("replace name of genre: ");
                              getBookGenres[i] = input.nextLine().trim();
                              break;
                         }
                         if (i == bookGenresLength - 1) {
                              System.out.println("not found this genre!");
                              break;
                         }
                    }
                    genresList = getBookGenres;
                    book.setGenre(genresList);
                    break;
               case 3:
                    genresList = addGenreOfBook(genresList, quantityGenre);
                    int genresListLength = genresList.length;
                    String[] newGenresList = new String[bookGenresLength + genresListLength];
                    System.arraycopy(getBookGenres, 0, newGenresList, 0, bookGenresLength);
                    System.arraycopy(genresList, 0, newGenresList, bookGenresLength, genresListLength);
                    book.setGenre(getBookGenres);
                    break;
          }
     }
}
