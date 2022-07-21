package ru.job4j.bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BankService {

    private final Map<User, List<Account>> users = new HashMap<>();

    public void addUser(User user) {
        if (!users.containsKey(user.getPassport())) {
            users.put(user, new ArrayList<Account>());
        }
    }

    public void addAccount(String passport, Account account) {
        User user = findByPassport(passport);
        if (user != null) {
            List<Account> value = users.get(user);
            if (!value.contains(account)) {
                value.add(account);
            }
        }
    }

    public User findByPassport(String passport) {
        User result = null;
        for (User user : users.keySet()) {
            if (passport.equals(user.getPassport())) {
                result = user;
                break;
            }
        }
        return result;
    }

    public Account findByRequisite(String passport, String requisite) {
        User user = findByPassport(passport);
        Account result = null;
        if (user != null) {
            List<Account> value = users.get(user);
            for (Account account : value) {
                if (account.getRequisite().equals(requisite)) {
                    result = account;
                    break;
                }
            }
        }
        return result;
    }

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