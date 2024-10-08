package BUS;
import DTO.BookTypes;
import java.util.Arrays;
import java.util.Scanner;

public class TypesBUS extends BookTypes {
     private BookTypes[] typesList;
     private int quantity;
     private Scanner input = new Scanner(System.in);

     public TypesBUS () {
          this.quantity = 0;
          typesList = new BookTypes[0];
     };

     public TypesBUS (BookTypes[] typesList, int quantity) {
          this.typesList = typesList;
          this.quantity = typesList.length;
     }

     public TypesBUS (TypesBUS typeArray) {
          this.typesList = typeArray.typesList;
          this.quantity = typeArray.quantity;
     }

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

     public void addType (BookTypes type) {
          if (type instanceof BookTypes) {
               typesList = Arrays.copyOf(typesList, typesList.length + 1);
               typesList[quantity] = type;
               quantity++;
          }
     }

     public void editTypeName () {
          String isFindId;
          boolean flag = true;
          do {
               System.out.print("enter the id of type you wanna edit: ");
               isFindId = input.nextLine().trim();
          } while (Validate.validateId(isFindId));

          for (BookTypes type : typesList) {
               if (type.getTypeId().equals(isFindId)) {
                    System.out.print("enter new type name: ");
                    String newTypeName = input.nextLine().trim();
                    type.setTypeName(newTypeName);
                    flag = false;
                    break;
               }
          }

          if (flag) {
               System.out.println("your type is not found! please try again!");
               System.out.print("\033\143");
               editTypeName();
          }
     }

     public void removeType () {
          
     }
}
