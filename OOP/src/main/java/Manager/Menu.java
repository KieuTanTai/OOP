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
          setBookInfo();
     }

     private int editHandler () {
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
     // set id
     public String setID () {
          String id;
          do {
               System.out.print("set id : ");
               id = input.nextLine().trim();
               if (Validate.validateID(id)) {
                    System.out.println("error id !");
                    id = "";
               }
          } while (id.isEmpty());
          return id;
     }

     // set name
     public String setName () {
          System.out.print("set name : ");
         return input.nextLine().trim();
     }

     // set release date
     public LocalDate setReleaseDate () {
          LocalDate date;
          do {
               System.out.print("set release date : ");
               String dateInput = input.nextLine().trim();
               date = Validate.formatInputDate(dateInput);
          } while (date == null);
          return date;
     }

     // set price
     public BigDecimal setPrice () {
          BigDecimal price;
          do {
               System.out.print("set price : ");
               String value = input.nextLine();
               price = Validate.isBigDecimal(value);
          } while (price == null);
          return price;
     }

     // set quantity
     public int setQuantity () {
          int quantity;
          do {
               System.out.print("set quantity: ");
               String quantityInput = input.nextLine().trim();
               quantity = Validate.isNumber(quantityInput);
          } while (quantity == -1);
          return quantity;
     }

     /*----- methods for only Books -----*/  
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

     // set author
     public String setAuthor () {
          String authorName;
          do {
               System.out.print("set author name: ");
               authorName = input.nextLine().trim();
               if (!Validate.checkHumanName(authorName)) {
                    System.out.println("error name!");
                    authorName = "";
               }
          } while (authorName.isEmpty());
          return authorName;
     }

     // set format
     public String setFormat () {
          String[] formats = {"Hardcover", "Paperback", "Leather-bound"}; 
          int userChoose;
          System.out.printf("1.%s\n2.%s\n3.%s\n", formats[0], formats[1], formats[2]);
          do {
               System.out.print("select your option (like \"1, 2, 3\"): ");
               userChoose = Validate.parseChooseHandler(input.nextLine().trim(),3);
          } while (userChoose == -1);
          return formats[userChoose - 1];
     }

     // set packaging size
     public String setPackagingSize () {
          String packagingSize;
          do {
               System.out.println("packaging size have format: \"number 'x'  number 'cm'\" ");
               System.out.print("set packaging size: ");
               packagingSize = input.nextLine();
               if (!Validate.checkPackagingSize(packagingSize)) {
                    System.out.println("error packaging size!");
                    packagingSize = "";
               }
          } while (packagingSize.isEmpty());
          return packagingSize;
     }

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

     public void setBookInfo () {
          String id = setID(), productName = setName();
          LocalDate date = setReleaseDate();
          BigDecimal price = setPrice();
          int quantity = setQuantity();
          Publishers publisher = setPublisher(); 
          String authorName = setAuthor();
          String format = setFormat();
          String packagingSize = setPackagingSize();

          // set book type


          // set book genres 
          
     }

}
