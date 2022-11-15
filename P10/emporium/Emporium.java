package emporium;

import product.Item;
import product.IceCreamFlavor;
import product.MixInFlavor;
import product.MixInAmount;
import product.MixIn;
import product.Container;
import product.Order;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import java.util.ArrayList;

public class Emporium {
    public Emporium() { }
    
    public Emporium(BufferedReader br) throws IOException { 
        int size = Integer.parseInt(br.readLine());
        while(size-- > 0) mixInFlavors.add(new MixInFlavor(br));

        size = Integer.parseInt(br.readLine());
        while(size-- > 0) iceCreamFlavors.add(new IceCreamFlavor(br));

        size = Integer.parseInt(br.readLine());
        while(size-- > 0) containers.add(new Container(br));

        size = Integer.parseInt(br.readLine());
        while(size-- > 0) orders.add(new Order(br));
    }    
    public void save(BufferedWriter bw) throws IOException {
        bw.write("" + mixInFlavors.size() + '\n');
        for(MixInFlavor mif : mixInFlavors) mif.save(bw);

        bw.write("" + iceCreamFlavors.size() + '\n');
        for(IceCreamFlavor icf : iceCreamFlavors) icf.save(bw);

        bw.write("" + containers.size() + '\n');
        for(Container c : containers) c.save(bw);

        bw.write("" + orders.size() + '\n');
        for(Order o : orders) o.save(bw);
    }

    public void addMixInFlavor(MixInFlavor flavor) {
        mixInFlavors.add(flavor);
    }
    public void addIceCreamFlavor(IceCreamFlavor flavor) {
        iceCreamFlavors.add(flavor);
    }
    public void addContainer(Container container) {
        containers.add(container);
    }
    public void addOrder(Order order) {
        orders.add(order);
    }
    public Object[] iceCreamFlavors() {
        return this.iceCreamFlavors.toArray();
    }
    public Object[] mixInFlavors() {
        return this.mixInFlavors.toArray();
    }
    public Object[] containers() {
        return this.containers.toArray();
    }
    public Object[] orders() {
        return this.orders.toArray();
    }

    private ArrayList<MixInFlavor> mixInFlavors = new ArrayList<>();
    private ArrayList<IceCreamFlavor> iceCreamFlavors = new ArrayList<>();
    private ArrayList<Container> containers = new ArrayList<>();
    private ArrayList<Order> orders = new ArrayList<>();
}
