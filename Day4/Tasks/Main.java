// Parent class
class Animal {
    void speak() {
        System.out.println("Animal makes a sound");
    }
}

// Child class
class Cat extends Animal {

    @Override
    void speak() {
        System.out.println("Cat meows: Meow!");
    }
}

// Main class to test
public class Main {
    public static void main(String[] args) {

        Animal genericAnimal = new Animal();
        genericAnimal.speak();   // Generic behavior

        Cat cat = new Cat();
        cat.speak();             // Specialized behavior (overridden)
    }
}