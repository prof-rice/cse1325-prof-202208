import java.util.HashMap;
import java.util.Objects;

abstract class Book {
    public Book(String title, long isbn) {
        this.title = title;
        this.isbn = isbn;
    }
    @Override
    public String toString() {
        return title + "(ISBN " + isbn + ", ";
    }
    protected String title;
    protected long isbn;
}


class Paperback extends Book {
    public Paperback(String title, long isbn, double weight) {
        super(title, isbn);
        this.weight = weight;
    }
    @Override
    public String toString() {
        return super.toString() + "paperback, " + weight + " kg)";
    }
    @Override
    public boolean equals(Object o) { // 1a (1 if straight comparison)
        if(o == this) return true;   // ½
        if(o == null) return false;  // ½
        if(o.getClass() != this.getClass()) return false;  // ½
        Paperback pb = (Paperback) o;  // ½
        return pb.title.equals(title)  // 1 (permit == - it's a string)
            && pb.isbn == isbn
            && pb.weight == weight;
    }
    @Override
    public int hashCode() { // 1b
        // ½ each for prime number and each field
        return Objects.hash(title, isbn, weight); // or 2
    }
    private double weight;
}

class eBook extends Book {
    public eBook(String title, long isbn, int kilobytes) {
        super(title, isbn);
        this.kilobytes = kilobytes;
    }
    @Override
    public String toString() {
        return super.toString() + "digital, " + kilobytes + " kb)";
    }
    @Override
    public boolean equals(Object o) {
        if(o == this) return true;
        if(o == null) return false;
        if(o.getClass() != this.getClass()) return false;
        eBook eb = (eBook) o;
        return eb.title.equals(title)
            && eb.isbn == isbn
            && eb.kilobytes == kilobytes;
    }
    @Override
    public int hashCode() {
        return Objects.hash(title, isbn, kilobytes);
    }
    private int kilobytes;
}

public class Bookstore {
    private HashMap<Book, Double> priceList = new HashMap<>();  // 1c. ½ left and right of =
    public void addBook(Book book, double price) {
        priceList.put(book, price);  // 1d. ½ each put and rest
    }
    @Override
    public String toString() {  // 1e.
        String catalog = "";
        for(Book b : priceList.keySet()) { // ½ for-each or for + ½ priceList + 1 keySet or equiv
            catalog += String.format("%-50s $%6.2f\n", b, priceList.get(b)); // 1½ for valid
        }
        return catalog;// ½  for returning valid string
    }
    
    public static void main(String[] args) {
        Bookstore store = new Bookstore();
        store.addBook(new Paperback("The Martian", 9780553418026L, 0.3), 8.99);
        store.addBook(new eBook("The Martian", 9780553418026L,  3693), 7.99);
        System.out.println(store);
    }
}


