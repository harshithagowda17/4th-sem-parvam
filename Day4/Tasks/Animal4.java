

public class Animal4 {
// Base class
class Animal {
    void speak() {
        System.out.println("Animal sound");
    }
}

// Dog class
class Dog extends Animal {
    @Override
    void speak() {
        System.out.println("Dog barks");
    }
}

// Cat class
class Cat extends Animal {
    @Override
    void speak() {
        System.out.println("Cat meows");
    }
}

// Tiger class
class Tiger extends Cat {
    @Override
    void speak() {
        super.speak(); // optional: includes Cat behavior first
        System.out.println("TIGER ROARS!");
    }
}

// Main class
public class Main {
    public static void main(String[] args) {

        Animal[] jungle = {
            new Dog(),
            new Cat(),
            new Tiger()
        };

        for (Animal a : jungle) {
            a.speak(); // Runtime polymorphism in action
            System.out.println("-----");
        }
    }
    

