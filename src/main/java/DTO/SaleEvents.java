package DTO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import BUS.SaleEventsBUS;
import util.Validate;

public class SaleEvents {
    private String saleEvId;
    private String saleEvName;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private SaleEventsDetail detail;
    private final Scanner sc = new Scanner(System.in);

    // constructor
    public SaleEvents() {
    }

    public SaleEvents(String saleEvId, String saleEvName, String description, LocalDate startDate, LocalDate enDate,
            SaleEventsDetail detail) {
        this.saleEvId = saleIDModifier(saleEvId);
        this.saleEvName = saleEvName;
        this.description = description;
        this.startDate = startDate;
        this.endDate = enDate;
        this.detail = detail;
    }

    public SaleEvents(String saleEvId, String saleEvName, String description, LocalDate startDate, LocalDate enDate) {
        this.saleEvId = saleIDModifier(saleEvId);
        this.saleEvName = saleEvName;
        this.description = description;
        this.startDate = startDate;
        this.endDate = enDate;
    }

    // getter
    public String getSaleEvId() {
        return saleEvId;
    }

    public String getSaleEvName() {
        return saleEvName;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public SaleEventsDetail getDetail() {
        return detail;
    }

    // setter
    public void setSaleEvId(String saleEvId) {
        this.saleEvId = saleIDModifier(saleEvId);
    }

    public void setSaleEvName(String saleEvName) {
        this.saleEvName = saleEvName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate enDate) {
        this.endDate = enDate;
    }

    public void setDetail(SaleEventsDetail detail) {
        this.detail = detail;
    }

    // setter no params
    public String setID() {
        StringBuilder id = new StringBuilder();
        try {
            SaleEventsBUS salesList = new SaleEventsBUS();
            salesList.readFile();
            SaleEvents[] list = salesList.getListSaleEvent();

            if (list.length == 0) {
                id = new StringBuilder("00000001");
            } else {
                String getID = list[list.length - 1].getSaleEvId();
                int prevID = Integer.parseInt(getID.substring(2, getID.length() - 2));
                id = new StringBuilder(String.format("%d", prevID + 1));
                while (id.length() != 8)
                    id.insert(0, "0");
            }
        } catch (Exception e) {
            System.out.println("error when execute with file!" + e.getMessage());
            id = new StringBuilder("00000001");
        }
        return saleIDModifier(id.toString());
    }

    public LocalDate setDate() {
        LocalDate date;
        do {
            System.out.print("set date (dd-mm-yyyy) : ");
            String dateInput = sc.nextLine().trim();
            date = Validate.isCorrectDate(dateInput);
        } while (date == null);
        return date;
    }

    public String setName() {
        String name;
        do {
            System.out.print("set name : ");
            name = sc.nextLine().trim();
            if (!Validate.checkName(name)) {
                System.out.println("name is wrong format!");
                name = "";
            }
        } while (name.isEmpty());
        return name;
    }

    // other methods
    public void setInfo() {
        System.out.println("*".repeat(60));
        String saleEvId = setID();

        // field name
        String saleEvName = setName();

        System.out.println("-".repeat(60));
        System.out.print("Enter description : ");
        String description = sc.nextLine();

        // set date and validate if start > end
        LocalDate startDate, endDate;
        Boolean checking = false;
        do {
            System.out.println("-".repeat(60));
            System.out.println("Start date!");
            startDate = setDate();

            System.out.println("-".repeat(60));
            System.out.println("End date!");
            endDate = setDate();
            checking = Validate.isValidRangeDate(startDate, endDate);
            // check flag
            if(!checking)
                System.out.println("Error start and end date !!!");
        } while (!checking);
        // set sale detail
        SaleEventsDetail tempDetail = new SaleEventsDetail();
        tempDetail.setSaleEvId(saleEvId);
        tempDetail.setInfo();
        System.out.println("*".repeat(60));

        int userChoice;
        System.out.printf("| %s %s %s |\n", "I.Cancel", "-".repeat(20), "II.Submit");
        do {
            System.out.print("choose option (1 or 2) : ");
            String option = sc.nextLine().trim();
            userChoice = Validate.parseChooseHandler(option, 2);
        } while (userChoice == -1);
        System.out.println("*".repeat(60));
        if (userChoice == 1)
            System.out.println("ok!");
        else {
            this.saleEvId = saleEvId;
            this.saleEvName = saleEvName;
            this.description = description;
            this.startDate = startDate;
            this.endDate = endDate;
            this.detail = tempDetail;
        }
    }

    public void showInfo() {
        System.out.println("=".repeat(140));
        System.out.printf("| %-22s : %s \n", "Sale Event ID", this.saleEvId != null ? this.saleEvId : "N/A");
        System.out.printf("| %-22s : %s \n", "Sale Event Name", this.saleEvName != null ? this.saleEvName : "N/A");
        System.out.printf("| %-22s : %s \n", "Description", this.description != null ? this.description : "N/A");
        System.out.printf("| %-22s : %s \n", "Start Date",
                this.startDate != null ? this.startDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) : "N/A");
        System.out.printf("| %-22s : %s \n", "End Date",
                this.endDate != null ? this.endDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) : "N/A");
        System.out.println("=".repeat(140));

    }

    public void showWithDetail() {
        System.out.println("=".repeat(140));
        System.out.printf("| %-22s : %s \n", "Sale Event ID", this.saleEvId != null ? this.saleEvId : "N/A");
        System.out.printf("| %-22s : %s \n", "Sale Event Name", this.saleEvName != null ? this.saleEvName : "N/A");
        System.out.printf("| %-22s : %s \n", "Description", this.description != null ? this.description : "N/A");
        System.out.printf("| %-22s : %s \n", "Start Date",
                this.startDate != null ? this.startDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) : "N/A");
        System.out.printf("| %-22s : %s \n", "End Date",
                this.endDate != null ? this.endDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) : "N/A");
        this.detail.showInfo();
        System.out.println("=".repeat(140));
    }

    // private method for modify id
    private String saleIDModifier(String bookID) {
        if (bookID.startsWith("SA") && bookID.endsWith("LE") && bookID.length() == 12)
            return bookID;
        if (!Validate.validateID(bookID)) {
            System.out.println("error id!");
            return "N/A";
        }
        return "SA" + bookID + "LE";
    }
}
