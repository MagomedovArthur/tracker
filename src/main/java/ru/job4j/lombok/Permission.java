package ru.job4j.lombok;

import lombok.Builder;
import lombok.Getter;
import lombok.Singular;
import lombok.ToString;

import java.util.List;

@Builder(builderMethodName = "of")
@ToString
@Getter
public class Permission {

    private int id;
    private String name;

    @Singular("addRule")
    private List<String> rules;

    public static void main(String[] args) {
        var permission = Permission.of()
                .id(1)
                .name("Artur")
                .addRule("rule1")
                .addRule("rule2")
                .addRule("rule3")
                .build();
        System.out.println(permission);
    }
}