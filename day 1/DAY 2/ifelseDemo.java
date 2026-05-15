// FILE: IfElseDemo.java
// TOPIC: Conditional Statements (if / else if / else)
// PURPOSE: Java makes decisions using conditions — just like a traffic signal

public class IfElseDemo {

    // main() is where Java starts execution — always
    public static void main(String[] args) {

        // Declare a variable to represent a traffic light colour
        String signal = "GREEN";

        // if: checks the FIRST condition
        // .equals() is used to compare Strings in Java (NOT ==)
        if (signal.equals("GREEN")) {
            System.out.println("Go! Road is clear.");
        }
        // else if: checked ONLY if the previous if was false
        else if (signal.equals("YELLOW")) {
            System.out.println("Slow down! Signal is changing.");
        }
        // else: runs when ALL above conditions are false
        else {
            System.out.println("STOP! Red signal.");
        }

        System.out.println("--------------------------------");

        // ── MARKS CHECKER EXAMPLE ──────────────────────────
        // Now let's check a student's marks and decide their grade
        int marks = 75;  // Try changing this to 90, 55, 35 and rerun

        if (marks >= 90) {
            System.out.println("Grade: A  — Excellent!");
        } else if (marks >= 75) {
            System.out.println("Grade: B  — Good work!");
        } else if (marks >= 60) {
            System.out.println("Grade: C  — Keep improving.");
        } else {
            System.out.println("Grade: F  — Need to study more.");
        }
    }
}