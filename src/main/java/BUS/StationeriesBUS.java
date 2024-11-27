package BUS;

import DTO.StaTypes;
import DTO.Stationeries;
import util.Validate;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Scanner;

public class StationeriesBUS implements IRuleSets {
    private Stationeries[] staList;
    private int count;
    private static final Scanner input = new Scanner(System.in);

    // *constructors (TEST DONE)
    public StationeriesBUS() {
        this.count = 0;
        this.staList = new Stationeries[0];
    }

    public StationeriesBUS(Stationeries[] staList, int count) {
        this.staList = staList;
        this.count = count;
    }

    // *getters / setters (TEST DONE)
    public Stationeries[] getStaList() {
        return this.staList;
    }

    public Stationeries getStationary(String stationaryID) {
        for (Stationeries stationary : staList)
            if (stationary.getProductID().equals(stationaryID))
                return stationary;
        return null;
    }

    public int getCount() {
        return count;
    }

    public void setStaList(Stationeries[] staList) {
        this.staList = staList;
    }

    public void setCount(int count) {
        this.count = count;
    }

    // all others methods like: add remove edit find show....
    // *show list (TEST DONE)
    public void showList() {
        if (staList == null)
            return;
        for (Stationeries stationary : staList)
            stationary.showInfo();
    }

    // *find methods (TEST DONE)
    @Override
    public void find() {
    }

    // strict find
    @Override
    public int find(String nameOrID) {
        for (int i = 0; i < staList.length; i++)
            if (staList[i].getProductID().equals(nameOrID)
                    || staList[i].getProductName().toLowerCase().equals(nameOrID.toLowerCase())
                    || staList[i].getStationeriesID().equals(nameOrID))
                return i;
        System.out.println("your stationary is not exist!");
        return -1;
    }

    // relative finds
    // return list index of products that have contains specific string
    public Stationeries[] relativeFind(Object originalKey, String request) {
        int count = 0;
        boolean flag = false;
        Stationeries[] staArray = new Stationeries[0];
        request = request.toLowerCase().trim();
        for (Stationeries stationary : staList) {
            if (originalKey instanceof String) {
                String key = (String) originalKey;
                String staName = stationary.getProductName();
                String brand = stationary.getBrand();
                String source = stationary.getSource();
                String material = stationary.getMaterial();

                staName = Validate.requiredNotNull(staName) ? staName.toLowerCase() : "";
                brand = Validate.requiredNotNull(brand) ? brand.toLowerCase() : "";
                source = Validate.requiredNotNull(source) ? source.toLowerCase() : "";
                material = Validate.requiredNotNull(material) ? material.toLowerCase() : "";

                // execute request
                if (request.equals("name") && staName.contains(key.toLowerCase()))
                    flag = true;

                else if (request.equals("brand") && brand.contains(key.toLowerCase()))
                    flag = true;

                else if (request.equals("source") && source.contains(key.toLowerCase()))
                    flag = true;

                else if (request.equals("material") && material.contains(key.toLowerCase()))
                    flag = true;

            } else if (originalKey instanceof LocalDate)
                if (request.equals("released") && stationary.getReleaseDate().isEqual((LocalDate) originalKey))
                    flag = true;
                else if (originalKey instanceof StaTypes && request.equals("type")) {
                    StaTypes key = (StaTypes) originalKey;
                    StaTypes types = stationary.getType();
                    String typeID = (Validate.requiredNotNull(types)) ? types.getTypeID() : "",
                            typeName = (Validate.requiredNotNull(types)) ? types.getTypeName().toLowerCase() : "";
                    if (typeID.equals(key.getTypeID()) || typeName.contains(key.getTypeName().toLowerCase()))
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

    // advanced finds
    public Stationeries[] advancedFind(BigDecimal minPrice, BigDecimal maxPrice, String request) {
        request = request.toLowerCase().trim();
        if (request.equals("range")
                && ((minPrice.compareTo(maxPrice) >= 0) || (minPrice.compareTo(BigDecimal.ZERO) < 0) ||
                        (maxPrice.compareTo(BigDecimal.ZERO) < 0))) {
            System.out.println("error range!");
            return null;
        }

        int count = 0;
        boolean flag = false;
        Stationeries[] staArray = new Stationeries[0];
        for (Stationeries stationary : staList) {
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

    public Stationeries[] advancedFind(Object originalKeyI, Object originalTimeOrKey, String request) {
        int count = 0;
        boolean flag = false;
        Stationeries[] staArray = new Stationeries[0];
        request = request.toLowerCase().trim();
        for (Stationeries stationary : staList) {
            int inputTime = 0;
            String brand = stationary.getBrand();
            String source = stationary.getSource();
            String material = stationary.getMaterial();

            brand = Validate.requiredNotNull(brand) ? brand.toLowerCase() : "";
            source = Validate.requiredNotNull(source) ? source.toLowerCase() : "";
            material = Validate.requiredNotNull(material) ? material.toLowerCase() : "";

            StaTypes types = stationary.getType();
            String typeID = (Validate.requiredNotNull(types)) ? types.getTypeID() : "",
                    typeName = (Validate.requiredNotNull(types)) ? types.getTypeName().toLowerCase() : "";

            // execute request
            if ((request.contains("month")) || (request.contains("year"))) {
                inputTime = Validate.isNumber((String) originalTimeOrKey);
                if (inputTime == -1) {
                    System.out.println("something went wrong!");
                    return null;
                }
            }

            if ((originalKeyI instanceof String) && (originalTimeOrKey instanceof String) && (request.contains("month"))) {
                String keyI = (String) originalKeyI;
                boolean keyII = stationary.getReleaseDate().getMonthValue() == inputTime;

                // execute request
                if ((request.contains("mat")) && (material.contains(keyI.toLowerCase()) && keyII))
                    flag = true;
                else if ((request.contains("brand")) && (brand.contains(keyI.toLowerCase()) && keyII))
                    flag = true;
                else if ((request.contains("source")) && (source.contains(keyI.toLowerCase()) && keyII))
                    flag = true;
                else if ((request.contains("type")) && ((typeID.equals(keyI) && keyII) || (typeName.contains(keyI.toLowerCase()) && keyII)))
                    flag = true;

            } else if ((originalKeyI instanceof String) && (originalTimeOrKey instanceof String) && (request.contains("year"))) {
                String keyI = (String) originalKeyI;
                boolean keyII = stationary.getReleaseDate().getYear() == inputTime;

                // execute request
                if ((request.contains("mat")) && (material.contains(keyI.toLowerCase()) && keyII))
                    flag = true;

                else if ((request.contains("brand")) && (brand.contains(keyI.toLowerCase()) && keyII))
                    flag = true;

                else if ((request.contains("source")) && (source.contains(keyI.toLowerCase()) && keyII))
                    flag = true;

                else if ((request.contains("type"))
                        && ((typeID.equals(keyI) && keyII) || (typeName.contains(keyI.toLowerCase()) && keyII)))
                    flag = true;

            } else if ((originalKeyI instanceof String) && (originalTimeOrKey instanceof String)) {
                String keyI = (String) originalKeyI, keyII = (String) originalTimeOrKey;
                boolean hasType = request.contains("type");
                boolean hasMaterial = request.contains("mat");
                boolean hasSource = request.contains("source");
                boolean hasBrand = request.contains("brand");

                // execute request
                boolean isTypeAndMaterial = (hasType && hasMaterial)
                        && ((typeID.equals(keyI) && material.contains(keyII.toLowerCase()))
                                || (typeID.equals(keyII) && material.contains(keyI.toLowerCase()))
                                || (typeName.contains(keyI.toLowerCase()) && material.contains(keyII.toLowerCase()))
                                || (typeName.contains(keyII.toLowerCase()) && material.contains(keyI.toLowerCase())));

                boolean isTypeAndBrand = (hasType && hasBrand)
                        && ((typeID.equals(keyI) && brand.contains(keyII.toLowerCase()))
                                || (typeID.equals(keyII) && brand.contains(keyI.toLowerCase()))
                                || (typeName.contains(keyI.toLowerCase()) && brand.contains(keyII.toLowerCase()))
                                || (typeName.contains(keyII.toLowerCase()) && brand.contains(keyI.toLowerCase())));

                boolean isTypeAndSource = (hasType && hasSource)
                        && ((typeID.equals(keyI) && source.contains(keyII.toLowerCase()))
                                || (typeID.equals(keyII) && source.contains(keyI.toLowerCase()))
                                || (typeName.contains(keyI.toLowerCase()) && source.contains(keyII.toLowerCase()))
                                || (typeName.contains(keyII.toLowerCase()) && source.contains(keyI.toLowerCase())));

                boolean isSourceAndBrand = (hasSource && hasBrand)
                        && ((source.contains(keyI.toLowerCase()) && brand.contains(keyII.toLowerCase()))
                                || (source.contains(keyII.toLowerCase()) && brand.contains(keyI.toLowerCase())));

                boolean isSourceAndMaterial = (hasSource && hasMaterial)
                        && ((source.contains(keyI.toLowerCase()) && material.contains(keyII.toLowerCase()))
                                || (source.contains(keyII.toLowerCase()) && material.contains(keyI.toLowerCase())));

                boolean isMaterialAndBrand = (hasMaterial && hasBrand)
                        && ((material.contains(keyI.toLowerCase()) && brand.contains(keyII.toLowerCase()))
                                || (material.contains(keyII.toLowerCase()) && brand.contains(keyI.toLowerCase())));

                // assign flag
                if (isTypeAndMaterial || isTypeAndBrand || isTypeAndSource || isSourceAndBrand || isSourceAndMaterial
                        || isMaterialAndBrand) {
                    flag = true;
                }

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

    // *search methods (TEST DONE)
    @Override
    public void search() {
        int choice;
        do {
            System.out.println("*".repeat(60));
            System.out.println("I. Strict search");
            System.out.println("II. Relative search");
            System.out.println("III. Advanced search");
            System.out.println("0. Exit");
            System.out.println("*".repeat(60));
            System.out.print("Enter your choice: ");
            choice = Validate.parseChooseHandler(input.nextLine().trim(), 3);
            switch (choice) {
                case 1:
                    System.out.println("Enter name or id of stationary: ");
                    String userInput = input.nextLine().trim();
                    search(userInput);
                    break;
                case 2:
                    caseRelativeSearch();
                    break;
                case 3:
                    caseAdvancedSearch();
                    break;
                case 0:
                    System.out.println("Exit program.");
                    break;
            }
        } while (choice != 0);
    }

    // case handler for relative search
    private void caseRelativeSearch() {
        int choice;
        do {
            System.out.println("*".repeat(60));
            System.out.println("I. Search by Type");
            System.out.println("II. Search by Release Date");
            System.out.println("III. Search by Brand");
            System.out.println("IV. Search by Source");
            System.out.println("V. Search by Material");
            System.out.println("VI. Search by Stationary's name");
            System.out.println("0. Exit");
            System.out.println("*".repeat(60));
            System.out.print("Enter your choice: ");
            choice = Validate.parseChooseHandler(input.nextLine().trim(), 6);
            switch (choice) {
                case 1:
                    System.out.println("*".repeat(60));
                    StaTypesBUS.showList();
                    System.out.println("*".repeat(60));
                    do {
                        choice = Validate.parseChooseHandler(input.nextLine().trim(), StaTypesBUS.getCount());
                    } while (choice != -1);
                    relativeSearch(StaTypesBUS.getTypesList()[choice - 1], "type");
                    break;
                case 2:
                    LocalDate date;
                    do {
                        System.out.print("Enter release date (dd-mm-yyyy) : ");
                        String dateInput = input.nextLine().trim();
                        date = Validate.isCorrectDate(dateInput);
                    } while (date == null);
                    relativeSearch(date, "released");
                    break;
                case 3:
                    System.out.println("Enter brand's name : ");
                    String brand = input.nextLine().trim();
                    relativeSearch(brand, "brand");
                    break;
                case 4:
                    System.out.println("Enter source's name : ");
                    String source = input.nextLine().trim();
                    relativeSearch(source, "source");
                    break;
                case 5:
                    System.out.println("Enter material's name: ");
                    String material = input.nextLine().trim();
                    relativeSearch(material, "material");
                    break;
                case 6:
                    System.out.println("Enter stationary's name: ");
                    String name = input.nextLine().trim();
                    relativeSearch(name, "name");
                    break;
                case 0:
                    System.out.println("Exit program.");
                    break;
            }
        } while (choice != 0);
    }

    // case handler for advanced search
    private void caseAdvancedSearch() {
        int choice, monthOrYear = 0;
        BigDecimal price = null;
        String material = "", brand = "", source = "", inputDate = "";
        StaTypes type = null;
        do {
            System.out.println("*".repeat(60));
            System.out.println("I. Search with min price");
            System.out.println("II. Search with max price");
            System.out.println("III. Search with range min to max price");
            System.out.println("IV. Search by Material & Month (Year) of release date");
            System.out.println("V. Search by Brand & Month (Year) of release date");
            System.out.println("VI. Search by Source & Month (Year) of release date");
            System.out.println("VII. Search by Type & Month (Year) of release date");

            System.out.println("VIII. Search by Type & Brand");
            System.out.println("IX. Search by Type & Material");
            System.out.println("X. Search by Type &  Source");
            System.out.println("XI. Search by Source & Brand");
            System.out.println("XII. Search by Source & Material");
            System.out.println("XIII. Search by Brand & Material");
            System.out.println("0. Exit");
            System.out.println("*".repeat(60));
            System.out.print("Enter your choice : ");
            choice = Validate.parseChooseHandler(input.nextLine().trim(), 13);
            switch (choice) {
                case 1:
                case 2:
                    do {
                        if (choice == 1)
                            System.out.print("Enter min price (VND) : ");
                        else if (choice == 2)
                            System.out.print("Enter max price (VND : ");
                        String value = input.nextLine().trim();
                        price = Validate.isBigDecimal(value);
                    } while (price == null);
                    if (choice == 1)
                        advancedSearch(price, price, "min");
                    else if (choice == 2)
                        advancedSearch(price, price, "max");
                    break;
                case 3:
                    BigDecimal maxPrice;
                    do {
                        System.out.print("Enter min price (VND : ");
                        String value = input.nextLine().trim();
                        price = Validate.isBigDecimal(value);

                        System.out.print("Enter max price (VND : ");
                        value = input.nextLine().trim();
                        maxPrice = Validate.isBigDecimal(value);
                    } while (price == null || maxPrice == null);
                    advancedSearch(price, maxPrice, "range");
                    break;
                case 4:
                case 5:
                case 6:
                case 7:
                    // get input month or year
                    int isNumber;
                    boolean valueFlag;
                    do {
                        System.out.println("I. Month || II.Year");
                        System.out.print("Enter your choice : ");
                        monthOrYear = Validate.parseChooseHandler(input.nextLine().trim(), 2);
                    } while (monthOrYear == -1);
                    if (monthOrYear == 1)
                        do {
                            System.out.println("Enter Month value : ");
                            inputDate = input.nextLine().trim();
                            valueFlag = true;
                            // validate input
                            isNumber = Validate.isNumber(inputDate);
                            if (isNumber == -1 || isNumber > 12 || isNumber < 1) {
                                System.out.println("Error value!");
                                valueFlag = false;
                            }
                        } while (!valueFlag);
                    else if (monthOrYear == 2)
                        do {
                            System.out.println("Enter Year value : ");
                            inputDate = input.nextLine().trim();
                            // validate input
                            valueFlag = true;
                            isNumber = Validate.isNumber(inputDate);
                            if (isNumber == -1 || isNumber > LocalDate.now().getYear() || isNumber < 1900) {
                                System.out.println("Error value!");
                                valueFlag = false;
                            }
                        } while (!valueFlag);
                case 8:
                case 9:
                case 10:
                case 11:
                case 12:
                case 13:
                    // get other fields
                    if (choice == 4 || choice == 9 || choice == 12 || choice == 13) {
                        System.out.println("Enter material's name : ");
                        material = input.nextLine().trim();
                    }

                    if (choice == 5 || choice == 8 || choice == 11 || choice == 13) {
                        System.out.println("Enter brand's name : ");
                        brand = input.nextLine().trim();
                    }

                    if (choice == 6 || choice == 10 || choice == 11 || choice == 12) {
                        System.out.println("Enter source's name : ");
                        source = input.nextLine().trim();
                    }

                    if (choice == 7 || choice == 8 || choice == 9 || choice == 10) {
                        System.out.println("*".repeat(60));
                        StaTypesBUS.showList();
                        System.out.println("*".repeat(60));
                        do {
                            choice = Validate.parseChooseHandler(input.nextLine(), StaTypesBUS.getCount());
                        } while (choice != -1);
                        type = StaTypesBUS.getTypesList()[monthOrYear - 1];
                    }

                    // if case is 4 -> 7
                    if (!inputDate.isEmpty() && monthOrYear != 0) {
                        if (!material.isEmpty())
                            advancedSearch(material, inputDate, monthOrYear == 1 ? "mat-month" : "mat-year");
                        else if (brand != null)
                            advancedSearch(brand, inputDate, monthOrYear == 1 ? "brand-month" : "brand-year");
                        else if (source != null)
                            advancedSearch(source, inputDate, monthOrYear == 1 ? "source-month" : "source-year");
                        else if (type != null)
                            advancedSearch(type.getTypeName(), inputDate, monthOrYear == 1 ? "type-month" : "type-year");
                        break;
                    }

                    // if case 8 -> 13
                    if (choice == 8)
                        advancedSearch(type, brand, "type-brand");
                    else if (choice == 9)
                        advancedSearch(type, material, "type-mat");
                    else if (choice == 10)
                        advancedSearch(type, source, "type-source");
                    else if (choice == 11)
                        advancedSearch(source, brand, "source-brand");
                    else if (choice == 12)
                        advancedSearch(source, material, "source-mat");
                    else if (choice == 13)
                        advancedSearch(brand, material, "brand-mat");
                    break;
                case 0:
                    System.out.println("Exit program.");
                    break;
            }
        } while (choice != 0);
    }

    @Override
    public void search(String nameOrID) {
        int index = find(nameOrID);
        if (index != -1)
            staList[index].showInfo();
    }

    // relative search
    public void relativeSearch(Object key, String request) {
        Stationeries[] indexList = relativeFind(key, request);
        if (indexList != null)
            for (Stationeries stationary : indexList)
                stationary.showInfo();
    }

    // advanced search
    public void advancedSearch(Object keyI, Object timeOrKey, String request) {
        Stationeries[] indexList;
        if ((keyI instanceof BigDecimal) && (timeOrKey instanceof BigDecimal))
            indexList = advancedFind((BigDecimal) keyI, (BigDecimal) timeOrKey, request);
        else
            indexList = advancedFind(keyI, timeOrKey, request);
        if (indexList != null)
            for (Stationeries stationary : indexList)
                stationary.showInfo();
    }

    // *add methods (TEST DONE)
    @Override
    public void add() {
        int choice;
        do {
            System.out.println("*".repeat(60));
            System.out.println("I. Add stationary");
            System.out.println("II. Add list of stationeries");
            System.out.println("0. Exit");
            System.out.println("*".repeat(60));
            System.out.print("Enter your choice : ");
            choice = Validate.parseChooseHandler(input.nextLine().trim(), 2);
            // try catch for execute file after add
            try {
                switch (choice) {
                    case 1:
                        Stationeries newStationary = new Stationeries();
                        newStationary.setInfo();
                        // confirm
                        System.out.printf("| %s %s %s |\n", "I.Cancel", "-".repeat(20), "II.Add");
                        do {
                            System.out.print("choose option (1 or 2) : ");
                            String option = input.nextLine().trim();
                            choice = Validate.parseChooseHandler(option, 2);
                        } while (choice == -1);
                        if (choice == 1)
                            break;
                        add(newStationary);
                        writeFile();
                        break;
                    case 2:
                        int count = 0;
                        Stationeries[] list = new Stationeries[0];
                        do {
                            System.out.print("Enter total stationeries you wanna add : ");
                            String option = input.nextLine().trim();
                            choice = Validate.isNumber(option);
                        } while (choice == -1);
                        // for loop with input time
                        for (int i = 0; i < choice; i++) {
                            Stationeries stationary = new Stationeries();
                            stationary.setInfo();
                            list = Arrays.copyOf(list, list.length + 1);
                            list[count] = stationary;
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
                    case 0:
                        System.out.println("Exit program.");
                        break;
                }
            } catch (Exception e) {
                System.out.printf("error writing file!\nt%s\n", e.getMessage());
            }
        } while (choice != 0);
    }

    @Override
    public void add(Object stationary) {
        if (stationary instanceof Stationeries) {
            Stationeries newStationary = (Stationeries) stationary;
            newStationary.setProductID(newStationary.getProductID());
            newStationary.setStationeriesID(newStationary.getStationeriesID());
            staList = Arrays.copyOf(staList, staList.length + 1);
            staList[count] = newStationary;
            count++;
        } else
            System.out.println("your object have something not like stationary!");

    }

    public void add(Stationeries[] newStationeries) {
        int tempIndex = 0, newListLength = newStationeries.length;
        int initCount = this.getCount();
        int total = initCount + newListLength;
        staList = Arrays.copyOf(staList, staList.length + newListLength);

        for (int i = initCount; i < total; i++, tempIndex++) {
            newStationeries[tempIndex].setProductID(newStationeries[tempIndex].getProductID());
            newStationeries[tempIndex].setStationeriesID(newStationeries[tempIndex].getStationeriesID());
            staList[i] = newStationeries[tempIndex];
        }
        this.count = total;
    }

    // *edit methods (TEST DONE)
    @Override
    public void edit() {
        int choice;
        do {
            System.out.println("*".repeat(60));
            System.out.println("I. Edit name");
            System.out.println("II. Edit release date");
            System.out.println("III. Edit price");
            System.out.println("IV. Edit quantity");
            System.out.println("V. Edit type");
            System.out.println("VI. Edit brand");
            System.out.println("VII. Edit source");
            System.out.println("VIII. Edit material");
            System.out.println("0. Exit");
            System.out.println("*".repeat(60));
            System.out.print("Enter your choice : ");
            choice = Validate.parseChooseHandler(input.nextLine().trim(), 10);
            // exits
            if (choice == 0) {
                System.out.println("Exit program.");
                break;
            }
            System.out.println("Enter name or id of stationary : ");
            String userInput = input.nextLine().trim();

            // if case
            try {
                if (choice == 1)
                    edit(userInput);
                else if (choice == 2)
                    editReleaseDate(userInput);
                else if (choice == 3)
                    editPrice(userInput);
                else if (choice == 4)
                    editQuantity(userInput);
                else if (choice == 5)
                    editType(userInput);
                else if (choice == 6)
                    editBrand(userInput);
                else if (choice == 7)
                    editSource(userInput);
                else if (choice == 8)
                    editMaterial(userInput);
                // update file
                writeFile();
            } catch (Exception e) {
                System.out.printf("error writing file!\nt%s\n", e.getMessage());
            }
        } while (choice != 0);
    }

    @Override
    public void edit(String stationaryNameOrID) {
        int index = find(stationaryNameOrID);
        if (index != -1) {
            String name;
            int userChoice;
            // show list for user choose
            staList[index].showInfo();
            System.out.printf("| %s %s %s |\n", "I.Cancel", "-".repeat(20), "II.Edit");
            do {
                System.out.print("choose option (1 or 2) : ");
                String option = input.nextLine().trim();
                userChoice = Validate.parseChooseHandler(option, 2);
            } while (userChoice == -1);
            if (userChoice == 1)
                return;

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
    public void editReleaseDate(String stationaryNameOrID) {
        int index = find(stationaryNameOrID);
        if (index != -1) {
            LocalDate date;
            int userChoice;
            // show list for user choose
            staList[index].showInfo();
            System.out.printf("| %s %s %s |\n", "I.Cancel", "-".repeat(20), "II.Edit");
            do {
                System.out.print("choose option (1 or 2) : ");
                String option = input.nextLine().trim();
                userChoice = Validate.parseChooseHandler(option, 2);
            } while (userChoice == -1);
            if (userChoice == 1)
                return;

            do {
                System.out.print("new release date : ");
                String dateInput = input.nextLine().trim();
                date = Validate.isCorrectDate(dateInput);
            } while (date == null);
            staList[index].setReleaseDate(date);
        }
    }

    // edit price
    public void editPrice(String stationaryNameOrID) {
        int index = find(stationaryNameOrID);
        if (index != -1) {
            BigDecimal price;
            int userChoice;
            // show list for user choose
            staList[index].showInfo();
            System.out.printf("| %s %s %s |\n", "I.Cancel", "-".repeat(20), "II.Edit");
            do {
                System.out.print("choose option (1 or 2) : ");
                String option = input.nextLine().trim();
                userChoice = Validate.parseChooseHandler(option, 2);
            } while (userChoice == -1);
            if (userChoice == 1)
                return;

            do {
                System.out.print("new price (VND) : ");
                String value = input.nextLine();
                price = Validate.isBigDecimal(value);
            } while (price == null);
            staList[index].setProductPrice(price);
        }
    }

    // edit quantity
    public void editQuantity(String stationaryNameOrID) {
        int index = find(stationaryNameOrID);
        if (index != -1) {
            int quantity, userChoice;
            // show list for user choose
            staList[index].showInfo();
            System.out.printf("| %s %s %s |\n", "I.Cancel", "-".repeat(20), "II.Edit");
            do {
                System.out.print("choose option (1 or 2) : ");
                String option = input.nextLine().trim();
                userChoice = Validate.parseChooseHandler(option, 2);
            } while (userChoice == -1);
            if (userChoice == 1)
                return;

            do {
                System.out.print("new quantity: ");
                String quantityInput = input.nextLine().trim();
                quantity = Validate.isNumber(quantityInput);
            } while (quantity == -1);
            staList[index].setQuantity(quantity);
        }
    }

    // edit type
    public void editType(String stationaryNameOrID) {
        if (StaTypesBUS.getCount() == 0) {
            System.out.println("not have any type for edit!");
            return;
        }

        int index = find(stationaryNameOrID);
        if (index != -1) {
            int userChoice;
            // show list for user choose
            staList[index].showInfo();
            System.out.printf("| %s %s %s |\n", "I.Cancel", "-".repeat(20), "II.Edit");
            do {
                System.out.print("choose option (1 or 2) : ");
                String option = input.nextLine().trim();
                userChoice = Validate.parseChooseHandler(option, 2);
            } while (userChoice == -1);
            if (userChoice == 1)
                return;

            StaTypesBUS.showList();
            do {
                System.out.print("choose type you want (like \"1, 2, 3,etc....\"): ");
                String option = input.nextLine().trim();
                userChoice = Validate.parseChooseHandler(option, StaTypesBUS.getCount());
            } while (userChoice == -1);

            StaTypes type = StaTypesBUS.getTypesList()[userChoice - 1];
            staList[index].setType(type);
        }
    }

    // edit brand
    public void editBrand(String stationaryNameOrID) {
        int index = find(stationaryNameOrID);
        if (index != -1) {
            String brand;
            int userChoice;
            // show list for user choose
            staList[index].showInfo();
            System.out.printf("| %s %s %s |\n", "I.Cancel", "-".repeat(20), "II.Edit");
            do {
                System.out.print("choose option (1 or 2) : ");
                String option = input.nextLine().trim();
                userChoice = Validate.parseChooseHandler(option, 2);
            } while (userChoice == -1);
            if (userChoice == 1)
                return;

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
    public void editSource(String stationaryNameOrID) {
        int index = find(stationaryNameOrID);
        if (index != -1) {
            String source;
            int userChoice;
            // show list for user choose
            staList[index].showInfo();
            System.out.printf("| %s %s %s |\n", "I.Cancel", "-".repeat(20), "II.Edit");
            do {
                System.out.print("choose option (1 or 2) : ");
                String option = input.nextLine().trim();
                userChoice = Validate.parseChooseHandler(option, 2);
            } while (userChoice == -1);
            if (userChoice == 1)
                return;

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
    public void editMaterial(String stationaryNameOrID) {
        int index = find(stationaryNameOrID);
        if (index != -1) {
            String material;
            int userChoice;
            // show list for user choose
            staList[index].showInfo();
            System.out.printf("| %s %s %s |\n", "I.Cancel", "-".repeat(20), "II.Edit");
            do {
                System.out.print("choose option (1 or 2) : ");
                String option = input.nextLine().trim();
                userChoice = Validate.parseChooseHandler(option, 2);
            } while (userChoice == -1);
            if (userChoice == 1)
                return;

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

    // *remove methods (TEST DONE)
    @Override
    public void remove() {
        int choice;
        do {
            System.out.println("*".repeat(60));
            System.out.println("I. Remove");
            System.out.println("0. Exit");
            System.out.println("*".repeat(60));
            System.out.print("Enter your choice: ");
            choice = Validate.parseChooseHandler(input.nextLine().trim(), 1);
            if (choice == 0) {
                System.out.println("Exit program.");
                break;
            } else if (choice == 1) {
                try {
                    System.out.println("Enter name or id of stationary: ");
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
    public void remove(String stationaryNameOrID) {
        int index = find(stationaryNameOrID);
        if (index != -1) {
            int userChoice;
            // show list for user choose
            staList[index].showInfo();
            System.out.printf("| %s %s %s |\n", "I.Cancel", "-".repeat(20), "II.Remove");
            do {
                System.out.print("choose option (1 or 2) : ");
                String option = input.nextLine().trim();
                userChoice = Validate.parseChooseHandler(option, 2);
            } while (userChoice == -1);
            if (userChoice == 1)
                return;

            for (int i = index; i < staList.length - 1; i++)
                staList[i] = staList[i + 1];
            staList = Arrays.copyOf(staList, staList.length - 1);
            count--;
        }

    }

    // *execute files (TEST DONE)
    // write file
    public void writeFile() throws IOException {
        try (DataOutputStream file = new DataOutputStream(
                new BufferedOutputStream(new FileOutputStream("src/main/resources/Stationeries", false)))) {
            file.writeInt(count);
            for (Stationeries stationary : staList) {
                file.writeUTF(stationary.getProductID());
                file.writeUTF(stationary.getStationeriesID());
                file.writeUTF(stationary.getProductName());
                file.writeUTF(stationary.getProductPrice().setScale(0).toString());
                file.writeUTF(stationary.getReleaseDate().toString());
                file.writeUTF(stationary.getType().getTypeID());
                file.writeUTF(stationary.getBrand());
                file.writeInt(stationary.getQuantity());
                file.writeUTF(stationary.getMaterial());
                file.writeUTF(stationary.getSource());
            }
        } catch (Exception err) {
            System.out.printf("error writing file!\n%s\n", err.getMessage());
        }
    }

    // read file
    public void readFile() throws IOException {
        File testFile = new File("src/main/resources/Stationeries");
        if (testFile.length() == 0)
            return;

        try (DataInputStream file = new DataInputStream(
                new BufferedInputStream(new FileInputStream("src/main/resources/Stationeries")))) {
            count = file.readInt();
            Stationeries[] list = new Stationeries[count];
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
                list[i] = new Stationeries(productID, stationaryID, productName, releaseDate, price, quantity, type,
                        brand, material, source);
            }
            setCount(count);
            setStaList(list);
        } catch (Exception err) {
            System.out.printf("error reading file!\n%s\n", err.getMessage());
        }
    }
}
