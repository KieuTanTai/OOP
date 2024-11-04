// package BUS;

// import java.math.BigDecimal;
// import java.time.LocalDate;

// import DTO.Books;
// import util.Validate;

// public class temp {
//      private void editGenreOfBook (Books book, int flag) {
//           String genresList = "";
//           String getBookGenres = book.getGenresId();
//           Integer quantityGenre = genresList.length;
//           switch (flag) {
//                case 1: 
//                     genresList = addGenreOfBook(genresList, quantityGenre);
//                     book.setGenresId(genresList);
//                     break;
//                case 2:
//                     String findGenre;
//                     System.out.print("enter genre you wanna replace: ");
//                     findGenre = input.nextLine().trim();
//                     for (int i = 0; i < bookGenresLength; i++) {
//                          if (getBookGenres[i].equals(findGenre)) {
//                               System.out.print("replace name of genre: ");
//                               getBookGenres[i] = input.nextLine().trim();
//                               break;
//                          }
//                          if (i == bookGenresLength - 1) {
//                               System.out.println("not found this genre!");
//                               break;
//                          }
//                     }
//                     genresList = getBookGenres;
//                     book.setGenreId(genresList);
//                     break;
//                case 3:
//                     genresList = addGenreOfBook(genresList, quantityGenre);
//                     int genresListLength = genresList.length;
//                     String[] newGenresList = new String[bookGenresLength + genresListLength];
//                     System.arraycopy(getBookGenres, 0, newGenresList, 0, bookGenresLength);
//                     System.arraycopy(genresList, 0, newGenresList, bookGenresLength, genresListLength);
//                     book.setGenreId(getBookGenres);
//                     break;
//           }
//      }
// }



// // older edit in book bus
//           String tempUserInput;
//           // int userChoose = editHandler();
//           Books book = this.booksList[find(bookId)]; 
//           // case that user chooses
//           switch (userChoose) {
//                case 1:
//                     System.out.print("enter new name: ");
//                     String newName = input.nextLine();
//                     book.setProductName(newName);
//                     break;
//                case 2:
//                     LocalDate newReleaseDate;
//                     do {
//                          System.out.print("enter new release date: ");
//                          tempUserInput = input.nextLine().trim();
//                          newReleaseDate = Validate.formatInputDate(tempUserInput);
//                     } while (newReleaseDate.equals(null));
//                     book.setReleaseDate(newReleaseDate);
//                     break;
//                case 3:
//                     Object newPrice;
//                     do {
//                          System.out.print("enter new price: ");
//                          newPrice = input.next();
//                     } while (!Validate.validatePrice(newPrice));
//                     book.setProductPrice((BigDecimal) newPrice);
//                     break;
//                case 4: 
//                     Integer newQuantity;
//                     do {
//                          System.out.println("enter new quantity: ");
//                          tempUserInput = input.nextLine().trim();
//                          newQuantity = Validate.isNumber(tempUserInput);
//                     } while (newQuantity.equals(-1));
//                     book.setQuantity(newQuantity);
//                     break;
//                case 5:
//                     System.out.print("enter new author: ");
//                     String newAuthor = input.nextLine().trim();
//                     book.setAuthor(newAuthor);
//                     break;

//                case 6:
//                     System.out.print("enter new type: ");
//                     tempUserInput = input.nextLine();
//                     break;

//                case 7:
//                     int optionChoose;

//                     System.out.println("option 1: replace all genres before.");
//                     System.out.println("option 2: replace one of all genres before.");
//                     System.out.println("option 3: add new genres.");
//                     do {
//                          System.out.print("enter value of option you choose (integer): ");
//                          tempUserInput = input.nextLine().trim();
//                          optionChoose = Validate.parseChooseHandler(tempUserInput, 3);
//                     } while (optionChoose == 0);
                    
//                     break;                    
     
//           } 