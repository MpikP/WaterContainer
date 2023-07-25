package pl.kurs.services;

import pl.kurs.models.Container;
import pl.kurs.models.OperationEvent;

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
    public static Container getContainerWithTheMostFailedEvent(List<Container> containers){
        Container maxFailedEventsContainer = containers.get(0);
        long maxFailedEventsCounter = 0;
        long containerFailedEventsCounter = 0;

        for(Container container : containers){
            containerFailedEventsCounter = container.getOperationEventsHistory().stream().filter(OperationEvent -> !OperationEvent.isWasSuccess()).count();
            if(containerFailedEventsCounter > maxFailedEventsCounter){
                maxFailedEventsCounter = containerFailedEventsCounter;
                maxFailedEventsContainer = container;
            }
        }

        return maxFailedEventsContainer;
    }

    public static Container getContainerMostOftenOperationType(List<Container> containers, OperationEvent.OperationType operationType){
        Container mostOftenOperationTypeContainer = containers.get(0);
        long mostOftenOperationTypeCounter = 0;
        long containerOperationTypeCounter = 0;

        for(Container container : containers){
            containerOperationTypeCounter = container.getOperationEventsHistory().stream().filter(OperationEvent -> OperationEvent.getOperationType().equals(operationType)).count();
            if(containerOperationTypeCounter > mostOftenOperationTypeCounter){
                mostOftenOperationTypeCounter = containerOperationTypeCounter;
                mostOftenOperationTypeContainer = container;
            }
        }
        return mostOftenOperationTypeContainer;
    }

}
