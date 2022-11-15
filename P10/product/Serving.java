package product;

import java.util.ArrayList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class Serving {
    public Serving(Container container) {
        this.container = container;
        this.scoops = new ArrayList<>();
        this.toppings = new ArrayList<>();
    }
    
    public Serving(BufferedReader br) throws IOException {
        this.container = new Container(br);
        this.scoops = new ArrayList<>();
        int numScoops = Integer.parseInt(br.readLine());
        while(numScoops-- > 0) scoops.add(new Scoop(br));
        this.toppings = new ArrayList<>();
        int numToppings = Integer.parseInt(br.readLine());
        while(numToppings-- > 0) toppings.add(new MixIn(br));
    }
    
    public void save(BufferedWriter bw) throws IOException {
        container.save(bw);
        bw.write("" + scoops.size() + '\n');
        for(Scoop s : scoops) s.save(bw);
        bw.write("" + toppings.size() + '\n');
        for(MixIn t : toppings) t.save(bw);
    }

    public void addTopping(MixIn topping) {
        toppings.add(topping);
    }
    public void addScoop(Scoop scoop) {
        scoops.add(scoop);
    }
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(container.toString());
        if(scoops.size() > 0) {
            String separator = (scoops.size() == 1) ? " with a scoop of " : " with scoops of ";
            for(Scoop s : scoops) {
                result.append(separator + s.toString());
                separator = ", ";
            }
        }
        if(toppings.size() > 0) {
            String separator = (toppings.size() == 1) ? " and topped with " : " and toppings of ";
            for(MixIn t : toppings) {
                result.append(separator + t.toString());
                separator = ", ";
            }
        }
        return result.toString();
    }
    private Container container;
    private ArrayList<Scoop> scoops;
    private ArrayList<MixIn> toppings;
}
