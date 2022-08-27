package ru.job4j.collection;

import java.util.Comparator;

public class LexSort implements Comparator<String> {

    @Override
    public int compare(String left, String right) {
        String[] arrayLeft = left.split(". ", 2);
        String[] arrayRight = right.split(". ", 2);
        return Integer.compare(Integer.parseInt(arrayLeft[0]),
                Integer.parseInt(arrayRight[0]));
    }
}