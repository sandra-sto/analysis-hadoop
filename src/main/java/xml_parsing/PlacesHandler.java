package xml_parsing;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import places.Place;
import places.PlacesData;

public class PlacesHandler extends DefaultHandler {

    PlacesData placesData;

    PlacesHandler() {
        super();
        placesData = new PlacesData();
    }

    @Override
    public void startElement(String uri,
                             String localName, String qName, Attributes attributes)
            throws SAXException {

        if (qName.equalsIgnoreCase("node")) {
            long id = Long.parseLong(attributes.getValue("id"));

            double longitude = Double.parseDouble(attributes.getValue("lon"));
            double latitude = Double.parseDouble(attributes.getValue("lat"));

            Place place = new Place(id, longitude, latitude);
            placesData.addPlace(place);
        }
    }

    public PlacesData getPlacesData() {
        return placesData;
    }
}