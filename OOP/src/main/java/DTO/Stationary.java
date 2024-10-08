package DTO;
import java.math.BigDecimal;
import java.time.LocalDate;

public class Stationary extends Products {
     private String stationaryId;
     private String typeId;
     private String stationaryBrand;
     private String material;
     private String source;

     public Stationary () {}

     public Stationary (String productId, String productName, String productBrand, LocalDate releaseDate, BigDecimal productPrice, Integer quantity, String stationaryId, String typeId, String stationaryBrand , String material, String source) {
          super(productId, productName, releaseDate, productPrice, quantity);
          this.stationaryId = stationaryId;
          this.typeId = typeId;
          this.stationaryBrand = stationaryBrand;
          this.material = material;
          this.source = source;
     }

     public String getStationaryId () {
          return this.stationaryId;
     }

     public String getTypeId () {
          return this.typeId;
     }

     public String getStationaryBrand() {
          return this.stationaryBrand;
     }

     public String getMaterial () {
          return this.material;
     }

     public String getSource () {
          return this.source;
     }

     public void setStationaryId (String stationaryId) {
          this.stationaryId = stationaryId;
     }

     public void setTypeId (String typeId) {
          this.typeId = typeId;
     }

     public void setStationaryBrand (String stationaryBrand) {
          this.stationaryBrand = stationaryBrand;
     }

     public void setMaterial (String material) {
          this.material = material;
     }

     public void setSource (String source) {
          this.source = source;
     }
}

