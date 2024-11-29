package DTO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.mindrot.jbcrypt.BCrypt;

import util.Validate;

public class Employees extends Person {
    private String status;
    private String username;
    private String password;
    private String role;

    // constructors
    public Employees() {
    }

    public Employees(String personID, String firstName, String lastName, LocalDate birthday, String phone,
            String status, String username, String password, String role) {
        super(personID, firstName, lastName, birthday, phone);
        this.status = status;
        this.username = username;
        this.password = hashPassword(password);
        this.role = role;
    }

    // getters / setters
    public String getStatus() {
        return status;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    // setter have params
    public void setStatus(String status) {
        this.status = status;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = hashPassword(password);
    }

    public void setRole(String role) {
        this.role = role;
    }

    // setter no params
    public String setStatus() {
        String[] status = { "Active", "Inactive" };
        int userChoice;
        System.out.printf("| %s %s %s |\n", "I.Active", "-".repeat(20), "II.Inactive");
        do {
            System.out.print("choose option (1 or 2) : ");
            String option = input.nextLine().trim();
            userChoice = Validate.parseChooseHandler(option, 2);
        } while (userChoice == -1);
        return status[userChoice - 1];
    }

    public String setRole() {
        int userChoice;
        String[] roles = { "Manager", "Employee", "Warehouse Keeper" };
        // show list for user choose
        System.out.println("=".repeat(88));
        System.out.printf("I.%s \nII.%s \nIII.%s\n", roles[0], roles[1], roles[2]);
        do {
            System.out.print("choose role (like 1, 2,etc...): ");
            String option = input.nextLine().trim();
            userChoice = Validate.parseChooseHandler(option, roles.length);
        } while (userChoice == -1);

        return roles[userChoice - 1];
    }

    // other methods
    @Override
    public void setInfo() {
        System.out.println("*".repeat(60));
        String id = setID(this);

        System.out.println("-".repeat(60));
        String firstName = setFirstName();

        System.out.println("-".repeat(60));
        String lastName = setLastName();

        System.out.println("-".repeat(60));
        LocalDate dateOfBirth = setDateOfBirth();

        System.out.println("-".repeat(60));
        String phone = setPhoneNumber();

        System.out.println("-".repeat(60));
        String status = setStatus();

        System.out.println("-".repeat(60));
        String role = setRole();

        // let userName is id of this employees
        String userName = id;
        // auto set password is fullName + dateOfBirth
        String password = id + role.replace(" ", "") + dateOfBirth.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")).replace("-", "@");

        int userChoice;
        System.out.println("*".repeat(60));
        System.out.printf("| %s %s %s |\n", "I.Cancel", "-".repeat(20), "II.Submit");
        do {
            System.out.print("choose option (1 or 2) : ");
            String option = input.nextLine().trim();
            userChoice = Validate.parseChooseHandler(option, 2);
        } while (userChoice == -1);
        System.out.println("*".repeat(60));
        if (userChoice == 1)
            System.out.println("ok!");
        else {
            setPersonID(id);
            setFullName(firstName, lastName);
            setDateOfBirth(dateOfBirth);
            setPhoneNumber(phone);
            setStatus(status);
            setUsername(userName);
            setPassword(password);
            setRole(role);
        }
    }

    @Override
    public void showInfo() {
        LocalDate dateOfBirth = getDateOfBirth();
        String employeeID = getPersonID(), employeeName = getFullName(), phone = getPhoneNumber();

        System.out.println("=".repeat(140));
        System.out.printf("| %-22s : %s \n", "ID", employeeID != null ? employeeID : "N/A");
        System.out.printf("| %-22s : %s \n", "Username", this.username != null ? this.username : "N/A");
        System.out.printf("| %-22s : %s \n", "Full Name", employeeName != null ? employeeName : "N/A");
        System.out.printf("| %-22s : %s \n", "Birthday", dateOfBirth != null ? dateOfBirth.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) : "N/A");
        System.out.printf("| %-22s : %s \n", "Phone", phone != null ? phone : "N/A");
        System.out.printf("| %-22s : %s \n", "Status", this.status != null ? this.status : "N/A");
        System.out.printf("| %-22s : %s \n", "Role", this.role != null ? this.role : "N/A");
        System.out.println("=".repeat(140));
    }

    @Override
    protected String personIDModifier(String employeeID) {
        if (employeeID.startsWith("EMP") && employeeID.endsWith("PS") && employeeID.length() == 13)
            return employeeID;
        if (!Validate.validateID(employeeID)) {
            System.out.println("error id!");
            return "N/A";
        }
        return "EMP" + employeeID + "PS";
    }

    // hash password / check password
    private String hashPassword(String password) {
        // check if password has been hashed or not
        if (isHashed(password))
            return password;
        return BCrypt.hashpw(password, BCrypt.gensalt(8));
    }

    private boolean isHashed(String password) {
        return password != null && password.length() == 60 && (password.startsWith("$2a$") || password.startsWith("$2b$") || password.startsWith("$2y$"));
    }
    

    @SuppressWarnings("unused")
    private boolean checkPassword(String password) {
        return BCrypt.checkpw(password, this.password);
    }
}
