public class Main {
    private static java.io.Console console = System.console();
    public static void main(String[] args) {
        Clock clock = new Clock(
            Integer.parseInt(console.readLine("Hour? ")),
            Integer.parseInt(console.readLine("Minute? ")),
            Integer.parseInt(console.readLine("Second? "))
        );
        System.out.println("The time is " + clock);
    }
}

