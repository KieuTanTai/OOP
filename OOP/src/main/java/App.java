import java.io.IOException;

import BUS.GenresBUS;
import BUS.MidForBooksBUS;
import BUS.StaTypesBUS;
import BUS.TypesBUS;

public class App {
    public static void main(String[] args) throws IOException {
        // !INIT ARRAY

        // !INIT VALUE
        
        // !INIT OBJ
        MidForBooksBUS testArray = new MidForBooksBUS();
        GenresBUS initList = new GenresBUS();
        TypesBUS testList = new TypesBUS();
        StaTypesBUS newTest = new StaTypesBUS();

        // !SHOW BEFORE
        // System.out.println("------------------------BEFORE-----------------------");
        // StaTypesBUS.showList();
        // System.out.println("------------------------BEFORE-----------------------");

        // !TEST METHODS
        
        // !SHOW DURING
        // System.out.println("------------------------DURING-----------------------");
        // StaTypesBUS.showList();
        // System.out.println("------------------------DURING-----------------------");

        // !SHOW RESULT
        
        System.out.println("------------------------AFTER(GENRES)-----------------------");
        initList.readFile();
        GenresBUS.showList();
        System.out.println("------------------------AFTER(GENRES)-----------------------");
        System.out.println("------------------------AFTER(BOOK_TYPE)-----------------------");
        testList.readFile();
        TypesBUS.showList();
        System.out.println("------------------------AFTER(BOOK_TYPE)-----------------------");
        System.out.println("------------------------AFTER(MID)-----------------------");
        testArray.readFile();
        MidForBooksBUS.showList();
        System.out.println("------------------------AFTER(MID)-----------------------");
        System.out.println("------------------------AFTER(STA_TYPES)-----------------------");
        newTest.readFile();
        StaTypesBUS.showList();
        System.out.println("------------------------AFTER(STA_TYPES)-----------------------");
    }
}
