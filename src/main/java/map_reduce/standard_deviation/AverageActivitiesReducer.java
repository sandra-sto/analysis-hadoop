package map_reduce.standard_deviation;

import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import places.Place;
import places.PlacesData;
import xml_parsing.ParsingException;
import xml_parsing.XmlParser;
import xml_parsing.XmlParserFactory;
import xml_parsing.XmlParserType;

public class AverageActivitiesReducer extends Reducer<Text, DoubleWritable, NullWritable, Text> {

    public void reduce(Text _key, Iterable<DoubleWritable> values, Context context) throws IOException, InterruptedException {
        double sumActivities = 0;
        long count = 0;

        for (DoubleWritable val : values) {
            sumActivities += val.get();
            count ++;
        }

        double average = sumActivities / count;
        String text = average + "";
        context.write(NullWritable.get(), new Text(text));
    }

}