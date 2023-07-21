package pl.kurs;

import pl.kurs.models.Container;
import pl.kurs.services.ContainerServices;

public class Main {
    public static void main(String[] args) {
        Container c1 = Container.create("pierwszy", 10, 5);
        Container c2 = Container.create("drugi", 10, 5);

        c1.transferWater(c2, 100);
    }
}