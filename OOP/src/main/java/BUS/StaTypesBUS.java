package BUS;

import DTO.StaTypes;
import java.util.Scanner;
public class StaTypesBUS implements IRuleSets {
    private static StaTypes[] typesList;
    private static int count;
    private final Scanner scanner = new Scanner(System.in);

    // Constructor
    public StaTypesBUS () {
        StaTypesBUS.typesList = new StaTypes[0];
        StaTypesBUS.count = 0;
    }

    public StaTypesBUS(int size, StaTypes[] list) {
        StaTypesBUS.typesList = list;
        StaTypesBUS.count = 0;
    }

    // all others methods like: add remove edit find show....
    // show list of types for user (DONE)
    public static void showList() {
    for (int i = 0; i < typesList.length; i++)
            System.out.printf("%d: %10s %s\n", i + 1, typesList[i].getTypeID(), typesList[i].getTypeName());
    }

    @Override
    public void add(Object type) {
        if (type instanceof StaTypes) {
            if (count < typesList.length) {
                typesList[count] = (StaTypes) type;
                count++;
                System.out.println("Type added successfully.");
            } else {
                System.out.println("List is full, cannot add more types.");
            }
        } else {
            System.out.println("Invalid object type. Must be a Type instance.");
        }
    }

    @Override
    public int find(String typeID) {
        for (int i = 0; i < count; i++) {
            if (typesList[i].getTypeID().equals(typeID)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void search(String typeID) {
        int index = find(typeID);
        if (index != -1) {
            System.out.println("Found Type: " + typesList[index].getTypeName());
        } else {
            System.out.println("Type not found.");
        }
    }

    @Override
    public void remove(String typeID) {
        int index = find(typeID);
        if (index != -1) {
            typesList[index] = typesList[count - 1];
            typesList[count - 1] = null;
            count--;
            System.out.println("Type removed successfully.");
        } else {
            System.out.println("Type not found.");
        }
    }

    @Override
    public void edit(String typeID) {
        int index = find(typeID);
        if (index != -1) {
            System.out.print("Enter new type name: ");
            String newName = scanner.nextLine();

            typesList[index].setTypeName(newName);
            System.out.println("Type edited successfully.");
        } else {
            System.out.println("Type not found.");
        }
    }

    public StaTypes[] getTypeList() {
        return typesList;
    }

    @Override
    public void add() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'add'");
    }

    @Override
    public void find() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'find'");
    }

    @Override
    public void search() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'search'");
    }

    @Override
    public void remove() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'remove'");
    }

    @Override
    public void edit() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'edit'");
    }
}
