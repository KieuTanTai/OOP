package BUS;

import DTO.BillDetails;
import util.Validate;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Scanner;

public class BillDetailsBus {
    private BillDetails[] ds;
    private int n;
    Scanner sc = new Scanner(System.in);

    public BillDetailsBus(){
        this.n = 0;
        ds = new BillDetails[0];
    }

    public BillDetailsBus(BillDetails[] ds, int n){
        this.n = n;
        this.ds = ds;
    }

    public BillDetailsBus(BillDetailsBus list){
        ds = list.ds;
        n = list.n;
    }

    public BillDetails[] getds(){
        return ds;
    }

    public void setds(BillDetails[] ds){
        this.ds = ds;
    }

    public int getn(){
        return this.n;
    }

    public void setn(int n){
        this.n = n;
    }

    public void nhap(){
        System.out.println("please insert how many bill details: ");
        n = sc.nextInt();
        ds = new BillDetails[n];
        for(int i = 0; i < n; ++i){
            add();
        }
    }

    public void xuat(){
        for(int i = 0; i < n; ++i){
            System.out.println(ds[i].toString());
        }
    }

    public void createBillDetailsList() {
        System.out.println("Insert the number of bill details: ");
        int numberOfDetails = sc.nextInt();
        sc.nextLine(); 
    
        ds = Arrays.copyOf(ds, n + numberOfDetails); 
    
        for (int i = 0; i < numberOfDetails; i++) {
            System.out.println("Input details for Bill Detail #" + (i + 1));
            BillDetails detail = new BillDetails();
            detail.setInfo();  
            ds[n] = detail;
            n++;
        }
    
        System.out.println("The Bill Details for this bill are: ");
        xuat();  
    }

    public void add(BillDetails bdObject){
        ds = Arrays.copyOf(ds, ds.length + 1);
        ds[n] = (BillDetails) bdObject;
        ++n;
    }

    public void add(){
        BillDetails ds = new BillDetails();
        ds.setInfo();
        add(ds);
    }

    public void remove (String id){
        int index = -1;
        for(int i = 0; i < ds.length; ++i){
            if(ds[i].getBillId().equals(id)){
                index = i;
                break;
            }
        }
        if(index != -1){
            for(int i = index; i < ds.length - 1; ++i){
                ds[i] = ds[i + 1];
            }
            ds = Arrays.copyOf(ds, ds.length-1);
            n--;
        }else{
            System.out.println("not found!!!");
        }
    }

    public void remove(){
        System.out.println("insert bill id you want to remove: ");
        String bd = sc.nextLine();
        remove(bd);
    }

    public void findBillId (String id){
        boolean flag = false;
        for(int i = 0; i < ds.length; ++i){
            if((ds[i].getBillId().equals(id))){
                flag = true;
            }
        }
        if(flag == false){
            System.out.println("not found");
        }
    }

    public void findQuantity(int q){
        boolean flag = false;
        for(int i = 0; i < ds.length; ++i){
            if(ds[i].getQuantity() == q){
                flag = true;
            }
        }
        if(flag == false){
            System.out.println("not found");
        }
    }

    public void findPrice(BigDecimal price){
        boolean flag = false;
        for(int i = 0; i < ds.length; ++i){
            if(ds[i].getPrice().equals(price)){
                flag = true;
            }
        }
        if(flag == false){
            System.out.println("not found");
        }
    }

    public void findSubTotal(BigDecimal sub){
        boolean flag = false;
        for(int i = 0; i < ds.length; ++i){
            if(ds[i].getSubTotal().equals(sub)){
                flag = true;
            }
        }
        if(flag == false){
            System.out.println("not found");
        }
    }

    public void find(){
        System.out.println("1. search bill id");
        System.out.println("2. search quantity");
        System.out.println("3. search price");
        System.out.println("4. search subtotal");
        System.out.println("insert:");
        int m = sc.nextInt();
        sc.nextLine();

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
                findBillId(bid);
                break;
            case 2:
            int quantity;
            String quantityInput;
            do {
                 System.out.print("set quantity : ");
                 quantityInput = sc.nextLine().trim();
                 quantity = Validate.isNumber(quantityInput);
            } while (quantityInput.isEmpty());
                findQuantity(quantity);
                break;
            case 3:
            BigDecimal price;
            do {
                 System.out.print("set price : ");
                 String value = sc.nextLine();
                 price = Validate.isBigDecimal(value);
            } while (price == null);
                findPrice(price);        
                break;
            case 4:
            BigDecimal sub;
            do {
                 System.out.print("set sub : ");
                 String value = sc.nextLine();
                 sub = Validate.isBigDecimal(value);
            } while (sub == null);
                findSubTotal(sub);        
                break;
            default:
                break;
        }
    }

    public void edit(){
        System.out.println("insert bill's id you want to edit: ");
        String fixBill = sc.nextLine();
        boolean flag = false;
        for(int i = 0; i < n; ++i){
            if(ds[i].getBillId().equals(fixBill)){
                flag = true;
                System.out.println("1. quantity");
                System.out.println("2. price");
                System.out.println("3. subTotal");
                System.out.println("insert index: ");
                int x = sc.nextInt();
                sc.nextLine();
                switch (x) {
                    case 1:
                    int quantity;
                    String quantityInput;
                    do {
                        System.out.print("set quantity : ");
                        quantityInput = sc.nextLine().trim();
                        quantity = Validate.isNumber(quantityInput);
                    } while (quantityInput.isEmpty());
                        ds[i].setQuantity(quantity);
                        ds[i].calcSubTotal();
                        break;
                    case 2:
                    BigDecimal price;
                    do {
                         System.out.print("set price : ");
                         String value = sc.nextLine();
                         price = Validate.isBigDecimal(value);
                    } while (price == null);
                        ds[i].setPrice(price);
                        ds[i].calcSubTotal();
                        break;
                    case 3:
                    BigDecimal subTotal;
                    do {
                         System.out.print("set subTotal : ");
                         String value = sc.nextLine();
                         subTotal = Validate.isBigDecimal(value);
                    } while (subTotal == null);
                        ds[i].setSubTotal(subTotal);
                        break;
                    default:
                        break;
                }
            }
        }
        if(flag == false){
            System.out.println("bill id doesn't exist");
        }
    }

}