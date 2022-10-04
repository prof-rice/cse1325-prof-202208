public class TestScoop {
    private final static String errorFormat = 
            "FAIL: expected \n  %s\nbut generate \n  %s\n\n";
    private static boolean failed(Item item, String expected) {
        String toString = item.name() + " : " + item.description + " : "
            + item.cost() + " : " + item.price();
        if(!toString.equals(expected)) {
            System.err.printf(errorFormat, expected, toString);
            return true;
        }
        return false;
    }
    private static boolean failed(Scoop scoop, String expected) {
        String toString = scoop.toString();
        if(!toString.equals(expected)) {
            System.err.printf(errorFormat, expected, toString);
            return true;
        }
        return false;
    }
    
    public static void main(String[] args) {
        int vector = 1;
        int result = 0;
        
        // /////////////////////////////////////////////////////////////////////
        // Create MixInFlavors

        // Empty description, 0 price and cost
        MixInFlavor mif = new MixInFlavor(
            "Bits", "", 0, 0);
        String expected = "Bits :  : 0 : 0";
        if(failed(mif, expected)) result |= vector;
        
        // Empty name and description, negative cost and price, price < cost
        mif = new MixInFlavor(
            "", "", -10, -99);
        expected = " :  : -10 : -99";
        if(failed(mif, expected)) result |= vector;
        
        // Normal (and for later tests)
        MixInFlavor mif2 = new MixInFlavor(
            "ピーナッツ", "刻んだ生ナッツ", 38, 100);
        expected = "ピーナッツ : 刻んだ生ナッツ : 38 : 100";
        if(failed(mif2, expected)) result |= vector;
        
        mif = new MixInFlavor(
            "Snickers Pieces", "Chopped up Snickers bar", 24, 99);
        expected = "Snickers Pieces : Chopped up Snickers bar : 24 : 99";
        if(failed(mif, expected)) result |= vector;
        
        vector <<= 1;
        
        // /////////////////////////////////////////////////////////////////////
        // Create IceCreamFlavors

        IceCreamFlavor icf = new IceCreamFlavor(
            "Tutti Frutti", "Who came up with THAT!", 399, 599);
        expected = "Tutti Frutti : Who came up with THAT! : 399 : 599";
        if(failed(icf, expected)) result |= vector;
    
        vector <<= 1;
        
        // /////////////////////////////////////////////////////////////////////
        // Create Scoop

        MixIn mixin1 = new MixIn(mif, MixInAmount.Normal);
        MixIn mixin2 = new MixIn(mif2, MixInAmount.Drenched);
        
        // No MixIns
        Scoop scoop = new Scoop(icf);
        expected = "Tutti Frutti";
        if(failed(scoop, expected)) result |= vector;
        
        // One MixIn
        scoop.addMixIn(mixin1);
        expected += " with Snickers Pieces";
        if(failed(scoop, expected)) result |= vector;
        
        // Two MixIns
        scoop.addMixIn(mixin2);
        expected += ", ピーナッツ (Drenched)";
        if(failed(scoop, expected)) result |= vector;
        
        // /////////////////////////////////////////////////////////////////////
        // Report result
        if(result != 0) System.err.println("\n### TEST FAILED###\n");
        System.exit(result);
    }
}
