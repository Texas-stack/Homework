package org.example;

/**
 * Основной класс приложения, демонстрирующий использование IntensiveContext для получения объекта класса и вызова его метода.
 */
public class Main {
    /**
     * Точка входа в приложение.
     *
     * @param args аргументы командной строки (не используются)
     */
    public static void main(String[] args) {
        IntensiveContext context = new IntensiveContext("org.example");
        SomeClass1 class1 = context.getObject(SomeClass1.class);
        class1.run();
    }
}