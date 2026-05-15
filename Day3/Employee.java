/**
 * 🛡️ TOPIC: ENCAPSULATION using Employee Salary System
 * 
 * Private = Protect employee details
 * Getter = Read salary safely
 * Setter = Update salary with validation
 */

public class Employee {

    // 1. Private variables (Hidden data)
    private String employeeName;
    private double salary;

    // 2. Constructor (Initialize values)
    public Employee(String employeeName, double salary) {
        this.employeeName = employeeName;
        this.salary = salary;
    }

    // 3. Getter method → Read employee name
    public String getEmployeeName() {
        return employeeName;
    }

    // 4. Getter method → Read salary safely
    public double getSalary() {
        return salary;
    }

    // 5. Setter method → Update salary with validation
    public void setSalary(double newSalary) {
        if (newSalary > 0) {
            this.salary = newSalary;
            System.out.println("Salary updated successfully: Rs." + newSalary);
        } else {
            System.out.println(" Error: Salary cannot be zero or negative!");
        }
    }

    public static void main(String[] args) {

        Employee emp = new Employee("Ashwini", 30000);

        System.out.println("----- Employee Details -----");

        // Accessing data using getters
        System.out.println("Employee Name: " + emp.getEmployeeName());
        System.out.println("Current Salary: Rs." + emp.getSalary());

        // Trying invalid salary update
        emp.setSalary(-5000);

        // Valid salary update
        emp.setSalary(45000);

        System.out.println("Final Salary: Rs." + emp.getSalary());
    }
}