public class Note extends Cash {
    public Note(String name, double value, double height, double width) {
        super(name, value);
    }
    @Override
    public String toString() {
        return "$" + value + " bill (" + height + "x" + width + " mm)";  
    }
    private double height;
    private double width;
}
