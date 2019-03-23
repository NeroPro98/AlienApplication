package zeon.com.chatapplication;

import android.graphics.Bitmap;

import java.io.Serializable;

public class User_Information implements Serializable{

    private long User_Id;
    private String User_Email;
    private String User_Password;
    private Bitmap User_Image;
    private String FirstName;
    private String MiddleName;
    private String FinalName;


    public User_Information(){

        User_Id = 000000;
        User_Email = null;
        User_Password = null;
        User_Image = null;
        FirstName = null;
        MiddleName = null;
        FinalName = null;
    }

    public User_Information(long user_Id, String user_Email, String user_Password, /*Bitmap user_Image,*/String first,String middle,String final_name) {
        User_Id = user_Id;
        User_Email = user_Email;
        User_Password = user_Password;
       // User_Image = user_Image;
        FirstName = first;
        MiddleName = middle;
        FinalName = final_name;
    }

    public long getUser_Id() {
        return User_Id;
    }

    public String getUser_Email() {
        return User_Email;
    }

    public String getUser_Password() {
        return User_Password;
    }

    public Bitmap getUser_Image() {
        return User_Image;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getMiddleName() {
        return MiddleName;
    }

    public String getFinalName() {
        return FinalName;
    }

    public void setUser_Id(long user_Id) {
        User_Id = user_Id;
    }
}
