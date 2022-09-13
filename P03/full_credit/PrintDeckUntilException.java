public class PrintDeckUntilException {
    public static void main(String[] args) {
        Deck deck = new Deck();
        deck.shuffle();
        try {
            while(true) {
                System.out.print(" " + deck.deal());
            }
        } catch(Deck.DeckEmpty e) {
        } catch(Exception e) {
            System.err.println("##### Exception: " + e + " > " + e.getMessage());
        }
        System.out.println();
    }
}

