package jGame.exception;

/**
 * this exception will be thrown in the class will a builder, when some must contain arguments are missing.
 */

public class BuilderException extends RuntimeException {
    private String message = null;

    public BuilderException(String message) {
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
