package BUS;

import DTO.Publishers;
import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class PublishersBUS {
    private static Publishers[] publishersList;
    private static int count;

    // Constructor: Initializes with an empty list and count 0
    public PublishersBUS() {
        PublishersBUS.count = 0;
        publishersList = new Publishers[0];
    }

    // Constructor: Initializes with specified count and list
    public PublishersBUS(int count, Publishers[] publishersList) {
        PublishersBUS.count = count;
        PublishersBUS.publishersList = publishersList;
    }

    // Getter: Returns count of publishers
    public int getCount() {
        return PublishersBUS.count;
    }

    // Getter: Returns the publishers list
    public Publishers[] getPublishersList() {
        return PublishersBUS.publishersList;
    }

    // Setter: Sets count of publishers
    public void setCount(int count) {
        PublishersBUS.count = count;
    }

    // Setter: Sets publishers list
    public void setPublishersList(Publishers[] publishersList) {
        PublishersBUS.publishersList = publishersList;
    }

    // Show all publishers in the list
    public static void showList() {
        for (int i = 0; i < publishersList.length; i++) {
            System.out.printf("%d: %10s %s\n", i + 1, publishersList[i].getPublisherID(), publishersList[i].getPublisherName());
        }
    }

    // Add a new Publisher to the list
    public static void add(Publishers publisher) {
        publishersList = Arrays.copyOf(publishersList, publishersList.length + 1);
        publishersList[count] = publisher;
        count++;
        System.out.println("Publisher added successfully.");
    }

    // Find the index of a Publisher by publisherID
    public int find(String publisherID) {
        for (int i = 0; i < publishersList.length; i++) {
            if (publishersList[i].getPublisherID().equals(publisherID)) {
                return i;
            }
        }
        return -1;
    }

    // Remove a Publisher from the list by publisherID
    public void remove(String publisherID) {
        int index = find(publisherID);
        if (index != -1) {
            for (int i = index; i < publishersList.length - 1; i++) {
                publishersList[i] = publishersList[i + 1];
            }
            publishersList = Arrays.copyOf(publishersList, publishersList.length - 1);
            count--;
            System.out.println("Publisher removed successfully.");
        } else {
            System.out.println("Publisher not found.");
        }
    }

    // Search for a Publisher by publisherID and display it
    public void search(String publisherID) {
        int index = find(publisherID);
        if (index != -1) {
            System.out.printf("Found Publisher: %s - %s\n", publishersList[index].getPublisherID(), publishersList[index].getPublisherName());
        } else {
            System.out.println("Publisher not found.");
        }
    }

    // Edit the publisherName of a Publisher by publisherID
    public void edit(String publisherID) {
        int index = find(publisherID);
        if (index != -1) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter new publisher name: ");
            String newPublisherName = scanner.nextLine();
            publishersList[index].setPublisherName(newPublisherName);
            System.out.println("Publisher updated successfully.");
        } else {
            System.out.println("Publisher not found.");
        }
    }

    // Write the list of Publishers to a file
    public void writeFile() throws IOException {
        try (DataOutputStream file = new DataOutputStream(new FileOutputStream("../../resources/Publishers", false))) {
            file.writeInt(count);
            for (int i = 0; i < count; i++) {
                file.writeUTF(publishersList[i].getPublisherID());
                file.writeUTF(publishersList[i].getPublisherName());
            }
            System.out.println("Write done!");
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }

    // Read the list of Publishers from a file
    public void readFile() throws IOException {
        try (DataInputStream file = new DataInputStream(new FileInputStream("../../resources/Publishers"))) {
            count = file.readInt();
            Publishers[] list = new Publishers[count];
            for (int i = 0; i < count; i++) {
                String publisherID = file.readUTF();
                String publisherName = file.readUTF();
                list[i] = new Publishers(publisherID, publisherName);
            }
            PublishersBUS.publishersList = list;
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }
}
