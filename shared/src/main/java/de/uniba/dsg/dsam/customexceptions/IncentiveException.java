package de.uniba.dsg.dsam.customexceptions;


public class IncentiveException  extends Exception  {

    /**
     * Constructs a new exception with {@code null} as its detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     */
    public IncentiveException() {
    }

    public IncentiveException(String message) {
        super(message);
    }

    public IncentiveException(Throwable cause) {
        super(cause);
    }

    public IncentiveException(String message, Throwable cause) {
        super(message, cause);
    }
}
