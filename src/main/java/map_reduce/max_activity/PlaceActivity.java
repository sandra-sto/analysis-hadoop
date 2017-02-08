package map_reduce.max_activity;

public class PlaceActivity {
    long id;
    double activity;

    PlaceActivity(long id, double activity) {
        this.id = id;
        this.activity = activity;
    }

    public long getId() {
        return  id;
    }

    public double getActivity() {
        return activity;
    }
}
