package org.example;

import java.lang.reflect.Constructor;

/**
 * Фабрика зависимостей для создания объектов указанного типа.
 */
public class DependencyFactory {

    /**
     * Создает объект указанного типа с помощью его конструктора по умолчанию.
     *
     * @param type тип объекта, который необходимо создать
     * @param <T>  обобщенный тип создаваемого объекта
     * @return новый объект указанного типа, созданный с помощью конструктора по умолчанию, или null в случае ошибки
     */
    public <T> T createObject(Class<T> type) {
        try {
            Constructor<T> constructor = type.getDeclaredConstructor();
            return constructor.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}