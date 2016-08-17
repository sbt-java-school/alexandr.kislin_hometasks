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
        List<Truck> result = registrByModel.get(modelsOfTrucks.Kamaz); // достаем реношки, но можно сделать запрос с клавы
        for (Truck truck : result) {
            System.out.println(truck.toString());
        }
    }
}
