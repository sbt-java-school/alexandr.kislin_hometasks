package sbt.kislin;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by axelk on 13.10.2016.
 */
public class ConverterImpl implements Converter {
    private Map<Class,ConverterTo> converters = new HashMap<>();


    @Override
    public <T> T convert(Objects valueFrom, Class<T> resultClass) {
        ConverterTo<T> converterTo = converters.get(resultClass);
        return converterTo.convert(valueFrom);
    }

    @Override
    public <T> void addConverter(Class<T> resultClass, ConverterTo<T> converterTo) {

    }

    @Override
    public <T> void removeConverter(Class<T> resultClass) {

    }
}
