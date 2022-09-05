package ru.job4j.stream;

import java.util.stream.Stream;

public class GenerateCard {
    public static void main(String[] args) {
        Stream.of(Suit.values())
                .flatMap(s -> Stream.of(Value.values())
                        .map(v -> new Card(s, v)))
                .forEach(System.out::println);
    }
}