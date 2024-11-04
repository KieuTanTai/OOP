package BUS;

import DTO.Type;
import java.util.Scanner;
public class TypeBUS implements RuleSets {
    private Type[] typeList;
    private int count;

    // Constructor
    public TypeBUS(int size) {
        typeList = new Type[size];
        count = 0;
    }

    @Override
    public void add(Object type) {
        if (type instanceof Type) {
            if (count < typeList.length) {
                typeList[count] = (Type) type;
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
            if (typeList[i].getTypeID().equals(typeID)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void search(String typeID) {
        int index = find(typeID);
        if (index != -1) {
            System.out.println("Found Type: " + typeList[index].getTypeName());
        } else {
            System.out.println("Type not found.");
        }
    }

    @Override
    public void remove(String typeID) {
        int index = find(typeID);
        if (index != -1) {
            typeList[index] = typeList[count - 1];
            typeList[count - 1] = null;
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
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter new type name: ");
            String newName = scanner.nextLine();

            typeList[index].setTypeName(newName);
            System.out.println("Type edited successfully.");
        } else {
            System.out.println("Type not found.");
        }
    }

    public Type[] getTypeList() {
        return typeList;
    }
}
