package DTO;

import java.util.Scanner;

import BUS.TypesBUS;
import util.Validate;

public class BookTypes {
     private String typeID;
     private String typeName;
     private Scanner input = new Scanner(System.in);

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
          String id = "";
          BookTypes[] list = TypesBUS.getTypesList();

          if (list.length == 0 || list == null) {
               return "00000001";
          } else {
               int prevID = Integer
                         .parseInt((list[list.length - 1]).getTypeID().substring(2, list.length - 2));
               id = String.format("%d", prevID + 1);
          }
          return typeIDModifier(id);
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
          this.typeID = setID();
          System.out.println("-".repeat(60));
          this.typeName = setName();
          System.out.println("*".repeat(60));
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
