package DTO;

public class Suppliers {
    private String supplierID;
    private String supplierName;
    private String phone;

    //constructor
    public Suppliers(){}
    public Suppliers(String supplierID, String supplierName, String phone) {
        this.supplierID = supplierID;
        this.supplierName = supplierName;
        this.phone = phone;
    }

    //getter
    public String getSupplierID() {
        return supplierID;
    }
    public String getSupplierName() {
        return supplierName;
    }

    public String getPhone() {
        return phone;
    }

    //setter
    public void setSupplierID(String supplierID) {
        this.supplierID = supplierID;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
