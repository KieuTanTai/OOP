package DTO;
import java.math.BigDecimal;
import java.time.LocalDate;

import util.Validate;

public class BillDetails{
    private String billId;
    private int quanity;
    private BigDecimal price;
    private BigDecimal subTotal;

    public BillDetails() {
    }

    public BillDetails(String billId, int quanity, BigDecimal price, BigDecimal subTotal) {
        this.billId = billId;
        this.quanity = quanity;
        this.price = price;
        this.subTotal = subTotal;
    }

    public void setBillId(String id){
        this.billId = id;
    }

    public String getBillId(){
        return this.billId;
    }

    public int getQuanity() {
        return this.quanity;
    }

    public void setQuanity(int quanity) {
        this.quanity = quanity;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getSubTotal() {
        return this.subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }
    
    public String setBillId() {
        String id;
          do {
               System.out.print("set bill id : ");
               id = sc.nextLine().trim();
               if (Validate.validateID(id)) {
                    System.out.println("error id !");
                    id = "";
               }
          } while (id.isEmpty());
          return id;
     }

    public void nhap(){
        quanity = setQuanity();
        price = setPrice();
        billId = setBillId();
    }

    }

    @Override
    public String toString() {
        return "{" + "billId='" + getBillId() + 
            " quanity='" + getQuanity() + "'" +
            ", price='" + getPrice() + "'" +
            ", subTotal='" + getSubTotal() + "'" +
            "}";
    }
    
}
