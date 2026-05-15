
    /**
 * 📝 SIMPLE ENGLISH EXPLANATION:
 * This program shows how a child class (like Car, Boat, or Plane) 
 * can change the behavior it gets from a parent class (like Vehicle).
 * 
 * All vehicles can "move", but each vehicle moves in its own way.
 * A Car moves on roads 🚘
 * A Boat moves on water 🚤
 * A Plane moves in the sky ✈️
 * 
 * In Java, when a child class gives its own version 
 * of a parent class method, it is called:
 * 👉 METHOD OVERRIDING
 * 
 * Also, we can treat all of them as a general "Vehicle",
 * but Java will automatically call the correct move() method
 * based on the actual object type.
 * 
 * This is called:
 * 👉 RUNTIME POLYMORPHISM
 */

/**
 * 🚗 Topic: Method Overriding (Runtime Polymorphism)
 * 
 * Concept: A child class provides its own specific implementation
 * of a method that is already defined in the parent class.
 */

// 1. Parent class (Base Class)
class Animal {
    // Parent version of the method
    public void speak() {
        System.out.println("Some animal makes a sound");
    }

    // Method that will be inherited but NOT overridden in this demo
    public void breathe() {
        System.out.println("Breathing with lungs");
    }
}

// 2. Child class (Sub Class)
class Dog extends Animal {
    // 💡 THE @Override ANNOTATION:
    // This tells the compiler: "I am intentionally replacing the parent's method."
    // If you misspell the name, the compiler will warn you!
    @Override                        
    public void speak() {
        System.out.println("Dog barks: Woof!");
    }
}

// 3. Grandchild class (Multilevel Inheritance)
class Labrador extends Dog {
    @Override
    public void speak() {
        // 💡 super.speak() calls the version in the IMMEDIATE parent (Dog)
        super.speak();               
        System.out.println("Labrador barks louder: WOOF WOOF!");
    }
}

public class AnimalDemo {
    public static void main(String[] args) {
        System.out.println("--- Case 1: Simple Object ---");
        Animal a1 = new Animal();    
        a1.speak();   // Logic: Parent method runs

        System.out.println("\n--- Case 2: UPCASTING (Important!) ---");
        // 💡 Polymorphism: Parent reference (Animal) but Child object (Dog)
        // This is like saying: "This is an Animal, but specifically it's a Dog."
        Animal a2 = new Dog();       
        
        // 🚀 RUNTIME POLYMORPHISM: 
        // Even though the reference is 'Animal', at RUNTIME the JVM sees 
        // the object is a 'Dog' and calls Dog's version of speak().
        a2.speak();   
        
        // Inherited methods still work fine
        a2.breathe(); 

        System.out.println("\n--- Case 3: Grandchild ---");
        Animal a3 = new Labrador();  
        a3.speak();   // Calls Dog's speak (via super) + Labrador's own logic
        
        // 🚀 SUMMARY: Method Overriding allows children to redefine behavior 
        // while maintaining the same "interface" (method name) as the parent.
    }
}

