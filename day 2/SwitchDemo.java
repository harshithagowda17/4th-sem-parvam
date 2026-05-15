// FILE: SwitchDemo.java
// TOPIC: switch-case statement
// PURPOSE: cleaner than many if-else when you test ONE value against many options

public class SwitchDemo {
    public static void main(String[] args) {

        int day = 3;  // 1=Monday, 2=Tuesday ... 7=Sunday

        // switch checks the value of 'day' and jumps to the matching case
        switch (day) {
            case 1:
                System.out.println("Monday — Week starts!");
                // break exits the switch — without it, it falls through to next case
                break;
            case 2:
                System.out.println("Tuesday");
                break;
            case 3:
                System.out.println("Wednesday — Middle of the week!");
                break;
            case 4:
                System.out.println("Thursday");
                break;
            case 5:
                System.out.println("Friday — Almost weekend!");
                break;
            case 6:
            case 7:
                // Two cases sharing one block = Saturday OR Sunday
                System.out.println("Weekend! No coding... just kidding.");
                break;
            // default runs if no case matches — like 'else' in if-else
            default:
                System.out.println("Invalid day number.");
        }
    }
}