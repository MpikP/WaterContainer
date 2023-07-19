package pl.kurs.models;

import java.util.Comparator;

public class FullWaterLevelComparator implements Comparator<Container> {

    @Override
    public int compare(Container c1, Container c2) {
        return Double.compare(c1.getWaterLevel() / c1.getMaxCapacity(), c2.getWaterLevel() / c2.getMaxCapacity());
    }
}
