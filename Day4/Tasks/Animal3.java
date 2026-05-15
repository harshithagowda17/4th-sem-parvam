

public class Animal3 {
    // Base class
class Animal {
    // Method that returns a value
    String checkHealth() {
        return "Normal";
    }
}

// Intermediate class (optional but kept for structure)
class Cat extends Animal {
    @Override
    String checkHealth() {
        return super.checkHealth(); // inherits same behavior unless changed
    }
}

// Child class
class Tiger extends Cat {

    @Override
    String checkHealth() {
        return "Danger: High Heart Rate";
    }
}

// Main class to test
public class Main {
    public static void main(String[] args) {
        Animal a = new Animal();
        Cat c = new Cat();
        Tiger t = new Tiger();

        System.out.println("Animal: " + a.checkHealth());
        System.out.println("Cat: " + c.checkHealth());
        System.out.println("Tiger: " + t.checkHealth());
    }
}
    
}
