package be.kdg.kandoe.backend.services.exceptions;

/**
 * ContentServiceException is a specific Exception for exceptions in the {link: ContentService}
 */
public class ContentServiceException extends RuntimeException{
    public ContentServiceException(String message) {
        super(message);
    }
}
