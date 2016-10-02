package sbt.kislin.hw3_multimap_generics;

import java.util.List;

/**
 * Created by Angel on 17.08.2016.
 */
public class main {
    public static enum modelsOfTrucks {Renault, Volvo, Scania, Kamaz}

    public static void main(String[] args) {
        TruckDao truckDao = new TruckDaoMemoryImpl();

        TruckRegistr<modelsOfTrucks, Truck> registrByModel = new TruckRegistrByModel();
        System.out.println(truckDao.getListOfTrucksByModel());

        for (Truck truck : truckDao.getListOfTrucksByModel()) {
            registrByModel.put((modelsOfTrucks) truck.getModel(), truck); // Здесь есть каст, а должен ли он быть, либо я нарушил суть?
        }
        List<Truck> result = registrByModel.get(modelsOfTrucks.Renault); // достаем реношки, но можно сделать запрос с клавы
        if (result != null) {
            for (Truck truck : result) {
                System.out.println(truck.toString());
            }
        }
        else {
            System.out.println("we havent this model");
        }

        TruckRegistrByGeneric<Long, Truck> truckRegistrByGeneric  = new TruckRegistrByGeneric<>();
        for (Truck truck: truckDao.getListOfTrucksById()) {
            truckRegistrByGeneric.put((Long) truck.getModel(),truck); // опять каст
        }
        System.out.println("Truck with id 2L:");
        List<Truck> truckById = truckRegistrByGeneric.get(2L);
        for (Truck truck: truckById) {
            System.out.println(truck);
        }
    }
}
