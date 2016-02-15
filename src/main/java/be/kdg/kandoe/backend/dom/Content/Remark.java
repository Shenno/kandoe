package Content;

import java.util.Date;

/**
 * Created by Len on 10-2-2016.
 */
public class Remark {

    private String text;
    private Date TimeStamp;

    public Remark(String text, Date timeStamp) {
        this.text = text;
        TimeStamp = timeStamp;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getTimeStamp() {
        return TimeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        TimeStamp = timeStamp;
    }
}
