/**
 * MASTER DEMO: Encapsulation in Banking System
 */
public class BankAccountSafe {

    // Private fields (Encapsulation)
    private String accountNumber;
    private String holderName;
    protected double balance;

    // Constructor
    public BankAccountSafe(String accountNumber, String holderName, double initialBalance) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;

        if (initialBalance >= 500) {
            this.balance = initialBalance;
        } else {
            System.out.println("Minimum balance is 500. Setting balance to 0.");
            this.balance = 0;
        }
    }

    // Getters
    public String getAccountNumber() {
        return accountNumber;
    }

    public String getHolderName() {
        return holderName;
    }

    public double getBalance() {
        return balance;
    }

    // Deposit method
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: Rs." + amount + " | Balance: Rs." + balance);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    // Withdraw method
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn: Rs." + amount + " | Balance: Rs." + balance);
        } else {
            System.out.println("Invalid or insufficient balance.");
        }
    }

    // Main method
    public static void main(String[] args) {

        // Create object
        BankAccountSafe acc1 = new BankAccountSafe("ACC101", "Rahul", 1000);

        // Display details
        System.out.println("Account No: " + acc1.getAccountNumber());
        System.out.println("Holder Name: " + acc1.getHolderName());
        System.out.println("Balance: Rs." + acc1.getBalance());

        // Transactions
        acc1.deposit(500);
        acc1.withdraw(300);
        acc1.withdraw(2000);
    }
}