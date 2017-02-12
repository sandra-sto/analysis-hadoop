package map_reduce;

import org.apache.hadoop.io.LongWritable;

import java.util.Date;

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


    public Time getTimeOfDay() {
        return Time.getTimeOfDay(date);
    }
}
