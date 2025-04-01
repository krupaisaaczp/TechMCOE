// Base Vehicle class
class Vehicle {
    private String make;
    private String model;
    private int year;
    private String fuelType;
    
    public Vehicle(String make, String model, int year, String fuelType) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.fuelType = fuelType;
    }
    
    public void displayInfo() {
        System.out.println("Vehicle: " + year + " " + make + " " + model);
        System.out.println("Fuel Type: " + fuelType);
    }
    
    // Getters and setters
    public String getMake() { return make; }
    public String getModel() { return model; }
    public int getYear() { return year; }
    public String getFuelType() { return fuelType; }
}

// Car class inherits from Vehicle
class Car extends Vehicle {
    private int numDoors;
    private String bodyType;
    private boolean hasGPS;
    
    public Car(String make, String model, int year, String fuelType, 
               int numDoors, String bodyType, boolean hasGPS) {
        super(make, model, year, fuelType);
        this.numDoors = numDoors;
        this.bodyType = bodyType;
        this.hasGPS = hasGPS;
    }
    
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Number of Doors: " + numDoors);
        System.out.println("Body Type: " + bodyType);
        System.out.println("GPS: " + (hasGPS ? "Yes" : "No"));
    }
    
    public void drive() {
        System.out.println("Driving the " + getYear() + " " + getMake() + " " + getModel());
    }
}

// Truck class inherits from Vehicle
class Truck extends Vehicle {
    private double cargoCapacity;
    private boolean hasTailgate;
    
    public Truck(String make, String model, int year, String fuelType, 
                double cargoCapacity, boolean hasTailgate) {
        super(make, model, year, fuelType);
        this.cargoCapacity = cargoCapacity;
        this.hasTailgate = hasTailgate;
    }
    
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Cargo Capacity: " + cargoCapacity + " tons");
        System.out.println("Tailgate: " + (hasTailgate ? "Yes" : "No"));
    }
    
    public void loadCargo(double weight) {
        if (weight <= cargoCapacity) {
            System.out.println("Loading " + weight + " tons of cargo");
        } else {
            System.out.println("Error: Exceeded maximum cargo capacity of " + cargoCapacity + " tons");
        }
    }
}

public class VehicleInheritanceSystem {
    public static void main(String[] args) {
        Car sedan = new Car("Honda", "Accord", 2023, "Gasoline", 4, "Sedan", true);
        Truck pickup = new Truck("Ford", "F-150", 2023, "Diesel", 2.5, true);
        
        System.out.println("Car Information:");
        sedan.displayInfo();
        sedan.drive();
        
        System.out.println("\nTruck Information:");
        pickup.displayInfo();
        pickup.loadCargo(1.5);
        pickup.loadCargo(3.0);
    }
}
