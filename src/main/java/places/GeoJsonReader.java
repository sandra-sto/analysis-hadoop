package places;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class GeoJsonReader {
    public GridCellsData readFromJson(String file) throws ParsingException{
        try {
            return tryReadfromJson(file);
        } catch (IOException e) {
            throw new ParsingException("Cannot read file");
        } catch(ParseException e) {
            throw new ParsingException("Cannot parse json file");
        }
    }

    private GridCellsData tryReadfromJson(String file) throws IOException, ParseException{
        JSONParser jsonParser = new JSONParser();
        Object obj = jsonParser.parse(new FileReader(file));
        JSONObject jsonObject = (JSONObject) obj;
        JSONArray features = (JSONArray) jsonObject.get("features");

        GridCellsData gridCellsData = new GridCellsData();

        for (Object featureObject : features) {
            JSONObject feature = (JSONObject) featureObject;

            JSONObject geometry = (JSONObject)  feature.get("geometry");

            JSONArray points = (JSONArray) geometry.get("coordinates");
            JSONArray pointsArray = (JSONArray) points.get(0);
            Polygon polygon = new Polygon();

            for (Object object : pointsArray) {
                JSONArray pointJson = (JSONArray) object;
                Coordinates coord = new Coordinates((Double) pointJson.get(0), (Double)
                        pointJson.get(1));
                polygon.addCoordinates(coord);
            }

            JSONObject properties = (JSONObject) feature.get("properties");
            long cellId = (Long) properties.get("cellId");

            GridCell gridCell = new GridCell(cellId, polygon);
            gridCellsData.addGridCell(gridCell);
        }
        return gridCellsData;
    }
}
