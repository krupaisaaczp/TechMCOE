// 1. Employee Management System
import java.util.*;

// Employee class
class Employee {
    private int id;
    private String name;
    private double salary;
    private String department;

    public Employee(int id, String name, double salary, String department) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.department = department;
    }

    // Getters and setters
    public int getId() { return id; }
    public String getName() { return name; }
    public double getSalary() { return salary; }
    public String getDepartment() { return department; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", department='" + department + '\'' +
                '}';
    }
}

// Various comparators for sorting
class NameComparator implements Comparator<Employee> {
    @Override
    public int compare(Employee e1, Employee e2) {
        return e1.getName().compareTo(e2.getName());
    }
}

class SalaryComparator implements Comparator<Employee> {
    @Override
    public int compare(Employee e1, Employee e2) {
        return Double.compare(e1.getSalary(), e2.getSalary());
    }
}

class DepartmentComparator implements Comparator<Employee> {
    @Override
    public int compare(Employee e1, Employee e2) {
        return e1.getDepartment().compareTo(e2.getDepartment());
    }
}

// Main employee management system
public class EmployeeManagementSystem {
    
    // LinkedHashSet to prevent duplicates while maintaining insertion order
    private LinkedHashSet<Employee> employees;
    
    // Map to quickly search employees by ID
    private Map<Integer, Employee> employeeMap;
    
    // Map to group employees by department
    private Map<String, List<Employee>> departmentMap;
    
    public EmployeeManagementSystem() {
        employees = new LinkedHashSet<>();
        employeeMap = new HashMap<>();
        departmentMap = new HashMap<>();
    }
    
    public boolean addEmployee(Employee emp) {
        boolean added = employees.add(emp);
        if (added) {
            employeeMap.put(emp.getId(), emp);
            
            // Update department map
            List<Employee> deptEmployees = departmentMap.getOrDefault(emp.getDepartment(), new ArrayList<>());
            deptEmployees.add(emp);
            departmentMap.put(emp.getDepartment(), deptEmployees);
        }
        return added;
    }
    
    public boolean removeEmployee(int id) {
        Employee emp = employeeMap.get(id);
        if (emp != null) {
            employees.remove(emp);
            employeeMap.remove(id);
            
            // Update department map
            List<Employee> deptEmployees = departmentMap.get(emp.getDepartment());
            if (deptEmployees != null) {
                deptEmployees.remove(emp);
                if (deptEmployees.isEmpty()) {
                    departmentMap.remove(emp.getDepartment());
                }
            }
            return true;
        }
        return false;
    }
    
    public Employee findEmployee(int id) {
        return employeeMap.get(id);
    }
    
    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employees);
    }
    
    public List<Employee> getEmployeesSortedByName() {
        List<Employee> sortedList = new ArrayList<>(employees);
        sortedList.sort(new NameComparator());
        return sortedList;
    }
    
    public List<Employee> getEmployeesSortedBySalary() {
        List<Employee> sortedList = new ArrayList<>(employees);
        sortedList.sort(new SalaryComparator());
        return sortedList;
    }
    
    public List<Employee> getEmployeesSortedByDepartment() {
        List<Employee> sortedList = new ArrayList<>(employees);
        sortedList.sort(new DepartmentComparator());
        return sortedList;
    }
    
    public List<Employee> getEmployeesByDepartment(String department) {
        return departmentMap.getOrDefault(department, new ArrayList<>());
    }
    
    // Main method for demonstration
    public static void main(String[] args) {
        EmployeeManagementSystem ems = new EmployeeManagementSystem();
        
        // Add sample employees
        ems.addEmployee(new Employee(101, "John Doe", 75000, "Engineering"));
        ems.addEmployee(new Employee(102, "Jane Smith", 85000, "HR"));
        ems.addEmployee(new Employee(103, "Michael Brown", 65000, "Engineering"));
        ems.addEmployee(new Employee(104, "Emily Johnson", 95000, "Finance"));
        
        // Try adding a duplicate
        Employee duplicate = new Employee(101, "John Doe Jr.", 80000, "Marketing");
        System.out.println("Added duplicate employee? " + ems.addEmployee(duplicate));
        
        // Print all employees
        System.out.println("\nAll Employees:");
        ems.getAllEmployees().forEach(System.out::println);
        
        // Print employees sorted by name
        System.out.println("\nEmployees sorted by name:");
        ems.getEmployeesSortedByName().forEach(System.out::println);
        
        // Print employees sorted by salary
        System.out.println("\nEmployees sorted by salary:");
        ems.getEmployeesSortedBySalary().forEach(System.out::println);
        
        // Print employees by department
        System.out.println("\nEmployees in Engineering department:");
        ems.getEmployeesByDepartment("Engineering").forEach(System.out::println);
    }
}
