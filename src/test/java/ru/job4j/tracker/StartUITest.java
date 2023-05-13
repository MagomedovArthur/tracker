package ru.job4j.tracker;

import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class StartUITest {

    private final String ln = System.lineSeparator();

    @Test
    public void whenCreateItem() {
        Output output = new StubOutput();
        Input in = new StubInput(new String[]{"0", "Item name", "1"});
        MemTracker tracker = new MemTracker();
        List<UserAction> actions = asList(new CreateAction(output), new ExitAction(output));
        new StartUI(output).init(in, tracker, actions);
        assertThat(tracker.findAll().get(0).getName(), is("Item name"));
    }

    @Test
    public void whenReplaceItem() {
        Output output = new StubOutput();
        MemTracker tracker = new MemTracker();
        Item item = tracker.add(new Item("Replaced item"));
        String replacedName = "New item name";
        Input in = new StubInput(
                new String[]{"0", String.valueOf(item.getId()), replacedName, "1"}
        );
        List<UserAction> actions = asList(new EditAction(output),
                new ExitAction(output));
        new StartUI(output).init(in, tracker, actions);
        assertThat(tracker.findById(item.getId()).getName(), is(replacedName));
    }

    @Test
    public void whenDeleteItem() {
        Output output = new StubOutput();
        MemTracker tracker = new MemTracker();
        Item item = tracker.add(new Item("Deleted item"));
        Input in = new StubInput(
                new String[]{"0", String.valueOf(item.getId()), "1"}
        );
        List<UserAction> actions = asList(
                new DeleteAction(output),
                new ExitAction(output));
        new StartUI(output).init(in, tracker, actions);
        assertThat(tracker.findById(item.getId()), is(nullValue()));
    }

    @Test
    public void whenExit() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[]{"0"}
        );
        MemTracker tracker = new MemTracker();
        List<UserAction> actions = asList(
                new ExitAction(out));
        new StartUI(out).init(in, tracker, actions);
        assertThat(out.toString(), is(
                "Menu:" + ln + "0. Exit" + ln
        ));
    }

    @Test
    public void whenFindAll() {
        MemTracker tracker = new MemTracker();
        Output output = new StubOutput();
        Item item = new Item("Item_1");
        tracker.add(item);
        Input input = new StubInput(new String[]{"0", "1"});
        List<UserAction> actions = asList(
                new ShowAllItemsAction(output),
                new ExitAction(output));
        String expected = "Menu:" + ln
                + "0. Show All Items" + ln
                + "1. Exit" + ln
                + "=== Show all items ===" + ln
                + item + ln
                + "Menu:" + ln
                + "0. Show All Items" + ln
                + "1. Exit" + ln;
        new StartUI(output).init(input, tracker, actions);
        assertThat(output.toString(), is(expected));
    }

    @Test
    public void findByName() {
        MemTracker tracker = new MemTracker();
        Output output = new StubOutput();
        Item item = new Item("Idea");
        tracker.add(item);
        Input input = new StubInput(new String[]{"0", "Idea", "1"});
        List<UserAction> actions = asList(
                new FindItemByNameAction(output),
                new ExitAction(output));
        String expected = "Menu:" + ln
                + "0. Find item by name" + ln
                + "1. Exit" + ln
                + "=== Find items by name ===" + ln
                + item + ln
                + "Menu:" + ln
                + "0. Find item by name" + ln
                + "1. Exit" + ln;
        new StartUI(output).init(input, tracker, actions);
        assertThat(output.toString(), is(expected));
    }

    @Test
    public void findById() {
        MemTracker tracker = new MemTracker();
        Output output = new StubOutput();
        Item item = new Item("Item_1");
        tracker.add(item);
        Input input = new StubInput(new String[]{"0", String.valueOf(item.getId()), "1"});
        List<UserAction> actions = asList(
                new FindItemByIdAction(output),
                new ExitAction(output));
        String expected = "Menu:" + ln
                + "0. Find item by Id" + ln
                + "1. Exit" + ln
                + "=== Find item by id ===" + ln
                + item + ln
                + "Menu:" + ln
                + "0. Find item by Id" + ln
                + "1. Exit" + ln;
        new StartUI(output).init(input, tracker, actions);
        assertThat(output.toString(), is(expected));
    }

    @Test
    public void whenInvalidExit() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[]{"9", "0"}
        );
        MemTracker tracker = new MemTracker();
        List<UserAction> actions = asList(new ExitAction(out));
        new StartUI(out).init(in, tracker, actions);
        String ln = System.lineSeparator();
        assertThat(out.toString(), is(
                        "Menu:" + ln
                                + "0. Exit" + ln
                                + "Wrong input, you can select: 0 .. 0" + ln
                                + "Menu:" + ln
                                + "0. Exit" + ln
                )
        );
    }
}