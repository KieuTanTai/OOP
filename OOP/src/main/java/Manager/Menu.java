package Manager;

import java.util.Scanner;

import BUS.GenresBUS;
import BUS.MidForBooksBUS;
import BUS.PublishersBUS;
import BUS.StaTypesBUS;
import BUS.TypesBUS;

public class Menu {
    Scanner input = new Scanner(System.in);

    public Menu() {
        // create static fields
        new TypesBUS();
        new GenresBUS();
        new StaTypesBUS();
        new PublishersBUS();
        new MidForBooksBUS();

        // methods handler();
        addHandler();
        findHandler();
        searchHandler();
        removeHandler();
        editHandler();

    }

    /*----- methods for only Books -----*/


    public static void addHandler() {
    }

    public static void findHandler() {
    }

    public static void searchHandler() {
    }

    public static void removeHandler() {
    }

    public static void editHandler() {
    }

}
