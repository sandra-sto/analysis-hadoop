package map_reduce.standard_deviation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import map_reduce.TelecomunicationActivity;
import map_reduce.TelecomunicationActivityParser;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;

import org.apache.hadoop.mapreduce.Mapper;

public class StandardDeviationMapper extends Mapper<LongWritable, Text, ParameterValue, DoubleWritable> {

    double averageSms;
    double averageCall;
    double averageInternet;

    @Override
    protected void setup(Context context){
        Configuration conf = context.getConfiguration();
        String pathToAverages = conf.get("Averages Output");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(pathToAverages));
            String line;

            line = reader.readLine();
            averageSms = Double.parseDouble(line);

            line = reader.readLine();
            averageCall = Double.parseDouble(line);

            line = reader.readLine();
            averageInternet = Double.parseDouble(line);

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void map(LongWritable ikey, Text ivalue, Context context) throws IOException, InterruptedException {
        String line = ivalue.toString();
        TelecomunicationActivity activity = TelecomunicationActivityParser.parseFromString(line);

        double squaredDifferenceFromAverageSms = Math.pow(activity.getOverallSmsActivity().get() - averageSms, 2);
        double squaredDifferenceFromAverageCall = Math.pow(activity.getOverallCallActivity().get() - averageCall, 2);
        double squaredDifferenceFromAverageInternet = Math.pow(activity.getInternetActivity().get() - averageInternet, 2);


//        context.write(new Text("Sms"), new DoubleWritable(squaredDifferenceFromAverageSms));
//        context.write(new Text("Calls"), new DoubleWritable(squaredDifferenceFromAverageCall));
//        context.write(new Text("Internet"), new DoubleWritable(squaredDifferenceFromAverageInternet));

        context.write(new ParameterValue("Sms", averageSms), new DoubleWritable(squaredDifferenceFromAverageSms));
        context.write(new ParameterValue("Calls", averageCall), new DoubleWritable(squaredDifferenceFromAverageCall));
        context.write(new ParameterValue("Internet", averageInternet), new DoubleWritable(squaredDifferenceFromAverageInternet));
    }

}