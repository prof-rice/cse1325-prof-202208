public class Main {
    private static java.io.Console console = System.console();
    private static int readInt(String prompt) {
        return Integer.parseInt(console.readLine(prompt));
    }
    public static void main(String[] args) {
        try {
            Clock clock = new Clock(
                readInt("Hour? "),
                readInt("Minute? "),
                readInt("Second? ")
            );
            System.out.println("The time is " + clock);
            int seconds = readInt("Seconds to tic? ");
            while(seconds-- >0) {
                clock = clock.add(1);
                System.out.println(clock);
            }
            while(seconds != 0) {
                seconds = readInt("Seconds to add (0 to continue)? ");
                clock = clock.add(seconds);
                System.out.println(clock);
            }
            Clock clock2 = new Clock(
                readInt("Add to that...\nHour? "),
                readInt("Minute? "),
                readInt("Second? ")
            );
            System.out.println(clock + " + " + clock2 + " = " + clock.add(clock2));
        } catch(Exception e) {
            System.err.println("Invalid time!\nGuru meditation: " + e);
        }
    }
}

