package ru.job4j.collection;

import java.util.HashSet;

public class UniqueText {
    public static boolean isEquals(String originText, String duplicateText) {
        boolean rsl = true;
        String[] origin = originText.split(" ");
        String[] text = duplicateText.split(" ");
        HashSet<String> check = new HashSet<>();
        for (String stringArray : origin) {
            check.add(stringArray);
        }
        for (String originStrArray : text) {
            if (check.contains(originStrArray)) {
                break;
            } else {
                rsl = false;
            }
        }
        return rsl;
    }
}