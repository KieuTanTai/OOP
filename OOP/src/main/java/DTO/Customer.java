package DTO;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Customer extends Person {
    // Thuộc tính (Fields) riêng của Customer
    private String address;
    private BigDecimal point;

    // Constructor
    public Customer(String id, String firstName, String lastName, LocalDate birthday, String phone,
                    String address, BigDecimal point) {
        super(id, firstName, lastName, birthday, phone); // Gọi constructor của lớp cha (Person)
        this.address = address;
        this.point = point;
    }

    // Getter và Setter cho address
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    // Getter và Setter cho point
    public BigDecimal getPoint() {
        return point;
    }

    public void setPoint(BigDecimal point) {
        this.point = point;
    }

    // Triển khai phương thức trừu tượng từ lớp cha
    @Override
    public void displayInfo() {
        System.out.println("Customer ID: " + getId());
        System.out.println("Name: " + getFirstName() + " " + getLastName());
        System.out.println("Birthday: " + getBirthday());
        System.out.println("Phone: " + getPhone());
        System.out.println("Address: " + address);
        System.out.println("Points: " + point);
    }
}
