package BUS;
import java.util.Arrays;
import java.util.Scanner;
import DTO.Publishers;

public class PublishersBUS implements IRuleSets {
    private static Publishers[] publishersList;
    private static int count;
    private final Scanner scanner = new Scanner(System.in);

    // Constructor  
    public PublishersBUS () {
        PublishersBUS.publishersList = new Publishers[0];
        PublishersBUS.count = 0;
    }

    public PublishersBUS(int size, Publishers[] publishers) {
        PublishersBUS.publishersList = publishers;
        PublishersBUS.count = size;
    }

    public static Publishers[] getPublishersList() {
        return Arrays.copyOf(PublishersBUS.publishersList, PublishersBUS.count);
    }

    public static Publishers getPublisher (String id) {
        for (Publishers publisher : publishersList)
            if (publisher.getPublisherID().equals(id))
                return publisher;
        return null;
     }

    public static int getCount () {
        return count;
    }

    // all others methods like: add remove edit find show....
     // show list of publisher for user (DONE)
     public static void showList() {
        for (int i = 0; i < publishersList.length; i++)
             System.out.printf("%d: %10s %s\n", i + 1, publishersList[i].getPublisherID(), publishersList[i].getPublisherName());
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
