package DTO;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;
import util.Validate;

public class BillDetails{
    private String billId;
    private int quantity;
    private BigDecimal price;
    private BigDecimal subTotal;
    Scanner sc = new Scanner(System.in);

    public BillDetails() {
    }

    public BillDetails(String billId, int quantity, BigDecimal price, BigDecimal subTotal) {
        this.billId = billId;
        this.quantity = quantity;
        this.price = price;
        this.subTotal = subTotal;
    }

    public void setBillId(String id){
        this.billId = id;
    }

    public String getBillId(){
        return this.billId;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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
        quantity = setquantity();
        price = setPrice();
        billId = setBillId();
    }

    }

    @Override
    public String toString() {
        return "{" + "billId='" + getBillId() + 
            " quantity='" + getquantity() + "'" +
            ", price='" + getPrice() + "'" +
            ", subTotal='" + getSubTotal() + "'" +
            "}";
    }
    
}
