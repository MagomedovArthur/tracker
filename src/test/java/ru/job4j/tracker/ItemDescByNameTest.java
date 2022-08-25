package ru.job4j.tracker;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ItemDescByNameTest {

    @Test
    void itemDescByName() {
        List<Item> items = Arrays.asList(
                new Item(2, "I2"),
                new Item(3, "I3"),
                new Item(1, "I1")
        );
        items.sort(new ItemDescByName());
        List<Item> expected = Arrays.asList(
                new Item(3, "I3"),
                new Item(2, "I2"),
                new Item(1, "I1")
        );
        Assert.assertEquals(expected, items);
    }
}