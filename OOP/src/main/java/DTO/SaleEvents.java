package DTO;

import java.time.LocalDate;

public class SaleEvents {
    private String saleEvId;
    private String saleEvName;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private SaleEventsDetail detail;

    // constructor
    public SaleEvents() {
    }

    public SaleEvents(String saleEvId, String saleEvName, String description, LocalDate startDate, LocalDate enđate, SaleEventsDetail detail) {
        this.saleEvId = saleEvId;
        this.saleEvName = saleEvName;
        this.description = description;
        this.startDate = startDate;
        this.endDate = enđate;
        this.detail = detail;
    }

    public SaleEvents(String saleEvId, String saleEvName, String description, LocalDate startDate, LocalDate enđate) {
        this.saleEvId = saleEvId;
        this.saleEvName = saleEvName;
        this.description = description;
        this.startDate = startDate;
        this.endDate = enđate;
    }

        public void nhap() {
        System.out.println("Nhập sale events id:");
        this.saleEvId = sc.nextLine();

        System.out.println("Nhập sale events name:");
        this.saleEvName = sc.nextLine();

        System.out.println("Nhập description:");
        this.description = sc.nextLine();

        System.out.println("Nhập start date:");
        this.startDate = LocalDate.parse(sc.next());

        System.out.println("Nhập end date:");
        this.endDate = LocalDate.parse(sc.next());

        this.detail.setSaleEvId(this.saleEvId);
        this.detail.nhap();
    }

    public void xuat(){
        System.out.println("sale events id là:" + this.saleEvId);
        System.out.println("sale events name là:" + this.saleEvName);
        System.out.println("description là:" + this.description);
        System.out.println("star date là:" + this.startDate);
        System.out.println("end date là:" + this.endDate);
    }

    public void xuatGomDetail(){
        System.out.println("sale events id là:" + this.saleEvId);
        System.out.println("sale events name là:" + this.saleEvName);
        System.out.println("description là:" + this.description);
        System.out.println("star date là:" + this.startDate);
        System.out.println("end date là:" + this.endDate);
        this.detail.xuat();
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
        this.saleEvId = saleEvId;
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

    public void setEndDate(LocalDate enđate) {
        this.endDate = enđate;
    }

    public void setDetail(SaleEventsDetail detail) {
        this.detail = detail;
    }
}
