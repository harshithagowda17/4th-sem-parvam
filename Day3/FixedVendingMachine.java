package Day3;

public class FixedVendingMachine {
    /**
 * 🛡️ TOPIC: THE SOLUTION (Encapsulation)
 * 
 * This is the FIXED version of our Vending Machine.
 * We locked the machine (Private) and added a Teller (Getters/Setters).
 */



    // 1. We lock the data by making it PRIVATE
    private double totalCashInMachine;
    private int productStock;

    // 2. We use a CONSTRUCTOR to set initial values
    public FixedVendingMachine(double initialCash, int initialStock) {
        this.totalCashInMachine = initialCash;
        this.productStock = initialStock;
    }

    // 3. GETTER: A safe way to READ the cash (We don't want a 'setter' for cash!)
    public double getTotalCashInMachine() {
        return totalCashInMachine;
    }

    // 4. GETTER: A safe way to READ the stock
    public int getProductStock() {
        return productStock;
    }

    // 5. SETTER: A safe way to CHANGE the stock with a RULE (Validation)
    public void setProductStock(int newStock) {
        if (newStock >= 0) {
            this.productStock = newStock;
            System.out.println("✅ Stock updated to: " + newStock);
        } else {
            System.out.println("❌ ERROR: Stock cannot be negative!");
        }
    }

    public static void main(String[] args) {
        FixedVendingMachine machine = new FixedVendingMachine(5000.0, 50);

        System.out.println("--- 🟢 SECURE MACHINE ---");
        
        // ❌ HACK ATTEMPT:
        // machine.totalCashInMachine = 0.0; // This line would now cause an ERROR!
        
        // ✅ SECURE ACCESS:
        System.out.println("Current Cash: Rs." + machine.getTotalCashInMachine());
        
        // Trying to break the machine:
        machine.setProductStock(-10); // The rule will catch this!
        machine.setProductStock(100); // This works!

        System.out.println("Final Stock: " + machine.getProductStock());
    }

}
