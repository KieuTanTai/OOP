package DTO;

import java.math.BigDecimal;
import java.time.LocalDate;

import util.Validate;

public class BillDetails extends Bill{
    private int quanity;
    private BigDecimal price;
    private BigDecimal subTotal;

    public BillDetails() {
    }

    public BillDetails(String billId, String employeeId, String customerId, String promoCode, BigDecimal discount, BigDecimal totalPrice, LocalDate date, int quanity, BigDecimal price, BigDecimal subTotal) {
        super(billId, employeeId, customerId, promoCode, discount, totalPrice, date);
        this.quanity = quanity;
        this.price = price;
        this.subTotal = subTotal;
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

    public int setQuanity() {
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

    // public void setSubTotal() {
    //     this.subTotal = subTotal;
    // }

    public void nhap(){
        super.nhap();
        quanity = setQuanity();
        price = setPrice();
    }

    public BigDecimal calcSubTotal(BigDecimal anotherPrice){
        this.subTotal = this.subTotal.add(anotherPrice);
        return this.subTotal;
    }

    @Override
    public String toString() {
        return super.toString() + "," +
            " quanity='" + getQuanity() + "'" +
            ", price='" + getPrice() + "'" +
            ", subTotal='" + getSubTotal() + "'" +
            "}";
    }
    
}
