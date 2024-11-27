package BUS;

import util.Validate;
import DTO.BookFormats;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class BookFormatsBUS implements IRuleSets {
     private static BookFormats[] formatsList;
     private static int count;
     private final Scanner input = new Scanner(System.in);

     // *constructors
     public BookFormatsBUS() {
          BookFormatsBUS.count = 0;
          formatsList = new BookFormats[0];
     }

     public BookFormatsBUS(BookFormats[] formatsList, int count) {
          BookFormatsBUS.formatsList = formatsList;
          BookFormatsBUS.count = count;
     }

     // *getter / setter
     public static BookFormats[] getFormatsList() {
          return Arrays.copyOf(BookFormatsBUS.formatsList, BookFormatsBUS.count);
     }

     public static BookFormats getFormat(String id) {
          for (BookFormats format : formatsList)
               if (format.getFormatID().equals(id))
                    return new BookFormats(format.getFormatID(), format.getFormatName());
          return null;
     }

     public static BookFormats[] getFormats(int start, int end) {
          int size = 0;
          BookFormats[] list = new BookFormats[0];
          if (start <= end)
               return null;
          for (int i = start; i < end; i++) {
               list = Arrays.copyOf(list, list.length + 1);
               list[size] = formatsList[i];
               size++;
          }
          return Arrays.copyOf(list, list.length);
     }

     public static int getCount() {
          return count;
     }

     public void setFormatsList(BookFormats[] formatsList) {
          BookFormatsBUS.formatsList = formatsList;
     }

     public void setFormat(String formatID, BookFormats newFormat) {
          for (int i = 0; i < count; i++)
               if (formatsList[i].getFormatID().equals(formatID)) {
                    formatsList[i] = newFormat;
                    return;
               }
     }

     public void setCount(int count) {
          BookFormatsBUS.count = count;
     }

     // Methods for add, remove, edit, search, etc.
     // *(TEST DONE)
     public static void showList() {
          if (formatsList == null)
               return;
          showAsTable(formatsList);
     }

     @Override
     public void find() {
     }

     @Override
     public int find(String nameOrID) {
          for (int i = 0; i < formatsList.length; i++)
               if (formatsList[i].getFormatID().equals(nameOrID)
                         || formatsList[i].getFormatName().toLowerCase().equals(nameOrID.toLowerCase().trim()))
                    return i;
          System.out.println("your format is not found!");
          return -1;
     }

     public BookFormats[] relativeFind(String name) {
          int count = 0;
          BookFormats[] formatsArray = new BookFormats[0];
          for (BookFormats format : formatsList)
               if (format.getFormatName().toLowerCase().contains(name.toLowerCase())) {
                    formatsArray = Arrays.copyOf(formatsArray, formatsArray.length + 1);
                    formatsArray[count] = format;
                    count++;
               }
          if (count == 0) {
               System.out.println("not found any formats!");
               return null;
          }
          return formatsArray;
     }

     @Override
     public void search() {
          int choice;
          do {
               System.out.println("*".repeat(60));
               System.out.println("I. Strict search");
               System.out.println("II. Relative search");
               System.out.println("0. Exit");
               System.out.println("*".repeat(60));
               System.out.print("Enter your choice: ");
               choice = Validate.parseChooseHandler(input.nextLine().trim(), 2);
               if (choice == 0) {
                    System.out.println("Exit program.");
                    break;
               }

               System.out.println("Enter name or id of format: ");
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
               showAsTable(formatsList[index]);
     }

     public void relativeSearch(String name) {
          BookFormats[] list = relativeFind(name);
          if (list != null)
               showAsTable(list);
     }

     @Override
     public void add() {
          int choice;
          do {
               System.out.println("*".repeat(60));
               System.out.println("I. Add format");
               System.out.println("II. Add list of formats");
               System.out.println("0. Exit");
               System.out.println("*".repeat(60));
               System.out.print("Enter your choice: ");
               choice = Validate.parseChooseHandler(input.nextLine().trim(), 2);

               try {
                    switch (choice) {
                         case 1:
                              BookFormats newFormat = new BookFormats();
                              newFormat.setInfo();
                              // confirm
                              System.out.printf("| %s %s %s |\n", "I.Cancel", "-".repeat(20), "II.Add");
                              do {
                                   System.out.print("choose option (1 or 2) : ");
                                   String option = input.nextLine().trim();
                                   choice = Validate.parseChooseHandler(option, 2);
                              } while (choice == -1);
                              if (choice == 1) break;
                              add(newFormat);
                              writeFile();
                              break;
                         case 2:
                              int count = 0;
                              BookFormats[] list = new BookFormats[0];
                              do {
                                   System.out.print("Enter total format you wanna add : ");
                                   String option = input.nextLine().trim();
                                   choice = Validate.isNumber(option);
                              } while (choice == -1);
                              // for loop with input time
                              for (int i = 0; i < choice; i++) {
                                   BookFormats format = new BookFormats();
                                   format.setInfo();
                                   list = Arrays.copyOf(list, list.length + 1);
                                   list[count] = format;
                                   count++;
                              }

                              // confirm
                              System.out.printf("| %s %s %s |\n", "I.Cancel", "-".repeat(20), "II.Add");
                              do {
                                   System.out.print("choose option (1 or 2) : ");
                                   String option = input.nextLine().trim();
                                   choice = Validate.parseChooseHandler(option, 2);
                              } while (choice == -1);
                              if (choice == 1) break;
                              add(list);
                              writeFile();
                              break;
                         case 0:
                              System.out.println("Exit program.");
                              break;
                    }
               } catch (Exception e) {
                    System.out.printf("error writing file!\nt%s\n", e.getMessage());
               }

          } while (choice != 0);
     }

     @Override
     public void add(Object format) {
          if (format instanceof BookFormats) {
               formatsList = Arrays.copyOf(formatsList, formatsList.length + 1);
               formatsList[count] = (BookFormats) format;
               count++;
          } else
               System.out.println("your format is not correct!");
     }

     public void add(BookFormats[] newFormats) {
          int tempIndex = 0, newListLength = newFormats.length;
          int initCount = getCount();
          int total = initCount + newListLength;
          formatsList = Arrays.copyOf(formatsList, formatsList.length + newListLength);

          for (int i = initCount; i < total; i++, tempIndex++)
               formatsList[i] = newFormats[tempIndex];
          BookFormatsBUS.count = total;
     }

     @Override
     public void edit() {
          int choice;
          do {
               System.out.println("*".repeat(60));
               System.out.println("I. Edit");
               System.out.println("0. Exit");
               System.out.println("*".repeat(60));
               System.out.print("Enter your choice: ");
               choice = Validate.parseChooseHandler(input.nextLine().trim(), 1);
               if (choice == 0) {
                    System.out.println("Exit program.");
                    break;
               } else if (choice == 1) {
                    try {
                         System.out.println("Enter name or id of format: ");
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
     public void edit(String nameOrID) {
          int index = find(nameOrID);
          if (index != -1) {
               String newName;
               do {
                    System.out.print("Enter new name: ");
                    newName = input.nextLine().trim();
                    if (!Validate.checkName(newName)) {
                         System.out.println("name is wrong format!");
                         newName = "";
                    }
               } while (newName.isEmpty());
               formatsList[index].setFormatName(newName);
          }
     }

     @Override
     public void remove() {
          int choice;
          do {
               System.out.println("*".repeat(60));
               System.out.println("I. Remove");
               System.out.println("0. Exit");
               System.out.println("*".repeat(60));
               System.out.print("Enter your choice: ");
               choice = Validate.parseChooseHandler(input.nextLine().trim(), 1);
               if (choice == 0) {
                    System.out.println("Exit program.");
                    break;
               } else if (choice == 1) {
                    try {
                         System.out.println("Enter name or id of format: ");
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
     public void remove(String nameOrID) {
          int index = find(nameOrID);
          if (index != -1) {
               for (int i = index; i < formatsList.length - 1; i++)
                    formatsList[i] = formatsList[i + 1];
               formatsList = Arrays.copyOf(formatsList, formatsList.length - 1);
               count--;
          }
     }

     // Show methods for displaying data in a table format
     public static void showAsTable(BookFormats[] list) {
          if (list == null)
               return;
          System.out.println("=".repeat(110));
          System.out.printf("| \t%-20s %-20s %-58s |\n", "No.", "Formats ID", "Formats Name");
          System.out.println("=".repeat(110));
          for (int i = 0; i < list.length; i++) {
               if (i > 0)
                    System.out.println("|" + "-".repeat(108) + "|");
               System.out.printf("| \t%-21s %-19s %-58s |\n", i + 1, list[i].getFormatID(), list[i].getFormatName());
          }
          System.out.println("=".repeat(110));
     }

     public static void showAsTable(BookFormats item) {
          if (item == null)
               return;
          System.out.println("=".repeat(110));
          System.out.printf("| \t%-20s %-20s %-58s |\n", "No.", "Formats ID", "Formats Name");
          System.out.println("=".repeat(110));
          System.out.println("|" + "-".repeat(108) + "|");
          System.out.printf("| \t%-21s %-19s %-58s |\n", 1, item.getFormatID(), item.getFormatName());
          System.out.println("=".repeat(110));
     }

     // File handling methods
     public void writeFile() throws IOException {
          try (DataOutputStream file = new DataOutputStream(
                    new FileOutputStream("OOP/src/main/resources/BookFormats", false))) {
               file.writeInt(count);
               for (int i = 0; i < count; i++) {
                    file.writeUTF(formatsList[i].getFormatID());
                    file.writeUTF(formatsList[i].getFormatName());
               }
          } catch (Exception err) {
               System.out.printf("error writing file!\n%s\n", err.getMessage());
          }
     }

     public void readFile() throws IOException {
          File testFile = new File("src/main/resources/BookFormats");
          if (testFile.length() == 0)
               return;

          try (DataInputStream file = new DataInputStream(
                    new BufferedInputStream(new FileInputStream("src/main/resources/BookFormats")))) {
               count = file.readInt();
               BookFormats[] list = new BookFormats[count];
               for (int i = 0; i < count; i++) {
                    String formatID = file.readUTF();
                    String formatName = file.readUTF();
                    list[i] = new BookFormats(formatID, formatName);
               }
               setCount(count);
               setFormatsList(list);
          } catch (Exception err) {
               System.out.printf("error reading file!\n%s\n", err.getMessage());
          }
     }
}
