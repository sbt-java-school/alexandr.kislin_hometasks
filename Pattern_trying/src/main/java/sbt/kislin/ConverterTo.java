package sbt.kislin;

import java.util.Objects;

/**
 * Created by axelk on 13.10.2016.
 */
public interface ConverterTo<T> {
    <T> T convert (Objects valueFrom);
}
