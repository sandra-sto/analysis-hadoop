package map_reduce.comparing_activities;

import java.io.IOException;

import map_reduce.TelecomunicationActivity;
import map_reduce.TelecomunicationActivityParser;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;

import org.apache.hadoop.mapreduce.Mapper;

public class ComparingActivitiesMapper extends Mapper<LongWritable, Text, LongWritable, TelecomunicationActivity> {

    public void map(LongWritable ikey, Text ivalue, Context context) throws IOException, InterruptedException {
        String line = ivalue.toString();
        TelecomunicationActivity activity = TelecomunicationActivityParser.parseFromString(line);

//        context.write(new LongWritable(activity.getSquareId()), new DoubleWritable(activity.getOverallSmsActivity()));
        context.write(activity.getSquareId(), activity);
    }

}