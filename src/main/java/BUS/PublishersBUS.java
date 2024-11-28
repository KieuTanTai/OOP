package BUS;

import DTO.Publishers;
import util.Validate;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class PublishersBUS implements IRuleSets {
    private static Publishers[] publishersList;
    private static int count;
    private final Scanner input = new Scanner(System.in);

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
        showAsTable(publishersList);
    }

    // *Find methods (TEST DONE)
    @Override
    public int find(String nameOrID) {
        for (int i = 0; i < publishersList.length; i++) {
            if (publishersList[i].getPublisherID().equals(nameOrID)
                    || publishersList[i].getPublisherName().toLowerCase().equals(nameOrID.toLowerCase().trim()))
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
        int choice;
        do {
            System.out.println("*".repeat(60));
            System.out.println("I. Strict search");
            System.out.println("II. Relative search");
            System.out.println("0. Exit");
            System.out.println("*".repeat(60));
            System.out.print("Enter your choice: ");
            String inputChoice = input.nextLine().trim();
            // validate if user choose 0
            if (inputChoice.equals("0")) {
                 System.out.println("Exit program.");
                 break;
            }
            choice = Validate.parseChooseHandler(inputChoice, 2);
            if (choice == -1)
                break;

            System.out.println("Enter name or id of publisher: ");
            String userInput = input.nextLine().trim();
            // if case
            if (choice == 1)
                search(userInput);
            else if (choice == 2)
                relativeSearch(userInput);

        } while (choice != 0);
    }

    @Override
    public void search(String nameOrID) {
        int index = find(nameOrID);
        if (index != -1)
            showAsTable(publishersList[index]);
    }

    public void relativeSearch(String name) {
        Publishers[] list = relativeFind(name);
        if (list != null)
            showAsTable(list);
    }

    // *Add methods (TEST DONE)
    @Override
    public void add() {
        int choice;
        do {
            System.out.println("*".repeat(60));
            System.out.println("I. Add publisher");
            System.out.println("II. Add list of publishers");
            System.out.println("0. Exit");
            System.out.println("*".repeat(60));
            System.out.print("Enter your choice: ");
            String inputChoice = input.nextLine().trim();
            // validate if user choose 0
            if (inputChoice.equals("0")) {
                 System.out.println("Exit program.");
                 break;
            }
            choice = Validate.parseChooseHandler(inputChoice, 2);

            try {
                switch (choice) {
                    case 1:
                        Publishers newPublisher = new Publishers();
                        newPublisher.setInfo();
                        // confirm
                        System.out.printf("| %s %s %s |\n", "I.Cancel", "-".repeat(20), "II.Add");
                        do {
                            System.out.print("choose option (1 or 2) : ");
                            String option = input.nextLine().trim();
                            choice = Validate.parseChooseHandler(option, 2);
                        } while (choice == -1);
                        if (choice == 1)
                            break;
                        add(newPublisher);
                        writeFile();
                        break;
                    case 2:
                        int count = 0;
                        Publishers[] list = new Publishers[0];
                        do {
                            System.out.print("Enter total format you wanna add : ");
                            String option = input.nextLine().trim();
                            choice = Validate.isNumber(option);
                        } while (choice == -1);
                        // for loop with input time
                        for (int i = 0; i < choice; i++) {
                            Publishers publisher = new Publishers();
                            publisher.setInfo();
                            list = Arrays.copyOf(list, list.length + 1);
                            list[count] = publisher;
                            count++;
                        }

                        // confirm
                        System.out.printf("| %s %s %s |\n", "I.Cancel", "-".repeat(20), "II.Add");
                        do {
                            System.out.print("choose option (1 or 2) : ");
                            String option = input.nextLine().trim();
                            choice = Validate.parseChooseHandler(option, 2);
                        } while (choice == -1);
                        if (choice == 1)
                            break;
                        add(list);
                        writeFile();
                        break;
                }
            } catch (Exception e) {
                System.out.printf("error writing file!\nt%s\n", e.getMessage());
            }

        } while (choice != 0);
    }

    @Override
    public void add(Object publisher) {
        if (publisher instanceof Publishers newPublisher) {
            newPublisher.setPublisherID(newPublisher.getPublisherID());
            publishersList = Arrays.copyOf(publishersList, publishersList.length + 1);
            publishersList[count] = newPublisher;
            count++;
        } else {
            System.out.println("your new publisher is not correct!");
        }
    }

    public void add(Publishers[] newPublishers) {
        int tempIndex = 0, newListLength = newPublishers.length;
        int initCount = getCount();
        int total = initCount + newListLength;
        publishersList = Arrays.copyOf(publishersList, publishersList.length + newListLength);

        for (int i = initCount; i < total; i++, tempIndex++) {
            newPublishers[tempIndex].setPublisherID(newPublishers[tempIndex].getPublisherID());
            publishersList[i] = newPublishers[tempIndex];
        }
        PublishersBUS.count = total;
    }

    // *Edit methods (TEST DONE)
    @Override
    public void edit() {
        int choice;
        do {
            System.out.println("*".repeat(60));
            System.out.println("I. Edit");
            System.out.println("0. Exit");
            System.out.println("*".repeat(60));
            System.out.print("Enter your choice: ");
            String inputChoice = input.nextLine().trim();
            // validate if user choose 0
            if (inputChoice.equals("0")) {
                 System.out.println("Exit program.");
                 break;
            }
            choice = Validate.parseChooseHandler(inputChoice, 1);
            if (choice == 1) {
                try {
                    System.out.println("Enter name or id of publisher: ");
                    String userInput = input.nextLine().trim();
                    edit(userInput);
                    writeFile();
                } catch (Exception e) {
                    System.out.printf("error writing file!\nt%s\n", e.getMessage());
                }
            }
        } while (choice != 0);
    }

    @Override
    public void edit(String nameOrID) {
        int index = find(nameOrID);
        if (index != -1) {
            int userChoice;
            // show list for user choice
            showAsTable(publishersList[index]);
            System.out.printf("| %s %s %s |\n", "I.Cancel", "-".repeat(20), "II.Edit");
            do {
                System.out.print("choose option (1 or 2) : ");
                String option = input.nextLine().trim();
                userChoice = Validate.parseChooseHandler(option, 2);
            } while (userChoice == -1);
            if (userChoice == 1)
                return;

            String newName;
            do {
                System.out.print("enter new name: ");
                newName = input.nextLine().trim();
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
        int choice;
        do {
            System.out.println("*".repeat(60));
            System.out.println("I. Remove");
            System.out.println("0. Exit");
            System.out.println("*".repeat(60));
            System.out.print("Enter your choice: ");
            String inputChoice = input.nextLine().trim();
            // validate if user choose 0
            if (inputChoice.equals("0")) {
                 System.out.println("Exit program.");
                 break;
            }
            choice = Validate.parseChooseHandler(inputChoice, 1);
            if (choice == 1) {
                try {
                    System.out.println("Enter name or id of publisher: ");
                    String userInput = input.nextLine().trim();
                    remove(userInput);
                    writeFile();
                } catch (Exception e) {
                    System.out.printf("error writing file!\nt%s\n", e.getMessage());
                }
            }
        } while (choice != 0);
    }

    @Override
    public void remove(String nameOrID) {
        int index = find(nameOrID);
        if (index != -1) {
            int userChoice;
            // show list for user choice
            showAsTable(publishersList[index]);
            System.out.printf("| %s %s %s |\n", "I.Cancel", "-".repeat(20), "II.Remove");
            do {
                System.out.print("choose option (1 or 2) : ");
                String option = input.nextLine().trim();
                userChoice = Validate.parseChooseHandler(option, 2);
            } while (userChoice == -1);
            if (userChoice == 1)
                return;

            for (int i = index; i < publishersList.length - 1; i++)
                publishersList[i] = publishersList[i + 1];
            publishersList = Arrays.copyOf(publishersList, publishersList.length - 1);
            count--;
        }
    }

    // show as table methods
    public static void showAsTable(Publishers[] list) {
        if (list == null)
            return;
        System.out.println("=".repeat(110));
        System.out.printf("| \t%-20s %-20s %-58s |\n", "No.", "Publishers ID", "Publishers Name");
        System.out.println("=".repeat(110));
        for (int i = 0; i < list.length; i++) {
            if (i > 0)
                System.out.println("|" + "-".repeat(108) + "|");
            System.out.printf("| \t%-21s %-19s %-58s |\n", i + 1, list[i].getPublisherID(), list[i].getPublisherName());
        }
        System.out.println("=".repeat(110));
    }

    public static void showAsTable(Publishers item) {
        if (item == null)
            return;
        System.out.println("=".repeat(110));
        System.out.printf("| \t%-20s %-20s %-58s |\n", "No.", "Publishers ID", "Publishers Name");
        System.out.println("=".repeat(110));
        System.out.println("|" + "-".repeat(108) + "|");
        System.out.printf("| \t%-21s %-19s %-58s |\n", 1, item.getPublisherID(), item.getPublisherName());
        System.out.println("=".repeat(110));
    }

    // *Write file (TEST DONE)
    public void writeFile() throws IOException {
        try (DataOutputStream file = new DataOutputStream(
                new BufferedOutputStream(new FileOutputStream("src/main/resources/Publishers", false)))) {
            file.writeInt(count);
            for (Publishers publisher : publishersList) {
                file.writeUTF(publisher.getPublisherID());
                file.writeUTF(publisher.getPublisherName());
            }
        } catch (Exception err) {
            System.out.printf("error writing file!\n%s\n", err.getMessage());
        }
    }

    // *Read file (TEST DONE)
    public void readFile() throws IOException {
        File testFile = new File("src/main/resources/Publishers");
        if (testFile.length() == 0)
            return;

        try (DataInputStream file = new DataInputStream(
                new BufferedInputStream(new FileInputStream("src/main/resources/Publishers")))) {
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
