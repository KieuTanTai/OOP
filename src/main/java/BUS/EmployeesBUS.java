package BUS;

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
            if (originalKey instanceof String key) {
                int month, year;
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
        int choice;
        do {
            System.out.println("*".repeat(60));
            System.out.println("I. Strict search");
            System.out.println("II. Relative search");
            System.out.println("0. Exit");
            System.out.println("*".repeat(60));
            System.out.print("Enter your choice : ");
            String inputChoice = input.nextLine().trim();
            // validate if user choose 0
            if (inputChoice.equals("0")) {
                 System.out.println("Exit program.");
                 break;
            }
            choice = Validate.parseChooseHandler(inputChoice, 2);
            switch (choice) {
                case 1:
                    System.out.println("Enter name or id of customer : ");
                    String userInput = input.nextLine().trim();
                    search(userInput);
                    break;
                case 2:
                    caseRelativeSearch();
                    break;
            }
        } while (choice != 0);
    }

    // case handler for relative search
    private void caseRelativeSearch() {
        int choice, monthOrYear;
        String inputDate;
        do {
            System.out.println("*".repeat(60));
            System.out.println("I. Search by First name");
            System.out.println("II. Search by Last name");
            System.out.println("III. Search by Full name");
            System.out.println("IV. Search by Phone");
            System.out.println("V. Search by status");
            System.out.println("VI. Search by role");
            System.out.println("VII. Search by Month (Year)");
            System.out.println("VIII. Search by Date of birth");
            System.out.println("0. Exit");
            System.out.println("*".repeat(60));
            System.out.print("Enter your choice : ");
            String inputChoice = input.nextLine().trim();
            // validate if user choose 0
            if (inputChoice.equals("0")) {
                 System.out.println("Exit program.");
                 break;
            }
            choice = Validate.parseChooseHandler(inputChoice, 8);
            switch (choice) {
                case 1:
                    relativeSearch(getInputFirstName(), "firstName");
                    break;
                case 2:
                    relativeSearch(getInputLastName(), "lastName");
                    break;
                case 3:
                    relativeSearch(getInputFirstName() + " " + getInputLastName(), "fullName");
                    break;
                case 4:
                    String phone;
                    do {
                        System.out.print("Enter phone number: ");
                        phone = input.nextLine().trim();
                        if (!Validate.validatePhone(phone)) {
                            System.out.println("invalid phone number!");
                            phone = "";
                        }
                    } while (phone.isEmpty());
                    relativeSearch(phone, "phone");
                    break;
                case 5:
                    String address;
                    do {
                        System.out.print("Enter address: ");
                        address = input.nextLine().trim();
                        if (address.isEmpty()) {
                            System.out.println("status cannot be empty!");
                        }
                    } while (address.isEmpty());
                    relativeSearch(address, "address");
                    break;
                case 6:
                    int userChoice;
                    String[] roles = { "Manager", "Employee", "Warehouse Keeper" };
                    // show list for user choose
                    System.out.println("=".repeat(160));
                    System.out.printf("| I.%s %s II.%s %s III.%s |\n", roles[0], "-".repeat(20), roles[1], "-".repeat(20), roles[2]);
                    do {
                        System.out.print("choose role (like 1, 2,etc...): ");
                        String option = input.nextLine().trim();
                        userChoice = Validate.parseChooseHandler(option, roles.length);
                        if (userChoice == 0) {
                            System.out.println("Error value!");
                            userChoice = -1;
                        }
                    } while (userChoice == -1);
                    relativeSearch(roles[userChoice - 1], "role");
                    break;
                case 7:
                    int isNumber = 0;
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
                            if (isNumber > 12 || isNumber < 1) {
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
                    relativeSearch(isNumber, monthOrYear == 1 ? "month" : "year");
                    break;
                case 8:
                    LocalDate date;
                    do {
                        System.out.print("Enter date of birth (dd-mm-yyyy) : ");
                        String dateInput = input.nextLine().trim();
                        date = Validate.isCorrectDate(dateInput);
                    } while (date == null);
                    relativeSearch(date, "date");
                    break;
            }
        } while (choice != 0);
    }

    // private methods for get input first / last name
    private String getInputFirstName() {
        String firstName;
        do {
            System.out.print("Enter first name : ");
            firstName = input.nextLine().trim();
            if (!Validate.checkHumanName(firstName)) {
                System.out.println("invalid first name!");
                firstName = "";
            }
        } while (firstName.isEmpty());
        return firstName;
    }

    private String getInputLastName() {
        String lastName;
        do {
            System.out.print("Enter last lastName : ");
            lastName = input.nextLine().trim();
            if (!Validate.checkHumanName(lastName)) {
                System.out.println("invalid last name!");
                lastName = "";
            }
        } while (lastName.isEmpty());
        return lastName;
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
        int choice;
        do {
            System.out.println("*".repeat(60));
            System.out.println("I. Add employee");
            System.out.println("II. Add list of employees");
            System.out.println("0. Exit");
            System.out.println("*".repeat(60));
            System.out.print("Enter your choice : ");
            String inputChoice = input.nextLine().trim();
            // validate if user choose 0
            if (inputChoice.equals("0")) {
                 System.out.println("Exit program.");
                 break;
            }
            choice = Validate.parseChooseHandler(inputChoice, 2);
            // try catch for execute file after add
            try {
                switch (choice) {
                    case 1:
                        Employees newEmployee = new Employees();
                        newEmployee.setInfo();
                        // confirm
                        System.out.printf("| %s %s %s |\n", "I.Cancel", "-".repeat(20), "II.Add");
                        do {
                            System.out.print("choose option (1 or 2) : ");
                            String option = input.nextLine().trim();
                            choice = Validate.parseChooseHandler(option, 2);
                        } while (choice == -1);
                        if (choice == 1)
                            break;
                        add(newEmployee);
                        writeFile();
                        break;
                    case 2:
                        int count = 0;
                        Employees[] list = new Employees[0];
                        do {
                            System.out.print("Enter total employees you wanna add : ");
                            String option = input.nextLine().trim();
                            choice = Validate.isNumber(option);
                        } while (choice == -1);
                        // for loop with input time
                        for (int i = 0; i < choice; i++) {
                            Employees employee = new Employees();
                            employee.setInfo();
                            list = Arrays.copyOf(list, list.length + 1);
                            list[count] = employee;
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
                }
            } catch (Exception e) {
                System.out.printf("error writing file!\nt%s\n", e.getMessage());
            }
        } while (choice != 0);
    }

    @Override
    public void add(Object employee) {
        if (employee instanceof Employees newEmployee) {
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
        int choice;
        do {
            System.out.println("*".repeat(60));
            System.out.println("I. Edit name");
            System.out.println("II. Edit date of birth");
            System.out.println("III. Edit phone");
            System.out.println("IV. Edit status");
            System.out.println("V. Edit role");
            System.out.println("0. Exit");
            System.out.println("*".repeat(60));
            System.out.print("Enter your choice : ");
            String inputChoice = input.nextLine().trim();
            // validate if user choose 0
            if (inputChoice.equals("0")) {
                 System.out.println("Exit program.");
                 break;
            }
            choice = Validate.parseChooseHandler(inputChoice, 5);
            System.out.println("Enter name or id of employee : ");
            String userInput = input.nextLine().trim();

            // if case
            try {
                if (choice == 1)
                    edit(userInput);
                else if (choice == 2)
                    editDateOfBirth(userInput);
                else if (choice == 3)
                    editPhone(userInput);
                else if (choice == 4)
                    editStatus(userInput);
                else if (choice == 5)
                    editRole(userInput);
                else 
                    break;
                // update file
                writeFile();
            } catch (Exception e) {
                System.out.printf("error writing file!\nt%s\n", e.getMessage());
            }
        } while (true);
    }

    @Override
    public void edit(String employeeID) {
        int index = find(employeeID);
        if (index != -1) {
            int userChoice;
            // show list for user choose
            employeesList[index].showInfo();
            System.out.printf("| %s %s %s |\n", "I.Cancel", "-".repeat(20), "II.Edit");
            do {
                System.out.print("choose option (1 or 2) : ");
                String option = input.nextLine().trim();
                userChoice = Validate.parseChooseHandler(option, 2);
            } while (userChoice == -1);
            if (userChoice == 1)
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
            int userChoice;

            employeesList[index].showInfo();
            System.out.printf("| %s %s %s |\n", "I.Cancel", "-".repeat(20), "II.Edit");
            do {
                System.out.print("Choose option (1 or 2): ");
                String option = input.nextLine().trim();
                userChoice = Validate.parseChooseHandler(option, 2);
            } while (userChoice == -1);
            if (userChoice == 1)
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
            int userChoice;

            employeesList[index].showInfo();
            System.out.printf("| %s %s %s |\n", "I.Cancel", "-".repeat(20), "II.Edit");
            do {
                System.out.print("Choose option (1 or 2): ");
                String option = input.nextLine().trim();
                userChoice = Validate.parseChooseHandler(option, 2);
            } while (userChoice == -1);
            if (userChoice == 1)
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
            int userChoice;

            employeesList[index].showInfo();
            System.out.printf("| %s %s %s |\n", "I.Cancel", "-".repeat(20), "II.Edit");
            do {
                System.out.print("Choose option (1 or 2): ");
                String option = input.nextLine().trim();
                userChoice = Validate.parseChooseHandler(option, 2);
            } while (userChoice == -1);

            if (userChoice == 1)
                return;

            System.out.printf("| %s %s %s |\n", "I.Active", "-".repeat(20), "II.Inactive");
            do {
                System.out.print("choose option (1 or 2) : ");
                String option = input.nextLine().trim();
                userChoice = Validate.parseChooseHandler(option, 2);
            } while (userChoice == -1);
            employeesList[index].setStatus(status[userChoice - 1]);
        }
    }

    public void editRole(String employeeID) {
        int index = find(employeeID);
        if (index != -1) {
            int userChoice;

            employeesList[index].showInfo();
            System.out.printf("| %s %s %s |\n", "I.Cancel", "-".repeat(20), "II.Edit");
            do {
                System.out.print("Choose option (1 or 2): ");
                String option = input.nextLine().trim();
                userChoice = Validate.parseChooseHandler(option, 2);
            } while (userChoice == -1);

            if (userChoice == 1)
                return;

            String[] roles = { "Manager", "Employee", "Warehouse Keeper" };
            // show list for user choose
            System.out.println("=".repeat(160));
            System.out.printf("| I.%s %s II.%s %s III.%s |\n", roles[0], "-".repeat(20), roles[1], "-".repeat(20),
                    roles[2]);
            do {
                System.out.print("choose role (like 1, 2,etc...): ");
                String option = input.nextLine().trim();
                userChoice = Validate.parseChooseHandler(option, 3);
            } while (userChoice == -1);

            employeesList[index].setRole(roles[userChoice - 1]);
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
            System.out.print("Enter your choice : ");
            String inputChoice = input.nextLine().trim();
            // validate if user choose 0
            if (inputChoice.equals("0")) {
                 System.out.println("Exit program.");
                 break;
            }
            choice = Validate.parseChooseHandler(inputChoice, 1);
            if (choice == 1) {
                try {
                    System.out.println("Enter name or id of employee : ");
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
    public void remove(String nameOrID) {
        int index = find(nameOrID);
        if (index != -1) {
            int userChoice;
            // show list for user choose
            employeesList[index].showInfo();
            System.out.printf("| %s %s %s |\n", "I.Cancel", "-".repeat(20), "II.Remove");
            do {
                System.out.print("choose option (1 or 2) : ");
                String option = input.nextLine().trim();
                userChoice = Validate.parseChooseHandler(option, 2);
            } while (userChoice == -1);
            if (userChoice == 1)
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
        if (testFile.length() == 0 || !testFile.exists())
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
