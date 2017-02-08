package map_reduce.standard_deviation;

import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import places.GeoJsonReader;
import places.Place;
import places.PlacesData;
import xml_parsing.ParsingException;
import xml_parsing.XmlParser;
import xml_parsing.XmlParserFactory;
import xml_parsing.XmlParserType;

public class StandardDeviationReducer extends Reducer<ParameterValue, DoubleWritable, DoubleWritable, DoubleWritable> {

    @Override
    protected void setup(Context context){
        GeoJsonReader jsonreader = new GeoJsonReader();

    }
    public void reduce(ParameterValue _key, Iterable<DoubleWritable> values, Context context) throws IOException, InterruptedException {
        double sum = 0;
        long count = 0;

        for (DoubleWritable val : values) {
            sum += val.get();
            count ++;
        }

        double average = sum / count;
        double stDeviation = Math.sqrt(average);

        context.write(_key.getValue(), new DoubleWritable(stDeviation));
    }

}