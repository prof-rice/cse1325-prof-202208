package person;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class Customer extends Person {
    public Customer(String name, String phone) {
        super(name, phone);
    }
    
    public Customer(BufferedReader br) throws IOException {
        super(br);
    }
    
    public void save(BufferedWriter bw) throws IOException {
        super.save(bw);
    } 
}
