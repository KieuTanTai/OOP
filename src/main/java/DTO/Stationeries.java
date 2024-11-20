package DTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

import BUS.StaTypesBUS;
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

     public Stationeries(String productId, String stationaryID, String productName, LocalDate releaseDate, BigDecimal productPrice,
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
          String id;
          do {
               System.out.print("set stationary id : ");
               id = input.nextLine().trim();
               if (Validate.validateID(id)) {
                    System.out.println("error id!");
                    id = "";
               }
          } while (id.isEmpty());
          return id;
     }

     public StaTypes setType() {
          int userChoose;
          StaTypesBUS.showList();
          if (StaTypesBUS.getCount() == 0) // if not have any types
               return null;
          System.out.println("----------------------------");
          do {
               System.out.print("choose type (like 1, 2,etc...) : ");
               String option = input.nextLine().trim();
               userChoose = Validate.parseChooseHandler(option, StaTypesBUS.getCount());
          } while (userChoose == -1);

          StaTypes type = StaTypesBUS.getTypesList()[userChoose - 1];
          return type;
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
          String id = setID();

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

          int userChoose;
          System.out.printf("*".repeat(60) + "\n");
          System.out.printf("| %s %s %s |\n", "I.Cancel", "-".repeat(20), "II.Submit");
          do {
               System.out.print("choose option (1 or 2) : ");
               String option = input.nextLine().trim();
               userChoose = Validate.parseChooseHandler(option, 2);
          } while (userChoose == -1);

          if (userChoose == 1) {
               System.out.println("ok!");
               return;
          } else {
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
          System.out.printf("| %-22s : %s \n", "Release Date", date != null ? date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) : "N/A");
          System.out.printf("| %-22s : %s \n", "Type", this.staTypes != null ? this.staTypes.getTypeName() : "N/A");
          System.out.printf("| %-22s : %s \n", "Material", this.material != null ? this.material : "N/A");
          System.out.printf("| %-22s : %s \n", "Source", this.source != null ? this.source : "N/A");
          System.out.printf("| %-22s : %s \n", "Brand", this.brand != null ? this.brand : "N/A");
          System.out.printf("| %-22s : %d \n", "Quantity", this.getQuantity());
          System.out.printf("| %-22s : %s \n", "Price", price != null ? Validate.formatPrice(price) : "N/A");
          System.out.println("=".repeat(140));
     }

     private String stationaryIDModifier(String stationaryID) {
          if (stationaryID.startsWith("STA") && stationaryID.length() == 8)
               return stationaryID;
          String regex = "^(?=[a-zA-Z0-9_-]{5}$)[^%+\\/#'::\":]+$";
          Pattern pattern = Pattern.compile(regex);
          if (!pattern.matcher(stationaryID).matches()) {
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
