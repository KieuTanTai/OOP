import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;

import BUS.BookFormatsBUS;
import BUS.BooksBUS;
import BUS.CustomersBUS;
import BUS.EmployeesBUS;
import BUS.GenresBUS;
import BUS.MidForBooksBUS;
import BUS.PublishersBUS;
import BUS.StaTypesBUS;
import BUS.StationeriesBUS;
import BUS.TypesBUS;
import DTO.Customers;
import DTO.Employees;

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
                newTest.readFile();
                initList.readFile();
                testList.readFile();
                testArray.readFile();
                listFormat.readFile();
                testPublishers.readFile();
                listBooks.readFile();
                listSta.readFile();
                cusList.readFile();
                empList.readFile();

                // !INIT ARRAY
                

                // !INIT VALUE


                // !SHOW BEFORE
                empList.showList();

                // !TEST METHODS
                // empList.relativeSearch(LocalDate.of(1995, 5, 12), "date");

                // !SHOW DURING
                // empList.showList();

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
