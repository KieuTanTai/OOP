
package DTO;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Scanner;

import BUS.BillDetailsBUS;
import BUS.BooksBUS;
import BUS.CustomersBUS;
import BUS.EmployeesBUS;
import BUS.SaleEventsBUS;
import BUS.StationeriesBUS;
import BUS.BillBUS;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import util.Validate;
// import DTO.BillDetails;

public class Bill {
    private String billId;
    private Employees employee;
    private Customers customer;
    private SaleEvents saleCode;
    private BigDecimal discount;
    private BigDecimal totalPrice;
    private LocalDate date = LocalDate.now();
    Scanner sc = new Scanner(System.in);

    // constructors
    public Bill() {
    }

    public Bill(String billId, Employees employee, Customers customer, SaleEvents saleCode, BigDecimal discount,
            LocalDate date) {
        this.billId = billId;
        this.employee = employee;
        this.customer = customer;
        this.saleCode = saleCode;
        this.discount = discount;
        this.date = date;
    }

    public Bill(String billId, Employees employee, Customers customer, SaleEvents saleCode, BigDecimal discount,
            BigDecimal totalPrice, LocalDate date) {
        this.billId = billId;
        this.employee = employee;
        this.customer = customer;
        this.saleCode = saleCode;
        this.discount = discount;
        this.totalPrice = totalPrice;
        this.date = date;
    }

    // getter / setter
    public String getBillId() {
        return this.billId;
    }

    public Employees getEmployee() {
        return this.employee;
    }

    public Customers getCustomer() {
        return this.customer;
    }

    public SaleEvents getSaleCode() {
        return this.saleCode;
    }

    public BigDecimal getDiscount() {
        return this.discount;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public BigDecimal getTotalPrice() {
        return this.totalPrice;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public void setEmployee(Employees employee) {
        this.employee = employee;
    }

    public void setSaleCode(SaleEvents saleCode) {
        this.saleCode = saleCode;
    }

    public void setCustomer(Customers customer) {
        this.customer = customer;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    // other methods
    private SaleEvents getValidSale(LocalDate date, BigDecimal totalPrice) {
        try {
            SaleEventsBUS saleList = new SaleEventsBUS();
            saleList.readFile();
            SaleEvents[] validSale = saleList.findByDateRange(date);
            if (validSale == null)
                return null;
            // filter validSale with totalPrice for get best discount
            int count = 0;
            BigDecimal[] discounts = new BigDecimal[0];
            SaleEvents[] sales = new SaleEvents[0];
            for (SaleEvents event : validSale) {
                SaleEventsDetail detail = event.getDetail();
                BigDecimal minPrice = detail.getMinPrice();
                BigDecimal evDiscount = detail.getDiscount();
                BigDecimal maxDiscount = detail.getMaxPriceDiscount();

                // calc
                if (totalPrice.compareTo(minPrice) < 0)
                    continue;
                discounts = Arrays.copyOf(discounts, discounts.length + 1);
                sales = Arrays.copyOf(sales, sales.length + 1);
                discounts[count] = Validate.executePrice(totalPrice, maxDiscount, evDiscount);
                sales[count] = event;
                count++;
            }

            // get best saleEvent
            BigDecimal largest = Validate.isLargestDiscount(discounts);
            for (int i = 0; i < count; i++)
                if (largest.compareTo(discounts[i]) == 0)
                    return sales[i];
            return sales[0];
        } catch (Exception e) {
            System.out.printf("error reading file!\n%s\n", e.getMessage());
            return null;
        }
    }

    public String setBillId() {
        StringBuilder id;
        try {
            BillBUS billList = new BillBUS();
            billList.readFile();
            Bill[] list = billList.getList();

            if (list.length == 0) {
                id = new StringBuilder("00000001");
            } else {
                String getID = list[list.length - 1].getBillId();
                int prevID = Integer.parseInt(getID.substring(2, getID.length() - 2));
                id = new StringBuilder(String.format("%d", prevID + 1));
                while (id.length() != 8)
                    id.insert(0, "0");
            }
        } catch (Exception e) {
            System.out.println("error when execute with file!" + e.getMessage());
            id = new StringBuilder("00000001");
        }
        return billIdModifier(id.toString());
    }

    public Employees setEmployee() {
        try {
            int userChoice;
            EmployeesBUS list = new EmployeesBUS();
            list.readFile();
            if (list.getCount() == 0) // if not have any supplier
                return null;
            Employees[] tempList = list.relativeFind("Employee", "role");
            int length = tempList.length;
            for (int i = 0; i < length; i++) {
                System.out.printf("%d : \n", i + 1);
                tempList[i].showInfo();
            }
            do {
                System.out.print("choose employee (like 1, 2,etc...) : ");
                String option = sc.nextLine().trim();
                userChoice = Validate.parseChooseHandler(option, tempList.length);
            } while (userChoice == -1);
            return tempList[userChoice - 1];
        } catch (IOException e) {
            System.out.println("error reading file!\n" + e.getMessage());
            return null;
        }
    }

    public Customers setCustomer() {
        try {
            int index;
            String customerID;
            CustomersBUS list = new CustomersBUS();
            list.readFile();
            if (list.getCount() == 0) // if not have any supplier
                return null;
            do {
                System.out.print("Enter Customer's id: ");
                customerID = sc.nextLine().trim();
                // execute sc id
                index = list.find(customerID);
                if (index == -1)
                    customerID = "";
            } while (customerID.isEmpty());
            return list.getCustomersList()[index];
        } catch (IOException e) {
            System.out.println("error reading file!\n" + e.getMessage());
            return null;
        }
    }

    // set details
    public BillDetails[] setBillDetails(String billId) {
        BillDetailsBUS listBills = new BillDetailsBUS();
        int userChoice;

        do {
            System.out.println("I. Add detail");
            System.out.println("II. Remove detail");
            System.out.println("III. Edit detail");
            System.out.println("0: Exit!");
            System.out.println("-".repeat(60));
            System.out.print("choose option (like 0, 1, 2,etc...) : ");
            String option = sc.nextLine().trim();
            if (option.equals("0")) {
                System.out.println("Exit program.");
                break;
            }
            userChoice = Validate.parseChooseHandler(option, 3);

            // execute userChoice
            try {
                switch (userChoice) {
                    case 1: // add
                        Products product = getProducts();
                        int quantity = setQuantity(product);
                        listBills.add(new BillDetails(billId, quantity, product, product.getProductPrice()));
                        break;

                    case 2: // remove
                            // if not have any bill detail
                        if (listBills.getCount() == 0) {
                            System.out.println("not have any bill detail!");
                            break;
                        }
                        listBills.showList();
                        System.out.println("-".repeat(60));
                        do {
                            System.out.println("choose 0 to EXIST!");
                            System.out.print("choose bill (like 0, 1, 2,etc...) : ");
                            option = sc.nextLine().trim();
                            if (option.equals("0")) {
                                System.out.println("Exit program.");
                                break;
                            }
                            userChoice = Validate.parseChooseHandler(option, listBills.getCount());
                        } while (userChoice == -1);
                        if (!option.equals("0")) {
                            BillDetails detail = listBills.getList()[userChoice - 1];
                            listBills.remove(billId, detail.getProduct().getProductID());
                        }
                        break;

                    case 3: // edit
                        if (listBills.getCount() == 0) {
                            System.out.println("not have any bill detail!");
                            break;
                        }
                        listBills.edit(billId);
                        break;
                }
            } catch (Exception e) {
                System.out.printf("error when execute file!\nt%s\n", e.getMessage());
            }
        } while (true);
        return listBills.getList();
    }

    // private set methods for bill detail
    private Products getProducts() {
        Products product = null;
        String nameOrID;
        try {
            do {
                System.out.print("Enter product's name or product's id : ");
                nameOrID = sc.nextLine().trim();

                if (nameOrID.startsWith("ST") && nameOrID.endsWith("PD")) {
                    StationeriesBUS staList = new StationeriesBUS();
                    staList.readFile();
                    product = staList.getStationary(nameOrID);
                } else if (nameOrID.startsWith("BK") && nameOrID.endsWith("PD")) {
                    BooksBUS bookList = new BooksBUS();
                    bookList.readFile();
                    product = bookList.getBook(nameOrID);
                }

                if (product == null)
                    System.out.println("Not found any product!");
            } while (product == null);
            return product;
        } catch (Exception e) {
            System.out.printf("error when execute file!\nt%s\n", e.getMessage());
            return null;
        }

    }

    private int setQuantity(Products product) {
        int quantity;
        // let new quantity
        do {
            System.out.print("set bill quantity : ");
            String quantityInput = sc.nextLine().trim();
            quantity = Validate.isNumber(quantityInput);
            if (quantity > product.getQuantity()) {
                System.out.println("This quantity is more than real quantity of this product !");
                quantity = -1;
            }
        } while (quantity == -1);
        return quantity;
    }

    // set info
    public void setInfo() {
        System.out.println("*".repeat(60));
        String id = setBillId();

        // fields date
        LocalDate date = LocalDate.now();
        System.out.println("-".repeat(60));
        Employees employee = setEmployee();

        System.out.println("-".repeat(60));
        Customers customer = setCustomer();

        System.out.println("-".repeat(60));
        BillDetails[] detailsArray = setBillDetails(id);
        BigDecimal totalPrice = new BigDecimal(0);

        int userChoice;
        System.out.println("*".repeat(60));
        System.out.printf("| %s %s %s |\n", "I.Cancel", "-".repeat(20), "II.Submit");

        do {
            System.out.print("choose option (1 or 2) : ");
            String option = sc.nextLine().trim();
            userChoice = Validate.parseChooseHandler(option, 2);
        } while (userChoice == -1);
        System.out.println("*".repeat(60));

        if (userChoice == 1)
            System.out.println("ok!");
        else {
            setBillId(id);
            setDate(date);
            setEmployee(employee);
            setCustomer(customer);

            // execute bill detail
            try {
                BillDetailsBUS detailList = new BillDetailsBUS();
                StationeriesBUS staList = new StationeriesBUS();
                BooksBUS bookList = new BooksBUS();
                bookList.readFile();
                staList.readFile();
                detailList.readFile();
                for (BillDetails detail : detailsArray) {
                    String productID = detail.getProduct().getProductID();
                    if (detailList.find(detail.getBillId(), productID) == -1) {
                        totalPrice = totalPrice.add(detail.getSubTotal());
                        detailList.add(detail);

                        // execute update quantity
                        if (productID.startsWith("ST") && productID.endsWith("PD"))
                            staList.updateQuantity(detail);

                        else if (productID.startsWith("BK") && productID.endsWith("PD"))
                            bookList.updateQuantity(detail);
                    }
                }
                staList.writeFile();
                bookList.writeFile();
                detailList.writeFile();
            } catch (Exception e) {
                System.out.println("error writing or reading file!\n" + e.getMessage());
            }
            // set all fields BigDecimal
            SaleEvents saleCode = getValidSale(date, totalPrice);
            BigDecimal discount;
            if (saleCode != null)
                discount = Validate.executePrice(totalPrice, saleCode.getDetail().getMaxPriceDiscount(), saleCode.getDetail().getDiscount());
            else
                discount = BigDecimal.ZERO;
            setTotalPrice(totalPrice);
            setSaleCode(saleCode);
            setDiscount(discount);
            System.out.println("create and set fields success");
        }
    }

    public void showInfo() {
        LocalDate date = this.getDate();
        BigDecimal totalPrice = this.getTotalPrice();
        BigDecimal discount = this.getDiscount();
        String billId = this.getBillId(), employeeName = this.getEmployee().getFullName(),
                customerName = this.getCustomer().getFullName();

        System.out.println("=".repeat(140));
        System.out.printf("| %-22s : %s \n", "Bill ID", billId != null ? billId : "N/A");
        System.out.printf("| %-22s : %s \n", "Date",
                date != null ? date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) : "N/A");
        System.out.printf("| %-22s : %s \n", "Employee", employeeName != null ? employeeName : "N/A");
        System.out.printf("| %-22s : %s \n", "Customer", customerName != null ? customerName : "N/A");

        try {
            BillDetailsBUS detailList = new BillDetailsBUS();
            detailList.readFile();
            BillDetails[] list = detailList.relativeFind(this.getBillId());
            for (BillDetails detail : list) {
                detail.showInfo();
            }
        } catch (Exception e) {
            System.out.println("| Error loading bill details!\n" + e.getMessage());
        }

        System.out.printf("| %-22s : %s \n", "Discount", discount != null ? Validate.formatPrice(discount) : "N/A");
        System.out.println("|" + "*".repeat(139));
        System.out.printf("| %-22s : %s \n", "Total Price",
                totalPrice != null ? Validate.formatPrice(totalPrice.subtract(discount)) : "N/A");
        System.out.println("=".repeat(140));
    }

    // modify bill id
    private String billIdModifier(String billId) {
        if (billId.startsWith("BI") && billId.endsWith("LL") && billId.length() == 12)
            return billId;
        if (!Validate.validateID(billId)) {
            System.out.println("error id!");
            return "N/A";
        }
        return "BI" + billId + "LL";
    }
}
