

public class Animal2 {
    // Base class
class Animal {
    void speak() {
        System.out.println("Animal makes a sound");
    }
}

// Parent class
class Cat extends Animal {
    @Override
    void speak() {
        super.speak();  // call Animal's speak()
        System.out.println("Cat meows");
    }
}

// Child class
class Tiger extends Cat {
    @Override
    void speak() {
        super.speak();  // call Cat's speak()
        System.out.println("TIGER ROARS!");
    }
}

// Main class to test
public class Main {
    public static void main(String[] args) {
        Tiger t = new Tiger();
        t.speak();
    }
}
    
}
