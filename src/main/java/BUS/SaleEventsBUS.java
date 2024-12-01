package BUS;

import DTO.SaleEvents;
import DTO.SaleEventsDetail;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Scanner;

public class SaleEventsBUS {
    private SaleEvents[] ListSaleEvent;
    private int count;
    Scanner sc = new Scanner(System.in);

    // constructors
    public SaleEventsBUS() {
        this.count = 0;
        this.ListSaleEvent = new SaleEvents[0];
    }

    public SaleEventsBUS(SaleEvents[] listSaleEvent, int count) {
        ListSaleEvent = listSaleEvent;
        this.count = count;
    }

    // getter / setter
    public SaleEvents[] getListSaleEvent() {
        return this.ListSaleEvent;
    }

    public int getCount() {
        return this.count;
    }

    public void setListSaleEvent(SaleEvents[] list) {
        this.ListSaleEvent = list;
    }

    public void setCount(int count) {
        this.count = count;
    }

    // other methods like add, remove, find, search, ......
    public void add(SaleEvents saleEvents) {
        count++;
        ListSaleEvent = Arrays.copyOf(ListSaleEvent, count);
        ListSaleEvent[count - 1] = saleEvents;
    }

    public void update(String id) {
        for (int i = 0; i < count; i++)
            if (ListSaleEvent[i].getSaleEvId() == id)
                ListSaleEvent[i].setInfo();
    }

    public boolean delete(int vt) {
        if (vt >= count)
            return false;

        count--;
        for (int x = vt; x < count; x++)
            ListSaleEvent[x] = ListSaleEvent[x + 1];
        ListSaleEvent[count] = null;
        ListSaleEvent = Arrays.copyOf(ListSaleEvent, count);
        return true;
    }

    public SaleEvents findById(String id) {
        for (int i = 0; i < count; i++)
            if (ListSaleEvent[i].getSaleEvId() == id)
                return ListSaleEvent[i];
        System.out.println("not found any sale event!");
        return null;
    }

    public SaleEvents findByPromoCode(String promoCode) {
        for (SaleEvents sale : ListSaleEvent)
            if (sale.getDetail().getPromoCode().equals(promoCode))
                return sale;
        System.out.println("not found any sale event!");
        return null;
    }

    public SaleEvents findByName(String name) {
        SaleEvents result = new SaleEvents();

        for (int i = 0; i < count; i++) {
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
            LocalDate startDate = LocalDate.parse(end);

            SaleEvents[] tempResult = new SaleEvents[ListSaleEvent.length];
            int temp = 0;

            for (SaleEvents event : ListSaleEvent) {
                if (event.getStartDate().equals(startDate)) {
                    tempResult[temp++] = event;
                }
            }

            return Arrays.copyOf(tempResult, temp);

        } catch (DateTimeParseException e) {
            System.out.println("Ngày không hợp lệ: " + end);
        }
        return new SaleEvents[0];
    }

    public SaleEvents[] findByDateRange(LocalDate date) {
        SaleEvents[] tempResult = new SaleEvents[0];
        int count = 0;

        for (SaleEvents event : ListSaleEvent) {
            LocalDate eventStart = event.getStartDate();
            LocalDate eventEnd = event.getEndDate();
            if ((eventStart.isBefore(date) || eventStart.isEqual(date)) && (eventEnd.isAfter(date) || eventEnd.isEqual(date))) {
                tempResult = Arrays.copyOf(tempResult, tempResult.length + 1);
                tempResult[count] = event;
                count++;
            }
        }

        if (count == 0) {
            System.out.println("Not found any sale event!");
            return null;
        }
        return tempResult;
    }

    public SaleEvents[] findByDateRange(LocalDate start, LocalDate end) {
        SaleEvents[] tempResult = new SaleEvents[0];
        int count = 0;

        for (SaleEvents event : ListSaleEvent) {
            if ((event.getStartDate().isEqual(start) || event.getStartDate().isAfter(start)) &&
                    (event.getEndDate().isEqual(end) || event.getEndDate().isBefore(end))) {
                tempResult = Arrays.copyOf(tempResult, tempResult.length + 1);
                tempResult[count] = event;
                count++;
            }
        }

        if (count == 0) {
            System.out.println("Not found any sale event!");
            return null;
        }
        return tempResult;
    }

    public void readFile() throws IOException {
        // test file
        File testFile = new File("src/main/resources/SaleEvents");
        if (testFile.length() == 0 || !testFile.exists())
            return;

        try (DataInputStream file = new DataInputStream(
                new BufferedInputStream(new FileInputStream("src/main/resources/SaleEvents")))) {
            int count = file.readInt();
            SaleEvents[] list = new SaleEvents[count];
            for (int i = 0; i < count; i++) {
                String saleEvId = file.readUTF();
                String saleEvName = file.readUTF();
                String description = file.readUTF();
                LocalDate startDate = LocalDate.parse(file.readUTF());
                LocalDate endDate = LocalDate.parse(file.readUTF());

                // get saleEvent detail
                String promoCode = file.readUTF();
                BigDecimal minPrice = new BigDecimal(file.readUTF());
                BigDecimal discount = new BigDecimal(file.readUTF());
                BigDecimal maxPriceDiscount = new BigDecimal(file.readUTF());
                SaleEventsDetail detail = new SaleEventsDetail(saleEvId, promoCode, minPrice, discount,
                        maxPriceDiscount);

                // set values for temp list
                list[i] = new SaleEvents(saleEvId, saleEvName, description, startDate, endDate, detail);
            }

            // set fields for BUS
            setCount(count);
            setListSaleEvent(list);
        } catch (Exception ex) {
            System.out.println("Lỗi dữ liệu: " + ex.getMessage());
        }
    }

    public void writeFile() throws IOException {
        File testFile = new File("src/main/resources/SaleEvents");
        if (testFile.length() == 0 || !testFile.exists())
            return;

        try (DataOutputStream file = new DataOutputStream(
                new BufferedOutputStream(new FileOutputStream("src/main/resources/SaleEvents")))) {
            file.writeInt(count);
            for (SaleEvents event : ListSaleEvent) {
                // Ghi thông tin SaleEvent
                file.writeUTF(event.getSaleEvId());
                file.writeUTF(event.getSaleEvName());
                file.writeUTF(event.getDescription());
                file.writeUTF(event.getStartDate().toString());
                file.writeUTF(event.getEndDate().toString());

                // Ghi thông tin SaleEventsDetail
                SaleEventsDetail detail = event.getDetail();
                // file.writeUTF(detail.getSaleEvId());
                file.writeUTF(detail.getPromoCode());
                file.writeUTF(detail.getMinPrice().toString());
                file.writeUTF(detail.getDiscount().toString());
                file.writeUTF(detail.getMaxPriceDiscount().toString());
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi mở file: " + e.getMessage());
        }
    }

}