package ru.job4j.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FilterNegativeNumbers {

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(-1, 3, 54, -3, -11, 32);
        List<Integer> positive = numbers.stream().filter(num -> num > 0).collect(Collectors.toList());
        positive.forEach(System.out::println);
    }
}