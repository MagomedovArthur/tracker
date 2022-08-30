package ru.job4j.search;

import java.util.ArrayList;
import java.util.function.Predicate;

public class PhoneDictionary {
    private ArrayList<Person> persons = new ArrayList<>();

    public void add(Person person) {
        this.persons.add(person);
    }

    /**
     * Вернуть список всех пользователей, который содержат key в любых полях.
     *
     * @param key Ключ поиска.
     * @return Список подощедщих пользователей.
     */

    public ArrayList<Person> find(String key) {
        Predicate<Person> combineAddress = k -> key.contains(k.getAddress());
        Predicate<Person> combinePhone = k -> key.contains(k.getPhone());
        Predicate<Person> combineSurname = k -> key.contains(k.getSurname());
        Predicate<Person> combineName = k -> key.contains(k.getName());
        Predicate<Person> combine = combineName.or(combineSurname).or(combinePhone).or(combineAddress);
        ArrayList<Person> result = new ArrayList<>();
        for (Person person : persons) {
            if (combine.test(person)) {
                result.add(person);
            }
        }
        return result;
    }
}