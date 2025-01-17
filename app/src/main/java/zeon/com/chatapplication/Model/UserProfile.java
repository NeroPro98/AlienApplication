package zeon.com.chatapplication.Model;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.text.format.Formatter;
import android.widget.ImageView;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;

import zeon.com.chatapplication.Chats;
import zeon.com.chatapplication.MyApplication;

import static android.content.Context.WIFI_SERVICE;

public class UserProfile implements Serializable {
    private String userName;
    private String password;
    private String email;
    private Date birthDate;
    private ArrayList<String> userFriends;
    private ArrayList<String> blockList;
    private Date joinDate;
    private ArrayList<Chat_Model> chatsList;
    private ArrayList<Message> currMessages = new ArrayList<>();
    private String userId;
    private String story;
    public  transient ObjectOutputStream output;
    public  transient ObjectInputStream input;
    private transient Socket Connection;
    public  String IPString;
    public  transient String PortString;
    private boolean signedIn;
    private transient onValueChangeListener valueChangeListener;
    private transient onValueChangeListener newMessagesListener;
    private transient onValueChangeListener Help14Listener;
    private transient onValueChangeListener Help11Listener;
    private transient onValueChangeListener Help4Listener;
    public  ArrayList<Object> User_Friend_Info = new ArrayList<>();
    private ArrayList<Object> User_List;
    private ArrayList<String> User_Friend_Name = new ArrayList<>();
    private ArrayList<String> User_Friend_Emails = new ArrayList<>();
    private ArrayList<Object> User_Block_List = new ArrayList<>();
    private ArrayList<Object> The_User_List_Info_File = new ArrayList<>();
    private ArrayList<Object> The_User_Hwo_Chat_With_Him = new ArrayList<>();
    public ArrayList<Object> Help11 = new ArrayList<>();
    public ArrayList<Object> Help4 = new ArrayList<>();
    public ArrayList<Object> Help14 = new ArrayList<>();

public int helpInt;
    public onValueChangeListener getHelp14Listener() {
        return Help14Listener;
    }

    public void setHelp14Listener(onValueChangeListener help14Listener) {
        Help14Listener = help14Listener;
    }

    private ImageView UserImage;
    public ArrayList<MessagePerson> The_User_Chat_Containt = new ArrayList<>();

    public int friendnum ;
    public int friendbolck ;
    public int friendfollower ;



    public ImageView getUserImage() {
        return UserImage;
    }

    public void setUserImage(ImageView userImage) {
        UserImage = userImage;
    }

    public ArrayList<MessagePerson> getThe_User_Chat_Containt() {
//        The_User_Chat_Containt.remove(2);
        return The_User_Chat_Containt;
    }

    public onValueChangeListener getHelp4Listener() {
        return Help4Listener;
    }

    public void setHelp4Listener(onValueChangeListener help4Listener) {
        Help4Listener = help4Listener;
    }

    public onValueChangeListener getHelp11Listener() {
        return Help11Listener;
    }

    public void setHelp11Listener(onValueChangeListener help11Listener) {
        Help11Listener = help11Listener;
    }

    public void setThe_User_Chat_Containt(ArrayList<MessagePerson> the_User_Chat_Containt) {
        The_User_Chat_Containt = the_User_Chat_Containt;
    }

    public void addThe_User_Chat_Containt(MessagePerson user) {
        The_User_Chat_Containt.add(user);
    }

    public int getFriendnum() {
        return friendnum;
    }

    public void setFriendnum(int friendnum) {
        this.friendnum = friendnum;
    }

    public int getFriendbolck() {
        return friendbolck;
    }

    public void setFriendbolck(int friendbolck) {
        this.friendbolck = friendbolck;
    }

    public int getFriendfollower() {
        return friendfollower;
    }

    public void setFriendfollower(int friendfollower) {
        this.friendfollower = friendfollower;
    }



    public ArrayList<Message> getCurrMessages() {
        ArrayList<Message> tmp = new ArrayList<>(currMessages);
        currMessages = new ArrayList<>();
        return tmp;
    }

    public void setCurrMessages(ArrayList<Message> currMessages) {
        this.currMessages = currMessages;
    }

    public ArrayList<Object> getThe_User_Hwo_Chat_With_Him() {
        return The_User_Hwo_Chat_With_Him;
    }

    public void setThe_User_Hwo_Chat_With_Him(ArrayList<Object> the_User_Hwo_Chat_With_Him) {
        The_User_Hwo_Chat_With_Him = the_User_Hwo_Chat_With_Him;
    }

    public void setThe_User_Hwo_Chat_With_Him_Add_ToList(ArrayList<Object> the_User_Hwo_Chat_With_Him) {
        for(int i=0;i<the_User_Hwo_Chat_With_Him.size();i++) {
            The_User_Hwo_Chat_With_Him.add(the_User_Hwo_Chat_With_Him.get(i));
        }
    }


    //private static Context mContext;


    public ArrayList<Object> getThe_User_List_Info_File() {
        return The_User_List_Info_File;
    }

    public void setThe_User_List_Info_File(ArrayList<Object> the_User_List_Info_File) {
        The_User_List_Info_File = the_User_List_Info_File;
    }

    public void setThe_User_List_Info_File_Element(ArrayList<Object>  Any){
        The_User_List_Info_File.add(Any);
    }


    public ArrayList<Object> getUser_Block_List() {
        return User_Block_List;
    }

    public void setUser_Block_List(ArrayList<Object> user_Block_List) {
        User_Block_List = user_Block_List;
    }
    public void setUser_Block_List_String(String user_Block_List) {
        User_Block_List.add(user_Block_List);
    }



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




    public ArrayList<Object> getUser_List() {
        return User_List;
    }

    public void setUser_List(ArrayList user_List) {
        User_List = user_List;
    }



    public ArrayList<Object> getUser_Friend_Info() {
   //     User_Friend_Info.remove(0);
    //    User_Friend_Info.remove(0);
     //   User_Friend_Info.remove(0);
      //  User_Friend_Info.remove(0);
        return User_Friend_Info;
    }

    public void setUser_Friend_Info(Object user_Friend_Info) {
        User_Friend_Info.add(user_Friend_Info);
    }

    public void setUser_Friend_Info_List(ArrayList user_Friend_Info) {
        User_Friend_Info = user_Friend_Info;
    }



 //   MyApplication data;

    public interface onValueChangeListener {
        void onChange();
    }

    public void setValueChangeListener(onValueChangeListener valueChangeListener) {
        this.valueChangeListener = valueChangeListener;
    }

    public void setMessageListener(onValueChangeListener listener){
        this.newMessagesListener = listener;
    }
    public onValueChangeListener getMessageListener(){
        return this.newMessagesListener;
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

    public transient HandleThread handleThread;

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
            while(true) {
                try {
                    List = (ArrayList<Object>) input.readObject();
                    System.out.println("IP " + IPString);
                    System.out.println(handleReceivedRequest(List));
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
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

    public Chat_Model getChat(String friendEmail) {
        for (Chat_Model c : chatsList) {
            if (c.getFriendEmail().equals(friendEmail))
                return c;
        }
        return null;
    }
    public void setChatModelList(Chat_Model msg){
        chatsList.add(msg);
    }

    public ArrayList<Chat_Model> getChatModelList(){
        return chatsList;
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


    public void connectToServer() throws IOException {
        System.out.println("Connecting to Server");
        IPString = "192.168.43.143";
        System.out.println(InetAddress.getLocalHost());
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

    public void setBlockListString(String blockList) {
        this.blockList.add(blockList);
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
                            app.JustNoThing.add((String)list.get(i));
                            //app.user.setUser_Friend_Name((String)list.get(i));
                            j++;
                        }
                    }
                    app.user.setUser_Friend_Info_List(app.JustNoThing);
                    for(int i =0 ; i<list.size();i =i+2){
                        if((list.get(i).equals("0"))) {
                            break;
                        }
                        else {
                            if(getUser_Friend_Name().size()!=0) {
                                for (int k = 0; k < getUser_Friend_Name().size(); k++) {
                                    if (!list.get(i).equals(getUser_Friend_Name().get(k))) {
                                        // but remove then bool
                                        app.user.setUser_Friend_Name((String) list.get(i));
                                    }
                                }
                            }else{
                                app.user.setUser_Friend_Name((String) list.get(i));
                            }
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
                Help4 = list;
                if(Help4Listener != null)Help4Listener.onChange();
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
                Help11 = list;
                if(Help11Listener != null)Help11Listener.onChange();
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
                break;

            }
            case 13: {
                Message message = new Message(list);
                System.out.println("TAG"+message.getObject().toString());
                currMessages = new ArrayList<>();
                currMessages.add(message);
                if (newMessagesListener != null) newMessagesListener.onChange();
                break;
            }
            case 14:{  //Check if user blocked

                Help14 = list;
                if(Help14Listener!=null)Help14Listener.onChange();
                return true;
            }

            case 19:{
                return (boolean) list.get(1);
            }
            case 100:
            {
                System.out.println(list.get(1));
                list = new ArrayList<>();
                list.add(true);
                list.add(true);
            }

        }
        return true;
    }

    public UserProfile getUserObject(){
        return this;
    }


}
