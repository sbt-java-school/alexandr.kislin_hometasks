package sbt.kislin.hw3_multimap_generics;

import java.util.*;

/**
 * Created by Angel on 17.08.2016.
 */
public class TruckRegistrByModel implements TruckRegistr<main.modelsOfTrucks, Truck> {

    private Map<main.modelsOfTrucks, List<Truck>> modelsOfTrucksMap = new HashMap<>();

    @Override
    public List<Truck> get(main.modelsOfTrucks model) {
        List<Truck> thisMoodelTrucks = new ArrayList<>();
        try {
            thisMoodelTrucks = modelsOfTrucksMap.get(model);
        }
        catch (NullPointerException n) //возможно тут другой эксепшн тоже надо ловить?
        {
            System.out.println("We havent this model in base"); // это по-хорошему надо в лог, но мы его ещё не проходили
            return null;
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
