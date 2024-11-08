package DTO;

import java.math.BigDecimal;

public class SaleEventsDetail {
    private String saleEvId;
    private String promoCode;
    private BigDecimal minPrice;
    private BigDecimal discount;
    private BigDecimal maxPriceDiscount;

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
