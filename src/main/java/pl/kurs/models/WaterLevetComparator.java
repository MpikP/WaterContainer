package pl.kurs.models;

import java.util.Comparator;

public class WaterLevetComparator implements Comparator<Container> {

    @Override
    public int compare(Container c1, Container c2) {
        return Double.compare(c1.getWaterLevel(), c2.getWaterLevel());
    }
}
