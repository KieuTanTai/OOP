package BUS;

import DTO.StaTypes;
import Manager.Menu;
import util.Validate;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class StaTypesBUS implements IRuleSets {
    private static StaTypes[] typesList;
    private static int count;
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
        for (int i = 0; i < typesList.length; i++)
            System.out.printf("%-3d: %-6s %s\n", i + 1, typesList[i].getTypeID(), typesList[i].getTypeName());
    }

    // Find methods
    @Override
    public void find() {
        Menu.findHandler();
    }

    @Override
    public int find(String nameOrID) {
        for (int i = 0; i < typesList.length; i++) {
            if (typesList[i].getTypeID().equals(nameOrID) || typesList[i].getTypeName().equals(nameOrID))
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
            System.out.println("not found any type!");
            return null;
        }
        return typesArray;
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
            System.out.printf("id: %-6s name: %s\n", typesList[index].getTypeID(), typesList[index].getTypeName());
    }

    public void relativeSearch(String name) {
        StaTypes[] list = relativeFind(name);
        if (list != null) {
            System.out.println("-----------------------------------------------");
            for (StaTypes type : list)
                System.out.printf("id: %-6s name: %s\n", type.getTypeID(), type.getTypeName());
            System.out.println("-----------------------------------------------");
        }
    }

    // *Add methods (TEST DONE)
    @Override
    public void add() {
        Menu.addHandler();
    }

    @Override
    public void add(Object type) {
        if (type instanceof StaTypes) {
            typesList = Arrays.copyOf(typesList, typesList.length + 1);
            typesList[count] = (StaTypes) type;
            count++;
        } else {
            System.out.println("your type is not correct!");
        }
    }

    public void add(StaTypes[] newTypes, int count) {
        typesList = Arrays.copyOf(typesList, typesList.length + newTypes.length);

        int tempIndex = 0;
        int initCount = getCount();
        int total = initCount + count;

        for (int i = initCount; i < total; i++, tempIndex++)
            typesList[i] = newTypes[tempIndex];
        StaTypesBUS.count = total;
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
                System.out.print("Enter new name: ");
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
        Menu.removeHandler();
    }

    @Override
    public void remove(String nameOrID) {
        int index = find(nameOrID);
        if (index != -1) {
            for (int i = index; i < typesList.length - 1; i++)
                typesList[i] = typesList[i + 1];
            typesList = Arrays.copyOf(typesList, typesList.length - 1);
            count--;
        }
    }

    // *Write file (TEST DONE)
    public void writeFile() throws IOException {
        try (DataOutputStream file = new DataOutputStream(
                new FileOutputStream("OOP/src/main/resources/StaTypes", false))) {
            file.writeInt(count);
            for (int i = 0; i < count; i++) {
                file.writeUTF(typesList[i].getTypeID());
                file.writeUTF(typesList[i].getTypeName());
            }
        } catch (Exception err) {
            System.out.printf("404 not found!\n%s", err.getMessage());
        }
    }

    // *Read file (TEST DONE)
    public void readFile() throws IOException {
        try (DataInputStream file = new DataInputStream(getClass().getResourceAsStream("/StaTypes"))) {
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
            System.out.printf("404 not found!\n%s", err.getMessage());
        }
    }
}
