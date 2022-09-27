public class Note extends Cash implements Foldable {
    public Note(String name, double value, double height, double width) {
        super(name, value);
    }
    @Override
    public void fold(int numberOfFolds) {
        System.out.println("Folded " + this + " into " + numberOfFolds + " folds.");
    }
    @Override
    public String toString() {
        return "$" + value + " bill (" + height + "x" + width + " mm)";  
    }
    private double height;
    private double width;
}
