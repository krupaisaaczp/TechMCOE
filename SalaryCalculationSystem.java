// Abstract Employee class to enforce inheritance
abstract class Employee {
    private final String name;
    private final String id;
    private final double baseSalary;

    public Employee(String name, String id, double baseSalary) {
        this.name = name;
        this.id = id;
        this.baseSalary = baseSalary;
    }

    // Abstract method for salary calculation
    public abstract double calculateSalary();

    // Getters
    public String getName() { return name; }
    public String getId() { return id; }
    public double getBaseSalary() { return baseSalary; }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " [Name: " + name + ", ID: " + id + ", Salary: $" + calculateSalary() + "]";
    }
}

// Manager class with bonus
class Manager extends Employee {
    private final double bonus;

    public Manager(String name, String id, double baseSalary, double bonus) {
        super(name, id, baseSalary);
        this.bonus = bonus;
    }

    @Override
    public double calculateSalary() {
        return getBaseSalary() + bonus;
    }

    public double getBonus() { return bonus; }

    @Override
    public String toString() {
        return super.toString() + " [Bonus: $" + bonus + "]";
    }
}

// Developer class with overtime pay
class Developer extends Employee {
    private final int overtimeHours;
    private final double hourlyRate;

    public Developer(String name, String id, double baseSalary, int overtimeHours, double hourlyRate) {
        super(name, id, baseSalary);
        this.overtimeHours = overtimeHours;
        this.hourlyRate = hourlyRate;
    }

    @Override
    public double calculateSalary() {
        return getBaseSalary() + (overtimeHours * hourlyRate);
    }

    public int getOvertimeHours() { return overtimeHours; }
    public double getHourlyRate() { return hourlyRate; }

    @Override
    public String toString() {
        return super.toString() + " [Overtime Hours: " + overtimeHours + ", Hourly Rate: $" + hourlyRate + "]";
    }
}

// Sales Employee class with commission
class SalesEmployee extends Employee {
    private final double salesAmount;
    private final double commissionRate;

    public SalesEmployee(String name, String id, double baseSalary, double salesAmount, double commissionRate) {
        super(name, id, baseSalary);
        this.salesAmount = salesAmount;
        this.commissionRate = commissionRate;
    }

    @Override
    public double calculateSalary() {
        return getBaseSalary() + (salesAmount * commissionRate);
    }

    public double getSalesAmount() { return salesAmount; }
    public double getCommissionRate() { return commissionRate; }

    @Override
    public String toString() {
        return super.toString() + " [Sales: $" + salesAmount + ", Commission Rate: " + (commissionRate * 100) + "%]";
    }
}

// Main class to run the program
public class SalaryCalculationSystem {
    public static void main(String[] args) {
        Employee manager = new Manager("Jane Smith", "M001", 70000, 10000);
        Employee developer = new Developer("Bob Johnson", "D001", 60000, 20, 50);
        Employee salesPerson = new SalesEmployee("Alice Brown", "S001", 40000, 100000, 0.05);

        // Array of employees to demonstrate polymorphism
        Employee[] employees = { manager, developer, salesPerson };

        System.out.println("=== Employee Salaries ===");
        for (Employee emp : employees) {
            System.out.println(emp);
        }

        // Calculate total payroll
        double totalPayroll = 0;
        for (Employee emp : employees) {
            totalPayroll += emp.calculateSalary();
        }

        System.out.println("\nTotal payroll cost: $" + totalPayroll);
    }
}
