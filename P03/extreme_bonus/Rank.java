public class Rank{
    public static final int MIN = 0;
    public static final int MAX = 9;
    public Rank(int rank) {
        if(MAX < rank || rank < MIN) throw new IllegalArgumentException("Invalid Rank " + rank);
        this.rank = rank;
    }
    public int compareTo(Rank r) {
        if(rank < r.rank) return -1;
        if(rank > r.rank) return 1;
        return 0;
    }
    @Override
    public String toString() {
        return "" + rank;
    }
    private int rank;
}
