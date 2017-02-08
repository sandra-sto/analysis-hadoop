package map_reduce.max_activity;

import java.io.IOException;
import java.util.TreeSet;

import map_reduce.BasicReducer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import places.GeoJsonReader;
import places.GridCell;
import places.GridCellsData;
import places.PlacesData;
import xml_parsing.ParsingException;

public class MaxActivityReducer extends BasicReducer<LongWritable, DoubleWritable, Text, DoubleWritable> {

    int maxLimit;
    PlacesData placesData;
    TreeSet<PlaceActivity> placesWiithMaxActivities;

    @Override
    protected void setup(Context context){
        super.setup(context);
        placesWiithMaxActivities = new TreeSet<PlaceActivity>(new PlaceActivityComparator());
        Configuration conf = context.getConfiguration();
        maxLimit = conf.getInt("Number of maximums", 1);
//        XmlParser xmlParser = XmlParserFactory.createXmlParser(XmlParserType.SAX_PARSER);
//        try {
//            placesData = xmlParser.parseFromXml("/home/nissatech/Downloads/ex_8XNRB6XjmAbpBRWEmTYx3VgRAxauq.osm");
//        } catch (ParsingException e) {
//            e.printStackTrace();
//        }
    }

    public void reduce(LongWritable _key, Iterable<DoubleWritable> values, Context context) throws IOException, InterruptedException {
        double sum = 0;

        for (DoubleWritable val : values) {
            sum += val.get();
        }

        if (placesWiithMaxActivities.size() < maxLimit || placesWiithMaxActivities.last().getActivity() < sum)
            placesWiithMaxActivities.add(new PlaceActivity(_key.get(), sum));
    }

    @Override
    protected void cleanup(Context context) throws IOException, InterruptedException {
//        Place place = placesData.getPlace(maxPlaceId);
//        if (place != null)
//            context.write(new Text(place.getCoordinates()), new DoubleWritable(maxActivity));
        for (int i = 0; i < maxLimit; i++) {
            PlaceActivity maxPlaceActivity = placesWiithMaxActivities.first();
            placesWiithMaxActivities.remove(maxPlaceActivity);
            GridCell cell = gridCellsData.getGridCell(maxPlaceActivity.getId());
            context.write(new Text(cell.toString()), new DoubleWritable(maxPlaceActivity.getActivity()));
//            context.write(new LongWritable(maxPlaceActivity.getId()), new DoubleWritable(maxPlaceActivity.getActivity()));
        }
    }

}