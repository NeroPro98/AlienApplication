package zeon.com.chatapplication;

import android.app.Application;
import android.content.res.Configuration;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.net.Socket;

import zeon.com.chatapplication.Model.UserProfile;

public class MyApplication extends Application {
    UserProfile user;
    Socket connection;

    public Socket getConnection() {
        return connection;
    }

    public void setConnection(Socket connection) {
        this.connection = connection;
    }

    boolean signedIn;

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
        checkTheInternalFile();
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
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }

}
