
    interface Encryptable {
    void encryptData();

    default void showSecurityLevel() {
        System.out.println("Standard Encryption");
    }
}
