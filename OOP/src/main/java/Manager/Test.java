package Manager;
// import BUS.*;
import DTO.*;
// import util.*;

public class Test {
    public static void main(String[] args) {
        Bill bill = new Bill();
        bill.nhap();
        System.out.println(bill.toString());
        bill.xuatBillDetails();

    }
}
