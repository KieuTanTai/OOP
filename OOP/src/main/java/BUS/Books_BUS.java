package BUS;
import java.util.Arrays;
import java.util.Scanner;

import DTO.Books;
public class Books_BUS{
     private Books[] booksList;
     private int index;
     Scanner input = new Scanner(System.in);

     public Books_BUS () {
          this.index = 0;
          this.booksList = new Books[0];
     }

     public Books_BUS (int index, Books[] booksList) {
          this.index = index;
          this.booksList = booksList;
     }

     public Books_BUS (Books_BUS booksArray) {
          this.index = booksArray.index;
          this.booksList = booksArray.booksList;
     } 

     public Books[] getBooksList () {
          return this.booksList;
     }

     public int getIndex () {
          return this.index;
     }

     public void setBooksList (Books[] newBooksList) {
          this.booksList = newBooksList;
     }

     public void setIndex (int newindex) {
          this.index = newindex;
     }

     public void addBook (Books newBook) {
          if(newBook instanceof Books) {
               booksList = Arrays.copyOf(booksList, booksList.length +1);
               booksList[index] = newBook;
               index ++;
          }
     }

     public void editBook (String id) {
          
     }
}
