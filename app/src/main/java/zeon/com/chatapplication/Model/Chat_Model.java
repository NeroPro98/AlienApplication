package zeon.com.chatapplication.Model;

public class Chat_Model {

    private int id;
    private int unequid;
    private String message;
    private boolean any;


    public Chat_Model(int id, int unequid, String message, boolean any) {
        this.id = id;
        this.unequid = unequid;
        this.message = message;
        this.any = any;
    }

    public Chat_Model() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUnequid() {
        return unequid;
    }

    public void setUnequid(int unequid) {
        this.unequid = unequid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean getAny() {
        return any;
    }

    public void setAny(boolean any) {
        this.any = any;
    }
}
