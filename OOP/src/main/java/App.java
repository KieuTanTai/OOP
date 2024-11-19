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
                Employees[] employees = {
                                new Employees("A1B2C3D4", "Minh", "Nguyen", LocalDate.of(1995, 5, 20), "0901234567",
                                                "Active", "minh.nguyen", "Password@123!", "Sales Associate"),
                                new Employees("E5F6G7H8", "Lan", "Tran", LocalDate.of(1989, 11, 15), "0912345678",
                                                "Inactive", "lan.tran", "Secure#Pass1$", "Inventory Manager"),
                                new Employees("X9Y0Z1A2", "Tuan", "Pham", LocalDate.of(1993, 4, 12), "0923456789",
                                                "Active", "tuan.pham", "Pass%word456@", "Warehouse Staff"),
                                new Employees("K3L4M5N6", "Hoa", "Le", LocalDate.of(1990, 8, 8), "0934567890",
                                                "Inactive", "hoa.le", "Admin@2023#", "Sales Associate"),
                                new Employees("O7P8Q9R0", "Hieu", "Dang", LocalDate.of(1992, 1, 15), "0945678901",
                                                "Active", "hieu.dang", "SafePass123$", "Inventory Manager"),
                                new Employees("A2B3C4D5", "Duc", "Vu", LocalDate.of(1998, 3, 25), "0956789012",
                                                "Active", "duc.vu", "ShopKeeper@99#", "Warehouse Staff"),
                                new Employees("B1C2D3E4", "Mai", "Phan", LocalDate.of(1987, 7, 7), "0967890123",
                                                "Inactive", "mai.phan", "Secure&Strong1!", "Sales Associate"),
                                new Employees("C3D4E5F6", "Nam", "Hoang", LocalDate.of(1994, 10, 10), "0978901234",
                                                "Active", "nam.hoang", "Inventory#Role@", "Inventory Manager"),
                                new Employees("D5E6F7G8", "Thu", "Nguyen", LocalDate.of(1991, 12, 30), "0989012345",
                                                "Inactive", "thu.nguyen", "Password!1234$", "Warehouse Staff"),
                                new Employees("E7F8G9H0", "Phat", "Nguyen", LocalDate.of(1985, 2, 19), "0990123456",
                                                "Active", "phat.nguyen", "Admin&Work@", "Sales Associate"),
                                new Employees("F1G2H3I4", "Ngoc", "Do", LocalDate.of(2000, 6, 15), "0901123456",
                                                "Inactive", "ngoc.do", "Secure@Store@", "Inventory Manager"),
                                new Employees("G4H5I6J7", "Khanh", "Tran", LocalDate.of(1996, 8, 28), "0912233445",
                                                "Active", "khanh.tran", "Retail!Pass2022#", "Warehouse Staff"),
                                new Employees("H6I7J8K9", "Linh", "Pham", LocalDate.of(1999, 11, 5), "0923344556",
                                                "Active", "linh.pham", "Manager$123#", "Sales Associate"),
                                new Employees("I8J9K0L1", "Binh", "Vu", LocalDate.of(1988, 4, 18), "0934455667",
                                                "Inactive", "binh.vu", "Team@123Pass$", "Inventory Manager"),
                                new Employees("J0K1L2M3", "Trung", "Nguyen", LocalDate.of(1997, 5, 12), "0945566778",
                                                "Active", "trung.nguyen", "Role&Secure1@", "Warehouse Staff"),
                                new Employees("K2L3M4N5", "Tam", "Ho", LocalDate.of(1990, 9, 3), "0956677889",
                                                "Inactive", "tam.ho", "Pass$Role456!", "Sales Associate"),
                                new Employees("L4M5N6O7", "Quang", "Le", LocalDate.of(1993, 3, 27), "0967788990",
                                                "Active", "quang.le", "Inventory@Admin1#", "Inventory Manager"),
                                new Employees("M6N7O8P9", "Hanh", "Do", LocalDate.of(1989, 6, 2), "0978899001",
                                                "Active", "hanh.do", "Safe&Password@", "Warehouse Staff"),
                                new Employees("N8O9P0Q1", "Bao", "Tran", LocalDate.of(1992, 7, 23), "0989900112",
                                                "Inactive", "bao.tran", "Work@Role123#", "Sales Associate"),
                                new Employees("O0P1Q2R3", "Son", "Hoang", LocalDate.of(1986, 12, 25), "0991011123",
                                                "Active", "son.hoang", "Secure$Inventory@", "Inventory Manager"),
                                new Employees("P2Q3R4S5", "Vy", "Nguyen", LocalDate.of(1991, 4, 5), "0902123456",
                                                "Active", "vy.nguyen", "WorkPass@Store1#", "Warehouse Staff"),
                                new Employees("Q4R5S6T7", "Dat", "Pham", LocalDate.of(1990, 9, 12), "0913234567",
                                                "Inactive", "dat.pham", "Secure&Role@", "Sales Associate"),
                                new Employees("R6S7T8U9", "Khai", "Le", LocalDate.of(1994, 1, 3), "0924345678",
                                                "Active", "khai.le", "Inventory@2022#", "Inventory Manager"),
                                new Employees("S8T9U0V1", "Tung", "Tran", LocalDate.of(1993, 12, 31), "0935456789",
                                                "Inactive", "tung.tran", "SafeAdminPass@", "Warehouse Staff"),
                                new Employees("T0U1V2W3", "Hung", "Vo", LocalDate.of(1992, 6, 7), "0946567890",
                                                "Active", "hung.vo", "AdminPass!Role#", "Sales Associate"),
                                new Employees("U2V3W4X5", "Nhung", "Hoang", LocalDate.of(1988, 11, 22), "0957678901",
                                                "Inactive", "nhung.hoang", "Secure$Pass2023@", "Inventory Manager"),
                                new Employees("V4W5X6Y7", "Hai", "Nguyen", LocalDate.of(1995, 8, 19), "0968789012",
                                                "Active", "hai.nguyen", "Role@AdminSecure1#", "Warehouse Staff"),
                                new Employees("W6X7Y8Z9", "My", "Pham", LocalDate.of(1986, 10, 1), "0979890123",
                                                "Inactive", "my.pham", "RetailPass@2023#", "Sales Associate"),
                                new Employees("X8Y9Z0A1", "Phong", "Le", LocalDate.of(1990, 3, 20), "0980901234",
                                                "Active", "phong.le", "Secure@SafeRole#", "Inventory Manager"),
                                new Employees("Y0Z1A2B3", "Viet", "Tran", LocalDate.of(1997, 7, 13), "0991012345",
                                                "Inactive", "viet.tran", "AdminPass2024@", "Warehouse Staff"),
                                new Employees("Z2A3B4C5", "Hanh", "Vu", LocalDate.of(1991, 2, 18), "0901123456",
                                                "Active", "hanh.vu", "Retail@PassRole#", "Sales Associate"),
                                new Employees("A4B5C6D7", "Thanh", "Pham", LocalDate.of(1998, 5, 28), "0912234567",
                                                "Inactive", "thanh.pham", "Secure#Inventory@", "Inventory Manager"),
                                new Employees("B6C7D8E9", "Cuong", "Nguyen", LocalDate.of(1992, 10, 11), "0923345678",
                                                "Active", "cuong.nguyen", "Role$AdminSecure@", "Warehouse Staff"),
                                new Employees("C8D9E0F1", "Huong", "Le", LocalDate.of(1993, 6, 26), "0934456789",
                                                "Inactive", "huong.le", "InventorySecure@1#", "Sales Associate"),
                                new Employees("D0E1F2G3", "Phuc", "Tran", LocalDate.of(1987, 4, 9), "0945567890",
                                                "Active", "phuc.tran", "RetailAdmin2023@", "Inventory Manager"),
                                new Employees("E2F3G4H5", "Duy", "Pham", LocalDate.of(1996, 8, 15), "0956678901",
                                                "Inactive", "duy.pham", "SafeRole&Pass@", "Warehouse Staff"),
                                new Employees("F4G5H6I7", "Anh", "Nguyen", LocalDate.of(1989, 9, 24), "0967789012",
                                                "Active", "anh.nguyen", "Secure@RetailRole1#", "Sales Associate"),
                                new Employees("G6H7I8J9", "Bao", "Hoang", LocalDate.of(1990, 1, 10), "0978890123",
                                                "Inactive", "bao.hoang", "AdminRole2024@", "Inventory Manager"),
                                new Employees("H8I9J0K1", "Kiet", "Do", LocalDate.of(1992, 3, 6), "0989901234",
                                                "Active", "kiet.do", "SafeInventoryPass@", "Warehouse Staff"),
                                new Employees("I0J1K2L3", "Thao", "Le", LocalDate.of(1988, 12, 17), "0991012345",
                                                "Inactive", "thao.le", "RetailPass#Secure@", "Sales Associate"),
                                new Employees("J2K3L4M5", "Phuong", "Nguyen", LocalDate.of(1994, 11, 19), "0902123456",
                                                "Active", "phuong.nguyen", "Inventory&SafePass@", "Inventory Manager"),
                                new Employees("K4L5M6N7", "Minh", "Tran", LocalDate.of(1991, 4, 30), "0913234567",
                                                "Inactive", "minh.tran", "Secure@2022Retail#", "Warehouse Staff"),
                                new Employees("L6M7N8O9", "Chau", "Vu", LocalDate.of(1997, 7, 25), "0924345678",
                                                "Active", "chau.vu", "Admin$SafeRole@", "Sales Associate"),
                                new Employees("M8N9O0P1", "Loc", "Pham", LocalDate.of(1992, 5, 13), "0935456789",
                                                "Inactive", "loc.pham", "SecureInventoryPass@", "Inventory Manager"),
                                new Employees("N0P1Q2R3", "Yen", "Phan", LocalDate.of(1994, 3, 8), "0946567890",
                                                "Active", "yen.phan", "Retail#Secure2024@", "Warehouse Staff"),
                                new Employees("O2P3Q4R5", "Hanh", "Ho", LocalDate.of(1989, 9, 12), "0957678901",
                                                "Inactive", "hanh.ho", "Inventory&PassRole@", "Sales Associate"),
                                new Employees("P4Q5R6S7", "Tan", "Nguyen", LocalDate.of(1995, 12, 5), "0968789012",
                                                "Active", "tan.nguyen", "SecureAdmin@2022#", "Inventory Manager"),
                                new Employees("Q6R7S8T9", "Diep", "Vu", LocalDate.of(1998, 7, 14), "0979890123",
                                                "Inactive", "diep.vu", "Warehouse&RolePass@", "Warehouse Staff"),
                                new Employees("R8S9T0U1", "Vinh", "Le", LocalDate.of(1987, 11, 2), "0980901234",
                                                "Active", "vinh.le", "RetailSecure$Pass#", "Sales Associate"),
                                new Employees("S0T1U2V3", "Thanh", "Do", LocalDate.of(1993, 4, 25), "0991012345",
                                                "Inactive", "thanh.do", "Admin@Role2023#", "Inventory Manager")

                };

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
