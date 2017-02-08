package map_reduce.daily_activity;

import java.io.IOException;

import map_reduce.BasicReducer;
import map_reduce.TelecomunicationActivity;
import org.apache.hadoop.io.Text;


public class TotalDailyActivityReducer extends BasicReducer<Text, TelecomunicationActivity, Text, Text> {

    public void reduce(Text _key, Iterable<TelecomunicationActivity> values, Context context) throws IOException, InterruptedException {
        double sumActivities = 0;

        for (TelecomunicationActivity val : values) {
            sumActivities += val.getOverallActivity().get();
        }

        String text = sumActivities + "";

        context.write(_key, new Text(text));
    }

}