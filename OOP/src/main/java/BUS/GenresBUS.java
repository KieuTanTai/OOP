package BUS;
import DTO.BookGenres;
import Manager.Menu;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class GenresBUS implements IRuleSets {
     private static BookGenres[] genresList;
     private static int count;
     private final Scanner input = new Scanner(System.in);

     // constructors
     public GenresBUS () {
          GenresBUS.count = 0;
          genresList = new BookGenres[0];
     }

     public GenresBUS (BookGenres[] genresList, int count) {
          GenresBUS.genresList = genresList;
          GenresBUS.count = count;
     }

     // getter/setter
     public static BookGenres[] getGenresList () {
          return Arrays.copyOf(GenresBUS.genresList, GenresBUS.count);
     }

     public static BookGenres getGenre (String id) {
          for (BookGenres genre : genresList)
               if (genre.getGenreID().equals(id))
                    return new BookGenres(genre.getGenreID(), genre.getGenreName());
          return null;
     }

     public static int getCount () {
          return count;
     }

     public void setGenresList (BookGenres[] genresList) {
          GenresBUS.genresList = genresList;
     }

     public void setCount (int count) {
          GenresBUS.count = count;
     }

     // all others methods like: add remove edit find show....
     // methods shows list of genres for user (DONE)
     public static void showList () {
          if (genresList == null)
               return;
          for (int i = 0; i < genresList.length; i++)
               System.out.printf("%d: %10s %s\n", i + 1, genresList[i].getGenreID(), genresList[i].getGenreName());
     }

     // find methods (DONE)
     @Override
     public void find() {
          Menu.findHandler();
     }

     @Override
     public int find (String inputId)  {
          for ( int i = 0; i < genresList.length; i++) {
               if (genresList[i].getGenreID().equals(inputId))
                    return i;
          }
          System.out.println("your genre is not found! ");
          return -1;
     }

     public BookGenres[] relativeFind (String name) {
          int count = 0;
          BookGenres[] genresArray = new BookGenres[0];
          for (BookGenres genre : genresList)
               if (genre.getGenreName().contains(name)) {
                    genresArray = Arrays.copyOf(genresArray, genresArray.length + 1);
                    genresArray[count] = genre;
                    count++;
               }
          if (count == 0) {
               System.out.println("not found any genres!");
               return null;
          }
          return genresArray;
     }

     // search methods (DONE)
     @Override
     public void search() {
          Menu.searchHandler();
     }

     @Override
     public void search (String inputId) {
          int index = find(inputId);
          if (index != -1)
               System.out.printf("your genre id is: %s\nGenre Name: %s\n", inputId, genresList[index].getGenreName());
     }

     public void relativeSearch (String name) {
          BookGenres[] list = relativeFind(name);
          if (list != null) {
               System.out.println("-----------------------------------------------");
               for (BookGenres genre : list)
                  System.out.printf("genre's id  : %s\ngenre name : %s\n", genre.getGenreID(), genre.getGenreName());
               System.out.println("-----------------------------------------------");
          }
     }

     // adds methods (DONE)
     @Override
     public void add() {
          Menu.addHandler();
     }

     @Override
     public void add (Object genre) {
          if (genre instanceof BookGenres) {
               genresList = Arrays.copyOf(genresList, genresList.length + 1);
               genresList[count] = (BookGenres) genre;
               count++;
          }
          else 
               System.out.println("your new genre is not correct !");
     }

     // edit methods (DONE)
     @Override
     public void edit() {
          Menu.editHandler();
     }

     @Override
     public void edit (String inputId) {
          int index = find(inputId);
          if (index == -1) {
               System.out.println("your genre is not found !");
               return;     
          }
          System.out.print("enter new genre name: ");
          String newTypeName = input.nextLine().trim();
          genresList[index].setGenreName(newTypeName);
     }

     // remove methods (DONE)
     @Override
     public void remove() {
          Menu.removeHandler();
     }

     @Override
     public void remove (String inputId) {
          int index = find(inputId);
          if (index == -1) {
               System.out.println("your genre is not found !");
               return;
          }
          for (int i = index; i < genresList.length - 1; i++) 
               genresList[i] = genresList[i + 1];
          genresList = Arrays.copyOf(genresList, genresList.length - 1);
          count--;
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
          try (DataOutputStream file = new DataOutputStream(new FileOutputStream("OOP/src/main/resources/BookGenres", false))) {
               file.writeInt(count);
               for (int i = 0; i < count; i++) {
                    file.writeUTF(genresList[i].getGenreID());
                    file.writeUTF(genresList[i].getGenreName());
               }
               System.out.println("write done!");
          } catch (FileNotFoundException err) {
               System.out.printf("404 not found!\n%s", err);
          }
     }


     // read file
     public void readFile () throws IOException {
          try (DataInputStream file = new DataInputStream(new FileInputStream("OOP/src/main/resources/BookGenres"))) {
               int count = file.readInt();
               BookGenres[] list = new BookGenres[count];
               for (int i = 0; i < count; i++) {
                    String genreID =  file.readUTF();
                    String genreName = file.readUTF();
                    list[i] = new BookGenres(genreID, genreName);
               }
               setCount(count);
               setGenresList(list);
               System.out.println("read done!");
          } catch (FileNotFoundException err) {
               System.out.printf("404 not found!\n%s", err);
          }
     }
}
