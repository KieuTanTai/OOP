package BUS;

import DTO.SaleEvents;

import java.util.Arrays;
import java.util.Scanner;

public class SaleEventsBUS {
    private static SaleEvents[] ListSaleEvent;
    private int count;
    Scanner sc = new Scanner(System.in);

    public SaleEventsBUS(SaleEvents[] listSaleEvent, int count) {
        ListSaleEvent = listSaleEvent;
        this.count = count;
    }

    public void add(SaleEvents saleEvents){
        count++;
        ListSaleEvent = Arrays.copyOf(ListSaleEvent, count);
        ListSaleEvent[count-1] = saleEvents;
    }

    public void update(String id){
        for (int i = 0 ; i < count ; i++)
            if (ListSaleEvent[i].getSaleEvId() == id)
                ListSaleEvent[i].nhap();
    }

    public boolean delete(int vt) {
        if (vt >= count)
            return false;

        count--;
        for (int x=vt; x<count; x++)
            ListSaleEvent[x] = ListSaleEvent[x+1];
        ListSaleEvent[count] = null;
        ListSaleEvent = Arrays.copyOf(ListSaleEvent, count);
        return true;
    }

    public SaleEvents findById(String  id){
        SaleEvents result = new SaleEvents();

        for (int i = 0 ; i < count ; i++){
            if (ListSaleEvent[i].getSaleEvId() == id){
                result = ListSaleEvent[i];
            }
        }
        return  result;
    }

    public SaleEvents findByName(String name){
        SaleEvents result = new SaleEvents();

        for (int i=0; i < count; i++){
            if (ListSaleEvent[i].getSaleEvName() == name)
                result = ListSaleEvent[i];

        }
        return result;
    }

    public void readFile(){}

    public void writeFile(){}

}