package ru.job4j.oop;

public class VehicleMain {
    public static void main(String[] args) {
        Vehicle airplane = new Airplane();
        Vehicle train = new Train();
        Vehicle bus = new Bus();

        Vehicle[] vehicles = new Vehicle[]{airplane, train, bus};
        for (Vehicle a : vehicles) {
            a.move();
        }
    }
}
