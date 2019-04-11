package Exceptions;

import api.Exceptions;

public class AlreadyAddedToTheListException extends Exception {
    public AlreadyAddedToTheListException(String message) {
        super(message);
    }
}
