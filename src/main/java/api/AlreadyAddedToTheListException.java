package api;

public class AlreadyAddedToTheListException extends Exception {
    public AlreadyAddedToTheListException(String message) {
        super(message);
    }
}
