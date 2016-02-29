package be.kdg.kandoe.backend.services.exceptions;

public class SessionServiceException extends RuntimeException {
    public SessionServiceException(String message) {
        super(message);
    }
}
