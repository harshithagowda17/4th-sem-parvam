public class BadVendingMachine {

    /**
 * 📝 TOPIC: THE PROBLEM (No Encapsulation)
 * 
 * Analogy: A Vending Machine with no glass and no lock.
 * Anyone can reach in, grab the sodas, and take the cash box.
 */
// The blueprint for our unsafe machine

    // 🔴 PUBLIC FIELDS: No protection. Anyone can change these.
    public double totalCashInMachine; // Amount of money inside (e.g. 5000.0)
    public int productStock;          // Number of items left (e.g. 50)

    public static void main(String[] args) { 
        
        // 1. We create the machine
        BadVendingMachine machine = new BadVendingMachine();
        
        // 2. We put some money and stock in it
        machine.totalCashInMachine = 5000.0;
        machine.productStock = 50;

        System.out.println("--- 🟢 INITIAL MACHINE STATE ---");
        System.out.println("Cash: Rs." + machine.totalCashInMachine);
        System.out.println("Stock: " + machine.productStock + " items");

        // ---------------------------------------------------------
        // ⚠️ THE DISASTER:
        // Because the fields are 'public', anyone can "steal" or "break" it.
        // ---------------------------------------------------------

        System.out.println("\n--- 🔴 AN ATTACKER ARRIVES! ---");

        machine.totalCashInMachine = 0.0; // 😱 Someone just took all the money!
        machine.productStock = -99;      // 😱 Someone set the stock to a negative number!

        System.out.println("Cash Left: Rs." + machine.totalCashInMachine);
        System.out.println("Stock Left: " + machine.productStock);
        
        System.out.println("\n💡 LESSON: This business failed because the data was NOT hidden (Private).");
    }
}

