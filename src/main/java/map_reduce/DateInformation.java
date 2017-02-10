package map_reduce;

import org.apache.hadoop.io.LongWritable;

import java.util.Date;

/**
 * Created by nissatech on 1/31/17.
 */
public class DateInformation {
    Date date;

    public DateInformation(Date date) {
        this.date = date;
    }

    public DateInformation(long time) {
        date = new Date(time);
    }

    public DateInformation(LongWritable time) {
        date = new Date(time.get());
    }

    public Date getDate() {
        return date;
    }

//    public String getTimeOfDayAsString() {
//        return getTimeOfDay().getTime();
//    }

    public Time getTimeOfDay() {
//        int hours = date.getHours();
//
//        if (hours < 8)
//            return Time.MORNING;
//        else if (hours < 16)
//        return Time.AFTERNOON;
//        else
//            return Time.EVENING;

        return Time.getTimeOfDay(date);
    }
}
