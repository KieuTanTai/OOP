package DTO;
public class MidForBooks {
     private Books book;
     private BookGenres genres;   

     // constructors
     public MidForBooks () {}

     public MidForBooks (Books book, BookGenres genres) {
          this.book = book;
          this.genres = genres;
     }

     // getter / setter
     public String getBookID () {
          return this.book.getProductID();
     }

     public String getGenreID () {
          return this.genres.getGenreID();
     }

     public String getBookName () {
          return this.book.getProductName();
     }

     public String getGenreName () {
          return this.genres.getGenreName();
     }

     public void setBook (Books book) {
          this.book = book;
     }

     public void setGenre (BookGenres genres) {
          this.genres = genres;
     }
}

