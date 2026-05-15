

public class Main1 {
    // Abstract class (contract)
abstract class Shape {
    abstract double calculateArea();
    abstract void draw();
}

// Concrete class implementing Shape
class Triangle extends Shape {
    private double base;
    private double height;

    public Triangle(double base, double height) {
        this.base = base;
        this.height = height;
    }

    @Override
    double calculateArea() {
        return 0.5 * base * height;
    }

    @Override
    void draw() {
        System.out.println("Drawing a Triangle");
    }
}

// Main class to test
public class Main {
    public static void main(String[] args) {
        Shape t = new Triangle(10, 5);

        t.draw();
        System.out.println("Area: " + t.calculateArea());
    }
}
    
}
