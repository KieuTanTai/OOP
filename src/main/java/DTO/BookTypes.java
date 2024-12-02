package DTO;

import java.util.Scanner;

import BUS.TypesBUS;
import util.Validate;

public class BookTypes {
     private String typeID;
     private String typeName;
     private final Scanner input = new Scanner(System.in);

     public BookTypes() {
     }

     public BookTypes(String typeID, String typeName) {
          this.typeID = typeIDModifier(typeID);
          this.typeName = typeName;
     }

     public String getTypeID() {
          return this.typeID;
     }

     public String getTypeName() {
          return this.typeName;
     }

     public void setTypeID(String typeID) {
          this.typeID = typeIDModifier(typeID);
     }

     public void setTypeName(String typeName) {
          this.typeName = typeName;
     }

     // set not param
     public String setID() {
          StringBuilder id;
          BookTypes[] list = TypesBUS.getTypesList();

          if (list.length == 0) {
               id = new StringBuilder("00000001");
          } else {
               String getID = list[list.length - 1].getTypeID();
               int prevID = Integer.parseInt(getID.substring(2, getID.length() - 2));
               id = new StringBuilder(String.format("%d", prevID + 1));
               // check if id length < 8
               while (id.length() != 8)
                    id.insert(0, "0");
          }
          return typeIDModifier(id.toString());
     }

     public String setName() {
          String name;
          do {
               System.out.print("set name : ");
               name = input.nextLine().trim();
               if (!Validate.checkName(name)) {
                    System.out.println("name is wrong format!");
                    name = "";
               }
          } while (name.isEmpty());
          return name;
     }

     public void setInfo() {
          System.out.println("*".repeat(60));
          String typeID = setID();
          // name fields
          String typeName = setName();
          System.out.println("*".repeat(60));

          int userChoice;
          System.out.printf("| %s %s %s |\n", "I.Cancel", "-".repeat(20), "II.Submit");
          do {
               System.out.print("choose option (1 or 2) : ");
               String option = input.nextLine().trim();
               userChoice = Validate.parseChooseHandler(option, 2);
          } while (userChoice == -1);
          System.out.println("*".repeat(60));

          if (userChoice == 1)
               System.out.println("ok!");
          else {
               setTypeID(typeID);
               setTypeName(typeName);
               System.out.println("create and set fields success");
          }
     }

     private String typeIDModifier(String typeID) {
          if (typeID.startsWith("BT") && typeID.endsWith("TP") && typeID.length() == 12)
               return typeID;
          if (!Validate.validateID(typeID)) {
               System.out.println("error id!");
               return "N/A";
          }
          return "BT" + typeID + "TP";
     }
}
