package DTO;

public class MidForBooks {
     private String productId;
     private String genreId;   

     // constructors
     public MidForBooks () {}

     public MidForBooks (String productId, String genreId) {
          this.productId = productId;
          this.genreId = genreId;
     }

     // getter / setter
     public String getProductId () {
          return this.productId;
     }

     public String getGenreId () {
          return this.genreId;
     }

     public void setProductId (String productId) {
          this.productId = productId;
     }

     public void setGenreId (String genreId) {
          this.genreId = genreId;
     }
}

