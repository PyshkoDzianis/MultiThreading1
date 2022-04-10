package by.pyshkodzianis.task3.entity;

import by.pyshkodzianis.task3.exception.LogisticsBaseException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class LogisticsBase {
    public static Logger logger = LogManager.getLogger();
    private static LogisticsBase logisticsBase;
    private static AtomicBoolean logisticsBaseCreated = new AtomicBoolean();
    private static ReentrantLock lock = new ReentrantLock();
    private static final int NUMBER_OF_RAMPS = 4;
    private Semaphore semaphore = new Semaphore(NUMBER_OF_RAMPS);
    private List<Ramp> ramps = new ArrayList<Ramp>();

    private LogisticsBase() {
        for (int i = 0; i < NUMBER_OF_RAMPS; i++) {
            Ramp ramp = new Ramp(i);
            ramps.add(ramp);
        }
    }

    public static LogisticsBase getLogisticsBase() {
        if (!logisticsBaseCreated.get()) {
            lock.lock();
            if (!logisticsBaseCreated.get()) {
                logisticsBase = new LogisticsBase();
                logisticsBaseCreated.set(true);
            }
            lock.unlock();
        }
        return logisticsBase;
    }

    public Ramp getRamp(Truck truck) throws LogisticsBaseException {
        if (truck == null) {
            throw new LogisticsBaseException("truck is null");
        }
        Ramp ramp;
        logger.log(Level.INFO, truck.toString() + " arrived at the logistics base and took the line");
        try {
            semaphore.acquire();
            lock.lock();
            int numberRamp = 0;
            do {
                ramp = ramps.get(numberRamp);
                numberRamp++;
            } while (ramp.isBusy());
            ramp.setBusy(true);
            logger.log(Level.INFO, truck.toString() + " took up the ramp " + ramp.getNumberRamp());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new LogisticsBaseException("Current thread " + truck.toString() + " was interrupted");
        } finally {
            lock.unlock();
        }
        return ramp;
    }

    public void releaseRamp(Truck truck, Ramp ramp) {
        ramp.setBusy(false);
        logger.log(Level.INFO, truck.toString() + " left the ramp " + ramp.getNumberRamp());
        semaphore.release();
    }
}