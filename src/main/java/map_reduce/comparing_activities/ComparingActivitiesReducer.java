package map_reduce.comparing_activities;

import java.io.IOException;

import map_reduce.BasicReducer;
import map_reduce.TelecomunicationActivity;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import places.*;


public class ComparingActivitiesReducer extends BasicReducer<LongWritable, TelecomunicationActivity, Text, Text> {

    public void reduce(LongWritable _key, Iterable<TelecomunicationActivity> values, Context context) throws IOException, InterruptedException {
        double sumCall = 0;
        double sumSms = 0;
        double sumInternet = 0;

        for (TelecomunicationActivity val : values) {
            sumCall += val.getOverallCallActivity().get();
            sumSms += val.getOverallSmsActivity().get();
            sumInternet += val.getInternetActivity().get();
        }

        String text = "Call: " + sumCall + "\t" + "Sms: " + sumSms + "\t" + "Internet: " + sumInternet + "\n";

        GridCell cell = gridCellsData.getGridCell(_key.get());
        context.write(new Text(cell.toString()), new Text(text));
//        context.write(_key, new Text(text));
    }

}