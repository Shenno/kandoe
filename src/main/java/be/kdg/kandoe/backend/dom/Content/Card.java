package Content;

/**
 * Created by Len on 10-2-2016.
 */
public class Card {
    private String text;
    private String imageURL;
    private boolean isPicked;
    private int priority;
    private String snapShotID;

    public Card(String text, String imageURL, boolean isPicked, int priority, String snapShotID) {
        this.text = text;
        this.imageURL = imageURL;
        this.isPicked = isPicked;
        this.priority = priority;
        this.snapShotID = snapShotID;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public boolean isPicked() {
        return isPicked;
    }

    public void setIsPicked(boolean isPicked) {
        this.isPicked = isPicked;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getSnapShotID() {
        return snapShotID;
    }

    public void setSnapShotID(String snapShotID) {
        this.snapShotID = snapShotID;
    }
}
