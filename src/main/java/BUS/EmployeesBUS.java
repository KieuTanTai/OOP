package BUS;

import Manager.Menu;
import util.Validate;
import DTO.Employees;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Scanner;

public class EmployeesBUS implements IRuleSets {
    private int count;
    private Employees[] employeesList;
    private final Scanner input = new Scanner(System.in);

    // *constructors (TEST DONE)
    public EmployeesBUS() {
        this.count = 0;
        employeesList = new Employees[0];
    }

    public EmployeesBUS(Employees[] employeesList, int count) {
        this.count = count;
        this.employeesList = employeesList;
    }

    // *getters / setters (TEST DONE)
    public Employees[] getEmployeesList() {
        return this.employeesList;
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
    // *show list (TEST DONE)
    public void showList() {
        if (employeesList == null)
            return;
        for (Employees employee : employeesList)
            employee.showInfo();
    }

    // *find methods(TEST DONE)
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

    public Employees[] relativeFind(Object originalKey, String request) {
        int count = 0;
        boolean flag = false;
        Employees[] employeesArray = new Employees[0];
        request = request.toLowerCase().trim();

        for (Employees employee : employeesList) {
            if (originalKey instanceof String) {
                int month, year;
                String key = (String) originalKey;
                String firstName = employee.getFirstName();
                String lastName = employee.getLastName();
                String fullName = employee.getFullName();
                String phone = employee.getPhoneNumber();
                String status = employee.getStatus();
                // String username = employee.getUsername();
                String role = employee.getRole();
                LocalDate dateOfBirth = employee.getDateOfBirth();

                // Assign and check null
                phone = Validate.requiredNotNull(phone) ? phone : "";
                status = Validate.requiredNotNull(status) ? status.toLowerCase() : "";
                // username = Validate.requiredNotNull(username) ? username.toLowerCase() : "";
                role = Validate.requiredNotNull(role) ? role.toLowerCase() : "";
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

                else if (request.equals("status") && status.equals(key.toLowerCase()))
                    flag = true;

                // else if (request.equals("username") && username.contains(key.toLowerCase()))
                // flag = true;

                else if (request.equals("role") && role.contains(key.toLowerCase()))
                    flag = true;

                else if (request.equals("month") && month == Validate.isNumber(key))
                    flag = true;

                else if (request.equals("year") && year == Validate.isNumber(key))
                    flag = true;

            } else if (originalKey instanceof LocalDate)
                if (request.equals("date") && (employee.getDateOfBirth().isEqual((LocalDate) originalKey)))
                    flag = true;

            if (flag) {
                employeesArray = Arrays.copyOf(employeesArray, employeesArray.length + 1);
                employeesArray[count] = employee;
                flag = false;
                count++;
            }
        }
        if (count == 0) {
            System.out.println("Not found any employees!");
            return null;
        }
        return employeesArray;
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
            employeesList[index].showInfo();
    }

    public void relativeSearch(Object key, String request) {
        Employees[] list = relativeFind(key, request);
        if (list != null)
            for (Employees employee : list)
                employee.showInfo();
    }

    // *add methods (TEST DONE)
    @Override
    public void add() {
        Menu.addHandler();
    }

    @Override
    public void add(Object employee) {
        if (employee instanceof Employees) {
            Employees newEmployee = (Employees) employee;
            newEmployee.setPersonID(newEmployee.getPersonID());
            employeesList = Arrays.copyOf(employeesList, employeesList.length + 1);
            employeesList[count] = newEmployee;
            count++;
        } else
            System.out.println("Your employee is not correct!");
    }

    public void add(Employees[] newEmployees) {
        int tempIndex = 0, newListLength = newEmployees.length;
        int initCount = getCount();
        int total = initCount + newListLength;
        employeesList = Arrays.copyOf(employeesList, employeesList.length + newListLength);

        for (int i = initCount; i < total; i++, tempIndex++) {
            newEmployees[tempIndex].setPersonID(newEmployees[tempIndex].getPersonID());
            employeesList[i] = newEmployees[tempIndex];
        }
        this.count = total;
    }

    // *edit methods (TEST DONE)
    @Override
    public void edit() {
        Menu.editHandler();
    }

    @Override
    public void edit(String employeeID) {
        int index = find(employeeID);
        if (index != -1) {
            int userChoose;
            // show list for user choose
            employeesList[index].showInfo();
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
            employeesList[index].setFullName(firstName, lastName);
        }
    }

    public void editDateOfBirth(String employeeID) {
        int index = find(employeeID);
        if (index != -1) {
            LocalDate date;
            int userChoose;

            employeesList[index].showInfo();
            System.out.printf("| %s %s %s |\n", "I.Cancel", "-".repeat(20), "II.Edit");
            do {
                System.out.print("Choose option (1 or 2): ");
                String option = input.nextLine().trim();
                userChoose = Validate.parseChooseHandler(option, 2);
            } while (userChoose == -1);
            if (userChoose == 1)
                return;

            do {
                System.out.print("Edit date of birth: ");
                String dateInput = input.nextLine().trim();
                date = Validate.isCorrectDate(dateInput);
            } while (date == null);

            employeesList[index].setDateOfBirth(date);
        }
    }

    public void editPhone(String employeeID) {
        int index = find(employeeID);
        if (index != -1) {
            String phone;
            int userChoose;

            employeesList[index].showInfo();
            System.out.printf("| %s %s %s |\n", "I.Cancel", "-".repeat(20), "II.Edit");
            do {
                System.out.print("Choose option (1 or 2): ");
                String option = input.nextLine().trim();
                userChoose = Validate.parseChooseHandler(option, 2);
            } while (userChoose == -1);
            if (userChoose == 1)
                return;

            do {
                System.out.print("Edit phone: ");
                phone = input.nextLine().trim();
                if (!Validate.validatePhone(phone)) {
                    System.out.println("Error: Invalid phone number!");
                    phone = "";
                }
            } while (phone.isEmpty());

            employeesList[index].setPhoneNumber(phone);
        }
    }

    public void editStatus(String employeeID) {
        int index = find(employeeID);
        if (index != -1) {
            String[] status = { "Active", "Inactive" };
            int userChoose;

            employeesList[index].showInfo();
            System.out.printf("| %s %s %s |\n", "I.Cancel", "-".repeat(20), "II.Edit");
            do {
                System.out.print("Choose option (1 or 2): ");
                String option = input.nextLine().trim();
                userChoose = Validate.parseChooseHandler(option, 2);
            } while (userChoose == -1);

            if (userChoose == 1)
                return;

            System.out.printf("| %s %s %s |\n", "I.Active", "-".repeat(20), "II.Inactive");
            do {
                System.out.print("choose option (1 or 2) : ");
                String option = input.nextLine().trim();
                userChoose = Validate.parseChooseHandler(option, 2);
            } while (userChoose == -1);
            employeesList[index].setStatus(status[userChoose - 1]);
        }
    }

    public void editRole(String employeeID) {
        int index = find(employeeID);
        if (index != -1) {
            int userChoose;

            employeesList[index].showInfo();
            System.out.printf("| %s %s %s |\n", "I.Cancel", "-".repeat(20), "II.Edit");
            do {
                System.out.print("Choose option (1 or 2): ");
                String option = input.nextLine().trim();
                userChoose = Validate.parseChooseHandler(option, 2);
            } while (userChoose == -1);

            if (userChoose == 1)
                return;

            String[] roles = { "Manager", "Employee", "Warehouse Keeper" };
            // show list for user choose
            System.out.printf("=".repeat(160) + "\n");
            System.out.printf("| I.%s %s II.%s %s III.%s |\n", roles[0], "-".repeat(20), roles[1], "-".repeat(20),
                    roles[2]);
            do {
                System.out.print("choose role (like 1, 2,etc...): ");
                String option = input.nextLine().trim();
                userChoose = Validate.parseChooseHandler(option, 3);
            } while (userChoose == -1);

            employeesList[index].setRole(roles[userChoose - 1]);
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
            employeesList[index].showInfo();
            System.out.printf("| %s %s %s |\n", "I.Cancel", "-".repeat(20), "II.Remove");
            do {
                System.out.print("choose option (1 or 2) : ");
                String option = input.nextLine().trim();
                userChoose = Validate.parseChooseHandler(option, 2);
            } while (userChoose == -1);
            if (userChoose == 1)
                return;

            for (int i = index; i < employeesList.length - 1; i++)
                employeesList[i] = employeesList[i + 1];
            employeesList = Arrays.copyOf(employeesList, employeesList.length - 1);
            count--;
        }
    }

    // *execute file resources (TEST DONE)
    // write file
    public void writeFile() throws IOException {
        try (DataOutputStream file = new DataOutputStream(
                new BufferedOutputStream(new FileOutputStream("src/main/resources/Employees", false)))) {
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
        File testFile = new File("src/main/resources/Employees");
        if (testFile.length() == 0)
            return;

        try (DataInputStream file = new DataInputStream(
                new BufferedInputStream(new FileInputStream("src/main/resources/Employees")))) {
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
                list[i] = new Employees(employeeID, firstName, lastName, dateOfBirth, phoneNumber, status, userName,
                        password, role);
            }
            setCount(count);
            setEmployeesList(list);
        } catch (Exception err) {
            System.out.printf("Error reading file!\n%s\n", err.getMessage());
        }
    }
}
