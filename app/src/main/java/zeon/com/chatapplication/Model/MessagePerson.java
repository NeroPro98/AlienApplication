package zeon.com.chatapplication.Model;

import android.media.Image;
import android.widget.ImageView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import zeon.com.chatapplication.R;

public class MessagePerson implements Serializable {

    private int id;
    private String name;
    private String message;
    private Date date;
    private String image;
    private String email;
    private ImageView personImage;
    private ArrayList<Chat_Model> ListChat = new ArrayList<>();

    public ArrayList<Chat_Model> getListChat() {
        return ListChat;
    }

    public void setListChat(ArrayList<Chat_Model> listChat) {
        ListChat = listChat;
    }

    public void setListChatadd(Chat_Model msg) {
        ListChat.add(msg);
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public MessagePerson(String temail, int ID, String s1, String s2, Date s3, String s4){

        email = temail;
        id = ID;
        name = s1;
        message = s2;
        date = s3;
       // personImage.setImageResource(R.drawable.man);
        image = s4;

    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public MessagePerson(){

        id = 0;
        name = "Unnamed";
        message = "";
        date = null;
        image="";

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getS1() {
        return name;
    }

    public void setS1(String s1) {
        this.name = s1;
    }

    public String getS2() {
        return message;
    }

    public void setS2(String s2) {
        this.message = s2;
    }

    public Date getS3() {
        return date;
    }

    public void setS3(Date s3) {
        this.date = s3;
    }

    public String getS4() {
        return image;
    }

    public void setS4(String s4) {
        this.image = s4;
    }


}

