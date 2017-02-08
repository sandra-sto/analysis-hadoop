package places;

public class GridCell {
    long id;
    Polygon polygon;

    public GridCell(long id, Polygon polygon) {
        this.id = id;
        this.polygon = polygon;
    }

    public long getId() {
        return id;
    }

    public Polygon getPolygon() {
        return polygon;
    }

    @Override
    public String toString() {
        return "" + id + " " + polygon.toString();
    }
}
