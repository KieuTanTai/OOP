package BUS;
import DTO.BookTypes;
import java.util.Arrays;
import java.util.Scanner;

public class TypesBUS extends BookTypes {
     private BookTypes[] typesList;
     private int quantity;
     private Scanner input = new Scanner(System.in);

     // constructors
     public TypesBUS () {
          this.quantity = 0;
          typesList = new BookTypes[0];
     };

     public TypesBUS (BookTypes[] typesList, int quantity) {
          this.typesList = typesList;
          this.quantity = quantity;
     }

     public TypesBUS (TypesBUS typeArray) {
          this.typesList = typeArray.typesList;
          this.quantity = typeArray.quantity;
     }

     // getter / setter
     public BookTypes[] getTypesList () {
          return this.typesList;
     }

     public int getQuantity () {
          return this.quantity;
     }

     public void setTypesList (BookTypes[] typesList) {
          this.typesList = typesList;
     }

     public void setQuantity (int quantity) {
          this.quantity = quantity;
     }

     
     // add remove edit find....
     public void showTypesList () {
          for (int i = 0; i < this.typesList.length; i++)
               System.out.printf("%s   %s\n", this.typesList[i].getTypeId(), this.typesList[i].getTypeName());
     }

     public int findType (String inputId)  {
          for ( int i = 0; i < this.typesList.length; i++) {
               if (typesList[i].getTypeId().equals(inputId))
                    return i;
          }
          System.out.println("your id is not found !");
          return -1;
     }

     public void searchType (String inputId) {
          int quantityType = findType(inputId);
          if  (quantityType != -1)
               System.out.printf("Your type id is: &s\n Type name: &s\n", inputId, typesList[quantityType].getTypeName());
     }

     public void addType (BookTypes type) {
          if (type instanceof BookTypes) {
               typesList = Arrays.copyOf(typesList, typesList.length + 1);
               typesList[quantity] = type;
               quantity++;
          }
     }

     public void editTypeName (String inputId) {
          int quantityType = findType(inputId); 
          if (quantityType != -1) {
               System.out.print("enter new type name: ");
               String newTypeName = input.nextLine().trim();
               typesList[quantityType].setTypeName(newTypeName);
          }
     }

     public void removeType (String inputId) {
          int quantityType = findType(inputId);
          if (quantityType != -1) {
               for (int i = findType(inputId); i < this.typesList.length - 1; i++)
                    typesList[i] = typesList[i+1];
               typesList = Arrays.copyOf(typesList, typesList.length -1);
          }

     }
}
