package DTO;

public class BookTypes {
     private String typeID;
     private String typeName; 

     public BookTypes() {}
     
     public BookTypes(String typeID, String typeName) {
          this.typeID = typeID;
          this.typeName = typeName;
     }

     public String getTypeID () {
          return this.typeID;
     }
     
     public String getTypeName () {
          return this.typeName;
     }
     
     public void setTypeID (String typeID) {
          this.typeID = typeID;
     }

     public void setTypeName (String typeName) {
          this.typeName = typeName;
     }
}
