package DTO;

import java.util.Scanner;

import BUS.TypesBUS;
import util.Validate;

public class BookGenres {
     private String genreID;
     private String genreName;
     private Scanner input = new Scanner(System.in);

     public BookGenres() {
     }

     public BookGenres(String genreID, String genreName) {
          this.genreID = genreID;
          this.genreName = genreName;
     }

     public String getGenreID() {
          return this.genreID;
     }

     public String getGenreName() {
          return this.genreName;
     }

     public void setGenreID(String genreID) {
          this.genreID = genreID;
     }

     public void setGenreName(String genreName) {
          this.genreName = genreName;
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
          return genreIDModifier(id);
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
          this.genreID = setID();
          System.out.println("-".repeat(60));
          this.genreName = setName();
          System.out.println("*".repeat(60));
     }

     private String genreIDModifier(String genreID) {
          if (genreID.startsWith("BG") && genreID.endsWith("GN") && genreID.length() == 12)
               return genreID;
          if (!Validate.validateID(genreID)) {
               System.out.println("error id!");
               return "N/A";
          }
          return "BG" + genreID + "GN";
     }
}
