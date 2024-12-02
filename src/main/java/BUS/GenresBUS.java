package BUS;

import DTO.BookGenres;
import util.Validate;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class GenresBUS implements IRuleSets {
     private static BookGenres[] genresList;
     private static int count;
     private final Scanner input = new Scanner(System.in);

     // constructors
     public GenresBUS() {
          GenresBUS.count = 0;
          genresList = new BookGenres[0];
     }

     public GenresBUS(BookGenres[] genresList, int count) {
          GenresBUS.genresList = genresList;
          GenresBUS.count = count;
     }

     // getters / setters
     public static BookGenres[] getGenresList() {
          return Arrays.copyOf(GenresBUS.genresList, GenresBUS.count);
     }

     public static BookGenres getGenre(String id) {
          for (BookGenres genre : genresList)
               if (genre.getGenreID().equals(id))
                    return new BookGenres(genre.getGenreID(), genre.getGenreName());
          return null;
     }

     public static BookGenres[] getGenres(int start, int end) {
          int size = 0;
          BookGenres[] list = new BookGenres[0];
          if (start >= end)
               return null;
          for (int i = start; i < end; i++) {
               list = Arrays.copyOf(list, list.length + 1);
               list[size] = genresList[i];
               size++;
          }
          return Arrays.copyOf(list, list.length);
     }

     public static int getCount() {
          return count;
     }

     public void setGenresList(BookGenres[] genresList) {
          GenresBUS.genresList = genresList;
     }

     public void setGenre(String genreID, BookGenres newGenre) {
          for (int i = 0; i < count; i++)
               if (genresList[i].getGenreID().equals(genreID)) {
                    genresList[i] = newGenre;
                    return;
               }
     }

     public void setCount(int count) {
          GenresBUS.count = count;
     }

     // all others methods like: add remove edit find show....
     // methods shows list of genres for user (DONE)
     public static void showList() {
          if (genresList == null)
               return;
          showAsTable(genresList);
     }

     // find methods (DONE)
     @Override
     public int find(String nameOrID) {
          for (int i = 0; i < genresList.length; i++) {
               if (genresList[i].getGenreID().equals(nameOrID)
                         || genresList[i].getGenreName().toLowerCase().equals(nameOrID.toLowerCase().trim()))
                    return i;
          }
          System.out.println("your genre is not found!");
          return -1;
     }

     public BookGenres[] relativeFind(String name) {
          int count = 0;
          BookGenres[] genresArray = new BookGenres[0];
          for (BookGenres genre : genresList)
               if (genre.getGenreName().toLowerCase().contains(name.toLowerCase())) {
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
          int choice;
          do {
               System.out.println("*".repeat(60));
               System.out.println("I. Strict search");
               System.out.println("II. Relative search");
               System.out.println("0. Exit");
               System.out.println("*".repeat(60));
               System.out.print("Enter your choice : ");
               String inputChoice = input.nextLine().trim();
               // validate if user choose 0
               if (inputChoice.equals("0")) {
                    System.out.println("Exit program.");
                    break;
               }
               choice = Validate.parseChooseHandler(inputChoice, 2);
               if (choice == -1)
                    break;

               System.out.println("Enter name or id of genre : ");
               String userInput = input.nextLine().trim();
               // if case
               if (choice == 1)
                    search(userInput);
               else if (choice == 2)
                    relativeSearch(userInput);

          } while (choice != 0);
     }

     @Override
     public void search(String nameOrID) {
          int index = find(nameOrID);
          if (index != -1)
               showAsTable(genresList[index]);
     }

     public void relativeSearch(String name) {
          BookGenres[] list = relativeFind(name);
          if (list != null)
               showAsTable(list);
     }

     // adds methods (DONE)
     @Override
     public void add() {
          int choice;
          do {
               System.out.println("*".repeat(60));
               System.out.println("I. Add genre");
               System.out.println("II. Add list of genres");
               System.out.println("0. Exit");
               System.out.println("*".repeat(60));
               System.out.print("Enter your choice : ");
               String inputChoice = input.nextLine().trim();
               // validate if user choose 0
               if (inputChoice.equals("0")) {
                    System.out.println("Exit program.");
                    break;
               }
               choice = Validate.parseChooseHandler(inputChoice, 2);

               try {
                    switch (choice) {
                         case 1:
                              BookGenres newGenre = new BookGenres();
                              newGenre.setInfo();
                              // confirm
                              System.out.printf("| %s %s %s |\n", "I.Cancel", "-".repeat(20), "II.Add");
                              do {
                                   System.out.print("choose option (1 or 2) : ");
                                   String option = input.nextLine().trim();
                                   choice = Validate.parseChooseHandler(option, 2);
                              } while (choice == -1);
                              if (choice == 1)
                                   break;
                              add(newGenre);
                              writeFile();
                              break;
                         case 2:
                              int count = 0;
                              BookGenres[] list = new BookGenres[0];
                              do {
                                   System.out.print("Enter total genres you wanna add : ");
                                   String option = input.nextLine().trim();
                                   choice = Validate.isNumber(option);
                              } while (choice == -1);
                              // for loop with input time
                              for (int i = 0; i < choice; i++) {
                                   BookGenres genre = new BookGenres();
                                   genre.setInfo();
                                   list = Arrays.copyOf(list, list.length + 1);
                                   list[count] = genre;
                                   count++;
                              }

                              // confirm
                              System.out.printf("| %s %s %s |\n", "I.Cancel", "-".repeat(20), "II.Add");
                              do {
                                   System.out.print("choose option (1 or 2) : ");
                                   String option = input.nextLine().trim();
                                   choice = Validate.parseChooseHandler(option, 2);
                              } while (choice == -1);
                              if (choice == 1)
                                   break;
                              add(list);
                              writeFile();
                              break;
                    }
               } catch (Exception e) {
                    System.out.printf("error writing file!\nt%s\n", e.getMessage());
               }

          } while (choice != 0);
     }

     @Override
     public void add(Object genre) {
          if (genre instanceof BookGenres newGenre) {
              newGenre.setGenreID(newGenre.getGenreID());
               genresList = Arrays.copyOf(genresList, genresList.length + 1);
               genresList[count] = newGenre;
               count++;
          } else
               System.out.println("your new genre is not correct!");
     }

     public void add(BookGenres[] newGenres) {
          int tempIndex = 0, newListLength = newGenres.length;
          int initCount = getCount();
          int total = initCount + newListLength;
          genresList = Arrays.copyOf(genresList, genresList.length + newListLength);

          for (int i = initCount; i < total; i++, tempIndex++) {
               newGenres[tempIndex].setGenreID(newGenres[tempIndex].getGenreID());
               genresList[i] = newGenres[tempIndex];
          }
          GenresBUS.count = total;
     }

     // edit methods (DONE)
     @Override
     public void edit() {
          int choice;
          do {
               System.out.println("*".repeat(60));
               System.out.println("I. Edit");
               System.out.println("0. Exit");
               System.out.println("*".repeat(60));
               System.out.print("Enter your choice : ");
               String inputChoice = input.nextLine().trim();
               // validate if user choose 0
               if (inputChoice.equals("0")) {
                    System.out.println("Exit program.");
                    break;
               }
               choice = Validate.parseChooseHandler(inputChoice, 1);
               if (choice == 1) {
                    try {
                         System.out.println("Enter name or id of genre : ");
                         String userInput = input.nextLine().trim();
                         edit(userInput);
                         writeFile();
                    } catch (Exception e) {
                         System.out.printf("error writing file!\nt%s\n", e.getMessage());
                    }
               }
          } while (choice != 0);
     }

     @Override
     public void edit(String id) {
          int index = find(id);
          if (index != -1) {
               int userChoice;
               // show list for user choice
               showAsTable(genresList[index]);
               System.out.printf("| %s %s %s |\n", "I.Cancel", "-".repeat(20), "II.Edit");
               do {
                    System.out.print("choose option (1 or 2) : ");
                    String option = input.nextLine().trim();
                    userChoice = Validate.parseChooseHandler(option, 2);
               } while (userChoice == -1);
               if (userChoice == 1)
                    return;

               String newName;
               do {
                    System.out.print("Enter new name: ");
                    newName = input.nextLine().trim();
                    if (!Validate.checkName(newName)) {
                         System.out.println("name is wrong genre!");
                         newName = "";
                    }
               } while (newName.isEmpty());
               genresList[index].setGenreName(newName);
          }
     }

     // remove methods (DONE)
     @Override
     public void remove() {
          int choice;
          do {
               System.out.println("*".repeat(60));
               System.out.println("I. Remove");
               System.out.println("0. Exit");
               System.out.println("*".repeat(60));
               System.out.print("Enter your choice : ");
               String inputChoice = input.nextLine().trim();
               // validate if user choose 0
               if (inputChoice.equals("0")) {
                    System.out.println("Exit program.");
                    break;
               }
               choice = Validate.parseChooseHandler(inputChoice, 1);
               if (choice == 1) {
                    try {
                         System.out.println("Enter name or id of genre : ");
                         String userInput = input.nextLine().trim();
                         remove(userInput);
                         writeFile();
                    } catch (Exception e) {
                         System.out.printf("error writing file!\nt%s\n", e.getMessage());
                    }
               }
          } while (choice != 0);
     }

     @Override
     public void remove(String id) {
          int index = find(id);
          if (index != -1) {
               int userChoice;
               // show list for user choice
               showAsTable(genresList[index]);
               System.out.printf("| %s %s %s |\n", "I.Cancel", "-".repeat(20), "II.Remove");
               do {
                    System.out.print("choose option (1 or 2) : ");
                    String option = input.nextLine().trim();
                    userChoice = Validate.parseChooseHandler(option, 2);
               } while (userChoice == -1);
               if (userChoice == 1)
                    return;

               for (int i = index; i < genresList.length - 1; i++)
                    genresList[i] = genresList[i + 1];
               genresList = Arrays.copyOf(genresList, genresList.length - 1);
               count--;
          }
     }

     // show as table methods
     public static void showAsTable(BookGenres[] list) {
          if (list == null)
               return;
          System.out.println("=".repeat(110));
          System.out.printf("| \t%-20s %-20s %-58s |\n", "No.", "Genres ID", "Genres Name");
          System.out.println("=".repeat(110));
          for (int i = 0; i < list.length; i++) {
               if (i > 0)
                    System.out.println("|" + "-".repeat(108) + "|");
               System.out.printf("| \t%-21s %-19s %-58s |\n", i + 1, list[i].getGenreID(), list[i].getGenreName());
          }
          System.out.println("=".repeat(110));
     }

     public static void showAsTable(BookGenres item) {
          if (item == null)
               return;
          System.out.println("=".repeat(110));
          System.out.printf("| \t%-20s %-20s %-58s |\n", "No.", "Genres ID", "Genres Name");
          System.out.println("=".repeat(110));
          System.out.println("|" + "-".repeat(108) + "|");
          System.out.printf("| \t%-21s %-19s %-58s |\n", 1, item.getGenreID(), item.getGenreName());
          System.out.println("=".repeat(110));
     }

     // execute file resources
     /*
      * DataOutputStream ? DataInputStream ?
      * FileOutputStream ? FileInputStream ?
      * read and some methods read ? write and some methods write ?
      * exception ?
      */

     // write file
     public void writeFile() throws IOException {
          try (DataOutputStream file = new DataOutputStream(
                    new BufferedOutputStream(new FileOutputStream("src/main/resources/BookGenres", false)))) {
               file.writeInt(count);
               for (BookGenres genre : genresList) {
                    file.writeUTF(genre.getGenreID());
                    file.writeUTF(genre.getGenreName());
               }
          } catch (Exception err) {
               System.out.printf("error writing file!\n%s\n", err.getMessage());
          }
     }

     // read file
     public void readFile() throws IOException {
          File testFile = new File("src/main/resources/BookGenres");
          if (testFile.length() == 0 || !testFile.exists())
               return;

          try (DataInputStream file = new DataInputStream(
                    new BufferedInputStream(new FileInputStream("src/main/resources/BookGenres")))) {
               int count = file.readInt();
               BookGenres[] list = new BookGenres[count];
               for (int i = 0; i < count; i++) {
                    String genreID = file.readUTF();
                    String genreName = file.readUTF();
                    list[i] = new BookGenres(genreID, genreName);
               }
               setCount(count);
               setGenresList(list);
          } catch (Exception err) {
               System.out.printf("error reading file!\n%s\n", err.getMessage());
          }
     }
}
