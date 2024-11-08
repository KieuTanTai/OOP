package BUS;
import DTO.BookGenres;
import java.util.Arrays;
import java.util.Scanner;

public class GenresBUS implements RuleSets{
     private BookGenres[] genresList;
     private int count;
     private final Scanner input = new Scanner(System.in);

     // constructors
     public GenresBUS () {
          this.count = 0;
          genresList = new BookGenres[0];
     }

     public GenresBUS (BookGenres[] genresList, int count) {
          this.genresList = genresList;
          this.count = count;
     }

     public GenresBUS (GenresBUS typeArray) {
          this.genresList = typeArray.genresList;
          this.count = typeArray.count;
     }     

     // getter/setter
     public BookGenres[] getGenresList () {
          return this.genresList;
     }

     public int getCount () {
          return this.count;
     }

     public void setGenresList (BookGenres[] genresList) {
          this.genresList = genresList;
     }

     public void setCount (int count) {
          this.count = count;
     }

     // all others methods like: add remove edit find show....
     // methods shows list of genres for user (DONE)
     public void showList () {
          for (int i = 0; i <= this.count; i++)
               System.out.printf("%d: %10s %s\n", i + 1, genresList[i].getGenreID(), genresList[i].getGenreName());
     }

     // find methods (DONE)
     //strict find
     @Override
     public int find (String inputId)  {
          for ( int i = 0; i < this.genresList.length; i++) {
               if (genresList[i].getGenreID().equals(inputId))
                    return i;
          }
          return -1;
     }

     // relative find
     public BookGenres[] relativeFind (String inputValue) {
          int count = 0;
          BookGenres[] genresArray = new BookGenres[0];
          for (BookGenres genre : genresList)
               if (genre.getGenreName().contains(inputValue)) {
                    genresArray = Arrays.copyOf(genresArray, genresArray.length + 1);
                    genresArray[count] = genre;
                    count++;
               }
          if (count == 0)
               return null;
          return genresArray;
     }

     // search methods (DONE)
     // strict search
     @Override
     public void search (String inputId) {
          int index = find(inputId);
          if (index == -1) {
               System.out.println("your genre is not found !");
               return;
          }
          System.out.printf("your genre id is: %s\nGenre Name: %s\n", inputId, genresList[index].getGenreName());
     }

     // relative search
     public void relativeSearch (String inputValue) {
          BookGenres[] list = relativeFind(inputValue);
          if (list == null) {
               System.out.println("not found any types!");
               return;
          }
         for (BookGenres genre : list)
             System.out.printf("genre's id  : %s\ngenre name : %s\n", genre.getGenreID(), genre.getGenreName());
     }

     // add method (DONE)
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

     // edit method (DONE)
     @Override
     public void edit (String inputId) {
          int genreIndex = find(inputId);
          if (genreIndex == -1) {
               System.out.println("your genre is not found !");
               return;     
          }
          System.out.print("enter new genre name: ");
          String newTypeName = input.nextLine().trim();
          genresList[genreIndex].setGenreName(newTypeName);
     }

     // remove method (DONE)
     @Override
     public void remove (String inputId) {
          int genreIndex = find(inputId);
          if (genreIndex == -1) {
               System.out.println("your genre is not found !");
               return;
          }
          for (int i = genreIndex; i < genresList.length - 1; i++) 
               genresList[i] = genresList[i + 1];
          genresList = Arrays.copyOf(genresList, genresList.length - 1);
          count--;
     }
}
