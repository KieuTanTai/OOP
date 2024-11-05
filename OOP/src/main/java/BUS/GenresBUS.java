package BUS;
import DTO.BookGenres;
import java.util.Arrays;
import java.util.Scanner;

public class GenresBUS implements RuleSets{
     private BookGenres[] genresList;
     private int many;
     private Scanner input = new Scanner(System.in);

     // constructors
     public GenresBUS () {
          this.many = 0;
          genresList = new BookGenres[0];
     };

     public GenresBUS (BookGenres[] genresList, int many) {
          this.genresList = genresList;
          this.many = many;
     }

     public GenresBUS (GenresBUS typeArray) {
          this.genresList = typeArray.genresList;
          this.many = typeArray.many;
     }     

     // getter/setter
     public BookGenres[] getGenresList () {
          return this.genresList;
     }

     public int getQuantity () {
          return this.many;
     }

     public void setGenresList (BookGenres[] genresList) {
          this.genresList = genresList;
     }

     public void setQuantity (int many) {
          this.many = many;
     }

     // add edit remove find show...
     public void showList () {
          for (int i = 0; i <= this.many; i++)
               System.out.printf("%10d %d\n", this.genresList[i].getGenreId(), this.genresList[i].getGenreName());
     }

     // find methods
     public int find (String inputId)  {
          for ( int i = 0; i < this.genresList.length; i++) {
               if (genresList[i].getGenreId().equals(inputId))
                    return i;
          }
          System.out.println("your id is not found !");
          return -1;
     }

     public BookGenres[] relativeFind (String inputValue) {
          int many = 0;
          BookGenres[] genresArray = new BookGenres[0];
          for (int i = 0; i < genresList.length; i++)
               if (genresList[i].getGenreName().contains(inputValue)) {
                    genresArray = Arrays.copyOf(genresArray, genresArray.length + 1);
                    genresArray[many] = genresArray[i];
                    many++;
               }
          if (many == 0)
               return null;
          return genresArray;
     }

     // search methods
     public void search (String inputId) {
          int genreIndex = find(inputId);
          if (genreIndex != -1)
               System.out.printf("your genre id is: %s\nGenre Name: %s\n", inputId, genresList[genreIndex].getGenreName());
     }

     public void relativeSearch (String inputValue) {
          BookGenres[] indexList = relativeFind(inputValue);
          if (indexList.equals(null)) {
               System.out.println("not found any types!");
               return;
          }
          for (int i = 0; i < indexList.length; i++)
               System.out.printf("total genres found : %d\ngenre name : %s\n", indexList.length, indexList[i].getGenreName());
     }

     public void add (Object genre) {
          if (genre instanceof BookGenres) {
               genresList = Arrays.copyOf(genresList, genresList.length + 1);
               genresList[many] = (BookGenres) genre;
               many++;
          }
          else
               System.out.println("your new genre is not instance of BookGenres!");
     }

     public void edit (String inputId) {
          int genreIndex = find(inputId);
          if (genreIndex != -1) {
               System.out.print("enter new genre name: ");
               String newTypeName = input.nextLine().trim();
               genresList[genreIndex].setGenreName(newTypeName);
          }
     }

     public void remove (String inputId) {
          int genreIndex = find(inputId);
          if (genreIndex != -1) {
               for (int i = genreIndex; i < genresList.length - 1; i++) 
                    genresList[i] = genresList[i+1];
               genresList = Arrays.copyOf(genresList, genresList.length - 1);
          }
     }
}
