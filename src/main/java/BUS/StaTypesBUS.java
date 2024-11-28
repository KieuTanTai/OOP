package BUS;

import DTO.StaTypes;
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

public class StaTypesBUS implements IRuleSets {
    private static StaTypes[] typesList;
    static int count;
    private final Scanner input = new Scanner(System.in);

    // *Constructors (TEST DONE)
    public StaTypesBUS() {
        StaTypesBUS.count = 0;
        typesList = new StaTypes[0];
    }

    public StaTypesBUS(StaTypes[] typesList, int count) {
        StaTypesBUS.typesList = typesList;
        StaTypesBUS.count = count;
    }

    // Getter/Setter
    public static StaTypes[] getTypesList() {
        return Arrays.copyOf(StaTypesBUS.typesList, StaTypesBUS.count);
    }

    public static StaTypes getType(String id) {
        for (StaTypes type : typesList)
            if (type.getTypeID().equals(id))
                return new StaTypes(type.getTypeID(), type.getTypeName());
        return null;
    }

    public static StaTypes[] getTypes(int start, int end) {
        int size = 0;
        StaTypes[] list = new StaTypes[0];
        if (!Validate.checkValidRange(start, end))
            return null;
        for (int i = start; i < end; i++) {
            list = Arrays.copyOf(list, list.length + 1);
            list[size] = typesList[i];
            size++;
        }
        return Arrays.copyOf(list, list.length);
    }

    public static int getCount() {
        return count;
    }

    public void setTypesList(StaTypes[] typesList) {
        StaTypesBUS.typesList = typesList;
    }

    public void setType(String typeID, StaTypes newType) {
        for (int i = 0; i < count; i++)
            if (typesList[i].getTypeID().equals(typeID)) {
                typesList[i] = newType;
                return;
            }
    }

    public void setCount(int count) {
        StaTypesBUS.count = count;
    }

    // all others methods like: add remove edit find show....
    // *display list of types for user (TEST DONE)
    public static void showList() {
        if (typesList == null)
            return;
        showAsTable(typesList);
    }

    // *Find methods (TEST DONE)
    @Override
    public int find(String nameOrID) {
        for (int i = 0; i < typesList.length; i++) {
            if (typesList[i].getTypeID().equals(nameOrID)
                    || typesList[i].getTypeName().toLowerCase().equals(nameOrID.toLowerCase().trim()))
                return i;
        }
        System.out.println("your type is not found!");
        return -1;
    }

    public StaTypes[] relativeFind(String name) {
        int count = 0;
        StaTypes[] typesArray = new StaTypes[0];
        for (StaTypes type : typesList)
            if (type.getTypeName().toLowerCase().contains(name.toLowerCase())) {
                typesArray = Arrays.copyOf(typesArray, typesArray.length + 1);
                typesArray[count] = type;
                count++;
            }
        if (count == 0) {
            System.out.println("not found any types!");
            return null;
        }
        return typesArray;
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
            System.out.print("Enter your choice : ");
            String inputChoice = input.nextLine().trim();
            // validate if user choose 0
            if (inputChoice.equals("0")) {
                 System.out.println("Exit program.");
                 break;
            }
            choice = Validate.parseChooseHandler(inputChoice, 2);
            if (choice == -1)
                break;

            System.out.println("Enter name or id of type : ");
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
            showAsTable(typesList[index]);
    }

    public void relativeSearch(String name) {
        StaTypes[] list = relativeFind(name);
        if (list != null)
            showAsTable(list);
    }

    // *Add methods (TEST DONE)
    @Override
    public void add() {
        int choice;
        do {
            System.out.println("*".repeat(60));
            System.out.println("I. Add type");
            System.out.println("II. Add list of types");
            System.out.println("0. Exit");
            System.out.println("*".repeat(60));
            System.out.print("Enter your choice : ");
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
                        StaTypes newType = new StaTypes();
                        newType.setInfo();
                        // confirm
                        System.out.printf("| %s %s %s |\n", "I.Cancel", "-".repeat(20), "II.Add");
                        do {
                            System.out.print("choose option (1 or 2) : ");
                            String option = input.nextLine().trim();
                            choice = Validate.parseChooseHandler(option, 2);
                        } while (choice == -1);
                        if (choice == 1)
                            break;
                        add(newType);
                        writeFile();
                        break;
                    case 2:
                        int count = 0;
                        StaTypes[] list = new StaTypes[0];
                        do {
                            System.out.print("Enter total types you wanna add : ");
                            String option = input.nextLine().trim();
                            choice = Validate.isNumber(option);
                        } while (choice == -1);
                        // for loop with input time
                        for (int i = 0; i < choice; i++) {
                            StaTypes type = new StaTypes();
                            type.setInfo();
                            list = Arrays.copyOf(list, list.length + 1);
                            list[count] = type;
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
    public void add(Object type) {
        if (type instanceof StaTypes newType) {
            newType.setTypeID(newType.getTypeID());
            typesList = Arrays.copyOf(typesList, typesList.length + 1);
            typesList[count] = newType;
            count++;
        } else {
            System.out.println("your type is not correct!");
        }
    }

    public void add(StaTypes[] newTypes) {
        int tempIndex = 0, newListLength = newTypes.length;
        int initCount = getCount();
        int total = initCount + newListLength;
        typesList = Arrays.copyOf(typesList, typesList.length + newListLength);

        for (int i = initCount; i < total; i++, tempIndex++) {
            newTypes[tempIndex].setTypeID(newTypes[tempIndex].getTypeID());
            typesList[i] = newTypes[tempIndex];
        }
        StaTypesBUS.count = total;
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
            System.out.print("Enter your choice : ");
            String inputChoice = input.nextLine().trim();
            // validate if user choose 0
            if (inputChoice.equals("0")) {
                 System.out.println("Exit program.");
                 break;
            }
            choice = Validate.parseChooseHandler(inputChoice, 1);
            if (choice == 1) {
                try {
                    System.out.println("Enter name or id of type : ");
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
            // show list for user choose
            showAsTable(typesList[index]);
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
                System.out.print("Enter new name : ");
                newName = input.nextLine().trim();
                if (!Validate.checkName(newName)) {
                    System.out.println("name is wrong format!");
                    newName = "";
                }
            } while (newName.isEmpty());
            typesList[index].setTypeName(newName);
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
            System.out.print("Enter your choice : ");
            String inputChoice = input.nextLine().trim();
            // validate if user choose 0
            if (inputChoice.equals("0")) {
                 System.out.println("Exit program.");
                 break;
            }
            choice = Validate.parseChooseHandler(inputChoice, 1);
            if (choice == 1) {
                try {
                    System.out.println("Enter name or id of type : ");
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
            // show list for user choose
            showAsTable(typesList[index]);
            System.out.printf("| %s %s %s |\n", "I.Cancel", "-".repeat(20), "II.Remove");
            do {
                System.out.print("choose option (1 or 2) : ");
                String option = input.nextLine().trim();
                userChoice = Validate.parseChooseHandler(option, 2);
            } while (userChoice == -1);
            if (userChoice == 1)
                return;

            for (int i = index; i < typesList.length - 1; i++)
                typesList[i] = typesList[i + 1];
            typesList = Arrays.copyOf(typesList, typesList.length - 1);
            count--;
        }
    }

    // show as table methods
    public static void showAsTable(StaTypes[] list) {
        if (list == null)
            return;
        System.out.println("=".repeat(110));
        System.out.printf("| \t%-20s %-20s %-58s |\n", "No.", "Types ID", "Types Name");
        System.out.println("=".repeat(110));
        for (int i = 0; i < list.length; i++) {
            if (i > 0)
                System.out.println("|" + "-".repeat(108) + "|");
            System.out.printf("| \t%-21s %-19s %-58s |\n", i + 1, list[i].getTypeID(), list[i].getTypeName());
        }
        System.out.println("=".repeat(110));
    }

    public static void showAsTable(StaTypes item) {
        if (item == null)
            return;
        System.out.println("=".repeat(110));
        System.out.printf("| \t%-20s %-20s %-58s |\n", "No.", "Types ID", "Types Name");
        System.out.println("=".repeat(110));
        System.out.println("|" + "-".repeat(108) + "|");
        System.out.printf("| \t%-21s %-19s %-58s |\n", 1, item.getTypeID(), item.getTypeName());
        System.out.println("=".repeat(110));
    }

    // *Write file (TEST DONE)
    public void writeFile() throws IOException {
        try (DataOutputStream file = new DataOutputStream(
                new BufferedOutputStream(new FileOutputStream("src/main/resources/StaTypes", false)))) {
            file.writeInt(count);
            for (StaTypes type : typesList) {
                file.writeUTF(type.getTypeID());
                file.writeUTF(type.getTypeName());
            }
        } catch (Exception err) {
            System.out.printf("error writing file!\n%s\n", err.getMessage());
        }
    }

    // *Read file (TEST DONE)
    public void readFile() throws IOException {
        File testFile = new File("src/main/resources/StaTypes");
        if (testFile.length() == 0)
            return;

        try (DataInputStream file = new DataInputStream(
                new BufferedInputStream(new FileInputStream("src/main/resources/StaTypes")))) {
            int count = file.readInt();
            StaTypes[] list = new StaTypes[count];
            for (int i = 0; i < count; i++) {
                String typeID = file.readUTF();
                String typeName = file.readUTF();
                list[i] = new StaTypes(typeID, typeName);
            }
            setCount(count);
            setTypesList(list);
        } catch (Exception err) {
            System.out.printf("error reading file!\n%s\n", err.getMessage());
        }
    }
}
