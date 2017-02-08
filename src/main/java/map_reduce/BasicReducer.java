package map_reduce;

import map_reduce.max_activity.PlaceActivity;
import map_reduce.max_activity.PlaceActivityComparator;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import places.GeoJsonReader;
import places.GridCell;
import places.GridCellsData;
import places.PlacesData;
import xml_parsing.ParsingException;

import java.io.IOException;
import java.util.TreeSet;


public class BasicReducer <S, T, V, Y> extends Reducer<S, T, V, Y> {
    protected GridCellsData gridCellsData;

    @Override
    protected void setup(Context context){
        GeoJsonReader jsonreader = new GeoJsonReader();
        try {
            gridCellsData = jsonreader.readFromJson("/home/nissatech/Downloads/milano-grid.geojson");
        } catch (ParsingException e) {
            e.printStackTrace();
        }
    }

}