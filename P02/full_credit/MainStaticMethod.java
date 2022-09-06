public class MainStaticMethod {
    private static java.io.Console console = System.console();
    private static int readInt(String prompt) {
        return Integer.parseInt(console.readLine(prompt));
    }
    public static void main(String[] args) {
        Clock clock = new Clock(
            readInt("Hour? "),
            readInt("Minute? "),
            readInt("Second? ")
        );
        System.out.println("The time is " + clock);
    }
}

