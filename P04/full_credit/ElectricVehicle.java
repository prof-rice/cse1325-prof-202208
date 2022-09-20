public class ElectricVehicle extends Vehicle {
    // Energy cost - must be set prior to using the class!
    public static double centsPerKwhOfElectricity = Double.NaN;

    public ElectricVehicle(int year, String make, String model, BodyStyle bodyStyle,
                double whPerMile, double kwhInBattery) {
        super(year, make, model, bodyStyle);
        this.whPerMile = whPerMile;
        this.kwhInBattery = kwhInBattery;           
    }
    @Override
    public double range() {
        return kwhInBattery / (whPerMile / 1000);
    }
    @Override
    public double fuelConsumed(double miles) {
        double kWh = miles * whPerMile / 1000;
        if(kWh > kwhInBattery) throw new ArithmeticException(String.format("Insufficient battery size - need %4.0f kWh", kWh));
        return kWh;
    }
    @Override
    public double dollarsToTravel(double miles) {
        return fuelConsumed(miles) * centsPerKwhOfElectricity / 100;
    }
    private double whPerMile;
    private double kwhInBattery;
}

