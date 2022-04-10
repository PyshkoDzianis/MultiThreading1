package by.pyshkodzianis.task3.entity;

import by.pyshkodzianis.task3.exception.LogisticsBaseException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Truck extends Thread {
    public static Logger logger = LogManager.getLogger();
    private final int numberTruck;
    private TypeOfGoods typeOfGoods;

    public Truck(int numberTruck, TypeOfGoods typeOfGoods) {
        super();
        this.numberTruck = numberTruck;
        this.typeOfGoods = typeOfGoods;
        if (typeOfGoods == TypeOfGoods.PERISHABLE) {
            this.setPriority(MAX_PRIORITY);
        } else {
            this.setPriority(MIN_PRIORITY);
        }
    }

    public TypeOfGoods getTypeOfGoods() {
        return typeOfGoods;
    }

    public void setTypeOfGoods(TypeOfGoods typeOfGoods) {
        this.typeOfGoods = typeOfGoods;
        if (typeOfGoods == TypeOfGoods.PERISHABLE) {
            this.setPriority(MAX_PRIORITY);
        } else {
            this.setPriority(MIN_PRIORITY);
        }
    }

    public int getNumberTruck() {
        return numberTruck;
    }

    @Override
    public void run() {
        LogisticsBase logisticsBase = LogisticsBase.getLogisticsBase();
        Ramp ramp = null;
        try {
            ramp = logisticsBase.getRamp(this);
            if (typeOfGoods == TypeOfGoods.EMPTY) {
                loadGoods();
            } else {
                unloadGoods();
            }

        } catch (InterruptedException | LogisticsBaseException e) {
            this.interrupt();
            logger.log(Level.ERROR, "Current thread " + this.toString() + " was interrupted");
        } finally {
            if (ramp != null) {
                logisticsBase.releaseRamp(this, ramp);
            }
        }
    }

    private void unloadGoods() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(new Random().nextInt(500));
        typeOfGoods = TypeOfGoods.EMPTY;
        logger.log(Level.INFO, this.toString() + " unloaded goods");
    }

    private void loadGoods() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(new Random().nextInt(500));
        typeOfGoods = TypeOfGoods.NORMAL;
        logger.log(Level.INFO, this.toString() + " loaded goods");
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + numberTruck;
        result = prime * result + ((typeOfGoods == null) ? 0 : typeOfGoods.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Truck other = (Truck) obj;
        if (numberTruck != other.numberTruck)
            return false;
        if (typeOfGoods != other.typeOfGoods)
            return false;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Truck [numberTruck=");
        builder.append(numberTruck);
        builder.append(", typeOfGoods=");
        builder.append(typeOfGoods);
        builder.append("]");
        return builder.toString();
    }

    public enum TypeOfGoods {
        NORMAL, PERISHABLE, EMPTY
    }
}