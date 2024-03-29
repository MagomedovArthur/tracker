# Проект «Tracker»

## О проекте

Этот проект - консольное приложение. Пользователю отображается меню с возможностями программы.

Программа может:

1. Добавлять заявку.

1. Заменять заявку на новую заявку по ID.

1. Удалять заявку по ID.

1. Отображать список всех заявок.

1. Производить поиск по имени заявки.
2. Две модели хранения данных:
    - Хранение в оперативной памяти (реализация Store -> MemTracker);
    - Хранение в базе данных PostgreSQL:
      - Работа с базой через запросы JDBC (реализация Store -> SqlTracker);
      - Работа с базой через запросы HQL (реализация Store -> HbmTracker).

## Технологии

* Java 18;
* Hibernate 5;
* PostgreSQL;
* JDBC;
* Lombok;
* Log4j;
* Тесты: Junit5, Mockito. Работают c H2 db;
* Maven. Поддержка профилей test/prod;
* Liquibase.
