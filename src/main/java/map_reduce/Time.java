package map_reduce;

/**
 * Created by nissatech on 2/6/17.
 */
public enum Time {
    MORNING, AFTERNOON, EVENING;

    String time;

    static {
        MORNING.time = "Morning (00 - 08)";
        AFTERNOON.time = "Afternoon (08 - 16)";
        EVENING.time = "Evening (16 - 24)";
    }

    public String getTime() {
        return time;
    }
}
