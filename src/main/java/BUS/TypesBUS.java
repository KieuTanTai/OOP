package BUS;

import Manager.Menu;
import util.Validate;
import DTO.BookTypes;

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

public class TypesBUS implements IRuleSets {
     private static BookTypes[] typesList;
     private static int count;
     private final Scanner input = new Scanner(System.in);

     // *constructors (TEST DONE)
     public TypesBUS() {
          TypesBUS.count = 0;
          typesList = new BookTypes[0];
     }

     public TypesBUS(BookTypes[] typesList, int count) {
          TypesBUS.typesList = typesList;
          TypesBUS.count = count;
     }

     // *getter / setter (TEST DONE)
     public static BookTypes[] getTypesList() {
          return Arrays.copyOf(TypesBUS.typesList, TypesBUS.count);
     }

     public static BookTypes getType(String id) {
          for (BookTypes type : typesList)
               if (type.getTypeID().equals(id))
                    return new BookTypes(type.getTypeID(), type.getTypeName());
          return null;
     }

     public static BookTypes[] getTypes(int start, int end) {
          int size = 0;
          BookTypes[] list = new BookTypes[0];
          if (start <= end)
               return null;
          for (int i = start; i < end; i++) {
               list = Arrays.copyOf(list, list.length + 1);
               list[size] = typesList[i];
               size++;
          }
          return Arrays.copyOf(list, list.length);
     }

     public static int getCount() {
          return count;
     }

     public void setTypesList(BookTypes[] typesList) {
          TypesBUS.typesList = typesList;
     }

     public void setType(String typeID, BookTypes newType) {
          for (int i = 0; i < count; i++)
               if (typesList[i].getTypeID().equals(typeID)) {
                    typesList[i] = newType;
                    return;
               }
     }

     public void setCount(int count) {
          TypesBUS.count = count;
     }

     // all others methods like: add remove edit find show....
     // *(TEST DONE)
     public static void showList() {
          if (typesList == null)
               return;
          showAsTable(typesList);
     }

     // find methods
     @Override
     public void find() {
          Menu.addHandler();
     }

     // *(TEST DONE)
     @Override
     public int find(String nameOrID) {
          for (int i = 0; i < typesList.length; i++)
               if (typesList[i].getTypeID().equals(nameOrID)
                         || typesList[i].getTypeName().toLowerCase().equals(nameOrID.toLowerCase().trim()))
                    return i;
          System.out.println("your type is not found!");
          return -1;
     }

     // *(TEST DONE)
     public BookTypes[] relativeFind(String name) {
          int count = 0;
          BookTypes[] typesArray = new BookTypes[0];
          for (BookTypes type : typesList)
               if (type.getTypeName().toLowerCase().contains(name.toLowerCase())) {
                    typesArray = Arrays.copyOf(typesArray, typesArray.length + 1);
                    typesArray[count] = type;
                    count++;
               }
          if (count == 0) {
               System.out.println("not found any types!");
               return null;
          }
          return typesArray;
     }

     // search methods

     @Override
     public void search() {
          Menu.searchHandler();
     }

     // *(TEST DONE)
     @Override
     public void search(String nameOrID) {
          int index = find(nameOrID);
          if (index != -1)
               showAsTable(typesList[index]);
     }

     // *(TEST DONE)
     public void relativeSearch(String name) {
          BookTypes[] list = relativeFind(name);
          if (list != null) 
               showAsTable(list);
     }

     // add methods (DONE)
     @Override
     public void add() {
          Menu.addHandler();
     }

     // *(TEST DONE)
     @Override
     public void add(Object type) {
          if (type instanceof BookTypes) {
               BookTypes newType = (BookTypes) type;
               newType.setTypeID(newType.getTypeID());
               typesList = Arrays.copyOf(typesList, typesList.length + 1);
               typesList[count] = (BookTypes) type;
               count++;
          } else
               System.out.println("your type is not correct!");
     }

     public void add(BookTypes[] newTypes) {
          int tempIndex = 0, newListLength = newTypes.length;
          int initCount = getCount();
          int total = initCount + newListLength;
          typesList = Arrays.copyOf(typesList, typesList.length + newListLength);

          for (int i = initCount; i < total; i++, tempIndex++) {
               newTypes[tempIndex].setTypeID(newTypes[tempIndex].getTypeID());
               typesList[i] = newTypes[tempIndex];
          }
          TypesBUS.count = total;
     }

     // edit methods
     @Override
     public void edit() {
          Menu.editHandler();
     }

     // *(TEST DONE)
     @Override
     public void edit(String nameOrID) {
          int index = find(nameOrID);
          if (index != -1) {
               int userChoose;
               // show list for user choice
               showAsTable(typesList[index]);
               System.out.printf("| %s %s %s |\n", "I.Cancel", "-".repeat(20), "II.Edit");
               do {
                    System.out.print("choose option (1 or 2) : ");
                    String option = input.nextLine().trim();
                    userChoose = Validate.parseChooseHandler(option, 2);
               } while (userChoose == -1);
               if (userChoose == 1)
                    return;

               String newName;
               do {
                    System.out.print("Enter new name: ");
                    newName = input.nextLine().trim();
                    if (!Validate.checkName(newName)) {
                         System.out.println("name is wrong format!");
                         newName = "";
                    }
               } while (newName.isEmpty());
               typesList[index].setTypeName(newName);
          }
     }

     // remove methods (DONE)
     @Override
     public void remove() {
          Menu.removeHandler();
     }

     // *(TEST DONE)
     @Override
     public void remove(String nameOrID) {
          int index = find(nameOrID);
          if (index != -1) {
               int userChoose;
               // show list for user choice
               showAsTable(typesList[index]);
               System.out.printf("| %s %s %s |\n", "I.Cancel", "-".repeat(20), "II.Remove");
               do {
                    System.out.print("choose option (1 or 2) : ");
                    String option = input.nextLine().trim();
                    userChoose = Validate.parseChooseHandler(option, 2);
               } while (userChoose == -1);
               if (userChoose == 1)
                    return;

               for (int i = index; i < typesList.length - 1; i++)
                    typesList[i] = typesList[i + 1];
               typesList = Arrays.copyOf(typesList, typesList.length - 1);
               count--;
          }
     }

     // show as table methods
     public static void showAsTable(BookTypes[] list) {
          if (list == null)
               return;
          System.out.println("=".repeat(110));
          System.out.printf("| \t%-20s %-20s %-58s |\n", "No.", "Types ID", "Types Name");
          System.out.println("=".repeat(110));
          for (int i = 0; i < list.length; i++) {
               if (i > 0)
                    System.out.println("|" + "-".repeat(108) + "|");
               System.out.printf("| \t%-21s %-19s %-58s |\n", i + 1, list[i].getTypeID(), list[i].getTypeName());
          }
          System.out.println("=".repeat(110));
     }

     public static void showAsTable(BookTypes item) {
          if (item == null)
               return;
          System.out.println("=".repeat(110));
          System.out.printf("| \t%-20s %-20s %-58s |\n", "No.", "Types ID", "Types Name");
          System.out.println("=".repeat(110));
          System.out.println("|" + "-".repeat(108) + "|");
          System.out.printf("| \t%-21s %-19s %-58s |\n", 1, item.getTypeID(), item.getTypeName());
          System.out.println("=".repeat(110));
     }

     // execute file resources
     /*
      * DataOutputStream ? DataInputStream ?
      * FileOutputStream ? FileInputStream ?
      * read and some methods read ? write and some methods write ?
      * exception ?
      */

     // *(TEST DONE)
     public void writeFile() throws IOException {
          try (DataOutputStream file = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("src/main/resources/BookTypes", false)))) {
               file.writeInt(count);
               for (BookTypes type : typesList) {
                    file.writeUTF(type.getTypeID());
                    file.writeUTF(type.getTypeName());
               }
          } catch (Exception err) {
               System.out.printf("error writing file!\n%s\n", err.getMessage());
          }
     }

     // *(TEST DONE)
     public void readFile() throws IOException {
          File testFile = new File("src/main/resources/BookTypes");
          if (testFile.length() == 0)
               return;

          try (DataInputStream file = new DataInputStream(new BufferedInputStream(new FileInputStream("src/main/resources/BookTypes")))) {
               count = file.readInt();
               BookTypes[] list = new BookTypes[count];
               for (int i = 0; i < count; i++) {
                    String typeID = file.readUTF();
                    String typeName = file.readUTF();
                    list[i] = new BookTypes(typeID, typeName);
               }
               setCount(count);
               setTypesList(list);
          } catch (Exception err) {
               System.out.printf("error reading file!\n%s\n", err.getMessage());
          }
     }
}