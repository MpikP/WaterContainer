package pl.kurs.models;

import java.io.Serializable;
import java.util.*;

public class Container implements Serializable {
    static final long serialVersionUID = 1L;
    private String name;
    private double maxCapacity;
    private double waterLevel;


    private Container(String name, double maxCapacity, double waterLevel) {
        this.name = name;
        this.maxCapacity = maxCapacity;
        this.waterLevel = waterLevel;
    }
    public Container create(String name, double maxCapacity, double waterLevel){
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
        if (maxCapacity < (qty + waterLevel)) {
            throw new RuntimeException("Too much water to add.");
        } else {
            waterLevel += qty;
        }
    }

    public void subtractWater(double qty) {
        if (waterLevel - qty < 0) {
            throw new RuntimeException("Too much water to subtract.");
        } else {
            waterLevel -= qty;
        }
    }

    public void transferWater(Container c, double qty) {
        c.subtractWater(qty);
        addWater(qty);
    }




}
