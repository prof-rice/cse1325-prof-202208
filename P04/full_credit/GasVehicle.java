public class GasVehicle extends Vehicle {
    // Fuel cost - must be set prior to using the class!
    public static double dollarsPerGallonOfGas = Double.NaN;

    public GasVehicle(int year, String make, String model, BodyStyle bodyStyle,
                double milesPerGallon, double gallonsInTank) {
        super(year, make, model, bodyStyle);
        this.milesPerGallon = milesPerGallon;
        this.gallonsInTank = gallonsInTank;           
    }
    @Override
    public double range() {
        return milesPerGallon * gallonsInTank;
    }
    @Override
    public double fuelConsumed(double miles) {
        double gallons = miles / milesPerGallon;
        if(gallons > gallonsInTank) throw new ArithmeticException(String.format("Insufficient gas - need %4.1f gallons", gallons));
        return gallons;
    }
    @Override
    public double dollarsToTravel(double miles) {
        return fuelConsumed(miles) * dollarsPerGallonOfGas;
    }
    private double milesPerGallon;
    private double gallonsInTank;
}
