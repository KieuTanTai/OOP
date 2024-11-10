public class employees extends person {
    // Thuộc tính (Fields) riêng của Employee
    private String status;
    private String username;
    private String password;
    private String role;

    // Constructor
    public employees(String id, String firstName, String lastName, LocalDate birthday, String phone,
                    String status, String username, String password, String role) {
        super(id, firstName, lastName, birthday, phone); // Gọi constructor của lớp cha (Person)
        this.status = status;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // Getter và Setter cho status
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Getter và Setter cho username
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // Getter và Setter cho password
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Getter và Setter cho role
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    // Triển khai phương thức trừu tượng từ lớp cha
    @Override
    public void displayInfo() {
        System.out.println("Employee ID: " + getId());
        System.out.println("Name: " + getFirstName() + " " + getLastName());
        System.out.println("Birthday: " + getBirthday());
        System.out.println("Phone: " + getPhone());
        System.out.println("Status: " + status);
        System.out.println("Username: " + username);
        System.out.println("Role: " + role);
    }
}
