

public class Shape2 {

    // Square -> integer input
    int calculateArea(int side) {
        return side * side;
    }

    // Rectangle -> integer inputs
    int calculateArea(int length, int width) {
        return length * width;
    }

    // Circle -> decimal input
    double calculateArea(double radius) {
        return Math.PI * radius * radius;
    }

    // Triangle -> decimal inputs
    float calculateArea(float base, float height) {
        return 0.5f * base * height;
    }

    public static void main(String[] args) {

        Shape2 shape = new Shape2();

        // Different number of inputs
        System.out.println("Square Area: " + shape.calculateArea(4));
        System.out.println("Rectangle Area: " + shape.calculateArea(4, 6));

        // Different data types
        System.out.println("Circle Area: " + shape.calculateArea(3.5));
        System.out.println("Triangle Area: " + shape.calculateArea(10.0f, 5.0f));
    }

    
}
