package BUS;
import DTO.BookGenres;
import DTO.BookTypes;
import java.util.Arrays;
import java.util.Scanner;

public class TypesBUS implements RuleSets {
     private BookTypes[] typesList;
     private int many;
     private Scanner input = new Scanner(System.in);

     // constructors
     public TypesBUS () {
          this.many = 0;
          typesList = new BookTypes[0];
     };

     public TypesBUS (BookTypes[] typesList, int many) {
          this.typesList = typesList;
          this.many = many;
     }

     public TypesBUS (TypesBUS typeArray) {
          this.typesList = typeArray.typesList;
          this.many = typeArray.many;
     }

     // getter / setter
     public BookTypes[] getTypesList () {
          return this.typesList;
     }

     public int getQuantity () {
          return this.many;
     }

     public void setTypesList (BookTypes[] typesList) {
          this.typesList = typesList;
     }

     public void setQuantity (int many) {
          this.many = many;
     }
     
     // add remove edit find show....
     public void showList () {
          for (int i = 0; i < this.typesList.length; i++)
               System.out.printf("%s   %s\n", this.typesList[i].getTypeId(), this.typesList[i].getTypeName());
     }

     // find methods
     public int find (String inputId)  {
          for ( int i = 0; i < this.typesList.length; i++) {
               if (typesList[i].getTypeId().equals(inputId))
                    return i;
          }
          System.out.println("your id is not found !");
          return -1;
     }

     public BookTypes[] relativeFind (String inputValue) {
          int many = 0;
          BookTypes[] typesArray = new BookTypes[0];
          for (int i = 0; i < typesList.length; i++)
               if (typesList[i].getTypeName().contains(inputValue)) {
                    typesArray = Arrays.copyOf(typesArray, typesArray.length + 1);
                    typesArray[many] = typesArray[i];
                    many++;
               }
          if (many == 0)
               return null;
          return typesArray;
     }

     // search methods
     public void search (String inputId) {
          int indexType = find(inputId);
          if  (indexType != -1)
               System.out.printf("Your type id is: &s\n Type name: &s\n", inputId, typesList[indexType].getTypeName());
     }

     public void relativeSearch (String inputValue) {
          BookTypes[] indexList = relativeFind(inputValue);
          if (indexList.equals(null)) {
               System.out.println("not found any types!");
               return;
          }
          for (int i = 0; i < indexList.length; i++)
               System.out.printf("total types found : %d\ntype name : %s\n", indexList.length, indexList[i].getTypeName());
     }

     public void add (Object type) {
          if (type instanceof BookTypes) {
               typesList = Arrays.copyOf(typesList, typesList.length + 1);
               typesList[many] = (BookTypes) type;
               many++;
          }
     }

     public void edit (String inputId) {
          int index = find(inputId); 
          if (index != -1) {
               System.out.print("enter new type name: ");
               String newTypeName = input.nextLine().trim();
               typesList[index].setTypeName(newTypeName);
          }
     }

     public void remove (String inputId) {
          int index = find(inputId);
          if (index != -1) {
               for (int i = index; i < this.typesList.length - 1; i++)
                    typesList[i] = typesList[i+1];
               typesList = Arrays.copyOf(typesList, typesList.length -1);
          }

     }
}
