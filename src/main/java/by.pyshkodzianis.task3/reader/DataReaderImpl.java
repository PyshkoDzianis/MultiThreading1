package by.pyshkodzianis.task3.reader;

import by.pyshkodzianis.task3.exception.LogisticsBaseException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DataReaderImpl implements Reader {
    public static Logger logger = LogManager.getLogger();

    @Override
    public List<String> readAll(String filePath) throws LogisticsBaseException {
        if (filePath == null) {
            throw new LogisticsBaseException("filePath is null");
        }
        Path path = Paths.get(filePath);
        List<String> data;
        try (Stream<String> streamLines = Files.lines(path)) {
            data = streamLines.collect(Collectors.toList());
        } catch (IOException e) {
            logger.log(Level.ERROR, "file " + filePath + " not found", e);
            throw new LogisticsBaseException("file " + filePath + " not found", e);
        }
        logger.log(Level.INFO, "read data from file : " + data);
        return data;
    }
}
