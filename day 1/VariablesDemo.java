public class VariablesDemo {
    // A variable is a named box in memory that stores a value
// Syntax: dataType variableName = value


    public static void main(String[] args) {


        // int — whole numbers (no decimal point)
        // Range: -2,147,483,648 to +2,147,483,647
        int age = 20;
        int numberOfStudents = 30;


        // double — decimal numbers (more precise than float)
        double percentage = 85.5;
        double price = 999.99;


        // String — text, always in double quotes, capital S
        // String is a CLASS, not a primitive type
        String name = "Riya";
        String city = "Bangalore";


        // boolean — only two possible values: true or false
        boolean isJavaFun = true;
        boolean isMonday = false;


        // char — single character, in single quotes
        char grade = 'A';


        // Printing with + to join text and variables (concatenation)
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("City: " + city);
        System.out.println("Is Java fun? " + isJavaFun);
        System.out.println("Percentage: " + percentage + "%");
        System.out.println("Grade: " + grade);


        // NAMING RULES (teach these with examples):
        // Valid:   myAge, studentName, totalMarks, firstName
        // Invalid: 1name (starts with digit), my-age (no hyphens)
        // Invalid: class, int, for (cannot use Java keywords)


    }
}
    
