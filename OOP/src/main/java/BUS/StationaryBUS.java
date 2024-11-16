package BUS;

import DTO.StaTypes;
import DTO.Stationary;
import Manager.Menu;
import util.Validate;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
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

    // constructors
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

    public Stationary geStationary(int index) {
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
    public int find(String nameOrID) {
        for (int i = 0; i < staList.length; i++)
            if (staList[i].getProductID().equals(nameOrID) || staList[i].getProductName().toLowerCase().equals(nameOrID.toLowerCase().trim())
                    || staList[i].getStationaryID().equals(nameOrID))
                return i;
        System.out.println("your stationary is not exist!");
        return -1;
    }

    // relative finds
    // return list index of products that have contains specific string
    public Stationary[] relativeFind(Object originalKey, String request) {
        int count = 0;
        boolean flag = false;
        Stationary[] staArray = new Stationary[0];
        request = request.toLowerCase().trim();
        for (Stationary stationary : staList) {
            if (originalKey instanceof String) {
                String key = (String) originalKey;
                String brand = stationary.getBrand().toLowerCase();
                String source = stationary.getSource().toLowerCase();
                String staName = stationary.getProductName().toLowerCase();
                String typeID = stationary.getType().getTypeID(), typeName = stationary.getType().getTypeName().toLowerCase();
                
                if (request.equals("name") && staName.contains(key.toLowerCase()))
                    flag = true;

                else if (request.equals("brand") && brand.equals(key))
                    flag = true;

                else if (request.equals("source") && source.equals(key))
                    flag = true;

                else if (request.equals("material") && stationary.getMaterial().equals(key))
                    flag = true;

                else if (request.equals("type") && (typeID.equals(key) || typeName.equals(key.toLowerCase())))
                    flag = true;

            } else if (originalKey instanceof LocalDate)
                if (request.equals("releaseDate") && stationary.getReleaseDate().equals((LocalDate) originalKey))
                    flag = true;

            if (flag) {
                staArray = Arrays.copyOf(staArray, staArray.length + 1);
                staArray[count] = stationary;
                flag = false;
                count++;
            }
        }
        if (count == 0) {
            System.out.println("not found any stationary!");
            return null;
        }
        return staArray;
    }

    // advanced finds
    public Stationary[] advancedFind(BigDecimal minPrice, BigDecimal maxPrice, String request) {
        int count = 0;
        boolean flag = false;
        Stationary[] staArray = new Stationary[0];
        request = request.toLowerCase().trim();
        for (Stationary stationary : staList) {
            BigDecimal productPrice = stationary.getProductPrice();

            if ((request.equals("min")) && (productPrice.compareTo(minPrice) >= 0))
                flag = true;

            else if ((request.equals("max")) && (productPrice.compareTo(maxPrice) <= 0))
                flag = true;

            else if (request.equals("range") && ((productPrice.compareTo(minPrice) >= 0) && (productPrice.compareTo(maxPrice) <= 0)))
                    flag = true;

            if (flag) {
                staArray = Arrays.copyOf(staArray, staArray.length + 1);
                staArray[count] = stationary;
                flag = false;
                count++;
            }
        }
        if (count == 0) {
            System.out.println("not found any stationary!");
            return null;
        }
        return staArray;
    }

    public Stationary[] advancedFind(Object originalKeyI, Object originalTimeOrKey, String request) {
        int count = 0;
        boolean flag = false;
        Stationary[] staArray = new Stationary[0];
        request = request.toLowerCase().trim();
        for (Stationary stationary : staList) {
            if ((originalKeyI instanceof String) && (originalTimeOrKey instanceof String) && (request.contains("month"))) {
                String keyI = (String) originalKeyI;
                boolean keyII = stationary.getReleaseDate().getMonthValue() == (int) originalTimeOrKey;
                String typeID = stationary.getType().getTypeID(), typeName = stationary.getType().getTypeName().toLowerCase();
                String material = stationary.getMaterial();
                String source = stationary.getSource();
                String brand = stationary.getBrand();

                if ((request.contains("mat")) && (material.equals(keyI.toLowerCase()) && keyII))
                    flag = true;
                else if ((request.contains("brand")) && (brand.equals(keyI.toLowerCase()) && keyII))
                    flag = true;
                else if ((request.contains("source")) && (source.equals(keyI.toLowerCase()) && keyII))
                    flag = true;
                else if ((request.contains("type")) && ((typeID.equals(keyI) && keyII) || (typeName.equals(keyI.toLowerCase()) && keyII)))
                    flag = true;

            } else if ((originalKeyI instanceof String) && (originalTimeOrKey instanceof String) && (request.contains("year"))) {
                String keyI = (String) originalKeyI;
                boolean keyII = stationary.getReleaseDate().getYear() == (int) originalTimeOrKey;
                String typeID = stationary.getType().getTypeID(), typeName = stationary.getType().getTypeName().toLowerCase();
                String material = stationary.getMaterial();
                String source = stationary.getSource();
                String brand = stationary.getBrand();

                if ((request.contains("mat")) && (material.equals(keyI.toLowerCase()) && keyII))
                    flag = true;

                else if ((request.contains("brand")) && (brand.equals(keyI.toLowerCase()) && keyII))
                    flag = true;

                else if ((request.contains("source")) && (source.equals(keyI.toLowerCase()) && keyII))
                    flag = true;

                else if ((request.contains("type")) && ((typeID.equals(keyI) && keyII) || (typeName.equals(keyI.toLowerCase()) && keyII)))
                    flag = true;

            } else if ((originalKeyI instanceof String) && (originalTimeOrKey instanceof String)) {
                String keyI = (String) originalKeyI, keyII = (String) originalTimeOrKey;
                boolean hasType = request.contains("type");
                boolean hasMaterial = request.contains("material");
                boolean hasSource = request.contains("source");
                boolean hasBrand = request.contains("brand");

                String typeID = stationary.getType().getTypeID(), typeName = stationary.getType().getTypeName().toLowerCase();
                String material = stationary.getMaterial();
                String source = stationary.getSource();
                String brand = stationary.getBrand();

                if ((hasType && hasMaterial) && ((typeID.equals(keyI) && material.equals(keyII.toLowerCase())) || 
                        (typeID.equals(keyII) && material.equals(keyI.toLowerCase())) || 
                        (typeName.equals(keyI.toLowerCase()) && material.equals(keyII.toLowerCase())) ||
                        (typeName.equals(keyII.toLowerCase()) && material.equals(keyI.toLowerCase()))))
                    flag = true;

                else if ((hasType && hasBrand) && ((typeID.equals(keyI) && brand.equals(keyII.toLowerCase())) || 
                        (typeID.equals(keyII) && brand.equals(keyI.toLowerCase())) || 
                        (typeName.equals(keyI.toLowerCase()) && brand.equals(keyII.toLowerCase())) ||
                        (typeName.equals(keyII.toLowerCase()) && brand.equals(keyI.toLowerCase()))))
                    flag = true;

                else if ((hasType && hasSource) && ((typeID.equals(keyI) && source.equals(keyII.toLowerCase())) ||
                        (typeID.equals(keyII) && source.equals(keyI.toLowerCase())) || 
                        (typeName.equals(keyI.toLowerCase()) && source.equals(keyII.toLowerCase())) ||
                        (typeName.equals(keyII.toLowerCase()) && source.equals(keyI.toLowerCase()))))
                    flag = true;

            }

            if (flag) {
                staArray = Arrays.copyOf(staArray, staArray.length + 1);
                staArray[count] = stationary;
                flag = false;
                count++;
            }
        }
        if (count == 0) {
            System.out.println("not found any stationary!");
            return null;
        }
        return staArray;
    }

    // search methods
    @Override
    public void search() {
        Menu.searchHandler();
    }

    @Override
    public void search(String nameOrID) {
        int index = find(nameOrID);
        if (index != -1)
            staList[index].showInfo();
    }

    // relative search
    public void relativeSearch(Object key, String request) {
        Stationary[] indexList = relativeFind(key, request);
        if (indexList != null)
            for (Stationary stationary : indexList)
                stationary.showInfo();
    }

    // advanced search
    public void advancedSearch(Object keyI, Object timeOrKey, String request) {
        Stationary[] indexList = advancedFind(keyI, timeOrKey, request);
        if (indexList != null)
            for (Stationary stationary : indexList)
                stationary.showInfo();
    }

    // add methods (DONE)
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
            System.out.println("your object have something not like stationary!");

    }

    public void add(Stationary[] newStationary, int size) {
        staList = Arrays.copyOf(staList, staList.length + newStationary.length);

        int tempIndex = 0;
        int initCount = this.getCount();
        int total = initCount + size;

        for (int i = initCount; i < total; i++, tempIndex++)
            staList[i] = newStationary[tempIndex];
        this.count = total;
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
            System.out.println("your stationary is not exist!");
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
            System.out.println("your stationary is not exist!");
            return;
        }
        staList[index].setReleaseDate(newDate);
    }

    // edit price
    public void edit(String id, BigDecimal newPrice) {
        int index = find(id);
        if (index == -1) {
            System.out.println("your stationary is not exist!");
            return;
        }
        staList[index].setProductPrice(newPrice);
    }

    // edit quantity
    public void edit(String id, int newQuantity) {
        int index = find(id);
        if (index == -1) {
            System.out.println("your stationary is not exist!");
            return;
        }
        staList[index].setQuantity(newQuantity);
    }

    // edit type
    public void edit(String id, StaTypes newType) {
        int index = find(id);
        if (index == -1) {
            System.out.println("your stationary is not exist!");
            return;
        }
        staList[index].setType(newType);
    }

    // edit brand
    public void edit(String id, String brand) {
        int index = find(id);
        if (index == -1) {
            System.out.println("your stationary is not exist!");
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
            System.out.println("your stationary is not found!");
            return;
        }
        for (int i = index; i < staList.length - 1; i++)
            staList[i] = staList[i + 1];
        staList = Arrays.copyOf(staList, staList.length - 1);
        count--;

    }

    // some other methods

    // execute files
    // write file
    public void writeFile() throws IOException {
        try (DataOutputStream file = new DataOutputStream(new FileOutputStream("OOP/src/main/resources/Stationeries", false))) {
            file.writeInt(count);
            for (int i = 0; i < count; i++) {
                file.writeUTF(staList[i].getProductID());
                file.writeUTF(staList[i].getStationaryID());
                file.writeUTF(staList[i].getProductName());
                file.writeUTF(staList[i].getProductPrice().setScale(0).toString());
                file.writeUTF(staList[i].getReleaseDate().toString());
                file.writeUTF(staList[i].getType().getTypeID());
                file.writeUTF(staList[i].getBrand());
                file.writeInt(staList[i].getQuantity());
                file.writeUTF(staList[i].getMaterial());
                file.writeUTF(staList[i].getSource());
            }
        } catch (Exception err) {
            System.out.printf("error writing file!\n%s\n", err.getMessage());
        }
    }

    // read file
    public void readFile() throws IOException {
        try (DataInputStream file = new DataInputStream(new FileInputStream("OOP/src/main/resources/Stationeries"))) {
            count = file.readInt();
            Stationary[] list = new Stationary[count];
            for (int i = 0; i < count; i++) {
                String productID = file.readUTF();
                String stationaryID = file.readUTF();
                String productName = file.readUTF();
                BigDecimal price = new BigDecimal(file.readUTF());
                LocalDate releaseDate = LocalDate.parse(file.readUTF());
                String typeID = file.readUTF();
                String brand = file.readUTF();
                int quantity = file.readInt();
                String material = file.readUTF(); // use as param for query publisher from class Publishers
                String source = file.readUTF();

                // execute IDs
                StaTypes type = StaTypesBUS.getType(typeID);
                list[i] = new Stationary(productID, stationaryID, productName, releaseDate, price, quantity, type,
                        brand, material, source);
            }
            setCount(count);
            setStaList(list);
        } catch (Exception err) {
            System.out.printf("error reading file!\n%s\n", err.getMessage());
        }
    }
}
