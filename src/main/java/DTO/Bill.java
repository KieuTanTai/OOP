package DTO;

import java.math.BigDecimal;
import java.util.Scanner;

import BUS.BillDetailsBus;
import BUS.BillBus;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import util.Validate;
// import DTO.BillDetails;

public class Bill {
    private String billId;
    private String employeeId;
    private String customerId;
    private SaleEvents saleCode;
    private BigDecimal discount;
    private BigDecimal totalPrice;
    private LocalDate date = LocalDate.now() ;
    private BillDetailsBus detailsBus;
    
    Scanner sc = new Scanner(System.in);

    public Bill() {
        detailsBus = new BillDetailsBus();
    }
 
    public Bill(String billId, String employeeId, String customerId, SaleEvents saleCode, BigDecimal discount, BigDecimal totalPrice, LocalDate date) {
        this.billId = billId;
        this.employeeId = employeeId;
        this.customerId = customerId;
        this.saleCode = saleCode;
        this.discount = discount;
        this.totalPrice = totalPrice;
        this.date = date;
    }

    public String setBillId() {
        String id = "";
        try {
                BillBus billList = new BillBus();
                billList.readFile();
                Bill[] list = billList.getds();
    
                if (list.length == 0) {
                    return "00000001";
                } else {
                    String getID = list[list.length - 1].getBillId();
                    int prevID = Integer.parseInt(getID.substring(2, getID.length() - 2));
                    id = String.format("%d", prevID + 1);
                    while (id.length() != 8)
                        id = "0" + id;
                }
        } catch (Exception e) {
            System.out.println("error when execute with file!" + e.getMessage());
            id = "";
        }
        return billIdModifier(id);
    }

    protected String billIdModifier(String billId) {
        if (billId.startsWith("Bi") && billId.endsWith("LL") && billId.length() == 12)
            return billId;
        if (!Validate.validateID(billId)) {
            System.out.println("error id!");
            return "N/A";
        }
        return "Bi" + billId + "LL";
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

    public BigDecimal setDiscount() {
        BigDecimal discount;
        do {
             System.out.print("set discount : ");
             String value = sc.nextLine();
             discount = Validate.isBigDecimal(value);
        } while (discount == null);
        return discount;
    }

//     public LocalDate setDate() {
//         LocalDate date;
//           do {
//                System.out.print("set date : ");
//                String dateInput = sc.nextLine().trim();
//                date = Validate.isCorrectDate(dateInput);
//           } while (date == null);
//           return date;
//    }
   
    public void nhap(){
        billId = setBillId();
        employeeId = setEmployeeId();
        customerId = setCustomerId();
        saleCode.nhap(); ;
        discount = setDiscount();
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

    public void setSaleCode(SaleEvents saleCode){
        this.saleCode = saleCode;
    }

    public SaleEvents getSaleCode(){
        return this.saleCode;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public BigDecimal getDiscount() {
        return this.discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getTotalPrice() {
        BigDecimal total = BigDecimal.ZERO;
        for (BillDetails detail : detailsBus.getds()) {
            total = total.add(detail.calcSubTotal());
        }
        return total;
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

    public BillDetailsBus getDetailsBus() {
        return detailsBus;
    }

    public void setDetailsBus(BillDetailsBus detailsBus) {
        this.detailsBus = detailsBus;
    }

    @Override
    public String toString() {
        return "{" +
            " billId='" + getBillId() + "'" +
            ", employeeId='" + getEmployeeId() + "'" +
            ", customerId='" + getCustomerId() +  "'" +
            ", discount='" + getDiscount() + "'" +
            ", totalPrice='" + getTotalPrice() + "'" +
            ", date='" + getDate() + "'" + "}";
    }
    
}


