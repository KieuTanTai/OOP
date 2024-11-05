import Manager.Menu;
import DTO.*;

import java.math.BigDecimal;
import java.time.LocalDate;

import BUS.*;
public class App {
    public static void main(String[] args) {
        BookTypes type = new BookTypes("T001", "hello");
        BookTypes typeZ = new BookTypes("T002", "hello2");
        BooksBUS temp = new BooksBUS();
        TypesBUS temp1 = new TypesBUS();
        temp1.add(type);
        temp1.add(typeZ);
        temp1.showList();
        Products book1 = new Books();

    }
}
