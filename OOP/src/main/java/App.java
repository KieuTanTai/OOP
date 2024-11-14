import util.Validate;
import java.io.IOException;

import BUS.GenresBUS;
import BUS.MidForBooksBUS;
import BUS.TypesBUS;;

public class App {
    public static void main(String[] args) throws IOException {
        // !INIT ARRAY
        

        // !INIT VALUE


        // !TEST METHODS
        MidForBooksBUS testArray = new MidForBooksBUS();
        GenresBUS initList = new GenresBUS();
        TypesBUS testList = new TypesBUS();


        // !SHOW RESULT
        System.out.println("-----------------------------------------------");
        initList.readFile();
        GenresBUS.showList();
        System.out.println("-----------------------------------------------");
        testList.readFile();
        TypesBUS.showList();
        System.out.println("-----------------------------------------------");
        testArray.readFile();
        MidForBooksBUS.showList();
        System.out.println("-----------------------------------------------");

        // !SHOW RESULT
    }
}
