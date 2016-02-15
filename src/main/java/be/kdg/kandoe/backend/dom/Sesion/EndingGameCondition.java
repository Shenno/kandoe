package Sesion;

/**
 * Created by Len on 10-2-2016.
 */
public class EndingGameCondition {
    private boolean reachConclusion;
    private boolean maxRounds;

    public EndingGameCondition(boolean reachConclusion, boolean maxRounds) {
        this.reachConclusion = reachConclusion;
        this.maxRounds = maxRounds;
    }

    public boolean isReachConclusion() {
        return reachConclusion;
    }

    public void setReachConclusion(boolean reachConclusion) {
        this.reachConclusion = reachConclusion;
    }

    public boolean isMaxRounds() {
        return maxRounds;
    }

    public void setMaxRounds(boolean maxRounds) {
        this.maxRounds = maxRounds;
    }
}
