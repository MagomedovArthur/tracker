package ru.job4j.oop;

public class Error {

    boolean active;
    int status;
    String massage;

    public Error() {
    }

    public Error(boolean active, int status, String massage) {
        this.active = active;
        this.status = status;
        this.massage = massage;
    }

    public void printInfo() {
        System.out.println("Активность ошибки: " + active);
        System.out.println("Статус ошибки: " + status);
        System.out.println("Сообщение об ошибке: " + massage);
    }

    public static void main(String[] args) {
        Error noError = new Error();
        noError.printInfo();
        Error error404 = new Error(true, 404, "Страница не найдена.");
        error404.printInfo();
        Error error502 = new Error(true, 502, "Сервер не отвечает.");
        error502.printInfo();
    }
}
