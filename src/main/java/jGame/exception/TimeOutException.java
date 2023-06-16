package jGame.exception;

/**
 * this exception will be thrown when the processing time is longer than setting timeOutTime
 */

public class TimeOutException extends RuntimeException {
    private String message = null;

    public TimeOutException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
