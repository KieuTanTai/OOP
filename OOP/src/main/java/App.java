import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;

import BUS.BookFormatsBUS;
import BUS.BooksBUS;
import BUS.CustomersBUS;
import BUS.GenresBUS;
import BUS.MidForBooksBUS;
import BUS.PublishersBUS;
import BUS.StaTypesBUS;
import BUS.StationeriesBUS;
import BUS.TypesBUS;
import DTO.Customers;

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
                newTest.readFile();
                initList.readFile();
                testList.readFile();
                testArray.readFile();
                listFormat.readFile();
                testPublishers.readFile();
                listBooks.readFile();
                listSta.readFile();

                // !INIT ARRAY
                Customers[] customers = new Customers[] {
                                new Customers("A1B2C3D4", "Minh", "Nguyen", LocalDate.of(1995, 5, 20), "0901234567",
                                                "Hanoi", BigDecimal.valueOf(12311.45)),
                                new Customers("E5F6G7H8", "Lan", "Tran", LocalDate.of(1989, 11, 15), "0912345678",
                                                "Ho Chi Minh", BigDecimal.valueOf(234.56)),
                                new Customers("I9J0K1L2", "Hiroshi", "Tanaka", LocalDate.of(2000, 3, 30), "0987654321",
                                                "Tokyo", BigDecimal.valueOf(345.67)),
                                new Customers("M3N4O5P6", "Mai", "Le", LocalDate.of(1997, 8, 10), "0998765432", "Kyoto",
                                                BigDecimal.valueOf(456.78)),
                                new Customers("Q7R8S9T0", "John", "Smith", LocalDate.of(1985, 7, 25), "0861234567",
                                                "London", BigDecimal.valueOf(567.89)),
                                new Customers("U1V2W3X4", "Sakura", "Ito", LocalDate.of(2002, 12, 5), "0332345678",
                                                "Osaka", BigDecimal.valueOf(67118.90)),
                                new Customers("Y5Z6A7B8", "Hoa", "Yamamoto", LocalDate.of(1993, 4, 18), "0343456789",
                                                "Paris", BigDecimal.valueOf(789.01)),
                                new Customers("C9D0E1F2", "Tom", "Wilson", LocalDate.of(1991, 2, 22), "0354567890",
                                                "Sydney", BigDecimal.valueOf(890.12)),
                                new Customers("G3H4I5J6", "Linh", "Pham", LocalDate.of(1998, 7, 12), "0376543210",
                                                "Da Nang", BigDecimal.valueOf(901.23)),
                                new Customers("K7L8M9N0", "Anh", "Bui", LocalDate.of(2003, 10, 4), "0387654321", "Hue",
                                                BigDecimal.valueOf(123.12)),
                                new Customers("O1P2Q3R4", "Yuki", "Sato", LocalDate.of(1994, 3, 18), "0398765432",
                                                "Nagoya", BigDecimal.valueOf(222.34)),
                                new Customers("S5T6U7V8", "Nam", "Vo", LocalDate.of(1986, 2, 9), "0323456789",
                                                "Can Tho", BigDecimal.valueOf(31145.98)),
                                new Customers("W9X0Y1Z2", "Quang", "Hoang", LocalDate.of(1999, 6, 27), "0312345678",
                                                "Nha Trang", BigDecimal.valueOf(777.77)),
                                new Customers("B3C4D5E6", "Dung", "Do", LocalDate.of(1992, 1, 3), "0335678901",
                                                "Hai Phong", BigDecimal.valueOf(123.33)),
                                new Customers("F7G8H9I0", "Khanh", "Nguyen", LocalDate.of(1996, 12, 14), "0346789012",
                                                "Da Lat", BigDecimal.valueOf(432.10)),
                                new Customers("A2B3C4D5", "Tuan", "Nguyen", LocalDate.of(1990, 4, 12), "0909876543",
                                                "Vinh", BigDecimal.valueOf(123.45)),
                                new Customers("D1E2F3G4", "Lai", "Bui", LocalDate.of(1988, 6, 30), "0918765432",
                                                "Can Tho", BigDecimal.valueOf(234.56)),
                                new Customers("H1I2J3K4", "Chie", "Nakamura", LocalDate.of(1995, 5, 10), "0945678901",
                                                "Kyoto", BigDecimal.valueOf(3415.67)),
                                new Customers("L2M3N4O5", "Sarah", "Johnson", LocalDate.of(1987, 10, 24), "0956789012",
                                                "New York", BigDecimal.valueOf(456.78)),
                                new Customers("P2Q3R4S5", "Maya", "Kim", LocalDate.of(2001, 2, 3), "0967890123",
                                                "San Francisco", BigDecimal.valueOf(567.89)),
                                new Customers("T1U2V3W4", "Tao", "Li", LocalDate.of(1993, 7, 21), "0978901234",
                                                "Shenzhen", BigDecimal.valueOf(678.90)),
                                new Customers("J4K5L6M7", "Luna", "Lee", LocalDate.of(1999, 9, 10), "0989012345",
                                                "Seoul", BigDecimal.valueOf(789.01)),
                                new Customers("A5B6C7D8", "Hao", "Le", LocalDate.of(1990, 11, 18), "0990123456", "HCMC",
                                                BigDecimal.valueOf(890.12)),
                                new Customers("G5H6I7J8", "Sam", "Carter", LocalDate.of(1992, 4, 5), "0801234567",
                                                "Los Angeles", BigDecimal.valueOf(901.23)),
                                new Customers("J9K0L1M2", "Emma", "Davis", LocalDate.of(1998, 12, 25), "0812345678",
                                                "Berlin", BigDecimal.valueOf(123.45)),
                                new Customers("F5G6H7I8", "Ryu", "Takeda", LocalDate.of(1991, 8, 11), "0823456789",
                                                "Osaka", BigDecimal.valueOf(234.56)),
                                new Customers("D6E7F8G9", "Noah", "Hughes", LocalDate.of(1994, 3, 6), "0834567890",
                                                "London", BigDecimal.valueOf(345.67)),
                                new Customers("L9M0N1O2", "Elliot", "Thompson", LocalDate.of(1997, 5, 21), "0845678901",
                                                "Edinburgh", BigDecimal.valueOf(456.78)),
                                new Customers("K8L9M0N1", "Sophia", "Scott", LocalDate.of(1996, 12, 19), "0856789012",
                                                "Singapore", BigDecimal.valueOf(567.89)),
                                new Customers("T3U4V5W6", "Jackson", "Martin", LocalDate.of(1988, 1, 7), "0867890123",
                                                "Barcelona", BigDecimal.valueOf(678.90)),
                                new Customers("R1S2T3U4", "Olivia", "Miller", LocalDate.of(1992, 2, 14), "0878901234",
                                                "Tokyo", BigDecimal.valueOf(11789.01)),
                                new Customers("C8D9E0F1", "Lucas", "Wilson", LocalDate.of(1995, 3, 18), "0889012345",
                                                "Chicago", BigDecimal.valueOf(1890.12)),
                                new Customers("M2N3O4P5", "Zara", "Evans", LocalDate.of(1990, 5, 12), "0890123456",
                                                "Paris", BigDecimal.valueOf(90111.23)),
                                new Customers("Q5R6S7T8", "Evan", "Morris", LocalDate.of(2002, 11, 20), "0901234599",
                                                "Brisbane", BigDecimal.valueOf(123.45)),
                                new Customers("F8G9H0I1", "Jasmine", "Wang", LocalDate.of(1994, 9, 9), "0912345688",
                                                "Toronto", BigDecimal.valueOf(21134.56)),
                                new Customers("P4Q5R6S7", "Xiao", "Liang", LocalDate.of(1998, 7, 23), "0923456789",
                                                "Beijing", BigDecimal.valueOf(345.67)),
                                new Customers("B2C3D4E5", "David", "Clark", LocalDate.of(1991, 6, 5), "0934567890",
                                                "Manchester", BigDecimal.valueOf(456.78)),
                                new Customers("N3O4P5Q6", "Yuan", "Cheng", LocalDate.of(1990, 3, 8), "0945678901",
                                                "Shanghai", BigDecimal.valueOf(567.89)),
                                new Customers("S3T4U5V6", "Isabella", "Gomez", LocalDate.of(2003, 1, 1), "0956789012",
                                                "Madrid", BigDecimal.valueOf(6178.90)),
                };

                // !INIT VALUE
                CustomersBUS cusList = new CustomersBUS(customers, 50);
                
                
                // !SHOW BEFORE
                cusList.showList();


                // !TEST METHODS


                // !SHOW DURING
                cusList.showList();


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
