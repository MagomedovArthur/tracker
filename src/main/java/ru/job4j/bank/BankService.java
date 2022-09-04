package ru.job4j.bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс банковской системы, содержащий методы для различных операций.
 *
 * @author Artur Magomedov
 * @version 1.0
 */
public class BankService {
    /**
     * Хранение пользователей осуществляется в Map.
     */
    private final Map<User, List<Account>> users = new HashMap<>();

    /**
     * Метод для добавления пользователя в систему.
     * Если пользователь с указанным номером паспорта уже сущетсвует,
     * то пользователь не добавится.
     *
     * @param user пользователь
     */
    public void addUser(User user) {
        if (!users.containsKey(user.getPassport())) {
            users.put(user, new ArrayList<Account>());
        }
    }

    /**
     * Метод добавления нового счета пользователю.
     * Проверяем, сущетсвует ли указанный пользователь.
     * Если пользователь существует, проверяем нет ли указанного счета у пользователя.
     *
     * @param passport паспорт
     * @param account  объект содержащий реквизиты и баланс пользователя
     */
    public void addAccount(String passport, Account account) {
        User user = findByPassport(passport);
        if (user != null) {
            List<Account> value = users.get(user);
            if (!value.contains(account)) {
                value.add(account);
            }
        }
    }

    /**
     * Метод поиска пользователя по паспорту.
     * Осуществляем перебор всех элементов Map.
     * Если указанный пользователь не найден, возвращем null.
     *
     * @param passport номер паспорта
     * @return объект типа User, состоящий из полей ИмяПользователя и Пасспорт
     */
    public User findByPassport(String passport) {
        return users.keySet()
                .stream()
                .filter(p -> p.getPassport().equals(passport))
                .findFirst()
                .orElse(null);
    }

    /**
     * Метод поиска счета пользователя по реквизитам.
     * Ищем пользователя по номеру паспорта.
     * Из всех счетов найденого пользователя ищем нужный счет по реквизитам.
     *
     * @param passport  паспорт
     * @param requisite реквизиты
     * @return возвращем объект типа Account
     */
    public Account findByRequisite(String passport, String requisite) {
        User user = findByPassport(passport);
        if (user != null) {
            return users.get(user)
                    .stream()
                    .filter(s -> s.getRequisite().equals(requisite))
                    .findFirst()
                    .orElse(null);
        }
        return null;
    }

    /**
     * Метод перечисления денег с одного счета на другой.
     * Если счет не найден или не хватает денег на счете, с которого переводят,
     * то метод не выполняется.
     *
     * @param srcPassport   номер паспорта пользователя с аккаунта которого переводят деньги
     * @param srcRequisite  реквизиты пользователя с которого переводят деньги
     * @param destPassport  номер паспорта пользователя которому переводят деньги
     * @param destRequisite реквизиты пользователя которому переводят деньги
     * @param amount        сумма перевода
     * @return возвращем true, если операция перевода была успешна, или false, если операция прервалась
     */
    public boolean transferMoney(String srcPassport, String srcRequisite,
                                 String destPassport, String destRequisite, double amount) {
        boolean rsl = false;
        Account srcRequisite1 = findByRequisite(srcPassport, srcRequisite);
        Account destRequisite1 = findByRequisite(destPassport, destRequisite);
        if (srcRequisite1 != null && amount <= srcRequisite1.getBalance() && destRequisite1 != null) {
            srcRequisite1.setBalance(srcRequisite1.getBalance() - amount);
            destRequisite1.setBalance(destRequisite1.getBalance() + amount);
            rsl = true;
        }
        return rsl;
    }
}