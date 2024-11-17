import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;

import BUS.BookFormatsBUS;
import BUS.BooksBUS;
import BUS.GenresBUS;
import BUS.MidForBooksBUS;
import BUS.PublishersBUS;
import BUS.StaTypesBUS;
import BUS.StationaryBUS;
import BUS.TypesBUS;
import DTO.BookFormats;
import DTO.Books;
import DTO.StaTypes;
import DTO.Stationary;
import util.Validate;

public class App {
        public static void main(String[] args) throws IOException {
                // !INIT OBJ
                MidForBooksBUS testArray = new MidForBooksBUS();
                GenresBUS initList = new GenresBUS();
                TypesBUS testList = new TypesBUS();
                StaTypesBUS newTest = new StaTypesBUS();
                PublishersBUS testPublishers = new PublishersBUS();
                BookFormatsBUS listFormat = new BookFormatsBUS();
                BooksBUS listBooks = new BooksBUS();
                StationaryBUS listSta = new StationaryBUS();
                Scanner input = new Scanner(System.in);
                newTest.readFile();
                initList.readFile();
                testList.readFile();
                testArray.readFile();
                listFormat.readFile();
                testPublishers.readFile();
                listBooks.readFile();

                // !INIT ARRAY


                // !INIT VALUE


                // !SHOW BEFORE
                listSta.readFile();
                listSta.showList();


                // !TEST METHODS
                // listBooks.showList();

                // !SHOW DURING

                // !SHOW RESULT
                // System.out.println("------------------------AFTER(GENRES)-----------------------");
                // GenresBUS.showList();
                // System.out.println("------------------------AFTER(GENRES)-----------------------");
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
