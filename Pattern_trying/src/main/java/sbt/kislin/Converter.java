package sbt.kislin;

import java.util.Objects;

/**
 * Created by axelk on 13.10.2016.
 */
public interface Converter {
//    Integer, Long, Float, Double, BigDecimal;
    <T> T convert (Objects valueFrom, Class<T> resultClass);
    <T> void addConverter(Class<T> resultClass, ConverterTo<T> converterTo);
    <T> void removeConverter(Class<T> resultClass);
}

