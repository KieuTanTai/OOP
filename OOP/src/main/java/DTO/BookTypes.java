package DTO;

public class BookTypes {
     private String typeId;
     private String typeName; 

     public BookTypes() {}
     
     public BookTypes(String typeId, String typeName) {
          this.typeId = typeId;
          this.typeName = typeName;
     }

     public String getTypeId () {
          return this.typeId;
     }
     
     public String getTypeName () {
          return this.typeName;
     }
     
     public void setTypeId (String typeId) {
          this.typeId = typeId;
     }

     public void setTypeName (String typeName) {
          this.typeName = typeName;
     }
}
