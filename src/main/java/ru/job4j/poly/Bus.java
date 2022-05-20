package ru.job4j.poly;

public class Bus implements Transport {

    @Override
    public void drive() {
        System.out.println("The bus is going...");
    }

    @Override
    public void passengers(int count) {
        System.out.println("The bus has " + count + " passengers.");
    }

    @Override
    public double refuel(double liters) {
        return 46.70 * liters;
    }
}
