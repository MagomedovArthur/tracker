package ru.job4j.stream;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Profiles {
    public static List<Address> collect(List<Profile> profiles) {
        return profiles.stream()
                .map(p -> new Profile(p.getAddress()))
                .map(p -> p.getAddress())
                .collect(Collectors.toList());
    }

    public static List<Address> collectSortWithoutDuplicate(List<Profile> profiles) {
        return profiles.stream()
                .map(p -> p.getAddress())
                .sorted(Address::compareTo)
                .distinct()
                .collect(Collectors.toList());
    }
}