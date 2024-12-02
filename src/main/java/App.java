import java.io.IOException;
import java.math.BigDecimal;
import java.security.PublicKey;
import java.time.LocalDate;
import java.util.Scanner;
import BUS.BookFormatsBUS;
import BUS.BooksBUS;
import BUS.CustomersBUS;
import BUS.EmployeesBUS;
import BUS.GRNsBUS;
import BUS.GenresBUS;
import BUS.MidForBooksBUS;
import BUS.PublishersBUS;
import BUS.SaleEventsBUS;
import BUS.StaTypesBUS;
import BUS.StationeriesBUS;
import BUS.SuppliersBUS;
import BUS.TypesBUS;
import DTO.BookFormats;
import DTO.BookGenres;
import DTO.BookTypes;
import DTO.Books;
import DTO.Customers;
import DTO.Employees;
import DTO.GRNs;
import DTO.Publishers;
import DTO.SaleEvents;
import DTO.SaleEventsDetail;
import DTO.StaTypes;
import DTO.Stationeries;
import DTO.Suppliers;

public class App {
        Scanner input = new Scanner(System.in);
        public static void main(String[] args) throws IOException {
                // !INIT OBJ
                MidForBooksBUS midForBook = new MidForBooksBUS();
                GenresBUS genreList = new GenresBUS();
                TypesBUS bookTypeList = new TypesBUS();
                StaTypesBUS staTypeList = new StaTypesBUS();
                PublishersBUS publisherList = new PublishersBUS();
                BookFormatsBUS formatList = new BookFormatsBUS();
                StationeriesBUS stationaryList = new StationeriesBUS();
                BooksBUS booksList = new BooksBUS();
                CustomersBUS customerList = new CustomersBUS();
                EmployeesBUS employeeList = new EmployeesBUS();
                SuppliersBUS supplierList = new SuppliersBUS();
                SaleEventsBUS saleEventList = new SaleEventsBUS();
                // *READ FILE
                genreList.readFile();
                bookTypeList.readFile();
                publisherList.readFile();
                formatList.readFile();
                supplierList.readFile();
                booksList.readFile();
                midForBook.readFile();
                customerList.readFile();
                employeeList.readFile();
                staTypeList.readFile();
                stationaryList.readFile();
                saleEventList.readFile();

                employeeList.showList();
        }
}