package org.example;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Сервис для поиска классов с определенной аннотацией в указанном пакете.
 */
public class SearchService {
    /**
     * Находит классы в указанном пакете, имеющие указанную аннотацию.
     *
     * @param basePackage  базовый пакет для поиска классов
     * @param annotation   класс аннотации, которой должны быть помечены найденные классы
     * @return список классов, удовлетворяющих условию
     */
    public List<Class<?>> findClassesWithAnnotation(String basePackage, Class<? extends Annotation> annotation) {
        Reflections reflections = new Reflections(new ConfigurationBuilder()
                .setUrls(ClasspathHelper.forPackage(basePackage))
                .filterInputsBy(new FilterBuilder().include(FilterBuilder.prefix(basePackage)))
                .setScanners(new SubTypesScanner(), new TypeAnnotationsScanner()));

        return new ArrayList<>(reflections.getTypesAnnotatedWith(annotation));
    }
}