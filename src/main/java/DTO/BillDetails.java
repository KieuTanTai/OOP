package DTO;

import java.math.BigDecimal;
import java.util.Scanner;

public class BillDetails{
    private String billId;
    private int quantity;
    private Products product;
    private BigDecimal price;
    private BigDecimal subTotal;
    Scanner sc = new Scanner(System.in);

    public BillDetails() {
    }

    public BillDetails(String billId, int quantity, Products product, BigDecimal price) {
        this.billId = billId;
        this.quantity = quantity;
        this.product = product;
        this.price = price;
        this.subTotal = price.multiply(new BigDecimal(quantity));
    }

    public Products getProduct() {
        return this.product;
    }

    public String getBillId(){
        return this.billId;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public BigDecimal getSubTotal() {
        return this.subTotal;
    }

    public void setBillId(String id){
        this.billId = id;
    }

    public void setProduct(Products product) {
         this.product = product;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    public void showInfo(){
        System.out.println("bill details from bill id: " + getBillId());
        System.out.println("quantity: " + getQuantity());
        System.out.println("price: " + getPrice());
        System.out.println("subtotal: " +getSubTotal());
    }

    @Override
    public String toString() {
        return "{" + "billId='" + getBillId() + 
            " quantity='" + getQuantity() + "'" +
            ", price='" + getPrice() + "'" +
            ", subTotal='" + getSubTotal() + "'" + "}";
    }


    
}