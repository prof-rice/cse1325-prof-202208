package vehicles;

/**
 * Defines a gasoline-fueled ground vehicle such as a car, truck, or SUV.
 *
 * @author             Professor George F. Rice
 * @version            1.0
 * @since              1.0
 * @license.agreement  Gnu General Public License 3.0
 */
public class GasVehicle extends Vehicle {
    /**
     * Cost of gasoline in dollars per gallon. This must be set prior to using the class!
     */
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
   /**
     * Calculate the cost of traveling a given distance.
     *
     * The cost of fuel is set by public field {@link dollarsPerGallonOfGas}.
     *
     * @param miles          The distance to be traveled in miles
     * @returns              The cost of travel in dollars
     * @since                1.0
     */
     @Override
    public double dollarsToTravel(double miles) {
        return fuelConsumed(miles) * dollarsPerGallonOfGas;
    }
    private double milesPerGallon;
    private double gallonsInTank;
}
