package map_reduce.standard_deviation;

import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.mapreduce.Reducer;


public class StandardDeviationReducer extends Reducer<ParameterValue, DoubleWritable, ParameterValue, DoubleWritable> {

    public void reduce(ParameterValue _key, Iterable<DoubleWritable> values, Context context) throws IOException, InterruptedException {
        double sum = 0;
        long count = 0;

        for (DoubleWritable val : values) {
            sum += val.get();
            count ++;
        }

        double average = sum / count;
        double stDeviation = Math.sqrt(average);

        context.write(_key, new DoubleWritable(stDeviation));
    }

}