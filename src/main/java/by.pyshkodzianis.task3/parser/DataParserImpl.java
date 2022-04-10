package by.pyshkodzianis.task3.parser;

import by.pyshkodzianis.task3.entity.Truck;
import by.pyshkodzianis.task3.exception.LogisticsBaseException;

import java.util.ArrayList;
import java.util.List;

public class DataParserImpl implements Parser {
    public static final String DATA_SPLIT = " ";

    @Override
    public List< Truck > parse(List<String> data) throws LogisticsBaseException {
        if (data == null) {
            throw new LogisticsBaseException("data is null");
        }
        List<Truck> trucks = new ArrayList<>();
        for (String lineData : data) {
            String[] numberTruckAndTypeGoods = lineData.split(DATA_SPLIT);
            int numberTruck = Integer.parseInt(numberTruckAndTypeGoods[0]);
            Truck.TypeOfGoods typeOfGoods = Truck.TypeOfGoods.valueOf(numberTruckAndTypeGoods[1]);
            Truck truck = new Truck(numberTruck, typeOfGoods);
            trucks.add(truck);
        }
        return trucks;
    }
}