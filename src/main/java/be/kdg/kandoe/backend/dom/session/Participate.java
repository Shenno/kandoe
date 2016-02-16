package Sesion;

/**
 * Created by Len on 10-2-2016.
 */
public class Participate {
    private boolean isRegistered=false;
    private boolean isOnTurn=false;

    public Participate(boolean isRegistered, boolean isOnTurn) {
        this.isRegistered = isRegistered;
        this.isOnTurn = isOnTurn;
    }

    public boolean isRegistered() {
        return isRegistered;
    }

    public void setIsRegistered(boolean isRegistered) {
        this.isRegistered = isRegistered;
    }

    public boolean isOnTurn() {
        return isOnTurn;
    }

    public void setIsOnTurn(boolean isOnTurn) {
        this.isOnTurn = isOnTurn;
    }
}
