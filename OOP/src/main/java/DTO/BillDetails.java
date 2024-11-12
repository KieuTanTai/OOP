package DTO;
import java.math.BigDecimal;
import java.time.LocalDate;

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

    public void nhap(){
        super.nhap();
        System.out.println("Vui long nhap quanity: ");
        setQuanity(sc.nextInt());
        System.out.println("Vui long nhap price: ");
        setPrice(sc.nextBigDecimal());
        sc.nextLine();
        System.out.println("Vui long nhap sub total: ");
        setSubTotal(sc.nextBigDecimal());
        sc.nextLine();

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
