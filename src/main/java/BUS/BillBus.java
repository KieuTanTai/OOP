package BUS;

import java.util.Scanner;
import java.math.BigDecimal;
import java.util.Arrays;
import java.time.LocalDate;
import java.io.*;
import DTO.*;
import util.Validate;


public class BillBus{
    Scanner sc = new Scanner(System.in);
    private Bill[] ds;
    private int n;
    private BillDetailsBus detailsBus;

    public BillBus(){
        n = 0;
        ds = new Bill[0];
        detailsBus = new BillDetailsBus();
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

    public void createBillDetailsList() {
        System.out.println("insert the number of bill details: ");
        int numberOfDetails = sc.nextInt();
        sc.nextLine(); 

        for (int i = 0; i < numberOfDetails; i++) {
            System.out.println("Input details for Bill Detail #" + (i + 1));
            BillDetails detail = new BillDetails();
            detail.nhap();
            detailsBus.add(detail); 
        }

        System.out.println("The Bill Details for this bill are: ");
        detailsBus.xuat(); 
    }

    public void nhap(){
        System.out.println("Vui long nhap so luong bill");
        n = sc.nextInt();
        ds = new Bill[n];
        for(int i = 0; i < n; ++i){
            add();
        }
    }

    public void xuat(){
        for(int i = 0; i < n; ++i){
            System.out.println(ds[i].toString());
        }
    }

    public void add(Bill bill){
        ds = Arrays.copyOf(ds, ds.length +1);
        ds[n] = bill;
        ++n;
    }

    public void add(){
        Bill ds = new Bill();
        ds.nhap();
        System.out.println("Do you want to add details for this bill? (Y/N)");
        String choice = sc.nextLine().trim().toUpperCase();
        if (choice.equals("Y")) {
            ds.getDetailsBus().createBillDetailsList(); // Gọi hàm tạo danh sách BillDetails
        }
        add(ds);
    }

    public void fixCustomerId(String bd, String newCId){
        for(int i = 0; i < n; ++i){
            if(ds[i].getBillId().equals(bd)){
                ds[i].setCustomerId(newCId);
            }
        }
    }

    public void fixEmployeeId(String bd, String newEId){
        for(int i = 0; i < n; ++i){
            if(ds[i].getBillId().equals(bd)){
                ds[i].setEmployeeId(newEId);
            }
        }
    }

    public void fixDate(String bd,LocalDate newDate){
        for(int i = 0; i < n; ++i){
            if(ds[i].getBillId().equals(bd)){
                ds[i].setDate(newDate);
            }
        }
    }

    public void fixDiscount(String bd, BigDecimal newDiscount){
        for(int i = 0; i < n; ++i){
            if(ds[i].getBillId().equals(bd)){
                ds[i].setDiscount(newDiscount);
            }
        }
    }

    public void fixTotalPrice(String bd, BigDecimal newTotalPrice){
        for(int i = 0; i < n; ++i){
            if(ds[i].getBillId().equals(bd)){
                ds[i].setTotalPrice(newTotalPrice);
            }
        }
    }

    public void edit(){
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
                System.out.println("5. total price");
                System.out.println("0. thoat");
                System.out.println("insert index: ");
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
                        System.out.println("set total price");
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
            System.out.println("bill id doesn't exist");
        }
    }

    public void remove(String bd){
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
            System.out.println("not found!!!");
        }
    }

    public void remove(){
        System.out.println("insert bill id you wanna remove");
        String bd = sc.nextLine();
        remove(bd);
    }

    public void findBillId(String newbd){
        boolean flag = false;
        for(int i = 0; i < n; ++i){
            if(ds[i].getBillId().equals(newbd)){
                ds[i].toString();
                flag = true;
            }
        }
        if(flag == false){
            System.out.println("not found!!!");
        }
    }

    public void findEmployeeId(String neweid){
        boolean flag = false;
        for(int i = 0; i < n; ++i){
            if(ds[i].getEmployeeId().equals(neweid)){
                ds[i].toString();
                flag = true;
            }
        }
        if(flag == false){
            System.out.println("not found!!!");
        }
    }

    public void findCustomerId(String newcid){
        boolean flag = false;
        for(int i = 0; i < n; ++i){
            if(ds[i].getCustomerId().equals(newcid)){
                ds[i].toString();
                flag = true;
            }
        }
        if(flag == false){
            System.out.println("not found!!!");
        }
    }

    @SuppressWarnings("unlikely-arg-type")
    public void findDate(LocalDate date){
        boolean flag = false;
        for(int i = 0; i < n; ++i){
            if(ds[i].getDate().equals(date)){
                ds[i].toString();
                flag = true;
            }
        }
        if(flag == false){
            System.out.println("not found!!!");
        }
    }

    public void findDiscount(BigDecimal newDiscount){
        boolean flag = false;
        for(int i = 0; i < n; ++i){
            if(ds[i].getDiscount().equals(newDiscount)){
                ds[i].toString();
                flag = true;
            }
        }
        if(flag == false){
            System.out.println("not found!!!");
        }
    }

    public void findTotalPrice(BigDecimal newtotal){
        boolean flag = false;
        for(int i = 0; i < n; ++i){
            if(ds[i].getTotalPrice().equals(newtotal)){
                ds[i].toString();
                flag = true;
            }
        }
        if(flag == false){
            System.out.println("not found!!!");
        }
    }

    public void find(){
        System.out.println("1. search bill id");
        System.out.println("2. search employee id");
        System.out.println("3. search customer id");
        System.out.println("4. search date");
        System.out.println("5. search discount");
        System.out.println("6. search total price");
        System.out.println("insert:");
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
                findBillId(bid);
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
                findEmployeeId(eid);
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
                findCustomerId(cid);       
                break;
            case 4:
            LocalDate date;
            do {
                 System.out.print("set date : ");
                 String dateInput = sc.nextLine().trim();
                 date = Validate.isCorrectDate(dateInput);
            } while (date == null);
                findDate(date);   
                break;
            case 5:
                BigDecimal discount;
            do {
                 System.out.print("set discount : ");
                 String value = sc.nextLine();
                 discount = Validate.isBigDecimal(value);
            } while (discount == null);
                findDiscount(discount);        
                break;
            case 6:
                System.out.println("insert total price you want to search");
                BigDecimal newTotalPrice = sc.nextBigDecimal();
                sc.nextLine();
                findTotalPrice(newTotalPrice);        
                break;
            default:
                break;
        }
    }

public void writeFile() throws IOException   {
    try (DataOutputStream file = new DataOutputStream(new FileOutputStream("src/main/resources/Bill ", false))) {
        file.writeInt(n); 
        for (int i = 0; i < n; ++i) {
            // write bill
            file.writeUTF(ds[i].getBillId());
            file.writeUTF(ds[i].getCustomerId());
            file.writeUTF(ds[i].getEmployeeId());
            file.writeUTF(ds[i].getSaleCode().getDetail().getPromoCode());
            file.writeUTF(ds[i].getDate().toString());
            file.writeUTF(ds[i].getDiscount().toString());
            file.writeUTF(ds[i].getTotalPrice().toString());

            // write bill details
            BillDetailsBus detailsBus = ds[i].getDetailsBus();
            if (detailsBus != null && detailsBus.getds() != null) {
                BillDetails[] details = detailsBus.getds();
                file.writeInt(details.length); 
                for (BillDetails detail : details) {
                    file.writeUTF(detail.getBillId());
                    file.writeInt(detail.getQuantity());
                    file.writeUTF(detail.getPrice().toString());
                }
            } else {
                file.writeInt(0); // no bill details
            }
        }
        System.out.println("Write to file successfully!");
    } catch (IOException e) {
        System.out.println("Error writing file: " + e.getMessage());
        }
    }

    public void readFile() throws IOException{
        try (DataInputStream file = new DataInputStream(new FileInputStream("src/main/resources/Bill"))) {
            int billCount = file.readInt();
            Bill[] tmpBill = new Bill[billCount];
    
            for (int i = 0; i < billCount; i++) {
                // read bill
                String billId = file.readUTF();
                String customerId = file.readUTF();
                String employeeId = file.readUTF();
                String saleCode = file.readUTF();
                LocalDate date = LocalDate.parse(file.readUTF());
                BigDecimal discount = new BigDecimal(file.readUTF());
                BigDecimal totalPrice = new BigDecimal(file.readUTF());
                SaleEvents sale = new SaleEvents();

                Bill fileBill = new Bill(billId,customerId,employeeId,saleCode, discount,totalPrice,date);
                
                // read bill details
                int detailsCount = file.readInt();
                BillDetailsBus tmpDetail = new BillDetailsBus();
                for (int j = 0; j < detailsCount; j++) {
                    String billDetailsId = file.readUTF();
                    int quantity = file.readInt();
                    BigDecimal price = new BigDecimal(file.readUTF());
                    BigDecimal subTotal = new BigDecimal(file.readUTF());
    
                    BillDetails dt = new BillDetails(billDetailsId, quantity, price, subTotal);
                    tmpDetail.add(dt);
                }
    
                fileBill.setDetailsBus(detailsBus);
                tmpBill[i] = fileBill;
            }

            setds(tmpBill);
            setn(billCount);

        System.out.println("Read from file successfully!");
    } catch (IOException e) {
        System.out.println("Error reading file: " + e.getMessage());
        }
    }
}