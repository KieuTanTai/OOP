import java.io.IOException;
import java.util.Scanner;

import BUS.BookFormatsBUS;
import BUS.BooksBUS;
import BUS.GenresBUS;
import BUS.MidForBooksBUS;
import BUS.PublishersBUS;
import BUS.StaTypesBUS;
import BUS.StationeriesBUS;
import BUS.TypesBUS;

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


                // !INIT VALUE


                // !SHOW BEFORE
                // listSta.showList();
                // StaTypesBUS.showList();
                // !TEST METHODS
                System.out.println("*".repeat(80));
                listBooks.getBooksList()[0].showInfo();
                
                // !SHOW DURING
                // listSta.showList();

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
