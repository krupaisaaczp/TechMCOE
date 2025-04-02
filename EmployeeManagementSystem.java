import java.util.*;

class Employee {
    int id;
    String name;
    double salary;
    String department;

    public Employee(int id, String name, double salary, String department) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.department = department;
    }

    @Override
    public String toString() {
        return "[ID: " + id + ", Name: " + name + ", Salary: " + salary + ", Department: " + department + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Employee employee = (Employee) obj;
        return id == employee.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

class SalaryComparator implements Comparator<Employee> {
    public int compare(Employee e1, Employee e2) {
        return Double.compare(e1.salary, e2.salary);
    }
}

class NameComparator implements Comparator<Employee> {
    public int compare(Employee e1, Employee e2) {
        return e1.name.compareTo(e2.name);
    }
}

public class EmployeeManagementSystem {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();
        Set<Employee> employeeSet = new HashSet<>();
        Map<String, List<Employee>> departmentMap = new HashMap<>();

        employees.add(new Employee(1, "Alice", 50000, "HR"));
        employees.add(new Employee(2, "Bob", 45000, "IT"));
        employees.add(new Employee(3, "Charlie", 60000, "Finance"));

        employeeSet.addAll(employees);
        departmentMap.put("HR", new ArrayList<>(List.of(new Employee(1, "Alice", 50000, "HR"))));

        // Sort by salary
        employees.sort(new SalaryComparator());
        System.out.println("Sorted by salary: " + employees);

        // Sort by name
        employees.sort(new NameComparator());
        System.out.println("Sorted by name: " + employees);

        // Search by ID
        int searchId = 2;
        for (Employee emp : employees) {
            if (emp.id == searchId) {
                System.out.println("Found: " + emp);
                break;
            }
        }
    }
}
