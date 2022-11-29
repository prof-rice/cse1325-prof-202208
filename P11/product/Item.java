package product;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class Item {
    public Item(String name, String description, int cost, int price) {
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.price = price;
    }
    
    public Item(BufferedReader br) throws IOException {
        this.name = br.readLine();
        this.description = br.readLine();
        this.cost = Integer.parseInt(br.readLine());
        this.price = Integer.parseInt(br.readLine());
    }
    
    public void save(BufferedWriter bw) throws IOException {
        bw.write(name + '\n');
        bw.write(description + '\n');
        bw.write("" + cost + '\n');
        bw.write("" + price + '\n');
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
