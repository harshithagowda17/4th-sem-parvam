class BasicPhone {

    private String brand;
    private String model;

    public BasicPhone(String brand, String model) {
        this.brand = brand;
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public void makeCall(String number) {
        System.out.println("Calling " + number + " using " + brand + " phone...");
    }

    public void sendMessage(String number, String text) {
        System.out.println("Sending SMS to " + number + ": " + text);
    }
}

public class Main {

    public static void main(String[] args) {

        BasicPhone phone = new BasicPhone("Nokia", "3310");

        phone.makeCall("9876543210");
        phone.sendMessage("9876543210", "Hello!");
    }
}