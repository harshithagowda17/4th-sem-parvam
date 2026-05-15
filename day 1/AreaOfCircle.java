public class AreaOfCircle {
    // Introduces Math.PI — a built-in constant in Java
// Also shows Math.pow() for exponentiation

    public static void main(String[] args) {

        // Radius of the circle (using double for decimals)
        double radius = 7.0;

        // Math.PI is a constant built into Java = 3.141592653589793
        // We do NOT type pi manually — Java provides it in Math class
        double area = Math.PI * radius * radius;

        // Alternative: Math.pow(base, exponent) raises base to a power
        // Math.pow(7.0, 2) means 7 squared = 49.0
        double areaV2 = Math.PI * Math.pow(radius, 2);

        // Circumference (perimeter) = 2 * pi * r
        double circumference = 2 * Math.PI * radius;

        System.out.println("Radius:        " + radius);
        System.out.println("Area:          " + area);
        System.out.println("Area (v2):     " + areaV2);  // same result!
        System.out.println("Circumference: " + circumference);

        // Trainer tip: Show that area and areaV2 print the same number
        // Teaches students there are multiple valid ways to write the same code

    }
}
    
