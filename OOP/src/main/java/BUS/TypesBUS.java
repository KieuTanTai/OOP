package BUS;

import Manager.Menu;
import util.Validate;
import DTO.BookTypes;

import java.io.DataInputStream;
import java.io.DataOutputStream;
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
          for (int i = 0; i < typesList.length; i++)
               System.out.printf("%-3d: %-6s %s\n", i + 1, typesList[i].getTypeID(), typesList[i].getTypeName());
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
               if (typesList[i].getTypeID().equals(nameOrID) || typesList[i].getTypeName().toLowerCase().equals(nameOrID.toLowerCase()))
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
               System.out.printf("id: %-6s name: %s\n", typesList[index].getTypeID(),
                         typesList[index].getTypeName());
     }

     // *(TEST DONE)
     public void relativeSearch(String name) {
          BookTypes[] list = relativeFind(name);
          if (list != null) {
               System.out.println("-----------------------------------------------");
               for (BookTypes type : list)
                    System.out.printf("id: %-6s name: %s\n", type.getTypeID(), type.getTypeName());
               System.out.println("-----------------------------------------------");
          }
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
               typesList = Arrays.copyOf(typesList, typesList.length + 1);
               typesList[count] = (BookTypes) type;
               count++;
          } else
               System.out.println("your type is not correct!");
     }

     public void add(BookTypes[] newTypes, int size) {
          typesList = Arrays.copyOf(typesList, typesList.length + newTypes.length);

          int tempIndex = 0;
          int initCount = getCount();
          int total = initCount + size;

          for (int i = initCount; i < total; i++, tempIndex++)
               typesList[i] = newTypes[tempIndex];
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
               for (int i = index; i < typesList.length - 1; i++)
                    typesList[i] = typesList[i + 1];
               typesList = Arrays.copyOf(typesList, typesList.length - 1);
               count--;
          }
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
          try (DataOutputStream file = new DataOutputStream(new FileOutputStream("OOP/src/main/resources/BookTypes", false))) {
               file.writeInt(count);
               for (int i = 0; i < count; i++) {
                    file.writeUTF(typesList[i].getTypeID());
                    file.writeUTF(typesList[i].getTypeName());
               }
          } catch (Exception err) {
               System.out.printf("error writing file!\n%s\n", err.getMessage());
          }
     }

     // *(TEST DONE)
     public void readFile() throws IOException {
          try (DataInputStream file = new DataInputStream(new FileInputStream("OOP/src/main/resources/BookTypes"))) {
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
