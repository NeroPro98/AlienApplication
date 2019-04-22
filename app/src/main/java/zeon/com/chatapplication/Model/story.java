package zeon.com.chatapplication.Model;

public class story {

    private int id;
    private String image;
    private String addtext;
    private String datestory;

    public story(int id, String image, String addtext, String datestory) {
        this.id = id;
        this.image = image;
        this.addtext = addtext;
        this.datestory = datestory;
    }

    public story() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAddtext() {
        return addtext;
    }

    public void setAddtext(String addtext) {
        this.addtext = addtext;
    }

    public String getDatestory() {
        return datestory;
    }

    public void setDatestory(String datestory) {
        this.datestory = datestory;
    }
}
