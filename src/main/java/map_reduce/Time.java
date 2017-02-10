package map_reduce;

import java.util.Date;

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

    public static Time getTimeOfDay(Date date) {
        int hours = date.getHours();

        if (hours < 8)
            return MORNING;
        else if (hours < 16)
            return AFTERNOON;
        else
            return EVENING;
    }

    public String getTimeOfDayAsString() {
        return time;
//        return getTimeOfDay().getTime();
    }
}
