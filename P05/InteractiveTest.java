import java.util.ArrayList;
import java.io.Console;

public class InteractiveTest {
    private ArrayList<MixInFlavor> mixins = new ArrayList<>();
    private ArrayList<IceCreamFlavor> flavors = new ArrayList<>();
    private ArrayList<Scoop> scoops = new ArrayList<>();
    
    private Console console = System.console();
    
    private int selectFromArray(String prompt, Object[] objects) {
        try {
            for(int i=0; i<objects.length; ++i) 
                console.printf("%d) %s\n", i, objects[i].toString());
            return Integer.parseInt(console.readLine("\n%s ", prompt));
        } catch(Exception e) {
            System.err.println("#### Invalid selection, " + e);
            return -1;
        }
    }
    
    private void addMixInFlavor() {
        console.printf("\nCreating new MixIn Flavor!\n\n");
        mixins.add(new MixInFlavor(
            console.readLine("Name? "),
            console.readLine("Description? "),
            Integer.parseInt(console.readLine("Price? ")),
            Integer.parseInt(console.readLine("Cost? "))
        ));
    }
    private void addIceCreamFlavor() {
        console.printf("\nCreating new Ice Cream Flavor!\n\n");
        flavors.add(new IceCreamFlavor(
            console.readLine("Name? "),
            console.readLine("Description? "),
            Integer.parseInt(console.readLine("Price? ")),
            Integer.parseInt(console.readLine("Cost? "))
        ));
    }
    private void addScoop() {
        try {
                console.printf("\nCreating a scoop of ice cream!\n\n");
                Scoop scoop = new Scoop(
                    flavors.get(selectFromArray("Flavor?", flavors.toArray())));
                String prompt = "Mixin? ";
                while(mixins.size() > 0) {
                    int mixinIndex = selectFromArray(prompt, mixins.toArray());
                    if(mixinIndex < 0) break;
                    int amount = selectFromArray("Amount?", MixInAmount.values());
                    if(amount < 0) break;
                    scoop.addMixIn(new MixIn(mixins.get(mixinIndex), 
                                   MixInAmount.values()[amount]));
                    prompt = "Another mixin?";
                }
                console.printf("Adding %s\n", scoop.toString());
                scoops.add(scoop);
        } catch(Exception e) {
            System.err.println("#### Cancel scoop creation: " + e);
        }
    }
        
    public void test() {
        char cmd = ' ';
        while(cmd != 'q') {
            try {
                console.printf("\n================\nMICE Tester v0.1\n================\n");
                if(scoops.size() > 0) console.printf("\nList of Ice Cream Scoops:\n");
                for(Scoop s : scoops) console.printf("  %s\n", s.toString());
                cmd = console.readLine("\nCreate new (m)ixin, (i)ce cream flavor, " 
                                      + ((flavors.size() > 0) ? "(s)coop, " : "") 
                                      + "or (q)uit? ")
                             .trim().toLowerCase().charAt(0);
                switch(cmd) {
                    case 'm' -> addMixInFlavor();
                    case 'i' -> addIceCreamFlavor();
                    case 's' -> addScoop();
                    case 'q' -> { }
                    default  -> System.err.println("#### Invalid Command");
                }
            } catch(Exception e) {
                System.err.println("#### Invalid input: " + e);
            }
        }       
    }
    
    public static void main(String[] args) {
        InteractiveTest interactiveTest = new InteractiveTest();
        interactiveTest.test();
    }
}
