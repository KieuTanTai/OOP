package DTO;

import java.util.Scanner;

import BUS.GenresBUS;
import util.Validate;

public class BookGenres {
     private String genreID;
     private String genreName;
     private Scanner input = new Scanner(System.in);

     public BookGenres() {
     }

     public BookGenres(String genreID, String genreName) {
          this.genreID = genreIDModifier(genreID);
          this.genreName = genreName;
     }

     public String getGenreID() {
          return this.genreID;
     }

     public String getGenreName() {
          return this.genreName;
     }

     public void setGenreID(String genreID) {
          this.genreID = genreIDModifier(genreID);
     }

     public void setGenreName(String genreName) {
          this.genreName = genreName;
     }

     // set not param
     public String setID() {
          String id = "";
          BookGenres[] list = GenresBUS.getGenresList();

          if (list.length == 0 || list == null) {
               return "00000001";
          } else {
               String getID = list[list.length - 1].getGenreID();
               int prevID = Integer
                         .parseInt(getID.substring(2, getID.length() - 2));
               id = String.format("%d", prevID + 1);
               // check if id length < 8
               while (id.length() != 8)
                    id = "0" + id;
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

          int userChoose;
          System.out.printf("*".repeat(60) + "\n");
          System.out.printf("| %s %s %s |\n", "I.Cancel", "-".repeat(20), "II.Submit");
          do {
              System.out.print("choose option (1 or 2) : ");
              String option = input.nextLine().trim();
              userChoose = Validate.parseChooseHandler(option, 2);
          } while (userChoose == -1);
          System.out.printf("*".repeat(60) + "\n");
  
          if (userChoose == 1) {
              System.out.println("ok!");
              return;
          
          }
          else {
               setGenreID(genreID);
               setGenreName(genreName);
               System.out.println("create and set fields success");
          }
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
