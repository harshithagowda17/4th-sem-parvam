public class CollegeDetails {
    
    // A variable is a named box in memory that stores a value
// Syntax: dataType variableName = value


    public static void main(String[] args) {


        // int — whole numbers (no decimal point)
        // Range: -2,147,483,648 to +2,147,483,647
        String collegename= "city engineering college";
        int phoneno= 123456789;
        int year=2000;


        // double — decimal numbers (more precise than float)
        double fees = 15000.00;


        // String — text, always in double quotes, capital S
        // String is a CLASS, not a primitive type
        String name = "Riya";
        String dept = "AIML";
        String city = "Bangalore";


        // boolean — only two possible values: true or false
    


        // char — single character, in single quotes
        char grade = 'A';


        // Printing with + to join text and variables (concatenation)
        System.out.println("Collegename: " + collegename);
        System.out.println("Phoneno: " + phoneno);
        System.out.println("Year: " + year);
        System.out.println("Grade: " + grade);
        System.out.println("Fees:"+fees);
        System.out.println("Name:"+name);
        System.out.println("Dept:"+dept);
        System.out.println("City:"+city);
        System.out.println("thank you!");


        // NAMING RULES (teach these with examples):
        // Valid:   myAge, studentName, totalMarks, firstName
        // Invalid: 1name (starts with digit), my-age (no hyphens)
        // Invalid: class, int, for (cannot use Java keywords)


    }
}
    
