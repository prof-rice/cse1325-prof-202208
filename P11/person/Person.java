package person;

import java.util.Objects;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class Person {
    public Person(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }
    
    public Person(BufferedReader br) throws IOException {
        this.name = br.readLine();
        this.phone = br.readLine();
    }
    
    public void save(BufferedWriter bw) throws IOException {
        bw.write(name + '\n');
        bw.write(phone + '\n');
    }

    public String name() {return name;}
    public String phone() {return phone;}
    
    @Override
    public String toString() {
        return name;
    }
    @Override
    public boolean equals(Object rhs) {
        if(this == rhs) return true;
        if(rhs == null) return false;
        if (this.getClass() != rhs.getClass()) return false;
        Person p = (Person) rhs;
        return name.equals(p.name) && phone.equals(p.phone);
    }
    @Override
    public int hashCode() {
        return Objects.hash(name, phone);
    }
    protected String name;
    protected String phone;
} 
