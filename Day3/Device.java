
    /**
 * DEMO: Multilevel Inheritance and the 'super' keyword.
 * GrandParent -> Parent -> Child
 */

// 1. Level 1: Grandparent
class Device {
    private String category = "Electronic";
    
    Device(String brand) {
        System.out.println("Initializing " + brand + " " + category + " device...");
    }

    public String getCategory() { return category; }
}

// 2. Level 2: Parent (inherits from Device)
class Computer extends Device {
    Computer(String brand) {
        super(brand);
        System.out.println("Setting up Computer systems...");
    }
}

// 3. Level 3: Child (inherits from Computer)
class Laptop extends Computer {
    Laptop(String brand) {
        super(brand);
        System.out.println("Laptop is ready for mobile use!");
    }

    public static void main(String[] args) {
        System.out.println("--- Starting Multilevel Inheritance Demo ---");
        Laptop myLaptop = new Laptop("Dell");
        
        // Use getter instead of direct access
        System.out.println("Device Category: " + myLaptop.getCategory());
    }

    
}
