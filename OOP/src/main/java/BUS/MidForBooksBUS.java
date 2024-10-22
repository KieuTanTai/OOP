package BUS;
import java.util.Arrays;
import java.util.Scanner;
import DTO.MidForBooks;

public class MidForBooksBUS {
     MidForBooks[] midList;
     private int quantity;
     Scanner input = new Scanner(System.in);

     // constructors
     public MidForBooksBUS () {
          this.quantity = 0;
          midList = new MidForBooks[0];
     }

     public MidForBooksBUS (int quantity, MidForBooks[] midList) {
          this.quantity = quantity;
          this.midList = midList;
     }

     public MidForBooksBUS (MidForBooksBUS MidFB) {
          this.quantity = MidFB.quantity;
          this.midList = MidFB.midList;
     }

     // getter / setter
     public int getQuantity () {
          return this.quantity;
     }

     public MidForBooks[] midList () {
          return this.midList;
     }

     public void setQuantity (int quantity) {
          this.quantity = quantity;
     }

     public void setMidList (MidForBooks[] midList) {
          this.midList = midList;
     }

     // add edit remove find show....
      public void showList () {
          for (int i = 0; i < this.midList.length; i++)
               System.out.printf("%s   %s\n", this.midList[i].getProductId(), this.midList[i].getGenreId());
     }

     public String[] find (String inputId) {
          int temp = 0;
          String[] genresId;
          for (int i = 0; i < midList.length; i++) {
               if (midList[i].getProductId().equals(inputId)) {
                    if (temp == 5)
                         System.out.println();
                    System.out.printf("%s\t");
                    temp++;
               }
          }
          if (temp == 0) {
               System.out.println("not found!");
               return null;
          }
          return ;
     }

     public boolean find (String productId, String genreId)  {
          for (int i = 0; i < midList.length; i++) {
               if ((midList[i].getProductId().equals(productId)) && midList[i].getGenreId().equals(genreId))
                    return true;
          }          
          System.out.println("not found!");
          return false;
     }
     
     public void search (String productId, String genreId) {
          if (find(productId, genreId))
               System.out.printf("product id: %s\tgenre id: %s\nexist!", productId, genreId);
     }

     public void search (String inputId) {

     }

     public void add (Object midObject) {
          if (midObject instanceof MidForBooks) {
               midList = Arrays.copyOf(midList, midList.length + 1);
               midList[quantity] = (MidForBooks) midObject;
               quantity++;
          }
     }

     public void edit (String inputId) {
          // int quantityType = find(inputId); 
          // if (quantityType != -1) {
          //      System.out.print("enter new type name: ");
          //      String newTypeName = input.nextLine().trim();
          // }
     }

     public void remove (String inputId) {
          // int quantityType = find(inputId);

     }
}
