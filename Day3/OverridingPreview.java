/**
 * 🔄 TOPIC: METHOD OVERRIDING (PREVIEW)
 */

// Parent Class
class Shape {

    private String color = "Black";

    public String getColor() {
        return color;
    }

    void draw() {
        System.out.println("Drawing a generic " + color + " shape...");
    }
}

// Child 1
class Circle extends Shape {

    @Override
    void draw() {
        System.out.println("Drawing a beautiful CIRCLE");
    }
}

// Child 2
class Square extends Shape {

    @Override
    void draw() {
        System.out.println("Drawing a perfect SQUARE");
    }
}

// Main Class
public class OverridingPreview {

    public static void main(String[] args) {

        System.out.println("--- SHAPE DRAWING SYSTEM ---");

        Shape s1 = new Circle();
        Shape s2 = new Square();

        s1.draw();
        s2.draw();

        System.out.println("\nLESSON: Overriding allows children to give their own version of a parent's action.");
    }
}