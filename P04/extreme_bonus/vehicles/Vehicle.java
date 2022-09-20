package vehicles;

/**
 * Defines an abstract self-propelled ground vehicle such as a car, truck, or SUV.
 *
 * @author             Professor George F. Rice
 * @version            1.0
 * @since              1.0
 * @license.agreement  Gnu General Public License 3.0
 */
public interface Vehicle {

   /**
     * Calculate how far the vehicle can travel without refueling.
     *
     * @returns              The expected distance in miles
     * @since                1.0
     */
    public double range();

   /**
     * Calculate how much fuel would be consumed to travel a given distance.
     *
     * @param miles          The distance to be traveled in miles
     * @returns              The amount of fuel consumed (units vary)
     * @since                1.0
     */
    public double fuelConsumed(double miles);

   /**
     * Calculate the cost of traveling a given distance.
     *
     * The cost of fuel is set by each subclass, typically as a public field.
     *
     * @param miles          The distance to be traveled in miles
     * @returns              The cost of travel in dollars
     * @since                1.0
     */
    public double dollarsToTravel(double miles);
}
