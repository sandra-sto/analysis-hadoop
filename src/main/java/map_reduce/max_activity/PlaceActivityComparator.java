package map_reduce.max_activity;

import java.util.Comparator;

public class PlaceActivityComparator implements Comparator<PlaceActivity> {

    PlaceActivityComparator() {
        super();
    }
    public int compare(PlaceActivity o1, PlaceActivity o2) {
        if (o1.getActivity() > o2.getActivity())
            return -1;
        else if (o1.getActivity() == o2.getActivity())
            return 0;
        else return 1;
    }
}
