public class Clock {
    public Clock(int hours, int minutes, int seconds) {
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
    }
    public String toString() {
        return two_digit(hours) + ":" + two_digit(minutes) + ":" + two_digit(seconds);
    }
    private String two_digit(int i) {
        return ((i<10) ? "0" : "") + i;
    }
    private int hours;
    private int minutes;
    private int seconds;
}

