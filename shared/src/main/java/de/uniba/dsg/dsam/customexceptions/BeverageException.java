package de.uniba.dsg.dsam.customexceptions;


public class BeverageException  extends Exception  {

    public BeverageException() {
    }

    public BeverageException(String message) {
        super(message);
    }

    public BeverageException(Throwable cause) {
        super(cause);
    }

    public BeverageException(String message, Throwable cause) {
        super(message, cause);
    }
}
