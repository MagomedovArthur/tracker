package ru.job4j.tracker;

import org.junit.Assert;
import org.junit.jupiter.api.Test;


import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ItemAscByNameTest {

    @Test
    void itemAscByName() {
        List<Item> items = Arrays.asList(
                new Item(2, "I2"),
                new Item(3, "I3"),
                new Item(1, "I1")
        );
        items.sort(new ItemAscByName());
        List<Item> expected = Arrays.asList(
                new Item(1, "I1"),
                new Item(2, "I2"),
                new Item(3, "I3")
        );
        Assert.assertEquals(expected, items);
    }
}