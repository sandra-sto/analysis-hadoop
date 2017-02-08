package places;

import java.util.HashMap;
import java.util.Map;

public class PlacesData {

    Map<Long, Place> places;

    public PlacesData() {
        places = new HashMap<Long, Place>();
    }

    public void addPlace(Place place) {
        places.put(place.getId(), place);
    }

    public Place getPlace(long id) {
        return places.get(id);
    }

}