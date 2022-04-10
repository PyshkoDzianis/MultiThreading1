package by.pyshkodzianis.task3.exception;

public class LogisticsBaseException extends Exception {


    public LogisticsBaseException() {
        super();
    }

    public LogisticsBaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public LogisticsBaseException(String message) {
        super(message);
    }

    public LogisticsBaseException(Throwable cause) {
        super(cause);
    }
}