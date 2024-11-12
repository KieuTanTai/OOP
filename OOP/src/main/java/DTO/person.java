package DTO;

import java.time.LocalDate;
public abstract class person {

    private String id;
    private String firstName;
    private String lastName;
    private LocalDate birthday;
    private String phone;


    public person(String id, String firstName, String lastName, LocalDate birthday, String phone) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.phone = phone;
    }

    // Getter và Setter cho id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    // Getter và Setter cho firstName
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    // Getter và Setter cho lastName
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    // Getter và Setter cho birthday
    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    // Getter và Setter cho phone
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    // Phương thức trừu tượng (abstract method), có thể triển khai ở các lớp con
    public abstract void displayInfo();
}
