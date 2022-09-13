import java.util.Stack;
import java.util.Collections;

public class Deck {
    public Deck() {
        for(Suit suit : Suit.values()) {
            for(int rank = Rank.MIN; rank <= Rank.MAX; ++rank) {
                deck.push(new Card(new Rank(rank), suit));
            }
        }
    }
    public void shuffle() {
        Collections.shuffle(deck);
    }
    public Card deal() {
        if (deck.empty()) throw new DeckEmpty();
        return deck.pop();
    }
    public boolean isEmpty() {
        return deck.empty();
    }
    public class DeckEmpty extends IndexOutOfBoundsException { }
    private Stack<Card> deck = new Stack<>();
}
