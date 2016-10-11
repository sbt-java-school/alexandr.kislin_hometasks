package sbt.kislin.hw3_multimap_generics;

import java.util.List;

/**
 * Created by Angel on 17.08.2016.
 */
public interface TruckRegistr<T, V> {
    List<V> get(T type);

    void put(T type, V value);
}
