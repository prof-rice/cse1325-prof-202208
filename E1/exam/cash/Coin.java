public class Coin extends Cash {
    public Coin(String name, double value, double diameter) {
        super(name, value);
        this.diameter = diameter;
    }
    @Override
    public String toString() {
        return 100*value + "¢ coin (" + diameter + " diameter)";  
    }
    private double diameter; // in mm
}
