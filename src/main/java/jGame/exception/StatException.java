package jGame.exception;

/**
 * this will be thrown when there is some error when the methods in the class Stat are used
 */
public class StatException extends RuntimeException {
    private String message = null;

    public StatException(String message) {
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
