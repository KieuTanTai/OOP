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
    private static Suppliers[] supplierList;
    private static int count;
    private final Scanner input = new Scanner(System.in);

    // Constructors
    public SuppliersBUS() {
        SuppliersBUS.count = 0;
        supplierList = new Suppliers[0];
    }

    public SuppliersBUS(Suppliers[] supplierList, int count) {
        SuppliersBUS.supplierList = supplierList;
        SuppliersBUS.count = count;
    }

    // Getters / Setters
    public static Suppliers[] getSupplierList() {
        return Arrays.copyOf(SuppliersBUS.supplierList, SuppliersBUS.count);
    }

    public static Suppliers getSupplier(String id) {
        for (Suppliers supplier : supplierList)
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
            list[size] = supplierList[i];
            size++;
        }
        return Arrays.copyOf(list, list.length);
    }

    public static int getCount() {
        return count;
    }

    public void setSupplierList(Suppliers[] supplierList) {
        SuppliersBUS.supplierList = supplierList;
    }

    public void setSupplier(String supplierID, Suppliers newSupplier) {
        for (int i = 0; i < count; i++)
            if (supplierList[i].getSupplierID().equals(supplierID)) {
                supplierList[i] = newSupplier;
                return;
            }
    }

    public void setCount(int count) {
        SuppliersBUS.count = count;
    }

    // Methods
    public static void showList() {
        if (supplierList == null)
            return;
        showAsTable(supplierList);
    }

    @Override
    public void find() {
        Menu.findHandler();
    }

    @Override
    public int find(String nameOrID) {
        for (int i = 0; i < supplierList.length; i++) {
            if (supplierList[i].getSupplierID().equals(nameOrID)
                    || supplierList[i].getSupplierName().toLowerCase().equals(nameOrID.toLowerCase().trim())) {
                return i;
            }
        }
        System.out.println("Your supplier is not found!");
        return -1;
    }

    public Suppliers[] relativeFind(String name) {
        int count = 0;
        Suppliers[] suppliersArray = new Suppliers[0];
        for (Suppliers supplier : supplierList)
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

    @Override
    public void search() {
        Menu.searchHandler();
    }

    @Override
    public void search(String nameOrID) {
        int index = find(nameOrID);
        if (index != -1)
            showAsTable(supplierList[index]);
    }

    public void relativeSearch(String name) {
        Suppliers[] list = relativeFind(name);
        if (list != null)
            showAsTable(list);
    }

    @Override
    public void add() {
        Menu.addHandler();
    }

    @Override
    public void add(Object supplier) {
        if (supplier instanceof Suppliers) {
            supplierList = Arrays.copyOf(supplierList, supplierList.length + 1);
            supplierList[count] = (Suppliers) supplier;
            count++;
        } else
            System.out.println("Your new supplier is not correct!");
    }

    public void add(Suppliers[] newSuppliers) {
        int tempIndex = 0, newListLength = newSuppliers.length;
        int initCount = getCount();
        int total = initCount + newListLength;
        supplierList = Arrays.copyOf(supplierList, supplierList.length + newListLength);

        for (int i = initCount; i < total; i++, tempIndex++)
            supplierList[i] = newSuppliers[tempIndex];
        SuppliersBUS.count = total;
    }

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
            showAsTable(supplierList[index]);
            System.out.printf("| %s %s %s |\n", "I.Cancel", "-".repeat(20), "II.Edit");
            do {
                 System.out.print("choose option (1 or 2) : ");
                 String option = input.nextLine().trim();
                 userChoose = Validate.parseChooseHandler(option, 2);
            } while (userChoose == -1);
            if (userChoose == 1)
                 return;

            String newName;
            String newPhone;
            do {
                System.out.print("Enter new name: ");
                newName = input.nextLine().trim();
                if (!Validate.checkName(newName)) {
                    System.out.println("Name is wrong format!");
                    newName = "";
                }
            } while (newName.isEmpty());
            do {
                System.out.print("Enter new phone: ");
                newPhone = input.nextLine().trim();
                if (!Validate.validatePhone(newPhone)) {
                    System.out.println("Phone is wrong format!");
                    newPhone = "";
                }
            } while (newPhone.isEmpty());
            supplierList[index].setSupplierName(newName);
            supplierList[index].setPhone(newPhone);
        }
    }

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
            showAsTable(supplierList[index]);
            System.out.printf("| %s %s %s |\n", "I.Cancel", "-".repeat(20), "II.Remove");
            do {
                 System.out.print("choose option (1 or 2) : ");
                 String option = input.nextLine().trim();
                 userChoose = Validate.parseChooseHandler(option, 2);
            } while (userChoose == -1);
            if (userChoose == 1)
                 return;

            for (int i = index; i < supplierList.length - 1; i++)
                supplierList[i] = supplierList[i + 1];
            supplierList = Arrays.copyOf(supplierList, supplierList.length - 1);
            count--;
        }
    }

    // show as table methods
    public static void showAsTable(Suppliers[] list) {
        if (list == null)
            return;
        System.out.println("=".repeat(110));
        System.out.printf("| \t%-20s %-20s %-58s |\n", "No.", "Genres ID", "Genres Name");
        System.out.println("=".repeat(110));
        for (int i = 0; i < list.length; i++) {
            if (i > 0)
                System.out.println("|" + "-".repeat(108) + "|");
            System.out.printf("| \t%-21s %-19s %-58s |\n", i + 1, list[i].getSupplierID(), list[i].getSupplierName());
        }
        System.out.println("=".repeat(110));
    }

    public static void showAsTable(Suppliers item) {
        if (item == null)
            return;
        System.out.println("=".repeat(110));
        System.out.printf("| \t%-20s %-20s %-58s |\n", "No.", "Genres ID", "Genres Name");
        System.out.println("=".repeat(110));
        System.out.println("|" + "-".repeat(108) + "|");
        System.out.printf("| \t%-21s %-19s %-58s |\n", 1, item.getSupplierID(), item.getSupplierName());
        System.out.println("=".repeat(110));
    }

    // other methods
    // write file
    public void writeFile() throws IOException {
        try (DataOutputStream file = new DataOutputStream(
                new BufferedOutputStream(new FileOutputStream("../../resources/Suppliers", false)))) {
            file.writeInt(count);
            for (int i = 0; i < count; i++) {
                file.writeUTF(supplierList[i].getSupplierID());
                file.writeUTF(supplierList[i].getSupplierName());
                file.writeUTF(supplierList[i].getPhone());
            }
            System.out.println("Write done!");
        } catch (Exception e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }

    // read file
    public void readFile() throws IOException {
        try (DataInputStream file = new DataInputStream(
                new BufferedInputStream(new FileInputStream("../../resources/Suppliers")))) {
            count = file.readInt();
            Suppliers[] list = new Suppliers[count];
            for (int i = 0; i < count; i++) {
                String supplierID = file.readUTF();
                String supplierName = file.readUTF();
                String phone = file.readUTF();
                list[i] = new Suppliers(supplierID, supplierName, phone);
            }
            SuppliersBUS.supplierList = list;
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }
}
