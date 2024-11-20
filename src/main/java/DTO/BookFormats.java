package DTO;

import java.util.Scanner;

import BUS.BookFormatsBUS;
import util.Validate;

public class BookFormats {
     private String formatID;
     private String formatName;
     private Scanner input = new Scanner(System.in);

     public BookFormats() {
     }

     public BookFormats(String formatID, String formatName) {
          this.formatID = formatIDModifier(formatID);
          this.formatName = formatName;
     }

     public String getFormatID() {
          return this.formatID;
     }

     public String getFormatName() {
          return this.formatName;
     }

     public void setFormatID(String formatID) {
          this.formatID = formatIDModifier(formatID);
     }

     public void setFormatName(String formatName) {
          this.formatName = formatName;
     }

     // set not param
     public String setID() {
          String id = "";
          BookFormats[] list = BookFormatsBUS.getFormatsList();

          if (list.length == 0 || list == null) {
               return "00000001";
          } else {
               String getID = list[list.length - 1].getFormatID();
               int prevID = Integer
                         .parseInt(getID.substring(3, getID.length() - 2));
               id = String.format("%d", prevID + 1);
          }
          return formatIDModifier(id);
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
          this.formatID = setID();
          System.out.println("-".repeat(60));
          this.formatName = setName();
          System.out.println("*".repeat(60));
     }

     private String formatIDModifier(String formatID) {
          if (formatID.startsWith("FOR") && formatID.endsWith("MT") && formatID.length() == 13)
               return formatID;
          if (!Validate.validateID(formatID)) {
               System.out.println("error id!");
               return "N/A";
          }
          return "FOR" + formatID + "MT";
     }
}
