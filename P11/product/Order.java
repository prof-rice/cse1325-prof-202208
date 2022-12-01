package product;

import java.util.ArrayList;

import person.Customer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class Order {
    public Order(Customer customer) {
        this.customer = customer;
        this.servings = new ArrayList<>();
    }
    
    public Order(BufferedReader br) throws IOException {
        customer = new Customer(br);
        this.servings = new ArrayList<>();
        int numServings = Integer.parseInt(br.readLine());
        while(numServings-- > 0) servings.add(new Serving(br));
    }
    
    public void save(BufferedWriter bw) throws IOException {
        customer.save(bw);
        bw.write("" + servings.size() + '\n');
        for(Serving s : servings) s.save(bw);
    }
    
    public Customer getCustomer() {
        return customer;
    }

    public void addServing(Serving serving) {
        servings.add(serving);
    }
    public Object[] servings() {
        return servings.toArray();
    }
    
    public int price() {
        int p = 0;
        for(var s : servings)   p += s.price();
        return p;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("For " + customer + ":<br/>");
        String separator = "";
        if(servings.size() > 0) {
            for(Serving s : servings) {
                result.append(separator + s.toString());
                separator = "<br/>";
            }
        }
        return result.toString();
    }
    private ArrayList<Serving> servings;
    private Customer customer;
}
