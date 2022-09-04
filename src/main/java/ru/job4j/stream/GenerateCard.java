package ru.job4j.stream;

import java.util.stream.Stream;

public class GenerateCard {
    public static void main(String[] args) {
        Suit[] suits = {
                Suit.Diamonds,
                Suit.Hearts,
                Suit.Spades,
                Suit.Clubs
        };
        Value[] values = {
                Value.V_6,
                Value.V_7,
                Value.V_8
        };
        Stream.of(suits)
                .flatMap(s -> Stream.of(values)
                        .map(v -> s + " " + v))
                .forEach(System.out::println);
    }
}
