package DTO;

import java.math.BigDecimal;
import java.sql.SQLOutput;
import java.util.Scanner;

public class SaleEventsDetail {
    private String saleEvId;
    private String promoCode;
    private BigDecimal minPrice;
    private BigDecimal discount;
    private BigDecimal maxPriceDiscount;
    Scanner sc = new Scanner(System.in);

    // constructor
    public SaleEventsDetail() {
    }

    public SaleEventsDetail(String saleEvId, String promoCode, BigDecimal minPrice, BigDecimal discount, BigDecimal maxPriceDiscount) {
        this.saleEvId = saleEvId;
        this.promoCode = promoCode;
        this.minPrice = minPrice;
        this.discount = discount;
        this.maxPriceDiscount = maxPriceDiscount;
    }

    public void nhap(){
        System.out.println("Nhập promo code:");
        this.promoCode = sc.nextLine();

        System.out.println("Nhập min price:");
        this.minPrice = sc.nextBigDecimal();

        System.out.println("Nhập discount:");
        this.discount = sc.nextBigDecimal();

        System.out.println("Nhập max price discount:");
        this.maxPriceDiscount = sc.nextBigDecimal();
    }

    public void xuat(){
        System.out.println("promo code là:" + this.promoCode);
        System.out.println("min price là:" + this.minPrice);
        System.out.println("discount là:" + this.discount);
        System.out.println("max price discount là:" + this.maxPriceDiscount);
    }


    // getter
    public String getSaleEvId() {
        return saleEvId;
    }

    public String getPromoCode() {
        return promoCode;
    }

    public BigDecimal getMinPrice() {
        return minPrice;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public BigDecimal getMaxPriceDiscount() {
        return maxPriceDiscount;
    }

    // setter
    public void setSaleEvId(String saleEvId) {
        this.saleEvId = saleEvId;
    }

    public void setPromoCode(String promoCode) {
        this.promoCode = promoCode;
    }

    public void setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public void setMaxPriceDiscount(BigDecimal maxPriceDiscount) {
        this.maxPriceDiscount = maxPriceDiscount;
    }




}
