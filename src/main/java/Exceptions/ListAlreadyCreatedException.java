package Exceptions;

public class ListAlreadyCreatedException extends Exception {
    /**
     * Constructs a new exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     *
     */
    public ListAlreadyCreatedException(String message) {
        super(message);
    }
}
