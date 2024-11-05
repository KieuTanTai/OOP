package BUS;
import java.util.Arrays;
import DTO.BookGenres;
import DTO.MidForBooks;
import util.Validate;

public class MidForBooksBUS {
     MidForBooks[] midList;
     private int count;

     // constructors
     public MidForBooksBUS () {
          this.count = 0;
          midList = new MidForBooks[0];
     }

     public MidForBooksBUS (int count, MidForBooks[] midList) {
          this.count = count;
          this.midList = midList;
     }

     public MidForBooksBUS (MidForBooksBUS MidFB) {
          this.count = MidFB.count;
          this.midList = MidFB.midList;
     }

     // getter / setter
     public int getCount () {
          return this.count;
     }

     public MidForBooks[] midList () {
          return this.midList;
     }

     public void setCount (int count) {
          this.count = count;
     }

     public void setMidList (MidForBooks[] midList) {
          this.midList = midList;
     }

     // all others methods like: add remove edit find show....
     // show list (DONE)
      public void showList () {
          if (!Validate.checkQuantity(count)) {
               System.out.println("you don't have any mid list in there!");
               return;
          }
          for (int i = 0; i < midList.length; i++)
               System.out.printf("%d: %10s%s\n - %10s%s\n",  i + 1, midList[i].getBookID(), midList[i].getBookName(),
               midList[i].getGenreID(), midList[i].getGenreName());
     }

     // find index of specific product and genre (DONE)
     public int find (String productId, String genreId)  {
          for (int i = 0; i < midList.length; i++)
               if ((midList[i].getBookID().equals(productId)) && midList[i].getGenreID().equals(genreId))
                    return i;
          return -1;
     }

     // get genres list with specific product (CONTINUE)
     public MidForBooks[] getGenres (String inputValue) {
          int count = 0;
          MidForBooks[] genresList = new MidForBooks[0];
         for (MidForBooks mid : midList)
             if (mid.getBookID().equals(inputValue) || mid.getBookName().contains(inputValue)) {
                 genresList = Arrays.copyOf(genresList, genresList.length + 1);
                 genresList[count] = mid;
                 count++;
             }
          if (count == 0)
               return null;
          return genresList;
     }

     // search method (DONE)
     public void search (String productId, String genreId) {
          int index = find(productId, genreId); 
          if (index == -1) {
               System.out.println("404 not found!");
               return;
          }
          System.out.printf("product name: %s\tgenre name: %s\nexist!", midList[index].getBookName(), midList[index].getGenreName());
     }

     // add methods (DONE)
     public void add (Object midObject) {
          if (midObject instanceof MidForBooks) {
               midList = Arrays.copyOf(midList, midList.length + 1);
               midList[count] = (MidForBooks) midObject;
               count++;
          }
          else
               System.out.println("your input is not correct !");
     }

     // edit method (DONE)
     public void edit (String productId, BookGenres genre) {
          int index = find (productId, genre.getGenreID()); 
          if (index == -1) {
               System.out.println("404 not found !");
               return;
          }
          midList[index].setGenre(genre);
     }

     // remove method (DONE)
     public void remove (String productId, String genreId) {
          int index = find(productId, genreId); 
          if (index == -1) {
               System.out.println("404 not found !");
               return;
          }
          for (int i = index; i < midList.length -1; i++)
               midList[i] = midList[i + 1];
          midList = Arrays.copyOf(midList, midList.length - 1);
          count--;
     }
}
