package by.pyshkodzianis.task3.parser;

import by.pyshkodzianis.task3.entity.Truck;
import by.pyshkodzianis.task3.exception.LogisticsBaseException;

import java.util.List;

public interface Parser {
    List< Truck > parse(List<String> data) throws LogisticsBaseException;
}

