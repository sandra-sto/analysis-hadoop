package map_reduce;

import org.apache.hadoop.mapreduce.Reducer;
import places.GeoJsonReader;
import places.GridCellsData;
import places.ParsingException;


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