// import java.util.HashMap;

import java.util.Objects;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class Term {
    public Term(double coefficient, double exponent) {
        this.coefficient = coefficient;
        this.exponent = exponent;
    }
    public Term(BufferedReader br) throws IOException {
        this.coefficient = Double.parseDouble(br.readLine());
        this.exponent = Double.parseDouble(br.readLine());
    }
    public void save(BufferedWriter bw) throws IOException {
        bw.write("" + coefficient + "\n" + exponent + "\n");
    }
    public double eval(double x) {
        return coefficient * Math.pow(x, exponent);
    }
    @Override
    public String toString() {
        return String.format("%+.6gx^%.0f", coefficient, exponent);
    }
    @Override
    public boolean equals(Object o) {
        if(o == this) return true;
        if(o == null) return false;
        if(o.getClass() != getClass()) return false;
        Term t = (Term) o;
        return equals(coefficient, t.coefficient)
            && equals(exponent,    t.exponent);
    }
    @Override
    public int hashCode() {
        return Objects.hash(coefficient, exponent);
    }
    double coefficient;
    double exponent;
    
    // double is imprecise - equal if "close enough"
    private static final double EPSILON = 0.000001d;
    private static boolean equals(double d1, double d2) {
        return Math.abs(d1-d2) < EPSILON;
    }
}
