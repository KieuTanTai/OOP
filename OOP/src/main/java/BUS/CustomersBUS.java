package BUS;

import Manager.Menu;
import util.Validate;
import DTO.Customers;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Scanner;

public class CustomersBUS implements IRuleSets {
     private Customers[] customersList;
     private int count;
     private final Scanner input = new Scanner(System.in);

     // constructors
     public CustomersBUS() {
          this.count = 0;
          customersList = new Customers[0];
     }

     public CustomersBUS(Customers[] customersList, int count) {
          this.customersList = customersList;
          this.count = count;
     }

     // getters / setters
     public Customers[] getCustomersList() {
          return Arrays.copyOf(this.customersList, this.count);
     }

     public Customers getCustomer(String customerID) {
          for (Customers customer : customersList)
               if (customer.getPersonID().equals(customerID))
                    return customer;
          return null;
     }

     public int getCount() {
          return count;
     }

     public void setCustomersList(Customers[] customersList) {
          this.customersList = customersList;
     }

     public void setCount(int count) {
          this.count = count;
     }

     // all others methods like: add remove edit find show....
     // show list
     public void showList() {
          if (customersList == null)
               return;
          for (Customers customer : customersList)
               customer.showInfo();
     }

     // find methods
     @Override
     public void find() {
          Menu.addHandler();
     }

     @Override
     public int find(String nameOrID) {
          for (int i = 0; i < customersList.length; i++)
               if (customersList[i].getPersonID().equals(nameOrID) ||
                         customersList[i].getFullName().toLowerCase().equals(nameOrID.toLowerCase().trim()))
                    return i;
          System.out.println("Your customer is not found!");
          return -1;
     }

     public Customers[] relativeFind(String name) {
          int count = 0;
          Customers[] customerArray = new Customers[0];
          for (Customers customer : customersList)
               if (customer.getFullName().toLowerCase().contains(name.toLowerCase())) {
                    customerArray = Arrays.copyOf(customerArray, customerArray.length + 1);
                    customerArray[count] = customer;
                    count++;
               }
          if (count == 0) {
               System.out.println("Not found any customers!");
               return null;
          }
          return customerArray;
     }

     @Override
     public void search() {
          Menu.searchHandler();
     }

     @Override
     public void search(String nameOrID) {
          int index = find(nameOrID);
          if (index != -1)
               customersList[index].showInfo();
          ;
     }

     public void relativeSearch(String name) {
          Customers[] list = relativeFind(name);
          if (list != null)
               for (Customers customer : list)
                    customer.showInfo();
     }

     @Override
     public void add() {
          Menu.addHandler();
     }

     @Override
     public void add(Object customer) {
          if (customer instanceof Customers) {
               customersList = Arrays.copyOf(customersList, customersList.length + 1);
               customersList[count] = (Customers) customer;
               count++;
          } else
               System.out.println("Your customer is not correct!");
     }

     public void add(Customers[] newCustomers) {
          int tempIndex = 0, newListLength = newCustomers.length;
          int initCount = getCount();
          int total = initCount + newListLength;
          customersList = Arrays.copyOf(customersList, customersList.length + newListLength);

          for (int i = initCount; i < total; i++, tempIndex++)
               customersList[i] = newCustomers[tempIndex];
          this.count = total;
     }

     @Override
     public void edit() {
          Menu.editHandler();
     }

     @Override
     public void edit(String nameOrID) {
          int index = find(nameOrID);
          if (index != -1) {
               String firstName, lastName;
               do {
                    System.out.print("edit first name: ");
                    firstName = input.nextLine().trim();
                    if (!Validate.checkHumanName(firstName)) {
                         System.out.println("invalid first name!");
                         firstName = "";
                    }
               } while (firstName.isEmpty());

               do {
                    System.out.print("edit last name: ");
                    lastName = input.nextLine().trim();
                    if (!Validate.checkName(lastName)) {
                         System.out.println("invalid last name!");
                         lastName = "";
                    }
               } while (lastName.isEmpty());
               customersList[index].setFullName(firstName, lastName);
          }
     }

     @Override
     public void remove() {
          Menu.removeHandler();
     }

     @Override
     public void remove(String nameOrID) {
          int index = find(nameOrID);
          if (index != -1) {
               for (int i = index; i < customersList.length - 1; i++)
                    customersList[i] = customersList[i + 1];
               customersList = Arrays.copyOf(customersList, customersList.length - 1);
               count--;
          }
     }

     // File operations for reading and writing
     public void writeFile() throws IOException {
          try (DataOutputStream file = new DataOutputStream(
                    new FileOutputStream("src/main/resources/Customers", false))) {
               file.writeInt(count);
               for (Customers customer : customersList) {
                    file.writeUTF(customer.getPersonID());
                    file.writeUTF(customer.getFirstName());
                    file.writeUTF(customer.getLastName());
                    file.writeUTF(customer.getDateOfBirth().toString());
                    file.writeUTF(customer.getPhoneNumber());
                    file.writeUTF(customer.getAddress());
                    file.writeUTF(customer.getPoint().toString());
               }
          } catch (Exception err) {
               System.out.printf("Error writing file!\n%s\n", err.getMessage());
          }
     }

     public void readFile() throws IOException {
          try (DataInputStream file = new DataInputStream(new FileInputStream("src/main/resources/Customers"))) {
               count = file.readInt();
               Customers[] list = new Customers[count];
               for (int i = 0; i < count; i++) {
                    String customerID = file.readUTF();
                    String firstName = file.readUTF();
                    String lastName = file.readUTF();
                    LocalDate dateOfBirth = LocalDate.parse(file.readUTF());
                    String phoneNumber = file.readUTF();
                    String address = file.readUTF();
                    BigDecimal point = new BigDecimal(file.readUTF());
                    list[i] = new Customers(customerID, firstName, lastName, dateOfBirth, phoneNumber, address, point);
               }
               setCount(count);
               setCustomersList(list);
          } catch (Exception err) {
               System.out.printf("Error reading file!\n%s\n", err.getMessage());
          }
     }
}
