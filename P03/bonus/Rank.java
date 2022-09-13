public class Rank{
    public static final int MIN = 0;
    public static final int MAX = 9;
    public Rank(int rank) {
        if(MAX < rank || rank < MIN) throw new IllegalArgumentException("Invalid Rank " + rank);
        this.rank = rank;
    }
    @Override
    public String toString() {
        return "" + rank;
    }
    private int rank;
}
