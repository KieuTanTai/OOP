package DTO;
import java.math.BigDecimal;
import java.time.LocalDate;

public class Stationary extends Products {
     private String stationaryId;
     private StaTypes staTypes;
     private String brand;
     private String material;
     private String source;

     public Stationary () {}

     public Stationary (String productId, String productName, String productBrand, LocalDate releaseDate, BigDecimal productPrice, 
      int quantity, String stationaryId, StaTypes type, String brand , String material, String source) {
          super(productId, productName, releaseDate, productPrice, quantity);
          this.stationaryId = stationaryId;
          this.staTypes = type;
          this.brand = brand;
          this.material = material;
          this.source = source;
     }

     public String getStationaryID () {
          return this.stationaryId;
     }

     public String getTypeID () {
          return this.staTypes.getTypeID();
     }

     public String getTypeName () {
          return this.staTypes.getTypeName();
     }

     public String getBrand() {
          return this.brand;
     }

     public String getMaterial () {
          return this.material;
     }

     public String getSource () {
          return this.source;
     }

     public void setStationaryID (String stationaryId) {
          this.stationaryId = stationaryId;
     }

     public void setType (StaTypes type) {
          this.staTypes = type;
     }

     public void setBrand (String brand) {
          this.brand = brand;
     }

     public void setMaterial (String material) {
          this.material = material;
     }

     public void setSource (String source) {
          this.source = source;
     }

     // other methods
     @Override
     protected String productIDModifier (String stationaryId) {
          return "STN" + stationaryId + "PD";
     }

     @Override
     public void setInfo() {

     }

     @Override
     public void showInfo() {
          System.out.printf("Stationary Id: %s\n", stationaryId);
          System.out.printf("Stationary type name: %s\n", staTypes.getTypeName());
          System.out.printf("Material: %s\n", material);
          System.out.printf("Source: %s\n", source);
          System.out.printf("Stationary brand: %s\n", brand);
     }
}

