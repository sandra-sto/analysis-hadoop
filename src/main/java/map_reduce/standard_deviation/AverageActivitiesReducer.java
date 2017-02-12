package map_reduce.standard_deviation;

import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;


public class AverageActivitiesReducer extends Reducer<Text, DoubleWritable, Text, Text> {

    public void reduce(Text _key, Iterable<DoubleWritable> values, Context context) throws IOException, InterruptedException {
        double sumActivities = 0;
        long count = 0;

        for (DoubleWritable val : values) {
            sumActivities += val.get();
            count ++;
        }

        double average = sumActivities / count;
        String text = average + "";
        context.write(_key, new Text(text));
    }

}