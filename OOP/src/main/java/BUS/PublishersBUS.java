package BUS;

import DTO.Publishers;
import Manager.Menu;
import util.Validate;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class PublishersBUS implements IRuleSets {
    private static Publishers[] publishersList;
    private static int count;
    private final Scanner scanner = new Scanner(System.in);

    // *Constructors (TEST DONE) 
    public PublishersBUS() {
        PublishersBUS.count = 0;
        publishersList = new Publishers[0];
    }

    public PublishersBUS(Publishers[] publishersList, int count) {
        PublishersBUS.publishersList = publishersList;
        PublishersBUS.count = count;
    }

    // *Getter / Setter (TEST DONE)
    public static Publishers[] getPublishersList() {
        return Arrays.copyOf(PublishersBUS.publishersList, PublishersBUS.count);
    }

    public static Publishers getPublisher(String id) {
        for (Publishers publisher : publishersList)
            if (publisher.getPublisherID().equals(id))
                return new Publishers(publisher.getPublisherID(), publisher.getPublisherName());
        return null;
    }
    

    public static int getCount() {
        return count;
    }

    public void setPublishersList(Publishers[] publishersList) {
        PublishersBUS.publishersList = publishersList;
    }

    public void setPublisher(String publisherID, Publishers newPublisher) {
        for (int i = 0; i < count; i++)
            if (publishersList[i].getPublisherID().equals(publisherID)) {
                publishersList[i] = newPublisher;
                return;
            }
    }

    public void setCount(int count) {
        PublishersBUS.count = count;
    }

    // all others methods like: add remove edit find show....
    // display list of types for user 
    public static void showList() {
        if (publishersList == null)
            return;
        for (int i = 0; i < publishersList.length; i++)
            System.out.printf("%-3d: %-6s %s\n", i + 1, publishersList[i].getPublisherID(), publishersList[i].getPublisherName());
    }

    // *Find methods (TEST DONE)
    @Override
    public void find() {
        Menu.findHandler();
    }

    @Override
    public int find(String nameOrID) {
        for (int i = 0; i < publishersList.length; i++) {
            if (publishersList[i].getPublisherID().equals(nameOrID) || publishersList[i].getPublisherName().toLowerCase().equals(nameOrID.toLowerCase().trim()))
                return i;
        }
        System.out.println("your publisher is not found!");
        return -1;
    }

    public Publishers[] relativeFind(String name) {
        int count = 0;
        Publishers[] publishersArray = new Publishers[0];
        for (Publishers publisher : publishersList)
            if (publisher.getPublisherName().toLowerCase().contains(name.toLowerCase())) {
                publishersArray = Arrays.copyOf(publishersArray, publishersArray.length + 1);
                publishersArray[count] = publisher;
                count++;
            }
        if (count == 0) {
            System.out.println("not found any publishers!");
            return null;
        }
        return publishersArray;
    }

    // *Search methods (TEST DONE)
    @Override
    public void search() {
        Menu.searchHandler();
    }

    @Override
    public void search(String nameOrID) {
        int index = find(nameOrID);
        if (index != -1)
            System.out.printf("id: %-6s name: %s\n", publishersList[index].getPublisherID(), publishersList[index].getPublisherName());
    }

    public void relativeSearch(String name) {
        Publishers[] list = relativeFind(name);
        if (list != null) {
            System.out.println("-----------------------------------------------");
            for (Publishers publisher : list)
                System.out.printf("id: %-6s name: %s\n", publisher.getPublisherID(), publisher.getPublisherName());
            System.out.println("-----------------------------------------------");
        }
    }

    // *Add methods (TEST DONE) 
    @Override
    public void add() {
        Menu.addHandler();
    }

    @Override
    public void add(Object publisher) {
        if (publisher instanceof Publishers) {
            publishersList = Arrays.copyOf(publishersList, publishersList.length + 1);
            publishersList[count] = (Publishers) publisher;
            count++;
        } else {
            System.out.println("your new publisher is not correct!");
        }
    }

    public void add(Publishers[] newPublishers, int size) {
        publishersList = Arrays.copyOf(publishersList, publishersList.length + newPublishers.length);

        int tempIndex = 0;
        int initCount = getCount();
        int total = initCount + size;

        for (int i = initCount; i < total; i++, tempIndex++)
            publishersList[i] = newPublishers[tempIndex];
        PublishersBUS.count = total;
    }

    // *Edit methods (TEST DONE)
    @Override
    public void edit() {
        Menu.editHandler();
    }

    @Override
    public void edit(String nameOrID) {
        int index = find(nameOrID);
        if (index != -1) {
            String newName;
            do {
                System.out.print("enter new name: ");
                newName = scanner.nextLine().trim();
                if (!Validate.checkName(newName)) {
                    System.out.println("invalid name format!");
                    newName = "";
                }
            } while (newName.isEmpty());
            publishersList[index].setPublisherName(newName);
        }
    }

    // *Remove methods (TEST DONE) 
    @Override
    public void remove() {
        Menu.removeHandler();
    }

    @Override
    public void remove(String nameOrID) {
        int index = find(nameOrID);
        if (index != -1) {
            for (int i = index; i < publishersList.length - 1; i++)
                publishersList[i] = publishersList[i + 1];
            publishersList = Arrays.copyOf(publishersList, publishersList.length - 1);
            count--;
        }
    }

    // Write file 
    public void writeFile() throws IOException {
        try (DataOutputStream file = new DataOutputStream(new FileOutputStream("OOP/src/main/resources/Publishers", false))) {
            file.writeInt(count);
            for (int i = 0; i < count; i++) {
                file.writeUTF(publishersList[i].getPublisherID());
                file.writeUTF(publishersList[i].getPublisherName());
            }
        } catch (Exception err) {
            System.out.printf("error writing file!\n%s\n", err.getMessage());
        }
    }

    // Read file 
    public void readFile() throws IOException {
        try (DataInputStream file = new DataInputStream(new FileInputStream("OOP/src/main/resources/Publishers"))) {
            int count = file.readInt();
            Publishers[] list = new Publishers[count];
            for (int i = 0; i < count; i++) {
                String publisherID = file.readUTF();
                String publisherName = file.readUTF();
                list[i] = new Publishers(publisherID, publisherName);
            }
            setCount(count);
            setPublishersList(list);
        } catch (Exception err) {
            System.out.printf("error reading file!\n%s\n", err.getMessage());
        }
    }
}
