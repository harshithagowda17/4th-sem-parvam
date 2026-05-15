

public class Shape5 {
    // Abstract class
abstract class Shape {
    abstract void draw();
}

public class Main {
    public static void main(String[] args) {

        // ❌ This line is NOT allowed and will cause a compile-time error:
        // Shape s = new Shape();

        // ✅ Correct way: use a subclass
        Shape s = new Circle();
        s.draw();
    }
}

// Concrete subclass
class Circle extends Shape {
    void draw() {
        System.out.println("Drawing a circle");
    }
}
    
}
