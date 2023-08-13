package ru.job4j.tracker;

import org.junit.jupiter.api.Test;

import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FindItemByNameActionTest {

    @Test
    public void execute() {
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        var result = tracker.add(new Item("Item1"));
        tracker.add(new Item("Item2"));
        FindItemByNameAction findItemByNameAction = new FindItemByNameAction(out);

        Input input = mock(Input.class);

        when(input.askStr(any(String.class))).thenReturn("Item1");

        findItemByNameAction.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(out.toString()).isEqualTo("=== Find items by name ===" + ln + result + ln);
        assertThat(tracker.findById(1).getName()).isEqualTo("Item1");
    }
}