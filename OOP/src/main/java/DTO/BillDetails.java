package DTO;

import java.math.BigDecimal;
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

    public int setQuantity() {
        int quantity;
        do {
             System.out.print("set quantity: ");
             String quantityInput = sc.nextLine().trim();
             quantity = Validate.isNumber(quantityInput);
        } while (quantity == -1);
        return quantity;
    }

    public BigDecimal setPrice() {
        BigDecimal price;
          do {
               System.out.print("set price : ");
               String value = sc.nextLine();
               price = Validate.isBigDecimal(value);
          } while (price == null);
          return price;
    }

    public void nhap(){
        quantity = setQuantity();
        price = setPrice();
        billId = setBillId();
    }

    public BigDecimal calcSubTotal(){
        BigDecimal QuantityDe = BigDecimal.valueOf(quantity);
        this.subTotal = price.multiply(QuantityDe);
        return this.subTotal;
    }

    @Override
    public String toString() {
        return "{" + "billId='" + getBillId() + 
            " quantity='" + getQuantity() + "'" +
            ", price='" + getPrice() + "'" +
            ", subTotal='" + getSubTotal() + "'" + "}";
    }


    
}
