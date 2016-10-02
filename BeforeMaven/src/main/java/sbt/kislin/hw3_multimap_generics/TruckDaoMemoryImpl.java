package sbt.kislin.hw3_multimap_generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Angel on 17.08.2016.
 */
public class TruckDaoMemoryImpl implements TruckDao {

    private List<Truck> mModelTrucksList = new ArrayList<>(Arrays.asList(
            new Truck<main.modelsOfTrucks>(1, main.modelsOfTrucks.Renault, 12000),
            new Truck<main.modelsOfTrucks>(2, main.modelsOfTrucks.Renault, 16500),
            new Truck<main.modelsOfTrucks>(3, main.modelsOfTrucks.Scania, 12300),
            new Truck<main.modelsOfTrucks>(4, main.modelsOfTrucks.Volvo, 14000)
    ));

    private List<Truck> mIDTrucksList = new ArrayList<>(Arrays.asList(
            new Truck<Long>(1, 2L, 12000),
            new Truck<Long>(2, 12L, 16500),
            new Truck<Long>(3, 34L, 12300),
            new Truck<Long>(4, 5L, 14000)
    ));

    @Override
    public List<Truck> getListOfTrucksById() {
        return mIDTrucksList;
    }

    @Override
    public List<Truck> getListOfTrucksByModel() {
        return mModelTrucksList;
    }
}
