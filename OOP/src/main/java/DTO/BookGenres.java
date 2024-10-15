package DTO;

public class BookGenres {
     private String genreId;
     private String genreName; 

     public BookGenres() {};
     public BookGenres(String genreId, String genreName) {
          this.genreId = genreId;
          this.genreName = genreName;
     }

     public String getGenreId () {
          return this.genreId;
     }
     
     public String getGenreName () {
          return this.genreName;
     }
     
     public void setGenreId (String genreId) {
          this.genreId = genreId;
     }

     public void setGenreName (String genreName) {
          this.genreName = genreName;
     }
}
