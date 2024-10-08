package BUS;
import DTO.BookGenres;
import DTO.BookTypes;
import java.util.Arrays;
import java.util.Scanner;

public class GenresBUS extends BookGenres {
     private BookGenres[] genresList;
     private int quantity;
     private Scanner input = new Scanner(System.in);

     public GenresBUS () {
          this.quantity = 0;
          genresList = new BookGenres[0];
     };

     public GenresBUS (BookGenres[] genresList, int quantity) {
          this.genresList = genresList;
          this.quantity = genresList.length;
     }

     public GenresBUS (GenresBUS typeArray) {
          this.genresList = typeArray.genresList;
          this.quantity = typeArray.quantity;
     }     

          public BookGenres[] getTypesList () {
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

     public void addGenre (BookGenres genres) {
          if (genres instanceof BookGenres) {
               genresList = Arrays.copyOf(genresList, genresList.length + 1);
               genresList[quantity] = genres;
               quantity++;
          }
     }

     public void editGenreName () {
          String isFindId;
          boolean flag = true;
          do {
               System.out.print("enter the id of genre you wanna edit: ");
               isFindId = input.nextLine().trim();
          } while (Validate.validateId(isFindId));

          for (BookGenres genre : genresList) {
               if (genre.getTypeId().equals(isFindId)) {
                    System.out.print("enter new genre name: ");
                    String newTypeName = input.nextLine().trim();
                    genre.setTypeName(newTypeName);
                    flag = false;
                    break;
               }
          }

          if (flag) {
               System.out.println("your genre is not found! please try again!");
               System.out.print("\033\143");
               editGenreName();
          }
     }
}
