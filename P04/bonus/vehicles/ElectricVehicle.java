package vehicles;

/**
 * Defines a battery and electric motor-propelled ground vehicle such as a car, truck, or SUV.
 *
 * @author             Professor George F. Rice
 * @version            1.0
 * @since              1.0
 * @license.agreement  Gnu General Public License 3.0
 */
public class ElectricVehicle extends Vehicle {
    /**
     * Cost of electric energy in cents per kilowatt-hour. This must be set prior to using the class!
     */
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
   /**
     * Calculate the cost of traveling a given distance.
     *
     * The cost of fuel is set by public field {@link centsPerKwhOfElectricity}.
     *
     * @param miles          The distance to be traveled in miles
     * @returns              The cost of travel in dollars
     * @since                1.0
     */
    @Override
    public double dollarsToTravel(double miles) {
        return fuelConsumed(miles) * centsPerKwhOfElectricity / 100;
    }
    private double whPerMile;
    private double kwhInBattery;
}

