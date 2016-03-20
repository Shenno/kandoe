package be.kdg.kandoe.frontend.controllers.resources.sessions;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * RemarkResource is a Resource object for {link: Remark}
 */
public class RemarkResource implements Serializable {
    private String text;

    private LocalDateTime timeStamp;

    private String username;

    public RemarkResource() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
