package BUS;
import DTO.*;
public class EmployeesBUS {
    private Employees[] employeesArray;
    private int count;

    // Trình xây dựng có kích thước mảng cố định
    public EmployeesBUS(int size) {
        employeesArray = new Employees[size];
        count = 0;
    }

    // Phương pháp thêm nhân viên
    public boolean addEmployee(Employees employee) {
        if (count < employeesArray.length) {
            employeesArray[count] = employee;
            count++;
            return true;
        }
        return false; // Mảng đã đầy
    }

    // Phương pháp truy xuất nhân viên theo tên người dùng
    public Employees getEmployee(String username) {
        for (int i = 0; i < count; i++) {
            if (employeesArray[i].getUsername().equals(username)) {
                return employeesArray[i];
            }
        }
        return null; //  Nếu không tìm thấy
    }

    // Method to remove an employee by username
    public boolean removeEmployee(String username) {
        for (int i = 0; i < count; i++) {
            if (employeesArray[i].getUsername().equals(username)) {
                // Dịch chuyển các phần tử mảng để loại bỏ nhân viên
                for (int j = i; j < count - 1; j++) {
                    employeesArray[j] = employeesArray[j + 1];
                }
                employeesArray[count - 1] = null; // Xóa phần tử cuối cùng
                count--;
                return true;
            }
        }
        return false; //     Không tìm thấy nhân viên
    }

    // Phương pháp lấy tất cả nhân viên dưới dạng mảng
    public Employees[] getAllEmployees() {
        Employees[] currentEmployees = new Employees[count];
        System.arraycopy(employeesArray, 0, currentEmployees, 0, count);
        return currentEmployees;
    }

    //Phương pháp lấy tổng số nhân viên
    public int getCount() {
        return count;
    }
}

    

