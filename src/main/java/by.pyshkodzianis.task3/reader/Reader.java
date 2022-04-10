package by.pyshkodzianis.task3.reader;

import by.pyshkodzianis.task3.exception.LogisticsBaseException;

import java.util.List;

public interface Reader {
    List<String> readAll(String path) throws LogisticsBaseException;
}

