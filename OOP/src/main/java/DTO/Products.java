package DTO;

import util.Validate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;

public abstract class Products {
    private String productID;
    private String productName;
    private LocalDate releaseDate;
    private BigDecimal productPrice;
    private int quantity;
    protected static final Scanner input = new Scanner(System.in);

    // constructors
    public Products() {
    }

    public Products(String productID, String productName, LocalDate releaseDate, BigDecimal productPrice, int quantity) {
        this.productID = productIDModifier(productID);
        this.productName = productName;
        this.releaseDate = releaseDate;
        this.productPrice = productPrice;
        this.quantity = quantity;
    }

    // getter / setter
    public String getProductID() {
        return this.productID;
    }

    public String getProductName() {
        return this.productName;
    }

    public LocalDate getReleaseDate() {
        return this.releaseDate;
    }

    public BigDecimal getProductPrice() {
        return this.productPrice;
    }

    public int getQuantity() {
        return this.quantity;
    }

    // set have param
    public void setProductID(String productID) {
        this.productID = productIDModifier(productID);
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // set not param
    public String setID() {
        String id;
        do {
            System.out.print("set id : ");
            id = input.nextLine().trim();
            if (!Validate.validateID(id)) {
                System.out.println("error id!");
                id = "";
            }
        } while (id.isEmpty());
        return productIDModifier(id);
    }

    public String setName() {
        String name;
        do {
            System.out.print("set name : ");
            name = input.nextLine().trim();
            if(!Validate.checkName(name)) {
                System.out.println("name is wrong format!");
                name = "";
            }
        } while(name.isEmpty());
        return name;
    }

    public LocalDate setRelDate() {
        LocalDate date;
        do {
            System.out.print("set release date (dd-mm-yyyy) : ");
            String dateInput = input.nextLine().trim();
            date = Validate.isCorrectDate(dateInput);
        } while (date == null);
        return date;
    }

    public BigDecimal setPrice() {
        BigDecimal price;
        do {
            System.out.print("set price (VND) : ");
            String value = input.nextLine();
            price = Validate.isBigDecimal(value);
        } while (price == null);
        return price;
    }

    public int setQuantity() {
        int quantity;
        do {
            System.out.print("set quantity : ");
            String quantityInput = input.nextLine().trim();
            quantity = Validate.isNumber(quantityInput);
        } while (quantity == -1);
        return quantity;
    }

    // other methods

    public abstract void setInfo();

    public abstract void showInfo();

    protected abstract String productIDModifier(String productID);
}
