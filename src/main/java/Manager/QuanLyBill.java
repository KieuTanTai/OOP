package Manager;

import java.util.Scanner;

import BUS.BillBus;

public class QuanLyBill {
    private BillBus ds;
    Scanner sc = new Scanner(System.in);

    public void menu(){
        ds = new BillBus();
        boolean flag = true;
        
        while(flag){
            System.out.println("------------Menu------------");
            System.out.println("1. add bill");
            System.out.println("2. delete bill by its id");
            System.out.println("3. edit bill's information");
            System.out.println("4. search bill");
            System.out.println("5. show bill list");
            System.out.println("6. stop program");
            System.out.println("enter your selection");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    ds.add();
                    System.out.println("------------BILL'S INFORMATION------------");
                    ds.xuat();
                    break;
                case 2:
                    ds.delete();
                    System.out.println("------------BILL'S INFORMATION------------");
                    ds.xuat();
                    break;
                case 3:
                    ds.edit();
                    System.out.println("------------BILL'S INFORMATION------------");
                    ds.xuat();        
                    break;
                case 4:
                    ds.find();
                    System.out.println("------------BILL'S INFORMATION------------");
                    ds.xuat();        
                    break;
                case 5:
                    System.out.println("------------BILL'S INFORMATION------------");
                    ds.xuat();
                    break;
                case 6:
                    
                default:
                    break;
            }
        }
    }
}
