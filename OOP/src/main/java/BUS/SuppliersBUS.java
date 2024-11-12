package BUS;

import DTO.Suppliers;
import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class SuppliersBUS {
    private static Suppliers[] suppliersList;
    private static int count;

    // Constructor: Initializes with an empty list and count 0
    public SuppliersBUS() {
        SuppliersBUS.count = 0;
        suppliersList = new Suppliers[0];
    }

    // Constructor: Initializes with specified count and list
    public SuppliersBUS(int count, Suppliers[] suppliersList) {
        SuppliersBUS.count = count;
        SuppliersBUS.suppliersList = suppliersList;
    }

    // Getter: Returns count of suppliers
    public int getCount() {
        return SuppliersBUS.count;
    }

    // Getter: Returns the suppliers list
    public Suppliers[] getSuppliersList() {
        return SuppliersBUS.suppliersList;
    }

    // Setter: Sets count of suppliers
    public void setCount(int count) {
        SuppliersBUS.count = count;
    }

    // Setter: Sets suppliers list
    public void setSuppliersList(Suppliers[] suppliersList) {
        SuppliersBUS.suppliersList = suppliersList;
    }

    // Show all suppliers in the list
    public static void showList() {
        for (int i = 0; i < suppliersList.length; i++) {
            System.out.printf("%d: %10s %s\n", i + 1, suppliersList[i].getSupplierID(), suppliersList[i].getSupplierName());
        }
    }

    // Add a new Supplier to the list
    public static void add(Suppliers supplier) {
        suppliersList = Arrays.copyOf(suppliersList, suppliersList.length + 1);
        suppliersList[count] = supplier;
        count++;
        System.out.println("Supplier added successfully.");
    }

    // Find the index of a Supplier by supplierID
    public int find(String supplierID) {
        for (int i = 0; i < suppliersList.length; i++) {
            if (suppliersList[i].getSupplierID().equals(supplierID)) {
                return i;
            }
        }
        return -1;
    }

    // Remove a Supplier from the list by supplierID
    public void remove(String supplierID) {
        int index = find(supplierID);
        if (index != -1) {
            for (int i = index; i < suppliersList.length - 1; i++) {
                suppliersList[i] = suppliersList[i + 1];
            }
            suppliersList = Arrays.copyOf(suppliersList, suppliersList.length - 1);
            count--;
            System.out.println("Supplier removed successfully.");
        } else {
            System.out.println("Supplier not found.");
        }
    }

    // Search for a Supplier by supplierID and display it
    public void search(String supplierID) {
        int index = find(supplierID);
        if (index != -1) {
            System.out.printf("Found Supplier: %s - %s\n", suppliersList[index].getSupplierID(), suppliersList[index].getSupplierName());
        } else {
            System.out.println("Supplier not found.");
        }
    }

    // Edit the supplierName of a Supplier by supplierID
    public void edit(String supplierID) {
        int index = find(supplierID);
        if (index != -1) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter new supplier name: ");
            String newSupplierName = scanner.nextLine();
            suppliersList[index].setSupplierName(newSupplierName);
            System.out.println("Supplier updated successfully.");
        } else {
            System.out.println("Supplier not found.");
        }
    }

    // Write the list of Suppliers to a file
    public void writeFile() throws IOException {
        try (DataOutputStream file = new DataOutputStream(new FileOutputStream("../../resources/Suppliers", false))) {
            file.writeInt(count);
            for (int i = 0; i < count; i++) {
                file.writeUTF(suppliersList[i].getSupplierID());
                file.writeUTF(suppliersList[i].getSupplierName());
            }
            System.out.println("Write done!");
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }

    // Read the list of Suppliers from a file
    public void readFile() throws IOException {
        try (DataInputStream file = new DataInputStream(new FileInputStream("../../resources/Suppliers"))) {
            count = file.readInt();
            Suppliers[] list = new Suppliers[count];
            for (int i = 0; i < count; i++) {
                String supplierID = file.readUTF();
                String supplierName = file.readUTF();
                list[i] = new Suppliers(supplierID, supplierName);
            }
            SuppliersBUS.suppliersList = list;
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }
}
