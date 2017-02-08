package map_reduce.comparing_activities;

import java.io.IOException;

import map_reduce.BasicReducer;
import map_reduce.TelecomunicationActivity;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import places.*;


public class ComparingActivitiesCombiner extends Reducer<LongWritable, TelecomunicationActivity, LongWritable, TelecomunicationActivity> {

    public void reduce(LongWritable _key, Iterable<TelecomunicationActivity> values, Context context) throws IOException, InterruptedException {
        double sumCallIn = 0;
        double sumCallOut = 0;
        double sumSmsIn = 0;
        double sumSmsOut = 0;
        double sumInternet = 0;

        for (TelecomunicationActivity val : values) {
            sumCallIn += val.getCallInActivity().get();
            sumCallOut += val.getCallOutActivity().get();
            sumSmsIn += val.getSmsInActivity().get();
            sumSmsOut += val.getSmsOutActivity().get();
            sumInternet += val.getInternetActivity().get();
        }

        context.write(_key, new TelecomunicationActivity(_key.get(), 0, sumSmsIn, sumSmsOut, sumCallIn, sumCallOut, sumInternet));
    }

}