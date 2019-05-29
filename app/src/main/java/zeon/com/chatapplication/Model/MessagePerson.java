package zeon.com.chatapplication.Model;

import android.media.Image;
import android.widget.ImageView;

import zeon.com.chatapplication.R;

public class MessagePerson {

    private int id;
    private String name;
    private String message;
    private String date;
    private String image;
    private ImageView personImage;


    public MessagePerson(int ID, String s1, String s2, String s3, String s4){

        id = ID;
        name = s1;
        message = s2;
        date = s3;
       // personImage.setImageResource(R.drawable.man);
        image = s4;

    }

    public MessagePerson(){

        id = 0;
        name = "Unnamed";
        message = "";
        date = "";
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

    public String getS3() {
        return date;
    }

    public void setS3(String s3) {
        this.date = s3;
    }

    public String getS4() {
        return image;
    }

    public void setS4(String s4) {
        this.image = s4;
    }


}

