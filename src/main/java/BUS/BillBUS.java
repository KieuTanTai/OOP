
package BUS;

import java.util.Scanner;
import java.math.BigDecimal;
import java.util.Arrays;
import java.time.LocalDate;
import java.io.*;
import DTO.*;
import util.Validate;

public class BillBUS implements IRuleSets {
    private Bill[] ds;
    private int n;
    Scanner sc = new Scanner(System.in);

    public BillBUS() {
        n = 0;
        ds = new Bill[0];
    }

    public BillBUS(Bill[] ds, int n) {
        this.ds = ds;
        this.n = n;
    }

    public BillBUS(BillBUS list) {
        ds = list.ds;
        n = list.n;
    }

    public Bill[] getList() {
        return ds;
    }

    public int getCount() {
        return n;
    }

    public void setList(Bill[] ds) {
        this.ds = ds;
    }

    public void setCount(int n) {
        this.n = n;
    }

    // other methods like add, search, find, remove, edit....
    // show list
    public void showList() {
        if (ds == null)
            return;
        for (Bill bill : ds)
            bill.showInfo();
    }

    // add methods
    public void add() {
        int choice;
        do {
            System.out.println("*".repeat(60));
            System.out.println("I. Add a new Bill");
            System.out.println("II. Add list of Bills");
            System.out.println("0. Exit");
            System.out.println("*".repeat(60));
            System.out.print("Enter your choice : ");
            String inputChoice = sc.nextLine().trim();

            // Validate if user choose 0
            if (inputChoice.equals("0")) {
                System.out.println("Exit program.");
                break;
            }

            choice = Validate.parseChooseHandler(inputChoice, 2);
            try {
                switch (choice) {
                    case 1:
                        Bill newBill = new Bill();
                        newBill.setInfo();
                        System.out.printf("| %s %s %s |\n", "I.Cancel", "-".repeat(20), "II.Add");
                        do {
                            System.out.print("Choose option (1 or 2) : ");
                            String option = sc.nextLine().trim();
                            choice = Validate.parseChooseHandler(option, 2);
                        } while (choice == -1);
                        if (choice == 1)
                            break;
                        add(newBill);
                        writeFile();
                        break;

                    case 2:
                        int count = 0;
                        Bill[] list = new Bill[0];
                        do {
                            System.out.print("Enter total Bills you want to add : ");
                            String option = sc.nextLine().trim();
                            choice = Validate.isNumber(option);
                        } while (choice == -1);

                        for (int i = 0; i < choice; i++) {
                            Bill bill = new Bill();
                            bill.setInfo();
                            list = Arrays.copyOf(list, list.length + 1);
                            list[count] = bill;
                            count++;
                        }

                        System.out.printf("| %s %s %s |\n", "I.Cancel", "-".repeat(20), "II.Add");
                        do {
                            System.out.print("Choose option (1 or 2) : ");
                            String option = sc.nextLine().trim();
                            choice = Validate.parseChooseHandler(option, 2);
                        } while (choice == -1);
                        if (choice == 1)
                            break;
                        add(list);
                        writeFile();
                        break;
                }
            } catch (Exception e) {
                System.out.printf("Error writing file!\n%s\n", e.getMessage());
            }
        } while (choice != 0);
    }

    @Override
    public void add(Object newBill) {
        if (newBill instanceof Bill bill) {
            ds = Arrays.copyOf(ds, ds.length + 1);
            ds[n] = bill;
            n++;
        } else {
            System.out.println("The new object is not a Bill!");
        }
    }

    public void add(Bill[] newBills) {
        int tempIndex = 0, newListLength = newBills.length;
        int initCount = this.getCount();
        int total = initCount + newListLength;
        ds = Arrays.copyOf(ds, ds.length + newListLength);

        for (int i = initCount; i < total; i++, tempIndex++) {
            ds[i] = newBills[tempIndex];
        }
        this.n = total;
    }

    // edit methods
    @Override
    public void edit() {
        int choice;
        do {
            System.out.println("*".repeat(60));
            System.out.println("I. Edit Employee");
            System.out.println("II. Edit Customer");
            System.out.println("III. Edit Date");
            System.out.println("0. Exit");
            System.out.println("*".repeat(60));
            System.out.print("Enter your choice: ");
            String inputChoice = sc.nextLine().trim();

            if (inputChoice.equals("0")) {
                System.out.println("Exiting edit mode.");
                break;
            }

            choice = Validate.parseChooseHandler(inputChoice, 3);
            System.out.print("Enter Bill ID : ");
            String billId = sc.nextLine().trim();

            try {
                if (choice == 1) {
                    editEmployee(billId);
                } else if (choice == 2) {
                    editCustomer(billId);
                } else if (choice == 3) {
                    edit(billId);
                }
                // Update the file after modification
                writeFile();
            } catch (Exception e) {
                System.out.printf("Error writing file!\n%s\n", e.getMessage());
            }
        } while (true);
    }

    public void editEmployee(String billId) {
        int index = find(billId);
        if (index != -1) {
            try {
                int userChoose;
                Bill bill = ds[index];
                bill.showInfo();
                System.out.printf("| %s %s %s |\n", "I.Cancel", "-".repeat(20), "II.Edit");
                do {
                    System.out.print("choose option (1 or 2) : ");
                    String option = sc.nextLine().trim();
                    userChoose = Validate.parseChooseHandler(option, 2);
                } while (userChoose == -1);
                if (userChoose == 1)
                    return;

                EmployeesBUS list = new EmployeesBUS();
                list.readFile();
                System.out.println("-".repeat(60));
                do {
                    System.out.print("name employee : ");
                    String name = sc.nextLine().trim();
                    userChoose = list.find(name);
                } while (userChoose == -1);

                Employees employees = list.getEmployeesList()[userChoose];
                ds[index].setEmployee(employees);
            } catch (IOException e) {
                System.out.println("error reading file!\n" + e.getMessage());
            }

        }
    }

    public void editCustomer(String billId) {
        int index = find(billId);
        if (index != -1) {
            try {
                int userChoose;
                Bill bill = ds[index];
                bill.showInfo();
                System.out.printf("| %s %s %s |\n", "I.Cancel", "-".repeat(20), "II.Edit");
                do {
                    System.out.print("choose option (1 or 2) : ");
                    String option = sc.nextLine().trim();
                    userChoose = Validate.parseChooseHandler(option, 2);
                } while (userChoose == -1);
                if (userChoose == 1)
                    return;

                CustomersBUS list = new CustomersBUS();
                list.readFile();
                System.out.println("-".repeat(60));
                do {
                    System.out.print("name customer : ");
                    String name = sc.nextLine().trim();
                    userChoose = list.find(name);
                } while (userChoose == -1);

                Customers customer = list.getCustomersList()[userChoose];
                ds[index].setCustomer(customer);
            } catch (IOException e) {
                System.out.println("error reading file!\n" + e.getMessage());
            }
        }
    }

    @Override
    public void edit(String billId) {
        int index = find(billId);
        if (index != -1) {
            Bill bill = ds[index];
            bill.showInfo();
            LocalDate date;
            do {
                System.out.print("Enter new date (yyyy-MM-dd): ");
                String dateInput = sc.nextLine().trim();
                date = Validate.isCorrectDate(dateInput);
            } while (date == null);
            bill.setDate(date);
        } else {
            System.out.println("Bill ID does not exist.");
        }
    }

    // remove methods
    public void remove(String bd) {
        int index = -1;
        for (int i = 0; i < n; ++i) {
            if (ds[i].getBillId().equals(bd)) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            for (int i = index; i < n - 1; ++i) {
                ds[i] = ds[i + 1];
            }
            ds = Arrays.copyOf(ds, ds.length - 1);
            n--;
        } else {
            System.out.println("not found!!!");
        }
    }

    public void remove() {
        System.out.println("insert bill id you wanna remove");
        String bd = sc.nextLine();
        remove(bd);
    }

    // search methods
    // strict search
    @Override
    public void search(String grnID) {
        int index = find(grnID);
        if (index != -1)
            ds[index].showInfo();
    }

    // relative search
    public void relativeSearch(Object key, String request) {
        Bill[] list = find(key, request);
        if (list != null)
            for (Bill bill : list)
                bill.showInfo();
    }

    // advanced search
    public void advancedSearch(Object keyI, Object timeOrKey, String request) {
        Bill[] list = find((BigDecimal) keyI, (BigDecimal) timeOrKey, request);
        if (list != null)
            for (Bill bill : list)
                bill.showInfo();

    }

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
            System.out.print("Enter your choice : ");
            String inputChoice = sc.nextLine().trim();

            if (inputChoice.equals("0")) {
                System.out.println("Exit program.");
                break;
            }
            choice = Validate.parseChooseHandler(inputChoice, 3);

            switch (choice) {
                case 1:
                    System.out.print("Enter Bill ID: ");
                    String billId = sc.nextLine().trim();
                    search(billId);
                    break;
                case 2:
                    caseRelativeSearch();
                    break;
                case 3:
                    caseAdvancedSearch();
                    break;
            }
        } while (choice != 0);
    }

    // case for fields Obj like customer or employee
    private void caseRelativeSearch() {
        int choice;
        do {
            System.out.println("*".repeat(60));
            System.out.println("I. Search by Employee");
            System.out.println("II. Search by Customer");
            System.out.println("III. Search by Date");
            System.out.println("0. Exit");
            System.out.println("*".repeat(60));
            System.out.print("Enter your choice : ");
            String inputChoice = sc.nextLine().trim();

            if (inputChoice.equals("0")) {
                System.out.println("Exit program.");
                break;
            }

            choice = Validate.parseChooseHandler(inputChoice, 3);

            switch (choice) {
                case 1:
                    System.out.print("Enter Employee ID or Name: ");
                    String employeeSearchInput = sc.nextLine().trim();
                    relativeSearch(employeeSearchInput, "employee");
                    break;
                case 2:
                    System.out.print("Enter Customer ID or Name: ");
                    String customerSearchInput = sc.nextLine().trim();
                    relativeSearch(customerSearchInput, "customer");
                    break;
                case 3:
                    LocalDate date;
                    do {
                        System.out.print("Enter date (dd-mm-yyyy): ");
                        String dateInput = sc.nextLine().trim();
                        date = Validate.isCorrectDate(dateInput);
                    } while (date == null);
                    relativeSearch(date, "date");
                    break;
            }
        } while (choice != 0);
    }

    // case for fields BigDecimal
    private void caseAdvancedSearch() {
        int choice;
        BigDecimal price;
        do {
            System.out.println("*".repeat(60));
            System.out.println("I. Search with min price");
            System.out.println("II. Search with max price");
            System.out.println("III. Search with range min to max price");
            System.out.println("0. Exit");
            System.out.println("*".repeat(60));
            System.out.print("Enter your choice : ");
            String inputChoice = sc.nextLine().trim();

            if (inputChoice.equals("0")) {
                System.out.println("Exit program.");
                break;
            }

            choice = Validate.parseChooseHandler(inputChoice, 3);

            switch (choice) {
                case 1:
                case 2:
                    do {
                        if (choice == 1)
                            System.out.print("Enter min price (VND): ");
                        else if (choice == 2)
                            System.out.print("Enter max price (VND): ");
                        String value = sc.nextLine().trim();
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
                        System.out.print("Enter min price (VND): ");
                        String value = sc.nextLine().trim();
                        price = Validate.isBigDecimal(value);

                        System.out.print("Enter max price (VND): ");
                        value = sc.nextLine().trim();
                        maxPrice = Validate.isBigDecimal(value);
                    } while (price == null || maxPrice == null);
                    advancedSearch(price, maxPrice, "range");
                    break;
            }
        } while (choice != 0);
    }

    // find methods
    // strict find
    @Override
    public int find(String billId) {
        for (int i = 0; i < ds.length; i++) {
            if (ds[i].getBillId().equals(billId)) {
                return i;
            }
        }
        System.out.println("Your bill is not exist!");
        return -1;
    }

    // Relative find
    public Bill[] find(Object originalKey, String request) {
        int count = 0;
        boolean flag = false;
        Bill[] resultArray = new Bill[0];
        request = request.toLowerCase().trim();

        for (Bill bill : ds) {
            if (originalKey instanceof Employees employee) {
                String employeeId = (bill.getEmployee() != null) ? bill.getEmployee().getPersonID() : "";
                String employeeName = (bill.getEmployee() != null) ? bill.getEmployee().getFullName().toLowerCase()
                        : "";

                if (request.equals("employee") && (employeeId.equals(employee.getPersonID())
                        || employeeName.contains(employee.getFullName().toLowerCase())))
                    flag = true;
            } else if (originalKey instanceof Customers customer) {
                String customerId = (bill.getCustomer() != null) ? bill.getCustomer().getPersonID() : "";
                String customerName = (bill.getCustomer() != null) ? bill.getCustomer().getFullName().toLowerCase()
                        : "";

                if (request.equals("customer") && (customerId.equals(customer.getPersonID())
                        || customerName.contains(customer.getFullName().toLowerCase())))
                    flag = true;
            } else if (originalKey instanceof LocalDate) {
                if (request.equals("date") && bill.getDate().isEqual((LocalDate) originalKey))
                    flag = true;
            }

            // Add matching bill to result
            if (flag) {
                resultArray = Arrays.copyOf(resultArray, resultArray.length + 1);
                resultArray[count] = bill;
                flag = false;
                count++;
            }
        }

        if (count == 0) {
            System.out.println("Not found any Bill!");
            return null;
        }
        return resultArray;
    }

    // Advanced find
    public Bill[] find(BigDecimal minPrice, BigDecimal maxPrice, String request) {
        request = request.toLowerCase().trim();
        if (minPrice.compareTo(BigDecimal.ZERO) < 0 || maxPrice.compareTo(BigDecimal.ZERO) < 0)
            return null;
        if (request.equals("range") && ((minPrice.compareTo(maxPrice) >= 0))) {
            System.out.println("Error in price range!");
            return null;
        }

        int count = 0;
        boolean flag = false;
        Bill[] billList = new Bill[0];
        for (Bill bill : ds) {
            BigDecimal totalPrice = bill.getTotalPrice();

            if ((request.equals("min")) && (totalPrice.compareTo(minPrice) >= 0))
                flag = true;

            else if ((request.equals("max")) && (totalPrice.compareTo(maxPrice) <= 0))
                flag = true;

            else if (request.equals("range")
                    && ((totalPrice.compareTo(minPrice) >= 0) && (totalPrice.compareTo(maxPrice) <= 0)))
                flag = true;

            if (flag) {
                billList = Arrays.copyOf(billList, billList.length + 1);
                billList[count] = bill;
                flag = false;
                count++;
            }
        }
        if (count == 0) {
            System.out.println("Not found any Bill!");
            return null;
        }
        return billList;
    }

    // execute file resources
    // write file
    public void writeFile() throws IOException {
        try (DataOutputStream file = new DataOutputStream(
            new BufferedOutputStream(new FileOutputStream("src/main/resources/Bill", false)))) {
            file.writeInt(n);
            for (int i = 0; i < n; ++i) {
                SaleEvents saleCode = ds[i].getSaleCode(); 
                // write bill
                file.writeUTF(ds[i].getBillId());
                file.writeUTF(ds[i].getCustomer().getPersonID());
                file.writeUTF(ds[i].getEmployee().getPersonID());
                file.writeUTF(saleCode != null ? saleCode.getDetail().getPromoCode() : "");
                file.writeUTF(ds[i].getDate().toString());
                file.writeUTF(ds[i].getDiscount().toString());
                file.writeUTF(ds[i].getTotalPrice().toString());
            }
        } catch (Exception e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }

    // read file
    public void readFile() throws IOException {
        File testFile = new File("src/main/resources/Bill");
        if (testFile.length() == 0 || !testFile.exists())
            return;

        try (DataInputStream file = new DataInputStream(
            new BufferedInputStream(new FileInputStream("src/main/resources/Bill")))) {
            int billCount = file.readInt();
            Bill[] tmpBill = new Bill[billCount];

            for (int i = 0; i < billCount; i++) {
                // read bill
                String billId = file.readUTF();
                String customerId = file.readUTF();
                String employeeId = file.readUTF();
                String promoCode = file.readUTF();
                String getDate = file.readUTF();
                LocalDate date = !getDate.isEmpty() ? LocalDate.parse(getDate) : null;
                BigDecimal discount = new BigDecimal(file.readUTF());
                BigDecimal totalPrice = new BigDecimal(file.readUTF());

                // Execute id
                CustomersBUS customersList = new CustomersBUS();
                EmployeesBUS employeesList = new EmployeesBUS();
                SaleEventsBUS salesList = new SaleEventsBUS();
                // read file
                customersList.readFile();
                employeesList.readFile();
                salesList.readFile();
                // get object
                Customers customer = customersList.getCustomer(customerId);
                Employees employee = employeesList.getEmployee(employeeId);
                SaleEvents saleEvent = salesList.findByPromoCode(promoCode);

                tmpBill[i] = new Bill(billId, employee, customer, saleEvent, discount, totalPrice, date);
            }
            setList(tmpBill);
            setCount(billCount);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
