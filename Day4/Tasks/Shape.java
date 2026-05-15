
    

public class Shape {

    // Method for Square
    int calculateArea(int side) {
        return side * side;
    }

    // Method for Rectangle
    int calculateArea(int length, int width) {
        return length * width;
    }

    public static void main(String[] args) {
        Shape s = new Shape();

        // Square area
        System.out.println("Area of Square: " + s.calculateArea(5));

        // Rectangle area
        System.out.println("Area of Rectangle: " + s.calculateArea(4, 6));
    }
}
