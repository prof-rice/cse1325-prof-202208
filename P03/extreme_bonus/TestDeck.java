public class TestDeck {
    public static void main(String[] args) {
        int vector = 1;
        int errorCode = 0;
        
        // All cards are created exactly once
        Deck d = new Deck();
        String expected = "9A8A7A6A5A4A3A2A1A0A9T8T7T6T5T4T3T2T1T0T9U8U7U6U5U4U3U2U1U0U";
        String actual = "";
        while(!d.isEmpty()) actual += d.deal().toString();
        if(!expected.equals(actual)) {
            System.err.println("\nERROR: Constructor failed to create cards");
            System.err.println("  Expected: " + expected);
            System.err.println("  Actual:   " + actual);
            errorCode |= vector;
        }
        vector <<= 1;
        
        // Decks are not identical after shuffling
        Deck d1 = new Deck(); d1.shuffle();
        Deck d2 = new Deck(); d2.shuffle();
        int matches = 0;
        while(!d1.isEmpty()) if(d1.deal().toString().equals(d2.deal().toString())) ++matches;
        if(matches > 25) {
            System.err.println("\nERROR: shuffle works poorly - " + matches + " matches");
            errorCode |= vector;
        }
        vector <<= 1;
        
        // Verify isEmpty() and the DeckEmpty exception
        Deck dd = new Deck();
        String errorMessage = "";
        try {
            for(int i=0; i<29; ++i) {
                dd.deal();
                if(dd.isEmpty()) errorMessage += "Deal " + i + " shows empty\n";
            }
            dd.deal();
            if(!dd.isEmpty()) errorMessage += "Deal 30 shows not empty\n";
        } catch (Exception e) {
            errorMessage += "Deal 0-30 threw " + e.getClass() + "\n";
        }
        try {
            dd.deal();
            errorMessage += "Deal 31 generated no exception\n";
        } catch(Deck.DeckEmpty e) {
            //pass
        } catch(Exception e) {
            errorMessage += "Deal 31 threw " + e.getClass() + "\n"; // wrong exception!
        }
        if(!errorMessage.isBlank()) {
            System.err.println("\nERROR: " + errorMessage);
            errorCode |= vector;
        }
        vector <<= 1;
        
        // Summarize test results       
        if(errorCode != 0) 
            System.err.println("\nFAIL: Error code " + errorCode);
        System.exit(errorCode);
    }
}
