
    /**
 * 📝 SIMPLE ENGLISH EXPLANATION:
 * This program shows how to create an "Incomplete Blueprint" (Abstract Class).
 * Think of it like a sketch of a building that says "This house needs a roof", 
 * but doesn't say what color or material the roof is.
 * 
 * We create a general 'Shape'. We know every shape has an Area, but 
 * we don't know the formula yet because we don't know if it's a circle or a square.
 * We force the 'Circle' and 'Rectangle' classes to finish the blueprint 
 * by writing their own area formulas.
 */

/**
 * 📐 Topic: Abstract Classes (Incomplete Blueprints)
 * 
 * Concept: An 'abstract' class is a class that cannot be used to create objects.
 * It exists ONLY to be a parent. It can have 'abstract' methods which 
 * HAVE NO BODY—children MUST provide the body.
 */

// 1. Abstract parent - Defines WHAT a shape should do, but not HOW.
abstract class Shape {
    String color;  // Abstract classes CAN have regular variables

    // Abstract classes CAN have regular methods with bodies
    public void displayColor() {
        System.out.println("Color: " + color);
    }

    // 💡 THE ABSTRACT METHOD:
    // Every shape has an area, but the formula is different for each.
    // So we mark it 'abstract' and leave it empty (no { } braces).
    public abstract double calculateArea();

    // Another abstract method - forcing child classes to implement drawing logic
    public abstract void draw();
}

// 2. Concrete child: Circle
// Since it extends an abstract class, it MUST implement all abstract methods.
class Circle extends Shape {
    double radius;

    Circle(double radius, String color) {
        this.radius = radius;
        this.color = color;      // Accessing inherited field
    }

    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;  // Specific formula for Circle
    }

    @Override
    public void draw() {
        System.out.println("Drawing a circle with radius: " + radius);
    }
}

// 3. Concrete child: Rectangle
class Rectangle extends Shape {
    double length, width;

    Rectangle(double length, double width, String color) {
        this.length = length;
        this.width = width;
        this.color = color;
    }

    @Override
    public double calculateArea() {
        return length * width;  // Specific formula for Rectangle
    }

    @Override
    public void draw() {
        System.out.println("Drawing a rectangle: " + length + "x" + width);
    }
}

public class ShapeDemo {
    public static void main(String[] args) {
        // ⚠️ TRY THIS: 
        // Shape s = new Shape();  
        // ^ This would cause a COMPILER ERROR: "Shape is abstract; cannot be instantiated"

        // 💡 Use polymorphism to store different shapes in Shape references
        Shape c = new Circle(5.0, "Red");     
        Shape r = new Rectangle(4.0, 6.0, "Blue");

        System.out.println("--- Circle Properties ---");
        c.draw();
        System.out.println("Area: " + String.format("%.2f", c.calculateArea()));
        c.displayColor();

        System.out.println("\n--- Rectangle Properties ---");
        r.draw();
        System.out.println("Area: " + r.calculateArea());
        r.displayColor();
        
        // 🚀 SUMMARY: Abstract classes provide a common structure but force
        // children to fill in the specific details (implementation).
    }
}
    
