package by.pyshkodzianis.task3.entity;

import java.util.concurrent.atomic.AtomicBoolean;

public class Ramp {
    private final int numberRamp;
    private AtomicBoolean isBusy = new AtomicBoolean();

    public Ramp(int numberRamp) {
        this.numberRamp = numberRamp;
    }

    public int getNumberRamp() {
        return numberRamp;
    }

    public boolean isBusy() {
        return isBusy.get();
    }

    public void setBusy(boolean isBusy) {
        this.isBusy.set(isBusy);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((isBusy == null) ? 0 : isBusy.hashCode());
        result = prime * result + numberRamp;
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
        Ramp other = (Ramp) obj;
        if (isBusy == null) {
            if (other.isBusy != null)
                return false;
        } else if (!isBusy.equals(other.isBusy))
            return false;
        if (numberRamp != other.numberRamp)
            return false;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Ramp [numberRamp=");
        builder.append(numberRamp);
        builder.append(", isBusy=");
        builder.append(isBusy);
        builder.append("]");
        return builder.toString();
    }
}
