
    
class CreditCard implements Encryptable {
    private String cardNumber;

    public CreditCard(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public void encryptData() {
        System.out.println("Encrypting Credit Card: " + cardNumber);
    }
}
