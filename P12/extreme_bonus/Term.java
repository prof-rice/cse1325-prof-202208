// import java.util.HashMap;

import java.util.Objects;
import java.util.HashMap;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class Term {
    public Term(double coefficient, double exponent) {
        this.coefficient = coefficient;
        this.exponent = exponent;
        
        // If subs has not been populated, do that now
        if(subs.size() == 0) {
            subs.put('0', '⁰');
            subs.put('1', '¹');
            subs.put('2', '²');
            subs.put('3', '³');
            subs.put('4', '⁴');
            subs.put('5', '⁵');
            subs.put('6', '⁶');        
            subs.put('7', '⁷');
            subs.put('8', '⁸');
            subs.put('9', '⁹');
            subs.put('+', '⁺');
            subs.put('-', '⁻');
            subs.put('e', 'ᵉ');
            subs.put('.', 'ᐧ');
            
            //System.err.println(subs);
        }
    }
    public Term(BufferedReader br) throws IOException {
        this(Double.parseDouble(br.readLine()),
             Double.parseDouble(br.readLine()));
    }
    public void save(BufferedWriter bw) throws IOException {
        bw.write("" + coefficient + "\n" + exponent + "\n");
    }
    public double eval(double x) {
        return coefficient * Math.pow(x, exponent);
    }
    @Override
    public String toString() {
        // result is the String representation of this polynomial term
        String result = "";
        
        // If an integer, valueOf adds ".0" to the end - remove that
        String s = String.valueOf(coefficient);
        if(s.endsWith(".0")) s = s.substring(0, s.length()-2);
        
        // If the coefficient is 0, we have no term at all
        if     (coefficient ==  0) return result;
        
        // If the coefficient is 1 or -1, we just need the sign
        else if(coefficient == -1) result += '-';
        else if(coefficient ==  1) result += '+';
        
        // Otherwise, we need the value of the coefficient
        // We use "+" to avoid Java doing character math (*sigh*)
        else if(coefficient  >  0) result += "+" + s;
        else                       result +=       s;
  
        // If the exponent is 0, we have no x at all
        if(exponent == 0) return result;
        result += 'x';
        
        // If the exponent is 1, we ONLY have x - no visible exponent
        if(exponent == 1) return result;
        
        // OK, we'll convert the exponent into superscript characters
        s = String.valueOf(exponent);
        StringBuilder sb = new StringBuilder();
        
        // If an integer, valueOf adds ".0" to the end - remove that
        if(s.endsWith(".0")) s = s.substring(0, s.length()-2);
        
        // Replace each char with its superscript equivalent
        // If missing, print a warning and use the char instead
        for(int i=0; i< s.length(); ++i) {
            Character c = subs.get(s.charAt(i));
            if(c == null) System.err.println("WARNING: No exponent match for " + c);
            sb.append((c != null) ? c : s.charAt(i));
        }
        result += sb.toString();
        return result;
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
    
    // Map to translate exponent chars into superscript chars
    private static HashMap<Character, Character> subs = new HashMap<>();
    
    // double is imprecise - equal if "close enough"
    private static final double EPSILON = 0.000001d;
    private static boolean equals(double d1, double d2) {
        return Math.abs(d1-d2) < EPSILON;
    }
}
