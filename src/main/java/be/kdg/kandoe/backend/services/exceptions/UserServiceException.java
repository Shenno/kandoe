package be.kdg.kandoe.backend.services.exceptions;

/**
 * UserServiceException is an specific Exception for exceptions in the {link: SessionService}
 */
public class UserServiceException extends RuntimeException
{
    // https://programmeren3-repaircafe.rhcloud.com/repair-cafe-applicatie/repair-cafe-frontend/presentation-layer/error-handling/

    /**
     * Deze exception wordt gesmeten wanneer iets fout gaat met gebruikers
     * Bijvoorbeeld: fout password of foute gebruikersnaam
     *
     * @param message
     */
    public UserServiceException(String message)
    {
        super(message);
    }
}
