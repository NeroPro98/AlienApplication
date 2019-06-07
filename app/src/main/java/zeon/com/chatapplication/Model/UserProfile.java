package zeon.com.chatapplication.Model;

import android.app.FragmentManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ConnectException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;

import zeon.com.chatapplication.Chats;
import zeon.com.chatapplication.MyApplication;

public class UserProfile implements Serializable {
    private String userName;
    private String password;
    private String email;
    private Date birthDate;
    private ArrayList<String> userFriends;
    private ArrayList<String> blockList;
    private Date joinDate;
    private ArrayList<Chats> chatsList;
    private String userId;
    private String story;
    public transient ObjectOutputStream output;
    public transient ObjectInputStream input;
    private transient Socket Connection;
    private String IPString;
    private String PortString;
    private boolean signedIn;
    private onValueChangeListener valueChangeListener;
    private onValueChangeListener newMessagesListener;
    public ArrayList<String> User_Friend_Info = new ArrayList<>();
    private ArrayList<String> User_List;
    private ArrayList<String> User_Friend_Name = new ArrayList<>();


    private ArrayList<String> User_Friend_Emails = new ArrayList<>();

    public ArrayList<String> getUser_Friend_Name() {
        return User_Friend_Name;
    }

    public ArrayList<String> getUser_Friend_Emails() {
        return User_Friend_Emails;
    }

    public void setUser_Friend_Emails(String user_Friend_Emails) {
        User_Friend_Emails.add(user_Friend_Emails);
    }

    public void setUser_Friend_Name(String user_Friend_Name) {
        User_Friend_Name.add(user_Friend_Name);
    }




    public ArrayList<String> getUser_List() {
        return User_List;
    }

    public void setUser_List(ArrayList user_List) {
        User_List = user_List;
    }



    public ArrayList<String> getUser_Friend_Info() {
        return User_Friend_Info;
    }

    public void setUser_Friend_Info(String user_Friend_Info) {
        User_Friend_Info.add(user_Friend_Info);
    }

    public void setUser_Friend_Info_List(ArrayList user_Friend_Info) {
        User_Friend_Info = user_Friend_Info;
    }



 //   MyApplication data;
    private static Context mContext;

    public interface onValueChangeListener {
        void onChange();
    }

    public void setValueChangeListener(onValueChangeListener valueChangeListener) {
        this.valueChangeListener = valueChangeListener;
    }


    public onValueChangeListener getValueChangeListener() {
        return valueChangeListener;
    }

    public boolean isSignedIn() {
        return signedIn;
    }

    public void setSignedIn(boolean signedIn) {
        this.signedIn = signedIn;
        if (valueChangeListener != null) valueChangeListener.onChange();

    }

    public HandleThread handleThread;

    public Socket getConnection() {
        return Connection;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story2) {
        story = story2;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return userName;
    }

    public void setName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return password;
    }

    public void setUserPassword(String password) {
        this.password = password;
    }

    public UserProfile() {
        userName = "";
        password = "";
        email = "";
        birthDate = new Date();
        userFriends = new ArrayList<>();
        blockList = new ArrayList<>();
        joinDate = new Date();
        chatsList = new ArrayList<>();
        story = "";
        IPString = "10.0.2.2";
        PortString = "0000";
        handleThread = null;
    }

    public class HandleThread extends Thread {
        ArrayList<Object> List;

        public HandleThread(Socket clientSocket) {
            start();
        }

        @Override
        public void run() {
            super.run();
            try {
                List = (ArrayList<Object>) input.readObject();
                System.out.println(handleReceivedRequest(List));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }



        /*
        @Nullable
        @Override
        public IBinder onBind(Intent intent) {
            return null;
        }


        @Override
        public void onCreate() {
            super.onCreate();

            try {
                input = (ObjectInputStream) Connection.getInputStream();
                output = (ObjectOutputStream) Connection.getOutputStream();
                ArrayList<Object> list = new ArrayList<>();
                list.add(email);
                output.writeObject(list);
                output.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


        @Override
        public int onStartCommand(Intent intent, int flags, int startId) {
                try {

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            return super.onStartCommand(intent, flags, startId);
        }*/

    }

    public void connect() {
        try {
            connectToServer();
            SetupStreams();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Chats getChat(String friendEmail) {
        for (Chats c : chatsList) {
            if (c.getFriendEmail().equals(friendEmail))
                return c;
        }
        return null;
    }

    public boolean addFriend(UserProfile friend) {
        boolean searchInFriendList = searchInFriendList(friend.getUserName());
        if (!searchInFriendList)
            return false;
        userFriends.add(friend.getUserName());
        return true;
    }

    public boolean deleteFriend(String friendName) {
        boolean searchInFriendList = searchInFriendList(friendName);
        if (!searchInFriendList)
            return false;
        userFriends.remove(friendName);
        return true;
    }

    public boolean blockUser(String name) {
        boolean searchInBlockList = searchInBlockList(name);
        if (!searchInBlockList)
            return false;
        blockList.add(name);
        return true;
    }

    public boolean unblockUser(String name) {
        boolean searchInBlockList = searchInBlockList(name);
        if (!searchInBlockList)
            return false;
        blockList.remove(name);
        return true;
    }

    public boolean searchInFriendList(String name) {
        for (String str : userFriends) {
            if (str.equals(name))
                return true;
        }
        return false;
    }

    public boolean searchInBlockList(String name) {
        for (String str : blockList) {
            if (str.equals(name))
                return true;
        }
        return false;
    }

    public void handleCurrMessages() {
    }

    public ArrayList<Message> checkTheServer() {
        return null;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public onValueChangeListener getNewMessagesListener() {
        return newMessagesListener;
    }

    public void setNewMessagesListener(onValueChangeListener newMessagesListener) {
        this.newMessagesListener = newMessagesListener;
    }

    public void connectToServer() throws IOException {
        System.out.println("Connecting to Server");
        System.out.println("The Ip is " + IPString);
        Connection = new Socket(IPString, 6790);  // here we setup the connection to specific server of IP address to specific port on this server Port:
        System.out.println("Connected");
    }

    public void CloseCrap() {
        try {
            output.close();
            input.close();
            Connection.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void SetupStreams() throws IOException {
        output = new ObjectOutputStream(Connection.getOutputStream());
        output.flush();
        input = new ObjectInputStream(Connection.getInputStream());
        output.writeObject(null);
    }

    /*public boolean sendRequest(ArrayList<Object> arrayList) throws IOException, InterruptedException, ClassNotFoundException {
        connectToServer();
        SetupStreams();
        output.writeObject(arrayList);
        output.flush();
        Thread.sleep(2000);
       // request request1 = (zeon.com.chatapplication.Model.request) input.readObject();
        ArrayList<Object> list = (ArrayList<Object>) input.readObject();
        return handleReceivedRequest(list);
    }*/

    public ArrayList<String> getUserFriends() {
        return userFriends;
    }
    public String getUserFriendsByPos(int postion) {
        return userFriends.get(postion);
    }

    public void setUserFriends(ArrayList<String> userFriends) {
        this.userFriends = userFriends;
    }

    public void SetUserFriendsByPos(String FriendEmail) {
         userFriends.add(FriendEmail);
    }

    public ArrayList<String> getBlockList() {
        return blockList;
    }

    public void setBlockList(ArrayList<String> blockList) {
        this.blockList = blockList;
    }


    public boolean handleReceivedRequest(ArrayList<Object> list) {


        MyApplication app = (MyApplication)MyApplication.getAppContext().getApplicationContext();

        int type = (int) list.get(0);
        switch (type) {
            case 0://Add User
            {
                return (boolean) list.get(1);
            }
            case 1: //Sign in and bring the user friend and all user in the server
            {

                boolean res = (boolean) list.get(1);
                if (res) {

                    app.setUser_Email((String) list.get(2));
                    app.user.setEmail((String) list.get(2));
                    app.user.setPassword((String) list.get(3));
                    app.user.setUserName((String) list.get(4));
                    app.user.setUserFriends((ArrayList<String>) list.get(5));
                    app.user.setBlockList((ArrayList<String>) list.get(6));
                    list.remove(0);
                    list.remove(0);
                    list.remove(0);
                    list.remove(0);
                    list.remove(0);
                    list.remove(0);
                    list.remove(0);
                    int j = 0;
                    for(int i =0 ; i<list.size();i++){
                        if((list.get(i).equals("0"))) {
                            break;
                        }
                        else {
                            app.user.User_Friend_Info.add((String)list.get(i));
                            //app.user.setUser_Friend_Name((String)list.get(i));
                            j++;
                        }
                    }

                    for(int i =0 ; i<list.size();i =i+2){
                        if((list.get(i).equals("0"))) {
                            break;
                        }
                        else {
                            app.user.setUser_Friend_Name((String)list.get(i));
                        }
                    }
                    app.user.getUser_Friend_Info();
                    for(int i =0 ; i<j+1;i++) {
                        list.remove(0);
                    }
                    list.size();
                    app.user.setUser_List(list);

                    app.user.setSignedIn(true);
                    app.user.getUserFriends();
                    app.user.getBlockList();
                    app.user.getUser_Friend_Info();
                    app.user.getUser_List();
                } else
                    app.user.setSignedIn(false);
                return res;
            }
            case 2:// delete user
            {
                return (boolean) list.get(1);
            }
            case 3: { //update user
                return (boolean) list.get(1);
            }
            case 4://Send The list user
            {
                return (boolean) list.get(1);
            }
            case 5: //Edit Info of the User
            {
                return (boolean) list.get(1);
            }
            case 6://To Add Friend
            {
                return (boolean) list.get(1);
            }

            case 7: // Remove Friend
            {
                return (boolean) list.get(1);
            }
            case 8: //Block Friend
            {
                return (boolean) list.get(1);
            }
            case 9: //To Send Story Of ListFriend
            {
                return (boolean) list.get(1);
            }
            case 10: //To UnBlock The User
            {
                return (boolean) list.get(1);
            }
            case 11: //To Send The Specific List Friend To user :)
            {
                return (boolean) list.get(1);
            }
            case 12: {
                Message message = new Message();
                message.setObject((String) list.get(1));
                message.setSenderEmail((String) list.get(2));

                for (int i = 0; i < chatsList.size(); i++) {
                    if (chatsList.get(i).getFriendEmail().equals(message.getSenderEmail()))
                        chatsList.get(i).addMessage(message);
                }
                isSignedIn();
                list.set(3, email);
                list.set(4, true);
                try {
                    output.writeObject(list);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            case 13: {
                Message message = new Message(list);
                for (int i = 0; i < chatsList.size(); i++) {
                    if (chatsList.get(i).getFriendEmail().equals(message.getSenderEmail()))
                        chatsList.get(i).addMessage(message);
                }
                if (newMessagesListener != null) newMessagesListener.onChange();
            }

        }
        return (boolean) list.get(1);
    }


}
