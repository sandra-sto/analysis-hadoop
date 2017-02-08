package map_reduce.standard_deviation;

import java.io.IOException;

import map_reduce.TelecomunicationActivity;
import map_reduce.TelecomunicationActivityParser;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;

import org.apache.hadoop.mapreduce.Mapper;

public class AverageActivitiesMapper extends Mapper<LongWritable, Text, Text, DoubleWritable> {

    @Override
    protected void setup(Context context){

    }

    public void map(LongWritable ikey, Text ivalue, Context context) throws IOException, InterruptedException {
        String line = ivalue.toString();
        TelecomunicationActivity activity = TelecomunicationActivityParser.parseFromString(line);

        context.write(new Text("Sms"), activity.getOverallSmsActivity());
        context.write(new Text("Call"), activity.getOverallCallActivity());
        context.write(new Text("Internet"), activity.getInternetActivity());
    }

}