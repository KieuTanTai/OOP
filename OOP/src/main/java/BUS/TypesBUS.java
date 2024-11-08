package BUS;
import DTO.BookTypes;
import java.util.Arrays;
import java.util.Scanner;

public class TypesBUS implements IRuleSets {
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
         for (int i = 0; i < typesList.length; i++)
              System.out.printf("%d: %10s %s\n",  i + 1, typesList[i].getTypeID(), typesList[i].getTypeName());
     }

     // find methods (DONE)
     // strict find
     @Override
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
     @Override
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
     @Override
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
     @Override
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
     @Override
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

     @Override
     public void add() {
          // TODO Auto-generated method stub
          throw new UnsupportedOperationException("Unimplemented method 'add'");
     }

     @Override
     public int find() {
          // TODO Auto-generated method stub
          throw new UnsupportedOperationException("Unimplemented method 'find'");
     }

     @Override
     public void search() {
          // TODO Auto-generated method stub
          throw new UnsupportedOperationException("Unimplemented method 'search'");
     }

     @Override
     public void remove() {
          // TODO Auto-generated method stub
          throw new UnsupportedOperationException("Unimplemented method 'remove'");
     }

     @Override
     public void edit() {
          // TODO Auto-generated method stub
          throw new UnsupportedOperationException("Unimplemented method 'edit'");
     }
}
