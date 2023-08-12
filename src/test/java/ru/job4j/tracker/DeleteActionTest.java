package ru.job4j.tracker;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.*;

class DeleteActionTest {

    @Test
    public void execute() {
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        tracker.add(new Item("New item"));
        DeleteAction deleteAction = new DeleteAction(out);

        Input input = mock(Input.class);

        when(input.askInt(any(String.class))).thenReturn(1);

        deleteAction.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(out.toString()).isEqualTo("=== Delete item ===" + ln + "Заявка удалена успешно." + ln);
    }
}