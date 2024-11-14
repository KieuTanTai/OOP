package DTO;
public class MidForBooks {
     private String bookID;
     private BookGenres genre;   

     // constructors
     public MidForBooks () {}

     public MidForBooks (String bookID, BookGenres genre) {
          this.bookID = bookID;
          this.genre = genre;
     }

     // getter / setter
     public String getBookID () {
          return this.bookID;
     }

     public BookGenres getGenre () {
          return this.genre;
     }

     public void setBookID (String id) {
          this.bookID = id;
     }

     public void setGenre (BookGenres genre) {
          this.genre = genre;
     }
}

