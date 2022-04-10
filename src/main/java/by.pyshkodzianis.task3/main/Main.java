package by.pyshkodzianis.task3.main;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import by.pyshkodzianis.task3.entity.Truck;
import by.pyshkodzianis.task3.exception.LogisticsBaseException;
import by.pyshkodzianis.task3.parser.DataParserImpl;
import by.pyshkodzianis.task3.parser.Parser;
import by.pyshkodzianis.task3.reader.DataReaderImpl;
import by.pyshkodzianis.task3.reader.Reader;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Main {
    public static Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        try {

            Reader reader = new DataReaderImpl();
            List< String > data = reader.readAll("resources/truck.txt");
            Parser parser = new DataParserImpl();
            List< Truck > trucks = parser.parse(data);
            ExecutorService executorService = Executors.newFixedThreadPool(5);
            for (Truck truck : trucks) {
                executorService.execute(truck);
            }
        } catch (LogisticsBaseException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
    }
}
