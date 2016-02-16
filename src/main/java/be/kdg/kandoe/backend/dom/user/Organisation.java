package be.kdg.kandoe.backend.dom.user;

/**
 * Created by Len on 10-2-2016.
 */
public class Organisation {
    private String Name;

    public Organisation(String name) {
        Name = name;
    }


    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
