package DTO;

import java.math.BigDecimal;
import java.util.Scanner;

import util.Validate;

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

    public SaleEventsDetail(String saleEvId, String promoCode, BigDecimal minPrice, BigDecimal discount,
            BigDecimal maxPriceDiscount) {
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

    // set no params
    public BigDecimal setPrice() {
        BigDecimal price;
        do {
            System.out.print("set price (VND) : ");
            String value = sc.nextLine();
            price = Validate.isBigDecimal(value);
        } while (price == null);
        return price;
    }

    public void setInfo() {
        System.out.println("-".repeat(60));
        System.out.println("Enter promo code:");
        String promoCode = sc.nextLine();

        System.out.println("-".repeat(60));
        System.out.println("Min price!");
        BigDecimal minPrice = setPrice();

        System.out.println("-".repeat(60));
        System.out.println("Discount!");
        BigDecimal discount = setPrice().divide(new BigDecimal(100));

        System.out.println("-".repeat(60));
        System.out.println("Max price discount!");
        BigDecimal maxPriceDiscount = setPrice();
        System.out.println("+".repeat(60));

        int userChoice;
        System.out.printf("| %s %s %s |\n", "I.Cancel", "-".repeat(20), "II.Submit");
        do {
            System.out.print("choose option (1 or 2) : ");
            String option = sc.nextLine().trim();
            userChoice = Validate.parseChooseHandler(option, 2);
        } while (userChoice == -1);
        System.out.println("+".repeat(60));
        if (userChoice == 1)
            System.out.println("ok!");
        else {
            this.promoCode = promoCode;
            this.minPrice = minPrice;
            this.discount = discount;
            this.maxPriceDiscount = maxPriceDiscount;
        }
    }

    public void showInfo() {
        System.out.println("-".repeat(140));
        System.out.printf("| %-22s : %s \n", "Promo Code", this.promoCode != null ? this.promoCode : "N/A");
        System.out.printf("| %-22s : %s \n", "Min Price",
                this.minPrice != null ? Validate.formatPrice(this.minPrice) : "N/A");
        System.out.printf("| %-22s : %s \n", "Discount", this.discount != null ? this.discount.multiply(new BigDecimal(100)) + "%" : "N/A");
        System.out.printf("| %-22s : %s \n", "Max Price Discount",
                this.maxPriceDiscount != null ? Validate.formatPrice(this.maxPriceDiscount) : "N/A");
        System.out.println("-".repeat(140));
    }
}
