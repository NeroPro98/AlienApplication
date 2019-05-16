package zeon.com.chatapplication.Model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;

public class UserProfile implements Serializable {
    private String userName;
    private String password;
    private String email;
    private Date birthDate;
    private ArrayList<String> userFriends;
    private ArrayList<String> blockList;
    private Date joinDate;
    private ArrayList<Message> currMessages;
    private String userId;
    private String story;
    public transient ObjectOutputStream output;
    public transient ObjectInputStream input;
    private transient Socket Connection;
    private String IPString;
    private String PortString;

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
        currMessages = new ArrayList<>();
        story = "";
        IPString = "10.0.2.2";
        PortString = "0000";
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
        System.out.println("The Ip is " + IPString);
        Connection = new Socket(IPString, 6789);  // here we setup the connection to specific server of IP address to specific port on this server Port:
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
    public boolean handleReceivedRequest(ArrayList<Object> list) {

        int type = (int) list.get(0);
        switch (type) {
            case 0://Add User
            {
                return (boolean) list.get(1);

            }
            case 1: //Sign in
            {
                return (boolean) list.get(1);
            }
            case 2:// edit user info
            {
                return (boolean) list.get(1);
            }
            case 3: {
                return (boolean) list.get(1);
            }
            case 4://bring friends list
            {
                return (boolean) list.get(1);
            }

        }
        return (boolean) list.get(1);
    }


}
