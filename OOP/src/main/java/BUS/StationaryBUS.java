package BUS;

import DTO.StaTypes;
import DTO.Stationary;
import Manager.Menu;
import util.Validate;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Scanner;

public class StationaryBUS implements IRuleSets {
    private Stationary[] staList;
    private int count;
    private static final Scanner input = new Scanner(System.in);

    //constructors
    public StationaryBUS() {
    }

    public StationaryBUS(Stationary[] staList, int count) {
        this.staList = staList;
        this.count = count;
    }


    // getters / setters
    public Stationary[] getStaList() {
        return this.staList;
    }

    public Stationary geStationary (int index) {
        return this.staList[index];
    }

    public void setStaList(Stationary[] staList) {
        this.staList = staList;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    // all others methods like: add remove edit find show....
    // find methods (DONE)
    @Override
    public void find() {
        Menu.findHandler();
    }

    // strict find
    @Override
    public int find(String inputValue) {
        for (int i = 0; i < staList.length; i++)
            if (staList[i].getProductID().equals(inputValue) || staList[i].getProductName().equals(inputValue))
                return i;
        return -1;
    }

    // relative find
    // return list index of products that have contains specific string
    public Stationary[] relativeFind(String name) {
        int count = 0;
        Stationary[] staArray = new Stationary[0];
        for (Stationary stationary : staArray)
            if (stationary.getProductName().contains(name)) {
                staArray = Arrays.copyOf(staArray, staArray.length + 1);
                staArray[count] = stationary;
                count++;
            }
        if (count == 0)
            return null;
        return staArray;
    }

    // add methods ()
    @Override
    public void add() {
        Menu.addHandler();
    }

    @Override
    public void add(Object stationary) {
        if (stationary instanceof Stationary) {
            staList = Arrays.copyOf(staList, staList.length + 1);
            staList[count] = (Stationary) stationary;
            count++;
        } else
            System.out.println("your new stationary have something not like stationary!");

    }

    // search methods
    @Override
    public void search() {
        Menu.searchHandler();
    }

    @Override
    public void search(String inputValue) {
        int index = find(inputValue);
        if (index == -1) {
            System.out.println("your stationary is not found!");
            return;
        }
        String toStringHandler = composeUsingFormatter(staList[index]);
        System.out.printf("stationer's id / name is : %s\nstationary detail : \n%s", inputValue, toStringHandler);
    }

    // relative search
    public void relativeSearch(String name) {
        Stationary[] indexList = relativeFind(name);
        if (indexList == null) {
            System.out.println("not found any stationary!");
            return;
        }
        for (Stationary stationary : indexList)
            System.out.printf("stationer's id : %s\ndetail : %s\n", stationary.getProductID(), composeUsingFormatter(stationary));
    }

    // advanced search
    public void advancedSearch() {

    }

    // edit methods
    @Override
    public void edit() {
        Menu.findHandler();
    }

    @Override
    public void edit(String id) {
        int index = find(id);
        if (index == -1) {
            System.out.println("your stationary is not exist !");
            return;
        }
        String newName;
        do {
            System.out.print("enter a new name for this stationary: ");
            newName = input.nextLine();
        } while (Validate.checkName(newName));
        staList[index].setProductName(newName);
    }

    // edit release date
    public void edit(String id, LocalDate newDate) {
        int index = find(id);
        if (index == -1) {
            System.out.println("your book is not exist !");
            return;
        }
        staList[index].setReleaseDate(newDate);
    }

    // edit price
    public void edit(String id, BigDecimal newPrice) {
        int index = find(id);
        if (index == -1) {
            System.out.println("your book is not exist !");
            return;
        }
        staList[index].setProductPrice(newPrice);
    }

    // edit quantity
    public void edit(String id, int newQuantity) {
        int index = find(id);
        if (index == -1) {
            System.out.println("your book is not exist !");
            return;
        }
        staList[index].setQuantity(newQuantity);
    }

    // edit type
    public void edit(String id, StaTypes newType) {
        int index = find(id);
        if (index == -1) {
            System.out.println("your book is not exist !");
            return;
        }
        staList[index].setType(newType);
    }

    // edit brand
    public void edit(String id, String brand) {
        int index = find(id);
        if (index == -1) {
            System.out.println("your book is not exist !");
            return;
        }
        staList[index].setBrand(brand);
    }

    // remove methods (DONE)
    @Override
    public void remove() {
        Menu.removeHandler();
    }

    @Override
    public void remove(String id) {
        int index = find(id);
        if (index == -1) {
            System.out.println("your stationary is not found !");
            return;
        }
        for (int i = index; i < staList.length - 1; i++)
            staList[i] = staList[i + 1];
        staList = Arrays.copyOf(staList, staList.length - 1);
        count--;

    }

    // some other methods

    // execute files
    //write file
    public void writeFile () throws IOException {
        try (DataOutputStream file = new DataOutputStream(new FileOutputStream("../../resources/ListGenres", false))) {
            file.writeInt(count);
            for (int i = 0; i < count; i++) {
                    file.writeUTF(staList[i].getProductID());
                    file.writeUTF(staList[i].getStationaryID());
                    file.writeUTF(staList[i].getProductName());
                    file.writeUTF(staList[i].getProductPrice().toString());
                    file.writeUTF(staList[i].getReleaseDate().toString());
                    file.writeUTF(staList[i].getType().getTypeID());
                    file.writeUTF(staList[i].getBrand());
                    file.writeInt(staList[i].getQuantity());
                    file.writeUTF(staList[i].getMaterial());
                    file.writeUTF(staList[i].getSource());
                    file.writeUTF(System.lineSeparator());
            }
            System.out.println("write done!");
        } catch (FileNotFoundException err) {
            System.out.printf("404 not found!\n%s", err);
        }
    }

    // read file
    public void readFile () throws IOException {
        try (DataInputStream file = new DataInputStream(new FileInputStream("../../resources/ListGenres"))) {
            count = file.readInt();
            Stationary[] list = new Stationary[count];
            for (int i = 0; i < count; i++) {
                    String productID =  file.readUTF();
                    String stationaryID = file.readUTF();
                    String productName = file.readUTF();
                    BigDecimal price = new BigDecimal(file.readUTF());
                    LocalDate releaseDate = LocalDate.parse(file.readUTF());
                    String typeID = file.readUTF();
                    String brand = file.readUTF();
                    int quantity = file.readInt();
                    String material = file.readUTF();  //use as param for query publisher from class Publishers
                    String source = file.readUTF();
                    file.readUTF();

                    // execute IDs
                    StaTypes type = StaTypesBUS.getStaType(typeID);
                    list[i] = new Stationary(productID, stationaryID, productName, releaseDate, price, quantity, type, brand, material, source);
            }
            setCount(count);
            setStaList(list);
        } catch (FileNotFoundException err) {
            System.out.printf("404 not found!\n%s", err);
        }
    }

    private String composeUsingFormatter(Stationary stationary) {
        return String.format(" stationary id: %s\n type: %s\n brand: %s\n material: %s\n source: %s\n",
                stationary.getStationaryID(), stationary.getType().getTypeName(), stationary.getBrand(), stationary.getMaterial(), stationary.getSource());
    }
}
