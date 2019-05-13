package zeon.com.chatapplication.Model;

import android.widget.ImageView;

public class FriendComp {

    private int id;
    private String name;
    private String ActiveState;
    private String image;
    private ImageView personImage;


    public FriendComp(int ID, String s1, String s3, String s4){

        id = ID;
        name = s1;
        ActiveState = s3;
        // personImage.setImageResource(R.drawable.man);
        image = s4;

    }

    public FriendComp(){

        id = 0;
        name = "Unnamed";
        ActiveState = "";
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

    public String getS3() {
        return ActiveState;
    }

    public void setS3(String s3) {
        this.ActiveState = s3;
    }

    public String getS4() {
        return image;
    }

    public void setS4(String s4) {
        this.image = s4;
    }


}





