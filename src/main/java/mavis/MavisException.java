package mavis;

/**
 * MavisException is a custom exception class for handling errors specific to the Mavis application.
 * It extends the built-in {@link Exception} class.
 */
public class MavisException extends Exception {
    /**
     * Constructs a new MavisException with the specified detail message.
     * @param message The detail message, which is saved for later retrieval by the
     *     {@link Throwable#getMessage()} method.
     */
    public MavisException(String message) {
        super(message);
    }
}
