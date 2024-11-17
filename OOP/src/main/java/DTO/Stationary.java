package DTO;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import BUS.PublishersBUS;
import BUS.StaTypesBUS;
import util.Validate;

public class Stationary extends Products {
     private String stationaryID;
     private StaTypes staTypes;
     private String brand;
     private String material;
     private String source;

     // constructors
     public Stationary() {
     }

     public Stationary(String productId, String stationaryID, String productName, LocalDate releaseDate,
               BigDecimal productPrice,
               int quantity, StaTypes type, String brand, String material, String source) {
          super(productId, productName, releaseDate, productPrice, quantity);
          this.stationaryID = stationaryID;
          this.staTypes = type;
          this.brand = brand;
          this.material = material;
          this.source = source;
     }

     // getter / setter
     public String getStationaryID() {
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
     public void setStationaryID(String id) {
          this.stationaryID = id;
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
     public String setStationaryID() {
          String id;
          do {
               System.out.print("set stationary id : ");
               id = input.nextLine().trim();
               if (Validate.validateID(id)) {
                    System.out.println("error id !");
                    id = "";
               }
          } while (id.isEmpty());
          return id;
     }

     public StaTypes setType() {
          int userChoose;
          PublishersBUS.showList();
          System.out.println("----------------------------");
          do {
               System.out.print("choose type (like 1, 2,etc...): ");
               String option = input.nextLine().trim();
               userChoose = Validate.parseChooseHandler(option, StaTypesBUS.getCount());
          } while (userChoose == -1);

          StaTypes type = StaTypesBUS.getTypesList()[userChoose - 1];
          return type;
     }

     public String setBrand() {
          String brand;
          do {
               System.out.print("set brand name: ");
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
               System.out.print("set material name: ");
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
               System.out.print("set source name (country): ");
               source = input.nextLine().trim();
               if (!Validate.checkHumanName(source)) {
                    System.out.println("error name!");
                    source = "";
               }
          } while (source.isEmpty());
          return source;
     }

     // other methods
     @Override
     public void setInfo() {
          System.out.println("-----------------------------------------------");
          String id = setID();
          System.out.println("-----------------------------------------------");
          String staID = setStationaryID();
          System.out.println("-----------------------------------------------");
          String name = setName();
          System.out.println("-----------------------------------------------");
          BigDecimal price = setPrice();
          System.out.println("-----------------------------------------------");
          LocalDate releaseDate = setRelDate();
          System.out.println("-----------------------------------------------");
          StaTypes type = setType();
          System.out.println("-----------------------------------------------");
          String brand = setBrand();
          System.out.println("-----------------------------------------------");
          String material = setMaterial();
          System.out.println("-----------------------------------------------");
          int quantity = setQuantity();
          System.out.println("-----------------------------------------------");
          String source = setSource();

          int userChoose;
          System.out.println("***********************************************");
          System.out.println("I.Cancel ------------------- II.Submit");
          do {
               System.out.print("choose option (like 1, 2,etc...): ");
               String option = input.nextLine().trim();
               userChoose = Validate.parseChooseHandler(option, 2);
          } while (userChoose == -1);

          if (userChoose == 1) {
               System.out.println("ok!");
               return;
          } else {
               // set fields for product
               setProductID(id);
               setStationaryID(staID);
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
          NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.of("vi", "VN"));
          System.out.println("=".repeat(160));
          System.out.printf("| %-22s : %s \n", "ID", this.getProductID());
          System.out.printf("| %-22s : %s \n", "Stationary ID", stationaryID);
          System.out.printf("| %-22s : %s \n", "Name", this.getProductName());
          System.out.printf("| %-22s : %s \n", "Release Date", 
                  date != null ? date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) : "N/A");
          System.out.printf("| %-22s : %s \n", "Type", 
                  staTypes != null ? staTypes.getTypeName() : "N/A");
          System.out.printf("| %-22s : %s \n", "Material", material != null ? material : "N/A");
          System.out.printf("| %-22s : %s \n", "Source", source != null ? source : "N/A");
          System.out.printf("| %-22s : %s \n", "Brand", brand != null ? brand : "N/A");
          System.out.printf("| %-22s : %d \n", "Quantity", this.getQuantity());
          System.out.printf("| %-22s : %s \n", "Price", formatter.format(this.getProductPrice()));
          System.out.println("=".repeat(160));
     }

     @Override
     protected String productIDModifier(String stationaryID) {
          return "STN" + stationaryID + "PD";
     }
}
