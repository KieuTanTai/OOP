package DTO;

public class BookGenres {
     private String genreID;
     private String genreName; 

     public BookGenres() {}
     
     public BookGenres(String genreID, String genreName) {
          this.genreID = genreID;
          this.genreName = genreName;
     }

     public String getGenreID () {
          return this.genreID;
     }
     
     public String getGenreName () {
          return this.genreName;
     }
     
     public void setGenreID (String genreID) {
          this.genreID = genreID;
     }

     public void setGenreName (String genreName) {
          this.genreName = genreName;
     }
}
