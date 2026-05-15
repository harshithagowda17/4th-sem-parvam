package Day4;

public class CalculatorDemo {
    /**
 * 📝 SIMPLE ENGLISH EXPLANATION:
 * This program shows how one method name 'add' can do different things. 
 * Just like a TV remote has one 'Power' button that turns on different devices, 
 * this class has one 'add' name that can add two numbers, three numbers, 
 * or even join two words together. 
 * 
 * In Java, this is called "METHOD OVERLOADING". 
 * The computer knows which version to use based on what you give it 
 * (like 2 numbers vs 3 numbers).
 */

/**
 * 🧮 Topic: Method Overloading (Compile-time Polymorphism)
 * 
 * Concept: Multiple methods in the SAME class with the SAME name
 * but DIFFERENT parameter lists. The compiler decides which one to call
 * based on the arguments passed.
 */

    // Overload 1: Accepts two integers
    // Use case: Simple integer addition
    public int add(int a, int b) {
        return a + b;
    }

    // Overload 2: Accepts three integers
    // Rule: Different NUMBER of parameters (3 vs 2)
    public int add(int a, int b, int c) {
        return a + b + c;
    }

    // Overload 3: Accepts two doubles
    // Rule: Different TYPE of parameters (double vs int)
    public double add(double a, double b) {
        return a + b;
    }

    // Overload 4: Accepts two Strings
    // Rule: Overloading works with any data type, even objects like String
    public String add(String a, String b) {
        return a + " + " + b + " = combined!";
    }

    /* 
    // ⚠️ THE CLASSIC OVERLOADING TRAP:
    // You CANNOT overload by changing ONLY the return type.
    // The parameters MUST be different.
    
    public double add(int a, int b) { 
        return a + b; 
    }
    // ^ This would cause a COMPILER ERROR because add(int, int) is already defined.
    */

    public static void main(String[] args) {
        // Create an object to access the methods
        CalculatorDemo calc = new CalculatorDemo();

        // 1. Calling the 2-int version
        System.out.println("Sum of 5 and 3: " + calc.add(5, 3));           

        // 2. Calling the 3-int version
        System.out.println("Sum of 2, 3, and 4: " + calc.add(2, 3, 4));    

        // 3. Calling the double version (automatically handles decimals)
        System.out.println("Sum of 1.5 and 2.5: " + calc.add(1.5, 2.5));   

        // 4. Calling the String version
        System.out.println("Combined Strings: " + calc.add("Java", "Rocks")); 
        
        // 🚀 SUMMARY: Method Overloading makes your API more intuitive by using 
        // the same name for similar actions on different data.
    }
}

