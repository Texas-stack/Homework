package org.example;

import org.example.DependencyFactory;
import org.example.SearchService;

import java.util.List;

/**
 * Класс, представляющий контекст интенсивного компонента, аналог Spring контекста.
 */
public class IntensiveContext {
    private final String basePackage;
    private final SearchService searchService;
    private final DependencyFactory dependencyFactory;

    /**
     * Конструктор для создания экземпляра класса IntensiveContext с указанием базового пакета.
     *
     * @param basePackage базовый пакет для сканирования классов с аннотацией @IntensiveComponent
     */
    public IntensiveContext(String basePackage) {
        this.basePackage = basePackage;
        this.searchService = new SearchService();
        this.dependencyFactory = new DependencyFactory();
    }

    /**
     * Получает объект указанного класса, аннотированного @IntensiveComponent, из контекста.
     *
     * @param type класс, объект которого необходимо получить
     * @param <T>  тип объекта
     * @return объект указанного класса из контекста
     * @throws RuntimeException если количество совпадающих классов не равно 1
     */
    public <T> T getObject(Class<T> type) {
        List<Class<?>> annotatedClasses = searchService.findClassesWithAnnotation(basePackage, IntensiveComponent.class);

        List<Class<?>> matchedClasses = annotatedClasses
                .stream()
                .filter(type::isAssignableFrom)
                .toList();

        if (matchedClasses.size() != 1) {
            throw new RuntimeException("Должен быть ровно один совпадающий класс");
        }

        Class<?> matchedClass = matchedClasses.getFirst();
        Object instance = dependencyFactory.createObject(matchedClass);

        return type.cast(instance);
    }
}