import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;

import BUS.BooksBUS;
import BUS.GenresBUS;
import BUS.MidForBooksBUS;
import BUS.PublishersBUS;
import BUS.StaTypesBUS;
import BUS.TypesBUS;
import DTO.Books;

public class App {
    public static void main(String[] args) throws IOException {
        // !INIT OBJ
        MidForBooksBUS testArray = new MidForBooksBUS();
        GenresBUS initList = new GenresBUS();
        TypesBUS testList = new TypesBUS();
        StaTypesBUS newTest = new StaTypesBUS();
        PublishersBUS testPublishers = new PublishersBUS();
        BooksBUS listBooks = new BooksBUS();

        initList.readFile();
        testList.readFile();
        testArray.readFile();
        newTest.readFile();
        testPublishers.readFile();

        // !INIT ARRAY
        String[] formats = { "Hardcover", "Paperback", "Leather-bound" };
        Books[] books = {
            new Books("BK001PD", "Cuộc Đời Kỳ Diệu", LocalDate.of(2024, 2, 15), new BigDecimal("120000"), 100, PublishersBUS.getPublishersList()[0], "Nguyễn Văn A", TypesBUS.getTypesList()[1], formats[0], "30x20 cm", GenresBUS.getGenres(0, 2)),
            new Books("BK002PD", "Khám Phá Vũ Trụ", LocalDate.of(2024, 5, 3), new BigDecimal("150000"), 80, PublishersBUS.getPublishersList()[1], "Trần Thị B", TypesBUS.getTypesList()[3], formats[1], "20x15 cm", GenresBUS.getGenres(3, 5)),
            new Books("BK003PD", "Lịch Sử Dân Tộc Việt Nam", LocalDate.of(2024, 3, 18), new BigDecimal("200000"), 50, PublishersBUS.getPublishersList()[2], "Lê Minh C", TypesBUS.getTypesList()[11], formats[2], "25x18 cm", GenresBUS.getGenres(6, 8)),
            new Books("BK004PD", "Mười Cuộc Phiêu Lưu", LocalDate.of(2024, 4, 20), new BigDecimal("110000"), 120, PublishersBUS.getPublishersList()[3], "Phan Quốc D", TypesBUS.getTypesList()[12], formats[0], "22x17 cm", GenresBUS.getGenres(9, 11)),
            new Books("BK005PD", "Nghệ Thuật Lãnh Đạo", LocalDate.of(2024, 6, 10), new BigDecimal("180000"), 70, PublishersBUS.getPublishersList()[4], "Nguyễn Thái E", TypesBUS.getTypesList()[5], formats[1], "30x20 cm", GenresBUS.getGenres(12, 14)),
            new Books("BK006PD", "Trí Tuệ Nhân Tạo", LocalDate.of(2024, 7, 22), new BigDecimal("250000"), 60, PublishersBUS.getPublishersList()[5], "Đặng Minh F", TypesBUS.getTypesList()[21], formats[2], "25x18 cm", GenresBUS.getGenres(15, 17)),
            new Books("BK007PD", "Tâm Lý Học Xã Hội", LocalDate.of(2024, 8, 30), new BigDecimal("220000"), 55, PublishersBUS.getPublishersList()[6], "Hà Lan G", TypesBUS.getTypesList()[2], formats[0], "20x15 cm", GenresBUS.getGenres(18, 20)),
            new Books("BK008PD", "Bí Mật Tâm Hồn", LocalDate.of(2024, 9, 12), new BigDecimal("170000"), 110, PublishersBUS.getPublishersList()[7], "Nguyễn Hoàng H", TypesBUS.getTypesList()[17], formats[1], "22x17 cm", GenresBUS.getGenres(0, 3)),
            new Books("BK009PD", "Khám Phá Thế Giới Kỳ Bí", LocalDate.of(2024, 10, 5), new BigDecimal("130000"), 90, PublishersBUS.getPublishersList()[2], "Vũ Minh I", TypesBUS.getTypesList()[3], formats[2], "28x20 cm", GenresBUS.getGenres(4, 7)),
            new Books("BK010PD", "Chuyện Của Một Đời Người", LocalDate.of(2024, 11, 1), new BigDecimal("160000"), 95, PublishersBUS.getPublishersList()[1], "Nguyễn Văn K", TypesBUS.getTypesList()[22], formats[0], "25x18 cm", GenresBUS.getGenres(8, 11)),
            new Books("BK011PD", "Đi Để Trở Lại", LocalDate.of(2024, 5, 10), new BigDecimal("140000"), 65, PublishersBUS.getPublishersList()[10], "Lê Thanh L", TypesBUS.getTypesList()[9], formats[1], "30x20 cm", GenresBUS.getGenres(12, 14)),
            new Books("BK012PD", "Điều Kỳ Diệu Trong Cuộc Sống", LocalDate.of(2024, 3, 2), new BigDecimal("190000"), 100, PublishersBUS.getPublishersList()[11], "Trần Đức M", TypesBUS.getTypesList()[12], formats[2], "22x17 cm", GenresBUS.getGenres(15, 17)),
            new Books("BK013PD", "Lý Thuyết Tâm Lý", LocalDate.of(2024, 7, 18), new BigDecimal("210000"), 75, PublishersBUS.getPublishersList()[1], "Phan Quỳnh N", TypesBUS.getTypesList()[8], formats[0], "28x20 cm", GenresBUS.getGenres(18, 20)),
            new Books("BK014PD", "Những Người Khác Trong Tâm Hồn", LocalDate.of(2024, 9, 9), new BigDecimal("160000"), 80, PublishersBUS.getPublishersList()[13], "Nguyễn Phương O", TypesBUS.getTypesList()[11], formats[1], "25x18 cm", GenresBUS.getGenres(0, 2)),
            new Books("BK015PD", "Sức Mạnh Của Sự Tự Tin", LocalDate.of(2024, 12, 21), new BigDecimal("250000"), 50, PublishersBUS.getPublishersList()[4], "Hà Nguyễn P", TypesBUS.getTypesList()[13], formats[2], "20x15 cm", GenresBUS.getGenres(3, 5)),
            new Books("BK016PD", "Cuộc Chiến Trong Nội Tâm", LocalDate.of(2024, 1, 15), new BigDecimal("120000"), 95, PublishersBUS.getPublishersList()[12], "Bùi Minh Q", TypesBUS.getTypesList()[16], formats[0], "28x20 cm", GenresBUS.getGenres(6, 8)),
            new Books("BK017PD", "Công Nghệ 4.0", LocalDate.of(2024, 8, 10), new BigDecimal("230000"), 85, PublishersBUS.getPublishersList()[16], "Trương Minh R", TypesBUS.getTypesList()[12], formats[1], "22x17 cm", GenresBUS.getGenres(9, 11)),
            new Books("BK018PD", "Chinh Phục Đỉnh Cao", LocalDate.of(2024, 4, 15), new BigDecimal("175000"), 100, PublishersBUS.getPublishersList()[7], "Nguyễn Thành S", TypesBUS.getTypesList()[19], formats[2], "30x20 cm", GenresBUS.getGenres(12, 14)),
            new Books("BK019PD", "Tìm Kiếm Những Vùng Đất Mới", LocalDate.of(2024, 6, 5), new BigDecimal("195000"), 60, PublishersBUS.getPublishersList()[14], "Ngô Minh T", TypesBUS.getTypesList()[21], formats[0], "25x18 cm", GenresBUS.getGenres(15, 17)),
            new Books("BK020PD", "Lựa Chọn Cuộc Đời", LocalDate.of(2024, 10, 25), new BigDecimal("170000"), 110, PublishersBUS.getPublishersList()[11], "Lê Anh U", TypesBUS.getTypesList()[1], formats[1], "20x15 cm", GenresBUS.getGenres(18, 20))
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

        // !SHOW BEFORE
        System.out.println("------------------------BEFORE-----------------------");
        MidForBooksBUS.showList();
        System.out.println("------------------------BEFORE-----------------------");

        // !TEST METHODS
        // book1.setInfo();
        // book1.showInfo();
        listBooks.add(books, 20);
        listBooks.search("BK001PD");

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
