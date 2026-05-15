public class TypeCastingDemo {
    // Type Casting = converting a value from one data type to another
// Two kinds: Widening (automatic) and Narrowing (manual)

    public static void main(String[] args) {

        // ---- STRING CONCATENATION ----
        String firstName = "Gagana";
        String lastName  = "gowda";
        String email   = "gagana@gmail.com";

        int currentYear = 2026;
        int birthYear = 2007;  // students: change this to your own!

        // Calculate age — basic arithmetic
        int age = currentYear - birthYear;

        // Calculate in months and days
        int ageInMonths = age * 12;
        int ageInDays   = age * 365; // prints 8, NOT 9!

        
        // + joins Strings — called CONCATENATION
        String fullName = firstName + " " + lastName;
        System.out.println("Full Name: " + fullName);
        System.out.println("Email: " + email);
        System.out.println("Birth Year:   " + birthYear);
        System.out.println("Current Year: " + currentYear);
        System.out.println("Your Age:     " + age + " years");
        System.out.println("In Months:    " + ageInMonths);
        System.out.println("In Days:      " + ageInDays + " (approx)");
        // ---- WIDENING CAST (Automatic) ----
        // int has less precision than double
        // Java automatically "widens" int into double — no cast needed
        int marks = 85;
        double marksDouble = marks; // automatic, no error
        System.out.println("Marks as double: " + marksDouble); // prints 85.0

        // ---- NARROWING CAST (Manual — Programmer must ask for it) ----
        // double has more precision than int
        // We MUST explicitly cast using (int) — we take responsibility
        double cgpa = 8.75;
        int cgpaInt = (int) cgpa; // decimal part is CUT OFF, NOT rounded
        System.out.println("CGPA as int: " + cgpaInt);
         // prints 8, NOT 9!

        // TRAINER TIP: Ask students "what will print?" before running.
        // Most will say 9. When 8 appears — that's the aha moment!
        // Teach: (int) TRUNCATES (chops off), it does NOT round.

    }
}

