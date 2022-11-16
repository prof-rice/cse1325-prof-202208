import java.util.Arrays;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Solver {
    public static void main(String[] args) {
        double  min = -1000;           // Start searching for roots at x = min
        double  max = 1000;            // Stop  searching for roots at x = max
        double  slices = 100000000;    // Check this many points between min and max
        int     threads = 1;           // Use this many threads to search
        double  precision = 0.000001;  // When a root is detected, narrow to this precision
        boolean log = false;           // Whether to print log messages to STDERR or not
        boolean fail = false;          // Set true if any initialization fails (e.g., bad filename)
        
        if(args.length == 0) {
            System.err.println("usage: java Solver [--precision p] [--threads t] [--slices s] [--min n] [--max m] [--log] file.poly...");
            System.err.println("  precision - solve until within this much of the actual root (default " + precision + ")");
            System.err.println("  threads   - use this many threads to solve each polynomial  (default " + threads + ")");
            System.err.println("  slices    - divide min-max range into this many subranges   (default " + slices + ")");
            System.err.println("  min       - start searching for roots at this x             (default " + min + ")");
            System.err.println("  max       - stop  searching for roots at this x             (default " + max + ")");
            System.err.println("  log       - print log messages to STDERR                    (default " + (log ? "print" : "no" + " messages)"));
            System.err.println("  file.poly - one or more polynomial files to load and solve");
            System.exit(0);
        }
        
        Map <String, Polynomial> polynomials = new HashMap<>();
        
        int index = -1;
        while(++index < args.length) {
            if(args[index].equals(   "--precision")) precision = Double.parseDouble(args[++index]);
            else if(args[index].equals("--threads")) threads   = Integer.parseInt  (args[++index]);
            else if(args[index].equals( "--slices")) slices    = Double.parseDouble(args[++index]);
            else if(args[index].equals(    "--min")) min       = Double.parseDouble(args[++index]);
            else if(args[index].equals(    "--max")) max       = Double.parseDouble(args[++index]);
            else {
                System.out.print("Loading " + args[index] + "... ");
                try (BufferedReader br = new BufferedReader(new FileReader(args[index]))) {
                    polynomials.put(args[index], new Polynomial(br));
                    System.out.println("done");
                } catch(IOException e) {
                    System.out.println("FAIL\n" + e);
                    fail = true;
                }
            }
        }

        Polynomial.log = log; // Set true to print log messages to STDERR, false by default
                
        if(fail) System.exit(-1);
        System.out.println("\nthreads = " + threads + ": range " + min + " to " + max + ", " + slices + " slices, precision = " + precision + "\n");
        for(var file : polynomials.keySet()) {
            Polynomial p = polynomials.get(file);
            p.solve(min, max, threads, slices, precision);
            System.out.println(file + ": " + p.toString() + " has " + p.roots().length + " roots\n  " 
                             + Arrays.toString(p.roots()) + "\n");
        }
    }
}
