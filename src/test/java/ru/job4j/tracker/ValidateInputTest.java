package ru.job4j.tracker;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ValidateInputTest {

    @Test
    public void whenInvalidInput() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[]{"one", "1"}
        );
        ValidateInput input = new ValidateInput(out, in);
        int selected = input.askInt("Enter menu:");
        assertThat(selected, is(1));
    }

    @Test
    public void whenNegativeNumberInput() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[]{"-1"}
        );
        ValidateInput input = new ValidateInput(out, in);
        int selected = input.askInt("Wrong input, you can select: 0 .. 6");
        assertThat(selected, is(-1));
    }

    @Test
    public void whenRightNumberInput() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[]{"6"}
        );
        ValidateInput input = new ValidateInput(out, in);
        int selected = input.askInt("=== Show all items ===\n"
                + "Хранилище еще не содержит заявок\n");
        assertThat(selected, is(6));
    }

    @Test
    public void whenMultipleRightInput() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[]{"4", "5"}
        );
        ValidateInput input = new ValidateInput(out, in);
        int selected = input.askInt("=== Find item by id ===\n"
                + "Enter id:");
        assertThat(selected, is(4));
        selected = input.askInt("Заявка с введенным id: 3 не найдена.\n"
                + "Menu:");
        assertThat(selected, is(5));
    }
}