// FILE: BreakContinueDemo.java
// TOPIC: break and continue keywords

public class BreakContinueDemo {
    public static void main(String[] args) {

        // ── BREAK: exits the entire loop immediately ─────────
        System.out.println("--- BREAK Example ---");
        for (int i = 1; i <= 10; i++) {
            if (i == 6) {
                break;  // Stop the loop completely when i reaches 6
            }
            System.out.println("i = " + i);  // Prints 1, 2, 3, 4, 5 only
        }

        // ── CONTINUE: skips the current iteration, continues loop ─
        System.out.println("\n--- CONTINUE Example: Skip even numbers ---");
        for (int i = 1; i <= 10; i++) {
            if (i % 2 == 0) {
                continue;  // Skip this iteration — go to next i
            }
            System.out.println("Odd: " + i);  // Prints 1, 3, 5, 7, 9
        }

        // ── NESTED LOOP + BREAK Example ───────────────────────
        // Pattern: print a right triangle of stars
        System.out.println("\n--- Nested Loop Pattern ---");
        for (int row = 1; row <= 5; row++) {
            for (int col = 1; col <= row; col++) {
                // Inner loop runs 'row' times per outer row
                System.out.print("* ");
            }
            System.out.println();  // Move to next line after each row
        }
    }
}