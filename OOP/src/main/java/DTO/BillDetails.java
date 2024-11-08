package DTO;

import java.math.BigDecimal;
import java.util.Scanner;

import util.Validate;

public class BillDetails{
    private int quanity;
    private BigDecimal price;
    private BigDecimal subTotal;
    Scanner sc = new Scanner(System.in);

    public BillDetails() {
    }

    public BillDetails(int quanity, BigDecimal price, BigDecimal subTotal) {
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

    public void nhap(){
        quanity = setQuanity();
        price = setPrice();
    }

    public BigDecimal calcSubTotal(){
        BigDecimal quanityDe = BigDecimal.valueOf(quanity);
        this.subTotal = price.multiply(quanityDe);
        return this.subTotal;
    }

    @Override
    public String toString() {
        return "{" +
            " quanity='" + getQuanity() + "'" +
            ", price='" + getPrice() + "'" +
            ", subTotal='" + getSubTotal() + "'" +
            "}";
    }

    public void setSoLuongBill() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setSoLuongBill'");
    }
    
}
