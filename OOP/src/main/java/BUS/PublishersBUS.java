package BUS;
import java.util.Scanner;
import DTO.Publishers;

public class PublishersBUS implements RuleSets {
    private Publishers[] publishersList;
    private int count;
    private final Scanner scanner = new Scanner(System.in);


    // Constructor  
    public PublishersBUS () {
        publishersList = new Publishers[0];
        count = 0;
    }

    public PublishersBUS(int size) {
        publishersList = new Publishers[size];
        count = 0;
    }

    public Publishers[] getPublishersList() {
        return publishersList;
    }

    public void add(Object publisher) {
        if (publisher instanceof Publishers) {
            if (count < publishersList.length) {
                publishersList[count] = (Publishers) publisher;
                count++;
                System.out.println("Publisher added successfully.");
            } else {
                System.out.println("List is full, cannot add more publishers.");
            }
        } else {
            System.out.println("Invalid object type. Must be a Publishers instance.");
        }
    }

    public int find(String publisherID) {
        for (int i = 0; i < count; i++) {
            if (publishersList[i].getPublisherID().equals(publisherID)) {
                return i; //
            }
        }
        return -1;
    }

    @Override
    public void search(String publisherID) {
        int index = find(publisherID);
        if (index != -1) {
            System.out.println("Found Publisher: " + publishersList[index].getPublisherName());
        } else {
            System.out.println("Publisher not found.");
        }
    }

    @Override // LOGIC ERROR
    public void remove(String publisherID) {
        int index = find(publisherID);
        if (index != -1) {
            publishersList[index] = publishersList[count - 1];
            publishersList[count - 1] = null; //
            count--;
            System.out.println("Publisher removed successfully.");
        } else {
            System.out.println("Publisher not found.");
        }
    }

    @Override
    public void edit(String publisherID) {
        int index = find(publisherID);
        if (index != -1) {
            System.out.print("Enter new publisher name: ");
            String newName = scanner.nextLine();
            publishersList[index].setPublisherName(newName);
            System.out.println("Publisher edited successfully.");
        } else {
            System.out.println("Publisher not found.");
        }
    }
}
