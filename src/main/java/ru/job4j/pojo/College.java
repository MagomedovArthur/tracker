package ru.job4j.pojo;

import java.util.Date;

public class College {
    public static void main(String[] args) {
        Student student = new Student();
        student.setFullName("Magomedov Usman Umarovich");
        student.setGroupNumber(25);
        student.setReceiptDate(new Date());

        System.out.println(student.getFullName() + " is a student of group №"
                + student.getGroupNumber() + ", date of admission is: "
                + student.getReceiptDate());
    }
}
