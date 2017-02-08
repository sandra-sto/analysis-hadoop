package places;

import java.util.ArrayList;
import java.util.List;

public class Polygon {
    List<Coordinates> coordinatesList;

   public Polygon() {
       coordinatesList = new ArrayList<Coordinates>();
   }

    public Polygon(List<Coordinates> coordinates) {
        this.coordinatesList = coordinates;
    }

    public void addCoordinates(Coordinates coordinates) {
        coordinatesList.add(coordinates);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Coordinates coord : coordinatesList) {
            builder.append("[" + coord.getLongitude() + ", " + coord.getLatitude() + "] ");
        }
        return builder.toString();
    }
}
