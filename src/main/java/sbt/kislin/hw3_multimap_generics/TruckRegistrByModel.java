package sbt.kislin.hw3_multimap_generics;

import java.util.*;

/**
 * Created by Angel on 17.08.2016.
 */
public class TruckRegistrByModel implements TruckRegistr<main.modelsOfTrucks, Truck> {

    private Map<main.modelsOfTrucks, List<Truck>> modelsOfTrucksMap = new TreeMap<>();

    @Override
    public List<Truck> get(main.modelsOfTrucks model) {
        List<Truck> thisMoodelTrucks = modelsOfTrucksMap.get(model);
        if (thisMoodelTrucks.isEmpty()) {
            System.out.println("trucks with this model not found");
        }
        return thisMoodelTrucks;
    }

    @Override
    public void put(main.modelsOfTrucks model, Truck value) {
        if (model == null || value == null) {
            throw new IllegalArgumentException("bad arguments given to put");
        }
        if (!modelsOfTrucksMap.containsKey(model)) {
            modelsOfTrucksMap.put(model, new ArrayList<>(Arrays.asList(value)));
        } else {
            modelsOfTrucksMap.get(model).add(value);
        }
    }
}
