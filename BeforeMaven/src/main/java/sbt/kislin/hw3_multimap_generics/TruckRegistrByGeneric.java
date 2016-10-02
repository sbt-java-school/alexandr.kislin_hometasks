package sbt.kislin.hw3_multimap_generics;

import java.util.*;

/**
 * Created by Angel on 18.08.2016.
 */
public class TruckRegistrByGeneric<T, V> implements TruckRegistr<T, V> {
    private Map<T, java.util.List<V>> truckRegistGenericMap = new HashMap<>();

    @Override
    public List<V> get(T type) {
        if (type == null) {
            throw new IllegalArgumentException("type is null");
        }
        List<V> items = truckRegistGenericMap.get(type);
        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("Havent this type");
        }
        return items;
    }

    @Override
    public void put(T type, V value) {
        if (type == null || value == null){
            throw new IllegalArgumentException("bad arguments given to put");
        }
        if (truckRegistGenericMap.containsKey(type)) {
            truckRegistGenericMap.get(type).add(value);
        }
        else{
            truckRegistGenericMap.put(type, new ArrayList<>(Arrays.asList(value)));
        }

    }
}
