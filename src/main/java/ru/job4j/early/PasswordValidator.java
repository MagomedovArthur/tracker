package ru.job4j.early;

public class PasswordValidator {

    public static String validate(String password) {

        if (containStrings(password)) {
            throw new IllegalArgumentException("Password is too easy, please enter another one");
        }
        if (password.length() < 8 || password.length() > 32) {
            throw new IllegalArgumentException("Password length must be between 8 "
                    + "and 32 characters!");
        }
        if (password.equals(password.toUpperCase())) {
            throw new IllegalArgumentException("Password must contain lowercase character.");
        }
        if (password.equals(password.toLowerCase())) {
            throw new IllegalArgumentException("Password must contain uppercase character.");
        }
        if (!isDigit(password)) {
            throw new IllegalArgumentException("Password must contain at least one number");
        }
        if (!isSpecialSymbol(password)) {
            throw new IllegalArgumentException("Password must contain at least one special character");
        }
        return password;
    }

    private static boolean isDigit(String password) {
        for (char charsPass : password.toCharArray()) {
            if (Character.isDigit(charsPass)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isSpecialSymbol(String password) {
        for (char charsPass : password.toCharArray()) {
            if (!Character.isLetterOrDigit(charsPass)) {
                return true;
            }
        }
        return false;
    }

    private static boolean containStrings(String password) {
        String[] unnecessaryStrings = {"qwerty", "admin", "user", "12345", "password"};
        for (String str : unnecessaryStrings) {
            if (password.toLowerCase().contains(str)) {
                return true;
            }
        }
        return false;
    }
}