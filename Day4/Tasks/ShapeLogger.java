
    public class ShapeLogger {

    // name first, area second
    void logShape(String name, int area) {
        System.out.println(name + ": " + area);
    }

    // area first, name second
    void logShape(int area, String name) {
        System.out.println(area + ": " + name);
    }

    public static void main(String[] args) {

        ShapeLogger logger = new ShapeLogger();

        // Different parameter order
        logger.logShape("Square", 25);
        logger.logShape(25, "Square");
    }

    
}
