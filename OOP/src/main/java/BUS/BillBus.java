package BUS;

import java.util.Scanner;
import java.math.BigDecimal;
import java.util.Arrays;
import java.time.LocalDate;

import DTO.*;
import util.Validate;


public class BillBus{
    Scanner sc = new Scanner(System.in);
    private Bill[] ds;
    private int n;

    public BillBus(){
        n = 0;
        ds = new Bill[0];
    }

    public BillBus(Bill[] ds, int n){
        this.ds = ds;
        this.n = n;
    }

    public BillBus(BillBus list){
        ds = list.ds;
        n = list.n;
    }

    public Bill[] getds(){
        return ds;
    }

    public int getn(){
        return n;
    }

    public void setds(Bill[] ds){
        this.ds = ds;
    }

    public void setn(int n){
        this.n = n;
    }

    public void nhap(){
        System.out.println("Vui long nhap so luong bill");
        n = sc.nextInt();
        ds = new Bill[n];
        for(int i = 0; i < n; ++i){
            them();
        }
    }

    public void xuat(){
        for(int i = 0; i < n; ++i){
            System.out.println(ds[i].toString());
            
        }
    }

    public void them(Bill bill){
        ds = Arrays.copyOf(ds, ds.length +1);
        ds[n] = bill;
        ++n;
    }

    public void them(){
        Bill ds = new Bill();
        ds.nhap();
        them(ds);
    }

    public void suaTheoCustomerId(String bd, String newCId){
        for(int i = 0; i < n; ++i){
            if(ds[i].getBillId().equals(bd)){
                ds[i].setCustomerId(newCId);
            }
        }
    }

    public void suaTheoEmployeeId(String bd, String newEId){
        for(int i = 0; i < n; ++i){
            if(ds[i].getBillId().equals(bd)){
                ds[i].setEmployeeId(newEId);
            }
        }
    }

    public void suaTheoDate(String bd,LocalDate newDate){
        for(int i = 0; i < n; ++i){
            if(ds[i].getBillId().equals(bd)){
                ds[i].setDate(newDate);
            }
        }
    }

    public void suaTheoDiscount(String bd, BigDecimal newDiscount){
        for(int i = 0; i < n; ++i){
            if(ds[i].getBillId().equals(bd)){
                ds[i].setDiscount(newDiscount);
            }
        }
    }

    public void suaTheoPromoCode(String bd, String newPromo){
        for(int i = 0; i < n; ++i){
            if(ds[i].getBillId().equals(bd)){
                ds[i].setPromoCode(newPromo);
            }
        }
    }

    public void suaTheoTotalPrice(String bd, BigDecimal newTotalPrice){
        for(int i = 0; i < n; ++i){
            if(ds[i].getBillId().equals(bd)){
                ds[i].setTotalPrice(newTotalPrice);
            }
        }
    }

    public void suaTheoQuanity(String bd, int newQuanity){
        for(int i = 0; i < n; ++i){
            if(ds[i].getBillId().equals(bd)){
                ds[i].setQuanity(newQuanity);
            }
        }
    }

    public void suaTheoPrice(String bd, BigDecimal newPrice){
        for(int i = 0; i < n; ++i){
            if(ds[i].getBillId().equals(bd)){
                ds[i].setPrice(newPrice);
            }
        }
    }

    public void suaTheoSubTotal(String bd, BigDecimal newSubTotal){
        for(int i = 0; i < n; ++i){
            if(ds[i].getBillId().equals(bd)){
                ds[i].setSubTotal(newSubTotal);
            }
        }
    }

    public void sua(){
        System.out.println("insert bill's id you want to edit: ");
        String fixBill = sc.nextLine();
        boolean flag = false;
        for(int i = 0; i < n; ++i){
            if(ds[i].getBillId().equals(fixBill)){
                flag = true;
                System.out.println("1. employeed id");
                System.out.println("2. customer id");
                System.out.println("3. date");
                System.out.println("4. discount");
                System.out.println("5. promo code");
                System.out.println("6. total price");
                System.out.println("7. price");
                System.out.println("8. quanity");
                System.out.println("9. sub total");
                System.out.println("0. thoat");
                System.out.println("Chon thong tin can sua: ");
                int x = sc.nextInt();
                sc.nextLine();
                switch (x) {
                    case 1:
                        String eid;
                    do {
                        System.out.print("set employee id : ");
                        eid = sc.nextLine().trim();
                        if (Validate.validateID(eid)) {
                        System.out.println("error id !");
                        eid = "";
                            }
                        } while (eid.isEmpty());
                        ds[i].setEmployeeId(eid);
                        break;
                    case 2:
                        String cid;
                    do {
                        System.out.print("set customer id : ");
                        cid = sc.nextLine().trim();
                        if (Validate.validateID(cid)) {
                              System.out.println("error id !");
                              cid = "";
                         }
                    } while (cid.isEmpty());  
                        ds[i].setCustomerId(cid);                  
                        break;
                    case 3:
                        LocalDate date;
                    do {
                         System.out.print("set date : ");
                         String dateInput = sc.nextLine().trim();
                         date = Validate.isCorrectDate(dateInput);
                    } while (date == null);
                        ds[i].setDate(date);
                        break;
                    case 4:
                        BigDecimal discount;
                    do {
                         System.out.print("set discount : ");
                         String value = sc.nextLine();
                         discount = Validate.isBigDecimal(value);
                    } while (discount == null);
                        ds[i].setDiscount(discount);
                        break;
                    case 5:
                        String code;
                    do {
                         System.out.print("set promo code : ");
                         code = sc.nextLine().trim();
                         if (Validate.validateID(code)) {
                              System.out.println("error code !");
                              code = "";
                         }
                    } while (code.isEmpty());
                        ds[i].setPromoCode(code);
                        break;
                    case 6:
                        System.out.println("nhap total price muon sua");
                        BigDecimal newTotalPrice = sc.nextBigDecimal();
                        sc.nextLine();
                        ds[i].setTotalPrice(newTotalPrice);
                        break;
                    case 7:
                        BigDecimal price;
                    do {
                         System.out.print("set price : ");
                         String value = sc.nextLine();
                         price = Validate.isBigDecimal(value);
                    } while (price == null);
                        ds[i].setPrice(price);
                        break;
                    case 8:
                        int quantity;
                    do {
                         System.out.print("set quantity: ");
                         String quantityInput = sc.nextLine().trim();
                         quantity = Validate.isNumber(quantityInput);
                    } while (quantity == -1);
                        ds[i].setQuanity(quantity);
                        break;
                    case 9:
                        System.out.println("nhap subtotal muon sua");
                        BigDecimal newSubTotal = sc.nextBigDecimal();
                        sc.nextLine();
                        ds[i].setSubTotal(newSubTotal);
                        break;    
                    default:
                        break;
                }
            }
        }
        if(flag == false){
            System.out.println("bill id khong ton tai");
        }
    }

    public void xoa(String bd){
        int index = -1;
        for(int i = 0; i < n; ++i){
            if(ds[i].getBillId().equals(bd)){
                index = i;
                break;
            }
        }
        if(index != -1){
            for(int i = index; i < n - 1; ++i){
                ds[i] = ds[i + 1];
            }
            ds = Arrays.copyOf(ds, ds.length-1);
            n--;
        }else{
            System.out.println("khong tim thay");
        }
    }

    public void xoa(){
        System.out.println("nhap bill id muon xoa");
        String bd = sc.nextLine();
        xoa(bd);
    }

    public void timKiemTheoBillId(String newbd){
        boolean flag = false;
        for(int i = 0; i < n; ++i){
            if(ds[i].getBillId().equals(newbd)){
                ds[i].toString();
                flag = true;
            }
        }
        if(flag == false){
            System.out.println("khong tim thay");
        }
    }

    public void timKiemTheoEmployeeId(String neweid){
        boolean flag = false;
        for(int i = 0; i < n; ++i){
            if(ds[i].getEmployeeId().equals(neweid)){
                ds[i].toString();
                flag = true;
            }
        }
        if(flag == false){
            System.out.println("khong tim thay");
        }
    }

    public void timKiemTheoCustomerId(String newcid){
        boolean flag = false;
        for(int i = 0; i < n; ++i){
            if(ds[i].getCustomerId().equals(newcid)){
                ds[i].toString();
                flag = true;
            }
        }
        if(flag == false){
            System.out.println("khong tim thay");
        }
    }

    @SuppressWarnings("unlikely-arg-type")
    public void timKiemTheoDate(LocalDate date){
        boolean flag = false;
        for(int i = 0; i < n; ++i){
            if(ds[i].getDate().equals(date)){
                ds[i].toString();
                flag = true;
            }
        }
        if(flag == false){
            System.out.println("khong tim thay");
        }
    }

    public void timKiemTheoDiscount(BigDecimal newDiscount){
        boolean flag = false;
        for(int i = 0; i < n; ++i){
            if(ds[i].getDiscount().equals(newDiscount)){
                ds[i].toString();
                flag = true;
            }
        }
        if(flag == false){
            System.out.println("khong tim thay");
        }
    }

    public void timKiemTheoPromoCode(String newpromo){
        boolean flag = false;
        for(int i = 0; i < n; ++i){
            if(ds[i].getPromoCode().equals(newpromo)){
                ds[i].toString();
                flag = true;
            }
        }
        if(flag == false){
            System.out.println("khong tim thay");
        }
    }

    public void timKiemTheoTotalPrice(BigDecimal newtotal){
        boolean flag = false;
        for(int i = 0; i < n; ++i){
            if(ds[i].getTotalPrice().equals(newtotal)){
                ds[i].toString();
                flag = true;
            }
        }
        if(flag == false){
            System.out.println("khong tim thay");
        }
    }

    public void timKiemTheoQuanity(int newquanity){
        boolean flag = false;
        for(int i = 0; i < n; ++i){
            if(ds[i].getQuanity() == newquanity){
                ds[i].toString();
                flag = true;
            }
        }
        if(flag == false){
            System.out.println("khong tim thay");
        }
    }

    public void timKiemTheoPrice(BigDecimal newprice){
        boolean flag = false;
        for(int i = 0; i < n; ++i){
            if(ds[i].getPrice().equals(newprice)){
                ds[i].toString();
                flag = true;
            }
        }
        if(flag == false){
            System.out.println("khong tim thay");
        }
    }

    public void timKiemTheoSubTotal(BigDecimal newsubtotal){
        boolean flag = false;
        for(int i = 0; i < n; ++i){
            if(ds[i].getSubTotal().equals(newsubtotal)){
                ds[i].toString();
                flag = true;
            }
        }
        if(flag == false){
            System.out.println("khong tim thay");
        }
    }

    public void timKiem(){
        System.out.println("1. search bill id");
        System.out.println("2. search employee id");
        System.out.println("3. search customer id");
        System.out.println("4. search date");
        System.out.println("5. search discount");
        System.out.println("6. search promo code");
        System.out.println("7. search total price");
        System.out.println("8. search quanity");
        System.out.println("9. search price");
        System.out.println("10. search sub total");
        System.out.println("Nhap lua chon");
        int m = sc.nextInt();
        sc.nextInt();

        switch (m) {
            case 1:
                String bid;
            do {
                 System.out.print("insert bill id : ");
                 bid = sc.nextLine().trim();
                 if (Validate.validateID(bid)) {
                      System.out.println("error id !");
                      bid = "";
                 }
            } while (bid.isEmpty());
                timKiemTheoBillId(bid);
                break;
            case 2:
                String eid;
            do {
                 System.out.print("set employee id : ");
                 eid = sc.nextLine().trim();
                 if (Validate.validateID(eid)) {
                      System.out.println("error id !");
                      eid = "";
                 }
            } while (eid.isEmpty());
                timKiemTheoEmployeeId(eid);
                break;
            case 3:
                String cid;
            do {
                 System.out.print("set customer id : ");
                 cid = sc.nextLine().trim();
                 if (Validate.validateID(cid)) {
                      System.out.println("error id !");
                      cid = "";
                 }
            } while (cid.isEmpty());
                timKiemTheoCustomerId(cid);       
                break;
            case 4:
            LocalDate date;
            do {
                 System.out.print("set date : ");
                 String dateInput = sc.nextLine().trim();
                 date = Validate.isCorrectDate(dateInput);
            } while (date == null);
                timKiemTheoDate(date);   
                break;
            case 5:
                BigDecimal discount;
            do {
                 System.out.print("set discount : ");
                 String value = sc.nextLine();
                 discount = Validate.isBigDecimal(value);
            } while (discount == null);
                timKiemTheoDiscount(discount);        
                break;
            case 6:
                String code;
            do {
                 System.out.print("set promo code : ");
                 code = sc.nextLine().trim();
                 if (Validate.validateID(code)) {
                      System.out.println("error code !");
                      code = "";
                 }
            } while (code.isEmpty());
                timKiemTheoPromoCode(code);        
                break;
            case 7:
                System.out.println("insert total price you want to search");
                BigDecimal newTotalPrice = sc.nextBigDecimal();
                sc.nextLine();
                timKiemTheoTotalPrice(newTotalPrice);        
                break;
            case 8:
                int quantity;
            do {
                 System.out.print("set quantity: ");
                 String quantityInput = sc.nextLine().trim();
                 quantity = Validate.isNumber(quantityInput);
            } while (quantity == -1);
                timKiemTheoQuanity(quantity);        
                break;
            case 9:
                BigDecimal price;
            do {
                 System.out.print("set price : ");
                 String value = sc.nextLine();
                 price = Validate.isBigDecimal(value);
            } while (price == null);
                timKiemTheoPrice(price);        
                break;
            case 10:
                System.out.println("insert sub total you want to search");
                BigDecimal newSubTotal = sc.nextBigDecimal();
                sc.nextLine();
                timKiemTheoSubTotal(newSubTotal);        
                break;
        
            default:
                break;
        }
    }
}
