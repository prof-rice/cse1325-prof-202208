package product;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class MixInFlavor extends Item {
    public MixInFlavor(String name, String description, int cost, int price) {
        super(name, description, cost, price);
    }
    
    public MixInFlavor(BufferedReader br) throws IOException {
        super(br);
    }
    
    public void save(BufferedWriter bw) throws IOException {
        super.save(bw);
    } 
}
