public class Clock {
    public Clock(int hours, int minutes, int seconds) {
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
        rationalize();
    }
    public String toString() {
        return two_digit(hours) + ":" + two_digit(minutes) + ":" + two_digit(seconds);
    }
    private String two_digit(int i) {
        return ((i<10) ? "0" : "") + i;
    }
    private void rationalize() {
        while(seconds < 0) {
            seconds += 60;
            minutes -= 1;
        }
        while(minutes < 0) {
            minutes += 60;
            hours -= 1;
        }
        while(hours < 0) {
            hours += 24;
        }
        while(seconds > 59) {
            seconds -= 60;
            minutes += 1;
        }
        while(minutes > 59) {
            minutes -= 60;
            hours += 1;
        }
        hours %= 24;
    }
    private int hours;
    private int minutes;
    private int seconds;
}

