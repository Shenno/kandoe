package be.kdg.kandoe.backend.services.exceptions;
/**
 * SessionServiceException is a specific Exception for exceptions in the {link: SessionService}
 */
public class SessionServiceException extends RuntimeException {
    public SessionServiceException(String message) {
        super(message);
    }
}
