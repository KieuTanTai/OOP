package DTO;

public class BookGenres {
     private String genreId;
     private String genreName; 

     public BookGenres() {};
     public BookGenres(String genreId, String genreName) {
          this.genreId = genreId;
          this.genreName = genreName;
     }

     public String getTypeId () {
          return this.genreId;
     }
     
     public String getTypeName () {
          return this.genreName;
     }
     
     public void setTypeId (String genreId) {
          this.genreId = genreId;
     }

     public void setTypeName (String genreName) {
          this.genreName = genreName;
     }
}
