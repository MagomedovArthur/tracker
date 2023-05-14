package ru.job4j.tracker;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.job4j.tracker.Item;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static org.assertj.core.api.Assertions.*;

public class SqlTrackerTest {

    private static Connection connection;

    @BeforeAll
    public static void initConnection() {
        try (InputStream in = new FileInputStream("db/liquibase_test.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")

            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @AfterAll
    public static void closeConnection() throws SQLException {
        connection.close();
    }

    @AfterEach
    public void wipeTable() throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("delete from items")) {
            statement.execute();
        }
    }

    @Test
    public void whenSaveItemAndFindByGeneratedIdThenMustBeTheSame() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item("item");
        tracker.add(item);
        assertThat(tracker.findById(item.getId())).isEqualTo(item);
    }

    @Test
    public void whenAddedItemAndThenRemovedIt() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item("item");
        tracker.add(item);
        tracker.delete(item.getId());
        assertThat(tracker.findById(item.getId())).isNull();
    }

    @Test
    public void whenEditItemAndFindByGeneratedIdThenMustBeTheSameAsEdited() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item("item");
        Item newItem = new Item("second_item");
        tracker.add(item);
        tracker.replace(item.getId(), newItem);
        assertThat(tracker.findById(item.getId())).isEqualTo(newItem);
    }

    @Test
    public void whenLookingForAllItems() {
        SqlTracker tracker = new SqlTracker(connection);
        Item itemOne = new Item("item1");
        Item itemTwo = new Item("item2");
        Item itemThree = new Item("item3");
        Item itemFour = new Item("item4");
        tracker.add(itemOne);
        tracker.add(itemTwo);
        tracker.add(itemThree);
        tracker.add(itemFour);
        List<Item> expected = List.of(
                new Item(itemOne.getId(), "item1", itemOne.getCreated()),
                new Item(itemTwo.getId(), "item2", itemTwo.getCreated()),
                new Item(itemThree.getId(), "item3", itemThree.getCreated()),
                new Item(itemFour.getId(), "item4", itemFour.getCreated())
        );
        assertThat(tracker.findAll()).isEqualTo(expected);
    }

    @Test
    public void whenSearchingByName() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item("первая заявка");
        Item item2 = new Item("вторая заявка");
        tracker.add(item);
        tracker.add(item2);
        List<Item> expected = List.of(
                new Item(item2.getId(), "вторая заявка", item2.getCreated())
        );
        assertThat(tracker.findByName("вторая заявка")).isEqualTo(expected);
    }
}