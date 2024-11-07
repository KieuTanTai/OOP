package DTO;

public class StaTypes {
    private String typeID,typeName;

    //constructor
    public StaTypes(){}

    public StaTypes(String typeID, String typeName) {
        this.typeID = typeID;
        this.typeName = typeName;
    }

    //getter
    public String getTypeID() {
        return typeID;
    }

    public String getTypeName() {
        return typeName;
    }

    //setter
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public void setTypeID(String typeID) {
        this.typeID = typeID;
    }
}

