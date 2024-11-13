package BUS;

import DTO.BillDetails;
import java.util.Arrays;
import java.util.Scanner;

public class BillDetailsBus {
    private static BillDetails[] ds;
    private static int n;
    Scanner sc = new Scanner(System.in);

    public BillDetailsBus(){
        BillDetailsBus.n = 0;
        ds = new BillDetails[0];
    }

    public BillDetailsBus(BillDetails[] ds, int n){
        BillDetailsBus.n = n;
        BillDetailsBus.ds = ds;
    }

    public BillDetails[] getds(){
        return ds;
    }

    public void setds(BillDetails[] ds){
        BillDetailsBus.ds = ds;
    }

    public int getn(){
        return BillDetailsBus.n;
    }

    public void setn(int n){
        BillDetailsBus.n = n;
    }

    public void nhap(){
        System.out.println("please insert how many bill details: ");
        n = sc.nextInt();
        ds = new BillDetails[n];
        for(int i = 0; i < n; ++i){
            add();
        }
    }

    public int find (String id){
        for(int i = 0;i < ds.length; ++i){
            if((ds[i].getBillId().equals(id))){
                return i;
            }
        }
        System.out.println("not found");
        return -1;
    }

    // public void search(String id){
    //     int index = find(id);
    //     if(index != -1){
           
    //     }
    // }

    public static void add(BillDetails bdObject){
        ds = Arrays.copyOf(ds, ds.length + 1);
        ds[n] = (BillDetails) bdObject;
        ++n;
    }

    public void add(){
        BillDetails ds = new BillDetails();
        ds.nhap();
        add(ds);
    }

    public void remove (String id){
        int index = find(id);
        if(index != -1){
            for(int i = index; i < ds.length; ++i){
                ds[i] = ds[i + 1];
                ds = Arrays.copyOf(ds, ds.length - 1);
                --n;
            }
        }
    }


}
