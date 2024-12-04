package BUS;

import DTO.BillDetails;
import DTO.Products;
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
import java.util.Arrays;
import java.util.Scanner;

public class BillDetailsBUS {
    private BillDetails[] ds;
    private int n;
    Scanner sc = new Scanner(System.in);

    // constructors
    public BillDetailsBUS() {
        this.n = 0;
        ds = new BillDetails[0];
    }

    public BillDetailsBUS(BillDetails[] ds, int n) {
        this.n = n;
        this.ds = ds;
    }

    public BillDetailsBUS(BillDetailsBUS list) {
        ds = list.ds;
        n = list.n;
    }

    // getter / setter
    public BillDetails[] getList() {
        return ds;
    }

    public int getCount() {
        return this.n;
    }

    public void setList(BillDetails[] ds) {
        this.ds = ds;
    }

    public void setBillDetail(BillDetails now, BillDetails newDetail) {
        for (int i = 0; i < this.n; i++) {
            String nowProductID = ds[i].getProduct().getProductID();
            String newProductID = now.getProduct().getProductID();
            if (ds[i].getBillId().equals(now.getBillId()) && nowProductID.equals(newProductID))
                ds[i] = newDetail;
        }
    }

    public void setCount(int n) {
        this.n = n;
    }

    // other methods like add, search, find, remove, edit,...
    // show list
    public void showList() {
        System.out.println(ds.length);
        if (ds == null)
            return;
        showAsTable(ds);
    }

    // add methods
    public void add(BillDetails detail) {
        if (find(detail.getBillId(), detail.getProduct().getProductID()) == -1) {
            ds = Arrays.copyOf(ds, ds.length + 1);
            ds[n] = detail;
            this.n++;
        }
    }

    public void add(BillDetails[] details) {
        int tempIndex = 0, newListLength = details.length;
        int initCount = n;
        int total = initCount + newListLength;
        ds = Arrays.copyOf(ds, ds.length + newListLength);

        for (int i = initCount; i < total; i++, tempIndex++) {
            ds[i] = details[tempIndex];
        }
        this.n = total;
    }

    // edit methods
    public void edit(String billID) {
        BillDetails[] list = relativeFind(billID);
        if (list != null) {
            showAsTable(list);

            int userChoose = 1, newQuantity;
            do {
                System.out.println("Choose 0 to EXIT!");
                System.out.print("Choose detail you want to edit (e.g., 1, 2, 3...): ");
                String option = sc.nextLine().trim();
                if (option.equals("0")) {
                    System.out.println("Exiting edit process.");
                    return;
                }
                userChoose = Validate.parseChooseHandler(option, list.length);
            } while (userChoose == -1);

            // for set type of product
            int productChoose = -1;
            do {
                System.out.println("Choose product type!");
                System.out.println("I. Book");
                System.out.println("II. Stationary");
                System.out.print("Your choice (1 or 2): ");
                String choice = sc.nextLine().trim();
                productChoose = Validate.parseChooseHandler(choice, 2);
            } while (productChoose == -1);

            // get new product
            Products product = getProducts(productChoose);
            if (product == null) {
                System.out.println("Error product! Exit program!");
                return;
            }
            // set quantity and calc other BigDecimal fields
            newQuantity = setQuantity();
            setBillDetail(list[userChoose - 1], new BillDetails(billID, newQuantity, product, product.getProductPrice()));
            System.out.println("Bill details updated successfully!");
        } 
    }

    // some methods private for execute specific fields
    private Products getProducts(int productChoose) {
        String nameOrID;
        System.out.println("Enter \"EXIT\" if you wanna exit! ");
        System.out.print("Enter product name or ID to find: ");
        Products product;
        try {
            if (productChoose == 1) {
                BooksBUS listBooks = new BooksBUS();
                listBooks.readFile();
                do {
                    nameOrID = sc.nextLine().trim();

                    // if nameOrID is "exist" then return null
                    if (nameOrID.toLowerCase().equals("exit"))
                        return null;

                    // now product choose is use for get index of product
                    productChoose = listBooks.find(nameOrID);
                    if (productChoose == -1)
                        nameOrID = "";
                } while (nameOrID.isEmpty());
                product = listBooks.getBooksList()[productChoose];
            } else {
                StationeriesBUS listStationeries = new StationeriesBUS();
                listStationeries.readFile();
                do {
                    nameOrID = sc.nextLine().trim();
                    // if nameOrID is "exist" then return null
                    if (nameOrID.toLowerCase().equals("exit"))
                        return null;

                    // now product choose is use for get index of product
                    productChoose = listStationeries.find(nameOrID);
                    if (productChoose == -1)
                        nameOrID = "";
                } while (nameOrID.isEmpty());
                product = listStationeries.getStaList()[productChoose];
            }
            return product;
        } catch (Exception e) {
            System.out.printf("Error reading file: %s\n", e.getMessage());
            return null;
        }
    }

    private int setQuantity() {
        int quantity;
        do {
            System.out.print("Set new quantity: ");
            String quantityInput = sc.nextLine().trim();
            quantity = Validate.isNumber(quantityInput);
            if (!Validate.checkQuantity(quantity)) {
                System.out.println("Error quantity! What the **** are you cooking!");
                quantity = -1;
            }
        } while (quantity == -1);
        return quantity;
    }

    // remove methods
     public void remove(String billID) {
          int size = 0;
          BillDetails[] reduceArray = new BillDetails[0];
         for (BillDetails detail : ds) {
             if (detail.getBillId().equals(billID))
                 continue;
             reduceArray = Arrays.copyOf(reduceArray, reduceArray.length + 1);
             reduceArray[size] = detail;
             size++;
         }

          if (size == ds.length) {
               System.out.println("not found any mid!");
               return;
          }
          setCount(size);
          setList(reduceArray);
     }


    public void remove(String billID, String productID) {
        int size = 0;
        BillDetails[] reducedArray = new BillDetails[0];
        for (BillDetails billDetail : ds) {
            if (billDetail.getBillId().equals(billID) && billDetail.getProduct().getProductID().equals(productID))
                continue;
            reducedArray = Arrays.copyOf(reducedArray, reducedArray.length + 1);
            reducedArray[size] = billDetail;
            size++;
        }

        if (size == ds.length) {
            System.out.println("not found any match!");
            return;
        }
        ds = reducedArray;
        n = size;
    }

    // find methods
    // strict find
    public int find(String billId, String productID) {
        for (int i = 0; i < ds.length; i++) {
            if (ds[i].getBillId().equals(billId) && ds[i].getProduct().getProductID().equals(productID)) {
                return i;
            }
        }
        return -1;
    }

    // relative find
    public BillDetails[] relativeFind(String billId) {
        int count = 0;
        BillDetails[] list = new BillDetails[0];
        for (BillDetails detail : ds) {
            if (detail.getBillId().equals(billId)) {
                list = Arrays.copyOf(list, list.length + 1);
                list[count] = detail;
                count++;
            }
        }
        if (count == 0) {
            System.out.println("Not found any bill details!");
            return null;
        }
        return list;
    }

    // edit methods

    // *show as table methods (TEST DONE)
    public void showAsTable(BillDetails[] list) {
        if (list == null || list.length == 0)
            return;
        System.out.println("=".repeat(140));
        System.out.printf("| %-6s %-15s %-66s %-12s %-16s %-16s |\n",
                "No.", "Bill ID", "Product Name", "Quantity", "Price (VND)", "Sub Total (VND)");
        System.out.println("=".repeat(140));
        for (int i = 0; i < list.length; i++) {
            if (i > 0)
                System.out.println("|" + "-".repeat(138) + "|");
            System.out.printf("| %-6d %-15s %-66s %-12d %-16s %-16s |\n",
                    i + 1,
                    list[i].getBillId(),
                    list[i].getProduct().getProductName(),
                    list[i].getQuantity(),
                    Validate.formatPrice(list[i].getPrice()),
                    Validate.formatPrice(list[i].getSubTotal()));
        }
        System.out.println("=".repeat(140));
    }

    public void showAsTable(BillDetails item) {
        if (item == null)
            return;
        System.out.println("=".repeat(140));
        System.out.printf("| %-6s %-15s %-66s %-12s %-16s %-16s |\n",
                "No.", "Bill ID", "Product Name", "Quantity", "Price (VND)", "Sub Total (VND)");
        System.out.println("=".repeat(140));
        System.out.printf("| %-6d %-15s %-66s %-12d %-16s %-16s |\n",
                1,
                item.getBillId(),
                item.getProduct().getProductName(),
                item.getQuantity(),
                Validate.formatPrice(item.getPrice()),
                Validate.formatPrice(item.getSubTotal()));
        System.out.println("=".repeat(140));
    }

    // exec file
    // write file
    public void writeFile() throws IOException {
        try (DataOutputStream file = new DataOutputStream(
                new BufferedOutputStream(new FileOutputStream("src/main/resources/BillDetails", false)))) {
            file.writeInt(n);
            for (BillDetails billDetail : ds) {
                file.writeUTF(billDetail.getBillId());
                file.writeInt(billDetail.getQuantity());
                file.writeUTF(billDetail.getProduct().getProductID());
                file.writeUTF(billDetail.getPrice().toString());
            }
        } catch (Exception err) {
            System.out.printf("error writing file!\n%s\n", err.getMessage());
        }
    }

    // read file
    public void readFile() throws IOException {
        File testFile = new File("src/main/resources/BillDetails");
        if (testFile.length() == 0 || !testFile.exists())
            return;

        try (DataInputStream file = new DataInputStream(
                new BufferedInputStream(new FileInputStream("src/main/resources/BillDetails")))) {
            int n = file.readInt();
            BillDetails[] list = new BillDetails[n];
            for (int i = 0; i < n; i++) {
                String billId = file.readUTF();
                int quantity = file.readInt();
                String productID = file.readUTF();
                BigDecimal price = new BigDecimal(file.readUTF());

                Products product;
                if (productID.startsWith("ST") && productID.endsWith("PD")) {
                    StationeriesBUS staList = new StationeriesBUS();
                    staList.readFile();
                    product = staList.getStationary(productID);
                } else if (productID.startsWith("BK") && productID.endsWith("PD")) {
                    BooksBUS bookList = new BooksBUS();
                    bookList.readFile();
                    product = bookList.getBook(productID);
                } else
                    product = null;
                list[i] = new BillDetails(billId, quantity, product, price);
            }
            setCount(n);
            setList(list);
        } catch (Exception err) {
            System.out.printf("error reading file!\n%s\n", err.getMessage());
        }
    }

}