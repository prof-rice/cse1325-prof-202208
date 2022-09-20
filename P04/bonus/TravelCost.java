import java.util.ArrayList;
import vehicles.Vehicle;
import vehicles.GasVehicle;
import vehicles.ElectricVehicle;
import vehicles.BodyStyle;

public class TravelCost {
    private static java.io.Console console = System.console();
    public static void main(String[] args) {
        ArrayList<Vehicle> vehicles = new ArrayList<>();

        vehicles.add(new ElectricVehicle(2022, "Telsa",    "Model S Plaid",   BodyStyle.Sedan,     297, 100  ));
        vehicles.add(new ElectricVehicle(2022, "Telsa",    "Model 3 LR",      BodyStyle.Sedan,     242,  82  ));
        vehicles.add(new ElectricVehicle(2022, "GM",       "Bolt",            BodyStyle.Hatchback, 286,  66  ));
        vehicles.add(new ElectricVehicle(2022, "Nissan",   "LEAF",            BodyStyle.Hatchback, 269,  60  ));
        vehicles.add(new ElectricVehicle(2022, "Ford",     "Mustang Mach-E",  BodyStyle.SUV,       347,  88  ));
        vehicles.add(new ElectricVehicle(2022, "Ford",     "F-150 Lightning", BodyStyle.Truck,     511, 131  ));
        vehicles.add(new GasVehicle(     2022, "Ford",     "F-150",           BodyStyle.Truck,      25,  23  ));
        vehicles.add(new GasVehicle(     2022, "Toyota",   "Prius Hybrid",    BodyStyle.Hatchback,  55,  11.4));
        vehicles.add(new GasVehicle(     2022, "Toyota",   "RAV4",            BodyStyle.Crossover,  31,  14.5));
        vehicles.add(new GasVehicle(     2022, "Nissan",   "Rogue",           BodyStyle.Hatchback,  33,  14.5));
        vehicles.add(new GasVehicle(     2022, "Chrysler", "Pacifica",        BodyStyle.Minivan,    24,  19  ));
        vehicles.add(new GasVehicle(     2022, "Chrysler", "Pacifica Hybrid", BodyStyle.Minivan,    30,  16.5));
        
        GasVehicle.dollarsPerGallonOfGas = Double.parseDouble(console.readLine("What is the price per gallon of gas (dollars)? "));
        ElectricVehicle.centsPerKwhOfElectricity = Double.parseDouble(console.readLine("What is the price per kWh of electricity (cents)? "));
        double distance = Double.parseDouble(console.readLine("How many miles is your trip? "));
        
        for(Vehicle v : vehicles) {
            try {
                console.printf("$%6.2f (range %1.0f) %s\n", v.dollarsToTravel(distance), v.range(), v);
            } catch(Exception e) {
                console.printf("Out of range for %s: %s\n", v, e.getMessage());
            }
        }
    }
}
