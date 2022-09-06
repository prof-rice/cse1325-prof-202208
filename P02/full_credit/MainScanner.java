import java.util.Scanner;

public class MainScanner {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter hours, minutes, and seconds: ");
        int hours   = in.nextInt();
        int minutes = in.nextInt();
        int seconds = in.nextInt();
        Clock clock = new Clock(hours, minutes, seconds);
        System.out.println("The time is " + clock);
    }
}

