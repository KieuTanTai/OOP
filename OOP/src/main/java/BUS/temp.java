package BUS;

import DTO.Books;

public class temp {
     private void editGenreOfBook (Books book, int flag) {
          String genresList = "";
          String getBookGenres = book.getGenresId();
          Integer quantityGenre = genresList.length;
          switch (flag) {
               case 1: 
                    genresList = addGenreOfBook(genresList, quantityGenre);
                    book.setGenresId(genresList);
                    break;
               case 2:
                    String findGenre;
                    System.out.print("enter genre you wanna replace: ");
                    findGenre = input.nextLine().trim();
                    for (int i = 0; i < bookGenresLength; i++) {
                         if (getBookGenres[i].equals(findGenre)) {
                              System.out.print("replace name of genre: ");
                              getBookGenres[i] = input.nextLine().trim();
                              break;
                         }
                         if (i == bookGenresLength - 1) {
                              System.out.println("not found this genre!");
                              break;
                         }
                    }
                    genresList = getBookGenres;
                    book.setGenreId(genresList);
                    break;
               case 3:
                    genresList = addGenreOfBook(genresList, quantityGenre);
                    int genresListLength = genresList.length;
                    String[] newGenresList = new String[bookGenresLength + genresListLength];
                    System.arraycopy(getBookGenres, 0, newGenresList, 0, bookGenresLength);
                    System.arraycopy(genresList, 0, newGenresList, bookGenresLength, genresListLength);
                    book.setGenreId(getBookGenres);
                    break;
          }
     }
}
