import java.io.IOException;
import java.math.BigDecimal;
import java.util.Scanner;

import BUS.BillBUS;
import BUS.BookFormatsBUS;
import BUS.BooksBUS;
import BUS.CustomersBUS;
import BUS.EmployeesBUS;
import BUS.GRNDetailsBUS;
import BUS.GRNsBUS;
import BUS.GenresBUS;
import BUS.MidForBooksBUS;
import BUS.PublishersBUS;
import BUS.SaleEventsBUS;
import BUS.StaTypesBUS;
import BUS.StationeriesBUS;
import BUS.SuppliersBUS;
import BUS.TypesBUS;
import DTO.Bill;
import DTO.GRNs;
import util.Validate;

public class App {
        Scanner input = new Scanner(System.in);

        public static void main(String[] args) throws IOException {
                // !INIT OBJ
                MidForBooksBUS midForBook = new MidForBooksBUS();
                GenresBUS genreList = new GenresBUS();
                TypesBUS bookTypeList = new TypesBUS();
                StaTypesBUS staTypeList = new StaTypesBUS();
                PublishersBUS publisherList = new PublishersBUS();
                BookFormatsBUS formatList = new BookFormatsBUS();
                StationeriesBUS stationaryList = new StationeriesBUS();
                BooksBUS booksList = new BooksBUS();
                CustomersBUS customerList = new CustomersBUS();
                EmployeesBUS employeeList = new EmployeesBUS();
                SuppliersBUS supplierList = new SuppliersBUS();
                SaleEventsBUS saleEventList = new SaleEventsBUS();
                GRNsBUS grnList = new GRNsBUS();
                BillBUS billList = new BillBUS();

                // *READ FILE
                genreList.readFile();
                bookTypeList.readFile();
                publisherList.readFile();
                formatList.readFile();
                supplierList.readFile();
                booksList.readFile();
                midForBook.readFile();
                customerList.readFile();
                employeeList.readFile();
                staTypeList.readFile();
                stationaryList.readFile();
                saleEventList.readFile();
                grnList.readFile();
                billList.readFile();

                for (int i = 0; i < 3; i++) {
                        Bill bill = new Bill();
                        bill.setInfo();
                        billList.add(bill);
                        billList.writeFile();
                }
                billList.showList();
        }
}