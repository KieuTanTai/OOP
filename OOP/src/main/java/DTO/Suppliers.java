package DTO;

public class Suppliers {
    private String supplierID,supplierName;

    //constructor
    public Suppliers(){}
    public Suppliers(String supplierID, String supplierName) {
        this.supplierID = supplierID;
        this.supplierName = supplierName;
    }

    //getter
    public String getSupplierID() {
        return supplierID;
    }
    public String getSupplierName() {
        return supplierName;
    }

    //setter
    public void setSupplierID(String supplierID) {
        this.supplierID = supplierID;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }
}
