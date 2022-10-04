public class Item {
    public Item(String name, String description, int cost, int price) {
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.price = price;
    }

    public String name() {return name;}
    public String description() {return description;}
    public int cost() {return cost;}
    public int price() {return price;}
    
    @Override
    public String toString() {
        return name;
    }
    protected String name;
    protected String description;
    protected int cost;
    protected int price;
} 
