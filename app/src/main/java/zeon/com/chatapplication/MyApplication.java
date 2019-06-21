package zeon.com.chatapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;

import zeon.com.chatapplication.Activity.Main_Chats_Page;
import zeon.com.chatapplication.Model.UserProfile;


public class MyApplication extends android.app.Application implements Serializable {
    public UserProfile user = new UserProfile();
    Socket connection;
    int Color;
    boolean signedIn;
    Bitmap image;
    String User_Name;
    String User_Email;
    String Specific_Email_Press;
    ArrayList <String> FriendEmails = new ArrayList<>();
    ArrayList<String> Emails = new ArrayList<String>();
    String currTalkingFriend;
    private ArrayList<Object> getUser_List_Every_init = new ArrayList<Object>();
    private ArrayList<Object> UserFriend_List_Every_init = new ArrayList<Object>();
    private ArrayList<Object> Helper_List = new ArrayList<>();
    private ArrayList<Object> The_User_List_Befor_Check;
    private int Fragment1_Counter=0;
    private String File_Name = "user_info";

    public int getFragment1_Counter() {
        return Fragment1_Counter;
    }

    public void setFragment1_Counter() {
        Fragment1_Counter ++;
    }



    public ArrayList<Object> getThe_User_List_From_Server() {
        return The_User_List_Befor_Check;
    }

    public void setThe_User_List_From_Server(ArrayList<Object> the_User_List_Befor_Check) {
        The_User_List_Befor_Check = the_User_List_Befor_Check;
    }



    public ArrayList<Object> getHelper_List() {
        return Helper_List;
    }

    public void setHelper_List(ArrayList<Object> helper_List) {
        Helper_List = helper_List;
    }




    public ArrayList<Object> getUserFriend_List_Every_init() {
        return UserFriend_List_Every_init;
    }

    public void setUserFriend_List_Every_init(ArrayList<Object> userFriend_List_Every_init) {
        UserFriend_List_Every_init = userFriend_List_Every_init;
    }



    public ArrayList<Object> getGetUser_List_Every_init() {
        return getUser_List_Every_init;
    }

    public void setGetUser_List_Every_init(ArrayList<Object> getUser_List_Every_init) {
        this.getUser_List_Every_init = getUser_List_Every_init;
    }



    public String getCurrTalkingFriend() {
        return currTalkingFriend;
    }

    public void setCurrTalkingFriend(String currTalkingFriend) {
        this.currTalkingFriend = currTalkingFriend;
    }

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
        MyApplication.mContext = getApplicationContext();
        Read_File_UserInfo();
        Sing_In();
     //   user = new UserProfile();
   //     checkTheInternalFile();  //here the file we read this before the GUI work
    }

    private void Sing_In() {
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                user.connect();
                boolean bool = false;
                ArrayList<Object> signlist = new ArrayList<>();
                signlist.add(1);
                signlist.add(user.getEmail());
                signlist.add(user.getPassword());
                try {
                    user.output.writeObject(signlist);
                    ArrayList<Object> inputlist =(ArrayList<Object>)user.input.readObject();
                    System.out.println("The inputList :"+inputlist);
                    user.output.flush();
                    bool = user.handleReceivedRequest(inputlist);
                    if(bool) {
                        Go_To_Chat_Page();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    public static Context getAppContext(){
        return MyApplication.mContext;
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

    public void Read_File_UserInfo() {
        FileInputStream fileInputStream;
        File file = new File(getFilesDir(), File_Name);

        if (file.exists()) {
            try {

                fileInputStream = openFileInput(File_Name);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                final UserProfile user_read = (UserProfile) objectInputStream.readObject();
                user = user_read;
                user_read.getEmail();
                user_read.getStory();
                user_read.getEmail();
                System.out.println("userRead:" + user_read);
                //InputList = user_read.getThe_User_Hwo_Chat_With_Him();
                objectInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }

    }

public void Go_To_Chat_Page(){

   // Toast.makeText(getAppContext(),"Welcome...",Toast.LENGTH_SHORT).show();
    Intent intent = new Intent(getAppContext(), Main_Chats_Page.class);
    startActivity(intent);
}

}
