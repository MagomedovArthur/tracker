package ru.job4j.stream;

import java.util.List;
import java.util.stream.Collectors;

public class ProductLabel {
    public List<String> generateLabels(List<Product> products) {
        return products.stream()
                .filter(t -> t.getStandard() - t.getActual() >= 0)
                .filter(r -> r.getStandard() - r.getActual() <= 3)
                .map(p -> new Label(p.getName(), p.getPrice() / 2))
                .map(p -> p.toString()).collect(Collectors.toList());
    }
}