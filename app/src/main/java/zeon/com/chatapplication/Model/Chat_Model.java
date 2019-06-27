package zeon.com.chatapplication.Model;

import android.widget.ImageView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Chat_Model implements Serializable {

    private int id;
    private int unequid;
    private String message;
    private boolean any;
    private ArrayList<Message> list;
    private String myEmail;
    private String friendEmail;
    private Date msgdate;
    private ImageView imagesend;
    private messageType type;




    public Chat_Model(String FromEmail, String ToEmail, String message, boolean any, Date date, ImageView image,messageType typemsg) {
        //this.id = id;
        //this.unequid = unequid;
        this.myEmail = FromEmail;
        this.friendEmail = ToEmail;
        this.message = message;
        this.any = any;
        this.msgdate = date;
        this.imagesend = image;
        type =typemsg;
    }

    public Chat_Model() {
    }

    public messageType getType() {
        return type;
    }

    public void setType(messageType type) {
        this.type = type;
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

    public ArrayList<Message> getList() {
        return list;
    }

    public void setList(ArrayList<Message> list) {
        this.list = list;
    }

    public String getMyEmail() {
        return myEmail;
    }

    public void setMyEmail(String myEmail) {
        this.myEmail = myEmail;
    }

    public String getFriendEmail() {
        return friendEmail;
    }

    public void setFriendEmail(String friendEmail) {
        this.friendEmail = friendEmail;
    }

    public void addMessage(Message message)
    {
        list.add(message);
    }
}
