import java.io.IOException;

import BUS.GenresBUS;
import BUS.MidForBooksBUS;
import BUS.PublishersBUS;
import BUS.StaTypesBUS;
import BUS.TypesBUS;
import DTO.Books;

public class App {
    public static void main(String[] args) throws IOException {
        // !INIT ARRAY


        // !INIT VALUE
        Books book1 = new Books();

        // !INIT OBJ
        MidForBooksBUS testArray = new MidForBooksBUS();
        GenresBUS initList = new GenresBUS();
        TypesBUS testList = new TypesBUS();
        StaTypesBUS newTest = new StaTypesBUS();
        PublishersBUS testPublishers = new PublishersBUS();

        initList.readFile();
        testList.readFile();
        testArray.readFile();
        newTest.readFile();
        testPublishers.readFile();

        // !SHOW BEFORE
        System.out.println("------------------------BEFORE-----------------------");
        MidForBooksBUS.showList();
        System.out.println("------------------------BEFORE-----------------------");

        // !TEST METHODS

        // book1.setInfo();
        // book1.showInfo();

        // !SHOW DURING
        System.out.println("------------------------DURING-----------------------");
        MidForBooksBUS.showList();
        System.out.println("------------------------DURING-----------------------");

        // !SHOW RESULT        
        // System.out.println("------------------------AFTER(GENRES)-----------------------");
        // initList.readFile();
        // GenresBUS.showList();
        // System.out.println("------------------------AFTER(GENRES)-----------------------");
        // System.out.println("------------------------AFTER(BOOK_TYPE)-----------------------");
        // testList.readFile();
        // TypesBUS.showList();
        // System.out.println("------------------------AFTER(BOOK_TYPE)-----------------------");
        // System.out.println("------------------------AFTER(MID)-----------------------");
        // testArray.readFile();
        // MidForBooksBUS.showList();
        // System.out.println("------------------------AFTER(MID)-----------------------");
        // System.out.println("------------------------AFTER(STA_TYPES)-----------------------");
        // newTest.readFile();
        // StaTypesBUS.showList();
        // System.out.println("------------------------AFTER(STA_TYPES)-----------------------");
        // System.out.println("------------------------AFTER(PUBLISHERS)-----------------------");
        // testPublishers.readFile();
        // PublishersBUS.showList();
        // System.out.println("------------------------AFTER(PUBLISHERS)-----------------------");
    }
}
