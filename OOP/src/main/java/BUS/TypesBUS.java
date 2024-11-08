package BUS;

import Manager.Menu;
import DTO.BookTypes;
import java.util.Arrays;
import java.util.Scanner;

public class TypesBUS implements IRuleSets {
     private static BookTypes[] typesList;
     private static int count;
     private final Scanner input = new Scanner(System.in);

    // constructors
     public TypesBUS() {
          TypesBUS.count = 0;
          typesList = new BookTypes[0];
     }

     public TypesBUS(BookTypes[] typesList, int count) {
          TypesBUS.typesList = typesList;
          TypesBUS.count = count;
     }

     // getter / setter
     public static BookTypes[] getTypesList() {
          return Arrays.copyOf(TypesBUS.typesList, TypesBUS.count);
     }

     public static int getCount() {
          return count;
     }

     public void setTypesList(BookTypes[] typesList) {
          TypesBUS.typesList = typesList;
     }

     public void setCount(int count) {
          TypesBUS.count = count;
     }

     // all others methods like: add remove edit find show....
     // show list of types for user (DONE)
     public static void showList() {
          for (int i = 0; i < typesList.length; i++)
               System.out.printf("%d: %10s %s\n", i + 1, typesList[i].getTypeID(), typesList[i].getTypeName());
     }

     // read / write file
     public void writeFile() {}

     public void readFile() {}

     // find methods (DONE)
     @Override
     public void find() {
          Menu.addHandler();
     }

     @Override
     public int find(String inputId) {
          for (int i = 0; i < typesList.length; i++) {
               if (typesList[i].getTypeID().equals(inputId))
                    return i;
          }
          return -1;
     }

     public BookTypes[] relativeFind(String name) {
          int count = 0;
          BookTypes[] typesArray = new BookTypes[0];
          for (BookTypes type : typesList)
               if (type.getTypeName().contains(name)) {
                    typesArray = Arrays.copyOf(typesArray, typesArray.length + 1);
                    typesArray[count] = type;
                    count++;
               }
          if (count == 0)
               return null;
          return typesArray;
     }

     // search methods (DONE)
     @Override
     public void search() {
          Menu.searchHandler();
     }

     @Override
     public void search(String inputId) {
          int index = find(inputId);
          if (index == -1) {
               System.out.println("your type is not found!");
               return;
          }
          System.out.printf("Your type id is: %s\n Type name: %s\n", inputId, typesList[index].getTypeName());
     }

     public void relativeSearch(String name) {
          BookTypes[] list = relativeFind(name);
          if (list == null) {
               System.out.println("not found any types!");
               return;
          }
          for (BookTypes type : list)
               System.out.printf("type's id : %s\ntype name : %s\n", type.getTypeID(), type.getTypeName());
     }

     // add methods (DONE)
     @Override
     public void add() {
          Menu.addHandler();
     }

     @Override
     public void add(Object type) {
          if (type instanceof BookTypes) {
               typesList = Arrays.copyOf(typesList, typesList.length + 1);
               typesList[count] = (BookTypes) type;
               count++;
          } else
               System.out.println("your type is not correct !");
     }

     // edit methods (DONE)
     @Override
     public void edit() {
          Menu.editHandler();
     }

     @Override
     public void edit(String inputId) {
          int index = find(inputId);
          if (index == -1) {
               System.out.println("your type is not found !");
               return;
          }
          System.out.print("enter new type name: ");
          String newTypeName = input.nextLine().trim();
          typesList[index].setTypeName(newTypeName);
     }

     // remove methods (DONE)     
     @Override
     public void remove() {
          Menu.removeHandler();
     }

     @Override
     public void remove(String inputId) {
          int index = find(inputId);
          if (index == -1) {
               System.out.println("your type is not found !");
               return;
          }
          for (int i = index; i < typesList.length - 1; i++)
               typesList[i] = typesList[i + 1];
          typesList = Arrays.copyOf(typesList, typesList.length - 1);
          count--;
     }
}
