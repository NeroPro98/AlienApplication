package zeon.com.chatapplication.Model;

//import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;

public class Group {
    private String name;
    private String id;
    private ArrayList<UserProfile> members;
    private ArrayList<String> admins;

    public String getName() {
        return name;
    }
    public void setName(String name) {
         this.name = name;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
}
