package DTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import BUS.StaTypesBUS;
import BUS.StationeriesBUS;
import util.Validate;

public class Stationeries extends Products {
     private String stationaryID;
     private StaTypes staTypes;
     private String brand;
     private String material;
     private String source;

     // constructors
     public Stationeries() {
     }

     public Stationeries(String productId, String stationaryID, String productName, LocalDate releaseDate,
               BigDecimal productPrice,
               int quantity, StaTypes type, String brand, String material, String source) {
          super(productId, productName, releaseDate, productPrice, quantity);
          this.stationaryID = stationaryIDModifier(stationaryID);
          this.staTypes = type;
          this.brand = brand;
          this.material = material;
          this.source = source;
     }

     // getter / setter
     public String getStationeriesID() {
          return this.stationaryID;
     }

     public StaTypes getType() {
          return this.staTypes;
     }

     public String getBrand() {
          return this.brand;
     }

     public String getMaterial() {
          return this.material;
     }

     public String getSource() {
          return this.source;
     }

     // setter have params
     public void setStationeriesID(String stationaryID) {
          this.stationaryID = stationaryIDModifier(stationaryID);
     }

     public void setType(StaTypes type) {
          this.staTypes = type;
     }

     public void setBrand(String brand) {
          this.brand = brand;
     }

     public void setMaterial(String material) {
          this.material = material;
     }

     public void setSource(String source) {
          this.source = source;
     }

     // setter no params
     public String setStationeriesID() {
          StringBuilder id;
          try {
               StationeriesBUS stationeriesList = new StationeriesBUS();
               stationeriesList.readFile();
               Stationeries[] list = stationeriesList.getStaList();

               if (list.length == 0) {
                    return "00000001";
               } else {
                    String getID = list[list.length - 1].getStationeriesID();
                    int prevID = Integer.parseInt(getID.substring(3));
                    id = new StringBuilder(String.format("%d", prevID + 1));
                    while (id.length() != 8)
                         id.insert(0, "0");
               }
          } catch (Exception e) {
               System.out.println("error when execute with file!" + e.getMessage());
               id = new StringBuilder();
          }
          return stationaryIDModifier(id.toString());
     }

     public StaTypes setType() {
          int userChoice;
          StaTypesBUS.showList();
          if (StaTypesBUS.getCount() == 0) // if not have any types
               return null;
          System.out.println("----------------------------");
          do {
               System.out.print("choose type (like 1, 2,etc...) : ");
               String option = input.nextLine().trim();
               userChoice = Validate.parseChooseHandler(option, StaTypesBUS.getCount());
          } while (userChoice == -1);

         return StaTypesBUS.getTypesList()[userChoice - 1];
     }

     public String setBrand() {
          String brand;
          do {
               System.out.print("set brand name : ");
               brand = input.nextLine().trim();
               if (!Validate.checkName(brand)) {
                    System.out.println("error name!");
                    brand = "";
               }
          } while (brand.isEmpty());
          return brand;
     }

     public String setMaterial() {
          String material;
          do {
               System.out.print("set material name : ");
               material = input.nextLine().trim();
               if (!Validate.checkName(material)) {
                    System.out.println("error name!");
                    material = "";
               }
          } while (material.isEmpty());
          return material;
     }

     public String setSource() {
          String source;
          do {
               System.out.print("set source name (country) : ");
               source = input.nextLine().trim();
               if (!Validate.checkHumanName(source)) {
                    System.out.println("error name!");
                    source = "";
               }
          } while (source.isEmpty());
          return source;
     }

     // *other methods (TEST DONE)
     @Override
     public void setInfo() {
          System.out.println("*".repeat(60));
          String id = setID(this);

          System.out.println("-".repeat(60));
          String staID = setStationeriesID();

          System.out.println("-".repeat(60));
          String name = setName();

          System.out.println("-".repeat(60));
          BigDecimal price = setPrice();

          System.out.println("-".repeat(60));
          LocalDate releaseDate = setRelDate();

          System.out.println("-".repeat(60));
          StaTypes type = setType();

          System.out.println("-".repeat(60));
          String brand = setBrand();

          System.out.println("-".repeat(60));
          String material = setMaterial();

          System.out.println("-".repeat(60));
          int quantity = setQuantity();

          System.out.println("*".repeat(60));
          String source = setSource();

          int userChoice;
          System.out.println("*".repeat(60));
          System.out.printf("| %s %s %s |\n", "I.Cancel", "-".repeat(20), "II.Submit");
          do {
               System.out.print("choose option (1 or 2) : ");
               String option = input.nextLine().trim();
               userChoice = Validate.parseChooseHandler(option, 2);
          } while (userChoice == -1);

          if (userChoice == 1)
               System.out.println("ok!");
          else {
               // set fields for product
               setProductID(id);
               setStationeriesID(staID);
               setProductName(name);
               setProductPrice(price);
               setReleaseDate(releaseDate);
               setQuantity(quantity);
               setType(type);
               setBrand(brand);
               setMaterial(material);
               setSource(source);
               System.out.println("create and set fields success");
          }
     }

     @Override
     public void showInfo() {
          LocalDate date = this.getReleaseDate();
          BigDecimal price = this.getProductPrice();
          String productID = this.getProductID(), productName = this.getProductName();

          System.out.println("=".repeat(140));
          System.out.printf("| %-22s : %s \n", "ID", productID != null ? productID : "N/A");
          System.out.printf("| %-22s : %s \n", "Stationeries ID", stationaryID != null ? stationaryID : "N/A");
          System.out.printf("| %-22s : %s \n", "Name", productName != null ? productName : "N/A");
          System.out.printf("| %-22s : %s \n", "Release Date",
                    date != null ? date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) : "N/A");
          System.out.printf("| %-22s : %s \n", "Type", this.staTypes != null ? this.staTypes.getTypeName() : "N/A");
          System.out.printf("| %-22s : %s \n", "Material", this.material != null ? this.material : "N/A");
          System.out.printf("| %-22s : %s \n", "Source", this.source != null ? this.source : "N/A");
          System.out.printf("| %-22s : %s \n", "Brand", this.brand != null ? this.brand : "N/A");
          System.out.printf("| %-22s : %d \n", "Quantity", this.getQuantity());
          System.out.printf("| %-22s : %s \n", "Price", price != null ? Validate.formatPrice(price) : "N/A");
          System.out.println("=".repeat(140));
     }

     private String stationaryIDModifier(String stationaryID) {
          if (stationaryID.startsWith("STA") && stationaryID.length() == 11)
               return stationaryID;
          if (!Validate.validateID(stationaryID)) {
               System.out.println("error id!");
               return "N/A";
          }
          return "STA" + stationaryID;
     }

     @Override
     protected String productIDModifier(String stationaryID) {
          if (stationaryID.startsWith("ST") && stationaryID.endsWith("PD") && stationaryID.length() == 12)
               return stationaryID;
          if (!Validate.validateID(stationaryID)) {
               System.out.println("error id!");
               return "N/A";
          }
          return "ST" + stationaryID + "PD";
     }
}
