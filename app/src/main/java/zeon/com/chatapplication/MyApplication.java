package zeon.com.chatapplication;

import android.content.Context;
import android.graphics.Bitmap;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;

import zeon.com.chatapplication.Model.UserProfile;


public class MyApplication extends android.app.Application {
    UserProfile user;
    Socket connection;
    int Color;
    boolean signedIn;
    Bitmap image;
    String User_Name;
    String User_Email;
    String Specific_Email_Press;
    ArrayList <String> FriendEmails = new ArrayList<>();
    ArrayList<String> Emails = new ArrayList<String>();


    public ArrayList<String> getFriendEmails() {
        return FriendEmails;
    }

    public void setFriendEmails(ArrayList<String> friendEmails) {
        FriendEmails = friendEmails;
    }



    public ArrayList<String> getEmails() {
        return Emails;
    }

    public String getUser_Name() {
        return User_Name;
    }

    public void setUser_Name(String user_Name) {
        User_Name = user_Name;
    }




    private static Context mContext;

    public static Context getmContext() {
        return mContext;
    }

    public String getSpecific_Email_Press() {
        return Specific_Email_Press;
    }

    public void setSpecific_Email_Press(String specific_Email_Press) {
        Specific_Email_Press = specific_Email_Press;
    }


    public String getEmails(int i) {
        return Emails.get(i);
    }

    public void setEmails(ArrayList<String> emails) {
        Emails = emails;
    }



    public String getUser_Email() {
        return User_Email;
    }

    public void setUser_Email(String user_Email) {
        System.out.println("Email is get "+ User_Email);
        user.setEmail(user_Email);
        User_Email = user_Email;
    }



    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public Socket getConnection() {
        return connection;
    }

    public int getColor() {
        return Color;
    }

    public void setColor(int color) {
        Color = color;
    }



    public void setConnection(Socket connection) {
        this.connection = connection;
    }



    public UserProfile getUser() {
        return user;
    }

    public void setUser(UserProfile user) {
        this.user = user;
    }

    public MyApplication()
    {
        super();
        user = new UserProfile();
        signedIn = false;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
     //   user = new UserProfile();
   //     checkTheInternalFile();  //here the file we read this before the GUI work
    }
    public void checkTheInternalFile()
    {
        String fileName = "myfile";
        FileInputStream fileInputStream;
        try {
            fileInputStream = openFileInput(fileName);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            UserProfile test = (UserProfile) objectInputStream.readObject();
            if (!(test.getPassword().length() ==0)) {
                user = test;
                signedIn= true;
                objectInputStream.close();
                fileInputStream.close();
                System.out.println(user.getUserName() + "  " + user.getPassword() + "  " + user.getEmail());
           }
            else
            {
                user = new UserProfile();
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }




}
