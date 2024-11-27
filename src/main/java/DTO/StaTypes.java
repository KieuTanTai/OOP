package DTO;

import java.util.Scanner;

import BUS.StaTypesBUS;
import util.Validate;

public class StaTypes {
    private String typeID;
    private String typeName;
    private Scanner input = new Scanner(System.in);

    // constructor
    public StaTypes() {
    }

    public StaTypes(String typeID, String typeName) {
        this.typeID = typeIDModifier(typeID);
        this.typeName = typeName;
    }

    // getter
    public String getTypeID() {
        return typeID;
    }

    public String getTypeName() {
        return typeName;
    }

    // setter
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public void setTypeID(String typeID) {
        this.typeID = typeIDModifier(typeID);
    }

    // set not param
    public String setID() {
        String id = "";
        StaTypes[] list = StaTypesBUS.getTypesList();

        if (list.length == 0 || list == null) {
            return "00000001";
        } else {
            String getID = list[list.length - 1].getTypeID();
            int prevID = Integer.parseInt(getID.substring(2, getID.length() - 2));
            id = String.format("%d", prevID + 1);
            // check if id length < 8
            while (id.length() != 8)
                id = "0" + id;
        }
        return typeIDModifier(id);
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
        this.typeID = setID();
        System.out.println("-".repeat(60));
        this.typeName = setName();
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
            setTypeID(typeID);
            setTypeName(typeName);
            System.out.println("create and set fields success");
        }
    }

    private String typeIDModifier(String typeID) {
        if (typeID.startsWith("ST") && typeID.endsWith("TP") && typeID.length() == 12)
            return typeID;
        if (!Validate.validateID(typeID)) {
            System.out.println("error id!");
            return "N/A";
        }
        return "ST" + typeID + "TP";
    }
}
