package DTO;

import util.Validate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Customers extends Person {
    private String address;
    private BigDecimal point;

    // Constructors
    public Customers() {
    }

    public Customers(String personID, String firstName, String lastName, LocalDate dateOfBirth, String phoneNumber,
            String address, BigDecimal point) {
        super(personID, firstName, lastName, dateOfBirth, phoneNumber);
        this.address = address;
        this.point = point;
    }

    // Getters
    public String getAddress() {
        return this.address;
    }

    public BigDecimal getPoint() {
        return this.point;
    }

    // Setters with parameters
    public void setAddress(String address) {
        this.address = address;
    }

    public void setPoint(BigDecimal point) {
        this.point = point;
    }

    // Setters without parameters (user input)
    public String setAddress() {
        String addr;
        do {
            System.out.print("Enter address: ");
            addr = input.nextLine().trim();
            if (addr.isEmpty()) {
                System.out.println("Address cannot be empty!");
            }
        } while (addr.isEmpty());
        return addr;
    }

    public BigDecimal setPoint() {
        BigDecimal point;
        do {
            System.out.print("customer point: ");
            String level = input.nextLine().trim();
            point = Validate.isBigDecimal(level);
        } while (point == null);
        return point;
    }

    // Override methods
    @Override
    public void setInfo() {
        System.out.println("*".repeat(60));
        String id = setID();
        System.out.println("-".repeat(60));
        String firstName = setFirstName();
        System.out.println("-".repeat(60));
        String lastName = setLastName();
        System.out.println("-".repeat(60));
        LocalDate dateOfBirth = setDateOfBirth();
        System.out.println("-".repeat(60));
        String phone = setPhoneNumber();
        System.out.println("-".repeat(60));
        String address = setAddress();
        System.out.println("-".repeat(60));
        BigDecimal point = setPoint();
        System.out.println("*".repeat(60));

        int userChoose;               
        System.out.printf("| %s %s %s |\n", "I.Cancel", "-".repeat(20), "II.Submit");

        do {
            System.out.print("choose option (1 or 2) : ");
            String option = input.nextLine().trim();
            userChoose = Validate.parseChooseHandler(option, 2);
        } while (userChoose == -1);
        System.out.printf("*".repeat(60) + "\n");
        if (userChoose == 1) {
            System.out.println("ok!");
            return;
        } else {
            setPersonID(id);
            setFullName(firstName, lastName);
            setDateOfBirth(dateOfBirth);
            setPhoneNumber(phone);
            setAddress(address);
            setPoint(point);
        }
    }

    @Override
    public void showInfo() {
        LocalDate dateOfBirth = getDateOfBirth();
        String customerID = getPersonID(), customerName = getFullName(), phone = getPhoneNumber();
        String level = "N/A";
        if (point != null) {
            if (point.compareTo(new BigDecimal(1000)) >= 0 && point.compareTo(new BigDecimal(10000)) < 0)
                level = "silver";
            else if (point.compareTo(new BigDecimal(10000)) >= 0 && point.compareTo(new BigDecimal(20000)) < 0)
                level = "gold";
            else if (point.compareTo(new BigDecimal(20000)) >= 0)
                level = "platinum";
            else
                level = "normal";
        }
        
        System.out.println("=".repeat(160));
        System.out.printf("| %-22s : %s \n", "ID", customerID != null ? customerID : "N/A");
        System.out.printf("| %-22s : %s \n", "Full Name", customerName!= null ? customerName: "N/A");
        System.out.printf("| %-22s : %s \n", "Date of Birth",
                dateOfBirth != null ? dateOfBirth.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) : "N/A");

        System.out.printf("| %-22s : %s \n", "Phone Number", phone != null ? phone : "N/A");
        System.out.printf("| %-22s : %s \n", "Address", address != null ? address : "N/A");
        System.out.printf("| %-22s : %s (%s)\n", "point", point != null ? point  : "N/A", level);
        System.out.println("=".repeat(160));
    }

    @Override
    protected String personIDModifier(String customerID) {
        if (customerID.startsWith("CUS") && customerID.endsWith("PS") && customerID.length() == 12)
            return customerID;
        if (!Validate.validateID(customerID)) {
            System.out.println("error id!");
            return "N/A";
        }
        return "CUS" + customerID + "PS";
    }
}
