package DTO;
public class MidForBooks {
     private String bookID;
     private BookGenres genres;   

     // constructors
     public MidForBooks () {}

     public MidForBooks (String bookID, BookGenres genres) {
          this.bookID = bookID;
          this.genres = genres;
     }

     // getter / setter
     public String getBookID () {
          return this.bookID;
     }

     public String getGenreID () {
          return this.genres.getGenreID();
     }

     public String getGenreName () {
          return this.genres.getGenreName();
     }

     public void setGenre (BookGenres genres) {
          this.genres = genres;
     }
}

