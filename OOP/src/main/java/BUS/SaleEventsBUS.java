package BUS;

import DTO.SaleEvents;
import DTO.SaleEventsDetail;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class SaleEventsBUS {
    private static SaleEvents[] ListSaleEvent;
    private static int count;
    Scanner sc = new Scanner(System.in);

    public SaleEventsBUS(SaleEvents[] listSaleEvent, int count) {
        ListSaleEvent = listSaleEvent;
        this.count = count;
    }

    public void nhap(){

    }

    public void add(SaleEvents saleEvents){
        count++;
        ListSaleEvent = Arrays.copyOf(ListSaleEvent, count);
        ListSaleEvent[count-1] = saleEvents;
    }

    public void update(String id){
        for (int i = 0 ; i < count ; i++)
            if (ListSaleEvent[i].getSaleEvId() == id)
                ListSaleEvent[i].nhap();
    }

    public boolean delete(int vt) {
        if (vt >= count)
            return false;

        count--;
        for (int x=vt; x<count; x++)
            ListSaleEvent[x] = ListSaleEvent[x+1];
        ListSaleEvent[count] = null;
        ListSaleEvent = Arrays.copyOf(ListSaleEvent, count);
        return true;
    }

    public SaleEvents findById(String  id){
        SaleEvents result = new SaleEvents();

        for (int i = 0 ; i < count ; i++){
            if (ListSaleEvent[i].getSaleEvId() == id){
                result = ListSaleEvent[i];
            }
        }
        return  result;
    }

    public SaleEvents findByName(String name){
        SaleEvents result = new SaleEvents();

        for (int i=0; i < count; i++){
            if (ListSaleEvent[i].getSaleEvName() == name)
                result = ListSaleEvent[i];

        }
        return result;
    }

    public SaleEvents[] findByStartDate(String start) {
        try {
            // Chuyển đổi chuỗi start thành LocalDate
            LocalDate startDate = LocalDate.parse(start);

            SaleEvents[] tempResult = new SaleEvents[ListSaleEvent.length];
            int temp = 0;

            // Duyệt qua danh sách SaleEvents
            for (SaleEvents event : ListSaleEvent) {
                if (event.getStartDate().equals(startDate)) {
                    tempResult[temp++] = event; // Thêm sự kiện phù hợp vào mảng tạm
                }
            }

            // Tạo mảng kết quả với kích thước chính xác
            return Arrays.copyOf(tempResult, temp);

        } catch (DateTimeParseException e) {
            System.out.println("Ngày không hợp lệ: " + start);
        }
        return new SaleEvents[0]; // Trả về mảng rỗng nếu không tìm thấy
    }


    public SaleEvents[] findByEndDate(String end) {
        try {
            // Chuyển đổi chuỗi start thành LocalDate
            LocalDate startDate = LocalDate.parse(end);

            SaleEvents[] tempResult = new SaleEvents[ListSaleEvent.length];
            int temp = 0;

            // Duyệt qua danh sách SaleEvents
            for (SaleEvents event : ListSaleEvent) {
                if (event.getStartDate().equals(startDate)) {
                    tempResult[temp++] = event; // Thêm sự kiện phù hợp vào mảng tạm
                }
            }

            // Tạo mảng kết quả với kích thước chính xác
            return Arrays.copyOf(tempResult, temp);

        } catch (DateTimeParseException e) {
            System.out.println("Ngày không hợp lệ: " + end);
        }
        return new SaleEvents[0]; // Trả về mảng rỗng nếu không tìm thấy
    }


    public SaleEvents[] findByDateRange(String start, String end) {
        try {
            // Chuyển đổi chuỗi ngày sang LocalDate
            LocalDate startDate = LocalDate.parse(start);
            LocalDate endDate = LocalDate.parse(end);

            // Tạo mảng tạm với kích thước bằng số lượng sự kiện
            SaleEvents[] tempResult = new SaleEvents[ListSaleEvent.length];
            int count = 0;

            // Duyệt qua danh sách SaleEvent
            for (SaleEvents event : ListSaleEvent) {
                // Kiểm tra nếu sự kiện nằm trong khoảng thời gian
                if ((event.getStartDate().isEqual(startDate) || event.getStartDate().isAfter(startDate)) &&
                        (event.getEndDate().isEqual(endDate) || event.getEndDate().isBefore(endDate))) {
                    tempResult[count++] = event; // Thêm sự kiện vào mảng tạm và tăng biến đếm
                }
            }

            // Sao chép các sự kiện phù hợp vào mảng kết quả
            SaleEvents[] result = Arrays.copyOf(tempResult, count);
            return result; // Trả về mảng kết quả
        } catch (DateTimeParseException e) {
            System.out.println("Ngày không hợp lệ: " + e.getMessage());
        }
        return new SaleEvents[0]; // Trả về mảng rỗng nếu có lỗi
    }




    public static void docFile(String nameFile) {
        try (FileReader file = new FileReader(nameFile);
             BufferedReader buffer = new BufferedReader(file)) {

            count = 0;
            ListSaleEvent = new SaleEvents[1]; // Khởi tạo mảng ban đầu với kích thước 1
            String[] data = new String[10]; // Dữ liệu cần đọc từ file

            String line = buffer.readLine(); // Đọc tiêu đề hoặc dòng mở đầu nếu có
            System.out.println(line); // In tiêu đề

            while (true) {
                // Đọc các thuộc tính của SaleEvent
                data[0] = buffer.readLine(); // saleEvId
                if (data[0] == null) // Kiểm tra EOF
                    break;

                count++;
                ListSaleEvent = Arrays.copyOf(ListSaleEvent, count); // Mở rộng mảng saleEvents

                data[1] = buffer.readLine(); // saleEvName
                data[2] = buffer.readLine(); // description
                data[3] = buffer.readLine(); // startDate
                data[4] = buffer.readLine(); // endDate

                // Đọc các thuộc tính của SaleEventsDetail
                data[5] = buffer.readLine(); // saleEvId (detail)
                data[6] = buffer.readLine(); // promoCode
                data[7] = buffer.readLine(); // minPrice
                data[8] = buffer.readLine(); // discount
                data[9] = buffer.readLine(); // maxPriceDiscount

                // Tạo đối tượng SaleEventsDetail
                SaleEventsDetail detail = new SaleEventsDetail();
                detail.setSaleEvId(data[5]);
                detail.setPromoCode(data[6]);
                detail.setMinPrice(new BigDecimal(data[7]));
                detail.setDiscount(new BigDecimal(data[8]));
                detail.setMaxPriceDiscount(new BigDecimal(data[9]));

                // Tạo đối tượng SaleEvent
                SaleEvents saleEvent = new SaleEvents();
                saleEvent.setSaleEvId(data[0]);
                saleEvent.setSaleEvName(data[1]);
                saleEvent.setDescription(data[2]);
                saleEvent.setStartDate(LocalDate.parse(data[3]));
                saleEvent.setEndDate(LocalDate.parse(data[4]));
                saleEvent.setDetail(detail);

                // Thêm vào mảng
                ListSaleEvent[count- 1] = saleEvent;
            }
        } catch (IOException ex) {
            System.out.println("Lỗi khi mở file: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Lỗi dữ liệu: " + ex.getMessage());
        }
    }


    public void ghiFile(String nameFile) {
        try {
            FileWriter file = new FileWriter(nameFile);
            BufferedWriter buffer = new BufferedWriter(file);

            for (SaleEvents event : ListSaleEvent) {
                // Ghi thông tin SaleEvent
                buffer.write(event.getSaleEvId());
                buffer.newLine();
                buffer.write(event.getSaleEvName());
                buffer.newLine();
                buffer.write(event.getDescription());
                buffer.newLine();
                buffer.write(event.getStartDate().toString());
                buffer.newLine();
                buffer.write(event.getEndDate().toString());
                buffer.newLine();

                // Ghi thông tin SaleEventsDetail
                SaleEventsDetail detail = event.getDetail();
                buffer.write(detail.getSaleEvId());
                buffer.newLine();
                buffer.write(detail.getPromoCode());
                buffer.newLine();
                buffer.write(detail.getMinPrice().toString());
                buffer.newLine();
                buffer.write(detail.getDiscount().toString());
                buffer.newLine();
                buffer.write(detail.getMaxPriceDiscount().toString());
                buffer.newLine();
            }

            // Đóng tài nguyên
            buffer.close();
            file.close();
        } catch (IOException e) {
            System.out.println("Lỗi khi mở file: " + e.getMessage());
        }
    }




}