package BUS;

import Manager.Menu;
import util.Validate;
import DTO.Customers;

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

public class CustomersBUS implements IRuleSets {
     private Customers[] customersList;
     private int count;
     private final Scanner input = new Scanner(System.in);

     // *constructors (TEST DONE)
     public CustomersBUS() {
          this.count = 0;
          customersList = new Customers[0];
     }

     public CustomersBUS(Customers[] customersList, int count) {
          this.customersList = customersList;
          this.count = count;
     }

     // *getters / setters (TEST DONE)
     public Customers[] getCustomersList() {
          return this.customersList;
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
     // *show list (TEST DONE)
     public void showList() {
          if (customersList == null)
               return;
          for (Customers customer : customersList)
               customer.showInfo();
     }

     // *find methods (TEST DONE)
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

     public Customers[] relativeFind(Object originalKey, String request) {
          int count = 0;
          boolean flag = false;
          Customers[] customersArray = new Customers[0];
          request = request.toLowerCase().trim();

          for (Customers customer : customersList) {
               if (originalKey instanceof String) {
                    int month, year;
                    String key = (String) originalKey;
                    String firstName = customer.getFirstName();
                    String lastName = customer.getLastName();
                    String fullName = customer.getFullName();
                    String phone = customer.getPhoneNumber();
                    String address = customer.getAddress();
                    LocalDate dateOfBirth = customer.getDateOfBirth(); 
                    
                    // assign and check null
                    phone = Validate.requiredNotNull(phone) ? phone : "";
                    address = Validate.requiredNotNull(address) ? address.toLowerCase() : "";
                    firstName = Validate.requiredNotNull(firstName) ? firstName.toLowerCase() : "";
                    lastName = Validate.requiredNotNull(lastName) ? lastName.toLowerCase() : "";
                    fullName = Validate.requiredNotNull(fullName) ? fullName.toLowerCase() : "";
                    year = Validate.requiredNotNull(dateOfBirth) ? dateOfBirth.getYear() : 0;
                    month = Validate.requiredNotNull(dateOfBirth) ? dateOfBirth.getMonthValue() : 0;

                    if (request.equals("firstname") && firstName.contains(key.toLowerCase()))
                         flag = true;

                    else if (request.equals("lastname") && lastName.contains(key.toLowerCase()))
                         flag = true;

                    else if (request.equals("fullname") && fullName.equals(key.toLowerCase()))
                         flag = true;

                    else if (request.equals("phone") && phone.contains(key))
                         flag = true;

                    else if (request.equals("address") && address.contains(key.toLowerCase()))
                         flag = true;

                    else if (request.equals("month") && month == Validate.isNumber(key))
                         flag = true;

                    else if (request.equals("year") && year == Validate.isNumber(key)) {
                         System.out.println(year == Validate.isNumber(key));
                         flag = true;
                    }

               } else if (originalKey instanceof LocalDate)
                    if (request.equals("date") && (customer.getDateOfBirth().isEqual((LocalDate) originalKey)))
                         flag = true;

               if (flag) {
                    customersArray = Arrays.copyOf(customersArray, customersArray.length + 1);
                    customersArray[count] = customer;
                    flag = false;
                    count++;
               }
          }
          if (count == 0) {
               System.out.println("not found any customers!");
               return null;
          }
          return customersArray;
     }

     public Customers[] advancedFind(BigDecimal minPoint, BigDecimal maxPoint, String request) {
          request = request.toLowerCase().trim();
          if (request.equals("range")
                    && ((minPoint.compareTo(maxPoint) >= 0) || (minPoint.compareTo(BigDecimal.ZERO) < 0) ||
                              (maxPoint.compareTo(BigDecimal.ZERO) < 0))) {
               System.out.println("error range!");
               return null;
          }

          int count = 0;
          boolean flag = false;
          Customers[] customersArray = new Customers[0];
          for (Customers customer : customersList) {
               BigDecimal customerPoint = customer.getPoint();

               if ((request.equals("min")) && (customerPoint.compareTo(minPoint) >= 0))
                    flag = true;

               else if ((request.equals("max")) && (customerPoint.compareTo(maxPoint) <= 0))
                    flag = true;

               else if (request.equals("range")
                         && ((customerPoint.compareTo(minPoint) >= 0) && (customerPoint.compareTo(maxPoint) <= 0)))
                    flag = true;

               if (flag) {
                    customersArray = Arrays.copyOf(customersArray, customersArray.length + 1);
                    customersArray[count] = customer;
                    flag = false;
                    count++;
               }
          }
          if (count == 0) {
               System.out.println("not found any customers!");
               return null;
          }
          return customersArray;
     }

     // *search methods (TEST DONE)
     @Override
     public void search() {
          Menu.searchHandler();
     }

     @Override
     public void search(String nameOrID) {
          int index = find(nameOrID);
          if (index != -1)
               customersList[index].showInfo();
     }

     public void relativeSearch(Object key, String request) {
          Customers[] list = relativeFind(key, request);
          if (list != null)
               for (Customers customer : list)
                    customer.showInfo();
     }

     public void search(BigDecimal minPoint, BigDecimal maxPoint, String request) {
          Customers[] list = advancedFind(minPoint, maxPoint, request);
          if (list != null)
               for (Customers customer : list)
                    customer.showInfo();

     }

     // *add methods (TEST DONE)
     @Override
     public void add() {
          Menu.addHandler();
     }

     @Override
     public void add(Object customer) {
          if (customer instanceof Customers) {
               Customers newCustomer = (Customers) customer;
               newCustomer.setPersonID(newCustomer.getPersonID());
               customersList = Arrays.copyOf(customersList, customersList.length + 1);
               customersList[count] = newCustomer;
               count++;
          } else
               System.out.println("Your customer is not correct!");
     }

     public void add(Customers[] newCustomers) {
          int tempIndex = 0, newListLength = newCustomers.length;
          int initCount = getCount();
          int total = initCount + newListLength;
          customersList = Arrays.copyOf(customersList, customersList.length + newListLength);

          for (int i = initCount; i < total; i++, tempIndex++){
               newCustomers[tempIndex].setPersonID(newCustomers[tempIndex].getPersonID());
               customersList[i] = newCustomers[tempIndex];
          }
          this.count = total;
     }

     // *edit methods (TEST DONE)
     @Override
     public void edit() {
          Menu.editHandler();
     }

     @Override
     public void edit(String customerID) {
          int index = find(customerID);
          if (index != -1) {
               int userChoose;
               // show list for user choose
               customersList[index].showInfo();
               System.out.printf("| %s %s %s |\n", "I.Cancel", "-".repeat(20), "II.Edit");
               do {
                    System.out.print("choose option (1 or 2) : ");
                    String option = input.nextLine().trim();
                    userChoose = Validate.parseChooseHandler(option, 2);
               } while (userChoose == -1);
               if (userChoose == 1)
                    return;

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
                    if (!Validate.checkHumanName(lastName)) {
                         System.out.println("invalid last name!");
                         lastName = "";
                    }
               } while (lastName.isEmpty());
               customersList[index].setFullName(firstName, lastName);
          }
     }

     public void editDateOfBirth(String customerID) {
          int index = find(customerID);
          if (index != -1) {
               LocalDate date;
               int userChoose;
               // show list for user choose
               customersList[index].showInfo();
               System.out.printf("| %s %s %s |\n", "I.Cancel", "-".repeat(20), "II.Edit");
               do {
                    System.out.print("choose option (1 or 2) : ");
                    String option = input.nextLine().trim();
                    userChoose = Validate.parseChooseHandler(option, 2);
               } while (userChoose == -1);
               if (userChoose == 1)
                    return;

               do {
                    System.out.print("edit date of birth : ");
                    String dateInput = input.nextLine().trim();
                    date = Validate.isCorrectDate(dateInput);
               } while (date == null);
               customersList[index].setDateOfBirth(date);
          }
     }

     public void editPhone(String customerID) {
          int index = find(customerID);
          if (index != -1) {
               int userChoose;
               String phone;
               // show list for user choose
               customersList[index].showInfo();
               System.out.printf("| %s %s %s |\n", "I.Cancel", "-".repeat(20), "II.Edit");
               do {
                    System.out.print("choose option (1 or 2) : ");
                    String option = input.nextLine().trim();
                    userChoose = Validate.parseChooseHandler(option, 2);
               } while (userChoose == -1);
               if (userChoose == 1)
                    return;

               do {
                    System.out.print("edit phone: ");
                    phone = input.nextLine().trim();
                    if (!Validate.validatePhone(phone)) {
                         System.out.println("error phone number!");
                         phone = "";
                    }
               } while (phone.isEmpty());
               customersList[index].setPhoneNumber(phone);
          }
     }

     public void editPoint(String customerID) {
          int index = find(customerID);
          if (index != -1) {
               BigDecimal point;
               int userChoose;
               // show list for user choose
               customersList[index].showInfo();
               System.out.printf("| %s %s %s |\n", "I.Cancel", "-".repeat(20), "II.Edit");
               do {
                    System.out.print("choose option (1 or 2) : ");
                    String option = input.nextLine().trim();
                    userChoose = Validate.parseChooseHandler(option, 2);
               } while (userChoose == -1);
               if (userChoose == 1)
                    return;

               do {
                    System.out.print("edit point (VND) : ");
                    String value = input.nextLine();
                    point = Validate.isBigDecimal(value);
               } while (point == null);
               customersList[index].setPoint(point);
          }
     }

     public void editAddress(String customerID) {
          int index = find(customerID);
          if (index != -1) {
               String address;
               int userChoose;
               // show list for user choose
               customersList[index].showInfo();
               System.out.printf("| %s %s %s |\n", "I.Cancel", "-".repeat(20), "II.Edit");
               do {
                    System.out.print("choose option (1 or 2) : ");
                    String option = input.nextLine().trim();
                    userChoose = Validate.parseChooseHandler(option, 2);
               } while (userChoose == -1);
               if (userChoose == 1)
                    return;

               do {
                    System.out.print("edit address: ");
                    address = input.nextLine().trim();
                    if (!Validate.checkAddress(address)) {
                         System.out.println("error name!");
                         address = "";
                    }
               } while (address.isEmpty());
               customersList[index].setAddress(address);
          }
     }

     // *remove methods (TEST DONE)
     @Override
     public void remove() {
          Menu.removeHandler();
     }

     @Override
     public void remove(String nameOrID) {
          int index = find(nameOrID);
          if (index != -1) {
               int userChoose;
               // show list for user choose
               customersList[index].showInfo();
               System.out.printf("| %s %s %s |\n", "I.Cancel", "-".repeat(20), "II.Remove");
               do {
                    System.out.print("choose option (1 or 2) : ");
                    String option = input.nextLine().trim();
                    userChoose = Validate.parseChooseHandler(option, 2);
               } while (userChoose == -1);
               if (userChoose == 1)
                    return;

               for (int i = index; i < customersList.length - 1; i++)
                    customersList[i] = customersList[i + 1];
               customersList = Arrays.copyOf(customersList, customersList.length - 1);
               count--;
          }
     }

     // File operations for reading and writing
     public void writeFile() throws IOException {
          try (DataOutputStream file = new DataOutputStream(
                    new BufferedOutputStream(new FileOutputStream("src/main/resources/Customers", false)))) {
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
          File testFile = new File("src/main/resources/Customers");
          if (testFile.length() == 0)
               return;

          try (DataInputStream file = new DataInputStream(
                    new BufferedInputStream(new FileInputStream("src/main/resources/Customers")))) {
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
