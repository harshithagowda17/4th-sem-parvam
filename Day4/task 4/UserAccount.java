
    
class UserAccount implements Encryptable {
    private String username;

    public UserAccount(String username) {
        this.username = username;
    }

    @Override
    public void encryptData() {
        System.out.println("Encrypting User Account: " + username);
    }
}
default void showSecurityLevel() {
    System.out.println("Standard Encryption");
}
