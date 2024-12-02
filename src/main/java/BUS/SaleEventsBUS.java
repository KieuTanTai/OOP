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
    private SaleEvents[] listSaleEvents;
    private int count;
    Scanner sc = new Scanner(System.in);

    // constructors
    public SaleEventsBUS() {
        this.count = 0;
        this.listSaleEvents = new SaleEvents[0];
    }

    public SaleEventsBUS(SaleEvents[] listSaleEvent, int count) {
        listSaleEvents = listSaleEvent;
        this.count = count;
    }

    // getter / setter
    public SaleEvents[] getListSaleEvent() {
        return this.listSaleEvents;
    }

    public int getCount() {
        return this.count;
    }

    public void setListSaleEvent(SaleEvents[] list) {
        this.listSaleEvents = list;
    }

    public void setCount(int count) {
        this.count = count;
    }

    // other methods like add, remove, find, search, ......
    // show list
    public void showList() {
        if (listSaleEvents == null)
            return;
        for (SaleEvents saleEvents : listSaleEvents)
            saleEvents.showWithDetail();
    }

    public void add(SaleEvents saleEvents) {
        count++;
        listSaleEvents = Arrays.copyOf(listSaleEvents, count);
        listSaleEvents[count - 1] = saleEvents;
    }

    public void update(String id) {
        for (int i = 0; i < count; i++)
            if (listSaleEvents[i].getSaleEvId().equals(id))
                listSaleEvents[i].setInfo();
    }

    public boolean delete(int vt) {
        if (vt >= count)
            return false;

        count--;
        for (int x = vt; x < count; x++)
            listSaleEvents[x] = listSaleEvents[x + 1];
        listSaleEvents[count] = null;
        listSaleEvents = Arrays.copyOf(listSaleEvents, count);
        return true;
    }

    public SaleEvents findById(String id) {
        for (int i = 0; i < count; i++)
            if (listSaleEvents[i].getSaleEvId().equals(id))
                return listSaleEvents[i];
        System.out.println("not found any sale event!");
        return null;
    }

    public SaleEvents findByPromoCode(String promoCode) {
        for (SaleEvents sale : listSaleEvents)
            if (sale.getDetail().getPromoCode().equals(promoCode))
                return sale;
        return null;
    }

    public SaleEvents findByName(String name) {
        SaleEvents result = new SaleEvents();

        for (int i = 0; i < count; i++) {
            if (listSaleEvents[i].getSaleEvName().equals(name))
                result = listSaleEvents[i];
        }
        return result;
    }

    public SaleEvents[] findByStartDate(String start) {
        try {
            LocalDate startDate = LocalDate.parse(start);

            SaleEvents[] tempResult = new SaleEvents[listSaleEvents.length];
            int temp = 0;

            for (SaleEvents event : listSaleEvents) {
                if (event.getStartDate().equals(startDate)) {
                    tempResult[temp++] = event;
                }
            }
            return Arrays.copyOf(tempResult, temp);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date! : " + start);
        }
        return new SaleEvents[0];
    }

    public SaleEvents[] findByEndDate(String end) {
        try {
            LocalDate startDate = LocalDate.parse(end);

            SaleEvents[] tempResult = new SaleEvents[listSaleEvents.length];
            int temp = 0;

            for (SaleEvents event : listSaleEvents) {
                if (event.getStartDate().equals(startDate)) {
                    tempResult[temp++] = event;
                }
            }

            return Arrays.copyOf(tempResult, temp);

        } catch (DateTimeParseException e) {
            System.out.println("Invalid date! : " + end);
        }
        return new SaleEvents[0];
    }

    public SaleEvents[] findByDateRange(LocalDate date) {
        SaleEvents[] tempResult = new SaleEvents[0];
        int count = 0;

        for (SaleEvents event : listSaleEvents) {
            LocalDate eventStart = event.getStartDate();
            LocalDate eventEnd = event.getEndDate();
            if ((eventStart.isBefore(date) || eventStart.isEqual(date))
                    && (eventEnd.isAfter(date) || eventEnd.isEqual(date))) {
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

        for (SaleEvents event : listSaleEvents) {
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
                SaleEventsDetail detail = new SaleEventsDetail(saleEvId, promoCode, minPrice, discount, maxPriceDiscount);

                // set values for temp list
                list[i] = new SaleEvents(saleEvId, saleEvName, description, startDate, endDate, detail);
            }

            // set fields for BUS
            setCount(count);
            setListSaleEvent(list);
        } catch (Exception ex) {
            System.out.println("Error when read file or something ! : " + ex.getMessage());
        }
    }

    public void writeFile() throws IOException {
        try (DataOutputStream file = new DataOutputStream(
                new BufferedOutputStream(new FileOutputStream("src/main/resources/SaleEvents")))) {
            file.writeInt(count);
            for (SaleEvents event : listSaleEvents) {
                file.writeUTF(event.getSaleEvId());
                file.writeUTF(event.getSaleEvName());
                file.writeUTF(event.getDescription());
                file.writeUTF(event.getStartDate().toString());
                file.writeUTF(event.getEndDate().toString());

                SaleEventsDetail detail = event.getDetail();
                file.writeUTF(detail.getPromoCode());
                file.writeUTF(detail.getMinPrice().toString());
                file.writeUTF(detail.getDiscount().toString());
                file.writeUTF(detail.getMaxPriceDiscount().toString());
            }
        } catch (Exception e) {
            System.out.println("Error when write file or something ! : " + e.getMessage());
        }
    }

}