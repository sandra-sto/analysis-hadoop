package map_reduce.daily_activity;

import java.io.IOException;

import map_reduce.BasicReducer;
import map_reduce.DateInformation;
import map_reduce.TelecomunicationActivity;
import map_reduce.Time;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import places.GridCell;


public class DailyActivityByPlacesReducer extends BasicReducer<LongWritable, TelecomunicationActivity, Text, Text> {

    public void reduce(LongWritable _key, Iterable<TelecomunicationActivity> values, Context context) throws IOException, InterruptedException {
        double morningActivities = 0;
        double afternoonActivities = 0;
        double eveningActivities = 0;

        for (TelecomunicationActivity val : values) {
            double overallActivities = val.getOverallActivity().get();
            DateInformation dateInformation = val.getDateInformation();

            if (dateInformation.getTimeOfDay() == Time.MORNING)
                morningActivities += overallActivities;
            else if (dateInformation.getTimeOfDay() == Time.AFTERNOON)
                afternoonActivities += overallActivities;
            else
                eveningActivities += overallActivities;

        }
        GridCell cell = gridCellsData.getGridCell(_key.get());
        String text = Time.MORNING.getTime() + ": " + morningActivities + "\t" + Time.AFTERNOON.getTime() + ": " + afternoonActivities + "\t" + Time.EVENING.getTime() + ": " + eveningActivities + "\n";

        context.write(new Text(cell.toString()), new Text(text));
    }

}