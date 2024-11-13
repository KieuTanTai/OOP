package BUS;

import DTO.StaTypes;
import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class StaTypesBUS {
    private static StaTypes[] typesList;
    private static int count;
    private Scanner scanner = new Scanner(System.in);

    // Constructor: Initializes with an empty list and count 0
    public StaTypesBUS() {
        StaTypesBUS.count = 0;
        typesList = new StaTypes[0];
    }

    // Constructor: Initializes with specified count and list
    public StaTypesBUS(int count, StaTypes[] typesList) {
        StaTypesBUS.count = count;
        StaTypesBUS.typesList = typesList;
    }

    // Getter: Returns count of types
    public static int getCount() {
        return count;
    }

    // Getter: Returns the types list
    public static StaTypes[] getTypesList() {
        return Arrays.copyOf(typesList, typesList.length);
    }

    // Setter: Sets count of types
    public void setCount(int count) {
        StaTypesBUS.count = count;
    }

    // Setter: Sets types list
    public void setTypesList(StaTypes[] typesList) {
        StaTypesBUS.typesList = typesList;
    }

    // Show all types in the list
    public static void showList() {
        for (int i = 0; i < typesList.length; i++) {
            System.out.printf("%d: %10s %s\n", i + 1, typesList[i].getTypeID(), typesList[i].getTypeName());
        }
    }

    // Add a new StaTypes to the list
    public static void add(StaTypes type) {
        typesList = Arrays.copyOf(typesList, typesList.length + 1);
        typesList[count] = type;
        count++;
        System.out.println("Type added successfully.");
    }

    // Find the index of a StaTypes by typeID
    public int find(String typeID) {
        for (int i = 0; i < typesList.length; i++) {
            if (typesList[i].getTypeID().equals(typeID)) {
                return i;
            }
        }
        return -1;
    }

    // Remove a StaTypes from the list by typeID
    public void remove(String typeID) {
        int index = find(typeID);
        if (index != -1) {
            for (int i = index; i < typesList.length - 1; i++) {
                typesList[i] = typesList[i + 1];
            }
            typesList = Arrays.copyOf(typesList, typesList.length - 1);
            count--;
            System.out.println("Type removed successfully.");
        } else {
            System.out.println("Type not found.");
        }
    }

    // Search for a StaTypes by typeID and display it
    public void search(String typeID) {
        int index = find(typeID);
        if (index != -1) {
            System.out.printf("Found Type: %s - %s\n", typesList[index].getTypeID(), typesList[index].getTypeName());
        } else {
            System.out.println("Type not found.");
        }
    }

    // Edit the typeName of a StaTypes by typeID
    public void edit(String typeID) {
        int index = find(typeID);
        if (index != -1) {
            System.out.print("Enter new type name: ");
            String newTypeName = scanner.nextLine();
            typesList[index].setTypeName(newTypeName);
            System.out.println("Type updated successfully.");
        } else {
            System.out.println("Type not found.");
        }
    }

    // Write the list of StaTypes to a file
    public void writeFile() throws IOException {
        try (DataOutputStream file = new DataOutputStream(new FileOutputStream("../../resources/StaTypes", false))) {
            file.writeInt(count);
            for (int i = 0; i < count; i++) {
                file.writeUTF(typesList[i].getTypeID());
                file.writeUTF(typesList[i].getTypeName());
            }
            System.out.println("Write done!");
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }

    // Read the list of StaTypes from a file
    public void readFile() throws IOException {
        try (DataInputStream file = new DataInputStream(new FileInputStream("../../resources/StaTypes"))) {
            count = file.readInt();
            StaTypes[] list = new StaTypes[count];
            for (int i = 0; i < count; i++) {
                String typeID = file.readUTF();
                String typeName = file.readUTF();
                list[i] = new StaTypes(typeID, typeName);
            }
            StaTypesBUS.typesList = list;
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }
}
