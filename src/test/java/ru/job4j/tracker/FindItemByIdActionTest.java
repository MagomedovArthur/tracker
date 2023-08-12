package ru.job4j.tracker;

import org.junit.jupiter.api.Test;

import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FindItemByIdActionTest {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MMMM-EEEE-yyyy HH:mm:ss");

    @Test
    public void execute() {
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        tracker.add(new Item("Item1"));
        tracker.add(new Item("Item2"));
        FindItemByIdAction findItemByIdAction = new FindItemByIdAction(out);

        Input input = mock(Input.class);

        when(input.askInt(any(String.class))).thenReturn(2);

        findItemByIdAction.execute(input, tracker);

        String ln = System.lineSeparator();
        String result = String.format("Item{id=%s, name='%s', created=%s}",
                tracker.findById(2).getId(), tracker.findById(2).getName(), tracker.findById(2).getCreated().format(FORMATTER));
        assertThat(out.toString()).isEqualTo("=== Find item by id ===" + ln + result + ln);
        assertThat(tracker.findById(2).getName()).isEqualTo("Item2");
    }
}