package Manager;
import java.util.Scanner;

import BUS.*;
import util.Validate;

public class Menu {
     Scanner input = new Scanner(System.in);
     
     public Menu() {
          // editHandler();
          addHandler();
          findHandler();
          searchHandler();
          removeHandler();
          editHandler();
     
     }

     // private int editHandlers () {
     //      int optionChoose;
     //      String userChoose;
     //      System.out.printf("%20s", "-");
     //      System.out.println("option 1: " + "edit book's name: ");
     //      System.out.println("option 2: " + "edit book's release date: ");
     //      System.out.println("option 3: " + "edit book's price: ");
     //      System.out.println("option 4: " + "edit book's quantity: ");
     //      System.out.println("option 5: " + "edit book's author: ");
     //      System.out.println("option 6: " + "edit book's type: ");
     //      System.out.println("option 7: " + "edit book's genre: ");
     //      System.out.println("option 8: " + "edit book's format: ");
     //      System.out.println("option 9: " + "edit book's packaging size: ");
     //      System.out.println("option 10: " + "exit!");
     //      System.out.printf("%20s", "-");
     //      // validate user choose
     //      do {
     //           System.out.print("enter value of option you choose (integer): ");
     //           userChoose = input.nextLine().trim();
     //           optionChoose = Validate.parseChooseHandler(userChoose, 10);
     //           if (optionChoose == 10) {
     //                System.out.println("exit successfully !");
     //                return 0;
     //           }
     //      } while (optionChoose == -1);
     //      return optionChoose;
     // }

     /*----- methods for only Books -----*/  




     public static void addHandler() {}
     public static void findHandler() {}
     public static void searchHandler() {}
     public static void removeHandler() {}
     public static void editHandler() {}

}
