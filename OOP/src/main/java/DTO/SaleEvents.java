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
