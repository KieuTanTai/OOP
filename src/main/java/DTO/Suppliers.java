package DTO;

import java.util.Scanner;

import BUS.SuppliersBUS;
import util.Validate;

public class Suppliers {
    private String supplierID;
    private String supplierName;
    private String phone;
    private Scanner input = new Scanner(System.in);

    // constructor
    public Suppliers() {
    }

    public Suppliers(String supplierID, String supplierName, String phone) {
        this.supplierID = supplierIDModifier(supplierID);
        this.supplierName = supplierName;
        this.phone = phone;
    }

    // getter
    public String getSupplierID() {
        return supplierID;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public String getPhone() {
        return phone;
    }

    // setter
    public void setSupplierID(String supplierID) {
        this.supplierID = supplierIDModifier(supplierID);
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    // set not param
    public String setID() {
        String id = "";
        Suppliers[] list = SuppliersBUS.getSupplierList();

        if (list.length == 0 || list == null) {
            id = "00000001";
        } else {
            String getID = list[list.length - 1].getSupplierID();
            int prevID = Integer
                    .parseInt(getID.substring(3, getID.length() - 3));
            id = String.format("%d", prevID + 1);
            // check if id length < 8
            while (id.length() != 8)
                id = "0" + id;
        }
        return supplierIDModifier(id);
    }

    public String setName() {
        String name;
        do {
            System.out.print("set name : ");
            name = input.nextLine().trim();
            if (!Validate.checkName(name)) {
                System.out.println("name is wrong format!");
                name = "";
            }
        } while (name.isEmpty());
        return name;
    }

    public String setPhone() {
        String phone;
        do {
            System.out.print("set phone number: ");
            phone = input.nextLine().trim();
            if (!Validate.validatePhone(phone)) {
                System.out.println("invalid phone number!");
                phone = "";
            }
        } while (phone.isEmpty());
        return phone;
    }

    public void setInfo() {
        System.out.println("*".repeat(60));
        this.supplierID = setID();
        System.out.println("-".repeat(60));
        this.supplierName = setName();
        System.out.println("-".repeat(60));
        this.phone = setPhone();
        System.out.println("*".repeat(60));

        int userChoose;
        System.out.printf("*".repeat(60) + "\n");
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
            setSupplierID(supplierID);
            setSupplierName(supplierName);
            setPhone(phone);
            System.out.println("create and set fields success");
        }
    }

    private String supplierIDModifier(String supplierID) {
        if (supplierID.startsWith("SUP") && supplierID.endsWith("CPN") && supplierID.length() == 14)
            return supplierID;
        if (!Validate.validateID(supplierID)) {
            System.out.println("error id!");
            return "N/A";
        }
        return "SUP" + supplierID + "CPN";
    }
}
