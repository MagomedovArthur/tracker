package ru.job4j.inheritance;

public class JSONReport extends TextReport {
    @Override
    public String generate(String name, String body) {
        return "{" + System.lineSeparator() + "\t" + "\"name\" : " + "\"" + name + "\"," + System.lineSeparator() + "\t" + "\"body\" : " + "\"" + body + "\"" + System.lineSeparator() + "}";
    }

    public static void main(String[] args) {
        JSONReport jsonReport = new JSONReport();
        System.out.println(jsonReport.generate("name", "body"));
    }
}
