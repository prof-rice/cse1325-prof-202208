public abstract class Vehicle {
    public Vehicle(int year, String make, String model, BodyStyle bodyStyle) {
        this.year = year;
        this.make = make;
        this.model = model;
        this.bodyStyle = bodyStyle;
    }
/*
    These would be the getters, if we needed any (we don't)

    public String make() {
        return make;
    }
    public String model() {
        return model;
    }
    public int year() {
        return year;
    }
    public BodyStyle bodyStyle() {
        return bodyStyle;
    }
*/
    // What we want to know about the vehicle
    public abstract double range();
    public abstract double fuelConsumed(double miles);
    public abstract double dollarsToTravel(double miles);
    
    // How we'll identify a vehicles
    @Override
    public String toString() {
        return "" + year + " " + make + " " + model + " " + bodyStyle;
    }
    
    private int year;
    private String make;
    private String model;
    private BodyStyle bodyStyle;
}
