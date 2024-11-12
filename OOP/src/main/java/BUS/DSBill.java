package BUS;
import DTO.BillDetails;

import java.util.Scanner;
import java.math.BigDecimal;
import java.util.Arrays;

public class DSBill{
    Scanner sc = new Scanner(System.in);
    private BillDetails[] ds;
    private int n;

    public DSBill(){
        n = 0;
        ds = new BillDetails[0];
    }

    public DSBill(BillDetails[] ds, int n){
        this.ds = ds;
        this.n = n;
    }

    public DSBill(DSBill list){
        ds = list.ds;
        n = list.n;
    }

    public BillDetails[] getds(){
        return ds;
    }

    public int getn(){
        return n;
    }

    public void setds(BillDetails[] ds){
        this.ds = ds;
    }

    public void setn(int n){
        this.n = n;
    }

    public void nhap(){
        System.out.println("Vui long nhap so luong bill");
        n = sc.nextInt();
        ds = new BillDetails[n];
        for(int i = 0; i < n; ++i){
            them();
        }
    }

    public void xuat(){
        for(int i = 0; i < n; ++i){
            System.out.println(ds[i].toString());
        }
    }

    public void them(BillDetails bill){
        ds = Arrays.copyOf(ds, ds.length +1);
        ds[n] = bill;
        ++n;
    }

    public void them(){
        BillDetails ds = new BillDetails();
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

    public void suaTheoDate(String bd, String newDate){
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

    public void sua(){
        System.out.println("nhap bill id muon sua: ");
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
                System.out.println("0. thoat");
                System.out.println("Chon thong tin can sua: ");
                int x = sc.nextInt();
                sc.nextLine();
                switch (x) {
                    case 1:
                        System.out.println("nhap enployee id muon sua:");
                        String newEId = sc.nextLine();
                        ds[i].setEmployeeId(newEId);
                        break;
                    case 2:
                        System.out.println("nhap customer id muon sua");
                        String newCId = sc.nextLine();
                        ds[i].setCustomerId(newCId);                      
                        break;
                    case 3:
                        System.out.println("nhap ngay muon sua theo dinh dang dd-mm-yyyy");
                        String newDate = sc.nextLine();
                        ds[i].setDate(newDate);
                        break;
                    case 4:
                        System.out.println("nhap discount muon sua");
                        BigDecimal newDiscount = sc.nextBigDecimal();
                        sc.nextLine();
                        ds[i].setDiscount(newDiscount);
                        break;
                    case 5:
                        System.out.println("nhap promo code muon sua");
                        String newPromo = sc.nextLine();
                        ds[i].setPromoCode(newPromo);
                        break;
                    case 6:
                        System.out.println("nhap total price muon sua");
                        BigDecimal newTotalPrice = sc.nextBigDecimal();
                        sc.nextLine();
                        ds[i].setTotalPrice(newTotalPrice);
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

    public void timKiemTheoDate(String date){
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

    public void timKiem(){
        System.out.println("1. search bill id");
        System.out.println("2. search employee id");
        System.out.println("3. search customer id");
        System.out.println("4. search date");
        System.out.println("5. search discount");
        System.out.println("6. search promo code");
        System.out.println("7. search total price");
        System.out.println("Nhap lua chon");
        int m = sc.nextInt();
        sc.nextInt();

        switch (m) {
            case 1:
                System.out.println("nhap bill id muon tim");
                String newBd = sc.nextLine();
                timKiemTheoBillId(newBd);
                break;
            case 2:
                System.out.println("nhap employee id muon tim");
                String newEid = sc.nextLine();
                timKiemTheoEmployeeId(newEid);
                break;
            case 3:
                System.out.println("nhap customer id muon tim");
                String newCid = sc.nextLine();
                timKiemTheoCustomerId(newCid);        
                break;
            case 4:
                System.out.println("nhap ngay muon tim");
                String newDate = sc.nextLine();
                timKiemTheoDate(newDate);        
                break;
            case 5:
                System.out.println("nhap discount muon tim");
                BigDecimal newDiscount = sc.nextBigDecimal();
                sc.nextLine();
                timKiemTheoDiscount(newDiscount);        
                break;
            case 6:
                System.out.println("nhap promo code muon tim");
                String newPromo = sc.nextLine();
                timKiemTheoPromoCode(newPromo);        
                break;
            case 7:
                System.out.println("nhap total price muon tim");
                BigDecimal newTotalPrice = sc.nextBigDecimal();
                sc.nextLine();
                timKiemTheoTotalPrice(newTotalPrice);        
                break;
            default:
                break;
        }
    }
}
