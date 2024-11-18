package BUS;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

import DTO.BookGenres;
import DTO.MidForBooks;
import util.Validate;

public class MidForBooksBUS {
     private static MidForBooks[] midList;
     private static int count;
     private Scanner input = new Scanner(System.in);

     // constructors
     public MidForBooksBUS() {
          count = 0;
          midList = new MidForBooks[0];
     }

     public MidForBooksBUS(MidForBooks[] midList, int count) {
          MidForBooksBUS.count = count;
          MidForBooksBUS.midList = midList;
     }

     // getters / setters
     public static int getCount() {
          return count;
     }

     public static MidForBooks[] getMidList() {
          return Arrays.copyOf(midList, midList.length);
     }

     public static MidForBooks getMidForBook(String bookID, BookGenres genre) {
          for (MidForBooks mid : midList)
               if (mid.getBookID().equals(bookID) && mid.getGenre().getGenreID().equals(genre.getGenreID()) &&
                         mid.getGenre().getGenreName().equals(genre.getGenreName()))
                    return new MidForBooks(mid.getBookID(), mid.getGenre());
          return null;
     }

     public void setCount(int count) {
          MidForBooksBUS.count = count;
     }

     public void setMid(MidForBooks now, MidForBooks newMid) {
          for (MidForBooks mid : midList)
               if (mid.getBookID().equals(now.getBookID())
                         && mid.getGenre().getGenreID().equals(now.getGenre().getGenreID()) &&
                         mid.getGenre().getGenreName().equals(now.getGenre().getGenreName()))
                    mid = newMid;
     }

     public void setMidList(MidForBooks[] midList) {
          MidForBooksBUS.midList = midList;
     }

     // all others methods like: add remove edit find....
     // *show lists (TEST DONE)
     public static void showList() {
          if (midList == null)
               return;
          showAsTable(midList);
     }

     // find methods
     // get genres list with specific product
     // *strict find (TEST DONE)
     public int find(String bookID, String genreID) {
          int size = midList.length;
          for (int i = 0; i < size; i++)
               if ((midList[i].getBookID().equals(bookID)) && midList[i].getGenre().getGenreID().equals(genreID))
                    return i;
          return -1;
     }

     // *relative find (TEST DONE)
     public MidForBooks[] relativeFind(String id) {
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

     // search methods
     // *strict search (TEST DONE)
     public void search(String bookID, String genreID) {
          int index = find(bookID, genreID);
          if (index == -1) {
               System.out.println("404 not found!");
               return;
          }
          showAsTable(midList[index]);
     }

     // *relative search (TEST DONE)
     public void relativeSearch(String id) {
          MidForBooks[] list = relativeFind(id);
          if (list != null)
               showAsTable(list);
     }

     // *add methods (TEST DONE)
     public void add(MidForBooks midObject) {
          if (find(midObject.getBookID(), midObject.getGenre().getGenreID()) == -1) {
               midList = Arrays.copyOf(midList, midList.length + 1);
               midList[count] = midObject;
               count++;
          }

     }

     public void add(MidForBooks[] midObject) {
          int tempIndex = 0, newListLength = midObject.length;
          int initCount = getCount();
          int total = initCount + newListLength;
          midList = Arrays.copyOf(midList, midList.length + newListLength);

          for (int i = initCount; i < total; i++, tempIndex++)
               midList[i] = midObject[tempIndex];
          MidForBooksBUS.count = total;
     }

     // *edit method (TEST DONE)
     public void edit(String bookID) {
          MidForBooks[] list = relativeFind(bookID);
          if (list != null) {
               int count = list.length;
               for (int i = 0; i < count; i++)
                    System.out.printf("%-3d: %-6s %-6s %s\n", i + 1, list[i].getBookID(),
                              list[i].getGenre().getGenreID(), list[i].getGenre().getGenreName());
               System.out.println("-".repeat(60));
               int userChoose, genreChoose;
               do {
                    System.out.print("choose book you wanna edit (like 1, 2,etc...): ");
                    String option = input.nextLine().trim();
                    userChoose = Validate.parseChooseHandler(option, list.length);
               } while (userChoose == -1);
               // set new genre for specified book
               GenresBUS.showList();
               System.out.println("-".repeat(60));
               do {
                    System.out.print("choose genre (like 1, 2,etc...): ");
                    String option = input.nextLine().trim();
                    genreChoose = Validate.parseChooseHandler(option, GenresBUS.getCount());
               } while (genreChoose == -1);
               MidForBooks newMid = new MidForBooks(bookID, GenresBUS.getGenresList()[genreChoose - 1]);
               setMid(list[userChoose - 1], newMid);
          }
     }

     // *remove method (TEST DONE)
     public void remove(String bookID) {
          int size = 0;
          MidForBooks[] reduceArray = new MidForBooks[0];
          for (int i = 0; i < midList.length; i++) {
               if (midList[i].getBookID().equals(bookID))
                    continue;
               reduceArray = Arrays.copyOf(reduceArray, reduceArray.length + 1);
               reduceArray[size] = midList[i];
               size++;
          }

          if (size == midList.length) {
               System.out.println("not found any mid!");
               return;
          }

          setCount(size);
          setMidList(reduceArray);
     }

     public void remove(String bookID, String genreID) {
          int index = find(bookID, genreID);
          if (index == -1) {
               System.out.println("404 not found!");
               return;
          }
          int size = midList.length;
          for (int i = index; i < size - 1; i++)
               midList[i] = midList[i + 1];
          midList = Arrays.copyOf(midList, midList.length - 1);
          count--;
     }

     // show as table methods
     public static void showAsTable(MidForBooks[] list) {
          if (list == null)
               return;
          System.out.println("=".repeat(110));
          System.out.printf("| \t%-20s %-21s %-19s %-37s |\n", "No.", "Book ID", "Genre ID", "Genre Name");
          System.out.println("=".repeat(110));
          for (int i = 0; i < list.length; i++) {
               if (i > 0)
                    System.out.println("|" + "-".repeat(108) + "|");
               System.out.printf("| \t%-20s %-21s %-19s %-37s |\n", i + 1, list[i].getBookID(),
                         list[i].getGenre().getGenreID(), list[i].getGenre().getGenreName());
          }
          System.out.println("=".repeat(110));
     }

     public static void showAsTable(MidForBooks item) {
          if (item == null)
               return;
          System.out.println("=".repeat(110));
          System.out.printf("| \t%-20s %-21s %-19s %-37s |\n", "No.", "Book ID", "Genre ID", "Genre Name");
          System.out.println("=".repeat(110));
          System.out.printf("| \t%-20s %-21s %-19s %-37s |\n", 1, item.getBookID(), item.getGenre().getGenreID(),
                    item.getGenre().getGenreName());
          System.out.println("=".repeat(110));
     }

     // execute file resources
     // *write file (TEST DONE)
     public void writeFile() throws IOException {
          try (DataOutputStream file = new DataOutputStream(
                    new BufferedOutputStream(new FileOutputStream("src/main/resources/MidForBooks", false)))) {
               file.writeInt(count);
               for (MidForBooks mid : midList) {
                    file.writeUTF(mid.getBookID());
                    file.writeUTF(mid.getGenre().getGenreID());
               }
          } catch (Exception err) {
               System.out.printf("error writing file!\n%s\n", err.getMessage());
          }
     }

     // *read file (TEST DONE)
     public void readFile() throws IOException {
          try (DataInputStream file = new DataInputStream(
                    new BufferedInputStream(new FileInputStream("src/main/resources/MidForBooks")))) {
               count = file.readInt();
               MidForBooks[] list = new MidForBooks[count];
               for (int i = 0; i < count; i++) {
                    String bookID = file.readUTF();
                    String genreID = file.readUTF();
                    BookGenres genre = GenresBUS.getGenre(genreID);
                    if (genre != null)
                         list[i] = new MidForBooks(bookID, genre);
               }
               setCount(count);
               setMidList(list);
          } catch (Exception err) {
               System.out.printf("error reading file!\n%s\n", err.getMessage());
          }
     }
}
