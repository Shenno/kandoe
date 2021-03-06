package be.kdg.kandoe.backend.services.exceptions;

/**
 * UserServiceException is a specific Exception for exceptions in the {link: SessionService}
 */
public class UserServiceException extends RuntimeException
{
    public UserServiceException(String message)
    {
        super(message);
    }
}
