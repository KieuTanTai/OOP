package DTO;

import java.util.Scanner;

import BUS.PublishersBUS;
import util.Validate;

public class Publishers {
    private String publisherID;
    private String publisherName;
    private final Scanner input = new Scanner(System.in);

    // constructor
    public Publishers() {
    }

    public Publishers(String publisherID, String publisherName) {
        this.publisherID = publisherIDModifier(publisherID);
        this.publisherName = publisherName;
    }

    // getter
    public String getPublisherID() {
        return publisherID;
    }

    public String getPublisherName() {
        return publisherName;
    }

    // setter
    public void setPublisherID(String publisherID) {
        this.publisherID = publisherIDModifier(publisherID);
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    // set not param
    public String setID() {
        StringBuilder id;
        Publishers[] list = PublishersBUS.getPublishersList();

        if (list.length == 0) {
            return "00000001";
        } else {
            String getID = list[list.length - 1].getPublisherID();
            int prevID = Integer.parseInt(getID.substring(3, getID.length() - 3));
            id = new StringBuilder(String.format("%d", prevID + 1));
            // check if id length < 8
            while (id.length() != 8)
                id.insert(0, "0");
        }
        return publisherIDModifier(id.toString());
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

    public void setInfo() {
        System.out.println("*".repeat(60));
        this.publisherID = setID();
        System.out.println("-".repeat(60));
        this.publisherName = setName();
        System.out.println("*".repeat(60));

        int userChoice;
        System.out.println("*".repeat(60));
        System.out.printf("| %s %s %s |\n", "I.Cancel", "-".repeat(20), "II.Submit");
        do {
            System.out.print("choose option (1 or 2) : ");
            String option = input.nextLine().trim();
            userChoice = Validate.parseChooseHandler(option, 2);
        } while (userChoice == -1);
        System.out.println("*".repeat(60));

        if (userChoice == 1)
            System.out.println("ok!");
        else {
            setPublisherID(publisherID);
            setPublisherName(publisherName);
            System.out.println("create and set fields success");
        }
    }

    private String publisherIDModifier(String publisherID) {
        if (publisherID.startsWith("PUB") && publisherID.endsWith("CPN") && publisherID.length() == 14)
            return publisherID;
        if (!Validate.validateID(publisherID)) {
            System.out.println("error id!");
            return "N/A";
        }
        return "PUB" + publisherID + "CPN";
    }
}
