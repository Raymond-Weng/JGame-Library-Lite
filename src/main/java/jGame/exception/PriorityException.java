package jGame.exception;

/**
 * this happens in the method with priority setting, when the value is unacceptable.
 */

public class PriorityException extends RuntimeException {
    private String message = null;

    public PriorityException(String message) {
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
