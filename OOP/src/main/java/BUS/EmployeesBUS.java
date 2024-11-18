package BUS;

import Manager.Menu;
import util.Validate;
import DTO.Employees;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Scanner;

public class EmployeesBUS implements IRuleSets {
    private Employees[] employeesList;
    private int count;
    private final Scanner input = new Scanner(System.in);

    // constructors 
    public EmployeesBUS() {
        this.count = 0;
        employeesList = new Employees[0];
    }

    public EmployeesBUS(Employees[] employeesList, int count) {
        this.employeesList = employeesList;
        this.count = count;
    }

    // getter / setter
    public Employees[] getEmployeesList() {
        return Arrays.copyOf(this.employeesList, this.count);
    }

    public Employees getEmployee(String employeeID) {
        for (Employees employee : employeesList)
            if (employee.getPersonID().equals(employeeID))
                return employee;
        return null;
    }

    public int getCount() {
        return count;
    }

    public void setEmployeesList(Employees[] employeesList) {
        this.employeesList = employeesList;
    }

    public void setCount(int count) {
        this.count = count;
    }

    // all others methods like: add remove edit find show....
    // show list
    public void showList() {
        if (employeesList == null)
            return;
        for (Employees employee : employeesList)
            employee.showInfo();
    }

    // find methods
    @Override
    public void find() {
        Menu.addHandler();
    }

    @Override
    public int find(String nameOrID) {
        for (int i = 0; i < employeesList.length; i++)
            if (employeesList[i].getPersonID().equals(nameOrID) ||
                employeesList[i].getFullName().toLowerCase().equals(nameOrID.toLowerCase().trim()))
                return i;
        System.out.println("Your employee is not found!");
        return -1;
    }

    public Employees[] relativeFind(String name) {
        int count = 0;
        Employees[] employeeArray = new Employees[0];
        for (Employees employee : employeesList)
            if (employee.getFullName().toLowerCase().contains(name.toLowerCase())) {
                employeeArray = Arrays.copyOf(employeeArray, employeeArray.length + 1);
                employeeArray[count] = employee;
                count++;
            }
        if (count == 0) {
            System.out.println("Not found any employees!");
            return null;
        }
        return employeeArray;
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
            employeesList[index].showInfo();
    }

    public void relativeSearch(String name) {
        Employees[] list = relativeFind(name);
        if (list != null)
            for (Employees employee : list)
                employee.showInfo();
    }

    // add methods
    @Override
    public void add() {
        Menu.addHandler();
    }

    @Override
    public void add(Object employee) {
        if (employee instanceof Employees) {
            employeesList = Arrays.copyOf(employeesList, employeesList.length + 1);
            employeesList[count] = (Employees) employee;
            count++;
        } else
            System.out.println("Your employee is not correct!");
    }

    public void add(Employees[] newEmployees) {
        int tempIndex = 0, newListLength = newEmployees.length;
        int initCount = getCount();
        int total = initCount + newListLength;
        employeesList = Arrays.copyOf(employeesList, employeesList.length + newListLength);

        for (int i = initCount; i < total; i++, tempIndex++)
            employeesList[i] = newEmployees[tempIndex];
        this.count = total;
    }

    // edit methods
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
            employeesList[index].setFullName(firstName, lastName);
        }
    }

    // remove methods
    @Override
    public void remove() {
        Menu.removeHandler();
    }

    @Override
    public void remove(String nameOrID) {
        int index = find(nameOrID);
        if (index != -1) {
            for (int i = index; i < employeesList.length - 1; i++)
                employeesList[i] = employeesList[i + 1];
            employeesList = Arrays.copyOf(employeesList, employeesList.length - 1);
            count--;
        }
    }

    // execute file resources
    // write file
    public void writeFile() throws IOException {
        try (DataOutputStream file = new DataOutputStream(
                new FileOutputStream("src/main/resources/Employees", false))) {
            file.writeInt(count);
            for (Employees employee : employeesList) {
                file.writeUTF(employee.getPersonID());
                file.writeUTF(employee.getFirstName());
                file.writeUTF(employee.getLastName());
                file.writeUTF(employee.getDateOfBirth().toString());
                file.writeUTF(employee.getPhoneNumber());
                file.writeUTF(employee.getStatus());
                file.writeUTF(employee.getUsername());
                file.writeUTF(employee.getPassword());
                file.writeUTF(employee.getRole());
            }
        } catch (Exception err) {
            System.out.printf("Error writing file!\n%s\n", err.getMessage());
        }
    }

    // read file
    public void readFile() throws IOException {
        try (DataInputStream file = new DataInputStream(new FileInputStream("src/main/resources/Employees"))) {
            count = file.readInt();
            Employees[] list = new Employees[count];
            for (int i = 0; i < count; i++) {
                String employeeID = file.readUTF();
                String firstName = file.readUTF();
                String lastName = file.readUTF();
                LocalDate dateOfBirth = LocalDate.parse(file.readUTF());
                String phoneNumber = file.readUTF();
                String status = file.readUTF();
                String userName = file.readUTF();
                String password = file.readUTF();
                String role = file.readUTF();
                list[i] = new Employees(employeeID, firstName, lastName, dateOfBirth, phoneNumber, status, userName, password, role);
            }
            setCount(count);
            setEmployeesList(list);
        } catch (Exception err) {
            System.out.printf("Error reading file!\n%s\n", err.getMessage());
        }
    }
}
