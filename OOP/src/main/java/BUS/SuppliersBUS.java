package BUS;

import DTO.Suppliers;
import java.util.Scanner;


public class SuppliersBUS implements RuleSets {
    private Suppliers[] suppliersList;
    private int count;

    // Constructor
    public SuppliersBUS(int size) {
        suppliersList = new Suppliers[size];
        count = 0;
    }

    @Override
    public void add(Object supplier) {
        if (supplier instanceof Suppliers) {
            if (count < suppliersList.length) {
                suppliersList[count] = (Suppliers) supplier;
                count++;
                System.out.println("Supplier added successfully.");
            } else {
                System.out.println("List is full, cannot add more suppliers.");
            }
        } else {
            System.out.println("Invalid object type. Must be a Suppliers instance.");
        }
    }

    @Override
    public int find(String supplierID) {
        for (int i = 0; i < count; i++) {
            if (suppliersList[i].getSupplierID().equals(supplierID)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void search(String supplierID) {
        int index = find(supplierID);
        if (index != -1) {
            System.out.println("Found Supplier: " + suppliersList[index].getSupplierName());
        } else {
            System.out.println("Supplier not found.");
        }
    }

    @Override
    public void remove(String supplierID) {
        int index = find(supplierID);
        if (index != -1) {
            suppliersList[index] = suppliersList[count - 1];
            suppliersList[count - 1] = null;
            count--;
            System.out.println("Supplier removed successfully.");
        } else {
            System.out.println("Supplier not found.");
        }
    }

    @Override
    public void edit(String supplierID) {
        int index = find(supplierID);
        if (index != -1) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter new supplier name: ");
            String newName = scanner.nextLine();

            suppliersList[index].setSupplierName(newName);
            System.out.println("Supplier edited successfully.");
        } else {
            System.out.println("Supplier not found.");
        }
    }


    public Suppliers[] getSuppliersList() {
        return suppliersList;
    }
}
