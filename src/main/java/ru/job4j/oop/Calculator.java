package ru.job4j.oop;

public class Calculator {

    private static int x = 5;

    public static int sum(int y) {
        return x + y;
    }

    public int multiply(int a) {
        return x * a;
    }

    public static int minus(int y) {
        return y - x;
    }

    public int divide(int y) {
        return y / x;
    }

    public int sumAllOperation(int c) {
        return sum(c) + multiply(c) + minus(c) + divide(c);
    }

    public static void main(String[] args) {
        int result = Calculator.sum(10);
        System.out.println(result);
        Calculator calculator = new Calculator();
        int rsl = calculator.multiply(5);
        System.out.println(rsl);
        System.out.println(Calculator.minus(100));
        Calculator calculatorOne = new Calculator();
        System.out.println(calculatorOne.divide(100));
        System.out.println(calculatorOne.sumAllOperation(100));
    }
}
