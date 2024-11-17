import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;

import BUS.BooksBUS;
import BUS.GenresBUS;
import BUS.MidForBooksBUS;
import BUS.PublishersBUS;
import BUS.StaTypesBUS;
import BUS.TypesBUS;
import DTO.Books;
import util.Validate;

public class App {
    public static void main(String[] args) throws IOException {
        // !INIT OBJ
        MidForBooksBUS testArray = new MidForBooksBUS();
        GenresBUS initList = new GenresBUS();
        TypesBUS testList = new TypesBUS();
        StaTypesBUS newTest = new StaTypesBUS();
        PublishersBUS testPublishers = new PublishersBUS();

        Scanner input = new Scanner(System.in);
        initList.readFile();
        testList.readFile();
        testArray.readFile();
        newTest.readFile();
        testPublishers.readFile();

        // !INIT ARRAY
        String[] formats = { "Hardcover", "Paperback", "Leather-bound" };
        Books[] books = {
                new Books("BK001PD", "Cuộc Đời Kỳ Diệu", LocalDate.of(2024, 2, 15), new BigDecimal("120000"), 100,
                        PublishersBUS.getPublishersList()[0], "Nguyễn Văn A", TypesBUS.getTypesList()[1], formats[0],
                        "30x20 cm", GenresBUS.getGenres(0, 2)),
                new Books("BK002PD", "Khám Phá Vũ Trụ", LocalDate.of(2024, 5, 3), new BigDecimal("150000"), 80,
                        PublishersBUS.getPublishersList()[1], "Trần Thị B", TypesBUS.getTypesList()[3], formats[1],
                        "20x15 cm", GenresBUS.getGenres(3, 5)),
                new Books("BK003PD", "Lịch Sử Dân Tộc Việt Nam", LocalDate.of(2024, 3, 18), new BigDecimal("200000"),
                        50, PublishersBUS.getPublishersList()[2], "Lê Minh C", TypesBUS.getTypesList()[11], formats[2],
                        "25x18 cm", GenresBUS.getGenres(6, 8)),
                new Books("BK004PD", "Mười Cuộc Phiêu Lưu", LocalDate.of(2024, 4, 20), new BigDecimal("110000"), 120,
                        PublishersBUS.getPublishersList()[3], "Phan Quốc D", TypesBUS.getTypesList()[12], formats[0],
                        "22x17 cm", GenresBUS.getGenres(9, 11)),
                new Books("BK005PD", "Nghệ Thuật Lãnh Đạo", LocalDate.of(2024, 6, 10), new BigDecimal("180000"), 70,
                        PublishersBUS.getPublishersList()[4], "Nguyễn Thái E", TypesBUS.getTypesList()[5], formats[1],
                        "30x20 cm", GenresBUS.getGenres(12, 14)),
                new Books("BK006PD", "Trí Tuệ Nhân Tạo", LocalDate.of(2024, 7, 22), new BigDecimal("250000"), 60,
                        PublishersBUS.getPublishersList()[5], "Đặng Minh F", TypesBUS.getTypesList()[21], formats[2],
                        "25x18 cm", GenresBUS.getGenres(15, 17)),
                new Books("BK007PD", "Tâm Lý Học Xã Hội", LocalDate.of(2024, 8, 30), new BigDecimal("220000"), 55,
                        PublishersBUS.getPublishersList()[6], "Hà Lan G", TypesBUS.getTypesList()[2], formats[0],
                        "20x15 cm", GenresBUS.getGenres(18, 20)),
                new Books("BK008PD", "Bí Mật Tâm Hồn", LocalDate.of(2024, 9, 12), new BigDecimal("170000"), 110,
                        PublishersBUS.getPublishersList()[9], "Nguyễn Hoàng H", TypesBUS.getTypesList()[17], formats[1],
                        "22x17 cm", GenresBUS.getGenres(0, 3)),
                new Books("BK009PD", "Khám Phá Thế Giới Kỳ Bí", LocalDate.of(2024, 10, 5), new BigDecimal("130000"), 90,
                        PublishersBUS.getPublishersList()[2], "Vũ Minh I", TypesBUS.getTypesList()[3], formats[2],
                        "28x20 cm", GenresBUS.getGenres(4, 7)),
                new Books("BK010PD", "Chuyện Của Một Đời Người", LocalDate.of(2024, 11, 1), new BigDecimal("160000"),
                        95, PublishersBUS.getPublishersList()[1], "Nguyễn Văn K", TypesBUS.getTypesList()[22],
                        formats[0], "25x18 cm", GenresBUS.getGenres(8, 11)),
                new Books("BK011PD", "Đi Để Trở Lại", LocalDate.of(2024, 5, 10), new BigDecimal("140000"), 65,
                        PublishersBUS.getPublishersList()[10], "Lê Thanh L", TypesBUS.getTypesList()[9], formats[1],
                        "30x20 cm", GenresBUS.getGenres(12, 14)),
                new Books("BK012PD", "Điều Kỳ Diệu Trong Cuộc Sống", LocalDate.of(2024, 3, 2), new BigDecimal("190000"),
                        100, PublishersBUS.getPublishersList()[11], "Trần Đức M", TypesBUS.getTypesList()[12],
                        formats[2], "22x17 cm", GenresBUS.getGenres(15, 17)),
                new Books("BK013PD", "Lý Thuyết Tâm Lý", LocalDate.of(2024, 7, 18), new BigDecimal("210000"), 75,
                        PublishersBUS.getPublishersList()[1], "Phan Quỳnh N", TypesBUS.getTypesList()[8], formats[0],
                        "28x20 cm", GenresBUS.getGenres(18, 20)),
                new Books("BK014PD", "Những Người Khác Trong Tâm Hồn", LocalDate.of(2024, 9, 9),
                        new BigDecimal("160000"), 80, PublishersBUS.getPublishersList()[13], "Nguyễn Phương O",
                        TypesBUS.getTypesList()[11], formats[1], "25x18 cm", GenresBUS.getGenres(0, 2)),
                new Books("BK015PD", "Sức Mạnh Của Sự Tự Tin", LocalDate.of(2024, 12, 21), new BigDecimal("250000"), 50,
                        PublishersBUS.getPublishersList()[4], "Hà Nguyễn P", TypesBUS.getTypesList()[13], formats[2],
                        "20x15 cm", GenresBUS.getGenres(3, 5)),
                new Books("BK016PD", "Cuộc Chiến Trong Nội Tâm", LocalDate.of(2024, 1, 15), new BigDecimal("120000"),
                        95, PublishersBUS.getPublishersList()[12], "Bùi Minh Q", TypesBUS.getTypesList()[16],
                        formats[0], "28x20 cm", GenresBUS.getGenres(6, 8)),
                new Books("BK017PD", "Công Nghệ 4.0", LocalDate.of(2024, 8, 10), new BigDecimal("230000"), 85,
                        PublishersBUS.getPublishersList()[16], "Trương Minh R", TypesBUS.getTypesList()[12], formats[1],
                        "22x17 cm", GenresBUS.getGenres(9, 11)),
                new Books("BK018PD", "Chinh Phục Đỉnh Cao", LocalDate.of(2024, 4, 15), new BigDecimal("175000"), 100,
                        PublishersBUS.getPublishersList()[7], "Nguyễn Thành S", TypesBUS.getTypesList()[19], formats[2],
                        "30x20 cm", GenresBUS.getGenres(12, 14)),
                new Books("BK019PD", "Tìm Kiếm Những Vùng Đất Mới", LocalDate.of(2024, 6, 5), new BigDecimal("195000"),
                        60, PublishersBUS.getPublishersList()[14], "Nguyễn Văn A", TypesBUS.getTypesList()[21],
                        formats[0], "25x18 cm", GenresBUS.getGenres(15, 17)),
                new Books("BK020PD", "Lựa Chọn Cuộc Đời", LocalDate.of(2024, 10, 25), new BigDecimal("170000"), 110,
                        PublishersBUS.getPublishersList()[11], "Lê Anh U", TypesBUS.getTypesList()[1], formats[1],
                        "20x15 cm", GenresBUS.getGenres(18, 20)),
                new Books("BK021PD", "Thế Giới Phẳng", LocalDate.of(2024, 2, 20), new BigDecimal("150000"), 80,
                        PublishersBUS.getPublishersList()[0], "Nguyễn Văn A", TypesBUS.getTypesList()[5], formats[1],
                        "25x18 cm", GenresBUS.getGenres(9, 11)),
                new Books("BK022PD", "Cuộc Sống Mới", LocalDate.of(2024, 5, 10), new BigDecimal("120000"), 120,
                        PublishersBUS.getPublishersList()[1], "Trần Thị B", TypesBUS.getTypesList()[3], formats[0],
                        "20x15 cm", GenresBUS.getGenres(0, 2)),
                new Books("BK023PD", "Khoa Học Hành Tinh", LocalDate.of(2024, 4, 18), new BigDecimal("180000"), 50,
                        PublishersBUS.getPublishersList()[3], "Lê Minh C", TypesBUS.getTypesList()[2], formats[2],
                        "28x20 cm", GenresBUS.getGenres(3, 5)),
                new Books("BK024PD", "Nghệ Thuật Sống", LocalDate.of(2024, 6, 25), new BigDecimal("160000"), 90,
                        PublishersBUS.getPublishersList()[2], "Phan Quốc D", TypesBUS.getTypesList()[8], formats[1],
                        "22x17 cm", GenresBUS.getGenres(6, 8)),
                new Books("BK025PD", "Hành Trình Tâm Linh", LocalDate.of(2024, 8, 12), new BigDecimal("250000"), 65,
                        PublishersBUS.getPublishersList()[4], "Nguyễn Thái E", TypesBUS.getTypesList()[16], formats[2],
                        "30x20 cm", GenresBUS.getGenres(9, 11)),
                new Books("BK026PD", "Khám Phá Lòng Đất", LocalDate.of(2024, 9, 14), new BigDecimal("130000"), 75,
                        PublishersBUS.getPublishersList()[5], "Đặng Minh F", TypesBUS.getTypesList()[11], formats[0],
                        "28x20 cm", GenresBUS.getGenres(0, 3)),
                new Books("BK027PD", "Con Đường Thành Công", LocalDate.of(2024, 10, 3), new BigDecimal("140000"), 100,
                        PublishersBUS.getPublishersList()[6], "Hà Lan G", TypesBUS.getTypesList()[13], formats[1],
                        "25x18 cm", GenresBUS.getGenres(12, 14)),
                new Books("BK028PD", "Nghệ Thuật Tâm Lý", LocalDate.of(2024, 11, 22), new BigDecimal("170000"), 50,
                        PublishersBUS.getPublishersList()[9], "Nguyễn Hoàng H", TypesBUS.getTypesList()[17], formats[2],
                        "22x17 cm", GenresBUS.getGenres(13, 17)),
                new Books("BK029PD", "Cuộc Đua Với Thời Gian", LocalDate.of(2024, 12, 5), new BigDecimal("120000"), 85,
                        PublishersBUS.getPublishersList()[10], "Vũ Minh I", TypesBUS.getTypesList()[12], formats[0],
                        "20x15 cm", GenresBUS.getGenres(11, 20)),
                new Books("BK030PD", "Những Câu Chuyện Cổ Tích", LocalDate.of(2024, 1, 18), new BigDecimal("140000"),
                        100, PublishersBUS.getPublishersList()[11], "Nguyễn Văn K", TypesBUS.getTypesList()[2],
                        formats[1], "28x20 cm", GenresBUS.getGenres(0, 2)),
                new Books("BK031PD", "Khám Phá Thiên Nhiên", LocalDate.of(2024, 3, 22), new BigDecimal("150000"), 90,
                        PublishersBUS.getPublishersList()[4], "Lê Thanh L", TypesBUS.getTypesList()[5], formats[0],
                        "30x20 cm", GenresBUS.getGenres(3, 5)),
                new Books("BK032PD", "Cuộc Sống Thường Ngày", LocalDate.of(2024, 2, 28), new BigDecimal("170000"), 75,
                        PublishersBUS.getPublishersList()[3], "Trần Đức M", TypesBUS.getTypesList()[19], formats[1],
                        "25x18 cm", GenresBUS.getGenres(6, 8)),
                new Books("BK033PD", "Tư Duy Sáng Tạo", LocalDate.of(2024, 5, 3), new BigDecimal("200000"), 70,
                        PublishersBUS.getPublishersList()[1], "Phan Quỳnh N", TypesBUS.getTypesList()[21], formats[2],
                        "22x17 cm", GenresBUS.getGenres(9, 11)),
                new Books("BK034PD", "Vẻ Đẹp Văn Hóa Việt", LocalDate.of(2024, 6, 14), new BigDecimal("110000"), 120,
                        PublishersBUS.getPublishersList()[2], "Nguyễn Phương O", TypesBUS.getTypesList()[22],
                        formats[1], "20x15 cm", GenresBUS.getGenres(12, 14)),
                new Books("BK035PD", "Đường Lên Đỉnh Núi", LocalDate.of(2024, 9, 25), new BigDecimal("160000"), 60,
                        PublishersBUS.getPublishersList()[7], "Hà Nguyễn P", TypesBUS.getTypesList()[16], formats[0],
                        "28x20 cm", GenresBUS.getGenres(15, 17)),
                new Books("BK036PD", "Khám Phá Đại Dương", LocalDate.of(2024, 7, 10), new BigDecimal("175000"), 55,
                        PublishersBUS.getPublishersList()[0], "Bùi Minh Q", TypesBUS.getTypesList()[8], formats[2],
                        "22x17 cm", GenresBUS.getGenres(11, 20)),
                new Books("BK037PD", "Cuộc Phiêu Lưu Mới", LocalDate.of(2024, 10, 1), new BigDecimal("190000"), 100,
                        PublishersBUS.getPublishersList()[11], "Trương Minh R", TypesBUS.getTypesList()[13], formats[1],
                        "30x20 cm", GenresBUS.getGenres(0, 12)),
                new Books("BK038PD", "Khoa Học Và Đời Sống", LocalDate.of(2024, 8, 8), new BigDecimal("220000"), 95,
                        PublishersBUS.getPublishersList()[9], "Nguyễn Thành S", TypesBUS.getTypesList()[3], formats[0],
                        "25x18 cm", GenresBUS.getGenres(3, 5)),
                new Books("BK039PD", "Tìm Hiểu Về Con Người", LocalDate.of(2024, 4, 15), new BigDecimal("240000"), 65,
                        PublishersBUS.getPublishersList()[10], "Nguyễn Văn A", TypesBUS.getTypesList()[2], formats[1],
                        "20x15 cm", GenresBUS.getGenres(6, 9)),
                new Books("BK040PD", "Đi Qua Những Thử Thách", LocalDate.of(2024, 11, 11), new BigDecimal("150000"), 90,
                        PublishersBUS.getPublishersList()[1], "Nguyễn Văn K", TypesBUS.getTypesList()[9], formats[2],
                        "28x20 cm", GenresBUS.getGenres(3, 11)),
                new Books("BK041PD", "Hành Trình Khám Phá", LocalDate.of(2024, 2, 12), new BigDecimal("180000"), 120,
                        PublishersBUS.getPublishersList()[0], "Trần Thị B", TypesBUS.getTypesList()[11], formats[1],
                        "30x20 cm", GenresBUS.getGenres(10, 14)),
                new Books("BK042PD", "Bí Ẩn Của Tâm Hồn", LocalDate.of(2024, 7, 6), new BigDecimal("160000"), 55,
                        PublishersBUS.getPublishersList()[5], "Phan Quốc D", TypesBUS.getTypesList()[5], formats[2],
                        "22x17 cm", GenresBUS.getGenres(13, 17)),
                new Books("BK043PD", "Công Nghệ Tương Lai", LocalDate.of(2024, 5, 28), new BigDecimal("220000"), 75,
                        PublishersBUS.getPublishersList()[6], "Nguyễn Hoàng H", TypesBUS.getTypesList()[13], formats[0],
                        "25x18 cm", GenresBUS.getGenres(14, 20)),
                new Books("BK044PD", "Lịch Sử Và Tương Lai", LocalDate.of(2024, 6, 18), new BigDecimal("190000"), 70,
                        PublishersBUS.getPublishersList()[3], "Đặng Minh F", TypesBUS.getTypesList()[19], formats[1],
                        "28x20 cm", GenresBUS.getGenres(0, 8)),
                new Books("BK045PD", "Hành Tinh Xanh", LocalDate.of(2024, 4, 9), new BigDecimal("175000"), 80,
                        PublishersBUS.getPublishersList()[11], "Lê Minh C", TypesBUS.getTypesList()[21], formats[2],
                        "20x15 cm", GenresBUS.getGenres(3, 9)),
                new Books("BK046PD", "Hành Trình Đến Thành Công", LocalDate.of(2024, 9, 23), new BigDecimal("250000"),
                        100, PublishersBUS.getPublishersList()[10], "Nguyễn Văn K", TypesBUS.getTypesList()[22],
                        formats[0], "30x20 cm", GenresBUS.getGenres(6, 8)),
                new Books("BK047PD", "Hành Động Nhỏ Thay Đổi Lớn", LocalDate.of(2024, 12, 8), new BigDecimal("130000"),
                        90, PublishersBUS.getPublishersList()[7], "Hà Lan G", TypesBUS.getTypesList()[16], formats[1],
                        "28x20 cm", GenresBUS.getGenres(9, 11)),
                new Books("BK048PD", "Kỳ Quan Thiên Nhiên", LocalDate.of(2024, 3, 3), new BigDecimal("240000"), 95,
                        PublishersBUS.getPublishersList()[4], "Phan Thái E", TypesBUS.getTypesList()[8], formats[2],
                        "25x18 cm", GenresBUS.getGenres(0, 14)),
                new Books("BK049PD", "Hành Tinh Nóng Lên", LocalDate.of(2024, 1, 30), new BigDecimal("170000"), 65,
                        PublishersBUS.getPublishersList()[0], "Nguyễn Phương O", TypesBUS.getTypesList()[2], formats[0],
                        "22x17 cm", GenresBUS.getGenres(13, 17)),
                new Books("BK050PD", "Thử Thách Trong Cuộc Sống", LocalDate.of(2024, 11, 7), new BigDecimal("200000"),
                        55, PublishersBUS.getPublishersList()[9], "Trương Minh R", TypesBUS.getTypesList()[9],
                        formats[1], "30x20 cm", GenresBUS.getGenres(14, 20))
        };

        // !INIT VALUE
        Books book1 = books[0];
        Books book2 = books[1];
        Books book3 = books[2];
        Books book4 = books[3];
        Books book5 = books[4];
        Books book6 = books[5];
        Books book7 = books[6];
        Books book8 = books[7];
        Books book9 = books[8];
        Books book10 = books[9];
        Books book11 = books[10];
        Books book12 = books[11];
        Books book13 = books[12];
        Books book14 = books[13];
        Books book15 = books[14];
        Books book16 = books[15];
        Books book17 = books[16];
        Books book18 = books[17];
        Books book19 = books[18];
        Books book20 = books[19];
        Books book21 = books[20];
        Books book22 = books[21];
        Books book23 = books[22];
        Books book24 = books[23];
        Books book25 = books[24];
        Books book26 = books[25];
        Books book27 = books[26];
        Books book28 = books[27];
        Books book29 = books[28];
        Books book30 = books[29];
        Books book31 = books[30];
        Books book32 = books[31];
        Books book33 = books[32];
        Books book34 = books[33];
        Books book35 = books[34];
        Books book36 = books[35];
        Books book37 = books[36];
        Books book38 = books[37];
        Books book39 = books[38];
        Books book40 = books[39];
        Books book41 = books[40];
        Books book42 = books[41];
        Books book43 = books[42];
        Books book44 = books[43];
        Books book45 = books[44];
        Books book46 = books[45];
        Books book47 = books[46];
        Books book48 = books[47];
        Books book49 = books[48];
        Books book50 = books[49];

        BooksBUS listBooks = new BooksBUS(books, 50);
        // !SHOW BEFORE
        // System.out.println("------------------------BEFORE-----------------------");
        // GenresBUS.showList();
        // System.out.println("------------------------BEFORE-----------------------");

        // !TEST METHODS
        listBooks.advancedSearch("12", "Nguyễn", "authMonth");

        // !SHOW DURING
        // System.out.println("------------------------DURING-----------------------");
        // MidForBooksBUS.showList();
        // System.out.println("------------------------DURING-----------------------");

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
