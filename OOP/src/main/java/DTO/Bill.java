package DTO;

import java.math.BigDecimal;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import util.Validate;
import DTO.BillDetails;

public class Bill {
    private String billId;
    private String employeeId;
    private String customerId;
    private String promoCode;
    private BigDecimal discount;
    private BigDecimal totalPrice;
    private LocalDate date;
    private BillDetails[] ds;
    private int n;
    
    Scanner sc = new Scanner(System.in);

    public Bill() {
    }
 
    public Bill(String billId, String employeeId, String customerId, String promoCode, BigDecimal discount, BigDecimal totalPrice, LocalDate date, BillDetails[] ds) {
        this.billId = billId;
        this.employeeId = employeeId;
        this.customerId = customerId;
        this.promoCode = promoCode;
        this.discount = discount;
        this.totalPrice = totalPrice;
        this.date = date;
        this.ds = ds;
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

    public String setEmployeeId() {
        String id;
          do {
               System.out.print("set employee id : ");
               id = sc.nextLine().trim();
               if (Validate.validateID(id)) {
                    System.out.println("error id !");
                    id = "";
               }
          } while (id.isEmpty());
          return id;
    }

    public String setCustomerId() {
        String id;
          do {
               System.out.print("set customer id : ");
               id = sc.nextLine().trim();
               if (Validate.validateID(id)) {
                    System.out.println("error id !");
                    id = "";
               }
          } while (id.isEmpty());
          return id;
    }

    public String setPromoCode() {
        String code;
          do {
               System.out.print("set promo code : ");
               code = sc.nextLine().trim();
               if (Validate.validateID(code)) {
                    System.out.println("error code !");
                    code = "";
               }
          } while (code.isEmpty());
          return code;
    }

    public BigDecimal setDiscount() {
        BigDecimal discount;
        do {
             System.out.print("set discount : ");
             String value = sc.nextLine();
             discount = Validate.isBigDecimal(value);
        } while (discount == null);
        return discount;
    }

    public LocalDate setDate() {
        LocalDate date;
          do {
               System.out.print("set date : ");
               String dateInput = sc.nextLine().trim();
               date = Validate.isCorrectDate(dateInput);
          } while (date == null);
          return date;
   }

   public void setSoLuongBill(){
        System.out.println("insert bill quanities");
        n = sc.nextInt();
        ds = new BillDetails[n];
        for(int i = 0;i < n; ++i){
            setBillDetails();
        }
   }

    public void setBillDetails(BillDetails bill){
        ds = Arrays.copyOf(ds, ds.length + 1);
        ds[n] = bill;
        ++n;
    }

    public void setBillDetails(){
        BillDetails ds = new BillDetails();
        ds.setSoLuongBill();
        setBillDetails(ds);
    }


    public void nhapBill(){
        billId = setBillId();
        employeeId = setEmployeeId();
        customerId = setCustomerId();
        promoCode = setPromoCode();
        discount = setDiscount();
        date = setDate();
        ds.setBillDetails();
    }

    public String getBillId() {
        return this.billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public String getEmployeeId() {
        return this.employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getCustomerId() {
        return this.customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getPromoCode() {
        return this.promoCode;
    }

    public void setPromoCode(String promoCode) {
        this.promoCode = promoCode;
    }

    public BigDecimal getDiscount() {
        return this.discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getTotalPrice() {
        return this.totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return this.date.format(formatter);
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
    
    public BillDetails[] getDs() {
        return this.ds;
    }

    public void setDs(BillDetails[] ds) {
        this.ds = ds;
    }

    public int getN(){
        return this.n;
    }

    public void setN(int n){
        this.n = n;
    }
    
    @Override
    public String toString() {
        return "{" +
            " billId='" + getBillId() + "'" +
            ", employeeId='" + getEmployeeId() + "'" +
            ", customerId='" + getCustomerId() + "'" +
            ", promoCode='" + getPromoCode() + "'" +
            ", discount='" + getDiscount() + "'" +
            ", totalPrice='" + getTotalPrice() + "'" +
            ", date='" + getDate() + "'" +
            ", ds='" + getDs() + "'" +
            ", n='" + getN() + "'" + "}";
    }
    
}

