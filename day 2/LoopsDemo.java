// FILE: LoopsDemo.java
// TOPIC: for loop, while loop, do-while loop

public class LoopsDemo {
    public static void main(String[] args) {

        // ── FOR LOOP ─────────────────────────────────────────
        // Use: when you KNOW how many times to repeat
        // Structure: for (start ; condition ; step) { body }
        System.out.println("--- FOR LOOP: 1 to 5 ---");
        for (int i = 1; i <= 5; i++) {
            // i starts at 1, runs while i <= 5, increases by 1 each time
            System.out.println("i = " + i);
        }

        // ── WHILE LOOP ────────────────────────────────────────
        // Use: when you DON'T know how many times — runs while condition is true
        System.out.println("\n--- WHILE LOOP: Countdown ---");
        int count = 5;
        while (count > 0) {
            // Runs as long as count is greater than 0
            System.out.println("Count: " + count);
            count--;  // count-- is same as count = count - 1
        }
        System.out.println("Blast off!");

        // ── DO-WHILE LOOP ─────────────────────────────────────
        // Use: when the body must run AT LEAST ONCE, then check condition
        // KEY DIFFERENCE: condition is checked AFTER the first run
        System.out.println("\n--- DO-WHILE: Runs at least once ---");
        int x = 10;
        do {
            // This block runs FIRST, then checks condition
            System.out.println("x = " + x);
            x++;
        } while (x < 5);  // condition is false from the start — but it ran once!
    }
}