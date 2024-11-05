package DTO;

import java.time.LocalDate;

public class SaleEvents {
    private String saleEvId;
    private String saleEvName;
    private String description;
    private LocalDate startDate;
    private LocalDate enđate;

    // constructor
    public SaleEvents() {
    }

    public SaleEvents(String saleEvId, String saleEvName, String description, LocalDate startDate, LocalDate enđate) {
        this.saleEvId = saleEvId;
        this.saleEvName = saleEvName;
        this.description = description;
        this.startDate = startDate;
        this.enđate = enđate;
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

    public LocalDate getEnđate() {
        return enđate;
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

    public void setEnđate(LocalDate enđate) {
        this.enđate = enđate;
    }

}
