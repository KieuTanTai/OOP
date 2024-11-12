package Manager;
import java.util.Scanner;

public class QuanLyBill {
    private DSBill ds;
    Scanner sc = new Scanner(System.in);

    public void menu(){
        ds = new DSBill();
        boolean flag = true;
        
        while(flag){
            System.out.println("------------Menu------------");
            System.out.println("1. them bill");
            System.out.println("2. xoa bill theo bill id");
            System.out.println("3. sua thong tin cua bill");
            System.out.println("4. tim kiem bill");
            System.out.println("5. xuat danh sach cac bill");
            System.out.println("6. ket thuc chuong trinh");
            System.out.println("Vui long nhap lua chon");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    ds.them();
                    System.out.println("------------Thong tin Bill------------");
                    ds.xuat();
                    break;
                case 2:
                    ds.xoa();
                    System.out.println("------------Thong tin Bill------------");
                    ds.xuat();
                    break;
                case 3:
                    ds.sua();
                    System.out.println("------------Thong tin Bill------------");
                    ds.xuat();        
                    break;
                case 4:
                    ds.timKiem();
                    System.out.println("------------Thong tin Bill------------");
                    ds.xuat();        
                    break;
                case 5:
                    System.out.println("------------Thong tin Bill------------");
                    ds.xuat();
                    break;
                default:
                    break;
            }
        }
    }
}
