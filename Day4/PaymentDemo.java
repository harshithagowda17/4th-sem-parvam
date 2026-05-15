
    /**
 * 📝 SIMPLE ENGLISH EXPLANATION:
 * This program shows how to create a "Contract" (Interface).
 * Imagine a legal contract that says "Any company that wants to be a 
 * Payment Provider MUST provide a way to 'pay' and 'print a receipt'."
 * 
 * We create an interface 'Payment'. Then 'CreditCard' and 'UPI' 
 * classes "sign" this contract by implementing it.
 * This way, our main system can handle any payment method as long as 
 * it follows the contract rules.
 */

/**
 * 💳 Topic: Interfaces (The Pure Contract)
 * 
 * Concept: An Interface is a collection of abstract methods. It is a 
 * CONTRACT that a class signs. If a class implements an interface,
 * it MUST provide implementations for all its methods.
 */

// 1. The Interface - Defines a standard for ANY payment method
interface Payment {
    // 💡 Rule: All methods in an interface are 'public abstract' by default.
    boolean processPayment(double amount);  
    void printReceipt(double amount);
    String getPaymentType();

    // 💡 Java 8+ Feature: Default Methods
    // These allow interfaces to have methods with a default BODY.
    default void logTransaction(double amount) {
        System.out.println("[AUDIT] Transaction of Rs." + amount + " via " + getPaymentType() + " logged.");
    }
}

// 2. Implementation: CreditCard
// A class signs the contract using the 'implements' keyword.
class CreditCard implements Payment {
    private String cardNumber;
    private double creditLimit;
    private double balance;

    CreditCard(String cardNumber, double creditLimit) {
        this.cardNumber = cardNumber;
        this.creditLimit = creditLimit;
        this.balance = 0;
    }

    // Must implement ALL methods from the interface
    @Override
    public boolean processPayment(double amount) {
        if (balance + amount <= creditLimit) {
            balance += amount;
            System.out.println("Credit card charged: Rs." + amount);
            return true;
        }
        System.out.println("Transaction Failed: Credit limit reached!");
        return false;
    }

    @Override
    public void printReceipt(double amount) {
        System.out.println("--- CREDIT CARD RECEIPT ---");
        System.out.println("Card: **** **** **** " + cardNumber.substring(cardNumber.length() - 4));
        System.out.println("Amount: Rs." + amount);
    }

    @Override
    public String getPaymentType() {
        return "Credit Card";
    }
}

// 3. Implementation: UPI
class UPI implements Payment {
    private String upiId;
    private double walletBalance;

    UPI(String upiId, double walletBalance) {
        this.upiId = upiId;
        this.walletBalance = walletBalance;
    }

    @Override
    public boolean processPayment(double amount) {
        if (walletBalance >= amount) {
            walletBalance -= amount;
            System.out.println("UPI payment successful from " + upiId + ": Rs." + amount);
            return true;
        }
        System.out.println("Transaction Failed: Insufficient UPI balance!");
        return false;
    }

    @Override
    public void printReceipt(double amount) {
        System.out.println("--- UPI RECEIPT ---");
        System.out.println("UPI ID: " + upiId);
        System.out.println("Amount: Rs." + amount);
    }

    @Override
    public String getPaymentType() {
        return "UPI (PhonePe/GPay)";
    }
}

public class PaymentDemo {
    // 💡 THE POWER OF INTERFACES:
    // This method doesn't care if it's UPI or CreditCard. 
    // It only cares that it 'is-a' Payment.
    public static void processAnyPayment(Payment payment, double amount) {
        System.out.println("\n--- Initiating " + payment.getPaymentType() + " ---");
        
        // 🚀 Polymorphism in action: calling interface methods
        if (payment.processPayment(amount)) {
            payment.printReceipt(amount);
            payment.logTransaction(amount); // calling the default method
        }
    }

    public static void main(String[] args) {
        // We can use the interface as a TYPE
        Payment card = new CreditCard("1234567890123456", 50000);
        Payment upi = new UPI("parvam@upi", 5000);

        // One method, multiple behaviors
        processAnyPayment(card, 2500);
        processAnyPayment(upi, 1200);
        processAnyPayment(upi, 6000); // Should fail
        
        // 🚀 SUMMARY: Interfaces allow different classes to work together
        // if they follow the same rules (contract).
    }
}

