package pl.kurs.models;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;

public class Container implements Serializable {
    static final long serialVersionUID = 1L;
    private String name;
    private double maxCapacity;
    private double waterLevel;

    private List<OperationEvent> operationEventsHistory = new ArrayList<>();


    private Container(String name, double maxCapacity, double waterLevel) {
        this.name = name;
        this.maxCapacity = maxCapacity;
        this.waterLevel = waterLevel;
    }
    public static Container create(String name, double maxCapacity, double waterLevel){
        if(maxCapacity <= 0)
            throw new RuntimeException("Max capacity should be more then 0");
        if(waterLevel < 0)
            throw new RuntimeException("Water level should be more or equal then 0");
        if(maxCapacity < waterLevel)
            throw new RuntimeException("Water level can't be more then max capacity");
        return new Container(name, maxCapacity, waterLevel);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWaterLevel() {
        return waterLevel;
    }

    public void setWaterLevel(double waterLevel) {
        this.waterLevel = waterLevel;
    }

    public double getMaxCapacity() {
        return maxCapacity;
    }

    public List<OperationEvent> getOperationEventsHistory() {
        return operationEventsHistory;
    }

    @Override
    public String toString() {
        return "Container{" +
                "name='" + name + '\'' +
                ", maxCapacity=" + maxCapacity +
                ", waterLevel=" + waterLevel +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Container container = (Container) o;
        return Double.compare(container.maxCapacity, maxCapacity) == 0 && Double.compare(container.waterLevel, waterLevel) == 0 && Objects.equals(name, container.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, maxCapacity, waterLevel);
    }

    public void addWater(double qty) {
        boolean success;
        if (!addingWaterIsPossible(qty)) {
            success = false;
        } else {
            waterLevel += qty;
            success = true;
        }
        saveEvent(Timestamp.from(Instant.now()), this, OperationEvent.OperationType.ADD, qty, success);
    }

    public void subtractWater(double qty) {
        boolean success;
        if (!drainingWaterIsPossible(qty)) {
            success = false;
        } else {
            waterLevel -= qty;
            success = true;
        }
        saveEvent(Timestamp.from(Instant.now()), this, OperationEvent.OperationType.DRAIN, qty, success);
    }

    private boolean addingWaterIsPossible(double value) {
        return waterLevel + value <= maxCapacity;
    }

    private boolean drainingWaterIsPossible(double value) {
        return waterLevel - value >= 0;
    }


    public void transferWater(Container source, double qty) {
        if(source.drainingWaterIsPossible(qty) && this.addingWaterIsPossible(qty)) {
            source.subtractWater(qty);
            this.addWater(qty);
        } else {
            source.saveEvent(Timestamp.from(Instant.now()), source, OperationEvent.OperationType.DRAIN, qty, false);
            this.saveEvent(Timestamp.from(Instant.now()), this, OperationEvent.OperationType.ADD, qty, false);
        }
    }

    private void saveEvent(Timestamp date, Container container, OperationEvent.OperationType type, double value, boolean success){
        operationEventsHistory.add(new OperationEvent(date, container, type, value, success));
    }




}
