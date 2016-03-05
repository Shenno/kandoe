package be.kdg.kandoe.frontend.controllers.resources.sessions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kevin on 5/03/2016.
 */
public class SessionResourcePost implements Serializable {
    /*(public participantsEmails: string[],
                public cardIds: number[],
                public themeId: number)*/

    private List<String> participantsEmails;
    private List<Integer> cardIds;
    private Integer themeId;

    public SessionResourcePost() {
        this.participantsEmails = new ArrayList<>();
        this.cardIds = new ArrayList<>();
    }

    public Integer getThemeId() {
        return themeId;
    }

    public void setThemeId(Integer themeId) {
        this.themeId = themeId;
    }

    public List<Integer> getCardIds() {
        return cardIds;
    }

    public void setCardIds(List<Integer> cardsIds) {
        this.cardIds = cardsIds;
    }

    public List<String> getParticipantsEmails() {
        return participantsEmails;
    }

    public void setParticipantsEmails(List<String> participantsEmails) {
        this.participantsEmails = participantsEmails;
    }
}
