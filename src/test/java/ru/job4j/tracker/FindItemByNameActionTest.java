package ru.job4j.tracker;

import org.junit.jupiter.api.Test;

import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FindItemByNameActionTest {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MMMM-EEEE-yyyy HH:mm:ss");

    @Test
    public void execute() {
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        tracker.add(new Item("Item1"));
        tracker.add(new Item("Item2"));
        FindItemByNameAction findItemByIdAction = new FindItemByNameAction(out);

        Input input = mock(Input.class);

        when(input.askStr(any(String.class))).thenReturn("Item1");

        findItemByIdAction.execute(input, tracker);

        String ln = System.lineSeparator();
        String result = String.format("Item{id=%s, name='%s', created=%s}",
                tracker.findById(1).getId(), tracker.findById(1).getName(), tracker.findById(1).getCreated().format(FORMATTER));
        assertThat(out.toString()).isEqualTo("=== Find items by name ===" + ln + result + ln);
        assertThat(tracker.findById(1).getName()).isEqualTo("Item1");
    }
}