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
            if (staList[i].getProductID().equals(nameOrID)
                    || staList[i].getProductName().toLowerCase().equals(nameOrID.toLowerCase())
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
                String material = stationary.getMaterial().toLowerCase();
                String staName = stationary.getProductName().toLowerCase();
                String typeID = stationary.getType().getTypeID(),
                        typeName = stationary.getType().getTypeName().toLowerCase();

                if (request.equals("name") && staName.contains(key.toLowerCase()))
                    flag = true;

                else if (request.equals("brand") && brand.contains(key.toLowerCase()))
                    flag = true;

                else if (request.equals("source") && source.contains(key.toLowerCase()))
                    flag = true;

                else if (request.equals("material") && material.contains(key.toLowerCase()))
                    flag = true;

                else if (request.equals("type") && (typeID.equals(key) || typeName.contains(key.toLowerCase())))
                    flag = true;

            } else if (originalKey instanceof LocalDate)
                if (request.equals("released") && stationary.getReleaseDate().isEqual((LocalDate) originalKey))
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
        request = request.toLowerCase().trim();
        if (request.equals("range")
                && ((minPrice.compareTo(maxPrice) >= 0) || (minPrice.compareTo(BigDecimal.ZERO) < 0) ||
                        (maxPrice.compareTo(BigDecimal.ZERO) < 0))) {
            System.out.println("error range!");
            return null;
        }

        int count = 0;
        boolean flag = false;
        Stationary[] staArray = new Stationary[0];
        for (Stationary stationary : staList) {
            BigDecimal productPrice = stationary.getProductPrice();

            if ((request.equals("min")) && (productPrice.compareTo(minPrice) >= 0))
                flag = true;

            else if ((request.equals("max")) && (productPrice.compareTo(maxPrice) <= 0))
                flag = true;

            else if (request.equals("range")
                    && ((productPrice.compareTo(minPrice) >= 0) && (productPrice.compareTo(maxPrice) <= 0)))
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
            if ((originalKeyI instanceof String) && (originalTimeOrKey instanceof String)
                    && (request.contains("month"))) {
                String keyI = (String) originalKeyI;
                boolean keyII = stationary.getReleaseDate().getMonthValue() == (int) originalTimeOrKey;
                String typeID = stationary.getType().getTypeID(),
                        typeName = stationary.getType().getTypeName().toLowerCase();
                String material = stationary.getMaterial();
                String source = stationary.getSource();
                String brand = stationary.getBrand();

                if ((request.contains("mat")) && (material.equals(keyI.toLowerCase()) && keyII))
                    flag = true;
                else if ((request.contains("brand")) && (brand.equals(keyI.toLowerCase()) && keyII))
                    flag = true;
                else if ((request.contains("source")) && (source.equals(keyI.toLowerCase()) && keyII))
                    flag = true;
                else if ((request.contains("type"))
                        && ((typeID.equals(keyI) && keyII) || (typeName.equals(keyI.toLowerCase()) && keyII)))
                    flag = true;

            } else if ((originalKeyI instanceof String) && (originalTimeOrKey instanceof String)
                    && (request.contains("year"))) {
                String keyI = (String) originalKeyI;
                boolean keyII = stationary.getReleaseDate().getYear() == (int) originalTimeOrKey;
                String typeID = stationary.getType().getTypeID(),
                        typeName = stationary.getType().getTypeName().toLowerCase();
                String material = stationary.getMaterial();
                String source = stationary.getSource();
                String brand = stationary.getBrand();

                if ((request.contains("mat")) && (material.equals(keyI.toLowerCase()) && keyII))
                    flag = true;

                else if ((request.contains("brand")) && (brand.equals(keyI.toLowerCase()) && keyII))
                    flag = true;

                else if ((request.contains("source")) && (source.equals(keyI.toLowerCase()) && keyII))
                    flag = true;

                else if ((request.contains("type"))
                        && ((typeID.equals(keyI) && keyII) || (typeName.equals(keyI.toLowerCase()) && keyII)))
                    flag = true;

            } else if ((originalKeyI instanceof String) && (originalTimeOrKey instanceof String)) {
                String keyI = (String) originalKeyI, keyII = (String) originalTimeOrKey;
                boolean hasType = request.contains("type");
                boolean hasMaterial = request.contains("material");
                boolean hasSource = request.contains("source");
                boolean hasBrand = request.contains("brand");

                String typeID = stationary.getType().getTypeID(),
                        typeName = stationary.getType().getTypeName().toLowerCase();
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
        Stationary[] indexList;
        if ((keyI instanceof BigDecimal) && (timeOrKey instanceof BigDecimal))
            indexList = advancedFind((BigDecimal) keyI, (BigDecimal) timeOrKey, request);
        else
            indexList = advancedFind(keyI, timeOrKey, request);
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
    public void edit(String stationaryID) {
        int index = find(stationaryID);
        if (index != -1) {
            String name;
            do {
                System.out.print("new name : ");
                name = input.nextLine().trim();
                if (!Validate.checkName(name)) {
                    System.out.println("name is wrong format!");
                    name = "";
                }
            } while (name.isEmpty());
            staList[index].setProductName(name);
        }
    }

    // edit release date
    public void editReleaseDate(String stationaryID) {
        int index = find(stationaryID);
        if (index != -1) {
            LocalDate date;
            do {
                System.out.print("new release date : ");
                String dateInput = input.nextLine().trim();
                date = Validate.isCorrectDate(dateInput);
            } while (date == null);
            staList[index].setReleaseDate(date);
        }
    }

    // edit price
    public void editPrice(String stationaryID) {
        int index = find(stationaryID);
        if (index != -1) {
            BigDecimal price;
            do {
                System.out.print("new price (VND) : ");
                String value = input.nextLine();
                price = Validate.isBigDecimal(value);
            } while (price == null);
            staList[index].setProductPrice(price);
        }
    }

    // edit quantity
    public void editQuantity(String stationaryID) {
        int index = find(stationaryID);
        if (index != -1) {
            int quantity;
            do {
                System.out.print("new quantity: ");
                String quantityInput = input.nextLine().trim();
                quantity = Validate.isNumber(quantityInput);
            } while (quantity == -1);
            staList[index].setQuantity(quantity);
        }
    }

    // edit type
    public void editType(String stationaryID) {
        int index = find(stationaryID);
        if (index != -1) {
            int userChoose;
            // show list for user choose
            StaTypesBUS.showList();
            if (StaTypesBUS.getCount() == 0) // if not have any publisher
                return;
            System.out.println("----------------------------");
            do {
                System.out.print("choose type you want (like \\\"1, 2, 3,etc....\\\"): ");
                String option = input.nextLine().trim();
                userChoose = Validate.parseChooseHandler(option, StaTypesBUS.getCount());
            } while (userChoose == -1);

            StaTypes type = StaTypesBUS.getTypesList()[userChoose - 1];
            staList[index].setType(type);
        }
    }

    // edit brand
    public void editBrand(String stationaryID) {
        int index = find(stationaryID);
        if (index != -1) {
            String brand;
            do {
                System.out.print("new brand name: ");
                brand = input.nextLine().trim();
                if (!Validate.checkName(brand)) {
                    System.out.println("error name!");
                    brand = "";
                }
            } while (brand.isEmpty());
            staList[index].setBrand(brand);
        }
    }

    // edit source
    public void editSource(String stationaryID) {
        int index = find(stationaryID);
        if (index != -1) {
            String source;
            do {
                System.out.print("new source name: ");
                source = input.nextLine().trim();
                if (!Validate.checkHumanName(source)) {
                    System.out.println("error name!");
                    source = "";
                }
            } while (source.isEmpty());
            staList[index].setSource(source);
        }
    }

    // edit material
    public void editMaterial(String stationaryID) {
        int index = find(stationaryID);
        if (index != -1) {
            String material;
            do {
                System.out.print("new material name: ");
                material = input.nextLine().trim();
                if (!Validate.checkName(material)) {
                    System.out.println("error name!");
                    material = "";
                }
            } while (material.isEmpty());
            staList[index].setMaterial(material);
        }
    }

    // remove methods (DONE)
    @Override
    public void remove() {
        Menu.removeHandler();
    }

    @Override
    public void remove(String stationaryID) {
        int index = find(stationaryID);
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
        try (DataOutputStream file = new DataOutputStream(
                new FileOutputStream("OOP/src/main/resources/Stationeries", false))) {
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
