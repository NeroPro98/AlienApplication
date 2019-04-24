package zeon.com.chatapplication.Model;

import java.io.Serializable;
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

    public UserProfile()
    {
        userName ="";
        password ="";
        email = "";
        birthDate = new Date();
        userFriends = new ArrayList<>();
        blockList = new ArrayList<>();
        joinDate = new Date();
        currMessages = new ArrayList<>();
    }


    public boolean addFriend(UserProfile friend){
        boolean searchInFriendList = searchInFriendList(friend.getUserName());
        if(!searchInFriendList)
            return false;
        userFriends.add(friend.getUserName());
        return true;
    }
    public boolean deleteFriend(String friendName){
        boolean searchInFriendList = searchInFriendList(friendName);
        if(!searchInFriendList)
            return false;
        userFriends.remove(friendName);
        return true;
    }
    public boolean blockUser(String name){
        boolean searchInBlockList = searchInBlockList(name);
        if(!searchInBlockList)
            return false;
        blockList.add(name);
        return true;}
    public boolean unblockUser(String name){
        boolean searchInBlockList = searchInBlockList(name);
        if(!searchInBlockList)
            return false;
        blockList.remove(name);
        return true;
    }
    public boolean searchInFriendList(String name){
        for(String str:userFriends)
        {
            if(str.equals(name))
                return true;
        }
        return false;
    }
    public boolean searchInBlockList(String name){
        for(String str:blockList)
        {
            if(str.equals(name))
                return true;
        }
        return false;
    }
    public void handleCurrMessages(){}
    public ArrayList<Message> checkTheServer(){return null;}
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
}
