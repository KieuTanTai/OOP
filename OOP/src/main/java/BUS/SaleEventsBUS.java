package BUS;

import DTO.SaleEvents;
import java.util.Arrays;

public class SaleEventsBUS {
    private static SaleEvents[] ListSaleEvent;
    private int count;

    public SaleEventsBUS(SaleEvents[] listSaleEvent, int count) {
        ListSaleEvent = listSaleEvent;
        this.count = count;
    }

    public void add(SaleEvents saleEvents){
        count++;
        ListSaleEvent = Arrays.copyOf(ListSaleEvent, count);
        ListSaleEvent[count-1] = saleEvents;
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
}