import java.io.IOException;
import java.math.BigDecimal;
import java.security.PublicKey;
import java.time.LocalDate;
import java.util.Scanner;

import BUS.BookFormatsBUS;
import BUS.BooksBUS;
import BUS.CustomersBUS;
import BUS.EmployeesBUS;
import BUS.GRNsBUS;
import BUS.GenresBUS;
import BUS.MidForBooksBUS;
import BUS.PublishersBUS;
import BUS.StaTypesBUS;
import BUS.StationeriesBUS;
import BUS.SuppliersBUS;
import BUS.TypesBUS;
import DTO.BookFormats;
import DTO.BookGenres;
import DTO.BookTypes;
import DTO.Books;
import DTO.Customers;
import DTO.Employees;
import DTO.GRNs;
import DTO.Publishers;
import DTO.SaleEvents;
import DTO.StaTypes;
import DTO.Stationeries;
import DTO.Suppliers;

public class App {
        Scanner input = new Scanner(System.in);

        public static void main(String[] args) throws IOException {
                // !INIT OBJ
                MidForBooksBUS testArray = new MidForBooksBUS();
                GenresBUS initList = new GenresBUS();
                TypesBUS testList = new TypesBUS();
                StaTypesBUS newTest = new StaTypesBUS();
                PublishersBUS testPublishers = new PublishersBUS();
                BookFormatsBUS listFormat = new BookFormatsBUS();
                StationeriesBUS listSta = new StationeriesBUS();
                BooksBUS listBooks = new BooksBUS();
                CustomersBUS cusList = new CustomersBUS();
                EmployeesBUS empList = new EmployeesBUS();
                SuppliersBUS supList = new SuppliersBUS();

                // *READ FILE
                initList.readFile();
                testList.readFile();
                testPublishers.readFile();
                listFormat.readFile();
                supList.readFile();
                listBooks.readFile();
                testArray.readFile();
                cusList.readFile();
                empList.readFile();
                newTest.readFile();
                listSta.readFile();


                // TODO
                // *Books, MidForBook, Stationeries (DONE)
                // *Customers, Employees (DONE)
                // *BookGenres, BookTypes, Publishers, StaTypes, Suppliers (DONE)
                //  *GRN, GRNDetail (DONE)
                //  *

                // !INIT ARRAY


                // !INIT VALUE
                GRNsBUS test = new GRNsBUS();
                test.add();

                // !SHOW BEFORE
                // SuppliersBUS.showList();

                // !TEST METHODS
                // !SHOW DURING
                // SuppliersBUS.showList();

                // !SHOW RESULT
                // System.out.println("------------------------AFTER(BOOK_TYPE)-----------------------");
                // TypesBUS.showList();
                // System.out.println("------------------------AFTER(BOOK_TYPE)-----------------------");
                // System.out.println("------------------------AFTER(MID)-----------------------");
                // MidForBooksBUS.showList();
                // System.out.println("------------------------AFTER(MID)-----------------------");
                // System.out.println("------------------------AFTER(STA_TYPES)-----------------------");
                // StaTypesBUS.showList();
                // System.out.println("------------------------AFTER(STA_TYPES)-----------------------");
                // System.out.println("------------------------AFTER(PUBLISHERS)-----------------------");
                // PublishersBUS.showList();
                // System.out.println("------------------------AFTER(PUBLISHERS)-----------------------");
        }
}
