import java.util.ArrayList;
import java.util.List;

// Assuming PaymentMethod is a parent type (or use Object for simplicity)
public class Main {
    public static void main(String[] args) {

        List<Object> paymentMethods = new ArrayList<>();

        paymentMethods.add(new CreditCard("1234-5678-9999"));
        paymentMethods.add(new UserAccount("john_doe"));

        for (Object p : paymentMethods) {

            if (p instanceof Encryptable) {
                Encryptable enc = (Encryptable) p;

                enc.showSecurityLevel();
                enc.encryptData();
            } else {
                System.out.println("Not encryptable: " + p.getClass().getSimpleName());
            }

            System.out.println("------------------");
        }
    }
}
