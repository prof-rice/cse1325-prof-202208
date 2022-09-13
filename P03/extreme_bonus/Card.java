public class Card {
    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    @Override
    public String toString() {
        return rank.toString() + suit; // or "" + rank + suit;
    }

    public int compareTo(Card card) {
        return rank.compareTo(card.rank);
    }

    private Suit suit;
    private Rank rank;
}
