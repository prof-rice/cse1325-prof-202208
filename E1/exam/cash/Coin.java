public class Coin extends Cash {
    public Coin(String name, double value) {
        super(name, value);
        this.diameter = diameter;
    }
    @Override
    public String toString() {
        return 100*value + "¢ coin (" + diameter + " diameter)";  
    }
    double diameter; // in mm
}
