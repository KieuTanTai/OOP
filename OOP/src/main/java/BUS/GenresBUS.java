package BUS;
import DTO.BookGenres;
import java.util.Arrays;
import java.util.Scanner;

public class GenresBUS extends BookGenres {
     private BookGenres[] genresList;
     private int quantity;
     private Scanner input = new Scanner(System.in);

     // constructors
     public GenresBUS () {
          this.quantity = 0;
          genresList = new BookGenres[0];
     };

     public GenresBUS (BookGenres[] genresList, int quantity) {
          this.genresList = genresList;
          this.quantity = quantity;
     }

     public GenresBUS (GenresBUS typeArray) {
          this.genresList = typeArray.genresList;
          this.quantity = typeArray.quantity;
     }     

     // getter/setter
     public BookGenres[] getBGenresList () {
          return this.genresList;
     }

     public int getQuantity () {
          return this.quantity;
     }

     public void setGenresList (BookGenres[] genresList) {
          this.genresList = genresList;
     }

     public void setQuantity (int quantity) {
          this.quantity = quantity;
     }

     // add edit remove find...
     public void showGenresList () {
          for (int i = 0; i <= this.quantity; i++)
               System.out.printf("%10d %d\n", this.genresList[i].getGenreId(), this.genresList[i].getGenreName());
     }

     public int findGenre (String inputId)  {
          for ( int i = 0; i < this.genresList.length; i++) {
               if (genresList[i].getGenreId().equals(inputId))
                    return i;
          }
          System.out.println("your id is not found !");
          return -1;
     }

     public void searchGenre (String inputId) {
          int genreIndex = findGenre(inputId);
          if (genreIndex != -1)
               System.out.printf("your genre id is: %s\nGenre Name: %s\n", inputId, genresList[genreIndex].getGenreName());
     }

     public void addGenre (BookGenres genres) {
          if (genres instanceof BookGenres) {
               genresList = Arrays.copyOf(genresList, genresList.length + 1);
               genresList[quantity] = genres;
               quantity++;
          }
     }

     public void editGenreName (String inputId) {
          int genreIndex = findGenre(inputId);
          if (genreIndex != -1) {
               System.out.print("enter new genre name: ");
               String newTypeName = input.nextLine().trim();
               genresList[genreIndex].setGenreName(newTypeName);
          }
     }

     public void removeGenre (String inputId) {
          int genreIndex = findGenre(inputId);
          if (genreIndex != -1) {
               for (int i = genreIndex; i < genresList.length - 1; i++) 
                    genresList[i] = genresList[i+1];
               genresList = Arrays.copyOf(genresList, genresList.length - 1);
          }
     }
}
