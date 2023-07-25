package pl.kurs.services;

import pl.kurs.models.Container;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ContainerService {
    public static Container getContainerWithTheMostWater(List<Container> containers){
        return Collections.max(containers, Comparator.comparingDouble(Container::getWaterLevel));
    }
    public static Container getContainerTheMostFull(List<Container> containers){
        return Collections.max(containers, Comparator.comparingDouble(x -> x.getWaterLevel() / x.getMaxCapacity()));
    }

    public static List<Container> getEmptyContainers(List<Container> containers){
        return containers.stream()
                .filter(x -> x.getWaterLevel() == 0)
                .collect(Collectors.toList());
    }
}
