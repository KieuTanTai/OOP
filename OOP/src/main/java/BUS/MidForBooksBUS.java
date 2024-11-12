package BUS;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import DTO.BookGenres;
import DTO.MidForBooks;

public class MidForBooksBUS {
     private static MidForBooks[] midList;
     private static int count;

     // constructors
     public MidForBooksBUS () {
          MidForBooksBUS.count = 0;
          midList = new MidForBooks[0];
     }

     public MidForBooksBUS (int count, MidForBooks[] midList) {
          MidForBooksBUS.count = count;
          MidForBooksBUS.midList = midList;
     }

     // getter / setter
     public int getCount () {
          return MidForBooksBUS.count;
     }

     public MidForBooks[] midList () {
          return MidForBooksBUS.midList;
     }

     public void setCount (int count) {
          MidForBooksBUS.count = count;
     }

     public void setMidList (MidForBooks[] midList) {
          MidForBooksBUS.midList = midList;
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

     // add methods *STATIC!!! (DONE)
     public static void add (MidForBooks midObject) {
          midList = Arrays.copyOf(midList, midList.length + 1);
          midList[count] = (MidForBooks) midObject;
          count++;
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

     // execute file resources
     /*
      * DataOutputStream ? DataInputStream ?
      * FileOutputStream ? FileInputStream ?  
      * read and some methods read ? write and some methods write ?
      * exception ?
      */
     //write file
     public void writeFile () throws IOException {
          try (DataOutputStream file = new DataOutputStream(new FileOutputStream("../../resources/ListGenres", false))) {
               file.writeInt(count);
               for (int i = 0; i < count; i++) {
                    file.writeUTF(midList[i].getBookID());
                    file.writeUTF(midList[i].getGenreID());
               }
               System.out.println("write done!");
          } catch (FileNotFoundException err) {
               System.out.printf("404 not found!\n%s", err);
          }
     }


     // read file
     public void readFile () throws IOException {
          try (DataInputStream file = new DataInputStream(new FileInputStream("../../resources/ListGenres"))) {
               count = file.readInt();
               MidForBooks[] list = new MidForBooks[count];
               for (int i = 0; i < count; i++) {
                    String bookID =  file.readUTF();
                    String genreID = file.readUTF();
                    BookGenres genre = GenresBUS.getGenre(genreID);
                    if (genre != null)
                         list[i] = new MidForBooks(bookID, genre);
               }
               setCount(count);
               setMidList(list);
          } catch (FileNotFoundException err) {
               System.out.printf("404 not found!\n%s", err);
          }
     }
}
