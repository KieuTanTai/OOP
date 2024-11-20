package BUS;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

import DTO.Suppliers;
import Manager.Menu;
import util.Validate;

public class SuppliersBUS implements IRuleSets {
    private static Suppliers[] suppliersList;
    private static int count;
    private final Scanner input = new Scanner(System.in);

    // *constructors (TEST DONE)
    public SuppliersBUS() {
        SuppliersBUS.count = 0;
        suppliersList = new Suppliers[0];
    }

    public SuppliersBUS(Suppliers[] suppliersList, int count) {
        SuppliersBUS.suppliersList = suppliersList;
        SuppliersBUS.count = count;
    }

    // *Getters / Setters (TEST DONE)
    public static Suppliers[] getSupplierList() {
        return Arrays.copyOf(SuppliersBUS.suppliersList, SuppliersBUS.count);
    }

    public static Suppliers getSupplier(String id) {
        for (Suppliers supplier : suppliersList)
            if (supplier.getSupplierID().equals(id))
                return new Suppliers(supplier.getSupplierID(), supplier.getSupplierName(), supplier.getPhone());
        return null;
    }

    public static Suppliers[] getSuppliers(int start, int end) {
        int size = 0;
        Suppliers[] list = new Suppliers[0];
        if (start >= end)
            return null;
        for (int i = start; i < end; i++) {
            list = Arrays.copyOf(list, list.length + 1);
            list[size] = suppliersList[i];
            size++;
        }
        return Arrays.copyOf(list, list.length);
    }

    public static int getCount() {
        return count;
    }

    public void setSupplierList(Suppliers[] suppliersList) {
        SuppliersBUS.suppliersList = suppliersList;
    }

    public void setSupplier(String supplierID, Suppliers newSupplier) {
        for (int i = 0; i < count; i++)
            if (suppliersList[i].getSupplierID().equals(supplierID)) {
                suppliersList[i] = newSupplier;
                return;
            }
    }

    public void setCount(int count) {
        SuppliersBUS.count = count;
    }

    // *show method (TEST DONE)
    public static void showList() {
        if (suppliersList == null)
            return;
        showAsTable(suppliersList);
    }

    // *find methods (TEST DONE)
    @Override
    public void find() {
        Menu.findHandler();
    }

    @Override
    public int find(String nameOrID) {
        for (int i = 0; i < suppliersList.length; i++) {
            if (suppliersList[i].getSupplierID().equals(nameOrID)
                    || suppliersList[i].getSupplierName().toLowerCase().equals(nameOrID.toLowerCase().trim())) {
                return i;
            }
        }
        System.out.println("Your supplier is not found!");
        return -1;
    }

    public Suppliers[] relativeFind(String name) {
        int count = 0;
        Suppliers[] suppliersArray = new Suppliers[0];
        for (Suppliers supplier : suppliersList)
            if (supplier.getSupplierName().toLowerCase().contains(name.toLowerCase())) {
                suppliersArray = Arrays.copyOf(suppliersArray, suppliersArray.length + 1);
                suppliersArray[count] = supplier;
                count++;
            }
        if (count == 0) {
            System.out.println("Not found any suppliers!");
            return null;
        }
        return suppliersArray;
    }

    // *search methods (TEST DONE)
    @Override
    public void search() {
        Menu.searchHandler();
    }

    @Override
    public void search(String nameOrID) {
        int index = find(nameOrID);
        if (index != -1)
            showAsTable(suppliersList[index]);
    }

    public void relativeSearch(String name) {
        Suppliers[] list = relativeFind(name);
        if (list != null)
            showAsTable(list);
    }

    // *add methods (TEST DONE)
    @Override
    public void add() {
        Menu.addHandler();
    }

    @Override
    public void add(Object supplier) {
        if (supplier instanceof Suppliers) {
            suppliersList = Arrays.copyOf(suppliersList, suppliersList.length + 1);
            suppliersList[count] = (Suppliers) supplier;
            count++;
        } else
            System.out.println("Your new supplier is not correct!");
    }

    public void add(Suppliers[] newSuppliers) {
        int tempIndex = 0, newListLength = newSuppliers.length;
        int initCount = getCount();
        int total = initCount + newListLength;
        suppliersList = Arrays.copyOf(suppliersList, suppliersList.length + newListLength);

        for (int i = initCount; i < total; i++, tempIndex++)
            suppliersList[i] = newSuppliers[tempIndex];
        SuppliersBUS.count = total;
    }

    // *edit methods (TEST DONE)
    @Override
    public void edit() {
        Menu.editHandler();
    }

    @Override
    public void edit(String id) {
        int index = find(id);
        if (index != -1) {
            int userChoose;
            // show list for user choose
            showAsTable(suppliersList[index]);
            System.out.printf("| %s %s %s |\n", "I.Cancel", "-".repeat(20), "II.Edit");
            do {
                 System.out.print("choose option (1 or 2) : ");
                 String option = input.nextLine().trim();
                 userChoose = Validate.parseChooseHandler(option, 2);
            } while (userChoose == -1);
            if (userChoose == 1)
                 return;

            String newName;
            do {
                System.out.print("Enter new name: ");
                newName = input.nextLine().trim();
                if (!Validate.checkName(newName)) {
                    System.out.println("Name is wrong format!");
                    newName = "";
                }
            } while (newName.isEmpty());
          
            suppliersList[index].setSupplierName(newName);
        }
    }

    public void editPhone(String supplierID) {
        int index = find(supplierID);
        if (index != -1) {
            String phone;
            int userChoose;

            showAsTable(suppliersList[index]);
            System.out.printf("| %s %s %s |\n", "I.Cancel", "-".repeat(20), "II.Edit");
            do {
                System.out.print("Choose option (1 or 2): ");
                String option = input.nextLine().trim();
                userChoose = Validate.parseChooseHandler(option, 2);
            } while (userChoose == -1);
            if (userChoose == 1)
                return;

            do {
                System.out.print("Edit phone: ");
                phone = input.nextLine().trim();
                if (!Validate.validatePhone(phone)) {
                    System.out.println("Error: Invalid phone number!");
                    phone = "";
                }
            } while (phone.isEmpty());

            suppliersList[index].setPhone(phone);
        }
    }

    // *remove methods (TEST DONE)
    @Override
    public void remove() {
        Menu.removeHandler();
    }

    @Override
    public void remove(String id) {
        int index = find(id);
        if (index != -1) {
            int userChoose;
            // show list for user choose
            showAsTable(suppliersList[index]);
            System.out.printf("| %s %s %s |\n", "I.Cancel", "-".repeat(20), "II.Remove");
            do {
                 System.out.print("choose option (1 or 2) : ");
                 String option = input.nextLine().trim();
                 userChoose = Validate.parseChooseHandler(option, 2);
            } while (userChoose == -1);
            if (userChoose == 1)
                 return;

            for (int i = index; i < suppliersList.length - 1; i++)
                suppliersList[i] = suppliersList[i + 1];
            suppliersList = Arrays.copyOf(suppliersList, suppliersList.length - 1);
            count--;
        }
    }

    // *show as table methods (TEST DONE)
    public static void showAsTable(Suppliers[] list) {
        if (list == null)
            return;
        System.out.println("=".repeat(110));
        System.out.printf("| \t%-20s %-20s %-20s %-58s |\n", "No.", "Suppliers ID", "Phone",  "Suppliers Name");
        System.out.println("=".repeat(110));
        for (int i = 0; i < list.length; i++) {
            if (i > 0)
                System.out.println("|" + "-".repeat(108) + "|");
            System.out.printf("| \t%-20s %-20s %-20s %-58s |\n", i + 1, list[i].getSupplierID(), list[i].getPhone(), list[i].getSupplierName());
        }
        System.out.println("=".repeat(110));
    }

    public static void showAsTable(Suppliers item) {
        if (item == null)
            return;
        System.out.println("=".repeat(110));
        System.out.printf("| \t%-20s %-20s %-20s %-58s |\n", "No.", "Genres ID", "Phone", "Genres Name");
        System.out.println("=".repeat(110));
        System.out.println("|" + "-".repeat(108) + "|");
        System.out.printf("| \t%-20s %-20s %-20s %-58s |\n", 1, item.getSupplierID(), item.getPhone(), item.getSupplierName());
        System.out.println("=".repeat(110));
    }

    // other methods
    // *execute files (TEST DONE)
    // write file
    public void writeFile() throws IOException {
        try (DataOutputStream file = new DataOutputStream(
                new BufferedOutputStream(new FileOutputStream("src/main/resources/Suppliers", false)))) {
            file.writeInt(count);
            for (int i = 0; i < count; i++) {
                file.writeUTF(suppliersList[i].getSupplierID());
                file.writeUTF(suppliersList[i].getSupplierName());
                file.writeUTF(suppliersList[i].getPhone());
            }
            System.out.println("Write done!");
        } catch (Exception e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }

    // read file
    public void readFile() throws IOException {
        try (DataInputStream file = new DataInputStream(
                new BufferedInputStream(new FileInputStream("src/main/resources/Suppliers")))) {
            count = file.readInt();
            Suppliers[] list = new Suppliers[count];
            for (int i = 0; i < count; i++) {
                String supplierID = file.readUTF();
                String supplierName = file.readUTF();
                String phone = file.readUTF();
                list[i] = new Suppliers(supplierID, supplierName, phone);
            }
            SuppliersBUS.suppliersList = list;
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }
}
