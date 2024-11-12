package BUS;
import java.util.Arrays;
import DTO.BookGenres;
import DTO.MidForBooks;

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

     // all others methods like: add remove edit find....
     // find methods (DONE)
     // get genres list with specific product
     public MidForBooks[] find (String id) {
          int count = 0;
          MidForBooks[] list = new MidForBooks[0];
          for (MidForBooks mid : midList)
             if (mid.getBookID().equals(id)) {
                 list = Arrays.copyOf(list, list.length + 1);
                 list[count] = mid;
                 count++;
             }
          if (count == 0) {
               System.out.println("not found any mid!");
               return null;
          }
          return list;
     }
     // strict find 
     public int find (String productId, String genreId)  {
          for (int i = 0; i < midList.length; i++)
               if ((midList[i].getBookID().equals(productId)) && midList[i].getGenreID().equals(genreId))
                    return i;
          System.out.println("404 not found !");
          return -1;
     }

     // search methods (DONE)
     // strict search
     public void search (String productId, String genreId) {
          int index = find(productId, genreId); 
          if (index != -1) 
               System.out.printf("product's id: %10s genre's name: %10s exist!", midList[index].getBookID(), midList[index].getGenreName());
     }

     // relative search
     public void relativeSearch (String id) {
          MidForBooks[] list = find(id);
          if (list != null)
               for (MidForBooks mid : list)
                    System.out.printf("product's id: %10s genre's id  : %10s genre name : %s\n", mid.getGenreID(), mid.getGenreName());
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
          if (index != -1)
               midList[index].setGenre(genre);
     }

     // remove method (DONE)
     public void remove (String productId, String genreId) {
          int index = find(productId, genreId); 
          if (index != -1) {
               for (int i = index; i < midList.length -1; i++)
                    midList[i] = midList[i + 1];
               midList = Arrays.copyOf(midList, midList.length - 1);
               count--;
          }
     }
}
