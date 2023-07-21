package pl.kurs.models;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

public class OperationEvent implements Serializable {
    private static final long serialVersionUID = 1L;

    public enum OperationType {ADD, DRAIN}

    private Timestamp date;
    private Container container;
    private OperationType operationType;
    private double waterValue;
    private boolean wasSuccess;

    public OperationEvent(Timestamp date, Container container, OperationType operationType, double waterValue, boolean wasSuccess) {
        this.date = date;
        this.container = container;
        this.operationType = operationType;
        this.waterValue = waterValue;
        this.wasSuccess = wasSuccess;
    }

    public Timestamp getDate() {
        return date;
    }

    public Container getContainer() {
        return container;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public double getWaterValue() {
        return waterValue;
    }

    public boolean isWasSuccess() {
        return wasSuccess;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OperationEvent that = (OperationEvent) o;
        return Double.compare(that.waterValue, waterValue) == 0 && wasSuccess == that.wasSuccess && Objects.equals(date, that.date) && Objects.equals(container, that.container) && operationType == that.operationType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, container, operationType, waterValue, wasSuccess);
    }

    @Override
    public String toString() {
        return "OperationEvent{" +
                "date=" + date +
                ", container=" + container +
                ", operationType=" + operationType +
                ", waterValue=" + waterValue +
                ", wasSuccess=" + wasSuccess +
                '}';
    }
}
