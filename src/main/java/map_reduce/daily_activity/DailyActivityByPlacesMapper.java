package map_reduce.daily_activity;

import java.io.IOException;

import map_reduce.DateInformation;
import map_reduce.TelecomunicationActivity;
import map_reduce.TelecomunicationActivityParser;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;

import org.apache.hadoop.mapreduce.Mapper;

public class DailyActivityByPlacesMapper extends Mapper<LongWritable, Text, LongWritable, TelecomunicationActivity> {

    public void map(LongWritable ikey, Text ivalue, Context context) throws IOException, InterruptedException {
        String line = ivalue.toString();
        TelecomunicationActivity activity = TelecomunicationActivityParser.parseFromString(line);

        context.write(activity.getSquareId(), activity);
    }

}