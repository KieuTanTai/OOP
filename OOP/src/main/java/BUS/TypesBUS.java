package BUS;
import DTO.BookTypes;
import java.util.Arrays;
import java.util.Scanner;

public class TypesBUS implements RuleSets {
     private BookTypes[] typesList;
     private int count;
     private final Scanner input = new Scanner(System.in);

     // constructors
     public TypesBUS () {
          this.count = 0;
          typesList = new BookTypes[0];
     }

     public TypesBUS (BookTypes[] typesList, int count) {
          this.typesList = typesList;
          this.count = count;
     }

     public TypesBUS (TypesBUS typeArray) {
          this.typesList = typeArray.typesList;
          this.count = typeArray.count;
     }

     // getter / setter
     public BookTypes[] getTypesList () {
          return this.typesList;
     }

     public int getCount () {
          return this.count;
     }

     public void setTypesList (BookTypes[] typesList) {
          this.typesList = typesList;
     }

     public void setCount (int count) {
          this.count = count;
     }
     
     // all others methods like: add remove edit find show....
     // show list of types for user (DONE)
     public void showList () {
         for (BookTypes bookTypes : this.typesList)
             System.out.printf("%s   %s\n", bookTypes.getTypeID(), bookTypes.getTypeName());
     }

     // find methods (DONE)
     // strict find
     public int find (String inputId)  {
          for ( int i = 0; i < this.typesList.length; i++) {
               if (typesList[i].getTypeID().equals(inputId))
                    return i;
          }
          return -1;
     }

     // relative find
     public BookTypes[] relativeFind (String name) {
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
     // strict search 
     public void search (String inputId) {
          int index = find(inputId);
          if  (index == -1) {
               System.out.println("your type is not found!");
               return;
          }
          System.out.printf("Your type id is: %s\n Type name: %s\n", inputId, typesList[index].getTypeName());
     }

     // relative search
     public void relativeSearch (String name) {
          BookTypes[] list = relativeFind(name);
          if (list == null) {
               System.out.println("not found any types!");
               return;
          }
         for (BookTypes type : list)
             System.out.printf("type's id : %s\ntype name : %s\n", type.getTypeID(), type.getTypeName());
     }

     // add method (DONE)
     public void add (Object type) {
          if (type instanceof BookTypes) {
               typesList = Arrays.copyOf(typesList, typesList.length + 1);
               typesList[count] = (BookTypes) type;
               count++;
          }
          else
               System.out.println("your type is not correct !");
     }

     // edit method (DONE)
     public void edit (String inputId) {
          int index = find(inputId); 
          if (index == -1) {
               System.out.println("your type is not found !");
               return;
          }
          System.out.print("enter new type name: ");
          String newTypeName = input.nextLine().trim();
          typesList[index].setTypeName(newTypeName);
     }

     // remove method (DONE)
     public void remove (String inputId) {
          int index = find(inputId);
          if (index == -1) {
               System.out.println("your type is not found !");
               return;
          }
          for (int i = index; i < this.typesList.length - 1; i++)
               typesList[i] = typesList[i + 1];
          typesList = Arrays.copyOf(typesList, typesList.length -1);
          count--;
     }
}
