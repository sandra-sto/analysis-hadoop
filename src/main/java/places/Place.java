package places;

public class Place {

    long id;
    double longitude;
    double latitude;

    public Place(long id, double longitude, double latitude) {
        this.id = id;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public long getId () {
        return id;
    }

    public String getCoordinates() {
        return longitude + " " + latitude;
    }
}