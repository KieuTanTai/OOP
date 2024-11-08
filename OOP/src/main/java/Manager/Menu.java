package Manager;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;

import BUS.*;
import DTO.BookTypes;
import DTO.Publishers;
import util.Validate;

public class Menu {
     Scanner input = new Scanner(System.in); 
     private final TypesBUS typesList;
     private final PublishersBUS publishersList;
     
     public Menu() {
          // editHandler();
          typesList = new TypesBUS();
          publishersList = new PublishersBUS();
          addHandler();
          findHandler();
          searchHandler();
          removeHandler();
          editHandler();
     
     }

     private int editHandlers () {
          int optionChoose;
          String userChoose;
          System.out.printf("%20s", "-");
          System.out.println("option 1: " + "edit book's name: ");
          System.out.println("option 2: " + "edit book's release date: ");
          System.out.println("option 3: " + "edit book's price: ");
          System.out.println("option 4: " + "edit book's quantity: ");
          System.out.println("option 5: " + "edit book's author: ");
          System.out.println("option 6: " + "edit book's type: ");
          System.out.println("option 7: " + "edit book's genre: ");
          System.out.println("option 8: " + "edit book's format: ");
          System.out.println("option 9: " + "edit book's packaging size: ");
          System.out.println("option 10: " + "exit!");
          System.out.printf("%20s", "-");
          // validate user choose
          do {
               System.out.print("enter value of option you choose (integer): ");
               userChoose = input.nextLine().trim();
               optionChoose = Validate.parseChooseHandler(userChoose, 10);
               if (optionChoose == 10) {
                    System.out.println("exit successfully !");
                    return 0;
               }
          } while (optionChoose == -1);
          return optionChoose;
     }

     /*----- methods for both child of products -----*/ 

     /*----- methods for only Books -----*/  
     public BookTypes setType () {
          int userChoose;
          BookTypes type;
          typesList.showList();
          System.out.println("----------------------------");
          do {
               System.out.print("choose type you want (like 1, 2,etc...): ");
               String option = input.nextLine().trim();
               userChoose = Validate.parseChooseHandler(option, typesList.getCount());
          } while (userChoose == -1);

          type = typesList.getTypesList()[userChoose - 1];
          return type;
     }

     // set publisher
     public Publishers setPublisher () {
          String publisherID, publisherName; 
          do {
               System.out.print("set publisher id: ");
               publisherID = input.nextLine().trim();
               if (Validate.validateID(publisherID)) {
                    System.out.println("wrong id! ");
                    publisherID = "";
               }
          } while (publisherID.isEmpty());

          do {
               System.out.print("set publisher name: ");
               publisherName = input.nextLine().trim();
               if (!Validate.checkName(publisherName)) {
                    System.out.println("wrong name!");
                    publisherName = "";
               }
          } while (publisherName.isEmpty());
          Publishers publisher = new Publishers(publisherID, publisherName);
          publishersList.add(publisher);
          return publisher;
     }

     public void addHandler() {}
     public void findHandler() {}
     public void searchHandler() {}
     public void removeHandler() {}
     public void editHandler() {}

}
