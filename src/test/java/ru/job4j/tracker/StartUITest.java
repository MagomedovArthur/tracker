package ru.job4j.tracker;

import org.junit.Test;

import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class StartUITest {

    private final String ln = System.lineSeparator();

    @Test
    public void whenCreateItem() {
        Output output = new StubOutput();
        Input in = new StubInput(new String[]{"0", "Item name", "1"});
        Tracker tracker = new Tracker();
        UserAction[] actions = {new CreateAction(output), new ExitAction(output)};
        new StartUI(output).init(in, tracker, actions);
        assertThat(tracker.findAll()[0].getName(), is("Item name"));
    }

    @Test
    public void whenReplaceItem() {
        Output output = new StubOutput();
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("Replaced item"));
        String replacedName = "New item name";
        Input in = new StubInput(
                new String[]{"0", String.valueOf(item.getId()), replacedName, "1"}
        );
        UserAction[] actions = {
                new EditAction(output),
                new ExitAction(output)
        };
        new StartUI(output).init(in, tracker, actions);
        assertThat(tracker.findById(item.getId()).getName(), is(replacedName));
    }

    @Test
    public void whenDeleteItem() {
        Output output = new StubOutput();
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("Deleted item"));
        Input in = new StubInput(
                new String[]{"0", String.valueOf(item.getId()), "1"}
        );
        UserAction[] actions = {
                new DeleteAction(output),
                new ExitAction(output)
        };
        new StartUI(output).init(in, tracker, actions);
        assertThat(tracker.findById(item.getId()), is(nullValue()));
    }

    @Test
    public void whenExit() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[]{"0"}
        );
        Tracker tracker = new Tracker();
        UserAction[] actions = {
                new ExitAction(out)
        };
        new StartUI(out).init(in, tracker, actions);
        assertThat(out.toString(), is(
                "Menu:" + ln + "0. Exit" + ln
        ));
    }

    @Test
    public void whenFindAll() {
        Tracker tracker = new Tracker();
        Output output = new StubOutput();
        Item item = new Item("Item_1");
        tracker.add(item);
        Input input = new StubInput(new String[]{"0", "1"});
        UserAction[] actions = {
                new ShowAllItemsAction(output),
                new ExitAction(output)
        };
        String expected = "Menu:" + ln
                + "0. Show All Items" + ln
                + "1. Exit" + ln
                + "=== Show all items ===" + ln
                + item.toString() + ln
                + "Menu:" + ln
                + "0. Show All Items" + ln
                + "1. Exit" + ln;
        new StartUI(output).init(input, tracker, actions);
        assertThat(output.toString(), is(expected));
    }

    @Test
    public void findByName() {
        Tracker tracker = new Tracker();
        Output output = new StubOutput();
        Item item = new Item("Idea");
        tracker.add(item);
        Input input = new StubInput(new String[]{"0", "Idea", "1"});
        UserAction[] actions = {
                new FindItemByNameAction(output),
                new ExitAction(output)
        };
        String expected = "Menu:" + ln
                + "0. Find item by name" + ln
                + "1. Exit" + ln
                + "=== Find items by name ===" + ln
                + item.toString() + ln
                + "Menu:" + ln
                + "0. Find item by name" + ln
                + "1. Exit" + ln;
        new StartUI(output).init(input, tracker, actions);
        assertThat(output.toString(), is(expected));
    }

    @Test
    public void findById() {
        Tracker tracker = new Tracker();
        Output output = new StubOutput();
        Item item = new Item("Item_1");
        tracker.add(item);
        Input input = new StubInput(new String[]{"0", String.valueOf(item.getId()), "1"});
        UserAction[] actions = {
                new FindItemByIdAction(output),
                new ExitAction(output)
        };
        String expected = "Menu:" + ln
                + "0. Find item by Id" + ln
                + "1. Exit" + ln
                + "=== Find item by id ===" + ln
                + item.toString() + ln
                + "Menu:" + ln
                + "0. Find item by Id" + ln
                + "1. Exit" + ln;
        new StartUI(output).init(input, tracker, actions);
        assertThat(output.toString(), is(expected));
    }
}