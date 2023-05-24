package jGame.exception;

public class PriorityException extends Exception {
    private String message = null;

    public PriorityException(String message) {
        this.message = message;
    }

    @Override
    public String toString(){
        return message;
    }

    @Override
    public String getMessage(){
        return message;
    }
}
