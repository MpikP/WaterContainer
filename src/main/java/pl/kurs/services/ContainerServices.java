package pl.kurs.services;

import pl.kurs.models.Container;
import pl.kurs.models.FullWaterLevelComparator;
import pl.kurs.models.WaterLevetComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ContainerServices {
    public static Container getContainerWithTheMostWater(List<Container> containers){
        return Collections.max(containers, new WaterLevetComparator());
    }
    public static Container getContainerTheMostFull(List<Container> containers){
        return Collections.max(containers, new FullWaterLevelComparator());
    }

    public static List<Container> getEmptyContainers(List<Container> containers){
        List<Container> emptyContainers = new ArrayList<>();
        for (Container container : containers) {
            if(container.getWaterLevel() == 0)
                emptyContainers.add(container);
        }
        return emptyContainers;
    }
}
