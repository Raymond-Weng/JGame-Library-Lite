package jGame.exception;

public class BuilderException extends Exception {
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
