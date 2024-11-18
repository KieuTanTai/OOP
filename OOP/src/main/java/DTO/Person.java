package DTO;

import util.Validate;

import java.time.LocalDate;
import java.util.Scanner;

public abstract class Person {
    private String personID;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String phoneNumber;
    protected static final Scanner input = new Scanner(System.in);

    // constructors 
    public Person() {
    }

    public Person(String personID, String firstName, String lastName, LocalDate dateOfBirth, String phoneNumber) {
        this.personID = personIDModifier(personID);
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
    }

    // getters / setters
    public String getPersonID() {
        return this.personID;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }

    public LocalDate getDateOfBirth() {
        return this.dateOfBirth;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    // Setters have params
    public void setPersonID(String personID) {
        this.personID = personIDModifier(personID);
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFullName(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // Setters no params 
    public String setID() {
        String id;
        do {
            System.out.print("set ID : ");
            id = input.nextLine().trim();
            if (!Validate.validateID(id)) {
                System.out.println("invalid id!");
                id = "";
            }
        } while (id.isEmpty());
        return personIDModifier(id);
    }

    public String setFirstName() {
        String name;
        do {
            System.out.print("set first name : ");
            name = input.nextLine().trim();
            if (!Validate.checkHumanName(name)) {
                System.out.println("invalid name!");
                name = "";
            }
        } while (name.isEmpty());
        return name;
    }

    public String setLastName() {
        String name;
        do {
            System.out.print("set last name : ");
            name = input.nextLine().trim();
            if (!Validate.checkHumanName(name)) {
                System.out.println("invalid name!");
                name = "";
            }
        } while (name.isEmpty());
        return name;
    }

    public LocalDate setDateOfBirth() {
        LocalDate date;
        do {
            System.out.print("set date of birth (dd-mm-yyyy) : ");
            String dateInput = input.nextLine().trim();
            date = Validate.isCorrectDate(dateInput);
        } while (date == null);
        return date;
    }

    public String setPhoneNumber() {
        String phone;
        do {
            System.out.print("set phone number: ");
            phone = input.nextLine().trim();
            if (!Validate.validatePhone(phone)) {
                System.out.println("invalid phone number!");
                phone = "";
            }
        } while (phone.isEmpty());
        return phone;
    }

    // Abstract methods
    public abstract void setInfo();
    public abstract void showInfo();
    protected abstract String personIDModifier(String personID);
}