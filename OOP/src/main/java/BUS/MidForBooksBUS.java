package BUS;
import java.util.Arrays;
import DTO.BookGenres;
import DTO.MidForBooks;
import util.Validate;

public class MidForBooksBUS {
     MidForBooks[] midList;
     private int quantity;

     // constructors
     public MidForBooksBUS () {
          this.quantity = 0;
          midList = new MidForBooks[0];
     }

     public MidForBooksBUS (int quantity, MidForBooks[] midList) {
          this.quantity = quantity;
          this.midList = midList;
     }

     public MidForBooksBUS (MidForBooksBUS MidFB) {
          this.quantity = MidFB.quantity;
          this.midList = MidFB.midList;
     }

     // getter / setter
     public int getQuantity () {
          return this.quantity;
     }

     public MidForBooks[] midList () {
          return this.midList;
     }

     public void setQuantity (int quantity) {
          this.quantity = quantity;
     }

     public void setMidList (MidForBooks[] midList) {
          this.midList = midList;
     }

     // add edit remove find show....
      public void showList () {
          if (!Validate.checkQuantity(quantity)) {
               System.out.println("you don't have any mid list in there!");
               return;
          }
          for (int i = 0; i < this.midList.length; i++)
               System.out.printf("%s   %s\n", this.midList[i].getBookId(), this.midList[i].getGenreId());
     }

     // get genres list with specific product
     public String[] getGenres (String inputValue) {
          int index = 0;
          String[] genresList = new String[index];
          for (int i = 0; i < midList.length; i++)
               if (midList[i].getBookId().equals(inputValue) || midList[i].getBookName().equals(inputValue)) {
                    genresList[index] = midList[i].getGenreName();
                    index++;
               }
          if (index == 0) {
               System.out.println("not found!");
               return null;
          }
          return genresList;
     }

     // find index of specific product and genre
     public int find (String productId, String genreId)  {
          for (int i = 0; i < midList.length; i++)
               if ((midList[i].getBookId().equals(productId)) && midList[i].getGenreId().equals(genreId))
                    return i;
          System.out.println("not found!");
          return -1;
     }

     public void search (String productId, String genreId) {
          int index = find(productId, genreId); 
          if (index != -1)
               System.out.printf("product name: %s\tgenre name: %s\nexist!", midList[index].getBookName(), midList[index].getGenreName());
     }

     public void add (Object midObject) {
          if (midObject instanceof MidForBooks) {
               midList = Arrays.copyOf(midList, midList.length + 1);
               midList[quantity] = (MidForBooks) midObject;
               quantity++;
          }
     }

     public void edit (String productId, BookGenres genre) {
          int index = find (productId, genre.getGenreId()); 
          if (index != -1)
               midList[index].setGenre(genre);
     }

     public void remove (String productId, String genreId) {
          int index = find(productId, genreId); 
          if (index != -1) {
               for (int i = index; i < midList.length -1; i++)
                    midList[i] = midList[i + 1];
               midList = Arrays.copyOf(midList, midList.length - 1);
          }
     }
}
