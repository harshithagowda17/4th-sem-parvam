public class SecureSocialProfile {
    /**
 * 🔐 TOPIC: ENCAPSULATION (DATA HIDING)
 * 
 * Analogy: Think of your bank account. You don't let people reach into the bank vault 
 * and grab money. Instead, you go to a TELLER (Getters/Setters). 
 * The Teller checks your ID (Validation) before giving or taking money.
 */

    // 1️⃣ STEP ONE: HIDE THE DATA
    // We use 'private' instead of 'public'.
    // 'private' means: "Only this class can see these variables. Outsiders are BLOCKED."
    private String username;
    private String password;
    private int followersCount;
    private boolean isPrivate;

    // 🏗️ THE CONSTRUCTOR: This is like a 'Setup' method that runs when you create a profile.
    public SecureSocialProfile(String name, String pass) {
        this.username = name;
        this.password = pass;
        this.followersCount = 0; // New profiles start with 0 followers
        this.isPrivate = true;   // New profiles are private by default
    }

    // 2️⃣ STEP TWO: PROVIDE A WAY TO READ DATA (GETTERS)
    // Since 'username' is private, we need a 'public' method to read it.
    // Analogy: You can't see the price in a locked store, so you ask a clerk "What is the price?"
    public String getUsername() {
        return username; // Sends the username back to the caller
    }

    public int getFollowersCount() {
        return followersCount;
    }

    // 3STEP THREE: PROVIDE A WAY TO CHANGE DATA SAFELY (SETTERS)
    // We use a method to change the password so we can add RULES (Validation).
    public void updatePassword(String oldPass, String newPass) {
        // RULE: You must know the old password to set a new one!
        if (this.password.equals(oldPass)) {
            // RULE: Password must be at least 6 characters long
            if (newPass.length() >= 6) {
                this.password = newPass;
                System.out.println("Success: Password updated.");
            } else {
                System.out.println(" Error: New password is too short (Minimum 6).");
            }
        } else {
            System.out.println(" Error: Wrong old password! You cannot change it.");
        }
    }

    // A safe way to add followers (Outsiders can't set it to -5000 anymore!)
    public void addFollower() {
        this.followersCount++; // Simply adds 1
    }

    // MAIN METHOD: Where we test our secure class
    public static void main(String[] args) {
        
        // 1. Create a secure profile
        SecureSocialProfile myProfile = new SecureSocialProfile("java_pro", "secure123");

        System.out.println("---  SECURE PROFILE SYSTEM ---");

        //  TRYING TO HACK:
        // myProfile.password = "123";  <-- This line will now give a RED ERROR in Java.
        // It won't even let the hacker try! Because 'password' is PRIVATE.

        //  THE RIGHT WAY: Use the official method
        System.out.println("Attempting password update...");
        myProfile.updatePassword("wrong_pass", "hacked!"); // This will fail validation
        myProfile.updatePassword("secure123", "new_strong_pass"); // This will succeed

        myProfile.addFollower(); // Adds 1 follower safely
        
        System.out.println("\nFinal Followers: " + myProfile.getFollowersCount());
        System.out.println("Username: " + myProfile.getUsername());
        
        System.out.println("\n CONCLUSION: Encapsulation makes our code SAFE and BUG-FREE!");
    }
}

