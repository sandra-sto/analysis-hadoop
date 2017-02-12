package map_reduce.max_activity;

import java.io.IOException;

import map_reduce.TelecomunicationActivity;
import map_reduce.TelecomunicationActivityParser;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;

public class MaxActivityMapper extends org.apache.hadoop.mapreduce.Mapper<LongWritable, Text, LongWritable, DoubleWritable> {

//    PlacesData placesData;

    @Override
    protected void setup(Context context){
//        XmlParser xmlParser = XmlParserFactory.createXmlParser(XmlParserType.SAX_PARSER);
//        try {
//            placesData = xmlParser.parseFromXml("/home/nissatech/Documents/xml.xml");
//        } catch (ParsingException e) {
//            e.printStackTrace();
//        }
    }

    public void map(LongWritable ikey, Text ivalue, Context context) throws IOException, InterruptedException {
        String line = ivalue.toString();
        TelecomunicationActivity activity = TelecomunicationActivityParser.parseFromString(line);

        context.write(activity.getSquareId(), activity.getOverallSmsActivity());
    }

}