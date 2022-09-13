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
    public Deck(int cards) {
        while(cards > deck.size()) {
            for(Suit suit : Suit.values()) {
                for(int rank = Rank.MIN; rank < Rank.MAX; ++rank) {
                    if(cards > deck.size())
                        deck.push(new Card(new Rank(rank), suit));
                }
            }
        }
    }
    public void add(Card card) {
        deck.push(card);
    }
    public void shuffle() {
        Collections.shuffle(deck);
    }
    public Card deal() {
        if (deck.empty()) throw new DeckEmpty();
        return deck.pop();
    }
    public int size() {
        return deck.size();
    }
    public boolean isEmpty() {
        return deck.empty();
    }
    public class DeckEmpty extends IndexOutOfBoundsException { }
    private Stack<Card> deck = new Stack<>();
}
