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

            while((line = reader.readLine()) != null) {
                String[] parts = line.split("\t");
                String parameter = parts[0];

                double value = Double.parseDouble(parts[1]);

                if(parameter.equals("Sms"))
                    averageSms = value;
                else if(parameter.equals("Call"))
                    averageCall = value;
                else
                    averageInternet = value;
            }

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

        context.write(new ParameterValue("Sms", averageSms), new DoubleWritable(squaredDifferenceFromAverageSms));
        context.write(new ParameterValue("Calls", averageCall), new DoubleWritable(squaredDifferenceFromAverageCall));
        context.write(new ParameterValue("Internet", averageInternet), new DoubleWritable(squaredDifferenceFromAverageInternet));
    }

}