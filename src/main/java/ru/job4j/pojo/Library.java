package ru.job4j.pojo;

public class Library {
    public static void main(String[] args) {
        Book bookOne = new Book("Clean code", 400);
        Book bookTwo = new Book("Pushkin", 300);
        Book bookThree = new Book("Dostaevskiy", 122);
        Book bookFour = new Book("Tolstoy", 700);
        Book[] books = new Book[4];
        books[0] = bookOne;
        books[1] = bookTwo;
        books[2] = bookThree;
        books[3] = bookFour;
        for (int index = 0; index < books.length; index++) {
            Book bk = books[index];
            System.out.println(bk.getName() + " - " + bk.getPages() + " pages");
        }
        Book booksReserve = books[0];
        books[0] = books[3];
        books[3] = booksReserve;
        for (int index = 0; index < books.length; index++) {
            Book bk = books[index];
            System.out.println(bk.getName() + " - " + bk.getPages() + " pages");
        }
        for (int index = 0; index < books.length; index++) {
            Book bk = books[index];
            if ("Clean code".equals(bk.getName())) {
                System.out.println(bk.getName() + " - " + bk.getPages() + " pages");
            }
        }
    }
}
